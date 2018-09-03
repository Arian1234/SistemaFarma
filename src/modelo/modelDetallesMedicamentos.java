package modelo;

import java.sql.CallableStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AMolinaDeveloper
 */
public class modelDetallesMedicamentos {
    private static String op;
private static int cod;
private static int codcomp;
private static int codmed;
//     public static ResultSet queryLaboratorio() {
//        try {
//
//            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `queryLaboratorios`(?,?)}");
//            Cst.setString(1, getOp());
//            Cst.setString(2, getBusc());
//            Cst.executeQuery();
//            return Cst.getResultSet();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//            return null;
//        }
//    }
//
//    ///////////////////////////////////////
//    //////////////////////////////////////
//    /////////////////////////////////////
    
    public static String mntoDetallesMed() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoDetalleMedicamentos`(?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setInt(2, getCod());
            Cst.setInt(3, getCodcomp());
            Cst.setInt(4, getCodmed());
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

    public static int getCodcomp() {
        return codcomp;
    }

    public static int getCodmed() {
        return codmed;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setCodcomp(int aCodcomp) {
        codcomp = aCodcomp;
    }

    public static void setCodmed(int aCodmed) {
        codmed = aCodmed;
    }
}
