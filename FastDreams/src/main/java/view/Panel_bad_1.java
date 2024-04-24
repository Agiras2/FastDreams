/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.Buttons;
import controller.ReadArray;
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
public class Panel_bad_1 extends javax.swing.JFrame {
    
    PanelBackground background = new PanelBackground();
    ReadArray readArray = new ReadArray("Name.txt");

    /**
     * Creates new form Panel_bad_1
     */
    public Panel_bad_1() {
        initComponents();
        
        this.setContentPane(background);
        this.setLocationRelativeTo(null);
        this.setResizable(false);;
        
        initComponents();
        
        Buttons buttonController = new Buttons();
        btn2.setIcon(buttonController.setIcono("/images/return.png", btn2));
        btn2.setPressedIcon(buttonController.setPressedIcon1("/images/return.png", btn2, 10, 10));
        
        Object[] data = readArray.leerArchivo();
        if (data.length > 0) {
            jLabel1.setText(data[0].toString());
        } else {
            jLabel1.setText("No hay datos disponibles");
        }
        
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Panel_3 panel3 = new Panel_3();
                panel3.setVisible(true);
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
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1420, 805));
        setMinimumSize(new java.awt.Dimension(1420, 805));
        setPreferredSize(new java.awt.Dimension(1420, 805));
        setSize(new java.awt.Dimension(1420, 805));

        btn2.setBorderPainted(false);
        btn2.setContentAreaFilled(false);

        jLabel1.setFont(new java.awt.Font("MS UI Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(96, 26, 75));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(612, Short.MAX_VALUE)
                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(590, 590, 590))
            .addGroup(layout.createSequentialGroup()
                .addGap(455, 455, 455)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(479, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public class PanelBackground extends JPanel { //Se deja aca en View debido a su simplicidad
    
    private Image image;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
       
        if (image == null) {
            image = new ImageIcon(getClass().getResource("/images/final 1 p1 text.png")).getImage();
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
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}