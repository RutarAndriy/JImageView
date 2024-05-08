package com.rutar.jimageview;

import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.event.*;

import static java.awt.Cursor.*;
import static java.awt.RenderingHints.*;
import static com.rutar.jimageview.JImageViewUtils.*;

// ............................................................................

/**
 * Клас JImageView
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageView extends JScrollPane {

/** Швидке масштабування */
public static final int SCALE_TYPE_FAST = 0;
/** Згладжене масштабування */
public static final int SCALE_TYPE_SMOOTH = 1;


/** Відкривання зображення в оригінальному розмірі */
public static final int OPEN_SIZE_ORIGINAL = 0;
/** Відкривання зображення вписаним у компонент */
public static final int OPEN_SIZE_INTERNAL_FIT = 1;
/** Відкривання зображення описаним навколо компонента */
public static final int OPEN_SIZE_EXTERNAL_FIT = 2;


/** Масштабний коефіцієнт лупи - x1.25 */
public static final float ZOOM_SCALE_X1_25 = 1.25f;
/** Масштабний коефіцієнт лупи - x1.50 */
public static final float ZOOM_SCALE_X1_50 = 1.50f;
/** Масштабний коефіцієнт лупи - x1.75 */
public static final float ZOOM_SCALE_X1_75 = 1.75f;
/** Масштабний коефіцієнт лупи - x2.00 */
public static final float ZOOM_SCALE_X2_00 = 2.00f;
/** Масштабний коефіцієнт лупи - x2.25 */
public static final float ZOOM_SCALE_X2_25 = 2.25f;
/** Масштабний коефіцієнт лупи - x2.50 */
public static final float ZOOM_SCALE_X2_50 = 2.50f;
/** Масштабний коефіцієнт лупи - x3.00 */
public static final float ZOOM_SCALE_X3_00 = 3.00f;
/** Масштабний коефіцієнт лупи - x4.00 */
public static final float ZOOM_SCALE_X4_00 = 4.00f;
/** Масштабний коефіцієнт лупи - x5.00 */
public static final float ZOOM_SCALE_X5_00 = 5.00f;

/** Форма лупи - прямокутна */
public static final int ZOOM_SHAPE_RECTANGLE = 0;
/** Форма лупи - еліптична */
public static final int ZOOM_SHAPE_ELLIPSE   = 1;

// ............................................................................

private static final Cursor CURSOR_HAND    = new Cursor(HAND_CURSOR);
private static final Cursor CURSOR_MOVE    = new Cursor(MOVE_CURSOR);
private static final Cursor CURSOR_REGION  = new Cursor(CROSSHAIR_CURSOR);
private static       Cursor CURSOR_DEFAULT = null;

// ............................................................................

// Масив стандартних масштабів
private final int[] scales =
    { 5, 8, 10,  15,  20,  25,   30,   35,   40,   45,   50,
                                 60,   70,   80,   90,  100,
                125, 150, 175,  200,  225,  250,  275,  300,
                                      350,  400,  450,  500,
                                      600,  700,  800,  900,
                               1000, 1500, 2000, 2500, 3000 };

// Масив стандартних масштабів вікна масштабування
private final float[] zoomLevels =
    { 1.25f, 1.50f, 1.75f, 2.00f, 2.25f, 2.50f, 3.00f, 4.00f, 5.00f };

///////////////////////////////////////////////////////////////////////////////
// Змінні, які реалізують основний функціонал компонента //////////////////////

private BufferedImage image;                           // Зображення для показу
private BufferedImage errorImage;     // Зображення яке показується при помилці
private int imageW;                          // Ширина оригінального зображення
private int imageH;                          // Висота оригінального зображення
private float imageScale;                                 // Масштаб зображення
private int imageScaleW;                    // Ширина масштабованого зображення
private int imageScaleH;                    // Висота масштабованого зображення
private int globalScaleMin = scales[0];                  // Мінімальний масштаб
private int globalScaleMax = scales[scales.length - 1]; // Максимальний масштаб
private float imageScaleMax;                  // Мінімальний масштаб зображення
private float imageScaleMin;                 // Максимальний масштаб зображення
private float imageScaleInternalFit;     // Масштаб для внутрішнього заповнення
private float imageScaleExternalFit;      // Масштаб для зовнішнього заповнення
private int imageScaleType = SCALE_TYPE_FAST;              // Тип масштабування
private int imageOpenSize = OPEN_SIZE_ORIGINAL; // Розмір відкритого зображення
private boolean lmbEnable    = true; // Переміщення зображення за допомогою ЛКМ
private boolean cmbEnable    = true;          // Зміна вигляду за допопогою СКМ
private boolean rmbEnable    = true;           // Масштабування за допоогою ПКМ
private boolean zmbEnable    = true;    // Вибір регіону за допомогою ЛКМ + ПКМ
private boolean wheelEnable  = true;            // Масштабування колесиком миші
private boolean wheelInvert  = false;             // Інвертування колесика миші
private boolean drugImageOut = true;         // Переміщення за межею компонента

///////////////////////////////////////////////////////////////////////////////
// Змінні, які мають відношення до фонової сітки //////////////////////////////

private boolean gridVisible = true;                             // Фонова сітка
private Color gridLightColor = Color.LIGHT_GRAY;       // I колір фонової сітки
private Color gridDarkColor  = Color.DARK_GRAY;       // II колір фонової сітки
private int gridSize = 25;                                      // Розмір сітки

///////////////////////////////////////////////////////////////////////////////
// Змінні, які мають відношення до регіону масштабування //////////////////////

private boolean specifyRegion;                 // Задання регіону масштабування
private Rectangle regionOrig;                            // Оригінальний регіон
private Rectangle regionNorm;                          // Нормалізований регіон
private Rectangle regionImage;               // Регіон в координатах зображення
private int regionMinimumSize = 9;      // Мінімальний розмір виділення регіону
private Color regionLightColor = Color.WHITE;          // I колір рамки регіону
private Color regionDarkColor  = Color.DARK_GRAY;     // II колір рамки регіону
private BasicStroke regionStroke;           // Основний штрих виділення регіону
private BasicStroke regionStrokeAdditional;     // Доп. штрих виділення регіону
private boolean regionAdditionalStroke = true;  // Малювання додаткового штриха

