package com.rutar.jimageview;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import static javax.swing.GroupLayout.*;
import static javax.swing.ScrollPaneConstants.*;

// ............................................................................

/**
 * Клас JImageViewDemo
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageViewDemo extends JFrame {

public JImageViewDemo() { initComponents(); }

///////////////////////////////////////////////////////////////////////////////

@SuppressWarnings("unchecked")
private void initComponents() {
        
imageView = new JImageView();
panel_components = new JPanel();
panel_1 = new JPanel();
panel_2 = new JPanel();
panel_3 = new JPanel();
        
btn_open = new JButton();
btn_zoom_in = new JButton();
btn_zoom_out = new JButton();
btn_center = new JButton();
btn_internal_fit = new JButton();
btn_external_fit = new JButton();
btn_original = new JButton();

field_scale = new JTextField();
btn_set_scale = new JButton();

cb_grid = new JCheckBox();
cb_show_scrollbar = new JCheckBox();
cb_lmb = new JCheckBox();
cb_cmb = new JCheckBox();
cb_rmb = new JCheckBox();
cb_wheel = new JCheckBox();

rb_fast = new JRadioButton();
rb_smooth = new JRadioButton();

sep_1 = new JSeparator();
sep_2 = new JSeparator();
sep_3 = new JSeparator();
sep_4 = new JSeparator();

bg_zoom = new ButtonGroup();

// ............................................................................

setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
setTitle("JImageView Demo");

btn_open.setText("Вибрати зображення");
btn_open.setActionCommand("btn_open");
btn_open.addActionListener(this::buttonPressed);

panel_1.setLayout(new GridLayout(1, 0, 5, 0));

btn_zoom_out.setText("Зменшити");
btn_zoom_out.setActionCommand("btn_zoom_out");
btn_zoom_out.addActionListener(this::buttonPressed);
panel_1.add(btn_zoom_out);

btn_zoom_in.setText("Збільшити");
btn_zoom_in.setActionCommand("btn_zoom_in");
btn_zoom_in.addActionListener(this::buttonPressed);
panel_1.add(btn_zoom_in);

btn_center.setText("Центрувати зображення");
btn_center.setActionCommand("btn_center");
btn_center.addActionListener(this::buttonPressed);

panel_2.setLayout(new GridLayout(1, 0, 5, 0));

btn_internal_fit.setText("Вписати");
btn_internal_fit.setActionCommand("btn_internal_fit");
btn_internal_fit.addActionListener(this::buttonPressed);
panel_2.add(btn_internal_fit);

btn_external_fit.setText("За розміром вікна");
btn_external_fit.setActionCommand("btn_external_fit");
btn_external_fit.addActionListener(this::buttonPressed);
panel_2.add(btn_external_fit);

btn_original.setText("Оригінальний розмір");
btn_original.setActionCommand("btn_original");
btn_original.addActionListener(this::buttonPressed);

panel_3.setLayout(new GridLayout(1, 0, 5, 0));

field_scale.setHorizontalAlignment(JTextField.CENTER);
field_scale.setText("100%");
field_scale.addFocusListener(new FocusAdapter() {
    @Override
    public void focusLost(FocusEvent evt) {
        fieldInputFocusLosted(evt);
    }
});

field_scale.addActionListener(this::fieldInputEntered);
panel_3.add(field_scale);

btn_set_scale.setText("Задати масштаб");
btn_set_scale.setActionCommand("btn_set_scale");
btn_set_scale.addActionListener(this::buttonPressed);
panel_3.add(btn_set_scale);

cb_grid.setSelected(true);
cb_grid.setText("Відображати сітку");
cb_grid.setActionCommand("cb_grid");
cb_grid.setMargin(new Insets(2, 0, 2, 5));
cb_grid.addActionListener(this::checkBoxPressed);

cb_show_scrollbar.setText("Постійно відображати смуги прокрутки");
cb_show_scrollbar.setActionCommand("cb_show_scrollbar");
cb_show_scrollbar.setMargin(new Insets(2, 0, 2, 5));
cb_show_scrollbar.addActionListener(this::checkBoxPressed);

cb_lmb.setSelected(true);
cb_lmb.setText("Перетягувати зображення лівою клавішею миші");
cb_lmb.setActionCommand("cb_lmb");
cb_lmb.setMargin(new Insets(2, 0, 2, 5));
cb_lmb.addActionListener(this::checkBoxPressed);

cb_cmb.setSelected(true);
cb_cmb.setText("Змінювати вигляд середньою клавішею миші");
cb_cmb.setToolTipText("");
cb_cmb.setActionCommand("cb_cmb");
cb_cmb.setMargin(new Insets(2, 0, 2, 5));
cb_cmb.addActionListener(this::checkBoxPressed);

cb_rmb.setSelected(true);
cb_rmb.setText("Використовувати збільшення правою клавішею миші");
cb_rmb.setActionCommand("cb_rmb");
cb_rmb.setMargin(new Insets(2, 0, 2, 5));
cb_rmb.addActionListener(this::checkBoxPressed);

cb_wheel.setSelected(true);
cb_wheel.setText("Масштабувати зображення колесиком миші");
cb_wheel.setActionCommand("cb_wheel");
cb_wheel.setMargin(new Insets(2, 0, 2, 5));
cb_wheel.addActionListener(this::checkBoxPressed);

bg_zoom.add(rb_fast);
rb_fast.setSelected(true);
rb_fast.setText("Швидке масштабування");
rb_fast.setActionCommand("rb_fast");
rb_fast.setMargin(new Insets(2, 0, 2, 5));
rb_fast.addActionListener(this::radioButtonPressed);

bg_zoom.add(rb_smooth);
rb_smooth.setText("Згладжене масштабування");
rb_smooth.setActionCommand("rb_smooth");
rb_smooth.setMargin(new Insets(2, 0, 2, 5));
rb_smooth.addActionListener(this::radioButtonPressed);

GroupLayout panel_componentsLayout = new GroupLayout(panel_components);
panel_components.setLayout(panel_componentsLayout);
panel_componentsLayout.setHorizontalGroup(panel_componentsLayout
    .createParallelGroup(Alignment.LEADING)
    .addComponent(sep_1)
    .addComponent(btn_open, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(panel_1, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(btn_center, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(panel_2, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(btn_original, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(sep_2)
    .addComponent(panel_3, Alignment.TRAILING, DEFAULT_SIZE,
                                               DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(sep_3)
    .addComponent(cb_grid, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_show_scrollbar, DEFAULT_SIZE,
                                     DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_cmb, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_lmb, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_rmb, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_wheel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(sep_4)
    .addComponent(rb_fast, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(rb_smooth, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
);

panel_componentsLayout.setVerticalGroup(panel_componentsLayout
    .createParallelGroup(Alignment.LEADING)
    .addGroup(panel_componentsLayout.createSequentialGroup()
        .addGap(0, 0, 0)
        .addComponent(btn_open)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(sep_1, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panel_1, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btn_center)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panel_2, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btn_original)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(sep_2, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panel_3, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(sep_3, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_grid)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_show_scrollbar)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_lmb)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_cmb)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_rmb)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_wheel)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(sep_4, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(rb_fast)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(rb_smooth)
        .addGap(0, 0, 0))
);

GroupLayout layout = new GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addGap(5, 5, 5)
        .addComponent(imageView, DEFAULT_SIZE, 396, Short.MAX_VALUE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panel_components, PREFERRED_SIZE,
                                        DEFAULT_SIZE, PREFERRED_SIZE)
        .addGap(5, 5, 5))
);
layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addGap(5, 5, 5)
        .addGroup(layout.createParallelGroup(Alignment.LEADING)
            .addComponent(panel_components, PREFERRED_SIZE,
                                            DEFAULT_SIZE, PREFERRED_SIZE)
            .addComponent(imageView, PREFERRED_SIZE, 0, Short.MAX_VALUE))
        .addGap(5, 5, 5))
);

pack();
setMinimumSize(getSize());

setSize(new Dimension(1000, 600));
setLocationRelativeTo(null);

}

///////////////////////////////////////////////////////////////////////////////

private void buttonPressed (ActionEvent ae) {
    
    switch (ae.getActionCommand()) {
        
        case "btn_zoom_in"      -> imageView.zoomIn();
        case "btn_zoom_out"     -> imageView.zoomOut();
        case "btn_original"     -> imageView.zoomOriginal();
        case "btn_internal_fit" -> imageView.fitInternal();
        case "btn_external_fit" -> imageView.fitExternal();
        
        // ....................................................................
        
        case "btn_set_scale" -> {
            String input = field_scale.getText();
            imageView.setImageScale(Integer.parseInt(input.split("%")[0]));
        }
        
        // ....................................................................
        
        case "btn_open" -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File("/home/rutar/test_1.jpg"));
            chooser.showOpenDialog(this);
            File file = chooser.getSelectedFile();
            imageView.setImage(new ImageIcon(file.getAbsolutePath()));
        }
        
    }
    
    field_scale.setText(imageView.getImageScale() + "%");

}

///////////////////////////////////////////////////////////////////////////////

private void checkBoxPressed (ActionEvent ae) {

    switch (ae.getActionCommand()) {
        
        case "cb_show_scrollbar" -> {
            
            imageView.setVerticalScrollBarPolicy(cb_show_scrollbar.isSelected()
                ? VERTICAL_SCROLLBAR_ALWAYS : VERTICAL_SCROLLBAR_AS_NEEDED);
            imageView.setHorizontalScrollBarPolicy(cb_show_scrollbar.isSelected()
                ? HORIZONTAL_SCROLLBAR_ALWAYS : HORIZONTAL_SCROLLBAR_AS_NEEDED);
            
        }
        
    }
    
}

///////////////////////////////////////////////////////////////////////////////

private void radioButtonPressed (ActionEvent ae) {

    switch (ae.getActionCommand()) {
        
        case "rb_fast"   -> { }
        case "rb_smooth" -> { }
        
    }
}

///////////////////////////////////////////////////////////////////////////////

private void fieldInputEntered     (ActionEvent ae) { fixInput(); }
private void fieldInputFocusLosted (FocusEvent  fe) { fixInput(); }

///////////////////////////////////////////////////////////////////////////////

private void fixInput() {
    
    String input = field_scale.getText();
    input = input.replaceAll("%", "");
    
    try { int num = Integer.parseInt(input);
          input = num + "%"; }
    catch (NumberFormatException e) { input = "100%"; }
    
    field_scale.setText(input);
    
}

///////////////////////////////////////////////////////////////////////////////

public static void main (String args[]) {

    EventQueue.invokeLater(() -> {
        new JImageViewDemo().setVisible(true);
    });
}

///////////////////////////////////////////////////////////////////////////////

private JImageView imageView;

private JPanel panel_components;
private JPanel panel_1;
private JPanel panel_2;
private JPanel panel_3;

private JButton btn_open;

private JButton btn_zoom_in;
private JButton btn_zoom_out;
private JButton btn_center;
private JButton btn_internal_fit;
private JButton btn_external_fit;
private JButton btn_original;

private JTextField field_scale;
private JButton btn_set_scale;

private JCheckBox cb_grid;
private JCheckBox cb_show_scrollbar;
private JCheckBox cb_lmb;
private JCheckBox cb_cmb;
private JCheckBox cb_rmb;
private JCheckBox cb_wheel;

private JRadioButton rb_fast;
private JRadioButton rb_smooth;

private JSeparator sep_1;
private JSeparator sep_2;
private JSeparator sep_3;
private JSeparator sep_4;

private ButtonGroup bg_zoom;

// Кінець класу JImageViewDemo ////////////////////////////////////////////////

}
