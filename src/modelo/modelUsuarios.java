package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AMolinaDeveloper
 */
public class modelUsuarios {

    private static String op;
    private static String user;
    private static String pwd;
    private static String busc;
    
    private static int cod;
    private static String nomb;
    private static String dni;
    private static String dir;
    private static String cel;
    private static String permisos;

    public static ResultSet queryUsuarios() {

        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `queryUsuarios`(?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setString(2, getUser());
            Cst.setString(3, getPwd());
            Cst.setString(4, getBusc());
            Cst.executeQuery();
            return Cst.getResultSet();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    public static String mntoUsuarios() {
        try {
            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoUsuarios`(?,?,?,?,?,?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setInt(2, getCod());
            Cst.setString(3, getNomb());
            Cst.setString(4, getDni());
            Cst.setString(5, getDir());
            Cst.setString(6, getCel());
            Cst.setString(7, getPermisos());
            Cst.setString(8, getUser());
            Cst.setString(9, getPwd());
            Cst.executeUpdate();
            return "Ejecutado con éxito.";
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
       return "Ejecutado sin éxito : "+ex;
        }
       
        
    }

    public static String getOp() {
        return op;
    }

    public static String getUser() {
        return user;
    }

    public static String getPwd() {
        return pwd;
    }

    public static String getBusc() {
        return busc;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setUser(String aUser) {
        user = aUser;
    }

    public static void setPwd(String aPwd) {
        pwd = aPwd;
    }

    public static void setBusc(String aBusc) {
        busc = aBusc;
    }

    public static int getCod() {
        return cod;
    }

    public static String getNomb() {
        return nomb;
    }

    public static String getDni() {
        return dni;
    }

    public static String getDir() {
        return dir;
    }

    public static String getCel() {
        return cel;
    }

    public static String getPermisos() {
        return permisos;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setNomb(String aNomb) {
        nomb = aNomb;
    }

    public static void setDni(String aDni) {
        dni = aDni;
    }

    public static void setDir(String aDir) {
        dir = aDir;
    }

    public static void setCel(String aCel) {
        cel = aCel;
    }

    public static void setPermisos(String aPermisos) {
        permisos = aPermisos;
    }
}
