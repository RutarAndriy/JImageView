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

private boolean lmbEnable, cmbEnable, rmbEnable, wheelEnable;

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
    
    iX = getWidth()/2  - imageScaleW/2;
    iY = getHeight()/2 - imageScaleH/2;
    
    g2.drawImage(image, iX, iY, imageScaleW, imageScaleH, null);
    
//    g2.setColor(Color.WHITE);
//    g2.setStroke(regionStrokeAdditional);
//    g2.drawRect(iX, iY, imageScaleW, imageScaleH);

//    g2.setColor(Color.BLACK);
//    g2.setStroke(regionStroke);
//    g2.drawRect(iX, iY, imageScaleW, imageScaleH);

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
    iW = image.getWidth(null);
    // Ширина області перегляду
    vW = getViewport().getWidth();
    // Ширина вертикального скролбару
    mW = vScrollBar.getMaximumSize().width;
    // Активна ширина вертикального скролбару
    sW = vScrollBar.isVisible() ? mW : 0;
    // Комія змінної sW, використовується для розрахунку imageScaleExternalFit
    eW = sW;
    
    // Висота зображення
    iH = image.getHeight(null);
    // Висота області перегляду
    vH = getViewport().getHeight();
    // Висота горизонтального скролбару
    mH = hScrollBar.getMaximumSize().height;
    // Активна висота горизонтального скролбару
    sH = hScrollBar.isVisible() ? mH : 0;
    // Комія змінної sH, використовується для розрахунку imageScaleExternalFit
    eH = sH;
    
    if      (getVerticalScrollBarPolicy() ==
        VERTICAL_SCROLLBAR_ALWAYS)   { sW = 0; }
    else if (getVerticalScrollBarPolicy() ==
        VERTICAL_SCROLLBAR_NEVER)    { eW = mW; }
    if      (getHorizontalScrollBarPolicy() ==
        HORIZONTAL_SCROLLBAR_ALWAYS) { sH = 0; }
    else if (getHorizontalScrollBarPolicy() ==
        HORIZONTAL_SCROLLBAR_NEVER)  { eH = mH; }
    
    fitWi = (int)(100d * (vW + sW) / iW);
    fitHi = (int)(100d * (vH + sH) / iH);
    
    fitWe = (int)(100d * (vW + eW - mW) / iW);
    fitHe = (int)(100d * (vH + eH - mH) / iH);
    
    imageScaleInternalFit = (fitWi < fitHi) ? fitWi : fitHi;
    imageScaleExternalFit = (fitWe > fitHe) ? fitWe : fitHe;

}

///////////////////////////////////////////////////////////////////////////////

private void calculateScaledImageSize() {
    
    imageScaleW = (int)(image.getWidth(null)  * imageScale / 100d);
    imageScaleH = (int)(image.getHeight(null) * imageScale / 100d);
    
}

///////////////////////////////////////////////////////////////////////////////

public void zoomIn (Point origin) {

    if (imageScale >= imageScaleMax) { return; }
    int scaleValue = imageScale;
    
    if (origin == null) { origin = SwingUtilities.convertPoint(getViewport(),
                               new Point(getViewport().getWidth()/2,
                                         getViewport().getHeight()/2),
                                         panelRoot); }
    
    if (!isValid()) { validate(); }
    oldPosition = new Point2D.Float(origin.x * 100f / panelRoot.getWidth(),
                                    origin.y * 100f / panelRoot.getHeight());
    
    for (int z = 0; z < scales.length; z++) {
        if (scaleValue < scales[z]) {
            scaleValue = scales[z];
            setImageScale(scaleValue);
            break;
        }
    }
    
    if (!isValid()) { validate(); }
    newPosition = new Point2D.Float(origin.x * 100f / panelRoot.getWidth(),
                                    origin.y * 100f / panelRoot.getHeight());
    
    int Dx = (int)((oldPosition.x - newPosition.x)
                  * panelRoot.getWidth()  / 100);
    int Dy = (int)((oldPosition.y - newPosition.y)
                  * panelRoot.getHeight() / 100); 
    
    imageViewport = (JViewport) SwingUtilities
                    .getAncestorOfClass(JViewport.class, panelRoot);
    
    Rectangle viewRect = imageViewport.getViewRect();

    viewRect.x += Dx;
    viewRect.y += Dy;
    
    panelRoot.scrollRectToVisible(viewRect);
    
}

// ............................................................................

