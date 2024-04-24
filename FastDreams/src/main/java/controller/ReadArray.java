/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadArray {

    private String RUTA_ARCHIVO;

    // Constructor que acepta la ruta del archivo como parámetro
    public ReadArray(String rutaArchivo) {
        this.RUTA_ARCHIVO = rutaArchivo;
    }

    public void borrarContenidoArchivo() {
        try {
            System.out.println("Buscando el archivo en la ruta: " + RUTA_ARCHIVO);
            BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO));
            writer.write(""); // Sobrescribe el contenido del archivo con una cadena vacía
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirDatosEnArchivo(Object[] data) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true)); // true para que añada al final del archivo
            for (Object value : data) {
                writer.write(value.toString());
                writer.newLine(); // Agrega una nueva línea después de cada dato
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer el contenido del archivo y devolverlo como un arreglo de objetos
    public Object[] leerArchivo() {
        List<Object> contenido = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Verificar si la línea no está vacía antes de agregarla
                if (!linea.trim().isEmpty()) {
                    contenido.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido.toArray();
    }

    // Método para contar el número de elementos en el archivo
    public int contarElementos() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Incrementar el contador si la línea no está vacía
                if (!linea.trim().isEmpty()) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    // Método para verificar si el archivo existe en la ruta especificada
    public boolean existeArchivo() {
        File archivo = new File(RUTA_ARCHIVO);
        return archivo.exists();
    }
}
