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

private int imageScale = 100;

private ImageIcon image = null;
private ImageIcon errorImage = null;

private int imageScaleMax;
private int imageScaleMin;
private int imageScaleInternalFit;
private int imageScaleExternalFit;

///////////////////////////////////////////////////////////////////////////////

public JImageView() { initComponents();

}

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
    
    if (imageScale == 900 ||
        imageScale >= imageScaleMax) { return; }
    
    if      (imageScale >= 10  && imageScale < 50)  { imageScale +=   5; }
    else if (imageScale >= 50  && imageScale < 100) { imageScale +=  10; }
    else if (imageScale >= 100 && imageScale < 300) { imageScale +=  25; }
    else if (imageScale >= 300 && imageScale < 500) { imageScale +=  50; }
    else if (imageScale >= 500 && imageScale < 900) { imageScale += 100; }

    labelImage.setIcon(getScaledImage());

}

// ............................................................................

public void zoomOut() {
    
    if (imageScale == 10 ||
        imageScale <= imageScaleMin) { return; }
    
    if      (imageScale > 10  && imageScale <= 50)  { imageScale -=   5; }
    else if (imageScale > 50  && imageScale <= 100) { imageScale -=  10; }
    else if (imageScale > 100 && imageScale <= 300) { imageScale -=  25; }
    else if (imageScale > 300 && imageScale <= 500) { imageScale -=  50; }
    else if (imageScale > 500 && imageScale <= 900) { imageScale -= 100; }

    labelImage.setIcon(getScaledImage());

}

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

public ImageIcon getImage() { return image; }

public void setImage (ImageIcon image)
    { if (image == null) { image = getErrorImage(); }
      setImageScaleMinMax(image);
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

    Image scaled = original.getScaledInstance(w, h, Image.SCALE_SMOOTH);
    
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

private void setImageScaleMinMax (ImageIcon image) {

    int w = image.getIconWidth();
    int h = image.getIconHeight();
    
    int z = (w > h) ? w : h;
    
    imageScaleMin = (int)(48d   / z * 100);
    imageScaleMax = (int)(3000d / z * 100);
    
    if (imageScaleMin > 100) { imageScaleMin = 100; }
    if (imageScaleMax < 100) { imageScaleMax = 100; }
    
}

// ............................................................................

private void setImageScaleFit() {

    int vW = getViewport().getWidth();
    int iW = image.getIconWidth();
    
    int vH = getViewport().getHeight();
    int iH = image.getIconHeight();
    
    int fitW = (int)(100d * vW / iW);
    int fitH = (int)(100d * vH / iH);
    
    imageScaleInternalFit = (fitW < fitH) ? fitW : fitH;
    imageScaleExternalFit = (fitW > fitH) ? fitW : fitH;

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
    { if (gridSize > 99) { gridSize = 99; }
      if (gridSize <  3) { gridSize = 3;  }
      int oldValue = this.gridSize;
      this.gridSize = gridSize;
      fireEvent("gridSize", oldValue, gridSize);
      getPropertyChangeSupport().firePropertyChange("gridSize",
                                                    oldValue, gridSize);
      repaint(); }

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
        
        setImageScaleFit();
        if (scrollBarVisible == isScrollBarVisible()) { return; }

        scrollBarVisible = isScrollBarVisible();
        CURSOR_DEFAULT = scrollBarVisible ? CURSOR_MOVE : null;
        labelImage.setCursor(CURSOR_DEFAULT);
        
    }
};

///////////////////////////////////////////////////////////////////////////////

private boolean isScrollBarVisible()
    { return getVerticalScrollBar().isVisible() ||
             getHorizontalScrollBar().isVisible(); }

///////////////////////////////////////////////////////////////////////////////

private JLabel labelImage;
private JPanel panelRoot;

// Кінець класу JImageView ////////////////////////////////////////////////////

}
