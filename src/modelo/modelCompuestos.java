package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AMolinaDeveloper
 */
public class modelCompuestos {
 private static String op;
   private static String busc;
    private static int cod;
    private static String nomb;
     private static String descrip;
    public static ResultSet queryCompuestos() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `queryCompuestos`(?,?)}");
            Cst.setString(1, getOp());
            Cst.setString(2, getBusc());
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
    public static String mntoCompuestos() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoCompuestos`(?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setInt(2, getCod());
            Cst.setString(3, getNomb());
            Cst.setString(4, getDescrip());
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

    public static String getNomb() {
        return nomb;
    }

    public static String getDescrip() {
        return descrip;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setNomb(String aNomb) {
        nomb = aNomb;
    }

    public static void setDescrip(String aDescrip) {
        descrip = aDescrip;
    }

    public static String getBusc() {
        return busc;
    }

    public static void setBusc(String aBusc) {
        busc = aBusc;
    }
}