public void zoomOut (Point origin) {
    
    if (imageScale <= imageScaleMin) { return; }
    int scaleValue = imageScale;
    
    if (origin == null) { origin = SwingUtilities.convertPoint(getViewport(),
                               new Point(getViewport().getWidth()/2,
                                         getViewport().getHeight()/2),
                                         panelRoot); }
        
    if (!isValid()) { validate(); }
    oldPosition = new Point2D.Float(origin.x * 100f / panelRoot.getWidth(),
                                    origin.y * 100f / panelRoot.getHeight());
    
    for (int z = scales.length - 1; z >= 0; z--) {
        if (scaleValue > scales[z]) {
            scaleValue = scales[z];
            setImageScale(scaleValue);
            break;
        }
    }
    
    if (!isValid()) { validate(); }
    newPosition = new Point2D.Float(origin.x * 100f / panelRoot.getWidth(),
                                    origin.y * 100f / panelRoot.getHeight());
    
    int Dx = (int)((oldPosition.x - newPosition.x)
                  * panelRoot.getWidth()  / 100);
    int Dy = (int)((oldPosition.y - newPosition.y)
                  * panelRoot.getHeight() / 100); 
    
    imageViewport = (JViewport) SwingUtilities
                    .getAncestorOfClass(JViewport.class, panelRoot);

    Rectangle viewRect = imageViewport.getViewRect();

    viewRect.x += Dx;
    viewRect.y += Dy;
    
    panelRoot.scrollRectToVisible(viewRect);
    
}

// ............................................................................

public void zoomOriginal() { setImageScale(100); }

///////////////////////////////////////////////////////////////////////////////

public void minimize() { setImageScale(imageScaleMin);
                         center(); }
public void maximize() { setImageScale(imageScaleMax);
                         center(); }

///////////////////////////////////////////////////////////////////////////////

public void fitInternal() { setImageScale(imageScaleInternalFit); }
public void fitExternal() { setImageScale(imageScaleExternalFit); }

///////////////////////////////////////////////////////////////////////////////

public void center() {
    
    if (!isValid()) { validate(); }
    
    Dimension size = panelRoot.getSize();
    Rectangle viewRect = getViewport().getViewRect();

    viewRect.x = (size.width  - viewRect.width)  / 2;
    viewRect.y = (size.height - viewRect.height) / 2;
    
    panelRoot.scrollRectToVisible(viewRect);
    
}

///////////////////////////////////////////////////////////////////////////////

public Image getImage() { return image; }

public void setImage (Image image)
    { if (image == null) { image = getErrorImage(); }       
      Image oldValue = this.image;
      this.image = image;
      zoomOriginal();
      fireEvent("image", oldValue, image);
      getPropertyChangeSupport().firePropertyChange("image",
                                                    oldValue, image); }

///////////////////////////////////////////////////////////////////////////////

public Image getErrorImage() { return errorImage; }

public void setErrorImage (Image errorImage)
    { if (errorImage == null) { errorImage = getRandomImage(); }
      Image oldValue = this.errorImage;
      this.errorImage = errorImage;
      fireEvent("errorImage", oldValue, errorImage);
      getPropertyChangeSupport().firePropertyChange("errorImage",
                                                    oldValue, errorImage); }

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

public Scale_Quality getScaleQuality() { return scaleQuality; }

public void setScaleQuality (Scale_Quality scaleQuality)
    { Scale_Quality oldValue = this.scaleQuality;
      this.scaleQuality = scaleQuality;
      panelRoot.repaint();
      fireEvent("scaleQuality", oldValue, scaleQuality);
      getPropertyChangeSupport().firePropertyChange("scaleQuality",
                                                    oldValue, scaleQuality); }

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
      getPropertyChangeSupport().firePropertyChange("regionStroke",
                                                    oldValue, regionStroke); }

///////////////////////////////////////////////////////////////////////////////

public boolean isLMBEnable() { return lmbEnable; }

