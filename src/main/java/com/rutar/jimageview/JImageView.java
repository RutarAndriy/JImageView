package com.rutar.jimageview;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.swing.*;
import java.awt.geom.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;

import static java.awt.RenderingHints.*;

// ............................................................................

/**
 * Клас JImageView
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageView extends JScrollPane {

// ............................................................................

private static final Cursor CURSOR_HAND
    = new Cursor(Cursor.HAND_CURSOR);

private static final Cursor CURSOR_MOVE
    = new Cursor(Cursor.MOVE_CURSOR);

private static final Cursor CURSOR_REGION
    = new Cursor(Cursor.CROSSHAIR_CURSOR);

private static Cursor CURSOR_DEFAULT = null;

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

private Image image = null;                            // Зображення для показу
private Image errorImage = null;      // Зображення яке показується при помилці

// ............................................................................

private boolean drugImageOut = true;         // Переміщення за межею компонента

private boolean gridVisible = true;                             // Фонова сітка
private Color gridLightColor = Color.LIGHT_GRAY;       // I колір фонової сітки
private Color gridDarkColor  = Color.DARK_GRAY;       // II колір фонової сітки
private int gridSize = 25;                                      // Розмір сітки

private boolean lmbEnable   = true;  // Переміщення зображення за допомогою ЛКМ
private boolean cmbEnable   = true;           // Зміна вигляду за допопогою СКМ
private boolean rmbEnable   = true;            // Масштабування за допоогою ПКМ
private boolean wheelEnable = true;             // Масштабування колесиком миші
private boolean wheelInvert = false;              // Інвертування колесика миші

public enum ScaleType { FAST, SMOOTH }                // Усі види масштабування
private ScaleType imageScaleType = ScaleType.FAST;         // Тип масштабування

// ............................................................................

private int imageW;                          // Ширина оригінального зображення
private int imageH;                          // Висота оригінального зображення

private int imageScale;                                   // Масштаб зображення

private int imageScaleW;                    // Ширина масштабованого зображення
private int imageScaleH;                    // Висота масштабованого зображення

private int globalScaleMin = scales[0];                  // Мінімальний масштаб
private int globalScaleMax = scales[scales.length - 1]; // Максимальний масштаб

private int imageScaleMax;                    // Мінімальний масштаб зображення
private int imageScaleMin;                   // Максимальний масштаб зображення
private int imageScaleInternalFit;       // Масштаб для внутрішнього заповнення
private int imageScaleExternalFit;        // Масштаб для зовнішнього заповнення

private boolean cursorOnImage;      // Знаходження курсора всередині компонента
private boolean scrollBarVisible;        // Видимість скролбарів, хоча б одного

private boolean specifyRegion;                 // Задання регіону масштабування
private Rectangle regionOrig;                            // Оригінальний регіон
private Rectangle regionNorm;                          // Нормалізований регіон
private Rectangle regionImage;               // Регіон в координатах зображення
private Color regionLightColor = Color.WHITE;          // I колір рамки регіону
private Color regionDarkColor  = Color.DARK_GRAY;     // II колір рамки регіону
private BasicStroke regionStroke;           // Основний штрих виділення регіону
private BasicStroke regionStrokeAdditional;     // Доп. штрих виділення регіону
private boolean regionAdditionalStroke = true;  // Малювання додаткового штриха

// ............................................................................

private Point origin;        // Точка, у якій відбулося натискання клавіші миші
private JPanel panelRoot;                    // Панель для малювання зображення
private JScrollBar hScrollBar, vScrollBar;           // Гор. та верт. скролбари

// ............................................................................

private static ArrayList <JImageViewListener> listeners = null;
private static transient PropertyChangeSupport propertyChangeSupport = null;

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
 * Малює основне зображення та прослуховує події введення
 */
