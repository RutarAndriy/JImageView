package com.rutar.jimageview;

import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

// ............................................................................

/**
 * Клас JImageViewDemo
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageViewDemo extends JFrame {

///////////////////////////////////////////////////////////////////////////////

public JImageViewDemo() {

initComponents();
initAppIcons();

face.addJImageViewListener(JImageViewListener);

resetSettings();
setLocationRelativeTo(null);

}

///////////////////////////////////////////////////////////////////////////////
                       
private void initComponents() {

face = new JImageView();
label_line_width = new JLabel();
slider_line_width = new JSlider();
label_smile_width = new JLabel();
slider_smile_width = new JSlider();
btn_smile_type = new JButton();
btn_line_color = new JButton();
btn_background = new JButton();
btn_reset = new JButton();

FormListener formListener = new FormListener();

setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
setTitle("JImageView Demo");
setResizable(false);

//face.setLineWidth(5);

GroupLayout faceLayout = new GroupLayout(face);
face.setLayout(faceLayout);
faceLayout.setHorizontalGroup(faceLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    .addGap(0, 208, Short.MAX_VALUE)
);
faceLayout.setVerticalGroup(faceLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    .addGap(0, 0, Short.MAX_VALUE)
);

label_line_width.setHorizontalAlignment(SwingConstants.CENTER);
label_line_width.setText("Товщина ліній");

slider_line_width.setMaximum(18);
slider_line_width.setMinimum(1);
slider_line_width.setToolTipText("Товщина ліній: 5");
slider_line_width.setValue(5);
slider_line_width.setRequestFocusEnabled(false);
slider_line_width.addChangeListener(formListener);

label_smile_width.setHorizontalAlignment(SwingConstants.CENTER);
label_smile_width.setText("Ширина усмішки");

slider_smile_width.setMaximum(175);
slider_smile_width.setToolTipText("Ширина усмішки: 120°");
slider_smile_width.setValue(120);
slider_smile_width.addChangeListener(formListener);

btn_smile_type.setText("Усмішка / Гримаса");
btn_smile_type.setActionCommand("smile/unsmile");
btn_smile_type.addActionListener(formListener);

btn_line_color.setText("Змінити колір ліній");
btn_line_color.setActionCommand("changeForeground");
btn_line_color.addActionListener(formListener);

btn_background.setText("Змінити колір фону");
btn_background.setActionCommand("changeBackground");
btn_background.addActionListener(formListener);

btn_reset.setText("Очистити налаштування");
btn_reset.setActionCommand("resetSettings");
btn_reset.addActionListener(formListener);

GroupLayout layout = new GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(face, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(label_line_width, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(slider_line_width, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
            .addComponent(label_smile_width, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(slider_smile_width, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_smile_type, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_line_color, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_reset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
);
layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(face, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_line_width)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slider_line_width, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_smile_width)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slider_smile_width, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_smile_type)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_line_color)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_background)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_reset)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
);

pack();

}

private class FormListener implements ActionListener, ChangeListener {

FormListener() {}

@Override
public void actionPerformed (ActionEvent evt) {
    if (evt.getSource() == btn_smile_type) {
        JImageViewDemo.this.jButtonActionPerformed(evt);
    }
    else if (evt.getSource() == btn_line_color) {
        JImageViewDemo.this.jButtonActionPerformed(evt);
    }
    else if (evt.getSource() == btn_background) {
        JImageViewDemo.this.jButtonActionPerformed(evt);
    }
    else if (evt.getSource() == btn_reset) {
        JImageViewDemo.this.jButtonActionPerformed(evt);
    }
}

@Override
public void stateChanged (ChangeEvent evt) {
    if (evt.getSource() == slider_line_width) {
        JImageViewDemo.this.jSliderStateCanged(evt);
    }
    else if (evt.getSource() == slider_smile_width) {
        JImageViewDemo.this.jSliderStateCanged(evt);
    }
}
}              

///////////////////////////////////////////////////////////////////////////////

private void jSliderStateCanged (ChangeEvent evt) {                                    

if (evt.getSource() == slider_line_width)
    { //face.setLineWidth(slider_line_width.getValue());
      slider_line_width.setToolTipText("Товщина ліній: " +
      slider_line_width.getValue()); }
else
    { //face.setMouthWidth(slider_smile_width.getValue());
      slider_smile_width.setToolTipText("Ширина усмішки: " +
      slider_smile_width.getValue() + "°"); }

}                                   

///////////////////////////////////////////////////////////////////////////////

private void jButtonActionPerformed (ActionEvent evt) {                                        

switch (evt.getActionCommand()) {

    // Усмішка/гримаса
    //case "smile/unsmile"    -> face.setSmile(!face.isSmile());
    // Колір ліній
    case "changeForeground" -> face.setForeground(getRandomColor());
    // Колір фону
    case "changeBackground" -> face.setBackground(getRandomColor());
    // Відновити початкові налаштування
    case "resetSettings"    -> resetSettings();

}
}                                       

///////////////////////////////////////////////////////////////////////////////

public static void main (String args[]) {
    
    EventQueue.invokeLater(() -> {
        new JImageViewDemo().setVisible(true);
    });
}

///////////////////////////////////////////////////////////////////////////////

private void initAppIcons() {

JImageViewBeanInfo beanInfo = new JImageViewBeanInfo();
ArrayList<Image> icons = new ArrayList<>();

icons.add(beanInfo.getIcon(BeanInfo.ICON_COLOR_16x16));
icons.add(beanInfo.getIcon(BeanInfo.ICON_COLOR_32x32));

setIconImages(icons);

}

///////////////////////////////////////////////////////////////////////////////
                   
private JImageView face;
private JLabel label_line_width;
private JLabel label_smile_width;
private JSlider slider_line_width;
private JSlider slider_smile_width;                

private JButton btn_background;
private JButton btn_line_color;
private JButton btn_reset;
private JButton btn_smile_type;

private Color defaultBackground = null;
private Color defaultForeground = null;

private final boolean smile = true;
private final int lineWidth = 5;
private final int mothWidth = 120;
    
///////////////////////////////////////////////////////////////////////////////

private void resetSettings() {
    
    if (defaultBackground == null) 
        { defaultBackground = face.getBackground(); }
    if (defaultForeground == null) 
        { defaultForeground = face.getForeground(); }
    
    //face.setSmile(smile);
    
    //face.setLineWidth(lineWidth);
    slider_line_width.setValue(lineWidth);
    
    //face.setMouthWidth(mothWidth);
    slider_smile_width.setValue(mothWidth);
    
    face.setBackground(defaultBackground);
    face.setForeground(defaultForeground);
    
}

///////////////////////////////////////////////////////////////////////////////

private Color getRandomColor() {
    return new Color( (int)(Math.random()*255),
                      (int)(Math.random()*255),
                      (int)(Math.random()*255) );
}
    
///////////////////////////////////////////////////////////////////////////////

private void printComponentChange (String eventText, JImageViewEvent evt) {
    System.out.println(eventText + " was changed" +
                                   " from " + evt.getOldValue() +
                                   " to "   + evt.getNewValue());
}

///////////////////////////////////////////////////////////////////////////////

JImageViewListener JImageViewListener = new JImageViewListener() {
    
    @Override
    public void smileChange (JImageViewEvent evt)
        { printComponentChange("Smile", evt); }

    @Override
    public void lineWidthChange (JImageViewEvent evt)
        { printComponentChange("Line width", evt); }

    @Override
    public void mouthWidthChange (JImageViewEvent evt)
        { printComponentChange("Mouth width", evt); }

    @Override
    public void backgroundChange (JImageViewEvent evt)
        { printComponentChange("Background", evt); }

    @Override
    public void foregroundChange (JImageViewEvent evt)
        { printComponentChange("Foreground", evt); }
};

// Кінець класу JImageViewDemo ////////////////////////////////////////////////

}
