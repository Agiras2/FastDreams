/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ButtonController;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import controller.Buttons;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Panel_2;
import controller.ReadArray;


/**
 *
 * @author Andrés
 */
public class Panel_1 extends javax.swing.JFrame {
    
    
    PanelBackground background = new PanelBackground();
    ReadArray readArray = new ReadArray("Name.txt");

    
    /**
     * Creates new form View
     */
    public Panel_1() {
        initComponents();
        
        this.setContentPane(background);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        initComponents();
        
        Buttons buttonController1 = new Buttons();
        ButtonController buttonController = new ButtonController();
        buttonController.initializeButton(btn1, jTextField1);
        btn1.setEnabled(false);

        btn1.setIcon(buttonController1.setIcono("/images/Botton 1.png", btn1));
        btn1.setPressedIcon(buttonController1.setPressedIcon1("/images/Botton 1.png", btn1, 10, 10));
        jTextField1.setBackground(new Color(0, 0, 0, 0));
        jTextField1.repaint();

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readArray.borrarContenidoArchivo();
                // Manejar el evento del botón
                String nombre = jTextField1.getText();
                
                // Guardar el nombre en el archivo Name.txt
                readArray.escribirDatosEnArchivo(new Object[] { nombre });

                // Realizar las operaciones necesarias con el nombre
                // Por ejemplo, abrir otro JFrame
                Panel_2 panel2 = new Panel_2();
                panel2.setVisible(true);
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

        jTextField1 = new javax.swing.JTextField();
        btn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1420, 805));
        setPreferredSize(new java.awt.Dimension(1420, 805));

        jTextField1.setBackground(new java.awt.Color(60, 63, 65));
        jTextField1.setFont(new java.awt.Font("Corbel", 2, 24)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setToolTipText("");
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        btn1.setBorderPainted(false);
        btn1.setContentAreaFilled(false);
        btn1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn1.setMaximumSize(new java.awt.Dimension(72, 72));
        btn1.setMinimumSize(new java.awt.Dimension(72, 72));
        btn1.setPreferredSize(new java.awt.Dimension(72, 72));
        btn1.setRequestFocusEnabled(false);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(569, 569, 569)
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(549, 549, 549)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(352, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn1ActionPerformed

    
    
    public class PanelBackground extends JPanel { //Se deja aca en View debido a su simplicidad
    
    private Image image;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
       
        if (image == null) {
            image = new ImageIcon(getClass().getResource("/images/p1 texto.png")).getImage();
        }
        
        // Escalar la imagen con mejor calidad
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g2d.dispose();
    }
}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
