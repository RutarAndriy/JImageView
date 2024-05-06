package com.rutar.jimageview;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.filechooser.*;

import static com.rutar.jimageview.JImageView.*;
import static com.rutar.jimageview.JImageViewUtils.*;

// ............................................................................

/**
 * Клас JImageViewDemo
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageViewDemo extends JFrame {

///////////////////////////////////////////////////////////////////////////////

public JImageViewDemo() { initComponents();
                          initAppIcons(); }

///////////////////////////////////////////////////////////////////////////////

@SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        bg_scale_type = new ButtonGroup();
        bg_magnifier_shape = new ButtonGroup();
        bg_scrollbars_visibility = new ButtonGroup();
        bg_open_size = new ButtonGroup();
        image_view = new JImageView();
        btn_open = new JButton();
        sp_open = new JSeparator();
        pnl_zoom = new JPanel();
        btn_zoom_out = new JButton();
        btn_zoom_in = new JButton();
        pnl_fit = new JPanel();
        btn_internal_fit = new JButton();
        btn_external_fit = new JButton();
        pnl_min_max = new JPanel();
        btn_minimize = new JButton();
        btn_maximize = new JButton();
        pnl_center_region = new JPanel();
        btn_center = new JButton();
        btn_region_fit = new JButton();
        btn_original = new JButton();
        sp_original = new JSeparator();
        pnl_scale = new JPanel();
        fld_scale = new JTextField();
        btn_set_scale = new JButton();
        sp_scale = new JSeparator();
        pnl_rotate_mirror = new JPanel();
        btn_rotate_90 = new JButton();
        btn_rotate_270 = new JButton();
        btn_rotate_180 = new JButton();
        btn_flip_h = new JButton();
        btn_flip_v = new JButton();
        sep_rotate_mirror = new JSeparator();
        tab_pnl_settings = new JTabbedPane();
        pnl_global = new JPanel();
        cb_lmb = new JCheckBox();
        cb_cmb = new JCheckBox();
        cb_rmb = new JCheckBox();
        cb_wheel = new JCheckBox();
        cb_zmb = new JCheckBox();
        sp_zmb = new JSeparator();
        rb_zoom_fast = new JRadioButton();
        rb_zoom_smooth = new JRadioButton();
        sep_zoom_smooth = new JSeparator();
        rb_open_original = new JRadioButton();
        rb_open_internal_fit = new JRadioButton();
        rb_open_external_fit = new JRadioButton();
        pnl_grid = new JPanel();
        cb_grid = new JCheckBox();
        sp_grid = new JSeparator();
        pnl_grid_light = new JPanel();
        fld_grid_light = new JTextField();
        btn_grid_light = new JButton();
        pnl_grid_dark = new JPanel();
        fld_grid_dark = new JTextField();
        btn_grid_dark = new JButton();
        pnl_grid_size = new JPanel();
        fld_grid_size = new JTextField();
        btn_grid_size = new JButton();
        pnl_magnifier = new JPanel();
        rb_magn_oval = new JRadioButton();
        rb_magn_rect = new JRadioButton();
        sp_magn_rect = new JSeparator();
        pnl_magn_w = new JPanel();
        fld_magn_w = new JTextField();
        btn_magn_w = new JButton();
        pnl_magn_h = new JPanel();
        fld_magn_h = new JTextField();
        btn_magn_h = new JButton();
        sp_magn_h = new JSeparator();
        cb_magn_scale = new JComboBox<>();
        sp_magn_scale = new JSeparator();
        cb_hide_cursor = new JCheckBox();
        sp_hide_cursor = new JSeparator();
        tab_pnl_zoom_borders = new JTabbedPane();
        pnl_f_zoom_border = new JPanel();
        cb_f_zoom_border = new JCheckBox();
        pnl_f_zoom_border_w = new JPanel();
        fld_f_zoom_border_w = new JTextField();
        btn_f_zoom_border_w = new JButton();
        pnl_f_zoom_border_h = new JPanel();
        fld_f_zoom_border_gap = new JTextField();
        btn_f_zoom_border_gap = new JButton();
        pnl_f_zoom_border_color = new JPanel();
        fld_f_zoom_border_color = new JTextField();
        btn_f_zoom_border_color = new JButton();
        pnl_s_zoom_border = new JPanel();
        cb_s_zoom_border = new JCheckBox();
        pnl_s_zoom_border_w = new JPanel();
        fld_s_zoom_border_w = new JTextField();
        btn_s_zoom_border_w = new JButton();
        pnl_s_zoom_border_h = new JPanel();
        fld_s_zoom_border_gap = new JTextField();
        btn_s_zoom_border_gap = new JButton();
        pnl_s_zoom_border_color = new JPanel();
        fld_s_zoom_border_color = new JTextField();
        btn_s_zoom_border_color = new JButton();
        pnl_zoom_gap = new JPanel();
        cb_flip_zoom = new JCheckBox();
        pnl_zoom_gap_w = new JPanel();
        fld_zoom_gap_w = new JTextField();
        btn_zoom_gap_w = new JButton();
        pnl_zoom_gap_h = new JPanel();
        fld_zoom_gap_h = new JTextField();
        btn_zoom_gap_h = new JButton();
        pnl_region = new JPanel();
        cb_additional_stroke = new JCheckBox();
        sp_additional_stroke = new JSeparator();
        pnl_f_stroke_color = new JPanel();
        fld_f_stroke_color = new JTextField();
        btn_f_stroke_color = new JButton();
        pnl_s_stroke_color = new JPanel();
        fld_s_stroke_color = new JTextField();
        btn_s_stroke_color = new JButton();
        sp_s_stroke_color = new JSeparator();
        pnl_stroke_w = new JPanel();
        fld_stroke_w = new JTextField();
        btn_stroke_w = new JButton();
        btn_random_stroke = new JButton();
        sep_random_stroke = new JSeparator();
        pnl_region_min = new JPanel();
        fld_region_min = new JTextField();
        btn_region_min = new JButton();
        pnl_additionally = new JPanel();
        cb_move_out = new JCheckBox();
        cb_zoom_out = new JCheckBox();
        cb_invert = new JCheckBox();
        sp_invert = new JSeparator();
        rb_sb_always = new JRadioButton();
        rb_sb_as_needed = new JRadioButton();
        rb_sb_never = new JRadioButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JImageView Demo");

        image_view.addPropertyChangeListener(this::imageViewPropertyChange);

        btn_open.setText("Вибрати зображення");
        btn_open.setActionCommand("btn_open");
        btn_open.addActionListener(this::onAction);

        pnl_zoom.setLayout(new GridLayout(1, 0, 5, 0));

        btn_zoom_out.setText("Зменшити");
        btn_zoom_out.setActionCommand("btn_zoom_out");
        btn_zoom_out.addActionListener(this::onAction);
        pnl_zoom.add(btn_zoom_out);

        btn_zoom_in.setText("Збільшити");
        btn_zoom_in.setActionCommand("btn_zoom_in");
        btn_zoom_in.addActionListener(this::onAction);
        pnl_zoom.add(btn_zoom_in);

        pnl_fit.setLayout(new GridLayout(1, 0, 5, 0));

        btn_internal_fit.setText("Вписати");
        btn_internal_fit.setActionCommand("btn_internal_fit");
        btn_internal_fit.addActionListener(this::onAction);
        pnl_fit.add(btn_internal_fit);

        btn_external_fit.setText("Описати");
        btn_external_fit.setActionCommand("btn_external_fit");
        btn_external_fit.addActionListener(this::onAction);
        pnl_fit.add(btn_external_fit);

        pnl_min_max.setLayout(new GridLayout(1, 0, 5, 0));

        btn_minimize.setText("Мінімізувати");
        btn_minimize.setActionCommand("btn_minimize");
        btn_minimize.addActionListener(this::onAction);
        pnl_min_max.add(btn_minimize);

        btn_maximize.setText("Максимізувати");
        btn_maximize.setActionCommand("btn_maximize");
        btn_maximize.addActionListener(this::onAction);
        pnl_min_max.add(btn_maximize);

        pnl_center_region.setLayout(new GridLayout(1, 0, 5, 0));

        btn_center.setText("Центрувати");
        btn_center.setActionCommand("btn_center");
        btn_center.addActionListener(this::onAction);
        pnl_center_region.add(btn_center);

        btn_region_fit.setText("Масштабувати регіон");
        btn_region_fit.setActionCommand("btn_region_fit");
        btn_region_fit.addActionListener(this::onAction);
        pnl_center_region.add(btn_region_fit);

        btn_original.setText("Оригінальний розмір зображення");
        btn_original.setActionCommand("btn_original");
        btn_original.addActionListener(this::onAction);

        pnl_scale.setLayout(new GridLayout(1, 0, 5, 0));

        fld_scale.setHorizontalAlignment(JTextField.CENTER);
        fld_scale.setActionCommand("fld_scale");
        fld_scale.setText(getRoundedValue(image_view.getImageScale()) + "%");
        fld_scale.addActionListener(this::onAction);
        pnl_scale.add(fld_scale);

        btn_set_scale.setText("Задати масштаб");
        btn_set_scale.setActionCommand("btn_set_scale");
        btn_set_scale.addActionListener(this::onAction);
        pnl_scale.add(btn_set_scale);

        pnl_rotate_mirror.setLayout(new GridLayout(1, 0, 5, 0));

        btn_rotate_90.setText("↶");
        btn_rotate_90.setActionCommand("btn_rotate_90");
        btn_rotate_90.setMargin(new Insets(0, 0, 0, 0));
        btn_rotate_90.setToolTipText("Повернути на 90° проти годинникової стрілки");
        btn_rotate_90.addActionListener(this::onAction);
        pnl_rotate_mirror.add(btn_rotate_90);

        btn_rotate_270.setText("↷");
        btn_rotate_270.setActionCommand("btn_rotate_270");
        btn_rotate_270.setMargin(new Insets(0, 0, 0, 0));
        btn_rotate_270.setToolTipText("Повернути на 90° за годинниковою стрілкою");
        btn_rotate_270.addActionListener(this::onAction);
        pnl_rotate_mirror.add(btn_rotate_270);

        btn_rotate_180.setText("↺");
        btn_rotate_180.setActionCommand("btn_rotate_180");
        btn_rotate_180.setMargin(new Insets(0, 0, 0, 0));
        btn_rotate_180.setToolTipText("Повернути на 180°");
        btn_rotate_180.addActionListener(this::onAction);
        pnl_rotate_mirror.add(btn_rotate_180);

        btn_flip_h.setText("⇆");
        btn_flip_h.setActionCommand("btn_flip_h");
        btn_flip_h.setMargin(new Insets(0, 0, 0, 0));
        btn_flip_h.setToolTipText("Відзеркалити горизонтально");
        btn_flip_h.addActionListener(this::onAction);
        pnl_rotate_mirror.add(btn_flip_h);

        btn_flip_v.setText("⇅");
        btn_flip_v.setActionCommand("btn_flip_v");
        btn_flip_v.setMargin(new Insets(0, 0, 0, 0));
        btn_flip_v.setToolTipText("Відзеркалити вертикально");
        btn_flip_v.addActionListener(this::onAction);
        pnl_rotate_mirror.add(btn_flip_v);

        increaseTextSize(6, btn_rotate_90, btn_rotate_270, btn_rotate_180, btn_flip_h, btn_flip_v);

        tab_pnl_settings.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));

        cb_lmb.setText("Переміщувати зображення лівою клавішею миші");
        cb_lmb.setActionCommand("cb_lmb");
        cb_lmb.setSelected(image_view.isLMBEnable());
        cb_lmb.addActionListener(this::onAction);

        cb_cmb.setText("Змінювати вигляд середньою клавішею миші");
        cb_cmb.setActionCommand("cb_cmb");
        cb_cmb.setSelected(image_view.isCMBEnable());
        cb_cmb.addActionListener(this::onAction);

        cb_rmb.setText("Збільшувати зображення правою клавішею миші");
        cb_rmb.setActionCommand("cb_rmb");
        cb_rmb.setSelected(image_view.isRMBEnable());
        cb_rmb.addActionListener(this::onAction);

        cb_wheel.setText("Масштабувати зображення колесиком миші");
        cb_wheel.setActionCommand("cb_wheel");
        cb_wheel.setSelected(image_view.isWheelEnable());
        cb_wheel.addActionListener(this::onAction);

        cb_zmb.setText("Масштабувати регіон (ЛКМ + ПКМ)");
        cb_zmb.setActionCommand("cb_zmb");
        cb_zmb.setSelected(image_view.isZMBEnable());
        cb_zmb.addActionListener(this::onAction);

        bg_scale_type.add(rb_zoom_fast);
        rb_zoom_fast.setText("Швидке масштабування");
        rb_zoom_fast.setActionCommand("rb_zoom_fast");
        rb_zoom_fast.setSelected(image_view.getImageScaleType() == SCALE_TYPE_FAST);
        rb_zoom_fast.addActionListener(this::onAction);

        bg_scale_type.add(rb_zoom_smooth);
        rb_zoom_smooth.setText("Згладжене масштабування");
        rb_zoom_smooth.setActionCommand("rb_zoom_smooth");
        rb_zoom_smooth.setSelected(image_view.getImageScaleType() == SCALE_TYPE_SMOOTH);
        rb_zoom_smooth.addActionListener(this::onAction);

        bg_open_size.add(rb_open_original);
        rb_open_original.setText("Відкривати зображення оригінального розміру");
        rb_open_original.setActionCommand("rb_open_original");
        rb_open_original.setSelected(image_view.getImageOpenSize() == OPEN_SIZE_ORIGINAL);
        rb_open_original.addActionListener(this::onAction);

        bg_open_size.add(rb_open_internal_fit);
        rb_open_internal_fit.setText("Відкривати зображення вписаним");
        rb_open_internal_fit.setActionCommand("rb_open_internal_fit");
        rb_open_internal_fit.setSelected(image_view.getImageOpenSize() == OPEN_SIZE_INTERNAL_FIT);
        rb_open_internal_fit.addActionListener(this::onAction);

        bg_open_size.add(rb_open_external_fit);
        rb_open_external_fit.setText("Відкривати зображення описаним");
        rb_open_external_fit.setActionCommand("rb_open_external_fit");
        rb_open_external_fit.setSelected(image_view.getImageOpenSize() == OPEN_SIZE_EXTERNAL_FIT);
        rb_open_external_fit.addActionListener(this::onAction);

        GroupLayout pnl_globalLayout = new GroupLayout(pnl_global);
        pnl_global.setLayout(pnl_globalLayout);
        pnl_globalLayout.setHorizontalGroup(pnl_globalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_globalLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_globalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_lmb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_cmb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_rmb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_wheel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_zmb)
                    .addComponent(cb_zmb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_zoom_fast, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_zoom_smooth, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sep_zoom_smooth, GroupLayout.Alignment.TRAILING)
                    .addComponent(rb_open_original, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_open_internal_fit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_open_external_fit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_globalLayout.setVerticalGroup(pnl_globalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_globalLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_lmb)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_cmb)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_rmb)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_wheel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_zmb)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_zmb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_zoom_fast)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_zoom_smooth)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep_zoom_smooth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_open_original)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_open_internal_fit)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_open_external_fit)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pnl_settings.addTab("Загальне", pnl_global);

        cb_grid.setText("Відображати сітку");
        cb_grid.setActionCommand("cb_grid");
        cb_grid.setSelected(image_view.isGridVisible());
        cb_grid.addActionListener(this::onAction);

        pnl_grid_light.setLayout(new GridLayout(1, 0, 5, 0));

        fld_grid_light.setHorizontalAlignment(JTextField.CENTER);
        fld_grid_light.setActionCommand("fld_grid_light");
        fld_grid_light.setText(getColorValue(image_view.getGridLightColor()));
        fld_grid_light.addActionListener(this::onAction);
        pnl_grid_light.add(fld_grid_light);

        btn_grid_light.setText("Задати світлий колір");
        btn_grid_light.setActionCommand("btn_grid_light");
        btn_grid_light.addActionListener(this::onAction);
        pnl_grid_light.add(btn_grid_light);

        pnl_grid_dark.setLayout(new GridLayout(1, 0, 5, 0));

        fld_grid_dark.setHorizontalAlignment(JTextField.CENTER);
        fld_grid_dark.setActionCommand("fld_grid_dark");
        fld_grid_dark.setText(getColorValue(image_view.getGridDarkColor()));
        fld_grid_dark.addActionListener(this::onAction);
        pnl_grid_dark.add(fld_grid_dark);

        btn_grid_dark.setText("Задати темний колір");
        btn_grid_dark.setActionCommand("btn_grid_dark");
        btn_grid_dark.addActionListener(this::onAction);
        pnl_grid_dark.add(btn_grid_dark);

        pnl_grid_size.setLayout(new GridLayout(1, 0, 5, 0));

        fld_grid_size.setHorizontalAlignment(JTextField.CENTER);
        fld_grid_size.setActionCommand("fld_grid_size");
        fld_grid_size.setText(image_view.getGridSize() + "px");
        fld_grid_size.addActionListener(this::onAction);
        pnl_grid_size.add(fld_grid_size);

        btn_grid_size.setText("Задати розмір сітки");
        btn_grid_size.setActionCommand("btn_grid_size");
        btn_grid_size.addActionListener(this::onAction);
        pnl_grid_size.add(btn_grid_size);

        GroupLayout pnl_gridLayout = new GroupLayout(pnl_grid);
        pnl_grid.setLayout(pnl_gridLayout);
        pnl_gridLayout.setHorizontalGroup(pnl_gridLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_gridLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_gridLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_grid, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_grid_light, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_grid_size, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_grid)
                    .addComponent(pnl_grid_dark, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_gridLayout.setVerticalGroup(pnl_gridLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_gridLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_grid)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_grid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_grid_light, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_grid_dark, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_grid_size, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pnl_settings.addTab("Сітка", pnl_grid);

        bg_magnifier_shape.add(rb_magn_oval);
        rb_magn_oval.setText("Овальна лупа");
        rb_magn_oval.setActionCommand("rb_magn_oval");
        rb_magn_oval.setSelected(image_view.getZoomShapeType() == ZOOM_SHAPE_ELLIPSE);
        rb_magn_oval.addActionListener(this::onAction);

        bg_magnifier_shape.add(rb_magn_rect);
        rb_magn_rect.setText("Прямокутна лупа");
        rb_magn_rect.setActionCommand("rb_magn_rect");
        rb_magn_rect.setSelected(image_view.getZoomShapeType() == ZOOM_SHAPE_RECTANGLE);
        rb_magn_rect.addActionListener(this::onAction);

        pnl_magn_w.setLayout(new GridLayout(1, 0, 5, 0));

        fld_magn_w.setHorizontalAlignment(JTextField.CENTER);
        fld_magn_w.setActionCommand("fld_magn_w");
        fld_magn_w.setText(image_view.getZoomArea().width + "px");
        fld_magn_w.addActionListener(this::onAction);
        pnl_magn_w.add(fld_magn_w);

        btn_magn_w.setText("Задати ширину лупи");
        btn_magn_w.setActionCommand("btn_magn_w");
        btn_magn_w.addActionListener(this::onAction);
        pnl_magn_w.add(btn_magn_w);

        pnl_magn_h.setLayout(new GridLayout(1, 0, 5, 0));

        fld_magn_h.setHorizontalAlignment(JTextField.CENTER);
        fld_magn_h.setActionCommand("fld_magn_h");
        fld_magn_h.setText(image_view.getZoomArea().height + "px");
        fld_magn_h.addActionListener(this::onAction);
        pnl_magn_h.add(fld_magn_h);

        btn_magn_h.setText("Задати висоту лупи");
        btn_magn_h.setActionCommand("btn_magn_h");
        btn_magn_h.addActionListener(this::onAction);
        pnl_magn_h.add(btn_magn_h);

        cb_magn_scale.setMaximumRowCount(9);
        cb_magn_scale.setModel(new DefaultComboBoxModel<>(new String[] { "Масштаб лупи x1.25", "Масштаб лупи x1.50", "Масштаб лупи x1.75", "Масштаб лупи x2.00", "Масштаб лупи x2.25", "Масштаб лупи x2.50", "Масштаб лупи x3.00", "Масштаб лупи x4.00", "Масштаб лупи x5.00" }));
        cb_magn_scale.setSelectedIndex(5);
        cb_magn_scale.setActionCommand("cb_magn_scale");
        cb_magn_scale.addActionListener(this::onAction);

        cb_hide_cursor.setText("Приховувати курсор при збільшенні");
        cb_hide_cursor.setActionCommand("cb_hide_cursor");
        cb_hide_cursor.setSelected(!image_view.isZoomShowCursor());
        cb_hide_cursor.addActionListener(this::onAction);

        cb_f_zoom_border.setText("Відображати I рамку лупи");
        cb_f_zoom_border.setActionCommand("cb_f_zoom_border");
        cb_f_zoom_border.setSelected(image_view.isZoomFirstBorderVisible());
        cb_f_zoom_border.addActionListener(this::onAction);

        pnl_f_zoom_border_w.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_zoom_border_w.setHorizontalAlignment(JTextField.CENTER);
        fld_f_zoom_border_w.setActionCommand("fld_f_zoom_border_w");
        fld_f_zoom_border_w.setText(image_view.getZoomFirstBorderWidth() + "px");
        fld_f_zoom_border_w.addActionListener(this::onAction);
        pnl_f_zoom_border_w.add(fld_f_zoom_border_w);

        btn_f_zoom_border_w.setText("Ширина I рамки лупи");
        btn_f_zoom_border_w.setActionCommand("btn_f_zoom_border_w");
        btn_f_zoom_border_w.addActionListener(this::onAction);
        pnl_f_zoom_border_w.add(btn_f_zoom_border_w);

        pnl_f_zoom_border_h.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_zoom_border_gap.setHorizontalAlignment(JTextField.CENTER);
        fld_f_zoom_border_gap.setActionCommand("fld_f_zoom_border_gap");
        fld_f_zoom_border_gap.setText(image_view.getZoomFirstBorderGap() + "px");
        fld_f_zoom_border_gap.addActionListener(this::onAction);
        pnl_f_zoom_border_h.add(fld_f_zoom_border_gap);

        btn_f_zoom_border_gap.setText("Відступ I рамки лупи");
        btn_f_zoom_border_gap.setActionCommand("btn_f_zoom_border_gap");
        btn_f_zoom_border_gap.addActionListener(this::onAction);
        pnl_f_zoom_border_h.add(btn_f_zoom_border_gap);

        pnl_f_zoom_border_color.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_zoom_border_color.setHorizontalAlignment(JTextField.CENTER);
        fld_f_zoom_border_color.setActionCommand("fld_f_zoom_border_color");
        fld_f_zoom_border_color.setText(getColorValue(image_view.getZoomFirstBorderColor()));
        fld_f_zoom_border_color.addActionListener(this::onAction);
        pnl_f_zoom_border_color.add(fld_f_zoom_border_color);

        btn_f_zoom_border_color.setText("Колір I рамки лупи");
        btn_f_zoom_border_color.setActionCommand("btn_f_zoom_border_color");
        btn_f_zoom_border_color.addActionListener(this::onAction);
        pnl_f_zoom_border_color.add(btn_f_zoom_border_color);

        GroupLayout pnl_f_zoom_borderLayout = new GroupLayout(pnl_f_zoom_border);
        pnl_f_zoom_border.setLayout(pnl_f_zoom_borderLayout);
        pnl_f_zoom_borderLayout.setHorizontalGroup(pnl_f_zoom_borderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, pnl_f_zoom_borderLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_f_zoom_borderLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(pnl_f_zoom_border_color, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_f_zoom_border, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_f_zoom_border_w, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_f_zoom_border_h, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_f_zoom_borderLayout.setVerticalGroup(pnl_f_zoom_borderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_f_zoom_borderLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_f_zoom_border)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_f_zoom_border_w, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_f_zoom_border_h, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_f_zoom_border_color, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        tab_pnl_zoom_borders.addTab("I рамка лупи", pnl_f_zoom_border);

        cb_s_zoom_border.setText("Відображати II рамку лупи");
        cb_s_zoom_border.setActionCommand("cb_s_zoom_border");
        cb_s_zoom_border.setSelected(image_view.isZoomSecondBorderVisible());
        cb_s_zoom_border.addActionListener(this::onAction);

        pnl_s_zoom_border_w.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_zoom_border_w.setHorizontalAlignment(JTextField.CENTER);
        fld_s_zoom_border_w.setActionCommand("fld_s_zoom_border_w");
        fld_s_zoom_border_w.setText(image_view.getZoomSecondBorderWidth() + "px");
        fld_s_zoom_border_w.addActionListener(this::onAction);
        pnl_s_zoom_border_w.add(fld_s_zoom_border_w);

        btn_s_zoom_border_w.setText("Ширина II рамки лупи");
        btn_s_zoom_border_w.setActionCommand("btn_s_zoom_border_w");
        btn_s_zoom_border_w.addActionListener(this::onAction);
        pnl_s_zoom_border_w.add(btn_s_zoom_border_w);

        pnl_s_zoom_border_h.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_zoom_border_gap.setHorizontalAlignment(JTextField.CENTER);
        fld_s_zoom_border_gap.setActionCommand("fld_s_zoom_border_gap");
        fld_s_zoom_border_gap.setText(image_view.getZoomSecondBorderGap() + "px");
        fld_s_zoom_border_gap.addActionListener(this::onAction);
        pnl_s_zoom_border_h.add(fld_s_zoom_border_gap);

        btn_s_zoom_border_gap.setText("Відступ II рамки лупи");
        btn_s_zoom_border_gap.setActionCommand("btn_s_zoom_border_gap");
        btn_s_zoom_border_gap.addActionListener(this::onAction);
        pnl_s_zoom_border_h.add(btn_s_zoom_border_gap);

        pnl_s_zoom_border_color.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_zoom_border_color.setHorizontalAlignment(JTextField.CENTER);
        fld_s_zoom_border_color.setActionCommand("fld_s_zoom_border_color");
        fld_s_zoom_border_color.setText(getColorValue(image_view.getZoomSecondBorderColor()));
        fld_s_zoom_border_color.addActionListener(this::onAction);
        pnl_s_zoom_border_color.add(fld_s_zoom_border_color);

        btn_s_zoom_border_color.setText("Колір II рамки лупи");
        btn_s_zoom_border_color.setActionCommand("btn_s_zoom_border_color");
        btn_s_zoom_border_color.addActionListener(this::onAction);
        pnl_s_zoom_border_color.add(btn_s_zoom_border_color);

        GroupLayout pnl_s_zoom_borderLayout = new GroupLayout(pnl_s_zoom_border);
        pnl_s_zoom_border.setLayout(pnl_s_zoom_borderLayout);
        pnl_s_zoom_borderLayout.setHorizontalGroup(pnl_s_zoom_borderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_s_zoom_borderLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_s_zoom_borderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_s_zoom_border, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_s_zoom_border_w, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_s_zoom_border_h, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_s_zoom_border_color, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_s_zoom_borderLayout.setVerticalGroup(pnl_s_zoom_borderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_s_zoom_borderLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_s_zoom_border)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_s_zoom_border_w, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_s_zoom_border_h, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_s_zoom_border_color, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        tab_pnl_zoom_borders.addTab("II рамка лупи", pnl_s_zoom_border);

        cb_flip_zoom.setText("Перешкоджати виходу лупи за межі компонента");
        cb_flip_zoom.setActionCommand("cb_flip_zoom");
        cb_flip_zoom.setSelected(image_view.isInvertZoomOut());
        cb_flip_zoom.addActionListener(this::onAction);

        pnl_zoom_gap_w.setLayout(new GridLayout(1, 0, 5, 0));

        fld_zoom_gap_w.setHorizontalAlignment(JTextField.CENTER);
        fld_zoom_gap_w.setActionCommand("fld_zoom_gap_w");
        fld_zoom_gap_w.setText(image_view.getZoomOffset().x + "px");
        fld_zoom_gap_w.addActionListener(this::onAction);
        pnl_zoom_gap_w.add(fld_zoom_gap_w);

        btn_zoom_gap_w.setText("Зміщення по гор.");
        btn_zoom_gap_w.setActionCommand("btn_zoom_gap_w");
        btn_zoom_gap_w.addActionListener(this::onAction);
        pnl_zoom_gap_w.add(btn_zoom_gap_w);

        pnl_zoom_gap_h.setLayout(new GridLayout(1, 0, 5, 0));

        fld_zoom_gap_h.setHorizontalAlignment(JTextField.CENTER);
        fld_zoom_gap_h.setActionCommand("fld_zoom_gap_h");
        fld_zoom_gap_h.setText(image_view.getZoomOffset().y + "px");
        fld_zoom_gap_h.addActionListener(this::onAction);
        pnl_zoom_gap_h.add(fld_zoom_gap_h);

        btn_zoom_gap_h.setText("Зміщення по верт.");
        btn_zoom_gap_h.setActionCommand("btn_zoom_gap_h");
        btn_zoom_gap_h.addActionListener(this::onAction);
        pnl_zoom_gap_h.add(btn_zoom_gap_h);

        GroupLayout pnl_zoom_gapLayout = new GroupLayout(pnl_zoom_gap);
        pnl_zoom_gap.setLayout(pnl_zoom_gapLayout);
        pnl_zoom_gapLayout.setHorizontalGroup(pnl_zoom_gapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_zoom_gapLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_zoom_gapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_flip_zoom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_zoom_gap_w, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_zoom_gap_h, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_zoom_gapLayout.setVerticalGroup(pnl_zoom_gapLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_zoom_gapLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_flip_zoom)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_zoom_gap_w, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_zoom_gap_h, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        tab_pnl_zoom_borders.addTab("Зміщення лупи", pnl_zoom_gap);

        GroupLayout pnl_magnifierLayout = new GroupLayout(pnl_magnifier);
        pnl_magnifier.setLayout(pnl_magnifierLayout);
        pnl_magnifierLayout.setHorizontalGroup(pnl_magnifierLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_magnifierLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_magnifierLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(rb_magn_oval, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_magn_rect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_magn_rect)
                    .addComponent(pnl_magn_w, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_magn_h, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_magn_h)
                    .addComponent(cb_magn_scale, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_magn_scale)
                    .addComponent(cb_hide_cursor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_hide_cursor)
                    .addComponent(tab_pnl_zoom_borders))
                .addGap(3, 3, 3))
        );
        pnl_magnifierLayout.setVerticalGroup(pnl_magnifierLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_magnifierLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(rb_magn_oval)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_magn_rect)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_magn_rect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_magn_w, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_magn_h, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_magn_h, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_magn_scale, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_magn_scale, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_hide_cursor)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_hide_cursor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab_pnl_zoom_borders)
                .addGap(3, 3, 3))
        );

        tab_pnl_settings.addTab("Лупа", pnl_magnifier);

        cb_additional_stroke.setText("Відображати додатковий штрих");
        cb_additional_stroke.setActionCommand("cb_additional_stroke");
        cb_additional_stroke.setSelected(image_view.isRegionAdditionalStroke());
        cb_additional_stroke.addActionListener(this::onAction);

        pnl_f_stroke_color.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_stroke_color.setHorizontalAlignment(JTextField.CENTER);
        fld_f_stroke_color.setActionCommand("fld_f_stroke_color");
        fld_f_stroke_color.setText(getColorValue(image_view.getRegionLightColor()));
        fld_f_stroke_color.addActionListener(this::onAction);
        pnl_f_stroke_color.add(fld_f_stroke_color);

        btn_f_stroke_color.setText("Колір осн. штриха");
        btn_f_stroke_color.setActionCommand("btn_f_stroke_color");
        btn_f_stroke_color.addActionListener(this::onAction);
        pnl_f_stroke_color.add(btn_f_stroke_color);

        pnl_s_stroke_color.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_stroke_color.setHorizontalAlignment(JTextField.CENTER);
        fld_s_stroke_color.setActionCommand("fld_s_stroke_color");
        fld_s_stroke_color.setText(getColorValue(image_view.getRegionDarkColor()));
        fld_s_stroke_color.addActionListener(this::onAction);
        pnl_s_stroke_color.add(fld_s_stroke_color);

        btn_s_stroke_color.setText("Колір дод. штриха");
        btn_s_stroke_color.setActionCommand("btn_s_stroke_color");
        btn_s_stroke_color.addActionListener(this::onAction);
        pnl_s_stroke_color.add(btn_s_stroke_color);

        pnl_stroke_w.setLayout(new GridLayout(1, 0, 5, 0));

        fld_stroke_w.setHorizontalAlignment(JTextField.CENTER);
        fld_stroke_w.setActionCommand("fld_stroke_w");
        fld_stroke_w.setText(String.format("%.0fpx", image_view.getRegionStroke().getLineWidth()));
        fld_stroke_w.addActionListener(this::onAction);
        pnl_stroke_w.add(fld_stroke_w);

        btn_stroke_w.setText("Задати шир. штриха");
        btn_stroke_w.setActionCommand("btn_stroke_w");
        btn_stroke_w.addActionListener(this::onAction);
        pnl_stroke_w.add(btn_stroke_w);

        btn_random_stroke.setText("Згенерувати новий варіант штриха");
        btn_random_stroke.setActionCommand("btn_random_stroke");
        btn_random_stroke.addActionListener(this::onAction);

        pnl_region_min.setLayout(new GridLayout(1, 0, 5, 0));

        fld_region_min.setHorizontalAlignment(JTextField.CENTER);
        fld_region_min.setActionCommand("fld_region_min");
        fld_region_min.setText(image_view.getRegionMinimumSize() + "px");
        fld_region_min.addActionListener(this::onAction);
        pnl_region_min.add(fld_region_min);

        btn_region_min.setText("Мін. розмір регіону");
        btn_region_min.setActionCommand("btn_region_min");
        btn_region_min.addActionListener(this::onAction);
        pnl_region_min.add(btn_region_min);

        GroupLayout pnl_regionLayout = new GroupLayout(pnl_region);
        pnl_region.setLayout(pnl_regionLayout);
        pnl_regionLayout.setHorizontalGroup(pnl_regionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_regionLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_regionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_additional_stroke, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_s_stroke_color, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_additional_stroke)
                    .addComponent(pnl_f_stroke_color, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_s_stroke_color)
                    .addComponent(pnl_stroke_w, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_random_stroke, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sep_random_stroke)
                    .addComponent(pnl_region_min, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_regionLayout.setVerticalGroup(pnl_regionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_regionLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_additional_stroke)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_additional_stroke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_f_stroke_color, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_s_stroke_color, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_s_stroke_color, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_stroke_w, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_random_stroke)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sep_random_stroke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_region_min, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pnl_settings.addTab("Регіон", pnl_region);

        cb_move_out.setText("Переміщувати зображення за межею видимості");
        cb_move_out.setActionCommand("cb_move_out");
        cb_move_out.setSelected(image_view.isDrugImageOut());
        cb_move_out.addActionListener(this::onAction);

        cb_zoom_out.setText("Відображати лупу за межею видимості");
        cb_zoom_out.setActionCommand("cb_zoom_out");
        cb_zoom_out.setSelected(image_view.isDrugZoomOut());
        cb_zoom_out.addActionListener(this::onAction);

        cb_invert.setText("Інвертувати колесико миші");
        cb_invert.setActionCommand("cb_invert");
        cb_invert.setSelected(image_view.isWheelInvert());
        cb_invert.addActionListener(this::onAction);

        bg_scrollbars_visibility.add(rb_sb_always);
        rb_sb_always.setText("Постійно відображат смуги прокрутки");
        rb_sb_always.setActionCommand("rb_sb_always");
        rb_sb_always.setSelected(image_view.getVerticalScrollBarPolicy() == ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED && image_view.getHorizontalScrollBarPolicy() == ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rb_sb_always.addActionListener(this::onAction);

        bg_scrollbars_visibility.add(rb_sb_as_needed);
        rb_sb_as_needed.setText("Відображати смуги прокрутки за потреби");
        rb_sb_as_needed.setActionCommand("rb_sb_as_needed");
        rb_sb_as_needed.setSelected(image_view.getVerticalScrollBarPolicy() == ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED && image_view.getHorizontalScrollBarPolicy() == ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rb_sb_as_needed.addActionListener(this::onAction);

        bg_scrollbars_visibility.add(rb_sb_never);
        rb_sb_never.setText("Ніколи не відображати смуги прокрутки");
        rb_sb_never.setActionCommand("rb_sb_never");
        rb_sb_never.setSelected(image_view.getVerticalScrollBarPolicy() == ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER && image_view.getHorizontalScrollBarPolicy() == ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        rb_sb_never.addActionListener(this::onAction);

        GroupLayout pnl_additionallyLayout = new GroupLayout(pnl_additionally);
        pnl_additionally.setLayout(pnl_additionallyLayout);
        pnl_additionallyLayout.setHorizontalGroup(pnl_additionallyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_additionallyLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_additionallyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_move_out, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_invert, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_invert)
                    .addComponent(rb_sb_as_needed, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_sb_always, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_sb_never, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_zoom_out, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_additionallyLayout.setVerticalGroup(pnl_additionallyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_additionallyLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_move_out)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_zoom_out)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_invert)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_invert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_sb_always)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_sb_as_needed)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_sb_never)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tab_pnl_settings.addTab("Додатково", pnl_additionally);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(image_view, GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(sp_scale, GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_scale, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_original, GroupLayout.Alignment.LEADING)
                    .addComponent(btn_original, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_open, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_open, GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_zoom, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_min_max, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_fit, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_center_region, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tab_pnl_settings)
                    .addComponent(pnl_rotate_mirror, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sep_rotate_mirror))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(image_view, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_open)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sp_open, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_zoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_fit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_min_max, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_center_region, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_original)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sp_original, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_scale, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sp_scale, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_rotate_mirror, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sep_rotate_mirror, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tab_pnl_settings)))
                .addGap(5, 5, 5))
        );

        pack();
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            boolean minimumSizeSet = false;
            @Override
            public void windowOpened (WindowEvent e) {
                if (!minimumSizeSet) {
                    setMinimumSize(getSize());
                    minimumSizeSet = true; } } });
    }//GEN-END:initComponents

///////////////////////////////////////////////////////////////////////////////

    private void onAction(ActionEvent evt) {//GEN-FIRST:event_onAction
 
    switch (evt.getActionCommand()) {
            
        case "btn_open" -> {
            JFileChooser chooser = new JFileChooser();
            chooser.getActionMap().get("viewTypeDetails")
                                  .actionPerformed(null);
            ImagePreviewComponent preview = new ImagePreviewComponent(chooser);
            chooser.setAccessory(preview);
            chooser.setFileFilter(new FileNameExtensionFilter("Image files",
                                      ImageIO.getReaderFileSuffixes()));
            int result = chooser.showOpenDialog(this);
            preview.deletePreview();
            if (result != JFileChooser.APPROVE_OPTION) { return; }
            File file = chooser.getSelectedFile();
            image_view.setImage(getImageQuickly(file)); }

        // ....................................................................
        
        case "btn_rotate_90"  -> { image_view.turnСlockwise();        }
        case "btn_rotate_180" -> { image_view.rollOver();             }
        case "btn_rotate_270" -> { image_view.turnСounterclockwise(); }
        case "btn_flip_h"     -> { image_view.mirrorHorizontally();   }
        case "btn_flip_v"     -> { image_view.mirrorVertically();     }
        
        case "btn_zoom_in"      -> { image_view.zoomIn(null);     }
        case "btn_zoom_out"     -> { image_view.zoomOut(null);    }
        case "btn_minimize"     -> { image_view.minimize();       }
        case "btn_maximize"     -> { image_view.maximize();       }
        case "btn_internal_fit" -> { image_view.fitInternal();    }
        case "btn_external_fit" -> { image_view.fitExternal();    }
        case "btn_original"     -> { image_view.zoomToOriginal(); }
        case "btn_region_fit"   -> { image_view.setRegion();      }
        case "btn_center"       -> { image_view.center();         }

        case "btn_set_scale", "fld_scale" ->
            { Float value = getNumericValue(fld_scale);
              if (value != null)
              { image_view.setImageScale(value);
                fld_scale.setText(getRoundedValue(image_view
                         .getImageScale()) + "%"); } }

        // ................................................................

        case "cb_lmb" -> { image_view.setLMBEnable(cb_lmb.isSelected()); }
        case "cb_cmb" -> { image_view.setCMBEnable(cb_cmb.isSelected()); }
        case "cb_rmb" -> { image_view.setRMBEnable(cb_rmb.isSelected()); }
        case "cb_zmb" -> { image_view.setZMBEnable(cb_zmb.isSelected()); }

        case "cb_wheel" ->
            { image_view.setWheelEnable(cb_wheel.isSelected()); }

        case "rb_zoom_fast" ->
            { image_view.setImageScaleType(SCALE_TYPE_FAST); }
        case "rb_zoom_smooth" ->
            { image_view.setImageScaleType(SCALE_TYPE_SMOOTH); }

        case "rb_open_original" ->
            { image_view.setImageOpenSize(OPEN_SIZE_ORIGINAL); }
        case "rb_open_internal_fit" ->
            { image_view.setImageOpenSize(OPEN_SIZE_INTERNAL_FIT); }
        case "rb_open_external_fit" ->
            { image_view.setImageOpenSize(OPEN_SIZE_EXTERNAL_FIT); }
        
        // ................................................................

        case "cb_grid" ->
            { image_view.setGridVisible(cb_grid.isSelected()); }

        case "btn_grid_light" ->
            { Color color = chooseColorValue(image_view
                           .getGridLightColor());
              if (color != null) { image_view
                                  .setGridLightColor(color); } }
        case "btn_grid_dark" ->
            { Color color = chooseColorValue(image_view
                           .getGridDarkColor());
              if (color != null) { image_view
                                  .setGridDarkColor(color); } }
        case "fld_grid_light" ->
            { Color color = getColorValue(fld_grid_light);
              if (color != null) { image_view
                                  .setGridLightColor(color); } }
        case "fld_grid_dark" ->
            { Color color = getColorValue(fld_grid_dark);
              if (color != null) { image_view
                                  .setGridDarkColor(color); } }

        case "btn_grid_size", "fld_grid_size" ->
            { Float value = getNumericValue(fld_grid_size);
              if (value != null)
              { image_view.setGridSize(value.intValue());
                fld_grid_size.setText(image_view.getGridSize() + "px"); } }

        // ................................................................

        case "rb_magn_oval" ->
            { image_view.setZoomShapeType(ZOOM_SHAPE_ELLIPSE); }
        case "rb_magn_rect" ->
            { image_view.setZoomShapeType(ZOOM_SHAPE_RECTANGLE); }

        case "btn_magn_w", "fld_magn_w" ->
            { Float value = getNumericValue(fld_magn_w);
              if (value != null)
              { Dimension zoomArea = image_view.getZoomArea();
                zoomArea.width = value.intValue();
                image_view.setZoomArea(zoomArea);
                fld_magn_w.setText(image_view
                          .getZoomArea().width + "px"); } }
        case "btn_magn_h", "fld_magn_h" ->
            { Float value = getNumericValue(fld_magn_h);
              if (value != null)
              { Dimension zoomArea = image_view.getZoomArea();
                zoomArea.height = value.intValue();
                image_view.setZoomArea(zoomArea);
                fld_magn_h.setText(image_view
                          .getZoomArea().height + "px"); } }

        case "cb_magn_scale" ->
            { float scale = 0;
              switch (cb_magn_scale.getSelectedIndex()) {
                  case 0 -> { scale = ZOOM_SCALE_X1_25; }
                  case 1 -> { scale = ZOOM_SCALE_X1_50; }
                  case 2 -> { scale = ZOOM_SCALE_X1_75; }
                  case 3 -> { scale = ZOOM_SCALE_X2_00; }
                  case 4 -> { scale = ZOOM_SCALE_X2_25; }
                  case 5 -> { scale = ZOOM_SCALE_X2_50; }
                  case 6 -> { scale = ZOOM_SCALE_X3_00; }
                  case 7 -> { scale = ZOOM_SCALE_X4_00; }
                  case 8 -> { scale = ZOOM_SCALE_X5_00; } }
              image_view.setZoomLevel(scale); }

        case "cb_hide_cursor" ->
            { image_view.setZoomShowCursor(!cb_hide_cursor.isSelected()); }

        case "cb_f_zoom_border" ->
            { image_view.setZoomFirstBorderVisible(cb_f_zoom_border
                        .isSelected()); }
        case "cb_s_zoom_border" ->
            { image_view.setZoomSecondBorderVisible(cb_s_zoom_border
                        .isSelected()); }

        case "btn_f_zoom_border_w", "fld_f_zoom_border_w" ->
            { Float value = getNumericValue(fld_f_zoom_border_w);
              if (value != null)
              { image_view.setZoomFirstBorderWidth(value.intValue());
                fld_f_zoom_border_w.setText(image_view
               .getZoomFirstBorderWidth() + "px"); } }
        case "btn_s_zoom_border_w", "fld_s_zoom_border_w" ->
            { Float value = getNumericValue(fld_s_zoom_border_w);
              if (value != null)
              { image_view.setZoomSecondBorderWidth(value.intValue());
                fld_s_zoom_border_w.setText(image_view
               .getZoomSecondBorderWidth() + "px"); } }

        case "btn_f_zoom_border_gap", "fld_f_zoom_border_gap" ->
            { Float value = getNumericValue(fld_f_zoom_border_gap);
              if (value != null)
              { image_view.setZoomFirstBorderGap(value.intValue());
                fld_f_zoom_border_gap.setText(image_view
               .getZoomFirstBorderGap() + "px"); } }
        case "btn_s_zoom_border_gap", "fld_s_zoom_border_gap" ->
            { Float value = getNumericValue(fld_s_zoom_border_gap);
              if (value != null)
              { image_view.setZoomSecondBorderGap(value.intValue());
                fld_s_zoom_border_gap.setText(image_view
               .getZoomSecondBorderGap() + "px"); } }

        case "btn_f_zoom_border_color" ->
            { Color color = chooseColorValue(image_view
                           .getZoomFirstBorderColor());
              if (color != null) { image_view
                                  .setZoomFirstBorderColor(color); } }
        case "btn_s_zoom_border_color" ->
            { Color color = chooseColorValue(image_view
                           .getZoomSecondBorderColor());
              if (color != null) { image_view
                                  .setZoomSecondBorderColor(color); } }

        case "fld_f_zoom_border_color" ->
            { Color color = getColorValue(fld_f_zoom_border_color);
              if (color != null) { image_view
                                  .setZoomFirstBorderColor(color); } }
        case "fld_s_zoom_border_color" ->
            { Color color = getColorValue(fld_s_zoom_border_color);
              if (color != null) { image_view
                                  .setZoomSecondBorderColor(color); } }

        case "cb_flip_zoom" ->
            { image_view.setInvertZoomOut(cb_flip_zoom.isSelected()); }

        case "btn_zoom_gap_w", "fld_zoom_gap_w" ->
            { Float value = getNumericValue(fld_zoom_gap_w);
              if (value != null)
              { Point zoomOffset = image_view.getZoomOffset();
                zoomOffset.x = value.intValue();
                image_view.setZoomOffset(zoomOffset);
                fld_zoom_gap_w.setText(image_view
               .getZoomOffset().x + "px"); } }
        case "btn_zoom_gap_h", "fld_zoom_gap_h" ->
            { Float value = getNumericValue(fld_zoom_gap_h);
              if (value != null)
              { Point zoomOffset = image_view.getZoomOffset();
                zoomOffset.y = value.intValue();
                image_view.setZoomOffset(zoomOffset);
                fld_zoom_gap_h.setText(image_view
               .getZoomOffset().y + "px"); } }

        // ................................................................

        case "cb_additional_stroke" ->
            { image_view.setRegionAdditionalStroke(cb_additional_stroke
                        .isSelected()); }

        case "btn_f_stroke_color" ->
            { Color color = chooseColorValue(image_view
                           .getRegionLightColor());
              if (color != null) { image_view
                                  .setRegionLightColor(color); } }
        case "btn_s_stroke_color" ->
            { Color color = chooseColorValue(image_view
                           .getRegionDarkColor());
              if (color != null) { image_view
                                  .setRegionDarkColor(color); } }

        case "fld_f_stroke_color" ->
            { Color color = getColorValue(fld_f_stroke_color);
              if (color != null) { image_view
                                  .setRegionLightColor(color); } }
        case "fld_s_stroke_color" ->
            { Color color = getColorValue(fld_s_stroke_color);
              if (color != null) { image_view
                                  .setRegionDarkColor(color); } }

        case "btn_stroke_w", "fld_stroke_w" ->
            { Float value = getNumericValue(fld_stroke_w);
              if (value != null)
              { BasicStroke stroke = image_view.getRegionStroke();
                stroke = getStroke(stroke, value.intValue());
                image_view.setRegionStroke(stroke);
                fld_stroke_w.setText(String.format("%.0fpx", image_view
                            .getRegionStroke().getLineWidth())); } }

        case "btn_random_stroke" ->
            { image_view.setRegionStroke(getRandomStroke()); }
            
        case "btn_region_min", "fld_region_min" ->
            { Float value = getNumericValue(fld_region_min);
              if (value != null)
              { image_view.setRegionMinimumSize(value.intValue());
                fld_region_min.setText(image_view
               .getRegionMinimumSize() + "px"); } }

        // ................................................................

        case "cb_move_out" ->
            { image_view.setDrugImageOut(cb_move_out.isSelected()); }
        case "cb_zoom_out" ->
            { image_view.setDrugZoomOut(cb_zoom_out.isSelected()); }
        case "cb_invert" ->
            { image_view.setWheelInvert(cb_invert.isSelected()); }

        case "rb_sb_always" ->
            { image_view.setHorizontalScrollBarPolicy
                        (HORIZONTAL_SCROLLBAR_ALWAYS);
              image_view.setVerticalScrollBarPolicy
                        (VERTICAL_SCROLLBAR_ALWAYS); }
        case "rb_sb_as_needed" ->
            { image_view.setHorizontalScrollBarPolicy
                        (HORIZONTAL_SCROLLBAR_AS_NEEDED);
              image_view.setVerticalScrollBarPolicy
                        (VERTICAL_SCROLLBAR_AS_NEEDED); }
        case "rb_sb_never" ->
            { image_view.setHorizontalScrollBarPolicy
                        (HORIZONTAL_SCROLLBAR_NEVER);
              image_view.setVerticalScrollBarPolicy
                        (VERTICAL_SCROLLBAR_NEVER); }
    }
    }//GEN-LAST:event_onAction

///////////////////////////////////////////////////////////////////////////////

    private void imageViewPropertyChange(PropertyChangeEvent evt) {//GEN-FIRST:event_imageViewPropertyChange

    switch (evt.getPropertyName()) {

    case "imageScale" ->
        { fld_scale.setText(getRoundedValue
                           (image_view.getImageScale()) + "%"); }
        
    case "gridLightColor" ->
        { fld_grid_light.setText(getColorValue(image_view
                        .getGridLightColor())); }
    case "gridDarkColor" ->
        { fld_grid_dark.setText(getColorValue(image_view
                       .getGridDarkColor())); }
    case "regionLightColor" ->
        { fld_f_stroke_color.setText(getColorValue(image_view
                            .getRegionLightColor())); }
    case "regionDarkColor" ->
        { fld_s_stroke_color.setText(getColorValue(image_view
                            .getRegionDarkColor())); }
    case "zoomFirstBorderColor" ->
        { fld_f_zoom_border_color.setText(getColorValue(image_view
                                 .getZoomFirstBorderColor())); }
    case "zoomSecondBorderColor" ->
        { fld_s_zoom_border_color.setText(getColorValue(image_view
                                 .getZoomSecondBorderColor())); }
        
    case "zoomLevel" ->
        { int index = -1;
          switch (String.valueOf(image_view.getZoomLevel())) {
              case "1.25" -> { index = 0; }
              case "1.5" -> { index = 1; }
              case "1.75" -> { index = 2; }
              case "2.0" -> { index = 3; }
              case "2.25" -> { index = 4; }
              case "2.5" -> { index = 5; }
              case "3.0" -> { index = 6; }
              case "4.0" -> { index = 7; }
              case "5.0" -> { index = 8; } }
          cb_magn_scale.setSelectedIndex(index); }
        
    }
    }//GEN-LAST:event_imageViewPropertyChange

///////////////////////////////////////////////////////////////////////////////

private String getRoundedValue (float value) {
    return String.format("%.2f", value);
}

///////////////////////////////////////////////////////////////////////////////

private String getColorValue (Color value) {
    return String.format("0x%06X", value.getRGB() & 0xFFFFFF);
}

///////////////////////////////////////////////////////////////////////////////

private Color getColorValue (JTextField field) {
    
    Color color = null;

    try { color = new Color(Integer.parseInt(field
                                   .getText().substring(2), 16));
          field.setBackground(bgNormal); }
    catch (NumberFormatException e) { field.setBackground(bgError); }
    
    return color;
    
}

///////////////////////////////////////////////////////////////////////////////

private Float getNumericValue (JTextField field) {
    
    Float result = null;
    String value = new String();
    char[] chars = field.getText().toCharArray();
    
    for (char c : chars) {
        if ((c >= 48 && c <= 57) ||
            (c == '-') || (c == '.') || (c == ',')) { value += c; } }
    
    try { result = Float.valueOf(value.replaceAll(",", "."));
          field.setBackground(bgNormal); }
    catch (NumberFormatException e) { field.setBackground(bgError); }
    
    return result;
    
}

///////////////////////////////////////////////////////////////////////////////

private Color chooseColorValue (Color color) {
    return JColorChooser.showDialog(this, null, color);
}

///////////////////////////////////////////////////////////////////////////////

private BasicStroke getStroke (BasicStroke stroke, int width) {
    
    if      (width < 1)  { width = 1;  }
    else if (width > 10) { width = 10; }
    
    return new BasicStroke(width, stroke.getEndCap(),
                                  stroke.getLineJoin(),
                                  stroke.getMiterLimit(),
                                  stroke.getDashArray(),
                                  stroke.getDashPhase()); }

///////////////////////////////////////////////////////////////////////////////

private BasicStroke getRandomStroke() {
    
    float width = image_view.getRegionStroke().getLineWidth();
    int cap = -1, join = -1;
    int limit = (int)(Math.random() * 7) + 1;
    float[] dash = new float[(int)(Math.random() * 5) + 2];
    int phase = 0;
    
    switch ((int)(Math.random() * 3)) {
        case 0 -> { cap = BasicStroke.CAP_BUTT; }
        case 1 -> { cap = BasicStroke.CAP_ROUND; }
        case 2 -> { cap = BasicStroke.CAP_SQUARE; } }
    
    switch ((int)(Math.random() * 3)) {
        case 0 -> { join = BasicStroke.JOIN_BEVEL; }
        case 1 -> { join = BasicStroke.JOIN_MITER; }
        case 2 -> { join = BasicStroke.JOIN_ROUND; } }
    
    for (int z = 0; z < dash.length; z++) {
        dash[z] = (int)(Math.random() * 11) + 3; }
    
    return new BasicStroke(width, cap, join, limit, dash, phase); }

///////////////////////////////////////////////////////////////////////////////

private void increaseTextSize (int delta, JButton ... buttons) {
    
    for (JButton button : buttons) {
        Font font = button.getFont();
        button.setFont(new Font(font.getName(),
                                font.getStyle(),
                                font.getSize() + delta)); } }

///////////////////////////////////////////////////////////////////////////////

private void initAppIcons() {

ArrayList<Image> icons = new ArrayList<>();
JImageViewBeanInfo bean = new JImageViewBeanInfo();

icons.add(bean.getIcon(BeanInfo.ICON_COLOR_16x16));
icons.add(bean.getIcon(BeanInfo.ICON_COLOR_32x32));

setIconImages(icons);

}

///////////////////////////////////////////////////////////////////////////////

public static void main (String args[]) {

    EventQueue.invokeLater(() -> {
        new JImageViewDemo().setVisible(true);
    });
}

///////////////////////////////////////////////////////////////////////////////

private final Color bgNormal = new JTextField().getBackground();
private final Color bgError  = new Color(255, 200, 200);

// ............................................................................

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonGroup bg_magnifier_shape;
    private ButtonGroup bg_open_size;
    private ButtonGroup bg_scale_type;
    private ButtonGroup bg_scrollbars_visibility;
    private JButton btn_center;
    private JButton btn_external_fit;
    private JButton btn_f_stroke_color;
    private JButton btn_f_zoom_border_color;
    private JButton btn_f_zoom_border_gap;
    private JButton btn_f_zoom_border_w;
    private JButton btn_flip_h;
    private JButton btn_flip_v;
    private JButton btn_grid_dark;
    private JButton btn_grid_light;
    private JButton btn_grid_size;
    private JButton btn_internal_fit;
    private JButton btn_magn_h;
    private JButton btn_magn_w;
    private JButton btn_maximize;
    private JButton btn_minimize;
    private JButton btn_open;
    private JButton btn_original;
    private JButton btn_random_stroke;
    private JButton btn_region_fit;
    private JButton btn_region_min;
    private JButton btn_rotate_180;
    private JButton btn_rotate_270;
    private JButton btn_rotate_90;
    private JButton btn_s_stroke_color;
    private JButton btn_s_zoom_border_color;
    private JButton btn_s_zoom_border_gap;
    private JButton btn_s_zoom_border_w;
    private JButton btn_set_scale;
    private JButton btn_stroke_w;
    private JButton btn_zoom_gap_h;
    private JButton btn_zoom_gap_w;
    private JButton btn_zoom_in;
    private JButton btn_zoom_out;
    private JCheckBox cb_additional_stroke;
    private JCheckBox cb_cmb;
    private JCheckBox cb_f_zoom_border;
    private JCheckBox cb_flip_zoom;
    private JCheckBox cb_grid;
    private JCheckBox cb_hide_cursor;
    private JCheckBox cb_invert;
    private JCheckBox cb_lmb;
    private JComboBox<String> cb_magn_scale;
    private JCheckBox cb_move_out;
    private JCheckBox cb_rmb;
    private JCheckBox cb_s_zoom_border;
    private JCheckBox cb_wheel;
    private JCheckBox cb_zmb;
    private JCheckBox cb_zoom_out;
    private JTextField fld_f_stroke_color;
    private JTextField fld_f_zoom_border_color;
    private JTextField fld_f_zoom_border_gap;
    private JTextField fld_f_zoom_border_w;
    private JTextField fld_grid_dark;
    private JTextField fld_grid_light;
    private JTextField fld_grid_size;
    private JTextField fld_magn_h;
    private JTextField fld_magn_w;
    private JTextField fld_region_min;
    private JTextField fld_s_stroke_color;
    private JTextField fld_s_zoom_border_color;
    private JTextField fld_s_zoom_border_gap;
    private JTextField fld_s_zoom_border_w;
    private JTextField fld_scale;
    private JTextField fld_stroke_w;
    private JTextField fld_zoom_gap_h;
    private JTextField fld_zoom_gap_w;
    private JImageView image_view;
    private JPanel pnl_additionally;
    private JPanel pnl_center_region;
    private JPanel pnl_f_stroke_color;
    private JPanel pnl_f_zoom_border;
    private JPanel pnl_f_zoom_border_color;
    private JPanel pnl_f_zoom_border_h;
    private JPanel pnl_f_zoom_border_w;
    private JPanel pnl_fit;
    private JPanel pnl_global;
    private JPanel pnl_grid;
    private JPanel pnl_grid_dark;
    private JPanel pnl_grid_light;
    private JPanel pnl_grid_size;
    private JPanel pnl_magn_h;
    private JPanel pnl_magn_w;
    private JPanel pnl_magnifier;
    private JPanel pnl_min_max;
    private JPanel pnl_region;
    private JPanel pnl_region_min;
    private JPanel pnl_rotate_mirror;
    private JPanel pnl_s_stroke_color;
    private JPanel pnl_s_zoom_border;
    private JPanel pnl_s_zoom_border_color;
    private JPanel pnl_s_zoom_border_h;
    private JPanel pnl_s_zoom_border_w;
    private JPanel pnl_scale;
    private JPanel pnl_stroke_w;
    private JPanel pnl_zoom;
    private JPanel pnl_zoom_gap;
    private JPanel pnl_zoom_gap_h;
    private JPanel pnl_zoom_gap_w;
    private JRadioButton rb_magn_oval;
    private JRadioButton rb_magn_rect;
    private JRadioButton rb_open_external_fit;
    private JRadioButton rb_open_internal_fit;
    private JRadioButton rb_open_original;
    private JRadioButton rb_sb_always;
    private JRadioButton rb_sb_as_needed;
    private JRadioButton rb_sb_never;
    private JRadioButton rb_zoom_fast;
    private JRadioButton rb_zoom_smooth;
    private JSeparator sep_random_stroke;
    private JSeparator sep_rotate_mirror;
    private JSeparator sep_zoom_smooth;
    private JSeparator sp_additional_stroke;
    private JSeparator sp_grid;
    private JSeparator sp_hide_cursor;
    private JSeparator sp_invert;
    private JSeparator sp_magn_h;
    private JSeparator sp_magn_rect;
    private JSeparator sp_magn_scale;
    private JSeparator sp_open;
    private JSeparator sp_original;
    private JSeparator sp_s_stroke_color;
    private JSeparator sp_scale;
    private JSeparator sp_zmb;
    private JTabbedPane tab_pnl_settings;
    private JTabbedPane tab_pnl_zoom_borders;
    // End of variables declaration//GEN-END:variables

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
                   gridSize, gridSize); } }
    
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
        
        case JFileChooser.DIRECTORY_CHANGED_PROPERTY -> { image = null;
                                                          repaint(); }
        
        case JFileChooser.SELECTED_FILE_CHANGED_PROPERTY -> {
                
            File file = (File) e.getNewValue();
            
                image = JImageViewUtils.getImageQuickly(file);
            if (image == null) {
                image = JImageViewUtils.getImageSlowly(file); }
            
            repaint();
        }
    }
}

// ............................................................................

private void deletePreview() { image = null; }

}

// Кінець класу JImageViewDemo ////////////////////////////////////////////////

}
