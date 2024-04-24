/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadData {
    public JTable cargarTabla() {
        // Ruta del archivo
        String archivo = "Array.txt";

        // Leer los datos del archivo y cargarlos en una lista de arrays de Strings
        List<String[]> datos = leerDatosDesdeArchivo(archivo);

        // Nombres de las columnas
        String[] columnas = {"Cantidad", "Clase", "Código", "Precio", "Referencia"};

        // Crear el modelo de tabla
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);

        // Agregar los datos al modelo de tabla
        for (String[] fila : datos) {
            String[] filaConCantidad = new String[fila.length + 1]; // Crear una nueva fila con espacio para la columna "Cantidad"
            filaConCantidad[0] = "1"; // Asignar un valor predeterminado para la columna "Cantidad"
            System.arraycopy(fila, 0, filaConCantidad, 1, fila.length); // Copiar los datos de la fila original
            modeloTabla.addRow(filaConCantidad); // Agregar la fila al modelo de tabla
        }

        // Crear la tabla con el modelo de datos
        JTable tabla = new JTable(modeloTabla);

        return tabla;
    }

    private List<String[]> leerDatosDesdeArchivo(String archivo) {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            List<String> filaActual = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) { // Ignorar líneas en blanco
                    filaActual.add(linea.trim());
                    if (filaActual.size() == 4) { // Si hemos recolectado 4 líneas, formamos una fila completa
                        datos.add(filaActual.toArray(new String[0])); // Agregamos la fila a los datos
                        filaActual.clear(); // Limpiamos la fila actual para empezar una nueva fila
                    }
                }
            }
            if (!filaActual.isEmpty()) { // Agregar la última fila si no está completa
                datos.add(filaActual.toArray(new String[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }
}