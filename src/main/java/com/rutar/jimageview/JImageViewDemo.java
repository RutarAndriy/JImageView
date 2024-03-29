package com.rutar.jimageview;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import com.rutar.jimageview.JImageView.*;

import static java.awt.Color.*;
import static javax.swing.GroupLayout.*;

// ............................................................................

/**
 * Клас JImageViewDemo
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageViewDemo extends JFrame {

private int grid_color_id;
private final Color[][] grid_colors = {
    { null, WHITE,       RED,    YELLOW,     GREEN,      BLUE },
    { null, BLACK, DARK_GRAY, DARK_GRAY, DARK_GRAY, DARK_GRAY }
};

private int grid_size_id = 4;
private final int[] grid_sizes =
    { 7, 9, 15, 20, 25, 30, 50, 70, 99 };

///////////////////////////////////////////////////////////////////////////////

public JImageViewDemo() { initComponents();
                          initAppIcons(); }

///////////////////////////////////////////////////////////////////////////////

@SuppressWarnings("unchecked")
private void initComponents() {
        
imageView = new JImageView();
panel_components = new JPanel();
panel_1 = new JPanel();
panel_2 = new JPanel();
panel_3 = new JPanel();
panel_4 = new JPanel();
panel_5 = new JPanel();
panel_6 = new JPanel();

btn_open = new JButton();
btn_zoom_in = new JButton();
btn_zoom_out = new JButton();
btn_minimize = new JButton();
btn_maximize = new JButton();
btn_internal_fit = new JButton();
btn_external_fit = new JButton();
btn_center = new JButton();
btn_region_fit = new JToggleButton();
btn_original = new JButton();
btn_rnd_grid_color = new JButton();
btn_rnd_grid_size = new JButton();

field_scale = new JTextField();
btn_set_scale = new JButton();

cb_grid = new JCheckBox();
cb_drag_out = new JCheckBox();
cb_lmb = new JCheckBox();
cb_cmb = new JCheckBox();
cb_rmb = new JCheckBox();
cb_wheel = new JCheckBox();
cb_invert = new JCheckBox();

rb_zoom_fast = new JRadioButton();
rb_zoom_smooth = new JRadioButton();

rb_sb_always = new JRadioButton();
rb_sb_as_needed = new JRadioButton();
rb_sb_never = new JRadioButton();

sep_1 = new JSeparator();
sep_2 = new JSeparator();
sep_3 = new JSeparator();
sep_4 = new JSeparator();
sep_5 = new JSeparator();
sep_6 = new JSeparator();

bg_zoom = new ButtonGroup();
bg_scrollbars = new ButtonGroup();

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

panel_2.setLayout(new GridLayout(1, 0, 5, 0));

btn_minimize.setText("Мінімізувати");
btn_minimize.setActionCommand("btn_minimize");
btn_minimize.addActionListener(this::buttonPressed);
panel_2.add(btn_minimize);

btn_maximize.setText("Максимізувати");
btn_maximize.setActionCommand("btn_maximize");
btn_maximize.addActionListener(this::buttonPressed);
panel_2.add(btn_maximize);

panel_3.setLayout(new GridLayout(1, 0, 5, 0));

btn_internal_fit.setText("Вписати");
btn_internal_fit.setActionCommand("btn_internal_fit");
btn_internal_fit.addActionListener(this::buttonPressed);
panel_3.add(btn_internal_fit);

btn_external_fit.setText("За розміром вікна");
btn_external_fit.setActionCommand("btn_external_fit");
btn_external_fit.addActionListener(this::buttonPressed);
panel_3.add(btn_external_fit);

panel_4.setLayout(new GridLayout(1, 0, 5, 0));

btn_center.setText("Центрувати");
btn_center.setActionCommand("btn_center");
btn_center.addActionListener(this::buttonPressed);
panel_4.add(btn_center);

btn_region_fit.setText("Масштабувати регіон");
btn_region_fit.setActionCommand("btn_region_fit");
btn_region_fit.addActionListener(this::buttonPressed);
panel_4.add(btn_region_fit);

btn_original.setText("Оригінальний розмір зображення");
btn_original.setActionCommand("btn_original");
btn_original.addActionListener(this::buttonPressed);

panel_5.setLayout(new GridLayout(1, 0, 5, 0));

field_scale.setHorizontalAlignment(JTextField.CENTER);
field_scale.setText("100%");
field_scale.addFocusListener(new FocusAdapter() {
    @Override
    public void focusLost (FocusEvent evt) {
        fieldInputFocusLosted();
    }
});

field_scale.addActionListener(ae -> this.fieldInputEntered());
panel_5.add(field_scale);

btn_set_scale.setText("Задати масштаб");
btn_set_scale.setActionCommand("btn_set_scale");
btn_set_scale.addActionListener(this::buttonPressed);
panel_5.add(btn_set_scale);

cb_grid.setSelected(true);
cb_grid.setText("Відображати сітку");
cb_grid.setActionCommand("cb_grid");
cb_grid.setMargin(new Insets(2, 0, 2, 5));
cb_grid.addActionListener(this::checkBoxPressed);

cb_drag_out.setSelected(true);
cb_drag_out.setText("Перетягування за межею видимості");
cb_drag_out.setActionCommand("cb_drag_out");
cb_drag_out.setMargin(new Insets(2, 0, 2, 5));
cb_drag_out.addActionListener(this::checkBoxPressed);

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

cb_invert.setText("Інвертувати колесико миші");
cb_invert.setActionCommand("cb_invert");
cb_invert.setMargin(new Insets(2, 0, 2, 5));
cb_invert.addActionListener(this::checkBoxPressed);

bg_zoom.add(rb_zoom_fast);
rb_zoom_fast.setSelected(true);
rb_zoom_fast.setText("Швидке масштабування");
rb_zoom_fast.setActionCommand("rb_zoom_fast");
rb_zoom_fast.setMargin(new Insets(2, 0, 2, 5));
rb_zoom_fast.addActionListener(this::radioButtonPressed);

bg_zoom.add(rb_zoom_smooth);
rb_zoom_smooth.setText("Згладжене масштабування");
rb_zoom_smooth.setActionCommand("rb_zoom_smooth");
rb_zoom_smooth.setMargin(new Insets(2, 0, 2, 5));
rb_zoom_smooth.addActionListener(this::radioButtonPressed);

bg_scrollbars.add(rb_sb_always);
rb_sb_always.setText("Постійно відображати смуги прокрутки");
rb_sb_always.setActionCommand("rb_sb_always");
rb_sb_always.setMargin(new Insets(2, 0, 2, 5));
rb_sb_always.addActionListener(this::radioButtonPressed);

bg_scrollbars.add(rb_sb_as_needed);
rb_sb_as_needed.setSelected(true);
rb_sb_as_needed.setText("Відображати смуги прокрутки за потреби");
rb_sb_as_needed.setActionCommand("rb_sb_as_needed");
rb_sb_as_needed.setMargin(new Insets(2, 0, 2, 5));
rb_sb_as_needed.addActionListener(this::radioButtonPressed);

bg_scrollbars.add(rb_sb_never);
rb_sb_never.setText("Ніколи не відображати смуги прокрутки");
rb_sb_never.setActionCommand("rb_sb_never");
rb_sb_never.setMargin(new Insets(2, 0, 2, 5));
rb_sb_never.addActionListener(this::radioButtonPressed);

panel_6.setLayout(new GridLayout(1, 0, 5, 0));

btn_rnd_grid_color.setText("Змінити колір сітки");
btn_rnd_grid_color.setActionCommand("btn_rnd_grid_color");
btn_rnd_grid_color.addActionListener(this::buttonPressed);
panel_6.add(btn_rnd_grid_color);

btn_rnd_grid_size.setText("Змінити розмір сітки");
btn_rnd_grid_size.setActionCommand("btn_rnd_grid_size");
btn_rnd_grid_size.addActionListener(this::buttonPressed);
panel_6.add(btn_rnd_grid_size);

GroupLayout panel_componentsLayout = new GroupLayout(panel_components);
panel_components.setLayout(panel_componentsLayout);
panel_componentsLayout.setHorizontalGroup(panel_componentsLayout
    .createParallelGroup(Alignment.LEADING)
    .addComponent(sep_1)
    .addComponent(btn_open, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(panel_1, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(panel_2, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(panel_3, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(panel_4, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(btn_original, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(sep_2)
    .addComponent(panel_5, Alignment.TRAILING, DEFAULT_SIZE,
                                               DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(sep_3)
    .addComponent(cb_grid, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_drag_out, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_cmb, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_lmb, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_rmb, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_wheel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(cb_invert, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(sep_4)
    .addComponent(rb_zoom_fast, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(rb_zoom_smooth, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(sep_5)
    .addComponent(rb_sb_always, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(rb_sb_as_needed, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(rb_sb_never, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
    .addComponent(sep_6)
    .addComponent(panel_6, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
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
        .addComponent(panel_2, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panel_3, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panel_4, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(btn_original)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(sep_2, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panel_5, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(sep_3, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_grid)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_drag_out)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_lmb)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_cmb)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_rmb)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_wheel)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cb_invert)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(sep_4, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(rb_zoom_fast)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(rb_zoom_smooth)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(sep_5, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(rb_sb_always)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(rb_sb_as_needed)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(rb_sb_never)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(sep_6, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(panel_6, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
        .addGap(2, 2, 2))
);

GroupLayout layout = new GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addGap(5, 5, 5)
        .addComponent(imageView, DEFAULT_SIZE, 500, Short.MAX_VALUE)
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

imageView.addPropertyChangeListener((PropertyChangeEvent evt) -> {
    
    switch (evt.getPropertyName()) {

        case "imageScale" -> field_scale.setText(evt.getNewValue() + "%");

    }
});

pack();
setMinimumSize(getSize());

setSize(new Dimension(1080, 600));
setLocationRelativeTo(null);

}

///////////////////////////////////////////////////////////////////////////////

private void initAppIcons() {

ArrayList<Image> icons = new ArrayList<>();
JImageViewBeanInfo bean = new JImageViewBeanInfo();

icons.add(bean.getIcon(BeanInfo.ICON_COLOR_16x16));
icons.add(bean.getIcon(BeanInfo.ICON_COLOR_32x32));

setIconImages(icons);

}

///////////////////////////////////////////////////////////////////////////////

private void buttonPressed (ActionEvent ae) {
    
    switch (ae.getActionCommand()) {
        
        case "btn_zoom_in"      -> imageView.zoomIn(null);
        case "btn_zoom_out"     -> imageView.zoomOut(null);
        case "btn_minimize"     -> imageView.minimize();
        case "btn_maximize"     -> imageView.maximize();
        case "btn_internal_fit" -> imageView.fitInternal();
        case "btn_external_fit" -> imageView.fitExternal();
        case "btn_center"       -> imageView.center();
        case "btn_region_fit"   -> imageView.setRegion();
        case "btn_original"     -> imageView.zoomToOriginal();
        
        // ....................................................................
        
        case "btn_set_scale" -> {
            String input = field_scale.getText();
            imageView.setImageScale(Integer.parseInt(input.split("%")[0]));
        }
        
        // ....................................................................
        
        case "btn_open" -> {
            JFileChooser chooser = new JFileChooser();
            chooser.getActionMap().get("viewTypeDetails")
                                  .actionPerformed(null);
            chooser.setAccessory(new ImagePreviewComponent(chooser));
            chooser.setFileFilter(new FileNameExtensionFilter("Image files",
                                      ImageIO.getReaderFileSuffixes()));
            chooser.setSelectedFile(new File("/home/rutar/test_1.jpg"));
            chooser.showOpenDialog(this);
            File file = chooser.getSelectedFile();
            imageView.setImage(JImageViewUtils.getImageQuickly(file));
        }
        
        // ....................................................................
        
        case "btn_rnd_grid_color" -> {
            
            grid_color_id += (grid_color_id < grid_colors[0].length)
                           ? 1 : -grid_colors[0].length;

            if (grid_color_id == grid_colors[0].length)
                { imageView.setGridLightColor(getRandomColor());
                  imageView.setGridDarkColor(getRandomColor()); }
            else
                { imageView.setGridLightColor(grid_colors[0][grid_color_id]);
                  imageView.setGridDarkColor (grid_colors[1][grid_color_id]); }
        }
        
        // ....................................................................
        
        case "btn_rnd_grid_size" -> {
            
            grid_size_id += (grid_size_id < grid_sizes.length - 1)
                           ? 1 : -grid_sizes.length + 1;

            imageView.setGridSize(grid_sizes[grid_size_id]);
        }   
    }
}

///////////////////////////////////////////////////////////////////////////////

private void checkBoxPressed (ActionEvent ae) {

    switch (ae.getActionCommand()) {
        
        case "cb_grid" ->
            { imageView.setGridVisible(cb_grid.isSelected()); }
        case "cb_drag_out" ->
            { imageView.setDrugImageOut(cb_drag_out.isSelected()); }
        case "cb_lmb" ->
            { imageView.setLMBEnable(cb_lmb.isSelected()); }
        case "cb_cmb" ->
            { imageView.setCMBEnable(cb_cmb.isSelected()); }
        case "cb_rmb" ->
            { imageView.setRMBEnable(cb_rmb.isSelected()); }
        case "cb_wheel" ->
            { imageView.setWheelEnable(cb_wheel.isSelected()); }
        case "cb_invert" ->
            { imageView.setWheelInvert(cb_invert.isSelected()); }
        
    }
    
}

///////////////////////////////////////////////////////////////////////////////

private void radioButtonPressed (ActionEvent ae) {
    
    switch (ae.getActionCommand()) {
        
        case "rb_zoom_fast"    ->
            { imageView.setImageScaleType(ScaleType.FAST); }
        case "rb_zoom_smooth"  ->
            { imageView.setImageScaleType(ScaleType.SMOOTH); }
        
        case "rb_sb_always"    ->
            { imageView.setHorizontalScrollBarPolicy(32);
              imageView  .setVerticalScrollBarPolicy(22); }
        case "rb_sb_as_needed" ->
            { imageView.setHorizontalScrollBarPolicy(30);
              imageView  .setVerticalScrollBarPolicy(20); }
        case "rb_sb_never"     ->
            { imageView.setHorizontalScrollBarPolicy(31);
              imageView  .setVerticalScrollBarPolicy(21); }
        
    }
}

///////////////////////////////////////////////////////////////////////////////

private void fieldInputEntered() {
    
    fixInput();
    ActionEvent event = new ActionEvent(btn_set_scale, -1, "btn_set_scale");

    for (ActionListener listener : btn_set_scale.getActionListeners()) {
        listener.actionPerformed(event);
    }
}

// ............................................................................

private void fieldInputFocusLosted() { fixInput(); }

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

private Color getRandomColor() {

return new Color((int)(Math.random() * 255),
                 (int)(Math.random() * 255),
                 (int)(Math.random() * 255));

}

///////////////////////////////////////////////////////////////////////////////

public static void main (String args[]) {

    EventQueue.invokeLater(() -> {
        new JImageViewDemo().setVisible(true);
    });
}

///////////////////////////////////////////////////////////////////////////////

private class ImagePreviewComponent extends JComponent
                                    implements PropertyChangeListener {

private BufferedImage image;
private final int gridSize = 15;

// ............................................................................

public ImagePreviewComponent (JFileChooser chooser)
    { chooser.addPropertyChangeListener(this);
      setPreferredSize(new Dimension(300, 300)); }

// ............................................................................

@Override
protected void paintComponent (Graphics g) {

    g.setColor(Color.LIGHT_GRAY);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.DARK_GRAY);

    for (int col = 0; col < getWidth();  col += gridSize) {
    for (int row = 0; row < getHeight(); row += gridSize*2) {
        g.fillRect(col, row + (col/gridSize%2 == 0 ? gridSize : 0),
                   gridSize, gridSize);
    }
    }
    
    if (image == null) { return; }

    int w = image.getWidth();
    int h = image.getHeight();
    
    float p = getWidth()  * 1f / w;
    float q = getHeight() * 1f / h;
    float z = (p < q) ? p : q;
    
    int x = getWidth()/2  - (int)(w * z / 2);
    int y = getHeight()/2 - (int)(h * z / 2);
    
    g.drawImage(image, x, y, (int)(w * z), (int)(h * z), null);

}

// ............................................................................

@Override
public void propertyChange (PropertyChangeEvent e) {

    switch (e.getPropertyName()) {
        
        case JFileChooser.DIRECTORY_CHANGED_PROPERTY -> {
            image = null;
            repaint();
        }
        
        case JFileChooser.SELECTED_FILE_CHANGED_PROPERTY -> {
                
            File file = (File) e.getNewValue();
            image = JImageViewUtils.getImageQuickly(file);
            repaint();
        }
    }
}
}

///////////////////////////////////////////////////////////////////////////////

private JImageView imageView;

private JPanel panel_components;
private JPanel panel_1;
private JPanel panel_2;
private JPanel panel_3;
private JPanel panel_4;
private JPanel panel_5;
private JPanel panel_6;

private JButton btn_open;

private JButton btn_zoom_in;
private JButton btn_zoom_out;
private JButton btn_minimize;
private JButton btn_maximize;
private JButton btn_internal_fit;
private JButton btn_external_fit;
private JButton btn_center;
private JToggleButton btn_region_fit;
private JButton btn_original;
private JButton btn_rnd_grid_color;
private JButton btn_rnd_grid_size;

private JTextField field_scale;
private JButton btn_set_scale;

private JCheckBox cb_grid;
private JCheckBox cb_drag_out;
private JCheckBox cb_lmb;
private JCheckBox cb_cmb;
private JCheckBox cb_rmb;
private JCheckBox cb_wheel;
private JCheckBox cb_invert;

private JRadioButton rb_zoom_fast;
private JRadioButton rb_zoom_smooth;

private JRadioButton rb_sb_always;
private JRadioButton rb_sb_as_needed;
private JRadioButton rb_sb_never;

private JSeparator sep_1;
private JSeparator sep_2;
private JSeparator sep_3;
private JSeparator sep_4;
private JSeparator sep_5;
private JSeparator sep_6;

private ButtonGroup bg_zoom;
private ButtonGroup bg_scrollbars;

// Кінець класу JImageViewDemo ////////////////////////////////////////////////

}
