package controlador;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.modelDetalleCompras;
import vista.Compras;
import vista.MyRenderer;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerDetalleCompras {

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
    public static String mntoDetallesCompras() {
        modelDetalleCompras.setOp(getOp());
        modelDetalleCompras.setCod(getCod());
        modelDetalleCompras.setCodMed(getCodMed());
        modelDetalleCompras.setPrecio(getPrecio());
        modelDetalleCompras.setCantidad(getCantidad());
        modelDetalleCompras.setCodProvee(getCodProvee());
        modelDetalleCompras.setSerie(getSerie());
        modelDetalleCompras.setPrecioM(getPrecioM());
        modelDetalleCompras.setPrecioN(getPrecioN());
        modelDetalleCompras.setBono(getBono());
        modelDetalleCompras.setCantUnid(getCantUnid());
        modelDetalleCompras.setUnidMed(getUnidMed());
        modelDetalleCompras.setTotal(getTotal());
   return modelDetalleCompras.mntoDetalleCompras();
    }
    
     public static ResultSet mntoActualizarCompras() {
         modelDetalleCompras.setOp(getOp());
     modelDetalleCompras.setProvee(getProvee());
     modelDetalleCompras.setCorrelativo(getCorrelativo());
     return modelDetalleCompras.mntoActualizarCompras();
     }
     public static ResultSet consultarDetallesCompras(String serie,String provee,DefaultTableModel dtm,String permiso) {
     
        dtm = new DefaultTableModel() {
            
            
             boolean[] canEdit = new boolean [] {
                false, false, true, false, true, false, false, false, false, false, false, false,false
            };
            
            public boolean isCellEditable(int fila, int column) {
               return canEdit [column];
            }
        };
 
        //COLUMNAS DE LA TABLA
        Compras.jTableCompras.setModel(dtm);
       
        dtm.addColumn("###");
        dtm.addColumn("Medicamento");
        dtm.addColumn("Cantidad");
        dtm.addColumn("Unid.");
        dtm.addColumn("Precio");
        dtm.addColumn("Total");
        
        dtm.addColumn("precioMinimo");
        dtm.addColumn("precioNormal");
        dtm.addColumn("Bono");
        dtm.addColumn("cantidadTotal");
        dtm.addColumn("C##");
        
        try {
            Compras.jTableCompras.getColumnModel().getColumn(10).setMaxWidth(0);
                Compras.jTableCompras.getColumnModel().getColumn(10).setMinWidth(0);
                Compras.jTableCompras.getColumnModel().getColumn(10).setPreferredWidth(0);
                Compras.jTableCompras.getColumnModel().getColumn(10).setWidth(0);
                  for(int x=0;x<11;x++){
                Compras.jTableCompras.getColumnModel().getColumn(x).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
               
            } 
            modelDetalleCompras.setOp("DETALLE");
            modelDetalleCompras.setCorrelativo(serie);
            modelDetalleCompras.setProvee(provee);
            ResultSet rs=modelDetalleCompras.mntoActualizarCompras();
                      while (rs.next()) {
                Object[] obj = new Object[11];
                for (int i = 0; i < 11; i++) {
                    obj[i] = rs.getString(i + 1);

                }
                System.out.println(rs.getString("C##")+" Soy el codigo del maestro");
                //PedidoInterno.lblcodVenta.setText(rs.getString("C##"));
                dtm.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(controllerDetalleCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
