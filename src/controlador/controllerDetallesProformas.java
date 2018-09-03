package controlador;

import java.math.BigDecimal;
import java.sql.ResultSet;
import modelo.modelDetalleProforma;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerDetallesProformas {
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
    //  public static ResultSet consultarClientes(DefaultTableModel dtm) {
//        dtm = new DefaultTableModel() {
//            public boolean isCellEditable(int fila, int column) {
//                return false;
//            }
//        };
//        //COLUMNAS DE LA TABLA
//        Clientes.jTableClientes.setModel(dtm);
//        dtm.addColumn("###");
//        dtm.addColumn("Cliente");
//        dtm.addColumn("Dni");
//        dtm.addColumn("Ruc");
//        dtm.addColumn("Direccion");
//        dtm.addColumn("Fono");
//
//
//        try {
//Clientes.jTableClientes.getColumnModel().getColumn(3).setMaxWidth(0);
//Clientes.jTableClientes.getColumnModel().getColumn(3).setMinWidth(0);
//Clientes.jTableClientes.getColumnModel().getColumn(3).setWidth(0);
//Clientes.jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(0);
//            modelClientes.setOp(getOp());
//            modelClientes.setBusc(getBusc());
//            ResultSet rs = modelClientes.queryClientes();
//            while (rs.next()) {
//                Object[] obj = new Object[6];
//                for (int i = 0; i < 6; i++) {
//                    obj[i] = rs.getString(i + 1);
//
//                }
//                dtm.addRow(obj);
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    
//        public static String consultarClientes() {
//         try {
//
//            modelClientes.setOp(getOp());
//            modelClientes.setBusc(getBusc());
//            ResultSet rs = modelClientes.queryClientes();
//            while (rs.next()) {
//                            
//                   PedidoInterno.Dni.setText(rs.getString("DNI"));             
//                PedidoInterno.Direccion.setText(rs.getString("DIRECCION"));
//                           PedidoInterno.codCliente.setText(rs.getString("###"));
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }   

    public static ResultSet mntoDetallesProformas() {
        modelDetalleProforma.setOp(getOp());
        modelDetalleProforma.setCab(getCab());
        modelDetalleProforma.setDet(getDet());
        modelDetalleProforma.setCodM(getCodM());
        modelDetalleProforma.setPrecioC(getPrecioC());
        modelDetalleProforma.setPrecioV(getPrecioV());
        modelDetalleProforma.setCantV(getCantV());
        modelDetalleProforma.setTotalV(getTotalV());
        modelDetalleProforma.setUniMed(getUniMed());
        modelDetalleProforma.setBono(getBono());
        return modelDetalleProforma.mntoDetalleProformas();

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
