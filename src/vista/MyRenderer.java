/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author AlvaroMolina
 */
public class MyRenderer extends DefaultTableCellRenderer {
Color background;
Color foreground;
public MyRenderer (Color background,Color foreground) {
super();
this.background = background;
this.foreground = foreground;
}
public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
comp.setFont(new java.awt.Font("Tahoma", 1, 11));
comp.setBackground(background);
comp.setForeground(foreground);

return comp;
}
}