///////////////////////////////////////////////////////////////////////////////
// Змінні, які мають відношення до області масштабування (лупи) ///////////////

private Point zoomOrigin;                                         // Центр лупи
private Dimension zoomArea = new Dimension(180, 180);           // Розміри лупи
private Point zoomOffset = new Point(0, 0);                     // Відступ лупи
private float zoomLevel = ZOOM_SCALE_X2_50;        // Рівень масштабування лупи
private int zoomShapeType = ZOOM_SHAPE_ELLIPSE;              // Тип фігури лупи
private boolean zoomFirstBorderVisible = true;        // Видимість I рамки лупи
private boolean zoomSecondBorderVisible = true;      // Видимість II рамки лупи
private int zoomFirstBorderWidth = 1;                    // Ширина I рамки лупи
private int zoomSecondBorderWidth = 3;                  // Ширина II рамки лупи
private int zoomFirstBorderGap = 1;                     // Відступ I рамки лупи
private int zoomSecondBorderGap = -1;                   // Віступ II рамки лупи
private Color zoomFirstBorderColor = Color.DARK_GRAY;     // Колір I рамки лупи
private Color zoomSecondBorderColor = Color.GRAY;        // Колір II рамки лупи
private boolean zoomShowCursor = true;      // Видимість курсора при збільшенні
private boolean drugZoomOut = false;      // Видимість лупи за межею компонента
private boolean invertZoomOut = false;  // Інвертувати лупу за межею компонента

///////////////////////////////////////////////////////////////////////////////
// Допоміжні змінні /////////////////////////////////////////// ///////////////

private boolean lmbPressed;                                    // ЛКМ натиснута
private boolean cmbPressed;                                    // СКМ натиснута
private boolean rmbPressed;                                    // ПКМ натиснута
private boolean zmbPressed;                              // ЛКМ і ПКМ натиснуті
private boolean cursorOnImage;      // Знаходження курсора всередині компонента
private boolean scrollBarVisible;        // Видимість скролбарів, хоча б одного
private boolean initialState;             // Початкова ініціалізація компонента
private Point pressOrigin;   // Точка, у якій відбулося натискання клавіші миші
private JPanel panelRoot;                    // Панель для малювання зображення
private JScrollBar hScrollBar, vScrollBar;           // Гор. та верт. скролбари
private ArrayList <JImageViewListener> listeners = null;      // Прослуховувачі

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

