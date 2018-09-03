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
public class modelMedicamentos {

    private static String op;
    private static String busc;
    private static int cod;
    private static String nomb;
    private static int codlabor;
    private static String present;
    private static String descrip;
    private static String fraccionable;
    private static int unidxcaja;
    private static BigDecimal preccosto;
    private static BigDecimal precmin;
    private static BigDecimal precnormal;
    private static String promocion;
    private static int activaprom;
    private static int regalo;
    private static int stock;
    private static int stockmin;
    private static String compsc;
    private static BigDecimal bono;
    public static ResultSet queryMedicamentos() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `queryMedicamentos`(?,?)}");
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
    public static String mntoMedicamentos() {
        try {

            CallableStatement Cst = Conexion.Conexion().prepareCall("{call `mntoMedicamentos`(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            Cst.setString(1, getOp());
            Cst.setInt(2, getCod());
            Cst.setString(3, getNomb());
            Cst.setInt(4, getCodlabor());
            Cst.setString(5, getPresent());
            Cst.setString(6, getDescrip());
            Cst.setString(7, getFraccionable());
            Cst.setInt(8, getUnidxcaja());
            Cst.setBigDecimal(9, getPreccosto());
            Cst.setBigDecimal(10, getPrecmin());
            Cst.setBigDecimal(11, getPrecnormal());
            Cst.setString(12, getPromocion());
            Cst.setInt(13, getActivaprom());
            Cst.setInt(14, getRegalo());
            Cst.setInt(15, getStock());
            Cst.setInt(16, getStockmin());
            Cst.setString(17, getCompsc());
            Cst.setBigDecimal(18,getBono());
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

    public static void setOp(String aOp) {
        op = aOp;
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

    public static int getCodlabor() {
        return codlabor;
    }

    public static String getPresent() {
        return present;
    }

    public static String getDescrip() {
        return descrip;
    }

    public static String getFraccionable() {
        return fraccionable;
    }

    public static int getUnidxcaja() {
        return unidxcaja;
    }

    public static BigDecimal getPreccosto() {
        return preccosto;
    }

    public static BigDecimal getPrecmin() {
        return precmin;
    }

    public static BigDecimal getPrecnormal() {
        return precnormal;
    }

    public static String getPromocion() {
        return promocion;
    }

    public static int getActivaprom() {
        return activaprom;
    }

    public static int getRegalo() {
        return regalo;
    }

    public static int getStock() {
        return stock;
    }

    public static int getStockmin() {
        return stockmin;
    }

    public static String getCompsc() {
        return compsc;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setNomb(String aNomb) {
        nomb = aNomb;
    }

    public static void setCodlabor(int aCodlabor) {
        codlabor = aCodlabor;
    }

    public static void setPresent(String aPresent) {
        present = aPresent;
    }

    public static void setDescrip(String aDescrip) {
        descrip = aDescrip;
    }

    public static void setFraccionable(String aFraccionable) {
        fraccionable = aFraccionable;
    }

    public static void setUnidxcaja(int aUnidxcaja) {
        unidxcaja = aUnidxcaja;
    }

    public static void setPreccosto(BigDecimal aPreccosto) {
        preccosto = aPreccosto;
    }

    public static void setPrecmin(BigDecimal aPrecmin) {
        precmin = aPrecmin;
    }

    public static void setPrecnormal(BigDecimal aPrecnormal) {
        precnormal = aPrecnormal;
    }

    public static void setPromocion(String aPromocion) {
        promocion = aPromocion;
    }

    public static void setActivaprom(int aActivaprom) {
        activaprom = aActivaprom;
    }

    public static void setRegalo(int aRegalo) {
        regalo = aRegalo;
    }

    public static void setStock(int aStock) {
        stock = aStock;
    }

    public static void setStockmin(int aStockmin) {
        stockmin = aStockmin;
    }

    public static void setCompsc(String aCompsc) {
        compsc = aCompsc;
    }

    public static BigDecimal getBono() {
        return bono;
    }

    public static void setBono(BigDecimal aBono) {
        bono = aBono;
    }

 
}
