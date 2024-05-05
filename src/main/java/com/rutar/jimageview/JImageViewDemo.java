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

        bg_1 = new ButtonGroup();
        bg_2 = new ButtonGroup();
        bg_3 = new ButtonGroup();
        image_view = new JImageView();
        btn_open = new JButton();
        sp_01 = new JSeparator();
        pnl_01 = new JPanel();
        btn_zoom_out = new JButton();
        btn_zoom_in = new JButton();
        pnl_02 = new JPanel();
        btn_minimize = new JButton();
        btn_maximize = new JButton();
        pnl_03 = new JPanel();
        btn_internal_fit = new JButton();
        btn_external_fit = new JButton();
        pnl_04 = new JPanel();
        btn_center = new JButton();
        btn_region_fit = new JButton();
        btn_original = new JButton();
        sp_02 = new JSeparator();
        pnl_05 = new JPanel();
        fld_scale = new JTextField();
        btn_set_scale = new JButton();
        sp_03 = new JSeparator();
        jPanel1 = new JPanel();
        btn_rotate_90 = new JButton();
        btn_rotate_270 = new JButton();
        btn_rotate_180 = new JButton();
        btn_flip_h = new JButton();
        btn_flip_v = new JButton();
        jSeparator1 = new JSeparator();
        tab_pnl_01 = new JTabbedPane();
        pnl_06 = new JPanel();
        cb_lmb = new JCheckBox();
        cb_cmb = new JCheckBox();
        cb_rmb = new JCheckBox();
        cb_wheel = new JCheckBox();
        cb_zmb = new JCheckBox();
        sp_04 = new JSeparator();
        rb_zoom_fast = new JRadioButton();
        rb_zoom_smooth = new JRadioButton();
        jSeparator3 = new JSeparator();
        jRadioButton1 = new JRadioButton();
        jRadioButton2 = new JRadioButton();
        jRadioButton3 = new JRadioButton();
        pnl_07 = new JPanel();
        cb_grid = new JCheckBox();
        sp_05 = new JSeparator();
        pnl_11 = new JPanel();
        fld_grid_light = new JTextField();
        btn_grid_light = new JButton();
        pnl_12 = new JPanel();
        fld_grid_dark = new JTextField();
        btn_grid_dark = new JButton();
        pnl_13 = new JPanel();
        fld_grid_size = new JTextField();
        btn_grid_size = new JButton();
        pnl_08 = new JPanel();
        rb_magn_oval = new JRadioButton();
        rb_magn_rect = new JRadioButton();
        sp_06 = new JSeparator();
        pnl_14 = new JPanel();
        fld_magn_w = new JTextField();
        btn_magn_w = new JButton();
        pnl_15 = new JPanel();
        fld_magn_h = new JTextField();
        btn_magn_h = new JButton();
        sp_07 = new JSeparator();
        cb_magn_scale = new JComboBox<>();
        sp_08 = new JSeparator();
        cb_hide_cursor = new JCheckBox();
        sp_09 = new JSeparator();
        tab_pnl_02 = new JTabbedPane();
        pnl_16 = new JPanel();
        cb_f_zoom_border = new JCheckBox();
        pnl_19 = new JPanel();
        fld_f_zoom_border_w = new JTextField();
        btn_f_zoom_border_w = new JButton();
        pnl_20 = new JPanel();
        fld_f_zoom_border_gap = new JTextField();
        btn_f_zoom_border_gap = new JButton();
        pnl_21 = new JPanel();
        fld_f_zoom_border_color = new JTextField();
        btn_f_zoom_border_color = new JButton();
        pnl_17 = new JPanel();
        cb_s_zoom_border = new JCheckBox();
        pnl_22 = new JPanel();
        fld_s_zoom_border_w = new JTextField();
        btn_s_zoom_border_w = new JButton();
        pnl_23 = new JPanel();
        fld_s_zoom_border_gap = new JTextField();
        btn_s_zoom_border_gap = new JButton();
        pnl_24 = new JPanel();
        fld_s_zoom_border_color = new JTextField();
        btn_s_zoom_border_color = new JButton();
        pnl_18 = new JPanel();
        cb_flip_zoom = new JCheckBox();
        pnl_25 = new JPanel();
        fld_zoom_gap_w = new JTextField();
        btn_zoom_gap_w = new JButton();
        pnl_26 = new JPanel();
        fld_zoom_gap_h = new JTextField();
        btn_zoom_gap_h = new JButton();
        pnl_09 = new JPanel();
        cb_additional_stroke = new JCheckBox();
        sp_10 = new JSeparator();
        pnl_27 = new JPanel();
        fld_f_stroke_color = new JTextField();
        btn_f_stroke_color = new JButton();
        pnl_28 = new JPanel();
        fld_s_stroke_color = new JTextField();
        btn_s_stroke_color = new JButton();
        sp_11 = new JSeparator();
        pnl_29 = new JPanel();
        fld_stroke_w = new JTextField();
        btn_stroke_w = new JButton();
        btn_random_stroke = new JButton();
        jSeparator2 = new JSeparator();
        pnl_30 = new JPanel();
        fld_region_min = new JTextField();
        btn_region_min = new JButton();
        pnl_10 = new JPanel();
        cb_move_out = new JCheckBox();
        cb_zoom_out = new JCheckBox();
        cb_invert = new JCheckBox();
        sp_12 = new JSeparator();
        rb_sb_always = new JRadioButton();
        rb_sb_as_needed = new JRadioButton();
        rb_sb_never = new JRadioButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("JImageView Demo");

        image_view.addPropertyChangeListener(this::imageViewPropertyChange);

        btn_open.setText("Вибрати зображення");
        btn_open.setActionCommand("btn_open");
        btn_open.addActionListener(this::onAction);

        pnl_01.setLayout(new GridLayout(1, 0, 5, 0));

        btn_zoom_out.setText("Зменшити");
        btn_zoom_out.setActionCommand("btn_zoom_out");
        btn_zoom_out.addActionListener(this::onAction);
        pnl_01.add(btn_zoom_out);

        btn_zoom_in.setText("Збільшити");
        btn_zoom_in.setActionCommand("btn_zoom_in");
        btn_zoom_in.addActionListener(this::onAction);
        pnl_01.add(btn_zoom_in);

        pnl_02.setLayout(new GridLayout(1, 0, 5, 0));

        btn_minimize.setText("Мінімізувати");
        btn_minimize.setActionCommand("btn_minimize");
        btn_minimize.addActionListener(this::onAction);
        pnl_02.add(btn_minimize);

        btn_maximize.setText("Максимізувати");
        btn_maximize.setActionCommand("btn_maximize");
        btn_maximize.addActionListener(this::onAction);
        pnl_02.add(btn_maximize);

        pnl_03.setLayout(new GridLayout(1, 0, 5, 0));

        btn_internal_fit.setText("Вписати");
        btn_internal_fit.setActionCommand("btn_internal_fit");
        btn_internal_fit.addActionListener(this::onAction);
        pnl_03.add(btn_internal_fit);

        btn_external_fit.setText("За розміром вікна");
        btn_external_fit.setActionCommand("btn_external_fit");
        btn_external_fit.addActionListener(this::onAction);
        pnl_03.add(btn_external_fit);

        pnl_04.setLayout(new GridLayout(1, 0, 5, 0));

        btn_center.setText("Центрувати");
        btn_center.setActionCommand("btn_center");
        btn_center.addActionListener(this::onAction);
        pnl_04.add(btn_center);

        btn_region_fit.setText("Масштабувати регіон");
        btn_region_fit.setActionCommand("btn_region_fit");
        btn_region_fit.addActionListener(this::onAction);
        pnl_04.add(btn_region_fit);

        btn_original.setText("Оригінальний розмір зображення");
        btn_original.setActionCommand("btn_original");
        btn_original.addActionListener(this::onAction);

        pnl_05.setLayout(new GridLayout(1, 0, 5, 0));

        fld_scale.setHorizontalAlignment(JTextField.CENTER);
        fld_scale.setActionCommand("fld_scale");
        fld_scale.setText(getRoundedValue(image_view.getImageScale()) + "%");
        fld_scale.addActionListener(this::onAction);
        pnl_05.add(fld_scale);

        btn_set_scale.setText("Задати масштаб");
        btn_set_scale.setActionCommand("btn_set_scale");
        btn_set_scale.addActionListener(this::onAction);
        pnl_05.add(btn_set_scale);

        jPanel1.setLayout(new GridLayout(1, 0, 5, 0));

        btn_rotate_90.setText("↶");
        btn_rotate_90.setToolTipText("Повернути на 90° проти годинникової стрілки");
        btn_rotate_90.setActionCommand("btn_rotate_90");
        btn_rotate_90.setMargin(new Insets(0, 0, 0, 0));
        btn_rotate_90.addActionListener(this::onAction);
        jPanel1.add(btn_rotate_90);

        btn_rotate_270.setText("↷");
        btn_rotate_270.setToolTipText("Повернути на 90° за годинниковою стрілкою");
        btn_rotate_270.setActionCommand("btn_rotate_270");
        btn_rotate_270.setMargin(new Insets(0, 0, 0, 0));
        btn_rotate_270.addActionListener(this::onAction);
        jPanel1.add(btn_rotate_270);

        btn_rotate_180.setText("↺");
        btn_rotate_180.setToolTipText("Повернути на 180°");
        btn_rotate_180.setActionCommand("btn_rotate_180");
        btn_rotate_180.setMargin(new Insets(0, 0, 0, 0));
        btn_rotate_180.addActionListener(this::onAction);
        jPanel1.add(btn_rotate_180);

        btn_flip_h.setText("⇆");
        btn_flip_h.setToolTipText("Відзеркалити горизонтально");
        btn_flip_h.setActionCommand("btn_flip_h");
        btn_flip_h.setMargin(new Insets(0, 0, 0, 0));
        btn_flip_h.addActionListener(this::onAction);
        jPanel1.add(btn_flip_h);

        btn_flip_v.setText("⇅");
        btn_flip_v.setToolTipText("Відзеркалити вертикально");
        btn_flip_v.setActionCommand("btn_flip_v");
        btn_flip_v.setMargin(new Insets(0, 0, 0, 0));
        btn_flip_v.addActionListener(this::onAction);
        jPanel1.add(btn_flip_v);

        increaseTextSize(6, btn_rotate_90, btn_rotate_270, btn_rotate_180, btn_flip_h, btn_flip_v);

        tab_pnl_01.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));

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

        bg_1.add(rb_zoom_fast);
        rb_zoom_fast.setText("Швидке масштабування");
        rb_zoom_fast.setActionCommand("rb_zoom_fast");
        rb_zoom_fast.setSelected(image_view.getImageScaleType() == SCALE_TYPE_FAST);
        rb_zoom_fast.addActionListener(this::onAction);

        bg_1.add(rb_zoom_smooth);
        rb_zoom_smooth.setText("Згладжене масштабування");
        rb_zoom_smooth.setActionCommand("rb_zoom_smooth");
        rb_zoom_smooth.setSelected(image_view.getImageScaleType() == SCALE_TYPE_SMOOTH);
        rb_zoom_smooth.addActionListener(this::onAction);

        jRadioButton1.setText("jRadioButton1");

        jRadioButton2.setText("jRadioButton2");

        jRadioButton3.setText("jRadioButton3");

        GroupLayout pnl_06Layout = new GroupLayout(pnl_06);
        pnl_06.setLayout(pnl_06Layout);
        pnl_06Layout.setHorizontalGroup(pnl_06Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_06Layout.createSequentialGroup()
                .addGroup(pnl_06Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_06Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pnl_06Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(cb_lmb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_cmb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_rmb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_wheel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sp_04)
                            .addComponent(cb_zmb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rb_zoom_fast, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rb_zoom_smooth, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(GroupLayout.Alignment.TRAILING, pnl_06Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator3)))
                .addGap(3, 3, 3))
            .addGroup(pnl_06Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_06Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_06Layout.setVerticalGroup(pnl_06Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_06Layout.createSequentialGroup()
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
                .addComponent(sp_04, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_zoom_fast)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_zoom_smooth)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pnl_01.addTab("Загальне", pnl_06);

        cb_grid.setText("Відображати сітку");
        cb_grid.setActionCommand("cb_grid");
        cb_grid.setSelected(image_view.isGridVisible());
        cb_grid.addActionListener(this::onAction);

        pnl_11.setLayout(new GridLayout(1, 0, 5, 0));

        fld_grid_light.setHorizontalAlignment(JTextField.CENTER);
        fld_grid_light.setActionCommand("fld_grid_light");
        fld_grid_light.setText(getColorValue(image_view.getGridLightColor()));
        fld_grid_light.addActionListener(this::onAction);
        pnl_11.add(fld_grid_light);

        btn_grid_light.setText("Задати світлий колір");
        btn_grid_light.setActionCommand("btn_grid_light");
        btn_grid_light.addActionListener(this::onAction);
        pnl_11.add(btn_grid_light);

        pnl_12.setLayout(new GridLayout(1, 0, 5, 0));

        fld_grid_dark.setHorizontalAlignment(JTextField.CENTER);
        fld_grid_dark.setActionCommand("fld_grid_dark");
        fld_grid_dark.setText(getColorValue(image_view.getGridDarkColor()));
        fld_grid_dark.addActionListener(this::onAction);
        pnl_12.add(fld_grid_dark);

        btn_grid_dark.setText("Задати темний колір");
        btn_grid_dark.setActionCommand("btn_grid_dark");
        btn_grid_dark.addActionListener(this::onAction);
        pnl_12.add(btn_grid_dark);

        pnl_13.setLayout(new GridLayout(1, 0, 5, 0));

        fld_grid_size.setHorizontalAlignment(JTextField.CENTER);
        fld_grid_size.setActionCommand("fld_grid_size");
        fld_grid_size.setText(image_view.getGridSize() + "px");
        fld_grid_size.addActionListener(this::onAction);
        pnl_13.add(fld_grid_size);

        btn_grid_size.setText("Задати розмір сітки");
        btn_grid_size.setActionCommand("btn_grid_size");
        btn_grid_size.addActionListener(this::onAction);
        pnl_13.add(btn_grid_size);

        GroupLayout pnl_07Layout = new GroupLayout(pnl_07);
        pnl_07.setLayout(pnl_07Layout);
        pnl_07Layout.setHorizontalGroup(pnl_07Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_07Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_07Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_grid, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_13, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_05)
                    .addComponent(pnl_12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_07Layout.setVerticalGroup(pnl_07Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_07Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_grid)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_05, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pnl_01.addTab("Сітка", pnl_07);

        bg_2.add(rb_magn_oval);
        rb_magn_oval.setText("Овальна лупа");
        rb_magn_oval.setActionCommand("rb_magn_oval");
        rb_magn_oval.setSelected(image_view.getZoomShapeType() == ZOOM_SHAPE_ELLIPSE);
        rb_magn_oval.addActionListener(this::onAction);

        bg_2.add(rb_magn_rect);
        rb_magn_rect.setText("Прямокутна лупа");
        rb_magn_rect.setActionCommand("rb_magn_rect");
        rb_magn_rect.setSelected(image_view.getZoomShapeType() == ZOOM_SHAPE_RECTANGLE);
        rb_magn_rect.addActionListener(this::onAction);

        pnl_14.setLayout(new GridLayout(1, 0, 5, 0));

        fld_magn_w.setHorizontalAlignment(JTextField.CENTER);
        fld_magn_w.setActionCommand("fld_magn_w");
        fld_magn_w.setText(image_view.getZoomArea().width + "px");
        fld_magn_w.addActionListener(this::onAction);
        pnl_14.add(fld_magn_w);

        btn_magn_w.setText("Задати ширину лупи");
        btn_magn_w.setActionCommand("btn_magn_w");
        btn_magn_w.addActionListener(this::onAction);
        pnl_14.add(btn_magn_w);

        pnl_15.setLayout(new GridLayout(1, 0, 5, 0));

        fld_magn_h.setHorizontalAlignment(JTextField.CENTER);
        fld_magn_h.setActionCommand("fld_magn_h");
        fld_magn_h.setText(image_view.getZoomArea().height + "px");
        fld_magn_h.addActionListener(this::onAction);
        pnl_15.add(fld_magn_h);

        btn_magn_h.setText("Задати висоту лупи");
        btn_magn_h.setActionCommand("btn_magn_h");
        btn_magn_h.addActionListener(this::onAction);
        pnl_15.add(btn_magn_h);

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

        pnl_19.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_zoom_border_w.setHorizontalAlignment(JTextField.CENTER);
        fld_f_zoom_border_w.setActionCommand("fld_f_zoom_border_w");
        fld_f_zoom_border_w.setText(image_view.getZoomFirstBorderWidth() + "px");
        fld_f_zoom_border_w.addActionListener(this::onAction);
        pnl_19.add(fld_f_zoom_border_w);

        btn_f_zoom_border_w.setText("Ширина I рамки лупи");
        btn_f_zoom_border_w.setActionCommand("btn_f_zoom_border_w");
        btn_f_zoom_border_w.addActionListener(this::onAction);
        pnl_19.add(btn_f_zoom_border_w);

        pnl_20.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_zoom_border_gap.setHorizontalAlignment(JTextField.CENTER);
        fld_f_zoom_border_gap.setActionCommand("fld_f_zoom_border_gap");
        fld_f_zoom_border_gap.setText(image_view.getZoomFirstBorderGap() + "px");
        fld_f_zoom_border_gap.addActionListener(this::onAction);
        pnl_20.add(fld_f_zoom_border_gap);

        btn_f_zoom_border_gap.setText("Відступ I рамки лупи");
        btn_f_zoom_border_gap.setActionCommand("btn_f_zoom_border_gap");
        btn_f_zoom_border_gap.addActionListener(this::onAction);
        pnl_20.add(btn_f_zoom_border_gap);

        pnl_21.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_zoom_border_color.setHorizontalAlignment(JTextField.CENTER);
        fld_f_zoom_border_color.setActionCommand("fld_f_zoom_border_color");
        fld_f_zoom_border_color.setText(getColorValue(image_view.getZoomFirstBorderColor()));
        fld_f_zoom_border_color.addActionListener(this::onAction);
        pnl_21.add(fld_f_zoom_border_color);

        btn_f_zoom_border_color.setText("Колір I рамки лупи");
        btn_f_zoom_border_color.setActionCommand("btn_f_zoom_border_color");
        btn_f_zoom_border_color.addActionListener(this::onAction);
        pnl_21.add(btn_f_zoom_border_color);

        GroupLayout pnl_16Layout = new GroupLayout(pnl_16);
        pnl_16.setLayout(pnl_16Layout);
        pnl_16Layout.setHorizontalGroup(pnl_16Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, pnl_16Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_16Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(pnl_21, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_f_zoom_border, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_19, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_20, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_16Layout.setVerticalGroup(pnl_16Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_16Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_f_zoom_border)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_19, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_20, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_21, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        tab_pnl_02.addTab("I рамка лупи", pnl_16);

        cb_s_zoom_border.setText("Відображати II рамку лупи");
        cb_s_zoom_border.setActionCommand("cb_s_zoom_border");
        cb_s_zoom_border.setSelected(image_view.isZoomSecondBorderVisible());
        cb_s_zoom_border.addActionListener(this::onAction);

        pnl_22.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_zoom_border_w.setHorizontalAlignment(JTextField.CENTER);
        fld_s_zoom_border_w.setActionCommand("fld_s_zoom_border_w");
        fld_s_zoom_border_w.setText(image_view.getZoomSecondBorderWidth() + "px");
        fld_s_zoom_border_w.addActionListener(this::onAction);
        pnl_22.add(fld_s_zoom_border_w);

        btn_s_zoom_border_w.setText("Ширина II рамки лупи");
        btn_s_zoom_border_w.setActionCommand("btn_s_zoom_border_w");
        btn_s_zoom_border_w.addActionListener(this::onAction);
        pnl_22.add(btn_s_zoom_border_w);

        pnl_23.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_zoom_border_gap.setHorizontalAlignment(JTextField.CENTER);
        fld_s_zoom_border_gap.setActionCommand("fld_s_zoom_border_gap");
        fld_s_zoom_border_gap.setText(image_view.getZoomSecondBorderGap() + "px");
        fld_s_zoom_border_gap.addActionListener(this::onAction);
        pnl_23.add(fld_s_zoom_border_gap);

        btn_s_zoom_border_gap.setText("Відступ II рамки лупи");
        btn_s_zoom_border_gap.setActionCommand("btn_s_zoom_border_gap");
        btn_s_zoom_border_gap.addActionListener(this::onAction);
        pnl_23.add(btn_s_zoom_border_gap);

        pnl_24.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_zoom_border_color.setHorizontalAlignment(JTextField.CENTER);
        fld_s_zoom_border_color.setActionCommand("fld_s_zoom_border_color");
        fld_s_zoom_border_color.setText(getColorValue(image_view.getZoomSecondBorderColor()));
        fld_s_zoom_border_color.addActionListener(this::onAction);
        pnl_24.add(fld_s_zoom_border_color);

        btn_s_zoom_border_color.setText("Колір II рамки лупи");
        btn_s_zoom_border_color.setActionCommand("btn_s_zoom_border_color");
        btn_s_zoom_border_color.addActionListener(this::onAction);
        pnl_24.add(btn_s_zoom_border_color);

        GroupLayout pnl_17Layout = new GroupLayout(pnl_17);
        pnl_17.setLayout(pnl_17Layout);
        pnl_17Layout.setHorizontalGroup(pnl_17Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_17Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_17Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_s_zoom_border, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_22, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_23, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_24, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_17Layout.setVerticalGroup(pnl_17Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_17Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_s_zoom_border)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_22, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_23, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_24, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        tab_pnl_02.addTab("II рамка лупи", pnl_17);

        cb_flip_zoom.setText("Перешкоджати виходу лупи за межі компонента");
        cb_flip_zoom.setActionCommand("cb_flip_zoom");
        cb_flip_zoom.setSelected(image_view.isInvertZoomOut());
        cb_flip_zoom.addActionListener(this::onAction);

        pnl_25.setLayout(new GridLayout(1, 0, 5, 0));

        fld_zoom_gap_w.setHorizontalAlignment(JTextField.CENTER);
        fld_zoom_gap_w.setActionCommand("fld_zoom_gap_w");
        fld_zoom_gap_w.setText(image_view.getZoomOffset().x + "px");
        fld_zoom_gap_w.addActionListener(this::onAction);
        pnl_25.add(fld_zoom_gap_w);

        btn_zoom_gap_w.setText("Зміщення по гор.");
        btn_zoom_gap_w.setActionCommand("btn_zoom_gap_w");
        btn_zoom_gap_w.addActionListener(this::onAction);
        pnl_25.add(btn_zoom_gap_w);

        pnl_26.setLayout(new GridLayout(1, 0, 5, 0));

        fld_zoom_gap_h.setHorizontalAlignment(JTextField.CENTER);
        fld_zoom_gap_h.setActionCommand("fld_zoom_gap_h");
        fld_zoom_gap_h.setText(image_view.getZoomOffset().y + "px");
        fld_zoom_gap_h.addActionListener(this::onAction);
        pnl_26.add(fld_zoom_gap_h);

        btn_zoom_gap_h.setText("Зміщення по верт.");
        btn_zoom_gap_h.setActionCommand("btn_zoom_gap_h");
        btn_zoom_gap_h.addActionListener(this::onAction);
        pnl_26.add(btn_zoom_gap_h);

        GroupLayout pnl_18Layout = new GroupLayout(pnl_18);
        pnl_18.setLayout(pnl_18Layout);
        pnl_18Layout.setHorizontalGroup(pnl_18Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_18Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_18Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_flip_zoom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_25, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_26, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_18Layout.setVerticalGroup(pnl_18Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_18Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_flip_zoom)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_25, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_26, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        tab_pnl_02.addTab("Зміщення лупи", pnl_18);

        GroupLayout pnl_08Layout = new GroupLayout(pnl_08);
        pnl_08.setLayout(pnl_08Layout);
        pnl_08Layout.setHorizontalGroup(pnl_08Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_08Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_08Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(rb_magn_oval, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_magn_rect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_06)
                    .addComponent(pnl_14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_07)
                    .addComponent(cb_magn_scale, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_08)
                    .addComponent(cb_hide_cursor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_09)
                    .addComponent(tab_pnl_02))
                .addGap(3, 3, 3))
        );
        pnl_08Layout.setVerticalGroup(pnl_08Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_08Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(rb_magn_oval)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_magn_rect)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_06, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_07, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_magn_scale, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_08, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_hide_cursor)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_09, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab_pnl_02)
                .addGap(3, 3, 3))
        );

        tab_pnl_01.addTab("Лупа", pnl_08);

        cb_additional_stroke.setText("Відображати додатковий штрих");
        cb_additional_stroke.setActionCommand("cb_additional_stroke");
        cb_additional_stroke.setSelected(image_view.isRegionAdditionalStroke());
        cb_additional_stroke.addActionListener(this::onAction);

        pnl_27.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_stroke_color.setHorizontalAlignment(JTextField.CENTER);
        fld_f_stroke_color.setActionCommand("fld_f_stroke_color");
        fld_f_stroke_color.setText(getColorValue(image_view.getRegionLightColor()));
        fld_f_stroke_color.addActionListener(this::onAction);
        pnl_27.add(fld_f_stroke_color);

        btn_f_stroke_color.setText("Колір осн. штриха");
        btn_f_stroke_color.setActionCommand("btn_f_stroke_color");
        btn_f_stroke_color.addActionListener(this::onAction);
        pnl_27.add(btn_f_stroke_color);

        pnl_28.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_stroke_color.setHorizontalAlignment(JTextField.CENTER);
        fld_s_stroke_color.setActionCommand("fld_s_stroke_color");
        fld_s_stroke_color.setText(getColorValue(image_view.getRegionDarkColor()));
        fld_s_stroke_color.addActionListener(this::onAction);
        pnl_28.add(fld_s_stroke_color);

        btn_s_stroke_color.setText("Колір дод. штриха");
        btn_s_stroke_color.setActionCommand("btn_s_stroke_color");
        btn_s_stroke_color.addActionListener(this::onAction);
        pnl_28.add(btn_s_stroke_color);

        pnl_29.setLayout(new GridLayout(1, 0, 5, 0));

        fld_stroke_w.setHorizontalAlignment(JTextField.CENTER);
        fld_stroke_w.setActionCommand("fld_stroke_w");
        fld_stroke_w.setText(String.format("%.0fpx", image_view.getRegionStroke().getLineWidth()));
        fld_stroke_w.addActionListener(this::onAction);
        pnl_29.add(fld_stroke_w);

        btn_stroke_w.setText("Задати шир. штриха");
        btn_stroke_w.setActionCommand("btn_stroke_w");
        btn_stroke_w.addActionListener(this::onAction);
        pnl_29.add(btn_stroke_w);

        btn_random_stroke.setText("Згенерувати новий варіант штриха");
        btn_random_stroke.setActionCommand("btn_random_stroke");
        btn_random_stroke.addActionListener(this::onAction);

        pnl_30.setLayout(new GridLayout(1, 0, 5, 0));

        fld_region_min.setHorizontalAlignment(JTextField.CENTER);
        fld_region_min.setActionCommand("fld_region_min");
        fld_stroke_w.setText(String.format("%.0fpx", image_view.getRegionStroke().getLineWidth()));
        fld_region_min.addActionListener(this::onAction);
        pnl_30.add(fld_region_min);

        btn_region_min.setText("Мін. розмір регіону");
        btn_region_min.setActionCommand("btn_region_min");
        btn_region_min.addActionListener(this::onAction);
        pnl_30.add(btn_region_min);

        GroupLayout pnl_09Layout = new GroupLayout(pnl_09);
        pnl_09.setLayout(pnl_09Layout);
        pnl_09Layout.setHorizontalGroup(pnl_09Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_09Layout.createSequentialGroup()
                .addGroup(pnl_09Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_09Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(pnl_09Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(cb_additional_stroke, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnl_28, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sp_10)
                            .addComponent(pnl_27, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sp_11)
                            .addComponent(pnl_29, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_random_stroke, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnl_09Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator2)))
                .addGap(3, 3, 3))
            .addGroup(pnl_09Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(pnl_30, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        pnl_09Layout.setVerticalGroup(pnl_09Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_09Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_additional_stroke)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_27, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_28, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_29, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_random_stroke)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_30, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pnl_01.addTab("Регіон", pnl_09);

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

        bg_3.add(rb_sb_always);
        rb_sb_always.setText("Постійно відображат смуги прокрутки");
        rb_sb_always.setActionCommand("rb_sb_always");
        rb_sb_always.setSelected(image_view.getVerticalScrollBarPolicy() == ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED && image_view.getHorizontalScrollBarPolicy() == ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rb_sb_always.addActionListener(this::onAction);

        bg_3.add(rb_sb_as_needed);
        rb_sb_as_needed.setText("Відображати смуги прокрутки за потреби");
        rb_sb_as_needed.setActionCommand("rb_sb_as_needed");
        rb_sb_as_needed.setSelected(image_view.getVerticalScrollBarPolicy() == ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED && image_view.getHorizontalScrollBarPolicy() == ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rb_sb_as_needed.addActionListener(this::onAction);

        bg_3.add(rb_sb_never);
        rb_sb_never.setText("Ніколи не відображати смуги прокрутки");
        rb_sb_never.setActionCommand("rb_sb_never");
        rb_sb_never.setSelected(image_view.getVerticalScrollBarPolicy() == ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER && image_view.getHorizontalScrollBarPolicy() == ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        rb_sb_never.addActionListener(this::onAction);

        GroupLayout pnl_10Layout = new GroupLayout(pnl_10);
        pnl_10.setLayout(pnl_10Layout);
        pnl_10Layout.setHorizontalGroup(pnl_10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_10Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_move_out, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_invert, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_12)
                    .addComponent(rb_sb_as_needed, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_sb_always, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rb_sb_never, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cb_zoom_out, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_10Layout.setVerticalGroup(pnl_10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_10Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_move_out)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_zoom_out)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_invert)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_sb_always)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_sb_as_needed)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rb_sb_never)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tab_pnl_01.addTab("Додатково", pnl_10);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(image_view, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(sp_03, GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_05, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_02, GroupLayout.Alignment.LEADING)
                    .addComponent(btn_original, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_open, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_01, GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_01, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_02, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_03, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_04, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tab_pnl_01)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
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
                        .addComponent(sp_01, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_01, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_02, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_03, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_04, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_original)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sp_02, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_05, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sp_03, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tab_pnl_01)))
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
    private ButtonGroup bg_1;
    private ButtonGroup bg_2;
    private ButtonGroup bg_3;
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
    private JPanel jPanel1;
    private JRadioButton jRadioButton1;
    private JRadioButton jRadioButton2;
    private JRadioButton jRadioButton3;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JPanel pnl_01;
    private JPanel pnl_02;
    private JPanel pnl_03;
    private JPanel pnl_04;
    private JPanel pnl_05;
    private JPanel pnl_06;
    private JPanel pnl_07;
    private JPanel pnl_08;
    private JPanel pnl_09;
    private JPanel pnl_10;
    private JPanel pnl_11;
    private JPanel pnl_12;
    private JPanel pnl_13;
    private JPanel pnl_14;
    private JPanel pnl_15;
    private JPanel pnl_16;
    private JPanel pnl_17;
    private JPanel pnl_18;
    private JPanel pnl_19;
    private JPanel pnl_20;
    private JPanel pnl_21;
    private JPanel pnl_22;
    private JPanel pnl_23;
    private JPanel pnl_24;
    private JPanel pnl_25;
    private JPanel pnl_26;
    private JPanel pnl_27;
    private JPanel pnl_28;
    private JPanel pnl_29;
    private JPanel pnl_30;
    private JRadioButton rb_magn_oval;
    private JRadioButton rb_magn_rect;
    private JRadioButton rb_sb_always;
    private JRadioButton rb_sb_as_needed;
    private JRadioButton rb_sb_never;
    private JRadioButton rb_zoom_fast;
    private JRadioButton rb_zoom_smooth;
    private JSeparator sp_01;
    private JSeparator sp_02;
    private JSeparator sp_03;
    private JSeparator sp_04;
    private JSeparator sp_05;
    private JSeparator sp_06;
    private JSeparator sp_07;
    private JSeparator sp_08;
    private JSeparator sp_09;
    private JSeparator sp_10;
    private JSeparator sp_11;
    private JSeparator sp_12;
    private JTabbedPane tab_pnl_01;
    private JTabbedPane tab_pnl_02;
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
