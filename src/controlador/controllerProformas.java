package controlador;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.modelProformas;
import vista.PedidoInterno;
import vista.main;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerProformas {

    private static String op;
    private static int cod;
    private static String codigo;
private static int cant;
private static int cantxcaja;
    private static int codClie;
    private static int codUsu;
    private static String cabecera;
    private static String detalle;
    private static BigDecimal total;
    private static String codVenta;
public static String mntoActualizarProforma(){
    modelProformas.setOp(getOp());
    modelProformas.setCodVenta(getCodVenta());
    
        return modelProformas.mntoActualizarProforma();
}
    public static String mntoProformas() {
        modelProformas.setOp(getOp());
        modelProformas.setCod(getCod());
        modelProformas.setCodClie(getCodClie());
        modelProformas.setCodUsu(getCodUsu());
        modelProformas.setTotal(getTotal());
        return modelProformas.mntoProformas();

    }

    
    public static void consultarMaestroProformas() {
        try {        

            modelProformas.setOp(getOp());
            modelProformas.setCabecera(getCabecera());
            modelProformas.setDetalle(getDetalle());
            ResultSet rs = modelProformas.queryProformas();
            while (rs.next()) {
              PedidoInterno.codClienteC.setText(rs.getString("###"));
              PedidoInterno.Cliente.setText(rs.getString("CLIENTE"));
              PedidoInterno.Dni.setText(rs.getString("DNI"));
              PedidoInterno.Direccion.setText(rs.getString("DIRECCION"));
              PedidoInterno.lblimporte.setText(rs.getString("TOTAL"));
              if(main.lblpermisos.getText().equals("ADMIN")){  
              PedidoInterno.jMenuAnular.setEnabled(true);}
            }

        
        } catch (SQLException ex) {
            Logger.getLogger(controllerProformas.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public static ResultSet consultarProformas(DefaultTableModel dtm,String permiso) {
     
        dtm = new DefaultTableModel() {
            
            
             boolean[] canEdit = new boolean [] {
                false, false, true, false, true, false, false, false, false, false, false, false,false
            };
            
            public boolean isCellEditable(int fila, int column) {
               return canEdit [column];
            }
        };
 
        //COLUMNAS DE LA TABLA
        PedidoInterno.jTablePedidoInterno.setModel(dtm);
       
        dtm.addColumn("###");
        dtm.addColumn("Medicamento");
        dtm.addColumn("Cantidad");
        dtm.addColumn("Unid.");
        dtm.addColumn("Precio");
        dtm.addColumn("Total");
        
        dtm.addColumn("cantTotal");
        dtm.addColumn("costo");
        dtm.addColumn("displays");
        dtm.addColumn("Bonificacion");
        dtm.addColumn("Caja");
        dtm.addColumn("BonoXUnid");
        dtm.addColumn("C##");
        
        try {
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(6).setMaxWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(6).setMinWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(6).setWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(6).setPreferredWidth(0);

PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(7).setMaxWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(7).setMinWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(7).setWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(7).setPreferredWidth(0);

PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(8).setMaxWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(8).setMinWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(8).setWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(8).setPreferredWidth(0);

PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(9).setMaxWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(9).setMinWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(9).setWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(9).setPreferredWidth(0);

PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(10).setMaxWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(10).setMinWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(10).setWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(10).setPreferredWidth(0);

PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(11).setMaxWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(11).setMinWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(11).setWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(11).setPreferredWidth(0);

PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(12).setMaxWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(12).setMinWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(12).setWidth(0);
PedidoInterno.jTablePedidoInterno.getColumnModel().getColumn(12).setPreferredWidth(0);
            modelProformas.setOp(getOp());
            modelProformas.setCabecera(getCabecera());
            modelProformas.setDetalle(getDetalle());
            ResultSet rs = modelProformas.queryProformas();
            while (rs.next()) {
                Object[] obj = new Object[13];
                for (int i = 0; i < 13; i++) {
                    obj[i] = rs.getString(i + 1);

                }
                System.out.println(rs.getString("C##")+" Soy el codigo del maestro");
                PedidoInterno.lblcodVenta.setText(rs.getString("C##"));
                dtm.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(controllerProformas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   

    public static String getOp() {
        return op;
    }

    public static int getCod() {
        return cod;
    }

    public static int getCodClie() {
        return codClie;
    }

    public static int getCodUsu() {
        return codUsu;
    }

    public static BigDecimal getTotal() {
        return total;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setCodClie(int aCodClie) {
        codClie = aCodClie;
    }

    public static void setCodUsu(int aCodUsu) {
        codUsu = aCodUsu;
    }

    public static void setTotal(BigDecimal aTotal) {
        total = aTotal;
    }

    public static String getCabecera() {
        return cabecera;
    }

    public static String getDetalle() {
        return detalle;
    }

    public static void setCabecera(String aCabecera) {
        cabecera = aCabecera;
    }

    public static void setDetalle(String aDetalle) {
        detalle = aDetalle;
    }

    public static String getCodVenta() {
        return codVenta;
    }

    public static void setCodVenta(String aCodVenta) {
        codVenta = aCodVenta;
    }

    public static String getCodigo() {
        return codigo;
    }

    public static int getCant() {
        return cant;
    }

    public static int getCantxcaja() {
        return cantxcaja;
    }

    public static void setCodigo(String aCodigo) {
        codigo = aCodigo;
    }

    public static void setCant(int aCant) {
        cant = aCant;
    }

    public static void setCantxcaja(int aCantxcaja) {
        cantxcaja = aCantxcaja;
    }
}
