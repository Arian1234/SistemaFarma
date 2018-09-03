package vista.img;

import controlador.controllerDetallesProformas;
import java.awt.Component;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import vista.PedidoInterno;

/**
 *
 * @author AMolinaDeveloper
 */
public class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private String oldValue = "";
        private String cant = "";
         private String codigo = "";
         private String columna = "";
    private String newValue = "";
    private String prec = "";
    private String tipo = "";
    private int fila = 0;
    private BigDecimal precio, total;
    private JComponent JText = new JTextField();
public boolean esNumerico(String num){
try{int x=Integer.parseInt(num);
return true;
}catch(NumberFormatException ex){
return false;}
}
    @Override
    public Object getCellEditorValue() {
        return ((JTextField) JText).getText();



    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
       columna=String.valueOf(column);
        oldValue = value.toString();
        
        ((JTextField) JText).setText(value.toString());//coloca valor de la celda al JTextField
        codigo = table.getValueAt(row, 0).toString();
        cant = table.getValueAt(row, 2).toString();
        prec = table.getValueAt(row, 4).toString();
        tipo = table.getValueAt(row, 3).toString();
        fila = row;
        System.out.println("cantidad antigua : " + cant);
        System.out.println("precio : " + table.getValueAt(row, 4).toString());
        System.out.println("total : " + table.getValueAt(row, 5).toString());
        System.out.println("fila : " + fila);

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return JText;
    }

    @Override
    public boolean stopCellEditing() {
        ResultSet rs;
        newValue = getCellEditorValue().toString();


        if (!oldValue.equals(newValue)) {
   
     try{      
if("2".equals(columna)){
if(esNumerico(newValue)){
   controllerDetallesProformas.setOp("C");
                controllerDetallesProformas.setCab(0);
                controllerDetallesProformas.setDet(0);
                controllerDetallesProformas.setCodM(Integer.parseInt(codigo));
                controllerDetallesProformas.setPrecioC(BigDecimal.ZERO);
                controllerDetallesProformas.setPrecioV(BigDecimal.ZERO);
                controllerDetallesProformas.setCantV(Integer.parseInt(newValue));
                controllerDetallesProformas.setTotalV(BigDecimal.ZERO);
                controllerDetallesProformas.setUniMed("");
                controllerDetallesProformas.setBono(BigDecimal.ZERO);
               rs=controllerDetallesProformas.mntoDetallesProformas();
                  
                if(rs.next()){
                    System.out.println(rs.getString("ESTADO"));
                JOptionPane.showMessageDialog(null,rs.getString("ESTADO"),"Stock Limitado",JOptionPane.ERROR_MESSAGE);
                   PedidoInterno.jTablePedidoInterno.setValueAt(oldValue, fila, 2);
                return false;
            
                }
}else{
JOptionPane.showMessageDialog(null, "Ingresa un numero valido","Caracteres no validos",JOptionPane.INFORMATION_MESSAGE);
 PedidoInterno.jTablePedidoInterno.setValueAt(oldValue, fila, 2);
 return false;
}
      
   

            
            if (tipo.equals("BONIFIC")) {

                JOptionPane.showMessageDialog(null, "Las bonificaciones no pueden ser modificadas.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                PedidoInterno.jTablePedidoInterno.setValueAt(oldValue, fila, 2);
                return false;
            } else {
                System.out.println("cantidad nueva : " + newValue);
                precio = BigDecimal.valueOf(Double.parseDouble(prec));
                total = precio.multiply(BigDecimal.valueOf(Double.parseDouble(newValue))).setScale(2, RoundingMode.HALF_UP);
                PedidoInterno.jTablePedidoInterno.setValueAt(total.toString(), fila, 5);
               
                if(tipo.equals("CAJA")){
                    
                         controllerDetallesProformas.setOp("C");
                controllerDetallesProformas.setCab(0);
                controllerDetallesProformas.setDet(0);
                controllerDetallesProformas.setCodM(Integer.parseInt(codigo));
                controllerDetallesProformas.setPrecioC(BigDecimal.ZERO);
                controllerDetallesProformas.setPrecioV(BigDecimal.ZERO);
                controllerDetallesProformas.setCantV(Integer.parseInt(newValue)*Integer.parseInt(PedidoInterno.jTablePedidoInterno.getValueAt(fila,8).toString()));
                controllerDetallesProformas.setTotalV(BigDecimal.ZERO);
                controllerDetallesProformas.setUniMed("");
                controllerDetallesProformas.setBono(BigDecimal.ZERO);
                rs=controllerDetallesProformas.mntoDetallesProformas();
                  
                if(rs.next()){
                    System.out.println(rs.getString("ESTADO"));
                JOptionPane.showMessageDialog(null,rs.getString("ESTADO"),"Stock Limitado",JOptionPane.ERROR_MESSAGE);
                   PedidoInterno.jTablePedidoInterno.setValueAt(oldValue, fila, 2);
                return false;
            
                }else{
                    
                PedidoInterno.jTablePedidoInterno.setValueAt(
                        BigDecimal.valueOf(Double.parseDouble(newValue))
                        .multiply(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(fila,8).toString()))).multiply(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(fila,11).toString()))).setScale(2, RoundingMode.HALF_UP), fila, 9);
                }
                }else if(tipo.equals("UNID")){
                 PedidoInterno.jTablePedidoInterno.setValueAt(
                        BigDecimal.valueOf(Double.parseDouble(newValue)).multiply(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(fila,11).toString()))), fila, 9);
               
                }
                                PedidoInterno.sumarTotal();
            }
        }}catch(NumberFormatException | SQLException | HeadlessException ex){
        JOptionPane.showMessageDialog(null,"Error en TableCellEditor "+ex);
        }
//precio nuevo
if("4".equals(columna)){
            if (tipo.equals("BONIFIC")) {

                JOptionPane.showMessageDialog(null, "Las bonificaciones no pueden ser modificadas.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                PedidoInterno.jTablePedidoInterno.setValueAt(cant, fila, 2);
                return false;
            } else {
                System.out.println("precio nuevo : " + newValue);
                precio = BigDecimal.valueOf(Double.parseDouble(newValue));
                total = precio.multiply(BigDecimal.valueOf(Double.parseDouble(cant))).setScale(2, RoundingMode.HALF_UP);
                PedidoInterno.jTablePedidoInterno.setValueAt(total.toString(), fila, 5);
               
                if(tipo.equals("CAJA")){
                PedidoInterno.jTablePedidoInterno.setValueAt(
                        BigDecimal.valueOf(Double.parseDouble(cant)).multiply(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(fila,8).toString()))).multiply(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(fila,11).toString()))).setScale(2, RoundingMode.HALF_UP), fila, 9);
                
                }else if(tipo.equals("UNID")){
                 PedidoInterno.jTablePedidoInterno.setValueAt(
                        BigDecimal.valueOf(Double.parseDouble(cant)).multiply(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(fila,11).toString()))), fila, 9);
               
                }
                                PedidoInterno.sumarTotal();
            }
        }
        }

        return super.stopCellEditing();
    
    }
}
