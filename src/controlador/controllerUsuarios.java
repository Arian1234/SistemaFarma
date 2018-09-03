package controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.modelUsuarios;
import vista.MyRenderer;
import vista.main;
import vista.usuarios;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerUsuarios {

    private static String op;
    private static String user;
    private static String pwd;
    private static String busc;
    private static int cod;
    private static String nomb;
    private static String dni;
    private static String dir;
    private static String cel;
    private static String permisos;

    public static ResultSet consultarSession() {

        modelUsuarios.setOp(getOp());
        modelUsuarios.setUser(getUser());
        modelUsuarios.setPwd(getPwd());
        modelUsuarios.setBusc(getBusc());
        ResultSet rs;
        rs = modelUsuarios.queryUsuarios();
        return rs;
    }

    public static ResultSet consultarSession(DefaultTableModel dtm, String op, String busc) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int column) {
                return false;
            }
        };
        //COLUMNAS DE LA TABLA
        usuarios.jTableUsuarios.setModel(dtm);
        dtm.addColumn("###");
        dtm.addColumn("Nombres");
        dtm.addColumn("Dni");
        dtm.addColumn("Direccion");
        dtm.addColumn("Celular");
        dtm.addColumn("Permisos");
        dtm.addColumn("Usuario");
        dtm.addColumn("Contraseña");
        dtm.addColumn("Estado");

        try {

            if (!main.lblpermisos.getText().equals("ADMIN")) {
                usuarios.cmbacceso.setEnabled(false);
                usuarios.jMenuNuevo.setEnabled(false);
                usuarios.jMenuIngresar.setEnabled(false);
                usuarios.jMenuItem1.setEnabled(false);
     usuarios.txtbusqueda.setEnabled(false);
            }
            usuarios.jTableUsuarios.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            usuarios.jTableUsuarios.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            usuarios.jTableUsuarios.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            usuarios.jTableUsuarios.getColumnModel().getColumn(3).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            usuarios.jTableUsuarios.getColumnModel().getColumn(4).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            usuarios.jTableUsuarios.getColumnModel().getColumn(5).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            usuarios.jTableUsuarios.getColumnModel().getColumn(6).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            usuarios.jTableUsuarios.getColumnModel().getColumn(7).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            usuarios.jTableUsuarios.getColumnModel().getColumn(8).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            modelUsuarios.setBusc(main.lblpermisos.getText().equals("ADMIN") ? "" : main.lblusuario.getText());
            modelUsuarios.setOp(main.lblpermisos.getText().equals("ADMIN") ? "D" : "E");
            modelUsuarios.setUser("");
            modelUsuarios.setPwd("");
            ResultSet rs = modelUsuarios.queryUsuarios();
            while (rs.next()) {
                Object[] obj = new Object[9];
                for (int i = 0; i < 9; i++) {
                    obj[i] = rs.getString(i + 1);

                }
                dtm.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //y para finalizar la hacemos visible

        return null;
    }

    public static String mntoUsuarios() {

        modelUsuarios.setOp(getOp());
        modelUsuarios.setCod(getCod());
        modelUsuarios.setNomb(getNomb());
        modelUsuarios.setDni(getDni());
        modelUsuarios.setDir(getDir());
        modelUsuarios.setCel(getCel());
        modelUsuarios.setPermisos(getPermisos());
        modelUsuarios.setUser(getUser());
        modelUsuarios.setPwd(getPwd());
        return modelUsuarios.mntoUsuarios();

    }

    public static String getOp() {
        return op;
    }

    public static String getUser() {
        return user;
    }

    public static String getPwd() {
        return pwd;
    }

    public static String getBusc() {
        return busc;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setUser(String aUser) {
        user = aUser;
    }

    public static void setPwd(String aPwd) {
        pwd = aPwd;
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

    public static String getDni() {
        return dni;
    }

    public static String getDir() {
        return dir;
    }

    public static String getCel() {
        return cel;
    }

    public static String getPermisos() {
        return permisos;
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

    public static void setDir(String aDir) {
        dir = aDir;
    }

    public static void setCel(String aCel) {
        cel = aCel;
    }

    public static void setPermisos(String aPermisos) {
        permisos = aPermisos;
    }
}
