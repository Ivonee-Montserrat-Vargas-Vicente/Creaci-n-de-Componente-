# Creación-de-Componente-

## Objetivo:
Diseñar e implementar un componente visual personalizado en Java utilizando NetBeans, que permita
personalización mediante propiedades y métodos públicos configurables por el usuario.

## Proposito:
El proposito de nuestro componente es que el usuario pueda ocupar el componente para textos cortos como comentarios, notas etc.
-Permite cambiar el color de letra, tipo y tamaño
-Permite cambiar el color de linea y el fondo del texto
-Permite elegir 3 opciones de escritura: solo numeros, letras o ambos y tambien el numero de caracteres
## 📋 Métodos de la Clase

| Método                          | Parámetros                     | Retorno    | Descripción                                                                 |
|----------------------------------|--------------------------------|------------|-----------------------------------------------------------------------------|
| `setText(String text)`           | `text`: Texto a asignar        | `void`     | Establece el texto en el campo de texto.                                   |
| `getText()`                      | -                              | `String`   | Obtiene el texto actual del campo.                                         |
| `setEditable(boolean editable)`  | `editable`: Editable (true/false) | `void`  | Habilita/deshabilita la edición del campo.                                |
| `setMaxLength(int length)`       | `length`: Longitud máxima      | `void`     | Define el máximo de caracteres permitidos (actualiza filtro).             |
| `getMaxLength()`                 | -                              | `int`      | Devuelve la longitud máxima configurada.                                  |
| `setInputType(InputType type)`   | `type`: Tipo de entrada        | `void`     | Establece el tipo de dato permitido (ej: numérico).                       |
| `getInputType()`                 | -                              | `InputType`| Obtiene el tipo de entrada configurado.                                   |
| `setLabelText(String text)`      | `text`: Texto de la etiqueta   | `void`     | Cambia el texto de la etiqueta asociada.                                  |
| `getLabelText()`                 | -                              | `String`   | Devuelve el texto de la etiqueta.                                         |
| `setBorderColor(Color color)`    | `color`: Color del borde       | `void`     | Modifica el color del borde inferior del campo.                           |
| `getBorderColor()`               | -                              | `Color`    | Retorna el color actual del borde.                                        |
| `setFocusColor(Color color)`     | `color`: Color en foco         | `void`     | Define el color al seleccionar el campo (requiere implementación).        |
| `getFocusColor()`                | -                              | `Color`    | Obtiene el color de foco configurado.                                    |
| `setTextColor(Color color)`      | `color`: Color del texto       | `void`     | Cambia el color del texto dentro del campo.                               |
| `setTextAlignment(int alignment)`| `alignment`: Alineación        | `void`     | Alinea el texto (`LEFT`, `CENTER`, `RIGHT`).                             |
| `setFontStyle(Font font)`        | `font`: Fuente personalizada   | `void`     | Aplica una fuente específica al texto.                                    |
| `getFontStyle()`                 | -                              | `Font`     | Devuelve la fuente actual del campo.                                      |
| `setBackgroundColor(Color color)`| `color`: Color de fondo        | `void`     | Cambia el color de fondo del componente.                                  |
| `getBackgroundColor()`           | -                              | `Color`    | Obtiene el color de fondo actual.                                         |

