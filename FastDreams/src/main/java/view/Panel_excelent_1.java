/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.Buttons;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Andrés
 */
public class Panel_excelent_1 extends javax.swing.JFrame {
    
    PanelBackground background = new PanelBackground();

    /**
     * Creates new form Panel_normal_1
     */
    public Panel_excelent_1() {
        initComponents();
        
        this.setContentPane(background);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        initComponents();
        
        Buttons buttonController = new Buttons();
        btn2.setIcon(buttonController.setIcono("/images/return.png", btn2));
        btn2.setPressedIcon(buttonController.setPressedIcon1("/images/return.png", btn2, 10, 10));
        btn3.setIcon(buttonController.setIcono("/images/next.png", btn3));
        btn3.setPressedIcon(buttonController.setPressedIcon1("/images/next.png", btn3, 10, 10));
        
        
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel_3 panel3 = new Panel_3();
                panel3.setVisible(true);
                dispose(); // Cierra el JFrame actual
            }
        });
        
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel_excelent_2 paneln2 = new Panel_excelent_2();
                paneln2.setVisible(true);
                dispose(); // Cierra el JFrame actual
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1420, 805));
        setSize(new java.awt.Dimension(1420, 805));

        btn2.setBorderPainted(false);
        btn2.setContentAreaFilled(false);

        btn3.setBorderPainted(false);
        btn3.setContentAreaFilled(false);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(400, 400, 400)
                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(407, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(696, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn3ActionPerformed

    
    public class PanelBackground extends JPanel { //Se deja aca en View debido a su simplicidad
    
        private Image image;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);


            if (image == null) {
                image = new ImageIcon(getClass().getResource("/images/final 3 p1 text.png")).getImage();
            }

            // Escalar la imagen con mejor calidad
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            g2d.dispose();
        }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    // End of variables declaration//GEN-END:variables
}
