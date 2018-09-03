package vista;

import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alvaro Molina Cruz. <Amolina at hotmail.com>
 */
/*
Secuencias de escape
Escape Sequence Descripción
\ T Inserta una pestaña en el texto en este punto.
\ B Inserta un retroceso en el texto en este punto.
\ N Inserte una nueva línea en el texto en este punto.
\ R Insertar un retorno de carro en el texto en este punto.
\ F Inserta un formfeed en el texto en este punto.
\ 'Inserte un carácter de comillas simples en el texto en este momento.
\ "Inserte un carácter de comillas dobles en el texto en este punto.
\\ Inserte un carácter de barra invertida en el texto en este punto.


\\ El carácter de barra invertida <br>
\ T El carácter de pestaña ('\ u0009') <br>
\ N El carácter de nueva línea (alimentación de línea) ('\ u000A') <br>
\ R El carácter de retorno de carro ('\ u000D') <br>
\ F El carácter de feed de formulario ('\ u000C') <br>
\ A El carácter de alerta (campana) ('\ u0007') <br>
\ E El carácter de escape ('\ u001B') <br>
\ Cx El carácter de control correspondiente a x
*/
public class validator {
    private static DefaultTableModel dtm;

    //************************************************SOLO NUMEROS******************************************** 
    public static void solonumeros(java.awt.event.KeyEvent evt, javax.swing.JTextField caja, int cant) {
        char car = evt.getKeyChar();
        if (caja.getText().length() >= cant) {
            evt.consume();
        }
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }

//************************************************SOLO LETRAS******************************************** 
    public void sololetras(java.awt.event.KeyEvent evt, javax.swing.JTextField caja, int cant) {
        char car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && (car != (char) KeyEvent.VK_SPACE) || caja.getText().length() >= cant) {
            evt.consume();
        }

    }

    public static void nonumdecimales(java.awt.event.KeyEvent evt, javax.swing.JTextField caja, int cant) {
        char car = evt.getKeyChar();
        if (caja.getText().length() >= cant) {
            evt.consume();
        }
        if ((car < '0' || car > '9') && ((car != ',') || (car != '.'))) {
            evt.consume();
        }
    }

    public static boolean novacios(String longitud, int cant, int longpwd, javax.swing.JPasswordField pwd, javax.swing.JTextField... caja) {
        for (int cn = 0; cn < cant; cn++) {
            String parametros = longitud.toString();
            String[] param = parametros.split("-");
            System.out.println(param[cn]);
            if (caja[cn].getText().isEmpty() || caja[cn].getText().length() < Integer.parseInt(param[cn])) {
                // caja[cn].setBorder(BorderFactory.createLineBorder(Color.red));
                caja[cn].setBackground(new java.awt.Color(244, 245, 242));
                caja[cn].setFont(new java.awt.Font("Calibri Light", 2, 12));
                caja[cn].setHorizontalAlignment(javax.swing.JTextField.LEFT);
                caja[cn].setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Tamaño mínimo de " + caja[cn].getName() + " es " + param[cn], javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 11), new java.awt.Color(255, 0, 0))); // NOI18N
                caja[cn].setCaretColor(new java.awt.Color(51, 204, 255));
                caja[cn].requestFocus();
              
                return true;
            } else {
                //   caja[cn].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                caja[cn].setBackground(new java.awt.Color(244, 245, 242));
                caja[cn].setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
                caja[cn].setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), caja[cn].getName(), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
            
            
                String c = new String(pwd.getPassword());
                
                if (c.length() < longpwd) {
                    pwd.setBackground(new java.awt.Color(244, 245, 242));
                    pwd.setFont(new java.awt.Font("Calibri Light", 2, 12));
                    pwd.setHorizontalAlignment(javax.swing.JTextField.LEFT);
                    pwd.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Tamaño mínimo de contraseña es " + longpwd, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 11), new java.awt.Color(255, 0, 0))); // NOI18N
                    pwd.setCaretColor(new java.awt.Color(51, 204, 255));
                    pwd.requestFocus();
                    return true;
                } else {
                    pwd.setBackground(new java.awt.Color(244, 245, 242));
                    pwd.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
                    pwd.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true),"Contraseña", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
                }
            }
        }
        return false;
    }

    
        public static boolean novacios(String longitud, int cant, javax.swing.JTextField... caja) {
        for (int cn = 0; cn < cant; cn++) {
//            String parametros=longitud.toString();
//String[] param=parametros.split("-");
//for(int y=0;y<param.length;y++){
//System.out.println(param[y]);}
            String parametros = longitud.toString();
            String[] param = parametros.split("-");
//for(int y=0;y<param.length;y++){
            System.out.println(param[cn]);
//}
            if (caja[cn].getText().isEmpty() || caja[cn].getText().length() < Integer.parseInt(param[cn])) {
                // caja[cn].setBorder(BorderFactory.createLineBorder(Color.red));
                caja[cn].setBackground(new java.awt.Color(244, 245, 242));
                caja[cn].setFont(new java.awt.Font("Calibri Light", 2, 12));
                caja[cn].setHorizontalAlignment(javax.swing.JTextField.LEFT);
                caja[cn].setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Tamaño mínimo de " + caja[cn].getName() + " es " + param[cn], javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 11), new java.awt.Color(255, 0, 0))); // NOI18N
                caja[cn].setCaretColor(new java.awt.Color(51, 204, 255));
                caja[cn].requestFocus();
               
                return true;
            } else {
                //   caja[cn].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                caja[cn].setBackground(new java.awt.Color(244, 245, 242));
                caja[cn].setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
                caja[cn].setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), caja[cn].getName(), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
            }
        }
        return false;
    }
    
    
    
    
    public void numerosletras(java.awt.event.KeyEvent evt, javax.swing.JTextField caja, int cant) {
        if (caja.getText().length() >= cant) {
            evt.consume();
        }

    }

    public static void soloDecimales(java.awt.event.KeyEvent evt, javax.swing.JTextField caja) {
        boolean solounavez = false;
        char c = evt.getKeyChar();

        if (caja.getText().length() == 0) {
            solounavez = false;

        }
        //////
        if (Character.isDigit(c) || c == '.') {
            if (c == '.' && solounavez == true) {
                evt.consume();
            }
            evt.isConsumed();
        } else {
            evt.consume();
        }
    }
     public static void vaciar_jtable(javax.swing.JTable jtable) {
         dtm=new DefaultTableModel();
        dtm = (DefaultTableModel) jtable.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }
     
}
