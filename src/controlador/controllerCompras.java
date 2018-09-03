package controlador;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.modelCompras;
import vista.Compras;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerCompras {
        private static String op;
    private static int cod;
private static int codProvee;
private static String serie;
private static BigDecimal total;
private static String fecha;
private static String proveedor;
private static DefaultTableModel dtm;
   public static String mntoCompras(){
       modelCompras.setOp(getOp());
       modelCompras.setCod(getCod());
       modelCompras.setCodProvee(getCodProvee());
       modelCompras.setSerie(getSerie());
       modelCompras.setFecha(getFecha());
       modelCompras.setTotal(getTotal());
       
       return modelCompras.mntoCompras();
          }
   

       public static String consultarComprasGeneradas() {
        try {

           modelCompras.setOp(getOp());
            modelCompras.setProveedor(getProveedor());
            modelCompras.setSerie(getSerie());
            ResultSet rs =  modelCompras.queryComprasGeneradas();
            if(rs.next()){
            System.out.println(rs.getString("###"));
            JOptionPane.showMessageDialog(null,"Codigo : ["+rs.getString("###")+"]\n"+
                    "Total : ["+rs.getString("TOTAL")+"]\n"+
                    "Fecha : ["+rs.getString("FECHA")+"]\n"
                    ,"Documento Existente",JOptionPane.ERROR_MESSAGE);
            return rs.getString("###");
            }else{
        
                if(Compras.isFechaValida(Compras.txtfecha.getText().toString())==true){
                    if(Compras.txtDocumento.getText().length()>0){
                Compras.jDialogFecha.dispose();
                Compras.txtserie.setText(getSerie());
                Compras.Fecha.setText(getFecha());
           Compras.txtmed.setEnabled(true);
           Compras.jDialog.setLocationRelativeTo(null);
           controllerMedicamentos.setOp("D");
        controllerMedicamentos.setBusc("");
        controllerMedicamentos.consultarMedicamentos("COMPRAS", dtm);
           Compras.jDialog.setVisible(true);
           Compras.txtbusq.requestFocus();
           
          
                }
                    else{
                      JOptionPane.showMessageDialog(null,"Numero de documento no valido."
                    ,"Error en Documento",JOptionPane.ERROR_MESSAGE);
                    }
                }
               else{
                JOptionPane.showMessageDialog(null,"Fecha no corresponde al formato solicitado ejemplo :"+"\n 2017-03-24 ."
                    ,"Error en fecha",JOptionPane.ERROR_MESSAGE);
                }
                
            }
return null;

        } catch ( Exception ex) {
            Logger.getLogger(controllerCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static String getOp() {
        return op;
    }

    public static int getCod() {
        return cod;
    }

    public static int getCodProvee() {
        return codProvee;
    }

    public static String getSerie() {
        return serie;
    }

    public static BigDecimal getTotal() {
        return total;
    }

    public static String getFecha() {
        return fecha;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setCodProvee(int aCodProvee) {
        codProvee = aCodProvee;
    }

    public static void setSerie(String aSerie) {
        serie = aSerie;
    }

    public static void setTotal(BigDecimal aTotal) {
        total = aTotal;
    }

    public static void setFecha(String aFecha) {
        fecha = aFecha;
    }

    public static String getProveedor() {
        return proveedor;
    }

    public static void setProveedor(String aProveedor) {
        proveedor = aProveedor;
    }
   
}
