package com.rutar.jimageview;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
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

public static final int SCALE_TYPE_FAST = 0;
public static final int SCALE_TYPE_SMOOTH = 1;

public static final int OPEN_SIZE_ORIGINAL = 0;
public static final int OPEN_SIZE_INTERNAL_FIT = 1;
public static final int OPEN_SIZE_EXTERNAL_FIT = 2;

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

private BufferedImage image;                           // Зображення для показу
private BufferedImage errorImage;     // Зображення яке показується при помилці

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

private int imageScaleType = SCALE_TYPE_FAST;              // Тип масштабування
private int imageOpenSize = OPEN_SIZE_INTERNAL_FIT;        // Розмір зображення

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

private boolean zoomRegion;               // Масштабування фрагмента зображення
private Point zoomOrigin;                          // Центр вікна масштабування
private int zoomAreaW = 200;                      // Ширина вікна масштабування
private int zoomAreaH = 200;                      // Висота вікна масштабування
private float zoomLevel = 3.0f;
private int zoomOffsetX = 0;
private int zoomOffsetY = 0;
private int zoomShapeType = 1;                       // Тип вікна масштабування
private int zoomFirstBorderWidth = 1;     // Ширина I рамки вікна масштабування
private int zoomSecondBorderWidth = 3;   // Ширина II рамки вікна масштабування
private int zoomFirstBorderGap = 1;      // Відступ I рамки вікна масштабування
private int zoomSecondBorderGap = -1;    // Віступ II рамки вікна масштабування
private Color zoomFirstBorderColor = Color.DARK_GRAY;          // Колір I рамки
private Color zoomSecondBorderColor = Color.GRAY;             // Колір II рамки
private boolean zoomShowCursor = true;                     // Видимість курсора

// ............................................................................

private Point origin;        // Точка, у якій відбулося натискання клавіші миші
private JPanel panelRoot;                    // Панель для малювання зображення
private JScrollBar hScrollBar, vScrollBar;           // Гор. та верт. скролбари

// ............................................................................

private static ArrayList <JImageViewListener> listeners = null;

///////////////////////////////////////////////////////////////////////////////

public JImageView() { initComponents(); }

///////////////////////////////////////////////////////////////////////////////

