package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AMolinaDeveloper
 */
public class modelClientes {

    private static String op;
    private static String busc;
    private static int cod;
    private static String nomb;
    private static String dni;
    private static String ruc;
    private static String dir;
    private static String fono;

    public static ResultSet queryClientes() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `queryClientes`(?,?)}");
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
    public static String mntoClientes() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoClientes`(?,?,?,?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setInt(2, getCod());
            Cst.setString(3, getNomb());
            Cst.setString(4, getDni());
            Cst.setString(5, getRuc());
            Cst.setString(6, getDir());
            Cst.setString(7, getFono());
            Cst.executeUpdate();
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

    public static String getBusc() {
        return busc;
    }

    public static void setBusc(String aBusc) {
        busc = aBusc;
    }

    public static int getCod() {
        return cod;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static String getNomb() {
        return nomb;
    }

    public static void setNomb(String aNomb) {
        nomb = aNomb;
    }

    public static String getDni() {
        return dni;
    }

    public static void setDni(String aDni) {
        dni = aDni;
    }

    public static String getRuc() {
        return ruc;
    }

    public static void setRuc(String aRuc) {
        ruc = aRuc;
    }

    public static String getDir() {
        return dir;
    }

    public static void setDir(String aDir) {
        dir = aDir;
    }

    public static String getFono() {
        return fono;
    }

    public static void setFono(String aFono) {
        fono = aFono;
    }
}