public void setLMBEnable (boolean lmbEnable)
    { boolean oldValue = this.lmbEnable;
      this.lmbEnable = lmbEnable;
      fireEvent("lmbEnable", oldValue, lmbEnable);
      getPropertyChangeSupport().firePropertyChange("lmbEnable",
                                                    oldValue, lmbEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isCMBEnable() { return cmbEnable; }

public void setCMBEnable (boolean cmbEnable)
    { boolean oldValue = this.cmbEnable;
      this.cmbEnable = cmbEnable;
      fireEvent("cmbEnable", oldValue, cmbEnable);
      getPropertyChangeSupport().firePropertyChange("cmbEnable",
                                                    oldValue, cmbEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isRMBEnable() { return rmbEnable; }

public void setRMBEnable (boolean rmbEnable)
    { boolean oldValue = this.rmbEnable;
      this.rmbEnable = rmbEnable;
      fireEvent("rmbEnable", oldValue, rmbEnable);
      getPropertyChangeSupport().firePropertyChange("rmbEnable",
                                                    oldValue, rmbEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isWheelEnable() { return wheelEnable; }

public void setWheelEnable (boolean wheelEnable)
    { boolean oldValue = this.wheelEnable;
      this.wheelEnable = wheelEnable;
      fireEvent("wheelEnable", oldValue, wheelEnable);
      getPropertyChangeSupport().firePropertyChange("wheelEnable",
                                                    oldValue, wheelEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isDrugImageOut() { return drugImageOut; }

public void setDrugImageOut (boolean drugImageOut)
    { boolean oldValue = this.drugImageOut;
      this.drugImageOut = drugImageOut;
      fireEvent("drugImageOut", oldValue, drugImageOut);
      getPropertyChangeSupport().firePropertyChange("drugImageOut",
                                                    oldValue, drugImageOut); }

///////////////////////////////////////////////////////////////////////////////

public boolean isGridVisible() { return gridVisible; }

public void setGridVisible (boolean gridVisible)
    { boolean oldValue = this.gridVisible;
      this.gridVisible = gridVisible;
      fireEvent("gridVisible", oldValue, gridVisible);
      getPropertyChangeSupport().firePropertyChange("gridVisible",
                                                    oldValue, gridVisible);
      panelRoot.repaint(); }

///////////////////////////////////////////////////////////////////////////////

public Color getGridLightColor() { return gridLightColor; }

public void setGridLightColor (Color gridLightColor)
    { if (gridLightColor == null) { gridLightColor = Color.LIGHT_GRAY; }
      Color oldValue = this.gridLightColor;
      this.gridLightColor = gridLightColor;
      fireEvent("gridLightColor", oldValue, gridLightColor);
      getPropertyChangeSupport().firePropertyChange("gridLightColor",
                                                    oldValue, gridLightColor);
      panelRoot.repaint(); }

///////////////////////////////////////////////////////////////////////////////

public Color getGridDarkColor() { return gridDarkColor; }

public void setGridDarkColor (Color gridDarkColor)
    { if (gridDarkColor == null) { gridDarkColor = Color.DARK_GRAY; }
      Color oldValue = this.gridDarkColor;
      this.gridDarkColor = gridDarkColor;
      fireEvent("gridDarkColor", oldValue, gridDarkColor);
      getPropertyChangeSupport().firePropertyChange("gridDarkColor",
                                                    oldValue, gridDarkColor);
      panelRoot.repaint(); }

///////////////////////////////////////////////////////////////////////////////

public int getGridSize() { return gridSize; }

public void setGridSize (int gridSize)
    { if      (gridSize > 99) { gridSize = 99; }
      else if (gridSize <  3) { gridSize = 3;  }
      int oldValue = this.gridSize;
      this.gridSize = gridSize;
      fireEvent("gridSize", oldValue, gridSize);
      getPropertyChangeSupport().firePropertyChange("gridSize",
                                                    oldValue, gridSize);
      panelRoot.repaint(); }

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
    
    origin = getPointOnImage(me);
    panelRoot.setCursor(isScrollBarVisible() ? CURSOR_HAND : CURSOR_DEFAULT);
}

@Override
public void mouseReleased (MouseEvent me) {
    
    origin = null;
    panelRoot.setCursor(CURSOR_DEFAULT);
}

@Override
public void mouseWheelMoved (MouseWheelEvent mwe)
    { if (mwe.getWheelRotation() > 0) { //zoomIn(getPointOnImage(mwe));  
    }
      else                            { zoomOut(getPointOnImage(mwe)); } }

};

///////////////////////////////////////////////////////////////////////////////

public final MouseMotionListener mouseMotionListener
       = new MouseMotionAdapter() {

@Override
public void mouseDragged (MouseEvent me) {

    if (origin != null) {
        
        if (!drugImageOut && !cursorOnImage) { return; }

        imageViewport = (JViewport) SwingUtilities
                        .getAncestorOfClass(JViewport.class, panelRoot);
        
        if (imageViewport != null) {
            
            Point point = getPointOnImage(me);
            
            int deltaX = origin.x - point.x;
            int deltaY = origin.y - point.y;
            
            Rectangle view = imageViewport.getViewRect();
            view.x += deltaX;
            view.y += deltaY;

            panelRoot.scrollRectToVisible(view);
            
        }
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

// Допоміжні перемінні ////////////////////////////////////////////////////////

private int iX, iY;
private int iW, vW, mW, sW, eW;
private int iH, vH, mH, sH, eH;
private int fitWi, fitHi, fitWe, fitHe;

private Point2D.Float oldPosition, newPosition;

// Кінець класу JImageView ////////////////////////////////////////////////////

}
