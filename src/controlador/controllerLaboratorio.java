package controlador;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.modelLaboratorio;
import vista.Laboratorios;
import vista.MyRenderer;
import vista.main;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerLaboratorio {

    private static String op;
    private static String busc;
    private static int cod;
    private static String nomb;
    private static String descrip;

    public static ResultSet consultarLaboratorio(String lab, DefaultTableModel dtm) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int column) {
                return false;
            }
        };

        //COLUMNAS DE LA TABLA
        if (lab.equals("laboratorio")) {
            Laboratorios.jTableLaborat.setModel(dtm);
        } else {

            main.jTableLaboratorios.setModel(dtm);
        }
        dtm.addColumn("###");
        dtm.addColumn("Laboratorio");
        dtm.addColumn("Descripcion");
        try {
            if (lab.equals("laboratorio")) {
                Laboratorios.jTableLaborat.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
                Laboratorios.jTableLaborat.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
                Laboratorios.jTableLaborat.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            } else {
                main.jTableLaboratorios.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
                main.jTableLaboratorios.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
                main.jTableLaboratorios.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));

            }

            modelLaboratorio.setOp(getOp());
            modelLaboratorio.setBusc(getBusc());
            ResultSet rs = modelLaboratorio.queryLaboratorio();
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

        //y para finalizar la hacemos visible
        return null;
    }

    public static String mntoLaboratorio() {

        modelLaboratorio.setOp(getOp());
        modelLaboratorio.setCod(getCod());
        modelLaboratorio.setNomb(getNomb());
        modelLaboratorio.setDescrip(getDescrip());
        return modelLaboratorio.mntoLaboratorio();

    }

    public static String consultarLaboratorio() {
        try {

            modelLaboratorio.setOp(getOp());
            modelLaboratorio.setBusc(getBusc());
            ResultSet rs = modelLaboratorio.queryLaboratorio();
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

    public static String getOp() {
        return op;
    }

    public static String getBusc() {
        return busc;
    }

    public static int getCod() {
        return cod;
    }

    public static String getNomb() {
        return nomb;
    }

    public static String getDescrip() {
        return descrip;
    }

    public static void setOp(String aOp) {
        op = aOp;
    }

    public static void setBusc(String aBusc) {
        busc = aBusc;
    }

    public static void setCod(int aCod) {
        cod = aCod;
    }

    public static void setNomb(String aNomb) {
        nomb = aNomb;
    }

    public static void setDescrip(String aDescrip) {
        descrip = aDescrip;
    }
}