initialState = true;

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

    if (zoomOrigin != null) {
        
        int vw = getViewport().getWidth() - 1;
        int vh = getViewport().getHeight() - 1;
        
        Point zOffset = new Point(zoomOffset);

        // ....................................................................
        
        if (!drugZoomOut) {

            int ow = zoomOffset.x;
            int oh = zoomOffset.y;

            int rX1 = (ow > 0) ?  ow : (invertZoomOut ? -ow :  ow);
            int rX2 = (ow < 0) ? -ow : (invertZoomOut ?  ow : -ow);

            int rY1 = (oh < 0) ? -oh : (invertZoomOut ?  oh : -oh);
            int rY2 = (oh > 0) ?  oh : (invertZoomOut ? -oh :  oh);

            if      (zoomOrigin.x + rX1 < 0)  { zoomOrigin.x = -rX1;     }
            else if (zoomOrigin.x - rX2 > vw) { zoomOrigin.x = vw + rX2; }

            if      (zoomOrigin.y + rY1 < 0)  { zoomOrigin.y = -rY1; }
            else if (zoomOrigin.y - rY2 > vh) { zoomOrigin.y = vh + rY2; } }
        
        // ....................................................................
        
        Area newClip;
        Shape oldClip = g2.getClip();
        Point pointOnImage = getPointOnImage(zoomOrigin);
        
        int x = pointOnImage.x - zoomArea.width/2;
        int y = pointOnImage.y - zoomArea.height/2;
        
        int zoomX = (int)(iX - (pointOnImage.x - iX) * (zoomLevel-1));
        int zoomY = (int)(iY - (pointOnImage.y - iY) * (zoomLevel-1));
        
        int zoomW = (int)(imageScaleW*zoomLevel);
        int zoomH = (int)(imageScaleH*zoomLevel);
        
        int zX = zoomX - (int)(iX * zoomLevel);
        int zY = zoomY - (int)(iY * zoomLevel);
        
        int s1 = zoomFirstBorderWidth    + zoomFirstBorderGap +
                 zoomSecondBorderWidth*2 + zoomSecondBorderGap;
        int s2 = zoomSecondBorderWidth   + zoomSecondBorderGap;
        int s3 = zoomFirstBorderWidth*2  + zoomFirstBorderGap +
                 zoomSecondBorderWidth*2 + zoomSecondBorderGap;
        
        // ....................................................................
        
        if (invertZoomOut) {
        
        int a = zoomArea.width/2  + zoomArea.width/30  + s3;
        int b = zoomArea.height/2 + zoomArea.height/30 + s3;
        
        if (zoomOrigin.x + a + zoomOffset.x > vw &&
            zoomOffset.x > 0) { zOffset.x *= -1; }
        if (zoomOrigin.x - a + zoomOffset.x < 0 &&
            zoomOffset.x < 0) { zOffset.x *= -1; }

        if (zoomOrigin.y + b - zoomOffset.y > vh &&
            zoomOffset.y < 0) { zOffset.y *= -1; }
        if (zoomOrigin.y - b - zoomOffset.y < 0 &&
            zoomOffset.y > 0) { zOffset.y *= -1; } }
        
        // ....................................................................
        
        if (zoomShapeType == 0)
            { newClip = new Area(new Rectangle2D.Float(x+zOffset.x,
                                                       y-zOffset.y,
                                                       zoomArea.width + 1,
                                                       zoomArea.height + 1)); }
        else
            { newClip = new Area(new Ellipse2D.Float(x+zOffset.x,
                                                     y-zOffset.y,
                                                     zoomArea.width + 1,
                                                     zoomArea.height + 1)); }

        newClip.intersect(new Area(oldClip));
        g2.setClip(newClip);
        
        if (gridVisible) { drawGrid(g2, zoomLevel, zX+zOffset.x,
                                                   zY-zOffset.y); }
        else { g2.setColor(getBackground());
               g2.fillRect(0, 0, vw, vh); }
        
        g2.drawImage(image, zoomX+zOffset.x, zoomY-zOffset.y,
                     zoomW, zoomH, null);
        g2.setClip(oldClip);
        
        // ....................................................................
        
        setImageScaleType(g2, SCALE_TYPE_SMOOTH);
        
        if (zoomFirstBorderVisible) {
        g2.setColor(zoomFirstBorderColor);
        g2.setStroke(new BasicStroke(zoomFirstBorderWidth*2));
        if (zoomShapeType == 0)
            { g2.drawRect(x-s1+zOffset.x, y-s1-zOffset.y,
                          zoomArea.width + s1*2, zoomArea.height + s1*2); }
        else
            { g2.drawOval(x-s1+zOffset.x, y-s1-zOffset.y,
                          zoomArea.width + s1*2, zoomArea.height + s1*2); } }

        if (zoomSecondBorderVisible) {
        g2.setColor(zoomSecondBorderColor);
        g2.setStroke(new BasicStroke(zoomSecondBorderWidth*2));
        if (zoomShapeType == 0)
            { g2.drawRect(x-s2+zOffset.x, y-s2-zOffset.y,
                          zoomArea.width + s2*2, zoomArea.height + s2*2); }
        else
            { g2.drawOval(x-s2+zOffset.x, y-s2-zOffset.y,
                          zoomArea.width + s2*2, zoomArea.height + s2*2); } }
        
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

    float gW = getWidth()  * sF;
    float gH = getHeight() * sF;
    float gS = gridSize    * sF;
    
    g2.setColor(gridLightColor);
    g2.fillRect((int)(-gS*3), (int)(-gS*3), (int)(gW+gS*3), (int)(gH+gS*3));
    g2.setColor(gridDarkColor);

    for (float col = -gS*3; col < gW+gS*3; col += gS) {
    for (float row = -gS*3; row < gH+gS*3; row += gS*2) {
        g2.fillRect((int)(col + dX),
                    (int)(row + (col/gS%2 == 0 ? gS : 0) + dY),
                    (int)(gS), (int)(gS)); } }
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
    float scaleValue = imageScale;

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
    float scaleValue = imageScale;

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

/** Відновлення оригінального розміру відображуваного зображення */
public void zoomToOriginal() { calculateImageFitScale();
                               setImageScale(100);
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

/** Збільшення масштабу лупи */
public void magnifierZoomIn() {
    
    int id = zoomLevels.length - 1;
    float currentLevel = getZoomLevel();
    for (int z = 0; z < zoomLevels.length - 1; z++) {
        if (zoomLevels[z] == currentLevel) { id = z + 1; } }
    
    setZoomLevel(zoomLevels[id]); }

///////////////////////////////////////////////////////////////////////////////

/** Зменшення масштабу лупи */
public void magnifierZoomOut() {
    
    int id = 0;
    float currentLevel = getZoomLevel();
    for (int z = zoomLevels.length - 1; z > 0; z--) {
        if (zoomLevels[z] == currentLevel) { id = z - 1; } }

    setZoomLevel(zoomLevels[id]); }

///////////////////////////////////////////////////////////////////////////////

/** Перехід в режим ручного задання регіону масштабування */
public void setRegion() {
    
    panelRoot.setCursor(CURSOR_REGION);
    regionOrig = new Rectangle();
    specifyRegion = true;
    panelRoot.repaint();
    
}

///////////////////////////////////////////////////////////////////////////////

/**
 * Масштабування зображення в межах визначеного регіону
 * @param region регіон масштабування
 */
public void setRegion (Rectangle region) {
    
    specifyRegion = false;
    panelRoot.repaint();
    
    int x = regionImage.x;
    int y = regionImage.y;
    
    int w = regionImage.width;
    int h = regionImage.height;
    
    int fitType = 0;
    float fitScale = calculateFitScale(w, h)[fitType];
    
    Point center = new Point(x + w/2, y + h/2);
    Point2D.Float percent = getLocalPercentagePoint(center, imageW, imageH);
    
    setImageScale(fitScale);

    Point location = getLocationPoint(percent, imageScaleW, imageScaleH);
    centerOnPoint(location);

}

///////////////////////////////////////////////////////////////////////////////

/** Обертання зображення на 90° за годинниковою стрілкою */
public void turnСlockwise()
    { setRotatedImage(getRotatedImage(image, ROTATE_90_DEG)); }

///////////////////////////////////////////////////////////////////////////////

/** Обертання зображення на 90° проти годинникової стрілки */
public void turnСounterclockwise()
    { setRotatedImage(getRotatedImage(image, ROTATE_270_DEG)); }

///////////////////////////////////////////////////////////////////////////////

/** Обертання зображення на 180° */
public void rollOver()
    { setRotatedImage(getRotatedImage(image, ROTATE_180_DEG)); }

///////////////////////////////////////////////////////////////////////////////

/** Відзеркалення зображення горизонтально */
public void mirrorHorizontally()
    { setRotatedImage(getFlippedImage(image, FLIP_HORIZONTAL)); }

///////////////////////////////////////////////////////////////////////////////

/** Відзеркалення зображення вертикально */
public void mirrorVertically()
    { setRotatedImage(getFlippedImage(image, FLIP_VERTICAL)); }

///////////////////////////////////////////////////////////////////////////////
// Getter'и та Setter'и - повертають та задають властивості компонента ////////
///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання основного зображення
 * @return основне зображення
 */
public BufferedImage getImage() { return image; }

/**
 * Задання основного зображення
 * @param image нове основне зображення
 */
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

/**
 * Отримання резервного зображення
 * @return резервне зображення
 */
public BufferedImage getErrorImage() { return errorImage; }

/**
 * Задання резервного зображення (яке відображається при помилці)
 * @param errorImage нове резервне зображення
 */
public void setErrorImage (BufferedImage errorImage)
    { if (errorImage == null) { errorImage = getRandomImage(); }
      Image oldValue = this.errorImage;
      this.errorImage = errorImage;
      fireAll("errorImage", oldValue, errorImage); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання масштабу зображення
 * @return масштаб зображення
 */
public float getImageScale() { return imageScale; }

/**
 * Задання масштабу зображення
 * @param imageScale новий масштаб зображення
 */
public void setImageScale (float imageScale)
    { calculateImageLimitScale();
      // ......................................................................
      // Ігнорування корекції значення для вписаного та описаного масштабів
      if (Math.abs(imageScale - imageScaleInternalFit) > 0.001 &&
          Math.abs(imageScale - imageScaleExternalFit) > 0.001) {
      if      (imageScale > globalScaleMax) { imageScale = globalScaleMax; }
      else if (imageScale < globalScaleMin) { imageScale = globalScaleMin; }
      if      (imageScale > imageScaleMax)  { imageScale = imageScaleMax;  }
      else if (imageScale < imageScaleMin)  { imageScale = imageScaleMin;  } }
      // ......................................................................
      float oldValue = this.imageScale;
      this.imageScale = imageScale;
      calculateScaledImageSize();
      panelRoot.setPreferredSize(new Dimension(imageScaleW, imageScaleH));
      panelRoot.updateUI();
      fireAll("imageScale", oldValue, imageScale); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання типу масштабування зображення
 * @return SCALE_TYPE_FAST або SCALE_TYPE_SMOOTH
 */
public int getImageScaleType() { return imageScaleType; }

/**
 * Задання типу масштабування зображення
 * @param imageScaleType SCALE_TYPE_FAST або SCALE_TYPE_SMOOTH
 */
public void setImageScaleType (int imageScaleType)
    { int oldValue = this.imageScaleType;
      if (imageScaleType != SCALE_TYPE_FAST &&
          imageScaleType != SCALE_TYPE_SMOOTH)
        { imageScaleType  = SCALE_TYPE_FAST; }
      this.imageScaleType = imageScaleType;
      panelRoot.repaint();
      fireAll("imageScaleType", oldValue, imageScaleType); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання розміру відкривання зображення
 * @return OPEN_SIZE_ORIGINAL, OPEN_SIZE_INTERNAL_FIT
 * або OPEN_SIZE_EXTERNAL_FIT
 */
public int getImageOpenSize() { return imageOpenSize; }

/**
 * Задання розміру відкривання зображення
 * @param imageOpenSize OPEN_SIZE_ORIGINAL, OPEN_SIZE_INTERNAL_FIT
 * або OPEN_SIZE_EXTERNAL_FIT
 */
public void setImageOpenSize (int imageOpenSize)
    { int oldValue = this.imageOpenSize;
      if (imageOpenSize != OPEN_SIZE_ORIGINAL &&
          imageOpenSize != OPEN_SIZE_INTERNAL_FIT &&
          imageOpenSize != OPEN_SIZE_EXTERNAL_FIT)
        { imageOpenSize  = OPEN_SIZE_INTERNAL_FIT; }
      this.imageOpenSize = imageOpenSize;
      fireAll("imageOpenSize", oldValue, imageOpenSize); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання доступності ЛКМ (переміщення зображення)
 * @return true, якщо доступно
 */
public boolean isLMBEnable() { return lmbEnable; }

/**
 * Задання доступності ЛКМ (переміщення зображення)
 * @param lmbEnable true - доступно, false - недоступно
 */
public void setLMBEnable (boolean lmbEnable)
    { boolean oldValue = this.lmbEnable;
      this.lmbEnable = lmbEnable;
      updateCursor();
      fireAll("lmbEnable", oldValue, lmbEnable); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання доступності СКМ (зміна вигляду зображення)
 * @return true, якщо доступно
 */
public boolean isCMBEnable() { return cmbEnable; }

/**
 * Задання доступності СКМ (зміна вигляду зображення)
 * @param cmbEnable true - доступно, false - недоступно
 */
public void setCMBEnable (boolean cmbEnable)
    { boolean oldValue = this.cmbEnable;
      this.cmbEnable = cmbEnable;
      fireAll("cmbEnable", oldValue, cmbEnable); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання доступності ПКМ (масштабування зображення)
 * @return true, якщо доступно
 */
public boolean isRMBEnable() { return rmbEnable; }

/**
 * Задання доступності ПКМ (масштабування зображення)
 * @param rmbEnable true - доступно, false - недоступно
 */
public void setRMBEnable (boolean rmbEnable)
    { boolean oldValue = this.rmbEnable;
      this.rmbEnable = rmbEnable;
      fireAll("rmbEnable", oldValue, rmbEnable); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання доступності комбінації ЛКМ + ПКМ (масштабування регіону)
 * @return true, якщо доступно
 */
public boolean isZMBEnable() { return zmbEnable; }

/**
 * Задання доступності комбінації ЛКМ + ПКМ (масштабування регіону)
 * @param zmbEnable true - доступно, false - недоступно
 */
public void setZMBEnable (boolean zmbEnable)
    { boolean oldValue = this.zmbEnable;
      this.zmbEnable = zmbEnable;
      fireAll("zmbEnable", oldValue, zmbEnable); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання доступності колесика миші (зміна масштабу зображення)
 * @return true, якщо доступно
 */
public boolean isWheelEnable() { return wheelEnable; }

/**
 * Задання доступності колесика миші (зміна масштабу зображення)
 * @param wheelEnable true - доступно, false - недоступно
 */
public void setWheelEnable (boolean wheelEnable)
    { boolean oldValue = this.wheelEnable;
      this.wheelEnable = wheelEnable;
      fireAll("wheelEnable", oldValue, wheelEnable); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання інвертування колесика миші
 * @return true, якщо інвертовано
 */
public boolean isWheelInvert() { return wheelInvert; }

/**
 * Задання інвертування колесика миші
 * @param wheelInvert true - інвертовано, false - неінвертовано
 */
public void setWheelInvert (boolean wheelInvert)
    { boolean oldValue = this.wheelInvert;
      this.wheelInvert = wheelInvert;
      fireAll("wheelInvert", oldValue, wheelInvert); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання доступності переміщення зображення за межами компонента
 * @return true, якщо доступно
 */
public boolean isDrugImageOut() { return drugImageOut; }

/**
 * Задання доступності переміщення зображення за межами компонента
 * @param drugImageOut true - доступно, false - недоступно
 */
public void setDrugImageOut (boolean drugImageOut)
    { boolean oldValue = this.drugImageOut;
      this.drugImageOut = drugImageOut;
      fireAll("drugImageOut", oldValue, drugImageOut); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання видимості фонової сітки
 * @return true, якщо видима
 */
public boolean isGridVisible() { return gridVisible; }

/**
 * Задання видимості фонової сітки
 * @param gridVisible true - видима, false - прихована
 */
public void setGridVisible (boolean gridVisible)
    { boolean oldValue = this.gridVisible;
      this.gridVisible = gridVisible;
      panelRoot.repaint();
      fireAll("gridVisible", oldValue, gridVisible); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання світлого кольору фонової сітки
 * @return світлий колір фонової сітки
 */
public Color getGridLightColor() { return gridLightColor; }

/**
 * Задання світлого кольору фонової сітки
 * @param gridLightColor новий світлий колір фонової сітки
 */
public void setGridLightColor (Color gridLightColor)
    { if (gridLightColor == null) { gridLightColor = Color.LIGHT_GRAY; }
      Color oldValue = this.gridLightColor;
      this.gridLightColor = gridLightColor;
      panelRoot.repaint();
      fireAll("gridLightColor", oldValue, gridLightColor); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання темного кольору фонової сітки
 * @return темний колір фонової сітки
 */
public Color getGridDarkColor() { return gridDarkColor; }

/**
 * Задання темного кольору фонової сітки
 * @param gridDarkColor новий темний колір фонової сітки
 */
public void setGridDarkColor (Color gridDarkColor)
    { if (gridDarkColor == null) { gridDarkColor = Color.DARK_GRAY; }
      Color oldValue = this.gridDarkColor;
      this.gridDarkColor = gridDarkColor;
      panelRoot.repaint();
      fireAll("gridDarkColor", oldValue, gridDarkColor); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання розміру фонової сітки
 * @return розмір фонової сітки
 */
public int getGridSize() { return gridSize; }

/**
 * Задання розміру фонової сітки
 * @param gridSize новий розмір фонової сітки
 */
public void setGridSize (int gridSize)
    { int oldValue = this.gridSize;
      if (gridSize < 3) { gridSize = 3; }
      this.gridSize = gridSize;
      panelRoot.repaint();
      fireAll("gridSize", oldValue, gridSize); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання штриха регіону масштабування
 * @return штрих регіону масштабування
 */
public BasicStroke getRegionStroke() { return regionStroke; }

/**
 * Задання штриха регіону масштабування
 * @param regionStroke новий штрих регіону масштабування
 */
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

/**
 * Отримання видимості додаткового штриха регіону масштабування
 * @return true, якщо видимий
 */
public boolean isRegionAdditionalStroke() { return regionAdditionalStroke; }

/**
 * Задання видимості додаткового штриха регіону масштабування
 * @param regionAdditionalStroke true - видимий, false - невидимий
 */
public void setRegionAdditionalStroke (boolean regionAdditionalStroke)
    { boolean oldValue = this.regionAdditionalStroke;
      this.regionAdditionalStroke = regionAdditionalStroke;
      fireAll("regionAdditionalStroke", oldValue, regionAdditionalStroke); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання світлого кольору рамки регіону масштабування
 * @return світлий колір рамки регіону масштабування
 */
public Color getRegionLightColor() { return regionLightColor; }

/**
 * Задання світлого кольору рамки регіону масштабування
 * @param regionLightColor новий світлий колір рамки регіону масштабування
 */
public void setRegionLightColor (Color regionLightColor)
    { if (regionLightColor == null) { regionLightColor = Color.WHITE; }
      Color oldValue = this.regionLightColor;
      this.regionLightColor = regionLightColor;
      fireAll("regionLightColor", oldValue, regionLightColor); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання темного кольору рамки регіону масштабування
 * @return темний колір рамки регіону масштабування
 */
public Color getRegionDarkColor() { return regionDarkColor; }

/**
 * Задання темного кольору рамки регіону масштабування
 * @param regionDarkColor новий темний колір рамки регіону масштабування
 */
public void setRegionDarkColor (Color regionDarkColor)
    { if (regionDarkColor == null) { regionDarkColor = Color.DARK_GRAY; }
      Color oldValue = this.regionDarkColor;
      this.regionDarkColor = regionDarkColor;
      fireAll("regionDarkColor", oldValue, regionDarkColor); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання мінімального розміру регіону масштабування
 * @return мінімальний розмір регіону масштабування
 */
public int getRegionMinimumSize() { return regionMinimumSize; }

/**
 * Задання мінімального розміру регіону масштабування
 * @param regionMinimumSize новий мінімальний розмір регіону масштабування
 */
public void setRegionMinimumSize (int regionMinimumSize)
    { if (regionMinimumSize < 3) { regionMinimumSize = 3; }
      int oldValue = this.regionMinimumSize;
      this.regionMinimumSize = regionMinimumSize;
      fireAll("regionMinimumSize", oldValue, regionMinimumSize); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання розмірів області масштабування
 * @return розміри області масштабування
 */
public Dimension getZoomArea() { return zoomArea; }

/**
 * Задання розмірів області масштабування
 * @param zoomArea нові розміри області масштабування
 */
public void setZoomArea (Dimension zoomArea)
    { if (zoomArea == null) { zoomArea = new Dimension(180, 180); }
      Dimension oldValue = this.zoomArea;
      this.zoomArea = zoomArea;
      fireAll("zoomArea", oldValue, zoomArea); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання відступів області масштабування
 * @return відступи області масштабування
 */
public Point getZoomOffset() { return zoomOffset; }

/**
 * Задання відступів області масштабування
 * @param zoomOffset нові відступи області масштабування
 */
public void setZoomOffset (Point zoomOffset)
    { if (zoomOffset == null) { zoomOffset = new Point(0, 0); }
      Point oldValue = this.zoomOffset;
      this.zoomOffset = zoomOffset;
      fireAll("zoomOffset", oldValue, zoomOffset); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання рівня масштабування лупи
 * @return рівень масштабування лупи
 */
public float getZoomLevel() { return zoomLevel; }

/**
 * Задання рівня масштабування лупи
 * @param zoomLevel новий рівень масштабування лупи
 */
public void setZoomLevel (float zoomLevel)
    { if (zoomLevel < zoomLevels[0])
          { zoomLevel = zoomLevels[0]; }
      else if (zoomLevel > zoomLevels[zoomLevels.length-1])
          { zoomLevel = zoomLevels[zoomLevels.length-1]; }
      else
          { float copy = zoomLevel;
            zoomLevel = ZOOM_SCALE_X3_00;
            for (float level : zoomLevels) {
                if (level == copy) { zoomLevel = level;
                                     break; } } }
    
      float oldValue = this.zoomLevel;
      this.zoomLevel = zoomLevel;
      panelRoot.repaint();
      fireAll("zoomLevel", oldValue, zoomLevel); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання типу фігури області масштабування
 * @return ZOOM_SHAPE_RECTANGLE або ZOOM_SHAPE_ELLIPSE
 */
public int getZoomShapeType() { return zoomShapeType; }

/**
 * Задання типу фігури області масштабування
 * @param zoomShapeType ZOOM_SHAPE_RECTANGLE або ZOOM_SHAPE_ELLIPSE
 */
public void setZoomShapeType (int zoomShapeType)
    { int oldValue = this.zoomShapeType;
      if (zoomShapeType != ZOOM_SHAPE_RECTANGLE &&
          zoomShapeType != ZOOM_SHAPE_ELLIPSE)
        { zoomShapeType  = ZOOM_SHAPE_ELLIPSE; }
      this.zoomShapeType = zoomShapeType;
      fireAll("zoomShapeType", oldValue, zoomShapeType); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання видимості I рамки лупи
 * @return true, якщо видима
 */
public boolean isZoomFirstBorderVisible() { return zoomFirstBorderVisible; }

/**
 * Задання видимості I рамки лупи
 * @param zoomFirstBorderVisible true - видима, false - невидима
 */
public void setZoomFirstBorderVisible (boolean zoomFirstBorderVisible)
    { boolean oldValue = this.zoomFirstBorderVisible;
      this.zoomFirstBorderVisible = zoomFirstBorderVisible;
      fireAll("zoomFirstBorderVisible", oldValue, zoomFirstBorderVisible); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання видимості II рамки лупи
 * @return true, якщо видима
 */
public boolean isZoomSecondBorderVisible() { return zoomSecondBorderVisible; }

/**
 * Задання видимості II рамки лупи
 * @param zoomSecondBorderVisible true - видима, false - невидима
 */
public void setZoomSecondBorderVisible (boolean zoomSecondBorderVisible)
    { boolean oldValue = this.zoomSecondBorderVisible;
      this.zoomSecondBorderVisible = zoomSecondBorderVisible;
      fireAll("zoomSecondBorderVisible", oldValue, zoomSecondBorderVisible); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання ширини I рамки лупи
 * @return ширина I рамки лупи
 */
public int getZoomFirstBorderWidth() { return zoomFirstBorderWidth; }

/**
 * Задання ширини I рамки лупи
 * @param zoomFirstBorderWidth нова ширина I рамки лупи
 */
public void setZoomFirstBorderWidth (int zoomFirstBorderWidth)
    { int oldValue = this.zoomFirstBorderWidth;
      if (zoomFirstBorderWidth < 0) { zoomFirstBorderWidth = 0; }
      this.zoomFirstBorderWidth = zoomFirstBorderWidth;
      fireAll("zoomFirstBorderWidth", oldValue, zoomFirstBorderWidth); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання ширини II рамки лупи
 * @return ширина II рамки лупи
 */
public int getZoomSecondBorderWidth() { return zoomSecondBorderWidth; }

/**
 * Задання ширини II рамки лупи
 * @param zoomSecondBorderWidth нова ширина II рамки лупи
 */
public void setZoomSecondBorderWidth (int zoomSecondBorderWidth)
    { int oldValue = this.zoomSecondBorderWidth;
      if (zoomSecondBorderWidth < 0) { zoomSecondBorderWidth = 0; }
      this.zoomSecondBorderWidth = zoomSecondBorderWidth;
      fireAll("zoomSecondBorderWidth", oldValue, zoomSecondBorderWidth); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання відступу I рамки лупи
 * @return відступ I рамки лупи
 */
public int getZoomFirstBorderGap() { return zoomFirstBorderGap; }

/**
 * Задання відступу I рамки лупи
 * @param zoomFirstBorderGap новий відступ I рамки лупи
 */
public void setZoomFirstBorderGap (int zoomFirstBorderGap)
    { int oldValue = this.zoomFirstBorderGap;
      this.zoomFirstBorderGap = zoomFirstBorderGap;
      fireAll("zoomFirstBorderGap", oldValue, zoomFirstBorderGap); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання відступу II рамки лупи
 * @return відступ II рамки лупи
 */
public int getZoomSecondBorderGap() { return zoomSecondBorderGap; }

/**
 * Задання відступу II рамки лупи
 * @param zoomSecondBorderGap новий відступ II рамки лупи
 */
public void setZoomSecondBorderGap (int zoomSecondBorderGap)
    { int oldValue = this.zoomSecondBorderGap;
      this.zoomSecondBorderGap = zoomSecondBorderGap;
      fireAll("zoomSecondBorderGap", oldValue, zoomSecondBorderGap); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання кольору I рамки лупи
 * @return колір I рамки лупи
 */
public Color getZoomFirstBorderColor() { return zoomFirstBorderColor; }

/**
 * Задання кольору I рамки лупи
 * @param zoomFirstBorderColor новий колір I рамки лупи
 */
public void setZoomFirstBorderColor (Color zoomFirstBorderColor)
    { if (zoomFirstBorderColor == null) { zoomFirstBorderColor =
                                          Color.DARK_GRAY; }
      Color oldValue = this.zoomFirstBorderColor;
      this.zoomFirstBorderColor = zoomFirstBorderColor;
      fireAll("zoomFirstBorderColor", oldValue, zoomFirstBorderColor); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання кольору II рамки лупи
 * @return колір II рамки лупи
 */
public Color getZoomSecondBorderColor() { return zoomSecondBorderColor; }

/**
 * Задання кольору II рамки лупи
 * @param zoomSecondBorderColor новий колір II рамки лупи
 */
public void setZoomSecondBorderColor (Color zoomSecondBorderColor)
    { if (zoomSecondBorderColor == null) { zoomSecondBorderColor =
                                           Color.GRAY; }
      Color oldValue = this.zoomSecondBorderColor;
      this.zoomSecondBorderColor = zoomSecondBorderColor;
      fireAll("zoomSecondBorderColor", oldValue, zoomSecondBorderColor); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання видимості курсора при збільшенні
 * @return true, якщо видимий
 */
public boolean isZoomShowCursor() { return zoomShowCursor; }

/**
 * Задання видимості курсора при збільшенні
 * @param zoomShowCursor true - видимий, false - невидимий
 */
public void setZoomShowCursor (boolean zoomShowCursor)
    { boolean oldValue = this.zoomShowCursor;
      this.zoomShowCursor = zoomShowCursor;
      fireAll("zoomShowCursor", oldValue, zoomShowCursor); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання видимості лупи за межею компонента
 * @return true, якщо видима
 */
public boolean isDrugZoomOut() { return drugZoomOut; }

/**
 * Задання видимості лупи за межею компонента
 * @param drugZoomOut true - видима, false - невидима
 */
public void setDrugZoomOut (boolean drugZoomOut)
    { boolean oldValue = this.drugZoomOut;
      this.drugZoomOut = drugZoomOut;
      fireAll("drugZoomOut", oldValue, drugZoomOut); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Отримання інвертування лупи за межею компонента
 * @return true, якщо інвертовано
 */
public boolean isInvertZoomOut() { return invertZoomOut; }

/**
 * Задання інвертування лупи за межею компонента
 * @param invertZoomOut true - інвертовано, false - неінвертовано
 */
public void setInvertZoomOut (boolean invertZoomOut)
    { boolean oldValue = this.invertZoomOut;
      this.invertZoomOut = invertZoomOut;
      fireAll("invertZoomOut", oldValue, invertZoomOut); }

///////////////////////////////////////////////////////////////////////////////
// Додавання та видалення прослуховувачів подій ///////////////////////////////
///////////////////////////////////////////////////////////////////////////////

/**
 * Додавання нового прослуховувача типу JImageViewListener
 * @param listener новий прослуховувач типу JImageViewListener
 */
public void addJImageViewListener (JImageViewListener listener)
    { getListeners().add(listener); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Видалення існуючого прослуховувача типу JImageViewListener
 * @param listener існуючий прослуховувач типу JImageViewListener
 */
public void removeJImageViewListener (JImageViewListener listener)
    { getListeners().remove(listener); }

///////////////////////////////////////////////////////////////////////////////

// Отримання доступних прослуховувачів типу JImageViewListener
private ArrayList <JImageViewListener> getListeners()
    { if (listeners == null) { listeners = new ArrayList<>(); }
      return listeners; }

///////////////////////////////////////////////////////////////////////////////

// Відправка подій усім доступним прослуховувачам
private void fireAll (String name, Object oldValue, Object newValue) {
    
    fireEvent          (name, oldValue, newValue);
    firePropertyChange (name, oldValue, newValue);
    
}

///////////////////////////////////////////////////////////////////////////////

// Відправка подій доступним прослуховувачам типу JImageViewListener
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
        case "ZMBEnable"
              -> listener.ZMBEnableChange(event);
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
        case "regionMinimumSize"
              -> listener.regionMinimumSizeChange(event);
        case "zoomArea"
              -> listener.zoomAreaChange(event);
        case "zoomOffset"
              -> listener.zoomOffsetChange(event);
        case "zoomLevel"
              -> listener.zoomLevelChange(event);
        case "zoomShapeType"
              -> listener.zoomShapeTypeChange(event);
        case "zoomFirstBorderVisible"
              -> listener.zoomFirstBorderVisibleChange(event);
        case "zoomSecondBorderVisible"
              -> listener.zoomSecondBorderVisibleChange(event);
        case "zoomFirstBorderWidth"
              -> listener.zoomFirstBorderWidthChange(event);
        case "zoomSecondBorderWidth"
              -> listener.zoomSecondBorderWidthChange(event);
        case "zoomFirstBorderGap"
              -> listener.zoomFirstBorderGapChange(event);
        case "zoomSecondBorderGap"
              -> listener.zoomSecondBorderGapChange(event);
        case "zoomFirstBorderColor"
              -> listener.zoomFirstBorderColorChange(event);
        case "zoomSecondBorderColor"
              -> listener.zoomSecondBorderColorChange(event);
        case "zoomShowCursor"
              -> listener.zoomShowCursorChange(event);
        case "drugZoomOut"
              -> listener.drugZoomOutChange(event);
        case "invertZoomOut"
              -> listener.invertZoomOutChange(event);
    }
}
}

///////////////////////////////////////////////////////////////////////////////
// Допоміжні методи ///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

private void setRotatedImage (BufferedImage image)
    { this.image = image;
      this.imageW = image.getWidth();
      this.imageH = image.getHeight();
      this.changeListener.stateChanged(null);
      setImageScale(imageScale); }

///////////////////////////////////////////////////////////////////////////////

private void calculateImageLimitScale() {
    
    int w = imageW;
    int h = imageH;

    int q = (w > h) ? h : w;
    int z = (w > h) ? w : h;
    
    imageScaleMin = (48f   / q * 100);
    imageScaleMax = (7000f / z * 100);
    
    if (imageScaleMin > 100) { imageScaleMin = 100; }
    if (imageScaleMax < 100) { imageScaleMax = 100; }
    
}

///////////////////////////////////////////////////////////////////////////////

private void calculateImageFitScale() {
    
    float[] fitScale = calculateFitScale(imageW, imageH);
    
    imageScaleInternalFit = fitScale[0];
    imageScaleExternalFit = fitScale[1];

}

///////////////////////////////////////////////////////////////////////////////

private float[] calculateFitScale (int regionW, int regionH) {
    
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
    
    float fitWi = (100f * (vW + sW - qW) / iW);
    float fitHi = (100f * (vH + sH - qH) / iH);
    
    float fitWe = (100f * (vW + eW - mW) / iW);
    float fitHe = (100f * (vH + eH - mH) / iH);
    
    return new float[] { (fitWi < fitHi) ? fitWi : fitHi,
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
    path = String.format(path, names[index]);
    
    try (InputStream is = getClass().getResourceAsStream(path)) {
        return JImageViewUtils.getImageQuickly(is); }
    catch (Exception e) { return null; }

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

    if (rmbPressed)
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

private final MouseListener mouseListener = new MouseAdapter() {

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
            
            if (!lmbEnable) { return; }
            if (cmbPressed || rmbPressed) { return; }
            lmbPressed = true;
            
            if (specifyRegion)
                { zmbPressed = false;
                  regionOrig.setLocation(getPointOnImage(me)); }
            else
                { pressOrigin = getPointOnImage(me);
                  panelRoot.setCursor(isScrollBarVisible() ? CURSOR_HAND :
                                                             CURSOR_DEFAULT); }
        }
        
        // Середня клавіша миші
        case MouseEvent.BUTTON2 -> {
        
            if (!cmbEnable) { return; }
            if (specifyRegion || lmbPressed || rmbPressed) { return; }
            cmbPressed = true;
            
            double sX = Math.abs(imageScale - imageScaleInternalFit);
            double sY = Math.abs(imageScale - imageScaleExternalFit);
            
            // Якщо зображення вписане - описуємо його
            if      (sX < 0.001) { fitExternal();    }
            // Якщо зображення описане - відновлюємо оригінальний розмір
            else if (sY < 0.001) { zoomToOriginal(); }
            // В інших випадках - вписуємо його
            else                 { fitInternal();    }        
        }
        
        // Права клавіша миші
        case MouseEvent.BUTTON3 -> {
            
            if (!rmbEnable) { return; }
            if (specifyRegion || cmbPressed) { return; }

            if (lmbPressed) { 
                if (!zmbEnable) { return; }
                else { lmbPressed = cmbPressed = rmbPressed = false;
                       zmbPressed = true;
                       setRegion();
                       return; } }
            
            rmbPressed = true;

            zoomOrigin = me.getPoint();
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

            lmbPressed = false;
            if (zmbPressed || cmbPressed || rmbPressed) { return; }
            
            if (specifyRegion)
                { if (regionNorm.width  <= regionMinimumSize ||
                      regionNorm.height <= regionMinimumSize) { setRegion(); }
                  else { updateCursor();
                         setRegion(regionNorm); } }
            else
                { pressOrigin = null;
                  panelRoot.setCursor(CURSOR_DEFAULT); }
        }
        
        // Середня клавіша миші
        case MouseEvent.BUTTON2 -> { cmbPressed = false; }
        
        // Права клавіша миші
        case MouseEvent.BUTTON3 -> {
            
            rmbPressed = false;
            if (specifyRegion || lmbPressed || cmbPressed) { return; }

            zoomOrigin = null;
            updateCursor();
            repaint();
        }
    }
}

// ............................................................................

@Override
public void mouseWheelMoved (MouseWheelEvent mwe) {
    
    if (!wheelEnable || specifyRegion || lmbPressed || cmbPressed) { return; }
    
    // Масштабування лупи
    if (rmbPressed)
        { if (mwe.getWheelRotation() > 0)
              { if (wheelInvert) { magnifierZoomOut(); }
                else             { magnifierZoomIn();  } }
          else
              { if (wheelInvert) { magnifierZoomIn();  }
                else             { magnifierZoomOut(); } } }
    
    // Масштабування зображення
    else
        { if (mwe.getWheelRotation() > 0)
              { if (wheelInvert) { zoomOut(mwe.getPoint()); }
                else             { zoomIn(mwe.getPoint());  } }
          else
              { if (wheelInvert) { zoomIn(mwe.getPoint());  }
                else             { zoomOut(mwe.getPoint()); } } }

}
};

///////////////////////////////////////////////////////////////////////////////

public final MouseMotionListener mouseMotionListener
       = new MouseMotionAdapter() {

@Override
public void mouseDragged (MouseEvent me) {

    // Задання регіону масштабування
    if (specifyRegion) {

        if (!lmbPressed) { return; }
        
        Point point_new = getPointOnImage(me);
        Point point_old = regionOrig.getLocation();
        
        regionOrig.setSize(point_new.x - point_old.x,
                           point_new.y - point_old.y);
        
        panelRoot.repaint();
    
    }
    
    // Ліва клавіша миші
    else if (lmbPressed) {
    
        if (!drugImageOut && !cursorOnImage) { return; }
        if (cmbPressed || rmbPressed || zmbPressed) { return; }
        
        Point point = getPointOnImage(me);

        int deltaX = pressOrigin.x - point.x;
        int deltaY = pressOrigin.y - point.y;

        Rectangle view = getViewport().getViewRect();
        view.x += deltaX;
        view.y += deltaY;

        panelRoot.scrollRectToVisible(view);
    
    }
    
    // Права клавіша миші
    else if (rmbPressed) {
    
        if (lmbPressed || cmbPressed || zmbPressed) { return; }
        
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
        
        if (initialState) { 
            switch (imageOpenSize) {
                case OPEN_SIZE_ORIGINAL     -> zoomToOriginal();
                case OPEN_SIZE_INTERNAL_FIT -> fitInternal();
                case OPEN_SIZE_EXTERNAL_FIT -> fitExternal(); }
            initialState = false;
        }
    }
};

// Кінець класу JImageView ////////////////////////////////////////////////////

}
