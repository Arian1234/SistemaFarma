package controlador;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.modelCompuestos;
import vista.Compuestos;
import vista.MyRenderer;
import vista.main;

/**
 *
 * @author AMolinaDeveloper
 */
public class controllerCompuestos {

    private static String op;
    private static String busc;
    private static int cod;
    private static String nomb;
    private static String descrip;

    public static ResultSet consultarCompuestos(DefaultTableModel dtm) {
        dtm = new DefaultTableModel() {
            public boolean isCellEditable(int fila, int column) {
                return false;
            }
        };
              //COLUMNAS DE LA TABLA
        Compuestos.jTableCompuestos.setModel(dtm);
        dtm.addColumn("###");
        dtm.addColumn("Compuesto");
        dtm.addColumn("Descripcion");
        try {
 Compuestos.jTableCompuestos.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
  Compuestos.jTableCompuestos.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
   Compuestos.jTableCompuestos.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
  
            modelCompuestos.setOp(getOp());
            modelCompuestos.setBusc(getBusc());
            ResultSet rs = modelCompuestos.queryCompuestos();
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
        public static ResultSet consultarCompuestos() {
        try {

            modelCompuestos.setOp(getOp());
            modelCompuestos.setBusc(getBusc());
            ResultSet rs =  modelCompuestos.queryCompuestos();
            if(rs.next()){
           System.out.println(rs.getString("###"));
            return rs;
            }
return null;
        } catch ( Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String mntoCompuestos() {

        modelCompuestos.setOp(getOp());
        modelCompuestos.setCod(getCod());
        modelCompuestos.setNomb(getNomb());
        modelCompuestos.setDescrip(getDescrip());
        return modelCompuestos.mntoCompuestos();

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
