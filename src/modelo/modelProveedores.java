package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AMolinaDeveloper
 */
public class modelProveedores {

    private static String op;
    private static String busc;
    private static int cod;
    private static String nomb;
        private static String ruc;
    private static String dir;
    private static String fono;
    private static String cel;
    private static String correo;

    public static ResultSet queryProveedores() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `queryProveedores`(?,?)}");
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
    public static String mntoProveedores() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoProveedores`(?,?,?,?,?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setInt(2, getCod());
            Cst.setString(3, getNomb());
            Cst.setString(4, getDir());
            Cst.setString(5, getFono());
            Cst.setString(6, getCel());
            Cst.setString(7, getCorreo());
             Cst.setString(8, getRuc());
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

    public static String getBusc() {
        return busc;
    }

    public static int getCod() {
        return cod;
    }

    public static String getNomb() {
        return nomb;
    }

    public static String getDir() {
        return dir;
    }

    public static String getFono() {
        return fono;
    }

    public static String getCel() {
        return cel;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setBusc(String aBusc) {
        busc = aBusc;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setNomb(String aNomb) {
        nomb = aNomb;
    }

    public static void setDir(String aDir) {
        dir = aDir;
    }

    public static void setFono(String aFono) {
        fono = aFono;
    }

    public static void setCel(String aCel) {
        cel = aCel;
    }

    public static void setCorreo(String aCorreo) {
        correo = aCorreo;
    }

    public static String getRuc() {
        return ruc;
    }

    public static void setRuc(String aRuc) {
        ruc = aRuc;
    }
}
