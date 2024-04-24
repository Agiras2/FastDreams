/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Buttons {
    
    public Icon setIcono(String url, JButton button){
        ImageIcon icon = new ImageIcon(getClass().getResource(url));
        Image originalImage = icon.getImage();
        
        int width = button.getWidth();
        int height = button.getHeight();
        
        // Crear una nueva imagen con mejor calidad
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        
        // Convertir la imagen escalada en un icono
        return new ImageIcon(scaledImage);
    }
    
    public Icon setPressedIcon1(String url, JButton button, int width, int height){
        ImageIcon icon = new ImageIcon(getClass().getResource(url));
        Image originalImage = icon.getImage();
        
        int width2 = button.getWidth() - width;
        int height2 = button.getHeight() - height;
        
        // Crear una nueva imagen con mejor calidad
        Image scaledImage = originalImage.getScaledInstance(width2, height2, Image.SCALE_SMOOTH);
        
        // Convertir la imagen escalada en un icono
        return new ImageIcon(scaledImage);
    }
}
