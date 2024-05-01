package com.rutar.jimageview;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

import static com.rutar.jimageview.JImageViewUtils.*;

// ............................................................................

/**
 * Клас JImageViewDemo
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageViewDemo extends JFrame {

///////////////////////////////////////////////////////////////////////////////

public JImageViewDemo() { initComponents(); }

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
        pnl_17 = new JPanel();
        cb_s_zoom_border = new JCheckBox();
        pnl_21 = new JPanel();
        fld_s_zoom_border_w = new JTextField();
        btn_s_zoom_border_w = new JButton();
        pnl_22 = new JPanel();
        fld_s_zoom_border_gap = new JTextField();
        btn_s_zoom_border_gap = new JButton();
        pnl_18 = new JPanel();
        cb_flip_zoom = new JCheckBox();
        pnl_23 = new JPanel();
        fld_zoom_gap_w = new JTextField();
        btn_zoom_gap_w = new JButton();
        pnl_24 = new JPanel();
        fld_zoom_gap_h = new JTextField();
        btn_zoom_gap_h = new JButton();
        pnl_09 = new JPanel();
        cb_additional_stroke = new JCheckBox();
        sp_10 = new JSeparator();
        pnl_25 = new JPanel();
        fld_f_stroke_color = new JTextField();
        btn_f_stroke_color = new JButton();
        pnl_26 = new JPanel();
        fld_s_stroke_color = new JTextField();
        btn_s_stroke_color = new JButton();
        sp_11 = new JSeparator();
        pnl_27 = new JPanel();
        fld_stroke_w = new JTextField();
        btn_stroke_w = new JButton();
        btn_random_stroke = new JButton();
        pnl_10 = new JPanel();
        cb_move_out = new JCheckBox();
        cb_zoom_out = new JCheckBox();
        cb_invert = new JCheckBox();
        sp_12 = new JSeparator();
        rb_sb_always = new JRadioButton();
        rb_sb_as_needed = new JRadioButton();
        rb_sb_never = new JRadioButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        image_view.setGridVisible(false);

        btn_open.setText("Вибрати зображення");
        btn_open.setActionCommand("btn_open");
        btn_open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        pnl_01.setLayout(new GridLayout(1, 0, 5, 0));

        btn_zoom_out.setText("Зменшити");
        btn_zoom_out.setActionCommand("btn_zoom_out");
        btn_zoom_out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_01.add(btn_zoom_out);

        btn_zoom_in.setText("Збільшити");
        btn_zoom_in.setActionCommand("btn_zoom_in");
        btn_zoom_in.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_01.add(btn_zoom_in);

        pnl_02.setLayout(new GridLayout(1, 0, 5, 0));

        btn_minimize.setText("Мінімізувати");
        btn_minimize.setActionCommand("btn_minimize");
        btn_minimize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_02.add(btn_minimize);

        btn_maximize.setText("Максимізувати");
        btn_maximize.setActionCommand("btn_maximize");
        btn_maximize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_02.add(btn_maximize);

        pnl_03.setLayout(new GridLayout(1, 0, 5, 0));

        btn_internal_fit.setText("Вписати");
        btn_internal_fit.setActionCommand("btn_internal_fit");
        btn_internal_fit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_03.add(btn_internal_fit);

        btn_external_fit.setText("За розміром вікна");
        btn_external_fit.setActionCommand("btn_external_fit");
        btn_external_fit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_03.add(btn_external_fit);

        pnl_04.setLayout(new GridLayout(1, 0, 5, 0));

        btn_center.setText("Центрувати");
        btn_center.setActionCommand("btn_center");
        btn_center.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_04.add(btn_center);

        btn_region_fit.setText("Масштабувати регіон");
        btn_region_fit.setActionCommand("btn_region_fit");
        btn_region_fit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_04.add(btn_region_fit);

        btn_original.setText("Оригінальний розмір зображення");
        btn_original.setActionCommand("btn_original");
        btn_original.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        pnl_05.setLayout(new GridLayout(1, 0, 5, 0));

        fld_scale.setHorizontalAlignment(JTextField.CENTER);
        fld_scale.setActionCommand("fld_scale");
        fld_scale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_05.add(fld_scale);

        btn_set_scale.setText("Задати масштаб");
        btn_set_scale.setActionCommand("btn_set_scale");
        btn_set_scale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_05.add(btn_set_scale);

        tab_pnl_01.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));

        cb_lmb.setText("Переміщувати зображення лівою клавішею миші");
        cb_lmb.setActionCommand("cb_lmb");
        cb_lmb.setSelected(image_view.isLMBEnable());
        cb_lmb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        cb_cmb.setText("Змінювати вигляд середньою клавішею миші");
        cb_cmb.setActionCommand("cb_cmb");
        image_view.isCMBEnable();
        cb_cmb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        cb_rmb.setText("Збільшувати зображення правою клавішею миші");
        cb_rmb.setActionCommand("cb_rmb");
        image_view.isRMBEnable();
        cb_rmb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        cb_wheel.setText("Масштабувати зображення колесиком миші");
        cb_wheel.setActionCommand("cb_wheel");
        image_view.isWheelEnable();
        cb_wheel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        cb_zmb.setText("Масштабувати регіон (ЛКМ + ПКМ)");
        cb_zmb.setActionCommand("cb_zmb");
        cb_zmb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        bg_1.add(rb_zoom_fast);
        rb_zoom_fast.setSelected(true);
        rb_zoom_fast.setText("Швидке масштабування");
        rb_zoom_fast.setActionCommand("rb_zoom_fast");
        rb_zoom_fast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        bg_1.add(rb_zoom_smooth);
        rb_zoom_smooth.setText("Згладжене масштабування");
        rb_zoom_smooth.setActionCommand("rb_zoom_smooth");
        rb_zoom_smooth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        GroupLayout pnl_06Layout = new GroupLayout(pnl_06);
        pnl_06.setLayout(pnl_06Layout);
        pnl_06Layout.setHorizontalGroup(pnl_06Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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
                    .addComponent(rb_zoom_smooth, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pnl_01.addTab("Загальне", pnl_06);

        cb_grid.setText("Відображати сітку");
        cb_grid.setActionCommand("cb_grid");
        cb_grid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        pnl_11.setLayout(new GridLayout(1, 0, 5, 0));

        fld_grid_light.setHorizontalAlignment(JTextField.CENTER);
        fld_grid_light.setActionCommand("fld_grid_light");
        fld_grid_light.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_11.add(fld_grid_light);

        btn_grid_light.setText("Задати світлий колір");
        btn_grid_light.setActionCommand("btn_grid_light");
        btn_grid_light.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_11.add(btn_grid_light);

        pnl_12.setLayout(new GridLayout(1, 0, 5, 0));

        fld_grid_dark.setHorizontalAlignment(JTextField.CENTER);
        fld_grid_dark.setActionCommand("fld_grid_dark");
        fld_grid_dark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_12.add(fld_grid_dark);

        btn_grid_dark.setText("Задати темний колір");
        btn_grid_dark.setActionCommand("btn_grid_dark");
        btn_grid_dark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_12.add(btn_grid_dark);

        pnl_13.setLayout(new GridLayout(1, 0, 5, 0));

        fld_grid_size.setHorizontalAlignment(JTextField.CENTER);
        fld_grid_size.setActionCommand("fld_grid_size");
        fld_grid_size.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_13.add(fld_grid_size);

        btn_grid_size.setText("Задати розмір сітки");
        btn_grid_size.setActionCommand("btn_grid_size");
        btn_grid_size.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
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
        rb_magn_oval.setSelected(true);
        rb_magn_oval.setText("Овальна лупа");
        rb_magn_oval.setActionCommand("rb_magn_oval");
        rb_magn_oval.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        bg_2.add(rb_magn_rect);
        rb_magn_rect.setText("Прямокутна лупа");
        rb_magn_rect.setActionCommand("rb_magn_rect");
        rb_magn_rect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        pnl_14.setLayout(new GridLayout(1, 0, 5, 0));

        fld_magn_w.setHorizontalAlignment(JTextField.CENTER);
        fld_magn_w.setActionCommand("fld_magn_w");
        fld_magn_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_14.add(fld_magn_w);

        btn_magn_w.setText("Задати ширину лупи");
        btn_magn_w.setActionCommand("btn_magn_w");
        btn_magn_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_14.add(btn_magn_w);

        pnl_15.setLayout(new GridLayout(1, 0, 5, 0));

        fld_magn_h.setHorizontalAlignment(JTextField.CENTER);
        fld_magn_h.setActionCommand("fld_magn_h");
        fld_magn_h.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_15.add(fld_magn_h);

        btn_magn_h.setText("Задати висоту лупи");
        btn_magn_h.setActionCommand("btn_magn_h");
        btn_magn_h.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_15.add(btn_magn_h);

        cb_magn_scale.setMaximumRowCount(9);
        cb_magn_scale.setModel(new DefaultComboBoxModel<>(new String[] { "Масштаб лупи x1.25", "Масштаб лупи x1.50", "Масштаб лупи x1.75", "Масштаб лупи x2.00", "Масштаб лупи x2.25", "Масштаб лупи x2.50", "Масштаб лупи x3.00", "Масштаб лупи x4.00", "Масштаб лупи x5.00" }));
        cb_magn_scale.setActionCommand("cb_magn_scale");
        cb_magn_scale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        cb_hide_cursor.setText("Приховувати курсор при збільшенні");
        cb_hide_cursor.setActionCommand("cb_hide_cursor");
        cb_hide_cursor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        cb_f_zoom_border.setText("Відображати I рамку лупи");
        cb_f_zoom_border.setActionCommand("cb_f_zoom_border");
        cb_f_zoom_border.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        pnl_19.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_zoom_border_w.setHorizontalAlignment(JTextField.CENTER);
        fld_f_zoom_border_w.setActionCommand("fld_f_zoom_border_w");
        fld_f_zoom_border_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_19.add(fld_f_zoom_border_w);

        btn_f_zoom_border_w.setText("Ширина I рамки лупи");
        btn_f_zoom_border_w.setActionCommand("btn_f_zoom_border_w");
        btn_f_zoom_border_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_19.add(btn_f_zoom_border_w);

        pnl_20.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_zoom_border_gap.setHorizontalAlignment(JTextField.CENTER);
        fld_f_zoom_border_gap.setActionCommand("fld_f_zoom_border_gap");
        fld_f_zoom_border_gap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_20.add(fld_f_zoom_border_gap);

        btn_f_zoom_border_gap.setText("Відступ I рамки лупи");
        btn_f_zoom_border_gap.setActionCommand("btn_f_zoom_border_gap");
        btn_f_zoom_border_gap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_20.add(btn_f_zoom_border_gap);

        GroupLayout pnl_16Layout = new GroupLayout(pnl_16);
        pnl_16.setLayout(pnl_16Layout);
        pnl_16Layout.setHorizontalGroup(pnl_16Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_16Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_16Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_f_zoom_border, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_19, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_20, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(3, 3, 3))
        );

        tab_pnl_02.addTab("I рамка лупи", pnl_16);

        cb_s_zoom_border.setText("Відображати II рамку лупи");
        cb_s_zoom_border.setActionCommand("cb_s_zoom_border");
        cb_s_zoom_border.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        pnl_21.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_zoom_border_w.setHorizontalAlignment(JTextField.CENTER);
        fld_s_zoom_border_w.setActionCommand("fld_s_zoom_border_w");
        fld_s_zoom_border_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_21.add(fld_s_zoom_border_w);

        btn_s_zoom_border_w.setText("Ширина II рамки лупи");
        btn_s_zoom_border_w.setActionCommand("btn_s_zoom_border_w");
        btn_s_zoom_border_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_21.add(btn_s_zoom_border_w);

        pnl_22.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_zoom_border_gap.setHorizontalAlignment(JTextField.CENTER);
        fld_s_zoom_border_gap.setActionCommand("fld_s_zoom_border_gap");
        fld_s_zoom_border_gap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_22.add(fld_s_zoom_border_gap);

        btn_s_zoom_border_gap.setText("Відступ II рамки лупи");
        btn_s_zoom_border_gap.setActionCommand("btn_s_zoom_border_gap");
        btn_s_zoom_border_gap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_22.add(btn_s_zoom_border_gap);

        GroupLayout pnl_17Layout = new GroupLayout(pnl_17);
        pnl_17.setLayout(pnl_17Layout);
        pnl_17Layout.setHorizontalGroup(pnl_17Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_17Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_17Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_s_zoom_border, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_21, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_22, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_17Layout.setVerticalGroup(pnl_17Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_17Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_s_zoom_border)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_21, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_22, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        tab_pnl_02.addTab("II рамка лупи", pnl_17);

        cb_flip_zoom.setText("Перешкоджати виходу лупи за межі компонента");
        cb_flip_zoom.setActionCommand("cb_flip_zoom");
        cb_flip_zoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        pnl_23.setLayout(new GridLayout(1, 0, 5, 0));

        fld_zoom_gap_w.setHorizontalAlignment(JTextField.CENTER);
        fld_zoom_gap_w.setActionCommand("fld_zoom_gap_w");
        fld_zoom_gap_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_23.add(fld_zoom_gap_w);

        btn_zoom_gap_w.setText("Зміщення по гор.");
        btn_zoom_gap_w.setActionCommand("btn_zoom_gap_w");
        btn_zoom_gap_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_23.add(btn_zoom_gap_w);

        pnl_24.setLayout(new GridLayout(1, 0, 5, 0));

        fld_zoom_gap_h.setHorizontalAlignment(JTextField.CENTER);
        fld_zoom_gap_h.setActionCommand("fld_zoom_gap_h");
        fld_zoom_gap_h.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_24.add(fld_zoom_gap_h);

        btn_zoom_gap_h.setText("Зміщення по верт.");
        btn_zoom_gap_h.setActionCommand("btn_zoom_gap_h");
        btn_zoom_gap_h.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_24.add(btn_zoom_gap_h);

        GroupLayout pnl_18Layout = new GroupLayout(pnl_18);
        pnl_18.setLayout(pnl_18Layout);
        pnl_18Layout.setHorizontalGroup(pnl_18Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_18Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_18Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_flip_zoom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_23, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_24, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_18Layout.setVerticalGroup(pnl_18Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_18Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_flip_zoom)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_23, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_24, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(tab_pnl_02, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        tab_pnl_01.addTab("Лупа", pnl_08);

        cb_additional_stroke.setText("Відображати додатковий штрих");
        cb_additional_stroke.setActionCommand("cb_additional_stroke");
        cb_additional_stroke.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        pnl_25.setLayout(new GridLayout(1, 0, 5, 0));

        fld_f_stroke_color.setHorizontalAlignment(JTextField.CENTER);
        fld_f_stroke_color.setActionCommand("fld_f_stroke_color");
        fld_f_stroke_color.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_25.add(fld_f_stroke_color);

        btn_f_stroke_color.setText("Колір осн. штриха");
        btn_f_stroke_color.setActionCommand("btn_f_stroke_color");
        btn_f_stroke_color.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_25.add(btn_f_stroke_color);

        pnl_26.setLayout(new GridLayout(1, 0, 5, 0));

        fld_s_stroke_color.setHorizontalAlignment(JTextField.CENTER);
        fld_s_stroke_color.setActionCommand("fld_s_stroke_color");
        fld_s_stroke_color.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_26.add(fld_s_stroke_color);

        btn_s_stroke_color.setText("Колір дод. штриха");
        btn_s_stroke_color.setActionCommand("btn_s_stroke_color");
        btn_s_stroke_color.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_26.add(btn_s_stroke_color);

        pnl_27.setLayout(new GridLayout(1, 0, 5, 0));

        fld_stroke_w.setHorizontalAlignment(JTextField.CENTER);
        fld_stroke_w.setActionCommand("fld_stroke_w");
        fld_stroke_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_27.add(fld_stroke_w);

        btn_stroke_w.setText("Задати шир. штриха");
        btn_stroke_w.setActionCommand("btn_stroke_w");
        btn_stroke_w.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });
        pnl_27.add(btn_stroke_w);

        btn_random_stroke.setText("Згенерувати новий варіант штриха");
        btn_random_stroke.setActionCommand("btn_random_stroke");
        btn_random_stroke.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        GroupLayout pnl_09Layout = new GroupLayout(pnl_09);
        pnl_09.setLayout(pnl_09Layout);
        pnl_09Layout.setHorizontalGroup(pnl_09Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_09Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnl_09Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cb_additional_stroke, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnl_26, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_10)
                    .addComponent(pnl_25, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sp_11)
                    .addComponent(pnl_27, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_random_stroke, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        pnl_09Layout.setVerticalGroup(pnl_09Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pnl_09Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(cb_additional_stroke)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_25, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_26, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_27, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_random_stroke)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_pnl_01.addTab("Регіон", pnl_09);

        cb_move_out.setText("Переміщувати зображення за межею видимості");
        cb_move_out.setActionCommand("cb_move_out");
        cb_move_out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        cb_zoom_out.setText("Відображати лупу за межею видимості");
        cb_zoom_out.setActionCommand("cb_zoom_out");
        cb_zoom_out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        cb_invert.setText("Інвертувати колесико миші");
        cb_invert.setActionCommand("cb_invert");
        cb_invert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        bg_3.add(rb_sb_always);
        rb_sb_always.setText("Постійно відображат смуги прокрутки");
        rb_sb_always.setActionCommand("rb_sb_always");
        rb_sb_always.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        bg_3.add(rb_sb_as_needed);
        rb_sb_as_needed.setSelected(true);
        rb_sb_as_needed.setText("Відображати смуги прокрутки за потреби");
        rb_sb_as_needed.setActionCommand("rb_sb_as_needed");
        rb_sb_as_needed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

        bg_3.add(rb_sb_never);
        rb_sb_never.setText("Ніколи не відображати смуги прокрутки");
        rb_sb_never.setActionCommand("rb_sb_never");
        rb_sb_never.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                onAction(evt);
            }
        });

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
                .addComponent(image_view, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addGap(4, 4, 4)
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
                    .addComponent(tab_pnl_01))
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
        //System.out.println(evt.getActionCommand());
        
        switch (evt.getActionCommand()) {

            case "btn_open" ->
                { File file = new File("/home/rutar/test_2.jpg");
                  BufferedImage image = getImageQuickly(file);
                  image_view.setImage(image); }

            case "btn_zoom_in" ->
                { image_view.zoomIn(null);
                  fld_scale.setText(getRoundedValue
                                   (image_view.getImageScale())); }
            case "btn_zoom_out" ->
                { image_view.zoomOut(null);
                  fld_scale.setText(getRoundedValue
                                   (image_view.getImageScale())); }
                
            case "btn_minimize" ->
                { image_view.minimize();
                  fld_scale.setText(getRoundedValue
                                   (image_view.getImageScale())); }
            case "btn_maximize" ->
                { image_view.maximize();
                  fld_scale.setText(getRoundedValue
                                   (image_view.getImageScale())); }
            case "btn_internal_fit" ->
                { image_view.fitInternal();
                  fld_scale.setText(getRoundedValue
                                   (image_view.getImageScale())); }
            case "btn_external_fit" ->
                { image_view.fitExternal();
                  fld_scale.setText(getRoundedValue
                                   (image_view.getImageScale())); }
            case "btn_original" ->
                { image_view.zoomToOriginal();
                  fld_scale.setText(getRoundedValue
                                   (image_view.getImageScale())); }
                
            case "btn_region_fit" -> { image_view.setRegion(); }
            case "btn_center"     -> { image_view.center();    }
                
            case "btn_set_scale", "fld_scale" ->
                { float newScale = Float.parseFloat(fld_scale.getText());
                  image_view.setImageScale(newScale);
                  fld_scale.setText(getRoundedValue
                                   (image_view.getImageScale())); }
        }
        
    }//GEN-LAST:event_onAction

///////////////////////////////////////////////////////////////////////////////

private String getRoundedValue (float value) {
    return String.format("%.2f", value);
}

///////////////////////////////////////////////////////////////////////////////

public static void main (String args[]) {

    EventQueue.invokeLater(() -> {
        new JImageViewDemo().setVisible(true);
    });
}

///////////////////////////////////////////////////////////////////////////////

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ButtonGroup bg_1;
    private ButtonGroup bg_2;
    private ButtonGroup bg_3;
    private JButton btn_center;
    private JButton btn_external_fit;
    private JButton btn_f_stroke_color;
    private JButton btn_f_zoom_border_gap;
    private JButton btn_f_zoom_border_w;
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
    private JButton btn_s_stroke_color;
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
    private JTextField fld_f_zoom_border_gap;
    private JTextField fld_f_zoom_border_w;
    private JTextField fld_grid_dark;
    private JTextField fld_grid_light;
    private JTextField fld_grid_size;
    private JTextField fld_magn_h;
    private JTextField fld_magn_w;
    private JTextField fld_s_stroke_color;
    private JTextField fld_s_zoom_border_gap;
    private JTextField fld_s_zoom_border_w;
    private JTextField fld_scale;
    private JTextField fld_stroke_w;
    private JTextField fld_zoom_gap_h;
    private JTextField fld_zoom_gap_w;
    private JImageView image_view;
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

// Кінець класу JImageViewDemo ////////////////////////////////////////////////

}