@SuppressWarnings("unchecked")                       
private void initComponents() {

panelRoot = new RootPane();

setWheelScrollingEnabled(false);

getViewport().addMouseListener(mouseListener);
getViewport().addChangeListener(changeListener);
getViewport().addMouseMotionListener(mouseMotionListener);
getViewport().addMouseWheelListener((MouseWheelListener) mouseListener);

setViewportView(panelRoot);

setRegionStroke(null);
setErrorImage(null);
setImage(null);

zoomToOriginal();

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
    
    if (gridVisible) { drawGrid(g2); }
    
    setImageScaleType(g2, imageScaleType);
    
    int iX = getWidth()/2  - imageScaleW/2;
    int iY = getHeight()/2 - imageScaleH/2;
    
    if (imageScale >= 100 || imageScaleType == SCALE_TYPE_FAST)
        { g2.drawImage(image, iX, iY, imageScaleW, imageScaleH, null); }
    else
        { g2.drawImage(getSmoothThumbnail(), iX, iY, null); }
    
    ///////////////////////////////////////////////////////////////////////////
    
    if (zoomRegion) {
        
        Area newClip;
        Shape oldClip = g2.getClip();
        Point pointOnImage = getPointOnImage(zoomOrigin);
        
        int x = pointOnImage.x - zoomAreaW/2;
        int y = pointOnImage.y - zoomAreaH/2;
        
        int zoomX = (int)(iX - (pointOnImage.x - iX) * (zoomLevel-1));
        int zoomY = (int)(iY - (pointOnImage.y - iY) * (zoomLevel-1));
        
        int zoomW = (int)(imageScaleW*zoomLevel);
        int zoomH = (int)(imageScaleH*zoomLevel);
        
        int zX = zoomX - (int)(iX * zoomLevel);
        int zY = zoomY - (int)(iY * zoomLevel);
        
        int s1 = zoomFirstBorderWidth + zoomFirstBorderGap +
                 zoomSecondBorderWidth*2 + zoomSecondBorderGap;
        int s2 = zoomSecondBorderWidth   + zoomSecondBorderGap;
        
        // ....................................................................
        
        if (zoomShapeType == 0)
            { newClip = new Area(new Rectangle2D.Float(x+zoomOffsetX,
                                                       y-zoomOffsetY,
                                                       zoomAreaW + 1,
                                                       zoomAreaH + 1)); }
        else
            { newClip = new Area(new Ellipse2D.Float(x+zoomOffsetX,
                                                     y-zoomOffsetY,
                                                     zoomAreaW + 1,
                                                     zoomAreaH + 1)); }

        newClip.intersect(new Area(oldClip));
        g2.setClip(newClip);
        
        drawGrid(g2, zoomLevel, zX+zoomOffsetX, zY-zoomOffsetY);
        g2.drawImage(image, zoomX+zoomOffsetX, zoomY-zoomOffsetY,
                     zoomW, zoomH, null);
        g2.setClip(oldClip);
        
        // ....................................................................
        
        setImageScaleType(g2, SCALE_TYPE_SMOOTH);
        
        g2.setColor(zoomFirstBorderColor);
        g2.setStroke(new BasicStroke(zoomFirstBorderWidth*2));
        if (zoomShapeType == 0)
            { g2.drawRect(x-s1+zoomOffsetX, y-s1-zoomOffsetY,
                          zoomAreaW + s1*2, zoomAreaH + s1*2); }
        else
            { g2.drawOval(x-s1+zoomOffsetX, y-s1-zoomOffsetY,
                          zoomAreaW + s1*2, zoomAreaH + s1*2); }

        g2.setColor(zoomSecondBorderColor);
        g2.setStroke(new BasicStroke(zoomSecondBorderWidth*2));
        if (zoomShapeType == 0)
            { g2.drawRect(x-s2+zoomOffsetX, y-s2-zoomOffsetY,
                          zoomAreaW + s2*2, zoomAreaH + s2*2); }
        else
            { g2.drawOval(x-s2+zoomOffsetX, y-s2-zoomOffsetY,
                          zoomAreaW + s2*2, zoomAreaH + s2*2); }
        
        setImageScaleType(g2, imageScaleType);
        
    }
    
    ///////////////////////////////////////////////////////////////////////////
    
    if (specifyRegion) {
        
        normalizeRegionRect(iX, iY);
        setImageScaleType(g2, SCALE_TYPE_FAST);
        
        Area area = new Area(new Rectangle2D
                       .Float(0, 0, getWidth(), getHeight()));
        
        area.subtract(new Area(regionNorm));
        g2.setColor(new Color(0x78000000, true));
        g2.fill(area);
        
        if (regionAdditionalStroke) {
            g2.setColor(regionDarkColor);
            g2.setStroke(regionStrokeAdditional);
            g2.draw(regionNorm);
        }
        
        g2.setColor(regionLightColor);
        g2.setStroke(regionStroke);
        g2.draw(regionNorm);
    
    }
}

///////////////////////////////////////////////////////////////////////////////

private void drawGrid (Graphics2D g2) {

    setImageScaleType(g2, SCALE_TYPE_FAST);
    drawGrid(g2, 1, 0, 0);
    setImageScaleType(g2, imageScaleType);

}

///////////////////////////////////////////////////////////////////////////////

