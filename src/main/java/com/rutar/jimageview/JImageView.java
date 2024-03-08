package com.rutar.jimageview;

import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.swing.*;
import java.awt.event.*;

// ............................................................................

/**
 * Клас JImageView
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageView extends JScrollPane {

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

///////////////////////////////////////////////////////////////////////////////

public JImageView() { initComponents(); }

///////////////////////////////////////////////////////////////////////////////

@SuppressWarnings("unchecked")                       
private void initComponents() {

panelRoot = new RootPane();
labelImage = new JLabel();

labelImage.setIcon(new ImageIcon(getClass().getResource("/com/rutar/jimageview/images/Uiconstock-E-Commerce-E-Commerce-Icon-Set.48.png")));
labelImage.setIcon(new ImageIcon(getClass().getResource("/com/rutar/jimageview/images/Untitled.png")));
labelImage.addMouseListener(imageMouseListener);
labelImage.addMouseMotionListener(imageMouseMotionListener);

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

getViewport().addChangeListener(e -> {});
setViewportView(panelRoot);

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
    labelImage.setCursor(CURSOR_HAND);
}

@Override
public void mouseReleased (MouseEvent me) {
    labelImage.setCursor(isScrollBarVisible() ? CURSOR_MOVE : null);
}

};

///////////////////////////////////////////////////////////////////////////////

private boolean cursorOnImage;
private JViewport imageViewport;

// ............................................................................

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

private boolean isScrollBarVisible()
    { return getVerticalScrollBar().isVisible() ||
             getHorizontalScrollBar().isVisible(); }

///////////////////////////////////////////////////////////////////////////////

private JLabel labelImage;
private JPanel panelRoot;

// Кінець класу JImageView ////////////////////////////////////////////////////

}
