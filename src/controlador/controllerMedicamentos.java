package controlador;

import java.awt.Color;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JEditorPane;
import javax.swing.table.DefaultTableModel;
import modelo.modelMedicamentos;
import vista.BuscarMedicamentos;
import vista.Compras;
import vista.Formulas;
import vista.Medicamentos;
import vista.MyRenderer;
import vista.PedidoInterno;
import vista.main;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerMedicamentos {

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
 
    public static ResultSet consultarMedicamentos(String clase, DefaultTableModel dtm) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int column) {
                return false;
            }
        };
        //COLUMNAS DE LA TABLA
        if (clase.equals("MEDIC")) {
            Medicamentos.jTableMedicamentos.setModel(dtm);
         
               
            
        } else {
            if (clase.equals("COMPRAS")) {
                Compras.jTableMedicamentos.setModel(dtm);
            
            } else {
                PedidoInterno.jTableMedicamentos.setModel(dtm);
          
            }
        }
        dtm.addColumn("###");
         Preferences prefs;
        prefs = Preferences.userRoot().node("configuracion");
        
         if (prefs.get("sistema", "farma").equals("comerc")) {
        dtm.addColumn("Prodc.");
         }else{
        dtm.addColumn("Medic.");  
         }
       
        dtm.addColumn("Laborat.");
        dtm.addColumn("Present.");
        dtm.addColumn("Descripc.");
        dtm.addColumn("Fracc.");

        dtm.addColumn("UndxCaja");
        dtm.addColumn("Costo");
        dtm.addColumn("Minimo");
        dtm.addColumn("Normal");
        dtm.addColumn("Unidad");

        dtm.addColumn("Prmc");
        dtm.addColumn("Aplica-desde");
        dtm.addColumn("Adicional");
        dtm.addColumn("Stock");
        dtm.addColumn("StockDetall.");
        dtm.addColumn("Alerta");
        dtm.addColumn("COMPSC");
        dtm.addColumn("Bono");
        try {
            if (clase.equals("MEDIC")) {
                Medicamentos.jTableMedicamentos.getColumnModel().getColumn(17).setMaxWidth(0);
                Medicamentos.jTableMedicamentos.getColumnModel().getColumn(17).setMinWidth(0);
                Medicamentos.jTableMedicamentos.getColumnModel().getColumn(17).setPreferredWidth(0);
                Medicamentos.jTableMedicamentos.getColumnModel().getColumn(17).setWidth(0);
                  for(int x=0;x<19;x++){
                Medicamentos.jTableMedicamentos.getColumnModel().getColumn(x).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
               
            } 
            } else {
                if (clase.equals("COMPRAS")) {
                    int xz = 2;
                    while (xz < 19) {
                        Compras.jTableMedicamentos.getColumnModel().getColumn(xz).setMaxWidth(0);
                        Compras.jTableMedicamentos.getColumnModel().getColumn(xz).setMinWidth(0);
                        Compras.jTableMedicamentos.getColumnModel().getColumn(xz).setPreferredWidth(0);
                        Compras.jTableMedicamentos.getColumnModel().getColumn(xz).setWidth(0);
                        xz++;
                    }
                           for(int x=0;x<19;x++){
                Compras.jTableMedicamentos.getColumnModel().getColumn(x).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            } 
                } else {
                    //AQUI MODIFICO MI TABLA MEDICAMENTOS EN VENTA(COLUMNAS)   
                    int x = 2;
                    while (x < 19) {
                        PedidoInterno.jTableMedicamentos.getColumnModel().getColumn(x).setMaxWidth(0);
                        PedidoInterno.jTableMedicamentos.getColumnModel().getColumn(x).setMinWidth(0);
                        PedidoInterno.jTableMedicamentos.getColumnModel().getColumn(x).setPreferredWidth(0);
                        PedidoInterno.jTableMedicamentos.getColumnModel().getColumn(x).setWidth(0);
                        x++;
                    }
                                  for(int xx=0;xx<19;xx++){
                PedidoInterno.jTableMedicamentos.getColumnModel().getColumn(xx).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            } 
                }
            }
            modelMedicamentos.setOp(getOp());
            modelMedicamentos.setBusc(getBusc());
            ResultSet rs = modelMedicamentos.queryMedicamentos();
            while (rs.next()) {
                Object[] obj = new Object[19];
                for (int i = 0; i < 19; i++) {
                    obj[i] = rs.getString(i + 1);

                }
                dtm.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet consultarMedicamentosStock(JEditorPane jep) {

        String ss = null;
        try {

            modelMedicamentos.setOp(getOp());
            modelMedicamentos.setBusc(getBusc());
            ResultSet rs = modelMedicamentos.queryMedicamentos();
            jep.setText(null);
            while (rs.next()) {

                jep.setText(jep.getText() + "[ " + rs.getString("###") + " ]   [ "
                        + rs.getString("MEDICAMENTO") + " ]   [ "
                        + rs.getString("LABORATORIO") + " ]   [ "
                        + rs.getString("STOCK DETALLADO") + " ]   [ "
                        + rs.getString("ESTADO") + " ]"
                        + "\n");
            }

        } catch (Exception ex) {
            Logger.getLogger(controllerMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet consultarMedicamentosDetalles(String op, DefaultTableModel dtm) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int column) {
                return false;
            }
        };
        //COLUMNAS DE LA TABLA
        if (op.equals("MEDICAMENTOS")) {
            Medicamentos.jTableDetallesComp.setModel(dtm);
        } else {
            PedidoInterno.jTableDetallesComp.setModel(dtm);
        }
        dtm.addColumn("###");
        dtm.addColumn("CodCompuesto");
        dtm.addColumn("Compuesto");
        if (op.equals("MEDICAMENTOS")) {
            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(0).setMaxWidth(0);
            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(0).setMinWidth(0);
            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(0).setPreferredWidth(0);
            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(0).setWidth(0);

            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(1).setMaxWidth(0);
            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(1).setMinWidth(0);
            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(1).setPreferredWidth(0);
            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(1).setWidth(0);
            
            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            Medicamentos.jTableDetallesComp.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        } else {
            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(0).setMaxWidth(0);
            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(0).setMinWidth(0);
            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(0).setPreferredWidth(0);
            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(0).setWidth(0);

            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(1).setMaxWidth(0);
            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(1).setMinWidth(0);
            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(1).setPreferredWidth(0);
            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(1).setWidth(0);
            
            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            PedidoInterno.jTableDetallesComp.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        }
        try {
            modelMedicamentos.setOp(getOp());
            modelMedicamentos.setBusc(getBusc());
            ResultSet rs = modelMedicamentos.queryMedicamentos();
            while (rs.next()) {
                Object[] obj = new Object[3];
                for (int i = 0; i < 3; i++) {
                    obj[i] = rs.getString(i + 1);

                }
                dtm.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet consultarMedicamentosOpcionales(DefaultTableModel dtm) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int column) {
                return false;
            }
        };
        //COLUMNAS DE LA TABLA
         Medicamentos.jTableMedicamentosOp.setModel(dtm);
        dtm.addColumn("###");
Preferences prefs;
        prefs = Preferences.userRoot().node("configuracion");
        if (prefs.get("sistema", "farma").equals("comerc")) {
        dtm.addColumn("Productos"); 
        }else{
        dtm.addColumn("Medicamentos"); 
        }
       
       

        try {

            Medicamentos.jTableMedicamentosOp.getColumnModel().getColumn(0).setMaxWidth(0);
            Medicamentos.jTableMedicamentosOp.getColumnModel().getColumn(0).setMinWidth(0);
            Medicamentos.jTableMedicamentosOp.getColumnModel().getColumn(0).setPreferredWidth(0);
            Medicamentos.jTableMedicamentosOp.getColumnModel().getColumn(0).setWidth(0);

             Medicamentos.jTableMedicamentosOp.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
             Medicamentos.jTableMedicamentosOp.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            modelMedicamentos.setOp(getOp());
            modelMedicamentos.setBusc(getBusc());
            ResultSet rs = modelMedicamentos.queryMedicamentos();
            while (rs.next()) {
                Object[] obj = new Object[2];
                for (int i = 0; i < 2; i++) {
                    obj[i] = rs.getString(i + 1);

                }
                dtm.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet consultarMedicamentosOpcionalesDetallado(DefaultTableModel dtm) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int column) {
                return false;
            }
        };
        //COLUMNAS DE LA TABLA

        PedidoInterno.jTableMedicamentosOp.setModel(dtm);
        dtm.addColumn("###");
        dtm.addColumn("Productos");
        dtm.addColumn("Laborat o marca");
        dtm.addColumn("Presentacion");
        dtm.addColumn("P Minimo");
        dtm.addColumn("P Normal");
        dtm.addColumn("P Unitario");
        dtm.addColumn("Bonificacion");

        try {

            PedidoInterno.jTableMedicamentosOp.getColumnModel().getColumn(0).setMaxWidth(0);
            PedidoInterno.jTableMedicamentosOp.getColumnModel().getColumn(0).setMinWidth(0);
            PedidoInterno.jTableMedicamentosOp.getColumnModel().getColumn(0).setPreferredWidth(0);
            PedidoInterno.jTableMedicamentosOp.getColumnModel().getColumn(0).setWidth(0);

            modelMedicamentos.setOp(getOp());
            modelMedicamentos.setBusc(getBusc());
            ResultSet rs = modelMedicamentos.queryMedicamentos();
            while (rs.next()) {
                Object[] obj = new Object[8];
                for (int i = 0; i < 8; i++) {
                    obj[i] = rs.getString(i + 1);

                }
                dtm.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ResultSet consultarMedicamentosBuscadosXCompuestos(DefaultTableModel dtm) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int column) {
                return false;
            }
        };
        //COLUMNAS DE LA TABLA
        BuscarMedicamentos.jTableMedicamentos.setModel(dtm);
        dtm.addColumn("###");
        dtm.addColumn("Medicamentos");
        dtm.addColumn("Laboratorio");
        dtm.addColumn("Presentacion");
        dtm.addColumn("Descripcion");
        dtm.addColumn("Fraccionable");
        dtm.addColumn("Und-caja");
        dtm.addColumn("P Minimo");
        dtm.addColumn("P Normal");
        dtm.addColumn("P Unitario");
        dtm.addColumn("Promo");
        dtm.addColumn("AplicaDesde");
        dtm.addColumn("Adicional");
        dtm.addColumn("Stock");
        dtm.addColumn("BonoUnidad");
        try {
for(int z=0;z<15;z++){
BuscarMedicamentos.jTableMedicamentos.getColumnModel().getColumn(z).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));               
            }

            modelMedicamentos.setOp(getOp());
            modelMedicamentos.setBusc(getBusc());
            ResultSet rs = modelMedicamentos.queryMedicamentos();
            while (rs.next()) {
                Object[] obj = new Object[15];
                for (int i = 0; i < 15; i++) {
                    obj[i] = rs.getString(i + 1);

                }
                dtm.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String consultarCodigoMedicamento() {
        try {

            modelMedicamentos.setOp(getOp());
            modelMedicamentos.setBusc(getBusc());
            ResultSet rs = modelMedicamentos.queryMedicamentos();
            if (rs.next()) {
                System.out.println(rs.getString("###"));
                return rs.getString("###");
            }
            return null;
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static String consultarMedicamentosXCodigo() {
        try {

            modelMedicamentos.setOp(getOp());
            modelMedicamentos.setBusc(getBusc());
            ResultSet rs = modelMedicamentos.queryMedicamentos();
            if (rs.next()) {
                System.out.println(rs.getString("###"));
                Formulas.txtcodlabor.setText(rs.getString("###"));
                Formulas.Costo.setText(rs.getString("COSTO"));
                Formulas.Precio.setText(rs.getString("NORMAL"));
                
                Formulas.laboratorio.setText(rs.getString("LABORATORIO"));
                Formulas.Presentacion.setText(rs.getString("PRESENTACION"));
                Formulas.Descripcion.setText(rs.getString("DESCRIPCION"));
                Formulas.Fraccionable.setText(rs.getString("FRACCIONABLE"));
                Formulas.CantXPaq.setText(rs.getString("UNIDS-CAJA"));
                Formulas.PrecioUnit.setText(rs.getString("PRECIO UNITARIO"));
                Formulas.Stock.setText(rs.getString("STOCK"));
                Formulas.StockDetallado.setText(rs.getString("STOCKDETALLADO"));
                
                
                
                return rs.getString("###");
            }
            return null;
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String mntoMedicamentos() {

        modelMedicamentos.setOp(getOp());
        modelMedicamentos.setCod(getCod());
        modelMedicamentos.setNomb(getNomb());
        modelMedicamentos.setCodlabor(getCodlabor());
        modelMedicamentos.setPresent(getPresent());
        modelMedicamentos.setDescrip(getDescrip());
        modelMedicamentos.setFraccionable(getFraccionable());
        modelMedicamentos.setUnidxcaja(getUnidxcaja());
        modelMedicamentos.setPreccosto(getPreccosto());
        modelMedicamentos.setPrecmin(getPrecmin());
        modelMedicamentos.setPrecnormal(getPrecnormal());
        modelMedicamentos.setPromocion(getPromocion());
        modelMedicamentos.setActivaprom(getActivaprom());
        modelMedicamentos.setRegalo(getRegalo());
        modelMedicamentos.setStock(getStock());
        modelMedicamentos.setStockmin(getStockmin());
        modelMedicamentos.setCompsc(getCompsc());
        modelMedicamentos.setBono(getBono());
        return modelMedicamentos.mntoMedicamentos();

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