private void drawGrid (Graphics2D g2, float sF, int dX, int dY) {

    int gW = (int)(getWidth()  * sF) + 1;
    int gH = (int)(getHeight() * sF) + 1;
    int gS = (int)(gridSize * sF);
    
    g2.setColor(gridLightColor);
    g2.fillRect(0, 0, gW, gH);
    g2.setColor(gridDarkColor);

    for (int col = 0; col < gW; col += gS) {
    for (int row = 0; row < gH; row += gS*2) {
        g2.fillRect(col + dX, row + (col/gS%2 == 0 ? gS : 0) + dY, gS, gS);
    }
    }
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
public void fitInternal() { calculateImageFitScale();
                            setImageScale(imageScaleInternalFit);
                            center(); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Задання розміру зображення таким чином, щоб його 
 * менша сторона максимально замістила доступний простір компонента
 */
public void fitExternal() { calculateImageFitScale();
                            setImageScale(imageScaleExternalFit);
                            center(); }

///////////////////////////////////////////////////////////////////////////////

/** 
 * Переміщує зображення таким чином, щоб воно відображалося в центрі
 * компонента. Метод доцільно використовувати лише тоді, коли зображення
 * не поміщається в межах компонента і є доступними смуги прокручування
 */
public void center() { centerOnPoint(new Point(imageScaleW/2,
                                               imageScaleH/2)); }

///////////////////////////////////////////////////////////////////////////////

/** 
 * Переміщує зображення таким чином, щоб задана точка відображалася в центрі
 * компонента. Метод доцільно використовувати лише тоді, коли зображення
 * не поміщається в межах компонента і є доступними смуги прокручування
 * @param point точка зображення
 */
public void centerOnPoint (Point point) {
    
    if (!isValid()) { validate(); }

    Rectangle viewRect = getViewport().getViewRect();
    
    viewRect.x = point.x - viewRect.width  / 2;
    viewRect.y = point.y - viewRect.height / 2;
    
    panelRoot.scrollRectToVisible(viewRect);

}

///////////////////////////////////////////////////////////////////////////////

/** Відновлення оригінального розміру відображуваного зображення */
public void zoomToOriginal() { setImageScale(100); }

///////////////////////////////////////////////////////////////////////////////
// Getter'и та Setter'и - повертають та задають властивості компонента ////////
///////////////////////////////////////////////////////////////////////////////

public BufferedImage getImage() { return image; }

public void setImage (BufferedImage image)
    { if (image == null) { image = getErrorImage(); }       
      Image oldValue = this.image;
      this.image = image;
      this.imageW = image.getWidth();
      this.imageH = image.getHeight();
      this.changeListener.stateChanged(null);
      switch (imageOpenSize) {
          case OPEN_SIZE_ORIGINAL     -> zoomToOriginal();
          case OPEN_SIZE_INTERNAL_FIT -> fitInternal();
          case OPEN_SIZE_EXTERNAL_FIT -> fitExternal();
      }
      fireAll("image", oldValue, image); }

///////////////////////////////////////////////////////////////////////////////

public BufferedImage getErrorImage() { return errorImage; }

public void setErrorImage (BufferedImage errorImage)
    { if (errorImage == null) { errorImage = getRandomImage(); }
      Image oldValue = this.errorImage;
      this.errorImage = errorImage;
      fireAll("errorImage", oldValue, errorImage); }

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
      fireAll("imageScale", oldValue, imageScale); }

///////////////////////////////////////////////////////////////////////////////

public int getImageScaleType() { return imageScaleType; }

public void setImageScaleType (int imageScaleType)
    { int oldValue = this.imageScaleType;
      this.imageScaleType = imageScaleType;
      panelRoot.repaint();
      fireAll("imageScaleType", oldValue, imageScaleType); }

///////////////////////////////////////////////////////////////////////////////

public int getImageOpenSize() { return imageOpenSize; }

public void setImageOpenSize (int imageOpenSize)
    { int oldValue = this.imageOpenSize;
      this.imageOpenSize = imageOpenSize;
      fireAll("imageOpenSize", oldValue, imageOpenSize); }

///////////////////////////////////////////////////////////////////////////////

public boolean isLMBEnable() { return lmbEnable; }

public void setLMBEnable (boolean lmbEnable)
    { boolean oldValue = this.lmbEnable;
      this.lmbEnable = lmbEnable;
      updateCursor();
      fireAll("lmbEnable", oldValue, lmbEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isCMBEnable() { return cmbEnable; }

public void setCMBEnable (boolean cmbEnable)
    { boolean oldValue = this.cmbEnable;
      this.cmbEnable = cmbEnable;
      fireAll("cmbEnable", oldValue, cmbEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isRMBEnable() { return rmbEnable; }

public void setRMBEnable (boolean rmbEnable)
    { boolean oldValue = this.rmbEnable;
      this.rmbEnable = rmbEnable;
      fireAll("rmbEnable", oldValue, rmbEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isWheelEnable() { return wheelEnable; }

public void setWheelEnable (boolean wheelEnable)
    { boolean oldValue = this.wheelEnable;
      this.wheelEnable = wheelEnable;
      fireAll("wheelEnable", oldValue, wheelEnable); }

///////////////////////////////////////////////////////////////////////////////

public boolean isWheelInvert() { return wheelInvert; }

public void setWheelInvert (boolean wheelInvert)
    { boolean oldValue = this.wheelInvert;
      this.wheelInvert = wheelInvert;
      fireAll("wheelInvert", oldValue, wheelInvert); }

///////////////////////////////////////////////////////////////////////////////

public boolean isDrugImageOut() { return drugImageOut; }

public void setDrugImageOut (boolean drugImageOut)
    { boolean oldValue = this.drugImageOut;
      this.drugImageOut = drugImageOut;
      fireAll("drugImageOut", oldValue, drugImageOut); }

///////////////////////////////////////////////////////////////////////////////

public boolean isGridVisible() { return gridVisible; }

public void setGridVisible (boolean gridVisible)
    { boolean oldValue = this.gridVisible;
      this.gridVisible = gridVisible;
      panelRoot.repaint();
      fireAll("gridVisible", oldValue, gridVisible); }

///////////////////////////////////////////////////////////////////////////////

public Color getGridLightColor() { return gridLightColor; }

public void setGridLightColor (Color gridLightColor)
    { if (gridLightColor == null) { gridLightColor = Color.LIGHT_GRAY; }
      Color oldValue = this.gridLightColor;
      this.gridLightColor = gridLightColor;
      panelRoot.repaint();
      fireAll("gridLightColor", oldValue, gridLightColor); }

///////////////////////////////////////////////////////////////////////////////

public Color getGridDarkColor() { return gridDarkColor; }

public void setGridDarkColor (Color gridDarkColor)
    { if (gridDarkColor == null) { gridDarkColor = Color.DARK_GRAY; }
      Color oldValue = this.gridDarkColor;
      this.gridDarkColor = gridDarkColor;
      panelRoot.repaint();
      fireAll("gridDarkColor", oldValue, gridDarkColor); }

///////////////////////////////////////////////////////////////////////////////

public int getGridSize() { return gridSize; }

public void setGridSize (int gridSize)
    { if      (gridSize > 99) { gridSize = 99; }
      else if (gridSize <  3) { gridSize = 3;  }
      int oldValue = this.gridSize;
      this.gridSize = gridSize;
      panelRoot.repaint();
      fireAll("gridSize", oldValue, gridSize); }

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
      
      BasicStroke oldValue = this.regionStroke;
      this.regionStroke = regionStroke;
      fireAll("regionStroke", oldValue, regionStroke); }

///////////////////////////////////////////////////////////////////////////////

public boolean isRegionAdditionalStroke() { return regionAdditionalStroke; }

public void setRegionAdditionalStroke (boolean regionAdditionalStroke)
    { boolean oldValue = this.regionAdditionalStroke;
      this.regionAdditionalStroke = regionAdditionalStroke;
      fireAll("regionAdditionalStroke", oldValue, regionAdditionalStroke); }

///////////////////////////////////////////////////////////////////////////////

public Color getRegionLightColor() { return regionLightColor; }

public void setRegionLightColor (Color regionLightColor)
    { if (regionLightColor == null) { regionLightColor = Color.WHITE; }
      Color oldValue = this.regionLightColor;
      this.regionLightColor = regionLightColor;
      fireAll("regionLightColor", oldValue, regionLightColor); }

///////////////////////////////////////////////////////////////////////////////

public Color getRegionDarkColor() { return regionDarkColor; }

public void setRegionDarkColor (Color regionDarkColor)
    { if (regionDarkColor == null) { regionDarkColor = Color.DARK_GRAY; }
      Color oldValue = this.regionDarkColor;
      this.regionDarkColor = regionDarkColor;
      fireAll("regionDarkColor", oldValue, regionDarkColor); }

///////////////////////////////////////////////////////////////////////////////
// Додавання та видалення прослуховувачів подій ///////////////////////////////
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

private void fireAll (String name, Object oldValue, Object newValue) {
    
    fireEvent          (name, oldValue, newValue);
    firePropertyChange (name, oldValue, newValue);
    
}

///////////////////////////////////////////////////////////////////////////////

private void fireEvent (String name, Object oldValue, Object newValue) {

JImageViewEvent event = new JImageViewEvent(this, oldValue, newValue);

for (JImageViewListener listener : getListeners()) {

    switch (name) {
        case "image"
              -> listener.imageChange(event);
        case "errorImage"
              -> listener.errorImageChange(event);
        case "imageScale"
              -> listener.imageScaleChange(event);
        case "imageScaleType"
              -> listener.imageScaleTypeChange(event);
        case "imageOpenSize"
              -> listener.imageOpenSizeChange(event);
        case "LMBEnable"
              -> listener.LMBEnableChange(event);
        case "CMBEnable"
              -> listener.CMBEnableChange(event);
        case "RMBEnable"
              -> listener.RMBEnableChange(event);
        case "wheelEnable"
              -> listener.wheelEnableChange(event);
        case "wheelInvert"
              -> listener.wheelInvertChange(event);
        case "drugImageOut"
              -> listener.drugImageOutChange(event);
        case "gridVisible"
              -> listener.gridVisibleChange(event);
        case "gridLightColor"
              -> listener.gridLightColorChange(event);
        case "gridDarkColor"
              -> listener.gridDarkColorChange(event);
        case "gridSize"
              -> listener.gridSizeChange(event);
        case "regionStroke"
              -> listener.regionStrokeChange(event);
        case "regionAdditionalStroke"
              -> listener.regionAdditionalStrokeChange(event);
        case "regionLightColor"
              -> listener.regionLightColorChange(event);
        case "regionDarkColor"
              -> listener.regionDarkColorChange(event);
        
    }
}
}

///////////////////////////////////////////////////////////////////////////////
// Допоміжні методи ///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

private void calculateImageLimitScale() {
    
    int w = imageW;
    int h = imageH;

    int q = (w > h) ? h : w;
    int z = (w > h) ? w : h;
    
    imageScaleMin = (int)(48d   / q * 100);
    imageScaleMax = (int)(7000d / z * 100);
    
    if (imageScaleMin > 100) { imageScaleMin = 100; }
    if (imageScaleMax < 100) { imageScaleMax = 100; }
    
}

///////////////////////////////////////////////////////////////////////////////

private void calculateImageFitScale() {
    
    int[] fitScale = calculateFitScale(imageW, imageH);
    
    imageScaleInternalFit = fitScale[0];
    imageScaleExternalFit = fitScale[1];

}

///////////////////////////////////////////////////////////////////////////////

private int[] calculateFitScale (int regionW, int regionH) {
    
    if (!isValid()) { validate(); }
    
    // Ширина зображення
    int iW = regionW;
    // Ширина області перегляду
    int vW = getViewport().getWidth();
    // Ширина вертикального скролбару
    int mW = vScrollBar.getMaximumSize().width;
    // Активна ширина вертикального скролбару
    int sW = vScrollBar.isVisible() ? mW : 0;
    // Допоміжна змінна, використовується для розрахунку imageScaleInternalFit    
    int qW = (regionW < imageW) ? mW : 0;
    // Копія змінної sW, використовується для розрахунку imageScaleExternalFit
    int eW = sW;
    
    // Висота зображення
    int iH = regionH;
    // Висота області перегляду
    int vH = getViewport().getHeight();
    // Висота горизонтального скролбару
    int mH = hScrollBar.getMaximumSize().height;
    // Активна висота горизонтального скролбару
    int sH = hScrollBar.isVisible() ? mH : 0;
    // Допоміжна змінна, використовується для розрахунку imageScaleInternalFit
    int qH = (regionH < imageH) ? mH : 0;
    // Копія змінної sH, використовується для розрахунку imageScaleExternalFit
    int eH = sH;
    
    if      (getVerticalScrollBarPolicy() ==
        VERTICAL_SCROLLBAR_ALWAYS)   { sW = 0;  qW = 0;  }
    else if (getVerticalScrollBarPolicy() ==
        VERTICAL_SCROLLBAR_NEVER)    { eW = mW; qW = mW; }
    if      (getHorizontalScrollBarPolicy() ==
        HORIZONTAL_SCROLLBAR_ALWAYS) { sH = 0;  qH = 0;  }
    else if (getHorizontalScrollBarPolicy() ==
        HORIZONTAL_SCROLLBAR_NEVER)  { eH = mH; qH = mH; }
    
    int fitWi = (int)(100d * (vW + sW - qW) / iW);
    int fitHi = (int)(100d * (vH + sH - qH) / iH);
    
    int fitWe = (int)(100d * (vW + eW - mW) / iW);
    int fitHe = (int)(100d * (vH + eH - mH) / iH);
    
    return new int[] { (fitWi < fitHi) ? fitWi : fitHi,
                       (fitWe > fitHe) ? fitWe : fitHe };

}

///////////////////////////////////////////////////////////////////////////////

private void calculateScaledImageSize() {
    
    imageScaleW = (int)(imageW * imageScale / 100d);
    imageScaleH = (int)(imageH * imageScale / 100d);
    
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
    
    return getLocalPercentagePoint(point, panelRoot.getWidth(),
                                          panelRoot.getHeight());

}

///////////////////////////////////////////////////////////////////////////////

private Point getLocationPoint (Point2D.Float point,
                                int width, int height) {
    
    return new Point((int)(point.x * width  / 100f),
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
    specifyRegion = true;
    panelRoot.repaint();
    
}

///////////////////////////////////////////////////////////////////////////////

public void setRegion (Rectangle region) {
    
    specifyRegion = false;
    panelRoot.repaint();
    
    int x = regionImage.x;
    int y = regionImage.y;
    
    int w = regionImage.width;
    int h = regionImage.height;
    
    int fitType = 0;
    int fitScale = calculateFitScale(w, h)[fitType];
    
    Point center = new Point(x + w/2, y + h/2);
    Point2D.Float percent = getLocalPercentagePoint(center, imageW, imageH);
    
    setImageScale(fitScale);

    Point location = getLocationPoint(percent, imageScaleW, imageScaleH);
    centerOnPoint(location);

}

///////////////////////////////////////////////////////////////////////////////

private void normalizeRegionRect (int iX, int iY) {
    
    int x = regionOrig.x;
    int y = regionOrig.y;
    int w = regionOrig.width;
    int h = regionOrig.height;
    Rectangle r = getViewport().getViewRect();
        
    // Обробка від'ємних координат
    if (w < 0) { w = -w; x -= w; }
    if (h < 0) { h = -h; y -= h; }

    // Заборона виходу за початкову точку зображення
    if (x < iX) { w -= (iX - x); x = iX; }
    if (y < iY) { h -= (iY - y); y = iY; }
    
    // Заборона виходу за межі розмірів зображення
    if (w > imageScaleW - (x - iX)) { w = imageScaleW - (x - iX); }
    if (h > imageScaleH - (y - iY)) { h = imageScaleH - (y - iY); }

    // Заборона виходу за початкову точку компонента
    if (x < r.x) { w -= (r.x - x); x = r.x; }
    if (y < r.y) { h -= (r.y - y); y = r.y; }
    
    // Заборона виходу за межі розмірів компонента
    if (x + w > r.x + r.width)  { w = r.x + r.width  - x; }
    if (y + h > r.y + r.height) { h = r.y + r.height - y; }
    
    regionNorm = new Rectangle(x, y, w, h);
    
    // ........................................................................
    
    x -= iX;
    y -= iY;
    
    Point2D.Float pXY = getLocalPercentagePoint(new Point(x, y),
                                                imageScaleW, imageScaleH);
    Point2D.Float pWH = getLocalPercentagePoint(new Point(w, h),
                                                imageScaleW, imageScaleH);
    
    Point xy = getLocationPoint(pXY, imageW, imageH);
    Point wh = getLocationPoint(pWH, imageW, imageH);
    
    x = xy.x;
    y = xy.y;
    w = wh.x;
    h = wh.y;
    
    regionImage = new Rectangle(x, y, w, h);
    
}

///////////////////////////////////////////////////////////////////////////////

private BufferedImage getRandomImage() {
    
    String path = "/com/rutar/jimageview/images/%s.png";
    String[] names = { "tree", "fire", "wave" };

    int index = (int)(Math.random() * 3);
    URL resource = getClass().getResource(String.format(path, names[index]));

    try { return ImageIO.read(resource); }
    catch (IOException e) { return null;}

}

///////////////////////////////////////////////////////////////////////////////

private void setImageScaleType (Graphics2D g2, int scaleType) {
    
if (scaleType == SCALE_TYPE_FAST) {

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

    if (zoomRegion)
        { CURSOR_DEFAULT = zoomShowCursor ? CURSOR_REGION :
                                            getUnvisibleCursor();
          panelRoot.setCursor(CURSOR_DEFAULT);
          return; }
    
    if (lmbEnable)
        { scrollBarVisible = isScrollBarVisible();
          CURSOR_DEFAULT = scrollBarVisible ? CURSOR_MOVE : null;
          panelRoot.setCursor(CURSOR_DEFAULT); }
    else
        { CURSOR_DEFAULT = null;
          panelRoot.setCursor(CURSOR_DEFAULT); }
}

///////////////////////////////////////////////////////////////////////////////

private Cursor getUnvisibleCursor() {
    
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    
    return toolkit.createCustomCursor(toolkit.createImage(new byte[0]),
                                      new Point(0, 0), null);

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
            zoomOrigin = me.getPoint();
            zoomRegion = true;
            updateCursor();
            repaint();
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
            zoomRegion = false;
            updateCursor();
            repaint();
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
    
    if (zoomRegion) {
        
        zoomOrigin = me.getPoint();
        repaint();
        
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
        
    }
};

// Кінець класу JImageView ////////////////////////////////////////////////////

}
