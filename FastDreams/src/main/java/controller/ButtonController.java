/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ButtonController {
    public static void initializeButton(JButton button, JTextField textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkButtonEnabled();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkButtonEnabled();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Solo necesario si el JTextField permite estilos de texto, como subrayado o negrita
            }

            public void checkButtonEnabled() {
                button.setEnabled(!textField.getText().isEmpty());
            }
        });
    }
}