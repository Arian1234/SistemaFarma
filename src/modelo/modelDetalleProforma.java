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
public class modelDetalleProforma {

    private static String op;
    private static int cab;
    private static int det;
    private static int codM;
    private static int cantV;
    private static String uniMed;
    private static BigDecimal precioC;
    private static BigDecimal precioV;
    private static BigDecimal totalV;
    private static BigDecimal bono;
//      public static ResultSet queryProformas() {
//        try {
//
//            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `queryClientes`(?,?)}");
//            Cst.setString(1, getOp());
//            Cst.setString(2, getBusc());
//            Cst.executeQuery();
//            return Cst.getResultSet();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//            return null;
//        }
//    }

    ///////////////////////////////////////
    //////////////////////////////////////
    /////////////////////////////////////
    public static ResultSet mntoDetalleProformas() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoDetalleProforma`(?,?,?,?,?,?,?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setInt(2, getCab());
            Cst.setInt(3, getDet());
            Cst.setInt(4, getCodM());
            Cst.setBigDecimal(5, getPrecioC());
            Cst.setBigDecimal(6, getPrecioV());
            Cst.setInt(7, getCantV());
            Cst.setBigDecimal(8, getTotalV());
            Cst.setString(9, getUniMed());
            Cst.setBigDecimal(10, getBono());
           
            return  Cst.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    public static String getOp() {
        return op;
    }

    public static int getCab() {
        return cab;
    }

    public static int getDet() {
        return det;
    }

    public static int getCodM() {
        return codM;
    }

    public static int getCantV() {
        return cantV;
    }

    public static BigDecimal getPrecioC() {
        return precioC;
    }

    public static BigDecimal getPrecioV() {
        return precioV;
    }

    public static BigDecimal getTotalV() {
        return totalV;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setCab(int aCab) {
        cab = aCab;
    }

    public static void setDet(int aDet) {
        det = aDet;
    }

    public static void setCodM(int aCodM) {
        codM = aCodM;
    }

    public static void setCantV(int aCantV) {
        cantV = aCantV;
    }

    public static void setPrecioC(BigDecimal aPrecioC) {
        precioC = aPrecioC;
    }

    public static void setPrecioV(BigDecimal aPrecioV) {
        precioV = aPrecioV;
    }

    public static void setTotalV(BigDecimal aTotalV) {
        totalV = aTotalV;
    }

    public static String getUniMed() {
        return uniMed;
    }

    public static void setUniMed(String aUniMed) {
        uniMed = aUniMed;
    }

    public static BigDecimal getBono() {
        return bono;
    }

    public static void setBono(BigDecimal aBono) {
        bono = aBono;
    }
}
