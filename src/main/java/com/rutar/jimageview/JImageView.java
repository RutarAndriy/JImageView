package com.rutar.jimageview;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import javax.swing.event.*;

// ............................................................................

/**
 * Клас JImageView
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageView extends JScrollPane {

// ............................................................................

private static final Cursor CURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
private static final Cursor CURSOR_MOVE = new Cursor(Cursor.MOVE_CURSOR);

// ............................................................................

// Масив стандартних масштабів
private final int[] scales =
    { 10,  15,  20,  25,   30,   35,   40,   45,   50,
                           60,   70,   80,   90,  100,
          125, 150, 175,  200,  225,  250,  275,  300,
                                350,  400,  450,  500,
                                600,  700,  800,  900,
                         1000, 1500, 2000, 2500, 3000 };

// ............................................................................

private static ArrayList <JImageViewListener> listeners = null;
private static transient PropertyChangeSupport propertyChangeSupport = null;
private static Cursor CURSOR_DEFAULT = null;

// ............................................................................

private JPanel panelRoot;

private boolean drugImageOut = true;         // Переміщення за межею компонента

private boolean gridVisible = true;                          // Фонова сітка
private Color gridLightColor = Color.LIGHT_GRAY;       // I колір фонової сітки
private Color gridDarkColor  = Color.DARK_GRAY;       // II колір фонової сітки
private int gridSize = 25;                                      // Розмір сітки

private int imageScale = 100;                             // Масштаб зображення

private Image image = null;                            // Зображення для показу
private Image errorImage = null;      // Зображення яке показується при помилці

private int imageScaleW;                    // Ширина масштабованого зображення
private int imageScaleH;                    // Висота масштабованого зображення

private int globalScaleMin = scales[0];
private int globalScaleMax = scales[scales.length - 1];

private int imageScaleMax;             // Мінімальний масштаб заного зображення
private int imageScaleMin;            // Максимальний масштаб заного зображення
private int imageScaleInternalFit;       // Масштаб для внутрішнього заповнення
private int imageScaleExternalFit;        // Масштаб для зовнішнього заповнення

// ............................................................................

private Scale_Quality scaleQuality = Scale_Quality.SMOOTH;

private boolean lmbEnable   = true;
private boolean cmbEnable   = true;
private boolean rmbEnable   = true;
private boolean wheelEnable = true;
private boolean wheelInvert = false;

public enum Scale_Quality { FAST, SMOOTH }

private JScrollBar hScrollBar, vScrollBar;

private Rectangle   regionRect;
private BasicStroke regionStroke;
private BasicStroke regionStrokeAdditional;

///////////////////////////////////////////////////////////////////////////////

public JImageView() { initComponents(); }

///////////////////////////////////////////////////////////////////////////////

@SuppressWarnings("unchecked")                       
private void initComponents() {

panelRoot = new RootPane();

setRegionStroke(null);
setErrorImage(null);
setImage(null);

setWheelScrollingEnabled(false);

getViewport().addMouseListener(mouseListener);
getViewport().addChangeListener(changeListener);
getViewport().addMouseMotionListener(mouseMotionListener);
getViewport().addMouseWheelListener((MouseWheelListener) mouseListener);

setViewportView(panelRoot);

}

///////////////////////////////////////////////////////////////////////////////

/**
 * Клас RootPane
 * Реалізує 
 */
private final class RootPane extends JPanel {

@Override
public void paintComponent (Graphics g) {

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    
    if (gridVisible) {

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_OFF);

        g2.setColor(gridLightColor);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(gridDarkColor);

        for (int col = 0; col < getWidth();  col += gridSize) {
        for (int row = 0; row < getHeight(); row += gridSize*2) {
            g.fillRect(col, row + (col/gridSize%2 == 0 ? gridSize : 0),
                       gridSize, gridSize);
        }
        }
    
    }
    
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    
    int iX = getWidth()/2  - imageScaleW/2;
    int iY = getHeight()/2 - imageScaleH/2;
    
    if (imageScale >= 100 || scaleQuality == Scale_Quality.FAST)
        { g2.drawImage(image, iX, iY, imageScaleW, imageScaleH, null); }
    else
        { g2.drawImage(getSmoothThumbnail(), iX, iY, null); }

//    g2.setColor(Color.WHITE);
//    g2.setStroke(regionStrokeAdditional);
//    g2.drawRect(iX, iY, imageScaleW, imageScaleH);
//    g2.setColor(Color.BLACK);
//    g2.setStroke(regionStroke);
//    g2.drawRect(iX, iY, imageScaleW, imageScaleH);

}
}
    
