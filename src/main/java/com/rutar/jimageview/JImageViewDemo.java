package com.rutar.jimageview;

import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.plaf.metal.MetalIconFactory;

// ............................................................................

/**
 * Клас JImageViewDemo
 * @author Rutar_Andriy
 * 07.03.2024
 */

public class JImageViewDemo extends JFrame {

    /**
     * Creates new form JImageViewDemo
     */
    public JImageViewDemo() {
        initComponents();
    }

///////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        imageView = new JImageView();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageView, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageView, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }//GEN-END:initComponents

///////////////////////////////////////////////////////////////////////////////

    public static void main (String args[]) {

        EventQueue.invokeLater(() -> {
            new JImageViewDemo().setVisible(true);
        });
    }

///////////////////////////////////////////////////////////////////////////////

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JImageView imageView;
    // End of variables declaration//GEN-END:variables
}