private final class RootPane extends JPanel {

@Override
public void paintComponent (Graphics g) {

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    
    if (gridVisible) {

        setImageScaleType(g2, ScaleType.FAST);

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
    
    setImageScaleType(g2, imageScaleType);
    
    int iX = getWidth()/2  - imageScaleW/2;
    int iY = getHeight()/2 - imageScaleH/2;
    
    if (imageScale >= 100 || imageScaleType == ScaleType.FAST)
        { g2.drawImage(image, iX, iY, imageScaleW, imageScaleH, null); }
    else
        { g2.drawImage(getSmoothThumbnail(), iX, iY, null); }
    
    if (specifyRegion) {
        
        normalizeRegionRect(iX, iY);
        
        if (regionAdditionalStroke) {
            g2.setColor(regionDarkColor);
            g2.setStroke(regionStrokeAdditional);
            g2.draw(regionNorm);
        }
        
        g2.setColor(regionLightColor);
        g2.setStroke(regionStroke);
        g2.draw(regionNorm);
    
    }
    
//    if (regionRectNormalized != null && !specifyRegion) {
//    
//    g2.setColor(Color.RED);
//    g2.setStroke(regionStroke);
//    g2.drawRect(regionRectNormalized.x,
//                regionRectNormalized.y,
//                (int)(regionRectNormalized.width  * imageScale / 100f),
//                (int)(regionRectNormalized.height * imageScale / 100f));
//    
//    }
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

    if (!isValid()) { validate(); }
    if (origin == null) { origin = new Point(getWidth()/2,
                                             getHeight()/2); }
    
    Point2D.Float oldPosition = getGlobalPercentagePoint(origin);
    
    for (int z = 0; z < scales.length; z++) {
        if (scaleValue < scales[z]) {
            scaleValue = scales[z];
            setImageScale(scaleValue);
            break;
        }
    }
    
    Point2D.Float newPosition = getGlobalPercentagePoint(origin);

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

    if (!isValid()) { validate(); }
    if (origin == null) { origin = new Point(getWidth()/2,
                                             getHeight()/2); }

    Point2D.Float oldPosition = getGlobalPercentagePoint(origin);

    for (int z = scales.length - 1; z >= 0; z--) {
        if (scaleValue > scales[z]) {
            scaleValue = scales[z];
            setImageScale(scaleValue);
            break;
        }
    }

    Point2D.Float newPosition = getGlobalPercentagePoint(origin);

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
      this.imageW = image.getWidth(null);
      this.imageH = image.getHeight(null);
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

public boolean isRegionAdditionalStroke() { return regionAdditionalStroke; }

public void setRegionAdditionalStroke (boolean regionAdditionalStroke)
    { boolean oldValue = this.regionAdditionalStroke;
      this.regionAdditionalStroke = regionAdditionalStroke;
      fireEvent("regionAdditionalStroke", oldValue, regionAdditionalStroke);
      getPropertyChangeSupport().firePropertyChange("regionAdditionalStroke",
                                 oldValue, regionAdditionalStroke); }

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

public Color getRegionLightColor() { return regionLightColor; }

public void setRegionLightColor (Color regionLightColor)
    { if (regionLightColor == null) { regionLightColor = Color.WHITE; }
      Color oldValue = this.regionLightColor;
      this.regionLightColor = regionLightColor;
      fireEvent("regionLightColor", oldValue, regionLightColor);
      getPropertyChangeSupport()
     .firePropertyChange("regionLightColor", oldValue, regionLightColor); }

///////////////////////////////////////////////////////////////////////////////

public Color getRegionDarkColor() { return regionDarkColor; }

public void setRegionDarkColor (Color regionDarkColor)
    { if (regionDarkColor == null) { regionDarkColor = Color.DARK_GRAY; }
      Color oldValue = this.regionDarkColor;
      this.regionDarkColor = regionDarkColor;
      fireEvent("regionDarkColor", oldValue, regionDarkColor);
      getPropertyChangeSupport()
     .firePropertyChange("regionDarkColor", oldValue, regionDarkColor); }

///////////////////////////////////////////////////////////////////////////////

public boolean isLMBEnable() { return lmbEnable; }

public void setLMBEnable (boolean lmbEnable)
    { boolean oldValue = this.lmbEnable;
      this.lmbEnable = lmbEnable;
      updateCursor();
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

public ScaleType getImageScaleType() { return imageScaleType; }

public void setImageScaleType (ScaleType imageScaleType)
    { ScaleType oldValue = this.imageScaleType;
      this.imageScaleType = imageScaleType;
      panelRoot.repaint();
      fireEvent("imageScaleType", oldValue, imageScaleType);
      getPropertyChangeSupport()
     .firePropertyChange("imageScaleType", oldValue, imageScaleType); }

///////////////////////////////////////////////////////////////////////////////
// Додавання та видалення прослуховувачів подій ///////////////////////////////
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
// Допоміжні методи ///////////////////////////////////////////////////////////
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
    
    int[] fitScale = calculateFitScale(image.getWidth(null),
                                       image.getHeight(null));
    
    imageScaleInternalFit = fitScale[0];
    imageScaleExternalFit = fitScale[1];

}

///////////////////////////////////////////////////////////////////////////////

private int[] calculateFitScale (int regionW, int regionH) {
    
    // Ширина зображення
    int iW = regionW;
    // Ширина області перегляду
    int vW = getViewport().getWidth();
    // Ширина вертикального скролбару
    int mW = vScrollBar.getMaximumSize().width;
    // Активна ширина вертикального скролбару
    int sW = vScrollBar.isVisible() ? mW : 0;
    // Комія змінної sW, використовується для розрахунку imageScaleExternalFit
    int eW = sW;
    
    // Висота зображення
    int iH = regionH;
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
    
    return new int[] { (fitWi < fitHi) ? fitWi : fitHi,
                       (fitWe > fitHe) ? fitWe : fitHe };

}

///////////////////////////////////////////////////////////////////////////////

private void calculateScaledImageSize() {
    
    imageScaleW = (int)(image.getWidth(null)  * imageScale / 100d);
    imageScaleH = (int)(image.getHeight(null) * imageScale / 100d);
    
}

///////////////////////////////////////////////////////////////////////////////

private Point2D.Float getLocalPercentagePoint (Point point, int width,
                                                            int height) {
    
    return new Point2D.Float(point.x * 100f / width,
                             point.y * 100f / height);

}

///////////////////////////////////////////////////////////////////////////////

private Point2D.Float getGlobalPercentagePoint (Point point) {

    if (!isValid()) { validate(); }
    point = getPointOnImage(point);
    //var point = SwingUtilities.convertPoint(getViewport(), origin, panelRoot);
    
    return getLocalPercentagePoint(point, panelRoot.getWidth(),
                                          panelRoot.getHeight());
    
//    return new Point2D.Float(point.x * 100f / panelRoot.getWidth(),
//                             point.y * 100f / panelRoot.getHeight());

}

///////////////////////////////////////////////////////////////////////////////

private Dimension getDimensionByPercentagePoint (Point2D.Float point,
                                                 int width, int height) {
    
    return new Dimension((int)(point.x * width  / 100f),
                         (int)(point.y * height / 100));
    
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

public void setRegion() {
    
    panelRoot.setCursor(CURSOR_REGION);
    regionOrig  = new Rectangle();
//    regionNorm  = new Rectangle();
//    regionImage = new Rectangle();
    specifyRegion = true;
    
}

///////////////////////////////////////////////////////////////////////////////

public void setRegion (Rectangle region) {
    
    specifyRegion = false;
    panelRoot.repaint();
    
    int x = regionImage.x;
    int y = regionImage.y;
    
    int w = regionImage.width;
    int h = regionImage.height;
    
    System.out.printf("[ %d, %d ] > [ %d. %d ]\n", x, y, w, h);
    
    int fitType = 0;
    int fitScale = calculateFitScale(w, h)[fitType];
    
    //System.out.println(dX + "/" + dY);
    
    //setImageScale(fitScale);
    
    
    
//    System.out.printf("Region: [ %d, %d, %d, %d ]\n",
//                       region.x, region.y, region.width, region.height);
}

///////////////////////////////////////////////////////////////////////////////

private void normalizeRegionRect (int iX, int iY) {
    
    int x = regionOrig.x;
    int y = regionOrig.y;
    int w = regionOrig.width;
    int h = regionOrig.height;
    
    // Обробка від'ємних координат
    if (w < 0) { w = -w; x -= w; }
    if (h < 0) { h = -h; y -= h; }

    // Заборона виходу за початкову точку зображення
    if (x < iX) { w -= (iX - x); x = iX; }
    if (y < iY) { h -= (iY - y); y = iY; }
    
    // Заборона виходу за межі розмірів зображення
    if (w > imageScaleW - (x - iX)) { w = imageScaleW - (x - iX); }
    if (h > imageScaleH - (y - iY)) { h = imageScaleH - (y - iY); }

    regionNorm = new Rectangle(x, y, w, h);
    
    x -= iX;
    y -= iY;
    
    Point2D.Float pXY = getLocalPercentagePoint(new Point(x, y),
                                                imageScaleW, imageScaleH);
    Point2D.Float pWH = getLocalPercentagePoint(new Point(w, h),
                                                imageScaleW, imageScaleH);
    
    Dimension dXY = getDimensionByPercentagePoint(pXY, imageW, imageH);
    Dimension dWH = getDimensionByPercentagePoint(pWH, imageW, imageH);
    
    x = dXY.width;
    y = dXY.height;
    w = dWH.width;
    h = dWH.height;
    
    regionImage = new Rectangle(x, y, w, h);
    
}

///////////////////////////////////////////////////////////////////////////////

private Image getRandomImage() {
    
    String path = "/com/rutar/jimageview/images/%s.png";
    String[] names = { "tree", "fire", "wave" };

    int index = (int)(Math.random() * 3);
    URL resource = getClass().getResource(String.format(path, names[index]));

    try { return ImageIO.read(resource); }
    catch (IOException e) { return null;}

}

///////////////////////////////////////////////////////////////////////////////

private void setImageScaleType (Graphics2D g2, ScaleType scaleType) {
    
if (scaleType == ScaleType.FAST) {

    // Інтерполяція альфа-каналу: швидка
    g2.setRenderingHint(KEY_ALPHA_INTERPOLATION,
                        VALUE_ALPHA_INTERPOLATION_SPEED);
    // Згладжування: вимкнено
    g2.setRenderingHint(KEY_ANTIALIASING,
                        VALUE_ANTIALIAS_OFF);
    // Рендеринг кольорів: швидкий
    g2.setRenderingHint(KEY_COLOR_RENDERING,
                        VALUE_COLOR_RENDER_SPEED);
    // Інтерполяція: метод найближчого сусіда
    g2.setRenderingHint(KEY_INTERPOLATION,
                        VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
    // Рендеринг: швидкий
    g2.setRenderingHint(KEY_RENDERING,
                        VALUE_RENDER_SPEED);
}
    
else {
    
    // Інтерполяція альфа-каналу: якісна
    g2.setRenderingHint(KEY_ALPHA_INTERPOLATION,
                        VALUE_ALPHA_INTERPOLATION_QUALITY);
    // Згладжування: увімкнене
    g2.setRenderingHint(KEY_ANTIALIASING,
                        VALUE_ANTIALIAS_ON);
    // Рендеринг кольорів: якісний
    g2.setRenderingHint(KEY_COLOR_RENDERING,
                        VALUE_COLOR_RENDER_QUALITY);
    // Інтерполяція: метод бікубічної інтерполяції
    g2.setRenderingHint(KEY_INTERPOLATION,
                        VALUE_INTERPOLATION_BICUBIC);
    // Рендеринг: якісний
    g2.setRenderingHint(KEY_RENDERING,
                        VALUE_RENDER_SPEED);
}
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

private Point getPointOnImage (Point point)
    { return SwingUtilities.convertPoint(getViewport(), point, panelRoot);}

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

private void updateCursor() {
    
    if (lmbEnable)
        { scrollBarVisible = isScrollBarVisible();
          CURSOR_DEFAULT = scrollBarVisible ? CURSOR_MOVE : null;
          panelRoot.setCursor(CURSOR_DEFAULT); }
    else
        { CURSOR_DEFAULT = null;
          panelRoot.setCursor(CURSOR_DEFAULT); }
}

///////////////////////////////////////////////////////////////////////////////
// Прослуховування та обробка подій ///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

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
            
            if (specifyRegion)
                { regionOrig.setLocation(getPointOnImage(me)); }
            
            if (!lmbEnable || specifyRegion) { return; }
            
            origin = getPointOnImage(me);
            panelRoot.setCursor(isScrollBarVisible() ? CURSOR_HAND :
                                                       CURSOR_DEFAULT);
        }
        
        // Середня клавіша миші
        case MouseEvent.BUTTON2 -> {
        
            if (!cmbEnable || specifyRegion) { return; }
            
            if (imageScale == 100)                        { fitInternal();    }
            else if (imageScale == imageScaleInternalFit) { fitExternal();    }
            else                                          { zoomToOriginal(); }
        }
            
        // Права клавіша миші
        case MouseEvent.BUTTON3 -> {
            
            if (!rmbEnable || specifyRegion) { return; }
            
            System.out.println("Right mouse button pressed");     
        }
    }
}

// ............................................................................

@Override
public void mouseReleased (MouseEvent me) {
    
    switch (me.getButton()) {
        
        // Ліва клавіша миші
        case MouseEvent.BUTTON1 -> {
            
            if (specifyRegion) { updateCursor();
                                 setRegion(regionNorm); }
            
            if (!lmbEnable || specifyRegion) { return; }
            
            origin = null;
            panelRoot.setCursor(CURSOR_DEFAULT);
        }
        
        // Права клавіша миші
        case MouseEvent.BUTTON3 -> {
            
            if (!rmbEnable || specifyRegion) { return; }
            
            System.out.println("Right mouse button released");     
        }
    }
}

// ............................................................................

@Override
public void mouseWheelMoved (MouseWheelEvent mwe) {
    
    if (!wheelEnable || specifyRegion) { return; }
    
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

    if (specifyRegion) { 
        
        Point point_new = getPointOnImage(me);
        Point point_old = regionOrig.getLocation();
        
        regionOrig.setSize(point_new.x - point_old.x,
                           point_new.y - point_old.y);
        
        panelRoot.repaint();
    }
    
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
        
        if (scrollBarVisible != isScrollBarVisible()) { updateCursor(); }
        
        calculateImageFitScale();
        
    }
};

// Кінець класу JImageView ////////////////////////////////////////////////////

}