###  Uso Básico
    ```java
// Ejemplo de configuración
miCampo.setLabelText("Usuario:");
miCampo.setMaxLength(15);
miCampo.setInputType(InputType.TEXT);
miCampo.setBorderColor(Color.BLUE);

// Obtener valores
String texto = miCampo.getText();
int maxCaracteres = miCampo.getMaxLength();

## Explicacion del codigo

Es un componente personalizado de interfaz gráfica basado en JPanel, que encapsula un JTextField con:
Un JLabel encima como etiqueta.
Estilos personalizados (colores, fuentes, bordes).
Restricciones de entrada como:
Límite de caracteres.
Solo letras, números o ambos.

Nuestra clase CustomTextField es un componente gráfico personalizado que extiende JPanel, y encapsula un campo de texto (JTextField) con una etiqueta (JLabel) y varias características adicionales como personalización visual y validación de entrada.

Primero, se declara la clase y una enumeración interna llamada InputType, la cual define el tipo de datos que el campo puede aceptar: ALL (letras y números), LETTERS (solo letras) y NUMBERS (solo números). 
    

    public class CustomTextField extends JPanel {

    public enum InputType {
        ALL, LETTERS, NUMBERS
    }

Se declaran varios atributos privados: label y textField para mostrar el texto y permitir entrada; borderColor y focusColor para personalizar los colores del borde; maxLength para limitar la cantidad de caracteres, e inputType para restringir el tipo de entrada. 
    
 
    private JLabel label;
 
    private JTextField textField;
    private Color borderColor = Color.GRAY;
    private Color focusColor = new Color(102, 0, 204);
    private int maxLength = 20;
    private InputType inputType = InputType.ALL;

El constructor principal CustomTextField(String labelText) recibe el texto de la etiqueta como parámetro. Se configura el diseño del panel con setLayout(null) (layout absoluto), y se crea y posiciona el label y el textField. Se aplican fuentes, colores y un borde inferior personalizado al campo de texto.  
    

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

Se agrega un FocusListener al textField que cambia dinámicamente el color del borde y de la etiqueta cuando el campo gana o pierde el foco (cuando el usuario hace clic para escribir o se sale del campo).  
  

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
Después se agregan los componentes al panel con add(label) y add(textField), se define un tamaño preferido de 300x60 píxeles, y se llama a updateDocumentFilter(), el cual configura las restricciones de entrada.


También hay un segundo constructor sin parámetros CustomTextField() que simplemente llama al constructor principal usando "Comentario:" como texto por defecto.
    
    public CustomTextField() {
        this("Comentario:");
    }

La clase incluye varios métodos públicos para manipular y personalizar el componente: puedes obtener o establecer el texto, la etiqueta, el tipo de entrada, el máximo de caracteres, colores, alineación del texto, fuente y fondo. Esto permite reutilizar el componente en distintos contextos con apariencia y comportamiento personalizado. 
   

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
        

El método más importante es updateDocumentFilter(), que instala un DocumentFilter sobre el documento del campo de texto. Este filtro intercepta cada intento del usuario de escribir (insertString y replace) y valida si lo que se quiere escribir cumple con las restricciones del tipo de entrada (LETTERS, NUMBERS, ALL) y si no supera el límite de caracteres (maxLength). Si la validación se cumple, permite la escritura, si no, la bloquea. 
   
        
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
                        return text.matches("[a-zA-Z0-9]*");
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

## Instrucciones de uso:

Por defecto el componente ya se encuentra en la Paleta de componentes asi que ahi lo podemos encontrar de manera visual

<img width="311" alt="image" src="https://github.com/user-attachments/assets/e50a37f6-93e7-4f7e-aec9-39a5815e662f" />

Arrastramos el componente al jFrame que vayamos a ocupar 

<img width="465" alt="image" src="https://github.com/user-attachments/assets/f55bb976-269e-4e89-a922-1d3907569dcd" />


Una vez que ya lo tenemos agregado al jFrame damos click derecho y apareceran algunas de sus propiedades que le agregamos


<img width="465" alt="image" src="https://github.com/user-attachments/assets/3c253867-3f05-404e-9fea-cc6f40bbc320" />

Personaliamos el componente como queramos


<img width="468" alt="image" src="https://github.com/user-attachments/assets/5a3cba51-1242-4cda-b8e9-8f7106ce14d5" />


Asi tenemos nuestro componente personalizado


![image](https://github.com/user-attachments/assets/3e2f4d0c-867c-47e4-a24c-1ea38ebf02bf)


## Video Explicativo

Para ver una demostración en video de cómo funciona el proyecto:
https://youtu.be/8zxEEbFJBhc?feature=shared

## Creadoras:

* Vargas Vicente Ivonee Montserrat
* Gomez Garcia Paris Lizette




