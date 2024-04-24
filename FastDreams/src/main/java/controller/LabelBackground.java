/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 *
 * @author Andrés
 */

public class LabelBackground extends JLabel {

    private Image logo;
    private Object interpolation;

    public LabelBackground() {
        super();
        // Establecer el tamaño predeterminado de la JLabel
        setPreferredSize(new Dimension(200, 200));
        // Cargar la imagen del fondo
        logo = new ImageIcon(getClass().getResource("/images/logo2.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar la imagen de fondo
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.drawImage(logo, 0, 0, getWidth(), getHeight(), null);
        g2d.dispose();
    }
    
    public void setInterpolation(Object interpolation) {
        this.interpolation = interpolation;

    }
}
