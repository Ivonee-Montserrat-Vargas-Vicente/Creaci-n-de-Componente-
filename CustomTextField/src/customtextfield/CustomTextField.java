/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package customtextfield;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;



/**
 *
 * @author minparis.gomez
 */
public class CustomTextField extends JPanel {

    public enum InputType {
        ALL, LETTERS, NUMBERS
    }

    private JLabel label;
    private JTextField textField;
    private Color borderColor = Color.GRAY;
    private Color focusColor = new Color(102, 0, 204);
    private int maxLength = 20;
    private InputType inputType = InputType.ALL;

    public CustomTextField(String labelText) {
        setLayout(null);
        setOpaque(true);

        label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(Color.GRAY);
        label.setBounds(10, 0, 200, 20);

        textField = new JTextField();
        textField.setBounds(10, 20, 280, 30);
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor));
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setOpaque(true);

        textField.setUI(new javax.swing.plaf.basic.BasicTextFieldUI());

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                label.setForeground(focusColor);
                textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, focusColor));
            }

            @Override
            public void focusLost(FocusEvent e) {
                label.setForeground(Color.GRAY);
                textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor));
            }
        });

        add(label);
        add(textField);

        setPreferredSize(new Dimension(300, 60));
        updateDocumentFilter();
    }

    public CustomTextField() {
        this("Comentario:");
    }
    public void setText(String text) {
        textField.setText(text);
    }
    public String getText() {
        return textField.getText();
    }
    public void setEditable(boolean editable) {
        textField.setEditable(editable);
    }
    public void setMaxLength(int length) {
        this.maxLength = length;
        updateDocumentFilter();
    }
    public int getMaxLength() {
        return maxLength;
    }
    public void setInputType(InputType type) {
        this.inputType = type;
        updateDocumentFilter();
    }
    public InputType getInputType() {
        return inputType;
    }
    public void setLabelText(String text) {
        label.setText(text);
    }
    public String getLabelText() {
        return label.getText();
    }
    public void setBorderColor(Color color) {
        this.borderColor = color;
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor));
    }
    public Color getBorderColor() {
        return borderColor;
    }
    public void setFocusColor(Color color) {
        this.focusColor = color;
    }
    public Color getFocusColor() {
        return focusColor;
    }
    public void setTextColor(Color color) {
        textField.setForeground(color);
    }
    public void setTextAlignment(int alignment) {
        textField.setHorizontalAlignment(alignment);
    }
    public void setFontStyle(Font font) {
        if (textField != null) {
            textField.setFont(font);
        }
    }
    public Font getFontStyle() {
        return textField != null ? textField.getFont() : null;
    }
    public void setBackgroundColor(Color color) {
        setBackground(color);
        textField.setBackground(color);
    }
    public Color getBackgroundColor() {
        return getBackground();
    }


    private void updateDocumentFilter() {
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            private boolean isValid(String text) {
                switch (inputType) {
                    case LETTERS:
                        return text.matches("[a-zA-Z]*");
                    case NUMBERS:
                        return text.matches("[0-9]*");
                    case ALL:
                    default:
                        return true;
                }
            }

             @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) {
                return;
            }
            String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
            if (newText.length() <= maxLength && isValid(string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text == null) {
                return;
            }
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
            if (newText.length() <= maxLength && isValid(text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    });
}
}