///////////////////////////////////////////////////////////////////////////////
// Активні методи - для виконання певних маніпуляцій із зображенням ///////////
///////////////////////////////////////////////////////////////////////////////

/**
 * Збільшення відображуваного зображення
 * @param origin точка, відносно якої відбувається збільшення.
 * Якщо не задана, то використовується центр компонента
 */
public void zoomIn (Point origin) {

    if (imageScale >= imageScaleMax) { return; }
    int scaleValue = imageScale;

    if (origin == null) { origin = new Point(getWidth()/2,
                                             getHeight()/2); }
    
    Point2D.Float oldPosition = getPercentagePoint(origin);
    
    for (int z = 0; z < scales.length; z++) {
        if (scaleValue < scales[z]) {
            scaleValue = scales[z];
            setImageScale(scaleValue);
            break;
        }
    }
    
    Point2D.Float newPosition = getPercentagePoint(origin);

    panelRoot.scrollRectToVisible
             (calculateScrollParams(oldPosition, newPosition));
    
}

///////////////////////////////////////////////////////////////////////////////

/**
 * Зменшення відображуваного зображення
 * @param origin точка, відносно якої відбувається зменшення.
 * Якщо не задана, то використовується центр компонента
 */
public void zoomOut (Point origin) {
    
    if (imageScale <= imageScaleMin) { return; }
    int scaleValue = imageScale;

        if (origin == null) { origin = new Point(getWidth()/2,
                                                 getHeight()/2); }

    Point2D.Float oldPosition = getPercentagePoint(origin);

    for (int z = scales.length - 1; z >= 0; z--) {
        if (scaleValue > scales[z]) {
            scaleValue = scales[z];
            setImageScale(scaleValue);
            break;
        }
    }

    Point2D.Float newPosition = getPercentagePoint(origin);

    panelRoot.scrollRectToVisible
             (calculateScrollParams(oldPosition, newPosition));
    
}

///////////////////////////////////////////////////////////////////////////////

/** Задання мінімально можливого розміру зображення + центрування */
public void minimize() { setImageScale(imageScaleMin);
                         center(); }

///////////////////////////////////////////////////////////////////////////////

