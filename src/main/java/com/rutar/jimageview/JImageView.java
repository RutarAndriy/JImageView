package com.rutar.jimageview;

import java.net.*;
import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

// ............................................................................

/**
 * Клас JImageView
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageView extends JScrollPane {

private static Cursor CURSOR_DEFAULT = null;

private static final Cursor CURSOR_HAND = new Cursor(Cursor.HAND_CURSOR);
private static final Cursor CURSOR_MOVE = new Cursor(Cursor.MOVE_CURSOR);

// ............................................................................

private static ArrayList <JImageViewListener> listeners = null;
private static transient PropertyChangeSupport propertyChangeSupport = null;

// ............................................................................

private boolean drugImageOut = true;         // Переміщення за межею компонента

private boolean backgroundGrid = true;                          // Фонова сітка
private Color gridLightColor = Color.LIGHT_GRAY;       // I колір фонової сітки
private Color gridDarkColor  = Color.DARK_GRAY;       // II колір фонової сітки
private int gridSize = 25;                                      // Розмір сітки

private int imageScale = 100;                             // Масштаб зображення

private ImageIcon image = null;                        // Зображення для показу
private ImageIcon errorImage = null;  // Зображення яке показується при помилці

private int imageScaleMax;             // Мінімальний масштаб заного зображення
private int imageScaleMin;            // Максимальний масштаб заного зображення
private int imageScaleInternalFit;       // Масштаб для внутрішнього заповнення
private int imageScaleExternalFit;        // Масштаб для зовнішнього заповнення

// ............................................................................

private int scaleMin = 10;
private int scaleMax = 900;

private JScrollBar hScrollBar, vScrollBar;

// ............................................................................

// Масив стандартних масштабів
private final int[] scales =
    { 10,  15,  20,  25,  30,  35,  40,  45,  50,
                          60,  70,  80,  90, 100,
          125, 150, 175, 200, 225, 250, 275, 300,
                              350, 400, 450, 500,
                              600, 700, 800, 900  };

///////////////////////////////////////////////////////////////////////////////

public JImageView() { initComponents(); }

///////////////////////////////////////////////////////////////////////////////

@SuppressWarnings("unchecked")                       
private void initComponents() {

panelRoot = new RootPane();
labelImage = new JLabel();

setErrorImage(null);
setImage(null);

labelImage.setIcon(getScaledImage());
labelImage.addMouseListener(imageMouseListener);
labelImage.addMouseMotionListener(imageMouseMotionListener);
labelImage.setCursor(CURSOR_DEFAULT);

GroupLayout panel_rootLayout = new GroupLayout(panelRoot);
panelRoot.setLayout(panel_rootLayout);
panel_rootLayout.setHorizontalGroup(panel_rootLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
    .addGroup(panel_rootLayout.createSequentialGroup()
        .addGap(0, 0, Short.MAX_VALUE)
        .addComponent(labelImage)
        .addGap(0, 0, Short.MAX_VALUE))
);
panel_rootLayout.setVerticalGroup(panel_rootLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
    .addGroup(panel_rootLayout.createSequentialGroup()
        .addGap(0, 0, Short.MAX_VALUE)
        .addComponent(labelImage)
        .addGap(0, 0, Short.MAX_VALUE))
);

addMouseListener(imageViewMouseListener);
addMouseWheelListener((MouseWheelListener) imageViewMouseListener);

getViewport().addChangeListener(changeListener);
setWheelScrollingEnabled(false);
setViewportView(panelRoot);

}

///////////////////////////////////////////////////////////////////////////////

public void zoomIn() {
    
    if (imageScale == scaleMax ||
        imageScale >= imageScaleMax) { return; }
    
    if      (imageScale >= 10  && imageScale < 50)  { imageScale +=   5; }
    else if (imageScale >= 50  && imageScale < 100) { imageScale +=  10; }
    else if (imageScale >= 100 && imageScale < 300) { imageScale +=  25; }
    else if (imageScale >= 300 && imageScale < 500) { imageScale +=  50; }
    else if (imageScale >= 500 && imageScale < 900) { imageScale += 100; }

    setImageScale(imageScale);

}

// ............................................................................

public void zoomOut() {
    
    if (imageScale == scaleMin ||
        imageScale <= imageScaleMin) { return; }
    
    if      (imageScale > 10  && imageScale <= 50)  { imageScale -=   5; }
    else if (imageScale > 50  && imageScale <= 100) { imageScale -=  10; }
    else if (imageScale > 100 && imageScale <= 300) { imageScale -=  25; }
    else if (imageScale > 300 && imageScale <= 500) { imageScale -=  50; }
    else if (imageScale > 500 && imageScale <= 900) { imageScale -= 100; }

    setImageScale(imageScale);

}

// ............................................................................

public void zoomOriginal() { setImageScale(100); }

///////////////////////////////////////////////////////////////////////////////

public void fitInternal() { setImageScale(imageScaleInternalFit); }
public void fitExternal() { setImageScale(imageScaleExternalFit); }

///////////////////////////////////////////////////////////////////////////////

private final class RootPane extends JPanel {

@Override
public void paintComponent (Graphics g) {

    super.paintComponent(g);

    if (!backgroundGrid) { return; }

    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(gridLightColor);
    g2.fillRect(0, 0, getWidth(), getHeight());
    g2.setColor(gridDarkColor);

    for (int a = 0; a < getWidth();  a += gridSize) {
    for (int b = 0; b < getHeight(); b += gridSize*2) {
        g.fillRect(a, b + (a/gridSize%2 == 0 ? gridSize : 0),
                   gridSize, gridSize);
    }
    }

}
}

///////////////////////////////////////////////////////////////////////////////

private void calculateImageScaleMinMax (ImageIcon image) {

    int w = image.getIconWidth();
    int h = image.getIconHeight();
    
    int z = (w > h) ? w : h;
    
    imageScaleMin = (int)(48d   / z * 100);
    imageScaleMax = (int)(3000d / z * 100);
    
    if (imageScaleMin > 100) { imageScaleMin = 100; }
    if (imageScaleMax < 100) { imageScaleMax = 100; }
    
}

// ............................................................................

private int iW, vW, mW, sW, eW;
private int iH, vH, mH, sH, eH;
private int fitWi, fitHi, fitWe, fitHe;

// ............................................................................

private void calculateImageScaleFit() {
    
    // Ширина зображення
    iW = image.getIconWidth();
    // Ширина області перегляду
    vW = getViewport().getWidth();
    // Ширина вертикального скролбару
    mW = vScrollBar.getMaximumSize().width;
    // Активна ширина вертикального скролбару
    sW = vScrollBar.isVisible() ? mW : 0;
    // Комія змінної sW, використовується для розрахунку imageScaleExternalFit
    eW = sW;
    
    // Висота зображення
    iH = image.getIconHeight();
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

public ImageIcon getImage() { return image; }

public void setImage (ImageIcon image)
    { if (image == null) { image = getErrorImage(); }
      calculateImageScaleMinMax(image);
      labelImage.setIcon(image);
      ImageIcon oldValue = this.image;
      this.image = image;
      fireEvent("image", oldValue, image);
      getPropertyChangeSupport().firePropertyChange("image",
                                                    oldValue, image);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

public ImageIcon getErrorImage() { return errorImage; }

public void setErrorImage (ImageIcon errorImage)
    { if (errorImage == null) { errorImage = getRandomImage(); }
      ImageIcon oldValue = this.errorImage;
      this.errorImage = errorImage;
      fireEvent("errorImage", oldValue, errorImage);
      getPropertyChangeSupport().firePropertyChange("errorImage",
                                                    oldValue, errorImage);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

private ImageIcon getScaledImage() {
    
    if (imageScale == 100) { return image; }
    
    Image original = image.getImage();
    
    int w = (int)(original.getWidth(null)  * imageScale/100f);
    int h = (int)(original.getHeight(null) * imageScale/100f);

    Image scaled = original.getScaledInstance(w, h, Image.SCALE_FAST);
    
    return new ImageIcon(scaled);
    
}

///////////////////////////////////////////////////////////////////////////////

private ImageIcon getRandomImage() {
    
    String path = "/com/rutar/jimageview/images/%s.png";
    String[] names = { "tree", "fire", "wave" };

    int index = (int)(Math.random() * 3);
    URL resource = getClass().getResource(String.format(path, names[index]));

    return new ImageIcon(resource);

}

///////////////////////////////////////////////////////////////////////////////

public boolean isDrugImageOut() { return drugImageOut; }

public void setDrugImageOut (boolean drugImageOut)
    { boolean oldValue = this.drugImageOut;
      this.drugImageOut = drugImageOut;
      fireEvent("drugImageOut", oldValue, drugImageOut);
      getPropertyChangeSupport().firePropertyChange("drugImageOut",
                                                    oldValue, drugImageOut);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

public boolean isBackgroundGrid() { return backgroundGrid; }

public void setBackgroundGrid (boolean backgroundGrid)
    { boolean oldValue = this.backgroundGrid;
      this.backgroundGrid = backgroundGrid;
      fireEvent("backgroundGrid", oldValue, backgroundGrid);
      getPropertyChangeSupport().firePropertyChange("backgroundGrid",
                                                    oldValue, backgroundGrid);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

public Color getGridLightColor() { return gridLightColor; }

public void setGridLightColor (Color gridLightColor)
    { Color oldValue = this.gridLightColor;
      this.gridLightColor = gridLightColor;
      fireEvent("gridLightColor", oldValue, gridLightColor);
      getPropertyChangeSupport().firePropertyChange("gridLightColor",
                                                    oldValue, gridLightColor);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

public Color getGridDarkColor() { return gridDarkColor; }

public void setGridDarkColor (Color gridDarkColor)
    { Color oldValue = this.gridDarkColor;
      this.gridDarkColor = gridDarkColor;
      fireEvent("gridDarkColor", oldValue, gridDarkColor);
      getPropertyChangeSupport().firePropertyChange("gridDarkColor",
                                                    oldValue, gridDarkColor);
      repaint(); }

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
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

public int getImageScale() { return imageScale; }

public void setImageScale (int imageScale)
    { if      (imageScale > scaleMax)      { imageScale = scaleMax;      }
      else if (imageScale < scaleMin)      { imageScale = scaleMin;      }
      else if (imageScale > imageScaleMax) { imageScale = imageScaleMax; }
      else if (imageScale < imageScaleMin) { imageScale = imageScaleMin; }
      int oldValue = this.imageScale;
      this.imageScale = imageScale;
      fireEvent("imageScale", oldValue, imageScale);
      getPropertyChangeSupport().firePropertyChange("imageScale",
                                                    oldValue, imageScale);
      labelImage.setIcon(getScaledImage()); }

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

private final MouseListener imageMouseListener
        = new MouseAdapter() {

@Override
public void mouseEntered (MouseEvent me) { cursorOnImage = true; }

@Override
public void mouseExited (MouseEvent me) { cursorOnImage = false; }

// ............................................................................

@Override
public void mousePressed (MouseEvent me) {
    origin = new Point(me.getPoint());
    labelImage.setCursor(isScrollBarVisible() ? CURSOR_HAND : null);
}

@Override
public void mouseReleased (MouseEvent me) {
    labelImage.setCursor(isScrollBarVisible() ? CURSOR_DEFAULT : null);
}

};

///////////////////////////////////////////////////////////////////////////////

public final MouseMotionListener imageMouseMotionListener
       = new MouseMotionAdapter() {

@Override
public void mouseDragged (MouseEvent me) {

    if (origin != null) {
                      
        if (!drugImageOut && !cursorOnImage) { return; }
        
        imageViewport = (JViewport) SwingUtilities
                        .getAncestorOfClass(JViewport.class, labelImage);
        
        if (imageViewport != null) {
            
            int deltaX = origin.x - me.getX();
            int deltaY = origin.y - me.getY();

            Rectangle view = imageViewport.getViewRect();
            view.x += deltaX;
            view.y += deltaY;

            labelImage.scrollRectToVisible(view);
            
        }
    }
}
};


///////////////////////////////////////////////////////////////////////////////

private final MouseListener imageViewMouseListener
        = new MouseAdapter() {
    
@Override
public void mouseClicked (MouseEvent e) {

}
 
@Override
public void mousePressed (MouseEvent me) {

}

@Override
public void mouseReleased (MouseEvent me) {

}

// ............................................................................

@Override
public void mouseWheelMoved (MouseWheelEvent mwe)
    { if (mwe.getWheelRotation() > 0) { zoomIn(); }
      else                            { zoomOut(); } }

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
        labelImage.setCursor(CURSOR_DEFAULT);
        
        }
        
        calculateImageScaleFit();
        
    }
};

///////////////////////////////////////////////////////////////////////////////

private boolean isScrollBarVisible()
    { hScrollBar = getHorizontalScrollBar();
      vScrollBar = getVerticalScrollBar();
      
      return hScrollBar.isVisible() || vScrollBar.isVisible(); }

///////////////////////////////////////////////////////////////////////////////

private JLabel labelImage;
private JPanel panelRoot;

// Кінець класу JImageView ////////////////////////////////////////////////////

}
