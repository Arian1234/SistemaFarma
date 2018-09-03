package controlador;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.modelClientes;
import vista.Clientes;
import vista.MyRenderer;
import vista.PedidoInterno;
import vista.main;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerClientes {

    private static String op;
    private static int cod;
    private static String busc;
    private static String nomb;
    private static String dni;
    private static String ruc;
    private static String dir;
    private static String fono;

    public static ResultSet consultarClientes(DefaultTableModel dtm) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int column) {
                return false;
            }
        };
        //COLUMNAS DE LA TABLA
        dtm.addColumn("###");
        dtm.addColumn("Cliente");
        dtm.addColumn("Dni");
        dtm.addColumn("Ruc");
        dtm.addColumn("Direccion");
        dtm.addColumn("Fono");
         Clientes.jTableClientes.setModel(dtm);
        try {
            Clientes.jTableClientes.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            Clientes.jTableClientes.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            Clientes.jTableClientes.getColumnModel().getColumn(3).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            Clientes.jTableClientes.getColumnModel().getColumn(4).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            Clientes.jTableClientes.getColumnModel().getColumn(5).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
//            Clientes.jTableClientes.getColumnModel().getColumn(3).setMaxWidth(0);
//            Clientes.jTableClientes.getColumnModel().getColumn(3).setMinWidth(0);
//            Clientes.jTableClientes.getColumnModel().getColumn(3).setWidth(0);
//            Clientes.jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(0);
            Clientes.jTableClientes.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));

            modelClientes.setOp(getOp());
            modelClientes.setBusc(getBusc());
            ResultSet rs = modelClientes.queryClientes();
            while (rs.next()) {
                Object[] obj = new Object[6];
                for (int i = 0; i < 6; i++) {
                    obj[i] = rs.getString(i + 1);

                }
                dtm.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String consultarClientes() {
        try {

            modelClientes.setOp(getOp());
            modelClientes.setBusc(getBusc());
            ResultSet rs = modelClientes.queryClientes();
            while (rs.next()) {

                PedidoInterno.Dni.setText(rs.getString("DNI"));
                 PedidoInterno.RUC.setText(rs.getString("RUC"));
                PedidoInterno.Direccion.setText(rs.getString("DIRECCION"));
                PedidoInterno.codClienteC.setText(rs.getString("###"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String mntoClientes() {

        modelClientes.setOp(getOp());
        modelClientes.setCod(getCod());
        modelClientes.setNomb(getNomb());
        modelClientes.setDni(getDni());
        modelClientes.setDir(getDir());
        modelClientes.setRuc(getRuc());
        modelClientes.setDir(getDir());
        modelClientes.setFono(getFono());
        return modelClientes.mntoClientes();

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

    public static String getDni() {
        return dni;
    }

    public static String getRuc() {
        return ruc;
    }

    public static String getDir() {
        return dir;
    }

    public static String getFono() {
        return fono;
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

    public static void setDni(String aDni) {
        dni = aDni;
    }

    public static void setRuc(String aRuc) {
        ruc = aRuc;
    }

    public static void setDir(String aDir) {
        dir = aDir;
    }

    public static void setFono(String aFono) {
        fono = aFono;
    }

    public static String getBusc() {
        return busc;
    }

    public static void setBusc(String aBusc) {
        busc = aBusc;
    }
}