/** Задання максимально можливого розміру зображення + центрування */
public void maximize() { setImageScale(imageScaleMax);
                         center(); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Задання розміру зображення таким чином, щоб його 
 * більша сторона максимально замістила доступний простір компонента
 */
public void fitInternal() { setImageScale(imageScaleInternalFit); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Задання розміру зображення таким чином, щоб його 
 * менша сторона максимально замістила доступний простір компонента
 */
public void fitExternal() { setImageScale(imageScaleExternalFit); }

///////////////////////////////////////////////////////////////////////////////

/** 
 * Переміщує зображння таким чином, щоб воно відображалося в центрі компонента.
 * Метод доцільно використовувати лише тоді, коли зображення не поміщається 
 * в межах компонента і є доступними смуги прокручування
 */
public void center() {
    
    if (!isValid()) { validate(); }
    
    Dimension size = panelRoot.getSize();
    Rectangle viewRect = getViewport().getViewRect();

    viewRect.x = (size.width  - viewRect.width)  / 2;
    viewRect.y = (size.height - viewRect.height) / 2;
    
    panelRoot.scrollRectToVisible(viewRect);
    
}

///////////////////////////////////////////////////////////////////////////////

/** Відновлення оригінального розміру відображуваного зображення */
public void zoomToOriginal() { setImageScale(100); }

///////////////////////////////////////////////////////////////////////////////
// Getter'и та Setter'и - повертають та задають властивості компонента ////////
///////////////////////////////////////////////////////////////////////////////

public Image getImage() { return image; }

public void setImage (Image image)
    { if (image == null) { image = getErrorImage(); }       
      Image oldValue = this.image;
      this.image = image;
      zoomToOriginal();
      fireEvent("image", oldValue, image);
      getPropertyChangeSupport()
     .firePropertyChange("image", oldValue, image); }

///////////////////////////////////////////////////////////////////////////////

public Image getErrorImage() { return errorImage; }

public void setErrorImage (Image errorImage)
    { if (errorImage == null) { errorImage = getRandomImage(); }
      Image oldValue = this.errorImage;
      this.errorImage = errorImage;
      fireEvent("errorImage", oldValue, errorImage);
      getPropertyChangeSupport()
     .firePropertyChange("errorImage", oldValue, errorImage); }

///////////////////////////////////////////////////////////////////////////////

public Scale_Quality getScaleQuality() { return scaleQuality; }

public void setScaleQuality (Scale_Quality scaleQuality)
    { Scale_Quality oldValue = this.scaleQuality;
      this.scaleQuality = scaleQuality;
      panelRoot.repaint();
      fireEvent("scaleQuality", oldValue, scaleQuality);
      getPropertyChangeSupport()
     .firePropertyChange("scaleQuality", oldValue, scaleQuality); }

///////////////////////////////////////////////////////////////////////////////

public BasicStroke getRegionStroke() { return regionStroke; }

public void setRegionStroke (BasicStroke regionStroke)
    { if (regionStroke == null) {
          regionStroke = new BasicStroke(2f, BasicStroke.CAP_BUTT, 
                                             BasicStroke.JOIN_MITER, 10, 
                                             new float[] { 3, 3 }, 0); }
    
      regionStrokeAdditional = new BasicStroke(regionStroke.getLineWidth(),
                                               regionStroke.getEndCap(),
                                               regionStroke.getLineJoin());
      
      // ......................................................................
      
      BasicStroke oldValue = this.regionStroke;
      this.regionStroke = regionStroke;
      fireEvent("regionStroke", oldValue, regionStroke);
      getPropertyChangeSupport()
     .firePropertyChange("regionStroke", oldValue, regionStroke); }

///////////////////////////////////////////////////////////////////////////////

public boolean isLMBEnable() { return lmbEnable; }

public void setLMBEnable (boolean lmbEnable)
    { boolean oldValue = this.lmbEnable;
      this.lmbEnable = lmbEnable;
      fireEvent("lmbEnable", oldValue, lmbEnable);
      getPropertyChangeSupport()
     .firePropertyChange("lmbEnable", oldValue, lmbEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isCMBEnable() { return cmbEnable; }

public void setCMBEnable (boolean cmbEnable)
    { boolean oldValue = this.cmbEnable;
      this.cmbEnable = cmbEnable;
      fireEvent("cmbEnable", oldValue, cmbEnable);
      getPropertyChangeSupport()
     .firePropertyChange("cmbEnable", oldValue, cmbEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isRMBEnable() { return rmbEnable; }

public void setRMBEnable (boolean rmbEnable)
    { boolean oldValue = this.rmbEnable;
      this.rmbEnable = rmbEnable;
      fireEvent("rmbEnable", oldValue, rmbEnable);
      getPropertyChangeSupport()
     .firePropertyChange("rmbEnable", oldValue, rmbEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isWheelEnable() { return wheelEnable; }

public void setWheelEnable (boolean wheelEnable)
    { boolean oldValue = this.wheelEnable;
      this.wheelEnable = wheelEnable;
      fireEvent("wheelEnable", oldValue, wheelEnable);
      getPropertyChangeSupport()
     .firePropertyChange("wheelEnable", oldValue, wheelEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isWheelInvert() { return wheelInvert; }

public void setWheelInvert (boolean wheelInvert)
    { boolean oldValue = this.wheelInvert;
      this.wheelInvert = wheelInvert;
      fireEvent("wheelInvert", oldValue, wheelInvert);
      getPropertyChangeSupport()
     .firePropertyChange("wheelEnable", oldValue, wheelInvert); }

///////////////////////////////////////////////////////////////////////////////

public boolean isDrugImageOut() { return drugImageOut; }

public void setDrugImageOut (boolean drugImageOut)
    { boolean oldValue = this.drugImageOut;
      this.drugImageOut = drugImageOut;
      fireEvent("drugImageOut", oldValue, drugImageOut);
      getPropertyChangeSupport()
     .firePropertyChange("drugImageOut", oldValue, drugImageOut); }

///////////////////////////////////////////////////////////////////////////////

public boolean isGridVisible() { return gridVisible; }

public void setGridVisible (boolean gridVisible)
    { boolean oldValue = this.gridVisible;
      this.gridVisible = gridVisible;
      panelRoot.repaint();
      fireEvent("gridVisible", oldValue, gridVisible);
      getPropertyChangeSupport()
     .firePropertyChange("gridVisible", oldValue, gridVisible); }

///////////////////////////////////////////////////////////////////////////////

public Color getGridLightColor() { return gridLightColor; }

public void setGridLightColor (Color gridLightColor)
    { if (gridLightColor == null) { gridLightColor = Color.LIGHT_GRAY; }
      Color oldValue = this.gridLightColor;
      this.gridLightColor = gridLightColor;
      panelRoot.repaint();
      fireEvent("gridLightColor", oldValue, gridLightColor);
      getPropertyChangeSupport()
     .firePropertyChange("gridLightColor", oldValue, gridLightColor); }

///////////////////////////////////////////////////////////////////////////////

public Color getGridDarkColor() { return gridDarkColor; }

public void setGridDarkColor (Color gridDarkColor)
    { if (gridDarkColor == null) { gridDarkColor = Color.DARK_GRAY; }
      Color oldValue = this.gridDarkColor;
      this.gridDarkColor = gridDarkColor;
      panelRoot.repaint();
      fireEvent("gridDarkColor", oldValue, gridDarkColor);
      getPropertyChangeSupport()
     .firePropertyChange("gridDarkColor", oldValue, gridDarkColor); }

///////////////////////////////////////////////////////////////////////////////

public int getGridSize() { return gridSize; }

public void setGridSize (int gridSize)
    { if      (gridSize > 99) { gridSize = 99; }
      else if (gridSize <  3) { gridSize = 3;  }
      int oldValue = this.gridSize;
      this.gridSize = gridSize;
      panelRoot.repaint();
      fireEvent("gridSize", oldValue, gridSize);
      getPropertyChangeSupport()
     .firePropertyChange("gridSize", oldValue, gridSize); }

///////////////////////////////////////////////////////////////////////////////

public int getImageScale() { return imageScale; }

public void setImageScale (int imageScale)
    { calculateImageLimitScale();
      if      (imageScale > globalScaleMax) { imageScale = globalScaleMax; }
      else if (imageScale < globalScaleMin) { imageScale = globalScaleMin; }
      if      (imageScale > imageScaleMax)  { imageScale = imageScaleMax;  }
      else if (imageScale < imageScaleMin)  { imageScale = imageScaleMin;  }
      int oldValue = this.imageScale;
      this.imageScale = imageScale;
      calculateScaledImageSize();
      panelRoot.setPreferredSize(new Dimension(imageScaleW, imageScaleH));
      panelRoot.updateUI();
      fireEvent("imageScale", oldValue, imageScale);
      getPropertyChangeSupport()
     .firePropertyChange("imageScale", oldValue, imageScale); }

///////////////////////////////////////////////////////////////////////////////

@Override
public void addPropertyChangeListener (PropertyChangeListener listener)
    { getPropertyChangeSupport().addPropertyChangeListener(listener); }

///////////////////////////////////////////////////////////////////////////////

@Override
public void removePropertyChangeListener (PropertyChangeListener listener)
    { getPropertyChangeSupport().removePropertyChangeListener(listener); }

///////////////////////////////////////////////////////////////////////////////

private PropertyChangeSupport getPropertyChangeSupport() {
    if (propertyChangeSupport == null) {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    return propertyChangeSupport;
}

///////////////////////////////////////////////////////////////////////////////

public void addJImageViewListener (JImageViewListener listener)
    { getListeners().add(listener); }

///////////////////////////////////////////////////////////////////////////////

public void removeJImageViewListener (JImageViewListener listener)
    { getListeners().remove(listener); }

///////////////////////////////////////////////////////////////////////////////

private ArrayList <JImageViewListener> getListeners()
    { if (listeners == null) { listeners = new ArrayList<>(); }
      return listeners; }

///////////////////////////////////////////////////////////////////////////////

private void fireEvent (String type, Object oldValue, Object newValue) {

JImageViewEvent event = new JImageViewEvent(this, oldValue, newValue);

for (JImageViewListener listener : getListeners()) {

    switch (type) {
        case "smile"      -> listener.smileChange(event);
        case "lineWidth"  -> listener.lineWidthChange(event);
        case "mouthWidth" -> listener.mouthWidthChange(event);
        case "background" -> listener.backgroundChange(event);
        case "foreground" -> listener.foregroundChange(event);
    }
}
}

///////////////////////////////////////////////////////////////////////////////

private void calculateImageLimitScale() {
    
    int w = image.getWidth(null);
    int h = image.getHeight(null);

    int q = (w > h) ? h : w;
    int z = (w > h) ? w : h;
    
    imageScaleMin = (int)(48d   / q * 100);
    imageScaleMax = (int)(7000d / z * 100);
    
    if (imageScaleMin > 100) { imageScaleMin = 100; }
    if (imageScaleMax < 100) { imageScaleMax = 100; }
    
}

///////////////////////////////////////////////////////////////////////////////

private void calculateImageFitScale() {
    
    // Ширина зображення
    int iW = image.getWidth(null);
    // Ширина області перегляду
    int vW = getViewport().getWidth();
    // Ширина вертикального скролбару
    int mW = vScrollBar.getMaximumSize().width;
    // Активна ширина вертикального скролбару
    int sW = vScrollBar.isVisible() ? mW : 0;
    // Комія змінної sW, використовується для розрахунку imageScaleExternalFit
    int eW = sW;
    
    // Висота зображення
    int iH = image.getHeight(null);
    // Висота області перегляду
    int vH = getViewport().getHeight();
    // Висота горизонтального скролбару
    int mH = hScrollBar.getMaximumSize().height;
    // Активна висота горизонтального скролбару
    int sH = hScrollBar.isVisible() ? mH : 0;
    // Комія змінної sH, використовується для розрахунку imageScaleExternalFit
    int eH = sH;
    
    if      (getVerticalScrollBarPolicy() ==
        VERTICAL_SCROLLBAR_ALWAYS)   { sW = 0; }
    else if (getVerticalScrollBarPolicy() ==
        VERTICAL_SCROLLBAR_NEVER)    { eW = mW; }
    if      (getHorizontalScrollBarPolicy() ==
        HORIZONTAL_SCROLLBAR_ALWAYS) { sH = 0; }
    else if (getHorizontalScrollBarPolicy() ==
        HORIZONTAL_SCROLLBAR_NEVER)  { eH = mH; }
    
    int fitWi = (int)(100d * (vW + sW) / iW);
    int fitHi = (int)(100d * (vH + sH) / iH);
    
    int fitWe = (int)(100d * (vW + eW - mW) / iW);
    int fitHe = (int)(100d * (vH + eH - mH) / iH);
    
    imageScaleInternalFit = (fitWi < fitHi) ? fitWi : fitHi;
    imageScaleExternalFit = (fitWe > fitHe) ? fitWe : fitHe;

}

///////////////////////////////////////////////////////////////////////////////

private void calculateScaledImageSize() {
    
    imageScaleW = (int)(image.getWidth(null)  * imageScale / 100d);
    imageScaleH = (int)(image.getHeight(null) * imageScale / 100d);
    
}

///////////////////////////////////////////////////////////////////////////////

private Point2D.Float getPercentagePoint (Point origin) {

    if (!isValid()) { validate(); }
    var point = SwingUtilities.convertPoint(getViewport(), origin, panelRoot);
    
    return new Point2D.Float(point.x * 100f / panelRoot.getWidth(),
                             point.y * 100f / panelRoot.getHeight());

}

///////////////////////////////////////////////////////////////////////////////

private Rectangle calculateScrollParams (Point2D.Float oldPosition,
                                         Point2D.Float newPosition) {
    
    int Dx = (int)((oldPosition.x - newPosition.x)
              * panelRoot.getWidth()  / 100);
    int Dy = (int)((oldPosition.y - newPosition.y)
              * panelRoot.getHeight() / 100); 

    Rectangle params = getViewport().getViewRect();

    params.x += Dx;
    params.y += Dy;

    return params;

}

///////////////////////////////////////////////////////////////////////////////

private Image getRandomImage() {
    
    String path = "/com/rutar/jimageview/images/%s.png";
    String[] names = { "tree", "fire", "wave" };

    //int index = (int)(Math.random() * 3);
    int index = 0;
    URL resource = getClass().getResource(String.format(path, names[index]));

    try { return ImageIO.read(resource); }
    catch (IOException e) { return null;}

}

///////////////////////////////////////////////////////////////////////////////

private BufferedImage getSmoothThumbnail() {

BufferedImage thumbnail = (BufferedImage) image;

int w = thumbnail.getWidth();
int h = thumbnail.getHeight();

int type = (thumbnail.getTransparency() ==
            Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB :
                                   BufferedImage.TYPE_INT_ARGB;

do {
    
if (w > imageScaleW) { w /= 2; if (w < imageScaleW) { w = imageScaleW; } }
if (h > imageScaleH) { h /= 2; if (h < imageScaleH) { h = imageScaleH; } }

BufferedImage tmp = new BufferedImage(w, h, type);
Graphics2D g2 = tmp.createGraphics();
g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);

g2.drawImage(thumbnail, 0, 0, w, h, null);
g2.dispose();

thumbnail = tmp;

} while (w != imageScaleW || h != imageScaleH);

return thumbnail;

}

///////////////////////////////////////////////////////////////////////////////

private Point getPointOnImage (MouseEvent me) {
    return SwingUtilities.convertMouseEvent(getViewport(), me, panelRoot)
                         .getPoint();
}

///////////////////////////////////////////////////////////////////////////////

private boolean isScrollBarVisible()
    { hScrollBar = getHorizontalScrollBar();
      vScrollBar = getVerticalScrollBar();
      
      return hScrollBar.isVisible() || vScrollBar.isVisible(); }

///////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////

private Point origin;
private JViewport imageViewport;

private boolean cursorOnImage;
private boolean scrollBarVisible;

// ............................................................................

private final MouseListener mouseListener
        = new MouseAdapter() {

@Override
public void mouseEntered (MouseEvent me) { cursorOnImage = true; }

@Override
public void mouseExited (MouseEvent me) { cursorOnImage = false; }

// ............................................................................

@Override
public void mousePressed (MouseEvent me) {
    
    switch (me.getButton()) {
        
        // Ліва клавіша миші
        case MouseEvent.BUTTON1 -> {
            origin = getPointOnImage(me);
            panelRoot.setCursor(isScrollBarVisible() ? CURSOR_HAND :
                                                       CURSOR_DEFAULT);
        }
        
        // Середня клавіша миші
        case MouseEvent.BUTTON2 -> {
        
            if (!cmbEnable) { return; }
            
            if (imageScale == 100)                        { fitInternal();    }
            else if (imageScale == imageScaleInternalFit) { fitExternal();    }
            else                                          { zoomToOriginal(); }
        
        }
    }
}

// ............................................................................

@Override
public void mouseReleased (MouseEvent me) {
    
    switch (me.getButton()) {
        
        // Ліва клавіша миші
        case MouseEvent.BUTTON1 -> {
            origin = null;
            panelRoot.setCursor(CURSOR_DEFAULT);
        }     
    }
}

// ............................................................................

@Override
public void mouseWheelMoved (MouseWheelEvent mwe) {
    
    if (mwe.getWheelRotation() > 0)
        { if (wheelInvert) { zoomOut(mwe.getPoint()); }
          else             { zoomIn(mwe.getPoint());  } }
    else
        { if (wheelInvert) { zoomIn(mwe.getPoint());  }
          else             { zoomOut(mwe.getPoint()); } }

}
};

///////////////////////////////////////////////////////////////////////////////

public final MouseMotionListener mouseMotionListener
       = new MouseMotionAdapter() {

@Override
public void mouseDragged (MouseEvent me) {

    if (origin != null) {
        
        if (!drugImageOut && !cursorOnImage) { return; }

            Point point = getPointOnImage(me);
            
            int deltaX = origin.x - point.x;
            int deltaY = origin.y - point.y;
            
            Rectangle view = getViewport().getViewRect();
            view.x += deltaX;
            view.y += deltaY;

            panelRoot.scrollRectToVisible(view);
            
    }
}
};

///////////////////////////////////////////////////////////////////////////////

private final ChangeListener changeListener = new ChangeListener() {

    @Override
    public void stateChanged (ChangeEvent e) {
        
        // Дуже важлива строка коду !!!
        // Якщо компонент не валідований, його необхідно обов'язково валідувати
        // Якщо цього не зробити, компонент повертатиме некоректні властивості
        if (!isValid()) { validate(); }
        
        if (scrollBarVisible != isScrollBarVisible()) {

            scrollBarVisible = isScrollBarVisible();
            CURSOR_DEFAULT = scrollBarVisible ? CURSOR_MOVE : null;
            panelRoot.setCursor(CURSOR_DEFAULT);
        
        }
        
        calculateImageFitScale();
        
    }
};

// Кінець класу JImageView ////////////////////////////////////////////////////

}
