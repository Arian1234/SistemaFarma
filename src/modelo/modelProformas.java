
package modelo;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;


/**
 *
 * @author AMolinaDeveloper
 */
public class modelProformas {
    
private static String op;

private static int cod;
private static String codigo;
private static int cant;
private static int cantxcaja;


private static int codClie;
private static int codUsu;
private static BigDecimal total;
private static String cabecera;
private static String detalle;     
private static String codVenta;
  public static String mntoActualizarProforma() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoActualizacionProformas`(?,?)}");
            Cst.setString(1, getOp());
            Cst.setString(2, getCodVenta());
           
            Cst.executeUpdate();
             return "EJECUTADO CON EXITO.";
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex); 
         return "EJECUTADO SIN EXITO. "+ex;}    }
  
  
  

    public static ResultSet queryProformas() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `queryProformas`(?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setString(2, getCabecera());
            Cst.setString(3, getDetalle());
            Cst.executeQuery();
            return Cst.getResultSet();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    ///////////////////////////////////////
    //////////////////////////////////////
    /////////////////////////////////////
    public static String mntoProformas() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoproformas`(?,?,?,?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setInt(2, getCod());
            Cst.setInt(3, getCodClie());
            Cst.setInt(4, getCodUsu());
            Cst.setBigDecimal(5, getTotal());
            Cst.registerOutParameter(6,Types.CHAR);
            Cst.registerOutParameter(7,Types.CHAR);
          
            Cst.executeUpdate();
            cabecera=Cst.getString(6);
                detalle=Cst.getString(7);
             return "EJECUTADO CON EXITO.";
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex); 
         return "EJECUTADO SIN EXITO. "+ex;}    }

    
    
    
    public static String getOp() {
        return op;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static int getCod() {
        return cod;
    }

    public static int getCodClie() {
        return codClie;
    }

    public static int getCodUsu() {
        return codUsu;
    }

    public static BigDecimal getTotal() {
        return total;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setCodClie(int aCodClie) {
        codClie = aCodClie;
    }

    public static void setCodUsu(int aCodUsu) {
        codUsu = aCodUsu;
    }

    public static void setTotal(BigDecimal aTotal) {
        total = aTotal;
    }

    public static String getCabecera() {
        return cabecera;
    }

    public static String getDetalle() {
        return detalle;
    }

    public static void setCabecera(String aCabecera) {
        cabecera = aCabecera;
    }

    public static void setDetalle(String aDetalle) {
        detalle = aDetalle;
    }

    public static String getCodVenta() {
        return codVenta;
    }

    public static void setCodVenta(String aCodVenta) {
        codVenta = aCodVenta;
    }

    public static int getCant() {
        return cant;
    }

    public static int getCantxcaja() {
        return cantxcaja;
    }

    public static void setCant(int aCant) {
        cant = aCant;
    }

    public static void setCantxcaja(int aCantxcaja) {
        cantxcaja = aCantxcaja;
    }

    public static String getCodigo() {
        return codigo;
    }

    public static void setCodigo(String aCodigo) {
        codigo = aCodigo;
    }

}
