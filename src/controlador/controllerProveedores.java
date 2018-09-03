package controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.modelProveedores;
import vista.Compras;
import vista.MyRenderer;
import vista.Proveedores;
import vista.main;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerProveedores {
       private static String op;
    private static int cod;
    private static String busc;    
    private static String nomb;
    private static String dir;
    private static String fono;
    private static String cel;
    private static String correo;
      private static String ruc;
     public static ResultSet consultarProveedor(String tabla,DefaultTableModel dtm) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int column) {
                return false;
            }
        };
       
        //COLUMNAS DE LA TABLA
        if(tabla.equals("PROVEE")){
        Proveedores.jTablepoveedor.setModel(dtm);
        dtm.addColumn("###");
        dtm.addColumn("Proveedor");
         dtm.addColumn("RUC");
        dtm.addColumn("Direccion");
        dtm.addColumn("Telefono");
        dtm.addColumn("celular");
        dtm.addColumn("Correo");
        Proveedores.jTablepoveedor.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Proveedores.jTablepoveedor.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Proveedores.jTablepoveedor.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Proveedores.jTablepoveedor.getColumnModel().getColumn(3).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Proveedores.jTablepoveedor.getColumnModel().getColumn(4).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Proveedores.jTablepoveedor.getColumnModel().getColumn(5).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Proveedores.jTablepoveedor.getColumnModel().getColumn(6).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        
        }else{
        Compras.jTableProveedores.setModel(dtm);
              dtm.addColumn("###");
        dtm.addColumn("Proveedor");
         dtm.addColumn("RUC");
        dtm.addColumn("Direccion");
        dtm.addColumn("Telefono");
        dtm.addColumn("celular");
        dtm.addColumn("Correo");
        
Compras.jTableProveedores.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Compras.jTableProveedores.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Compras.jTableProveedores.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Compras.jTableProveedores.getColumnModel().getColumn(3).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Compras.jTableProveedores.getColumnModel().getColumn(4).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Compras.jTableProveedores.getColumnModel().getColumn(5).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
Compras.jTableProveedores.getColumnModel().getColumn(6).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));

        }

        try {
   modelProveedores.setOp(getOp());
            modelProveedores.setBusc(getBusc());
            ResultSet rs = modelProveedores.queryProveedores();
            while (rs.next()) {
                Object[] obj = new Object[7];
                for (int i = 0; i < 7; i++) {
                    obj[i] = rs.getString(i + 1);

                }
                dtm.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

              return null;
    }
     
           public static String consultarProveedores() {
         try {

            modelProveedores.setOp(getOp());
            modelProveedores.setBusc(getBusc());
            ResultSet rs = modelProveedores.queryProveedores();
            while (rs.next()) {
                            
                   Compras.ruc.setText(rs.getString("RUC"));             
                Compras.Direccion.setText(rs.getString("DIRECCION"));
                           Compras.codProvee.setText(rs.getString("###"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String mntoProveedor() {

       modelProveedores.setOp(getOp());
        modelProveedores.setCod(getCod());
        modelProveedores.setNomb(getNomb());
        modelProveedores.setCel(getCel());
        modelProveedores.setDir(getDir());
        modelProveedores.setCorreo(getCorreo());
        modelProveedores.setFono(getFono());
    modelProveedores.setRuc(getRuc());
        return modelProveedores.mntoProveedores();

    }

    public static String getOp() {
        return op;
    }

    public static int getCod() {
        return cod;
    }

    public static String getNomb() {
        return nomb;
    }

    public static String getDir() {
        return dir;
    }

    public static String getFono() {
        return fono;
    }

    public static String getCel() {
        return cel;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setNomb(String aNomb) {
        nomb = aNomb;
    }

    public static void setDir(String aDir) {
        dir = aDir;
    }

    public static void setFono(String aFono) {
        fono = aFono;
    }

    public static void setCel(String aCel) {
        cel = aCel;
    }

    public static void setCorreo(String aCorreo) {
        correo = aCorreo;
    }

    public static String getBusc() {
        return busc;
    }

    public static void setBusc(String aBusc) {
        busc = aBusc;
    }

    public static String getRuc() {
        return ruc;
    }

    public static void setRuc(String aRuc) {
        ruc = aRuc;
    }
}
