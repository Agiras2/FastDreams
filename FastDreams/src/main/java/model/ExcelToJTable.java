/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.Panel_normal_2;
import view.Panel_normal_3;
import view.Panel_excelent_2;
import view.Panel_excelent_3;
import controller.ReadArray;

public class ExcelToJTable extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;
    private JTextField codeField;
    private Object[][] originalData; // Copia de respaldo de los datos originales
    private int itemCount = 0; // Contador de elementos agregados
    private Panel_normal_2 panel2;
    private Panel_excelent_2 panele2;
    private Object[] lastFoundData;
    private ReadArray array;
    private JButton myButton; // Nuevo botón
    private JButton getCodeButton; // Botón "Obtener datos por código"

    public ExcelToJTable() {
        array = new ReadArray("Array.txt"); // Inicializa el objeto array
    }

    public ExcelToJTable(Panel_normal_2 panel2) {
        super("Import Excel to JTable");
        this.panel2 = panel2;
        this.array = new ReadArray("Array.txt");

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Clase");
        model.addColumn("Código");
        model.addColumn("Precio");
        model.addColumn("Referencia1");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Buscar");
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Buscar: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Verificar si se han agregado al menos dos elementos antes de cerrar la ventana
                if (itemCount < 2) {
                    // Si no se han agregado suficientes elementos, mostrar un mensaje de advertencia
                    JOptionPane.showMessageDialog(null, "Debe agregar al menos dos elementos antes de cerrar la ventana.");
                    // Cancelar el evento de cierre de la ventana
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                } else {
                    // Si se han agregado suficientes elementos, cerrar la ventana
                    dispose();
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "Utiliza el botón 'Agregar' para cerrar la ventana.");
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().toLowerCase();
                if (!searchTerm.isEmpty()) {
                    filterTable(searchTerm);
                } else {
                    resetTable();
                }
            }
        });

        codeField = new JTextField(20);
        getCodeButton = new JButton("Obtener datos por código"); // Inicializamos el botón aquí
        JPanel codePanel = new JPanel();
        codePanel.add(new JLabel("Código: "));
        codePanel.add(codeField);
        codePanel.add(getCodeButton);
        add(codePanel, BorderLayout.SOUTH);

        getCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = codeField.getText().trim();
                if (!code.isEmpty()) {
                    getDataByCode(code);
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un código válido.");
                }
            }
        });

        // Nuevo botón
        myButton = new JButton("Terminar");
        myButton.setEnabled(false); // Inicialmente desactivado
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual y abrir el siguiente panel
                dispose();
                panel2.closeWindow();
                Panel_normal_3 panel3 = new Panel_normal_3();
                panel3.setVisible(true);
            }
        });
        add(myButton, BorderLayout.WEST);

        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        importExcel(); // Llama al método para importar los datos del archivo de Excel
    }
        
    public ExcelToJTable(Panel_excelent_2 panele2) {
        super("Import Excel to JTable");
        this.panele2 = panele2;
        this.array = new ReadArray("Array.txt");

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("Clase");
        model.addColumn("Código");
        model.addColumn("Precio");
        model.addColumn("Referencia1");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Buscar");
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Buscar: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Verificar si se han agregado al menos dos elementos antes de cerrar la ventana
                if (itemCount < 2) {
                    // Si no se han agregado suficientes elementos, mostrar un mensaje de advertencia
                    JOptionPane.showMessageDialog(null, "Debe agregar al menos dos elementos antes de cerrar la ventana.");
                    // Cancelar el evento de cierre de la ventana
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                } else {
                    // Si se han agregado suficientes elementos, cerrar la ventana
                    dispose();
                }
            }
        });
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "Utiliza el botón 'Agregar' para cerrar la ventana.");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().toLowerCase();
                if (!searchTerm.isEmpty()) {
                    filterTable(searchTerm);
                } else {
                    resetTable();
                }
            }
        });

        codeField = new JTextField(20);
        getCodeButton = new JButton("Obtener datos por código"); // Inicializamos el botón aquí
        JPanel codePanel = new JPanel();
        codePanel.add(new JLabel("Código: "));
        codePanel.add(codeField);
        codePanel.add(getCodeButton);
        add(codePanel, BorderLayout.SOUTH);

        getCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = codeField.getText().trim();
                if (!code.isEmpty()) {
                    getDataByCode(code);
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese un código válido.");
                }
            }
        });

        // Nuevo botón
        myButton = new JButton("Terminar");
        myButton.setEnabled(false); // Inicialmente desactivado
        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual y abrir el siguiente panel
                dispose();
                panele2.closeWindow();
                Panel_excelent_3 panele3 = new Panel_excelent_3();
                panele3.setVisible(true);
            }
        });
        add(myButton, BorderLayout.WEST);

        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        importExcel(); // Llama al método para importar los datos del archivo de Excel
    }

    private void importExcel() {
        // Ubicación predefinida del archivo de Excel en el proyecto
        String filePath = "datos.xlsx";
        array.borrarContenidoArchivo();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(1);

            originalData = new Object[sheet.getLastRowNum() + 1][4]; // Copia de respaldo de los datos originales

            DecimalFormat decimalFormat = new DecimalFormat("#.##########");

            for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Object[] rowData = new Object[4];
                for (int i = 0; i < 4; i++) {
                    Cell cell = row.getCell(i == 2 ? 6 : (i + 2), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String columnName = "";
                    switch (i) {
                        case 0:
                            columnName = "Clase";
                            break;
                        case 1:
                            columnName = "Referencia1";
                            break;
                        case 2:
                            columnName = "Precio";
                            break;
                        case 3:
                            columnName = "Referencia3";
                            break;
                    }
                    if (!columnName.isEmpty()) {
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData[i] = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                if (columnName.equals("Precio")) {
                                    rowData[i] = decimalFormat.format(cell.getNumericCellValue());
                                } else {
                                    rowData[i] = cell.getNumericCellValue();
                                }
                                break;
                            case BOOLEAN:
                                rowData[i] = cell.getBooleanCellValue();
                                break;
                        }
                    }
                }
                model.addRow(rowData);
                originalData[rowIndex] = rowData.clone(); // Almacena una copia de la fila original
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error importing Excel data: " + ex.getMessage());
        }
    }

    private void filterTable(String searchTerm) {
        model.setRowCount(0); // Limpiar la tabla antes de mostrar los resultados de búsqueda
        if (originalData != null) { // Verificar que originalData no sea nulo
            for (Object[] rowData : originalData) {
                for (Object cellData : rowData) {
                    if (cellData != null && cellData.toString().toLowerCase().contains(searchTerm)) {
                        model.addRow(rowData); // Agrega la fila original si encuentra una coincidencia
                        break; // No es necesario seguir buscando en esta fila
                    }
                }
            }
        }
    }

    private void resetTable() {
        model.setRowCount(0); // Limpiar la tabla
        if (originalData != null) { // Verificar que originalData no sea nulo
            for (Object[] rowData : originalData) {
                model.addRow(rowData); // Agrega todas las filas originales
            }
        }
    }

    public void getDataByCode(String code) {
        Object[] data = null;
        int foundCount = 0;

        for (int i = 0; i < table.getRowCount(); i++) {
            Object value = table.getValueAt(i, 1); // Código está en la segunda columna
            if (value != null && value.toString().equals(code)) {
                foundCount++;
                if (foundCount == 1) {
                    data = new Object[table.getColumnCount()]; // Crear un nuevo array para almacenar los datos
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        data[j] = table.getValueAt(i, j); // Almacenar los datos de la fila encontrada
                    }
                    // Incrementar el contador de elementos en el archivo Array.txt
                    int fileElementCount = array.contarElementos();
                    if (fileElementCount >= 2) {
                        // Si hay al menos dos elementos en el archivo, desactivar el botón "Obtener datos por código"
                        getCodeButton.setEnabled(false);
                        // Activar el botón "Terminar"
                        myButton.setEnabled(true);
                    }
                    itemCount++; // Aumentar el contador de elementos encontrados
                    if (itemCount == 1) {
                        // Mostrar un mensaje indicando que se necesita agregar otro código para agregar el segundo elemento
                        JOptionPane.showMessageDialog(null, "Se ha encontrado un elemento. Busque otro código para agregar el segundo elemento.");
                    }
                }
            }
        }

        if (foundCount > 2) {
            // Mostrar un mensaje indicando que se han encontrado más de dos elementos
            JOptionPane.showMessageDialog(null, "Se han encontrado más de dos elementos.");
        } else if (foundCount == 1) {
            // Mostrar un mensaje indicando que se necesita buscar otro código para agregar el segundo elemento
            JOptionPane.showMessageDialog(null, "Se ha encontrado un elemento. Busque otro código para agregar el segundo elemento.");
        } else if (foundCount == 0) {
            // Mostrar un mensaje indicando que no se encontraron datos para el código especificado
            JOptionPane.showMessageDialog(null, "No se encontraron datos para el código especificado.");
        }

        if (data != null) {
            // Aquí puedes hacer lo que quieras con los datos almacenados en 'data'
            // Por ejemplo, imprimirlos en consola
            System.out.println("Datos encontrados:");
            for (Object value : data) {
                System.out.println(value);
            }
            // Llama al método para escribir los datos en el archivo
            array.escribirDatosEnArchivo(data);
        }
    }
}