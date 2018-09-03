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
public class modelDetalleCompras {

    private static String op;
    private static int cod;
    private static int codMed;
    private static BigDecimal precio;
    private static int cantidad;
    private static int codProvee;
    private static String serie;
    private static BigDecimal precioN;
    private static BigDecimal precioM;
    private static BigDecimal bono;
    private static Integer cantUnid;
    private static String unidMed;
    private static BigDecimal total;
    private static String correlativo;
    private static String provee;

    public static String mntoDetalleCompras() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoDetalleCompras`(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setInt(2, getCod());
            Cst.setInt(3, getCodMed());
            Cst.setBigDecimal(4, getPrecio());
            Cst.setInt(5, getCantidad());
            Cst.setInt(6, getCodProvee());
            Cst.setString(7, getSerie());
            Cst.setBigDecimal(8, getPrecioM());
            Cst.setBigDecimal(9, getPrecioN());
            Cst.setBigDecimal(10, getBono());
            Cst.setInt(11, getCantUnid());
            Cst.setString(12, getUnidMed());
            Cst.setBigDecimal(13, getTotal());
            Cst.executeUpdate();
            return "Ejecutado con éxito.";
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return "Ejecutado sin éxito. " + ex;
        }
    }

    public static ResultSet mntoActualizarCompras() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoActualizarCompras`(?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setString(2, getCorrelativo());
            Cst.setString(3, getProvee());
            
            return Cst.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    public static String getOp() {
        return op;
    }

    public static int getCod() {
        return cod;
    }

    public static int getCodMed() {
        return codMed;
    }

    public static BigDecimal getPrecio() {
        return precio;
    }

    public static int getCantidad() {
        return cantidad;
    }

    public static int getCodProvee() {
        return codProvee;
    }

    public static String getSerie() {
        return serie;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setCodMed(int aCodMed) {
        codMed = aCodMed;
    }

    public static void setPrecio(BigDecimal aPrecio) {
        precio = aPrecio;
    }

    public static void setCantidad(int aCantidad) {
        cantidad = aCantidad;
    }

    public static void setCodProvee(int aCodProvee) {
        codProvee = aCodProvee;
    }

    public static void setSerie(String aSerie) {
        serie = aSerie;
    }

    public static BigDecimal getPrecioN() {
        return precioN;
    }

    public static BigDecimal getPrecioM() {
        return precioM;
    }

    public static void setPrecioN(BigDecimal aPrecioN) {
        precioN = aPrecioN;
    }

    public static void setPrecioM(BigDecimal aPrecioM) {
        precioM = aPrecioM;
    }

    public static BigDecimal getBono() {
        return bono;
    }

    public static void setBono(BigDecimal aBono) {
        bono = aBono;
    }

    public static Integer getCantUnid() {
        return cantUnid;
    }

    public static void setCantUnid(Integer aCantUnid) {
        cantUnid = aCantUnid;
    }

    public static String getUnidMed() {
        return unidMed;
    }

    public static void setUnidMed(String aUnidMed) {
        unidMed = aUnidMed;
    }

    /**
     * @return the total
     */
    public static BigDecimal getTotal() {
        return total;
    }

    /**
     * @param aTotal the total to set
     */
    public static void setTotal(BigDecimal aTotal) {
        total = aTotal;
    }

    /**
     * @return the correlativo
     */
    public static String getCorrelativo() {
        return correlativo;
    }

    /**
     * @param aCorrelativo the correlativo to set
     */
    public static void setCorrelativo(String aCorrelativo) {
        correlativo = aCorrelativo;
    }

    /**
     * @return the provee
     */
    public static String getProvee() {
        return provee;
    }

    /**
     * @param aProvee the provee to set
     */
    public static void setProvee(String aProvee) {
        provee = aProvee;
    }

}
