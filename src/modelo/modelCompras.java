package modelo;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AMolinaDeveloper
 */
public class modelCompras {
    private static String op;
    private static int cod;
private static int codProvee;
private static String serie;
private static BigDecimal total;
private static String fecha;
private static String proveedor;
    //    public static ResultSet queryCompras() {
//        try {
//
//            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `queryProveedores`(?,?)}");
//            Cst.setString(1, getOp());
//            Cst.setString(2, getBusc());
//            Cst.executeQuery();
//            return Cst.getResultSet();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//            return null;
//        }
//    }

        public static ResultSet queryComprasGeneradas() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `queryCompraGenerada`(?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setString(2, getProveedor());
            Cst.setString(3, getSerie());
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
    public static String mntoCompras() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoCompras`(?,?,?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setInt(2, getCod());
            Cst.setInt(3, getCodProvee());
            Cst.setString(4, getSerie());
            Cst.setBigDecimal(5, getTotal());
            Cst.setString(6, getFecha());
                  Cst.executeUpdate();
            return "Ejecutado con éxito.";
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return "Ejecutado sin éxito. " + ex;
        }
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
