/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import com.itextpdf.text.Element;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Andrés
 */
public class Bill {
    
    Document document = new Document();
    
    
    
   
    // Método para obtener la fecha actual como un objeto Date
    public Date getCurrentDate() {
        return new Date(); // Devuelve la fecha y hora actuales
    }
    
    // Método para obtener la fecha actual como un objeto LocalDate (Java 8+)
    public LocalDate getCurrentLocalDate() {
        return LocalDate.now(); 
    }
    
    // Método para obtener la fecha actual como una cadena de texto formateada (Java 8+)
    public String getCurrentDateFormatted() {
        LocalDate currentDate = LocalDate.now(); // Obtiene la fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        return currentDate.format(formatter); // Devuelve la fecha formateada como una cadena de texto
    }
    
    public void setFormattedDate(JLabel label) {
        LocalDate currentDate = LocalDate.now(); // Obtiene la fecha actual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        label.setText(formattedDate); // Asigna la fecha formateada al JLabel
    }
    
    public long sumarValores(String archivo) {
        long suma = 0; // Inicializar la suma
        int contador = 0; // Contador para rastrear los renglones
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contador++; // Incrementar el contador de renglones
                
                // Solo sumar los valores cada 4 renglones, comenzando desde el tercero
                if (contador % 4 == 3) {
                    suma += Long.parseLong(linea.trim()); // Sumar el valor de la línea actual
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return suma;
    }   
    
    
    public String formatAsColombianPesos(long cantidad) {
        DecimalFormat df = new DecimalFormat("#,###"); // Formato con separadores de miles
        return "$" + df.format(cantidad); // Agrega el símbolo de pesos y devuelve la cantidad formateada
    }
    
    
    public String calcularIVA(long valor) {
        // Calcular el IVA (19%)
        double iva = valor * 0.19;

        // Redondear el resultado a 2 decimales y aproximar al valor más cercano
        BigDecimal ivaRedondeado = BigDecimal.valueOf(iva).setScale(2, RoundingMode.HALF_UP);

        // Devolver el resultado como cadena de texto
        return "$" + ivaRedondeado.toString();
    }
    
    
    public void generarPDF(JPanel panel, String rutaPDF) {
        try {
            // Obtener la imagen del panel
            BufferedImage bufferedImage = getImageFromPanel(panel);

            // Calcular las dimensiones del PDF en función del tamaño de la imagen
            float imageWidth = bufferedImage.getWidth();
            float imageHeight = bufferedImage.getHeight();

            // Agregar un margen adicional alrededor de la imagen
            float margin = 50; // Puedes ajustar este valor según sea necesario

            // Calcular el ancho y el alto del PDF
            float pdfWidth = imageWidth + 2 * margin;
            float pdfHeight = imageHeight + 2 * margin;

            // Crear el documento PDF con las dimensiones calculadas
            Document document = new Document(new Rectangle(pdfWidth, pdfHeight));

            // Inicializar el escritor PDF
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rutaPDF));

            // Abrir el documento
            document.open();

            // Ajustar el área de dibujo del documento PDF
            document.setMargins(0, 0, 0, 0);

            // Calcular la posición vertical para centrar la imagen
            float verticalPosition = (pdfHeight - imageHeight) / 2;

            // Convertir BufferedImage a Image
            Image pdfImage = Image.getInstance(toByteArray(bufferedImage));

            // Posicionar la imagen en el centro de la página y ajustar la posición vertical
            pdfImage.setAlignment(Element.ALIGN_CENTER);
            pdfImage.setAbsolutePosition(margin, verticalPosition);

            // Agregar la imagen al documento PDF
            document.add(pdfImage);

            // Cerrar el documento
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }



    private byte[] toByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

    private BufferedImage getImageFromPanel(JPanel panel) {
        BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        panel.paint(g2d);
        g2d.dispose();
        return image;
    }
    
    public void abrirPDF(String rutaPDF) {
        try {
            File pdfFile = new File(rutaPDF);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("Desktop is not supported");
                }
            } else {
                System.out.println("El archivo PDF no existe");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }   
}



