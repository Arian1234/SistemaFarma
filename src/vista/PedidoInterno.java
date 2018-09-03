package vista;

import com.mxrck.autocompleter.TextAutoCompleter;
import controlador.controllerClientes;
import controlador.controllerDetallesProformas;
import controlador.controllerMedicamentos;
import controlador.controllerProformas;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.EventObject;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableRowSorter;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import modelo.modelClientes;
import modelo.modelProformas;
import vista.img.MyTableCellEditor;

/**
 *
 * @author AMolinaDeveloper
 */
public class PedidoInterno extends javax.swing.JInternalFrame implements KeyListener, TableCellEditor {
private static DefaultTableModel modelo;
    private TableRowSorter trsFiltro;

    //para exportar
    private JFileChooser FileChooser = new JFileChooser();
    private Vector columna = new Vector();
    private Vector filas = new Vector();
    private static int tabla_ancho = 0;
    private static int tabla_alto = 0;
    
    
    
    private int x;
    private int y;
    private static DefaultTableModel dtm;
    private Object[] value = {"Alpha", "Epsilon", "Omega", "Psi", "Beta", "Rho"};
    

    /**
     * Creates new form Ventas
     */
    public PedidoInterno() {
 
        initComponents();
        this.txtbusq.addKeyListener(this);
        this.jTableMedicamentos.addKeyListener(this);
        TextAutoCompleterClientes(Cliente);
        this.jTablePedidoInterno.getColumnModel().getColumn(2).setCellEditor(new MyTableCellEditor());
        this.jTablePedidoInterno.getColumnModel().getColumn(4).setCellEditor(new MyTableCellEditor());
        // Timer();
      //  if (main.lblpermisos.getText().equals("ADMIN")) {

            jTablePedidoInterno.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "###", "Producto", "Cantidad", "Unid.", "Precio", "Total", "cantidadTotal", "costo", "displays", "Bonificacion", "Caja", "BonoXUnid"
                    }
            ) {
                boolean[] canEdit = new boolean[]{
                    false, false, true, false, true, true, false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            });

            jTablePedidoInterno.getColumnModel().getColumn(0).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(1).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(2).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(3).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(4).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(5).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(6).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(6).setMaxWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(7).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(7).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(7).setMaxWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(8).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(8).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(8).setMaxWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(9).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(9).setMaxWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(10).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(10).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(10).setMaxWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(11).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(11).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(11).setMaxWidth(0);
            this.jTablePedidoInterno.getColumnModel().getColumn(2).setCellEditor(new MyTableCellEditor());
            this.jTablePedidoInterno.getColumnModel().getColumn(4).setCellEditor(new MyTableCellEditor());

       // };

        jTablePedidoInterno.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        jTablePedidoInterno.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        jTablePedidoInterno.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        jTablePedidoInterno.getColumnModel().getColumn(3).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        jTablePedidoInterno.getColumnModel().getColumn(4).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        jTablePedidoInterno.getColumnModel().getColumn(5).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));

        
        

   
    }
    
    
   
    public void clears(){
        this.txtmed.setText(null);
        this.txtcabecera.setText(null);
        this.txtdetalle.setText(null);
        this.lblitems.setText("<<SIN DATOS>>");
        this.lblimportetotal.setText("000.00");
        this.lblimporte.setText("000.00");
        this.lblcosto.setText("<<SIN DATOS>>");
        this.lblCod.setText("<<SIN DATOS>>");
        this.lblMedicamento.setText("<<SIN DATOS>>");
        this.lblLabor.setText("<<SIN DATOS>>");
        this.lblPresent.setText("<<SIN DATOS>>");
        this.lblDescrip.setText("<<SIN DATOS>>");
        this.lblStocks.setText("<<SIN DATOS>>");
        this.lblStockDet.setText("<<SIN DATOS>>");
        this.lblDisplay.setText("<<SIN DATOS>>");
        this.lblPrecioMin.setText("<<SIN DATOS>>");
        this.lblPrecioNormal.setText("<<SIN DATOS>>");
        this.lblPrecioUnidad.setText("<<SIN DATOS>>");
        this.lblPromocion.setText("<<SIN DATOS>>");
        this.lblAplicaDesde.setText("<<SIN DATOS>>");
        this.lblBonificacion.setText("<<SIN DATOS>>");
        this.Cliente.setEnabled(true);
        this.Dni.setEnabled(true);
        validator.vaciar_jtable(jTablePedidoInterno);
        validator.vaciar_jtable(jTableMedicamentos);

}
    
        public void CrearTabla(File file) throws IOException {
        modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("DNI");
        PedidoInterno.jTablePedidoInterno.setModel(modelo);
        Workbook workbook = null;

        try {
            workbook = Workbook.getWorkbook(file);

            Sheet sheet = workbook.getSheet(0);
            columna.clear();

            for (int i = 0; i < sheet.getColumns(); i++) {
                Cell cell1 = sheet.getCell(i, 0);
                columna.add(cell1.getContents());
            }
            filas.clear();

            for (int j = 1; j < sheet.getRows(); j++) {

                Vector d = new Vector();

                for (int i = 0; i < sheet.getColumns(); i++) {

                    Cell cell = sheet.getCell(i, j);
                    d.add(cell.getContents());
                }
                d.add("\n");
                //filas.add(d);
                modelo.addRow(d);
            }

        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
public void imprimir(){
int x = 0;
        String detalle = "";
        while (x <= jTablePedidoInterno.getRowCount() - 1) {
            detalle = this.jTablePedidoInterno.getValueAt(x, 0).toString() + "   " + this.jTablePedidoInterno.getValueAt(x, 1).toString() + "   "
                    + this.jTablePedidoInterno.getValueAt(x, 4).toString() + "   "
                    + (this.jTablePedidoInterno.getValueAt(x, 2).toString()) + "" + (this.jTablePedidoInterno.getValueAt(x, 3).toString()) + "   "
                    + (this.jTablePedidoInterno.getValueAt(x, 5).toString()) + " \n" + detalle;
            x++;
        }

        
        
//Fecha actual desglosada:
        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        Ticket a = new Ticket("DIVINO NINO EIRL",
                "Expedio en?",
                this.codClienteC.getText() + "-" + this.Cliente.getText(),
                txtcabecera.getText() + "-" + txtdetalle.getText(),
                main.lblusuario.getText(),
                año + "/" + mes + "/" + dia + "   " + hora + ":" + minuto + ":" + segundo,
                detalle, lblimporte.getText(), " ", " ");
        try {
            //las p solo son para que se cumpla el formato de la clase tu las remplazas por los parametros que quieras
            a.print();
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex);
            Logger.getLogger(PedidoInterno.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public boolean StockOk() {
        System.out.println("la tabla de Pedido interno tiene : " + PedidoInterno.jTablePedidoInterno.getRowCount() + " filas");
        if (PedidoInterno.jTablePedidoInterno.getRowCount() > 0) {
            String codigos = "", codig = "";
            int cant = 0, canti = 0;
            System.out.println(PedidoInterno.jTablePedidoInterno.getRowCount() + " filas de la tabla");

            if (jTablePedidoInterno.getRowCount() > 1) {
                for (int q = 0; q < jTablePedidoInterno.getRowCount(); q++) {
                    codigos = (PedidoInterno.jTablePedidoInterno.getValueAt(q, 0).toString());

                    for (int qq = 0; qq < jTablePedidoInterno.getRowCount(); qq++) {
                        codig = (PedidoInterno.jTablePedidoInterno.getValueAt(qq, 0).toString());
                        if (codigos.equals(codig)) {
                            if ((PedidoInterno.jTablePedidoInterno.getValueAt(qq, 3).toString()).equals("CAJA")) {
                                cant = (Integer.parseInt(PedidoInterno.jTablePedidoInterno.getValueAt(qq, 2).toString()) * Integer.parseInt(PedidoInterno.jTablePedidoInterno.getValueAt(qq, 8).toString())) + cant;
                            } else {
                                cant = Integer.parseInt(PedidoInterno.jTablePedidoInterno.getValueAt(qq, 2).toString()) + cant;
                            }

                        }

                    }

                    System.out.println("cantidad: " + cant + " codigo : " + codigos);

                    controllerDetallesProformas.setOp("C");
                    controllerDetallesProformas.setCab(0);
                    controllerDetallesProformas.setDet(0);
                    controllerDetallesProformas.setCodM(Integer.parseInt(codigos));
                    controllerDetallesProformas.setPrecioC(BigDecimal.ZERO);
                    controllerDetallesProformas.setPrecioV(BigDecimal.ZERO);
                    controllerDetallesProformas.setCantV(cant);
                    controllerDetallesProformas.setTotalV(BigDecimal.ZERO);
                    controllerDetallesProformas.setUniMed("");
                    controllerDetallesProformas.setBono(BigDecimal.ZERO);
                    ResultSet rs = controllerDetallesProformas.mntoDetallesProformas();

                    // main.jEditorPaneAdvertencias.setText(null);
                    try {
                        if (rs.next()) {
                            System.out.println(rs.getString("ESTADO") + " <-ESTADO");
                            // JOptionPane.showMessageDialog(this, rs.getString("ESTADO"), "Stock Limitado", JOptionPane.ERROR_MESSAGE);
                            main.jTabbedPane1.setSelectedIndex(1);
                            main.jEditorPaneAdvertencias.setText(rs.getString("ESTADO") + " LA VENTA NO PODRA SER GENERADA.");
                            //      this.jMenu2.setEnabled(false);
                            return false;
                        } else {
                            main.jTabbedPane1.setSelectedIndex(0);
                            main.jEditorPaneAdvertencias.setText(null);
                        //    this.jMenu2.setEnabled(true);
                            return true;
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                        Logger.getLogger(PedidoInterno.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    cant = 0;
                }
            }
            if (jTablePedidoInterno.getRowCount() == 1) {
                canti = 0;
                if ((PedidoInterno.jTablePedidoInterno.getValueAt(0, 3).toString()).equals("CAJA")) {
                    canti = (Integer.parseInt(PedidoInterno.jTablePedidoInterno.getValueAt(0, 2).toString()) * Integer.parseInt(PedidoInterno.jTablePedidoInterno.getValueAt(0, 8).toString()));
                } else {
                    canti = Integer.parseInt(PedidoInterno.jTablePedidoInterno.getValueAt(0, 2).toString());
                }

                controllerDetallesProformas.setOp("C");
                controllerDetallesProformas.setCab(0);
                controllerDetallesProformas.setDet(0);
                controllerDetallesProformas.setCodM(Integer.parseInt(this.jTablePedidoInterno.getValueAt(0, 0).toString()));
                controllerDetallesProformas.setPrecioC(BigDecimal.ZERO);
                controllerDetallesProformas.setPrecioV(BigDecimal.ZERO);
                controllerDetallesProformas.setCantV(canti);
                controllerDetallesProformas.setTotalV(BigDecimal.ZERO);
                controllerDetallesProformas.setUniMed("");
                controllerDetallesProformas.setBono(BigDecimal.ZERO);
                ResultSet rs = controllerDetallesProformas.mntoDetallesProformas();

                // main.jEditorPaneAdvertencias.setText(null);
                try {
                    if (rs.next()) {
                        System.out.println(rs.getString("ESTADO") + " <-ESTADO");

                        main.jTabbedPane1.setSelectedIndex(1);
                        main.jEditorPaneAdvertencias.setText(rs.getString("ESTADO") + " LA VENTA NO PODRA SER GENERADA.");
                        // this.jMenu2.setEnabled(false);
                        return false;
                    } else {
                        main.jTabbedPane1.setSelectedIndex(0);
                        main.jEditorPaneAdvertencias.setText(null);
                       // this.jMenu2.setEnabled(true);
                        return true;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    Logger.getLogger(PedidoInterno.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        return false;
    }

    ;

    public final void Timer() {
        // Clase en la que está el código a ejecutar 
        TimerTask timerTask = new TimerTask() {
            public void run() {
                StockOk();

                //    Toolkit.getDefaultToolkit().beep();
                System.out.println("Consultando stock actual...");
            }
        };

        // Aquí se pone en marcha el timer cada segundo. 
        Timer timer = new Timer();
        // Dentro de 0 milisegundos avísame cada 1000 milisegundos 
        // 900000 es 15 minutos
        timer.scheduleAtFixedRate(timerTask, 0, 5000);
    }

    public void generarVenta() {
        if (this.codClienteC.getText().length() == 10 && this.jTablePedidoInterno.getRowCount() > 0) {
            System.out.println("Datos validos");
            controllerProformas.setOp("G");
            controllerProformas.setCod(0);
            controllerProformas.setCodClie(Integer.parseInt(this.codClienteC.getText()));
            controllerProformas.setCodUsu(Integer.parseInt(main.lblcodigo.getText()));
            controllerProformas.setTotal(BigDecimal.valueOf(Double.parseDouble(lblimporte.getText())));
            String xx = controllerProformas.mntoProformas();
            System.out.println(xx + " y la cabecera es " + modelProformas.getCabecera() + " - " + modelProformas.getDetalle());
            this.txtcabecera.setText(modelProformas.getCabecera());
            this.txtdetalle.setText(modelProformas.getDetalle());
            ResultSet rst;
            int rpt = 0;
            int x = 0;
            String detalle = "";
            while (x <= jTablePedidoInterno.getRowCount() - 1) {

                System.out.println("Recorro la fila " + x + " de " + jTablePedidoInterno.getRowCount());
                controllerDetallesProformas.setOp("I");
                controllerDetallesProformas.setCab(Integer.parseInt(modelProformas.getCabecera()));
                controllerDetallesProformas.setDet(Integer.parseInt(modelProformas.getDetalle()));
                controllerDetallesProformas.setCodM(Integer.parseInt(this.jTablePedidoInterno.getValueAt(x, 0).toString()));
                controllerDetallesProformas.setPrecioC(BigDecimal.valueOf(Double.parseDouble(this.jTablePedidoInterno.getValueAt(x, 7).toString())).setScale(3, RoundingMode.HALF_UP));
                controllerDetallesProformas.setPrecioV(BigDecimal.valueOf(Double.parseDouble(this.jTablePedidoInterno.getValueAt(x, 4).toString())));
                controllerDetallesProformas.setCantV(Integer.parseInt(this.jTablePedidoInterno.getValueAt(x, 2).toString()));
                controllerDetallesProformas.setTotalV(BigDecimal.valueOf(Double.parseDouble(this.jTablePedidoInterno.getValueAt(x, 5).toString())));
                controllerDetallesProformas.setUniMed(this.jTablePedidoInterno.getValueAt(x, 3).toString());
                controllerDetallesProformas.setBono(BigDecimal.valueOf(Double.parseDouble(this.jTablePedidoInterno.getValueAt(x, 9).toString())));

                detalle = this.jTablePedidoInterno.getValueAt(x, 0).toString() + "   " + this.jTablePedidoInterno.getValueAt(x, 1).toString() + "   "
                        + this.jTablePedidoInterno.getValueAt(x, 4).toString() + "   "
                        + (this.jTablePedidoInterno.getValueAt(x, 2).toString()) + "" + (this.jTablePedidoInterno.getValueAt(x, 3).toString()) + "   "
                        + (this.jTablePedidoInterno.getValueAt(x, 5).toString()) + " \n" + detalle;
                //rst =
                controllerDetallesProformas.mntoDetallesProformas();

                x++;
            }
            // if (rpt == 0) {
            JOptionPane.showMessageDialog(null, "Venta generada", "Nº correlativo : " + modelProformas.getCabecera() + " - " + modelProformas.getDetalle(), JOptionPane.INFORMATION_MESSAGE);

            //  clear();
            this.jDialog.dispose();
            this.txtmed.requestFocus();
            // }

        } else {
            System.out.println("Datos invalidos");
        }

    }

    public void agregarMedicamento(KeyEvent evt) {
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER && this.txtcantidad.getText().length() > 0) {
            int cant = Integer.parseInt(this.txtcantidad.getText());
            Object[] obj = new Object[12];
            if (cant >= Integer.parseInt(this.lblAplicaDesde.getText())) {
            }
            dtm = (DefaultTableModel) this.jTablePedidoInterno.getModel();

            obj[0] = this.lblCod.getText();
            obj[1] = this.lblMedicamento.getText();
            obj[2] = this.txtcantidad.getText();
            obj[3] = this.jListCantidad.getSelectedIndex() == 0 ? "CAJA" : "UNID";

            if (this.jListCantidad.getSelectedIndex() == 0) {
                if (this.chkprecioespecial.isSelected()) {
                    obj[4] = this.lblPrecioMin.getText();

                } else {
                    obj[4] = this.lblPrecioNormal.getText();
                }
                obj[6] = String.valueOf(Integer.parseInt(this.lblDisplay.getText()) * Integer.parseInt(this.txtcantidad.getText()));

            } else {
                obj[4] = this.lblPrecioUnidad.getText();
                obj[6] = this.txtcantidad.getText();
            }
            obj[8] = this.lblDisplay.getText();
            obj[7] = BigDecimal.valueOf(Double.parseDouble(this.lblcosto.getText()))
                    .divide(BigDecimal.valueOf(Double.parseDouble(obj[8].toString())), 3, RoundingMode.HALF_UP);
//.multiply(BigDecimal.valueOf(Double.parseDouble(obj[6].toString())));
            BigDecimal canti = null, precio = null;
            canti = BigDecimal.valueOf(Double.parseDouble(this.txtcantidad.getText()));
            precio = BigDecimal.valueOf(Double.parseDouble(obj[4].toString()));
            obj[5] = canti.multiply(precio).setScale(2, RoundingMode.HALF_UP);
            System.out.println(obj[5] + " cantidad total");
            obj[9] = BigDecimal.valueOf(Double.parseDouble(this.lblBonoVenta.getText())).multiply(BigDecimal.valueOf(Double.parseDouble(obj[6].toString()))).setScale(2, RoundingMode.HALF_UP);
            obj[10] = lblPrecioNormal.getText();
            obj[11] = this.lblBonoVenta.getText();

            dtm.addRow(obj);

            if (cant >= Integer.parseInt(this.lblAplicaDesde.getText()) && this.lblPromocion.getText().equals("SI")) {
                obj[2] = this.lblBonificacion.getText();
                obj[3] = "BONIFIC";
                obj[4] = "0.00";
                obj[5] = "0.00";
                obj[6] = this.lblBonificacion.getText();
                obj[9] = "0.00";
                obj[11] = "0.00";
                dtm.addRow(obj);

            }
            System.out.println(this.jTablePedidoInterno.getRowCount());
            this.lblitems.setText(this.jTablePedidoInterno.getRowCount() + " ITEM(S) AGREGADO(S).");
            sumarTotal();
            this.chkprecioespecial.setSelected(false);
            this.chkprecioespecial.setEnabled(false);
            this.txtcantidad.setText(null);
            this.jDialogAgregar.dispose();
            this.txtbusq.setText(null);
            this.txtbusq.requestFocus();

            this.lblcosto.setText("<<SIN DATOS>>");
            this.lblCod.setText("<<SIN DATOS>>");
            this.lblMedicamento.setText("<<SIN DATOS>>");
            this.lblLabor.setText("<<SIN DATOS>>");
            this.lblPresent.setText("<<SIN DATOS>>");
            this.lblDescrip.setText("<<SIN DATOS>>");
            this.lblStocks.setText("<<SIN DATOS>>");
            this.lblStockDet.setText("<<SIN DATOS>>");
            this.lblDisplay.setText("<<SIN DATOS>>");
            this.lblPrecioMin.setText("<<SIN DATOS>>");
            this.lblPrecioNormal.setText("<<SIN DATOS>>");
            this.lblPrecioUnidad.setText("<<SIN DATOS>>");
            this.lblPromocion.setText("<<SIN DATOS>>");
            this.lblAplicaDesde.setText("<<SIN DATOS>>");
            this.lblBonificacion.setText("<<SIN DATOS>>");

        }
    }

    public void clear() {
        this.Cliente.setText(null);
        this.Dni.setText(null);
        this.Direccion.setText(null);
        this.codClienteC.setText(null);
        this.txtmed.setText(null);
        this.txtcabecera.setText(null);
        this.txtdetalle.setText(null);
        this.lblitems.setText("<<SIN DATOS>>");
        this.lblimportetotal.setText("000.00");
        this.lblimporte.setText("000.00");
        this.lblcosto.setText("<<SIN DATOS>>");
        this.lblCod.setText("<<SIN DATOS>>");
        this.lblMedicamento.setText("<<SIN DATOS>>");
        this.lblLabor.setText("<<SIN DATOS>>");
        this.lblPresent.setText("<<SIN DATOS>>");
        this.lblDescrip.setText("<<SIN DATOS>>");
        this.lblStocks.setText("<<SIN DATOS>>");
        this.lblStockDet.setText("<<SIN DATOS>>");
        this.lblDisplay.setText("<<SIN DATOS>>");
        this.lblPrecioMin.setText("<<SIN DATOS>>");
        this.lblPrecioNormal.setText("<<SIN DATOS>>");
        this.lblPrecioUnidad.setText("<<SIN DATOS>>");
        this.lblPromocion.setText("<<SIN DATOS>>");
        this.lblAplicaDesde.setText("<<SIN DATOS>>");
        this.lblBonificacion.setText("<<SIN DATOS>>");
        this.Cliente.setEnabled(true);
        this.Dni.setEnabled(true);
        validator.vaciar_jtable(jTablePedidoInterno);
        validator.vaciar_jtable(jTableMedicamentos);
        this.Cliente.requestFocus();
    }

    public final void TextAutoCompleterClientes(JTextField busc) {
        TextAutoCompleter Tac = new TextAutoCompleter(busc);

        modelClientes.setOp("D");
        modelClientes.setBusc("");
        ResultSet rs = modelClientes.queryClientes();
        try {
            Tac.removeAllItems();
            Tac.setMode(0);
            while (rs.next()) {
                Object[] obj = new Object[1];
                obj[0] = rs.getString("CLIENTE");
                Tac.addItem(obj[0]);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        txtbusq = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableMedicamentos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCod = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMedicamento = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblLabor = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblPresent = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblDescrip = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblStockDet = new javax.swing.JLabel();
        lblStocks = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblDisplay = new javax.swing.JLabel();
        lblPrecioMin = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblPrecioNormal = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblPrecioUnidad = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblPromocion = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblAplicaDesde = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        lblBonificacion = new javax.swing.JLabel();
        costo = new javax.swing.JLabel();
        lblcosto = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblBonoVenta = new javax.swing.JLabel();
        lblComponentes = new javax.swing.JLabel();
        jDialogAgregar = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        txtcantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        chkprecioespecial = new javax.swing.JCheckBox();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListCantidad = new javax.swing.JList();
        jDialogSimilares = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableDetallesComp = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableMedicamentosOp = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        Cliente = new javax.swing.JTextField();
        chkBuscVentas = new javax.swing.JCheckBox();
        txtmed = new javax.swing.JTextField();
        Dni = new javax.swing.JTextField();
        chkmodificarClie = new javax.swing.JCheckBox();
        Direccion = new javax.swing.JTextField();
        txtcabecera = new javax.swing.JTextField();
        txtdetalle = new javax.swing.JTextField();
        codClienteC = new javax.swing.JTextField();
        lblcodVenta = new javax.swing.JTextField();
        RUC = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePedidoInterno = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblimportetotal1 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lblimportetotal = new javax.swing.JTextField();
        lblimporte = new javax.swing.JTextField();
        lblimportetotal2 = new javax.swing.JLabel();
        lblitems = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuActualizar = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuAnular = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jDialog.setTitle("Ingresar el producto solicitado");
        jDialog.setBackground(new java.awt.Color(51, 51, 255));
        jDialog.setMinimumSize(new java.awt.Dimension(887, 219));
        jDialog.setModal(true);
        jDialog.setUndecorated(true);
        jDialog.setResizable(false);
        jDialog.setType(java.awt.Window.Type.UTILITY);

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        txtbusq.setBackground(new java.awt.Color(255, 255, 153));
        txtbusq.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtbusq.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtbusq.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Búsqueda[Nombre de producto o Codigo Barra]-[F4]-[F5]-[F7]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtbusq.setCaretColor(new java.awt.Color(51, 204, 255));
        txtbusq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbusqKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusqKeyReleased(evt);
            }
        });

        jTableMedicamentos.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTableMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jTableMedicamentos.setToolTipText("Presionar [F5] para buscar opcionales");
        jTableMedicamentos.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableMedicamentos.setShowHorizontalLines(false);
        jTableMedicamentos.getTableHeader().setResizingAllowed(false);
        jTableMedicamentos.getTableHeader().setReorderingAllowed(false);
        jTableMedicamentos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableMedicamentosFocusGained(evt);
            }
        });
        jTableMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMedicamentosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTableMedicamentosMouseEntered(evt);
            }
        });
        jTableMedicamentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableMedicamentosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableMedicamentosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableMedicamentos);

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel4.setText("[Esc] Salir ");

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 0));
        jLabel5.setText("COD :");

        lblCod.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblCod.setText("<<SIN DATOS>>");
        lblCod.setMaximumSize(new java.awt.Dimension(129, 14));
        lblCod.setMinimumSize(new java.awt.Dimension(129, 14));
        lblCod.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 0, 0));
        jLabel7.setText("PRODUCTO:");

        lblMedicamento.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        lblMedicamento.setText("<<SIN DATOS>>");
        lblMedicamento.setMaximumSize(new java.awt.Dimension(129, 14));
        lblMedicamento.setMinimumSize(new java.awt.Dimension(129, 14));
        lblMedicamento.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 0, 0));
        jLabel9.setText("LABOR. O MARCA:");

        lblLabor.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblLabor.setText("<<SIN DATOS>>");
        lblLabor.setMaximumSize(new java.awt.Dimension(129, 14));
        lblLabor.setMinimumSize(new java.awt.Dimension(129, 14));
        lblLabor.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel11.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 0, 0));
        jLabel11.setText("PRESENTACION :");

        lblPresent.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblPresent.setText("<<SIN DATOS>>");
        lblPresent.setMaximumSize(new java.awt.Dimension(129, 14));
        lblPresent.setMinimumSize(new java.awt.Dimension(129, 14));
        lblPresent.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel13.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 0, 0));
        jLabel13.setText("DESCRIPCION :");

        lblDescrip.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblDescrip.setText("<<SIN DATOS>>");
        lblDescrip.setMaximumSize(new java.awt.Dimension(129, 14));
        lblDescrip.setMinimumSize(new java.awt.Dimension(129, 14));
        lblDescrip.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel15.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 0, 0));
        jLabel15.setText("STOCK :");

        lblStockDet.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        lblStockDet.setText("<<SIN DATOS>>");
        lblStockDet.setMaximumSize(new java.awt.Dimension(129, 14));
        lblStockDet.setMinimumSize(new java.awt.Dimension(129, 14));
        lblStockDet.setPreferredSize(new java.awt.Dimension(129, 14));

        lblStocks.setFont(new java.awt.Font("Calibri Light", 1, 13)); // NOI18N
        lblStocks.setText("<<SIN DATOS>>");
        lblStocks.setMaximumSize(new java.awt.Dimension(129, 14));
        lblStocks.setMinimumSize(new java.awt.Dimension(129, 14));
        lblStocks.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel18.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(153, 0, 0));
        jLabel18.setText("STOCK DETALL:");

        jLabel19.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(153, 0, 0));
        jLabel19.setText("CANT. DSPL :");

        lblDisplay.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblDisplay.setText("<<SIN DATOS>>");
        lblDisplay.setMaximumSize(new java.awt.Dimension(129, 14));
        lblDisplay.setMinimumSize(new java.awt.Dimension(129, 14));
        lblDisplay.setPreferredSize(new java.awt.Dimension(129, 14));

        lblPrecioMin.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblPrecioMin.setText("<<SIN DATOS>>");
        lblPrecioMin.setMaximumSize(new java.awt.Dimension(129, 14));
        lblPrecioMin.setMinimumSize(new java.awt.Dimension(129, 14));
        lblPrecioMin.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel22.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 0, 0));
        jLabel22.setText("PREC. MIN. :");

        jLabel23.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(153, 0, 0));
        jLabel23.setText("PREC. NORM. :");

        lblPrecioNormal.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblPrecioNormal.setText("<<SIN DATOS>>");
        lblPrecioNormal.setMaximumSize(new java.awt.Dimension(129, 14));
        lblPrecioNormal.setMinimumSize(new java.awt.Dimension(129, 14));
        lblPrecioNormal.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel25.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 0, 0));
        jLabel25.setText("PREC. UNID. :");

        lblPrecioUnidad.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblPrecioUnidad.setText("<<SIN DATOS>>");
        lblPrecioUnidad.setMaximumSize(new java.awt.Dimension(129, 14));
        lblPrecioUnidad.setMinimumSize(new java.awt.Dimension(129, 14));
        lblPrecioUnidad.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel27.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 0, 0));
        jLabel27.setText("PROMOCION:");

        lblPromocion.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        lblPromocion.setText("<<SIN DATOS>>");
        lblPromocion.setMaximumSize(new java.awt.Dimension(129, 14));
        lblPromocion.setMinimumSize(new java.awt.Dimension(129, 14));
        lblPromocion.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel29.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(153, 0, 0));
        jLabel29.setText("APLICA DESDE :");

        lblAplicaDesde.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblAplicaDesde.setText("<<SIN DATOS>>");
        lblAplicaDesde.setMaximumSize(new java.awt.Dimension(129, 14));
        lblAplicaDesde.setMinimumSize(new java.awt.Dimension(129, 14));
        lblAplicaDesde.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel31.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(153, 0, 0));
        jLabel31.setText("BONIFICAC. :");

        lblBonificacion.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblBonificacion.setText("<<SIN DATOS>>");
        lblBonificacion.setMaximumSize(new java.awt.Dimension(129, 14));
        lblBonificacion.setMinimumSize(new java.awt.Dimension(129, 14));
        lblBonificacion.setPreferredSize(new java.awt.Dimension(129, 14));

        costo.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        costo.setForeground(new java.awt.Color(240, 240, 240));
        costo.setText("COSTO :");

        lblcosto.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblcosto.setForeground(new java.awt.Color(240, 240, 240));
        lblcosto.setText("<<SIN DATOS>>");
        lblcosto.setMaximumSize(new java.awt.Dimension(129, 14));
        lblcosto.setMinimumSize(new java.awt.Dimension(129, 14));
        lblcosto.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel32.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(153, 0, 0));
        jLabel32.setText("BONO VENT :");

        lblBonoVenta.setFont(new java.awt.Font("Calibri Light", 3, 12)); // NOI18N
        lblBonoVenta.setForeground(new java.awt.Color(204, 0, 0));
        lblBonoVenta.setText("<<SIN DATOS>>");
        lblBonoVenta.setMaximumSize(new java.awt.Dimension(129, 14));
        lblBonoVenta.setMinimumSize(new java.awt.Dimension(129, 14));
        lblBonoVenta.setPreferredSize(new java.awt.Dimension(129, 14));

        lblComponentes.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblComponentes.setForeground(new java.awt.Color(240, 240, 240));
        lblComponentes.setText("<<SIN DATOS>>");
        lblComponentes.setMaximumSize(new java.awt.Dimension(129, 14));
        lblComponentes.setMinimumSize(new java.awt.Dimension(129, 14));
        lblComponentes.setPreferredSize(new java.awt.Dimension(129, 14));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtbusq, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStocks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStockDet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMedicamento, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(lblLabor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPresent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblAplicaDesde, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(lblPrecioMin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(lblPrecioNormal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(lblPrecioUnidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(lblPromocion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBonificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(18, 18, 18)
                                .addComponent(lblBonoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addComponent(jLabel7)
                        .addComponent(jLabel5)
                        .addComponent(jLabel9)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19)
                                .addComponent(jLabel18)
                                .addComponent(jLabel15)
                                .addComponent(jLabel13))
                            .addGap(147, 147, 147)
                            .addComponent(costo)
                            .addGap(18, 18, 18)
                            .addComponent(lblcosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(114, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(652, Short.MAX_VALUE)
                    .addComponent(lblComponentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(104, 104, 104)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(lblPrecioMin, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCod, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(lblPrecioNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(lblPrecioUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLabor, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(lblPromocion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPresent, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(lblAplicaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(lblBonificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStocks, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(lblBonoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStockDet, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtbusq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(140, 140, 140)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(costo)
                                .addComponent(lblcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel7)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel18))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(161, Short.MAX_VALUE)
                    .addComponent(lblComponentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)))
        );

        javax.swing.GroupLayout jDialogLayout = new javax.swing.GroupLayout(jDialog.getContentPane());
        jDialog.getContentPane().setLayout(jDialogLayout);
        jDialogLayout.setHorizontalGroup(
            jDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialogLayout.setVerticalGroup(
            jDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialogAgregar.setTitle("Ingrese la cantidad [CAJA] O [UNIDAD]");
        jDialogAgregar.setMinimumSize(new java.awt.Dimension(638, 114));
        jDialogAgregar.setModal(true);
        jDialogAgregar.setUndecorated(true);
        jDialogAgregar.setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.setMaximumSize(new java.awt.Dimension(324, 74));
        jPanel1.setMinimumSize(new java.awt.Dimension(324, 74));
        jPanel1.setPreferredSize(new java.awt.Dimension(324, 74));

        txtcantidad.setBackground(new java.awt.Color(244, 245, 242));
        txtcantidad.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtcantidad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtcantidad.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Ingrese Cantidad", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtcantidad.setCaretColor(new java.awt.Color(51, 204, 255));
        txtcantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcantidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcantidadFocusLost(evt);
            }
        });
        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel6.setText("[Esc] para cancelar");

        chkprecioespecial.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        chkprecioespecial.setForeground(new java.awt.Color(153, 0, 0));
        chkprecioespecial.setText("Precio especial");
        chkprecioespecial.setEnabled(false);
        chkprecioespecial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                chkprecioespecialKeyReleased(evt);
            }
        });

        jListCantidad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jListCantidad.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "CAJA", "UNIDAD" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListCantidad.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jListCantidadKeyReleased(evt);
            }
        });
        jListCantidad.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListCantidadValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(jListCantidad);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkprecioespecial, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel6)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtcantidad)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkprecioespecial)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogAgregarLayout = new javax.swing.GroupLayout(jDialogAgregar.getContentPane());
        jDialogAgregar.getContentPane().setLayout(jDialogAgregarLayout);
        jDialogAgregarLayout.setHorizontalGroup(
            jDialogAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAgregarLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialogAgregarLayout.setVerticalGroup(
            jDialogAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
        );

        jDialogSimilares.setTitle("Medicamentos opcionales");
        jDialogSimilares.setMinimumSize(new java.awt.Dimension(854, 178));
        jDialogSimilares.setModal(true);
        jDialogSimilares.setUndecorated(true);
        jDialogSimilares.setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Productos opcionales", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        jTableDetallesComp.setFont(new java.awt.Font("Calibri Light", 0, 10)); // NOI18N
        jTableDetallesComp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Compuesto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDetallesComp.setToolTipText("Borrar con [Supr]");
        jTableDetallesComp.setFocusable(false);
        jTableDetallesComp.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableDetallesComp.getTableHeader().setReorderingAllowed(false);
        jTableDetallesComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDetallesCompMouseClicked(evt);
            }
        });
        jTableDetallesComp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableDetallesCompKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableDetallesCompKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTableDetallesComp);

        jTableMedicamentosOp.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTableMedicamentosOp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Productos"
            }
        ));
        jTableMedicamentosOp.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableMedicamentosOp.getTableHeader().setReorderingAllowed(false);
        jTableMedicamentosOp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableMedicamentosOpKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableMedicamentosOpKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTableMedicamentosOp);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialogSimilaresLayout = new javax.swing.GroupLayout(jDialogSimilares.getContentPane());
        jDialogSimilares.getContentPane().setLayout(jDialogSimilaresLayout);
        jDialogSimilaresLayout.setHorizontalGroup(
            jDialogSimilaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogSimilaresLayout.setVerticalGroup(
            jDialogSimilaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Formulario de pedido interno");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/connections.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(975, 487));
        setMinimumSize(new java.awt.Dimension(975, 522));
        setPreferredSize(new java.awt.Dimension(975, 522));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar Pedido Interno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        Cliente.setName("Cliente");
        Cliente.setBackground(new java.awt.Color(244, 245, 242));
        Cliente.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        Cliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Cliente.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Cliente", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Cliente.setCaretColor(new java.awt.Color(51, 204, 255));
        Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClienteActionPerformed(evt);
            }
        });
        Cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ClienteKeyReleased(evt);
            }
        });

        chkBuscVentas.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        chkBuscVentas.setText("REALIZAR BUSQUEDA DE VENTAS");
        chkBuscVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBuscVentasActionPerformed(evt);
            }
        });

        txtmed.setBackground(new java.awt.Color(244, 245, 242));
        txtmed.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtmed.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtmed.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Productos[F4]-[F5]-[F7]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtmed.setCaretColor(new java.awt.Color(51, 204, 255));
        txtmed.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmedFocusGained(evt);
            }
        });
        txtmed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmedActionPerformed(evt);
            }
        });
        txtmed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmedKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmedKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmedKeyTyped(evt);
            }
        });

        Dni.setName("Dni [F5]");
        Dni.setBackground(new java.awt.Color(244, 245, 242));
        Dni.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        Dni.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Dni.setToolTipText("PRESIONAR [F5] PARA AGREGAR");
        Dni.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "DNI [F5] ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Dni.setCaretColor(new java.awt.Color(51, 204, 255));
        Dni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DniKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DniKeyTyped(evt);
            }
        });

        chkmodificarClie.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        chkmodificarClie.setText("MODIFICAR EL CLIENTE");
        chkmodificarClie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkmodificarClieActionPerformed(evt);
            }
        });

        Direccion.setBackground(new java.awt.Color(255, 255, 153));
        Direccion.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        Direccion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Direccion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Direccion", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Direccion.setCaretColor(new java.awt.Color(51, 204, 255));
        Direccion.setEnabled(false);
        Direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DireccionKeyReleased(evt);
            }
        });

        txtcabecera.setBackground(new java.awt.Color(255, 153, 153));
        txtcabecera.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        txtcabecera.setEnabled(false);
        txtcabecera.setMaximumSize(new java.awt.Dimension(100, 28));
        txtcabecera.setMinimumSize(new java.awt.Dimension(100, 28));
        txtcabecera.setNextFocusableComponent(txtdetalle);
        txtcabecera.setPreferredSize(new java.awt.Dimension(100, 39));
        txtcabecera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcabeceraKeyTyped(evt);
            }
        });

        txtdetalle.setBackground(new java.awt.Color(255, 153, 153));
        txtdetalle.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        txtdetalle.setEnabled(false);
        txtdetalle.setMaximumSize(new java.awt.Dimension(100, 28));
        txtdetalle.setMinimumSize(new java.awt.Dimension(100, 28));
        txtdetalle.setPreferredSize(new java.awt.Dimension(100, 39));
        txtdetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdetalleKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdetalleKeyTyped(evt);
            }
        });

        codClienteC.setBackground(new java.awt.Color(255, 255, 204));
        codClienteC.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        codClienteC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        codClienteC.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Codigo cliente", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        codClienteC.setCaretColor(new java.awt.Color(51, 204, 255));
        codClienteC.setEnabled(false);
        codClienteC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codClienteCKeyReleased(evt);
            }
        });

        lblcodVenta.setBackground(new java.awt.Color(255, 255, 204));
        lblcodVenta.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblcodVenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lblcodVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Codigo venta", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        lblcodVenta.setCaretColor(new java.awt.Color(51, 204, 255));
        lblcodVenta.setEnabled(false);
        lblcodVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lblcodVentaKeyReleased(evt);
            }
        });

        Dni.setName("Dni [F5]");
        RUC.setBackground(new java.awt.Color(255, 204, 51));
        RUC.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        RUC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        RUC.setToolTipText("PRESIONAR [F5] PARA AGREGAR");
        RUC.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "RUC [F5] ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        RUC.setCaretColor(new java.awt.Color(51, 204, 255));
        RUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RUCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RUCKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmed)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Dni, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RUC)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Direccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codClienteC, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkmodificarClie))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblcodVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkBuscVentas)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(codClienteC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkmodificarClie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtmed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblcodVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkBuscVentas))
                    .addComponent(txtcabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Items del Pedido", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        jTablePedidoInterno.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTablePedidoInterno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "###", "Producto", "Cantidad", "Unid.", "Precio", "Total", "cantidadTotal", "costo", "displays", "Bonificacion", "Caja", "BonoXUnid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePedidoInterno.setToolTipText("Presione [ESPACIO] para cambiar de Unidad");
        jTablePedidoInterno.setColumnSelectionAllowed(true);
        jTablePedidoInterno.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTablePedidoInterno.getTableHeader().setReorderingAllowed(false);
        jTablePedidoInterno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePedidoInternoMouseClicked(evt);
            }
        });
        jTablePedidoInterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTablePedidoInternoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePedidoInternoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTablePedidoInternoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePedidoInterno);
        jTablePedidoInterno.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTablePedidoInterno.getColumnModel().getColumnCount() > 0) {
            jTablePedidoInterno.getColumnModel().getColumn(0).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(1).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(2).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(3).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(4).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(5).setResizable(false);
            jTablePedidoInterno.getColumnModel().getColumn(6).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(6).setMaxWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(7).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(7).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(7).setMaxWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(8).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(8).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(8).setMaxWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(9).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(9).setMaxWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(10).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(10).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(10).setMaxWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(11).setMinWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(11).setPreferredWidth(0);
            jTablePedidoInterno.getColumnModel().getColumn(11).setMaxWidth(0);
        }

        jLabel35.setFont(new java.awt.Font("Calibri Light", 1, 13)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Items:");

        jLabel38.setFont(new java.awt.Font("Calibri Light", 1, 13)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(153, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Monto total :");

        lblimportetotal1.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        lblimportetotal1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimportetotal1.setText("nuevo(s) sol(es)");
        lblimportetotal1.setMaximumSize(new java.awt.Dimension(157, 14));
        lblimportetotal1.setMinimumSize(new java.awt.Dimension(157, 14));
        lblimportetotal1.setPreferredSize(new java.awt.Dimension(157, 14));

        jLabel39.setFont(new java.awt.Font("Calibri Light", 1, 13)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(153, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("Siendo redondeado a :");

        lblimportetotal.setEditable(false);
        lblimportetotal.setBackground(new java.awt.Color(255, 255, 153));
        lblimportetotal.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N

        lblimporte.setEditable(false);
        lblimporte.setBackground(new java.awt.Color(255, 255, 153));
        lblimporte.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N

        lblimportetotal2.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        lblimportetotal2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimportetotal2.setText("nuevo(s) sol(es)");
        lblimportetotal2.setMaximumSize(new java.awt.Dimension(157, 14));
        lblimportetotal2.setMinimumSize(new java.awt.Dimension(157, 14));
        lblimportetotal2.setPreferredSize(new java.awt.Dimension(157, 14));

        lblitems.setEditable(false);
        lblitems.setBackground(new java.awt.Color(255, 153, 102));
        lblitems.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblimporte, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblimportetotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblimportetotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblimportetotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblitems, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(241, 241, 241))
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel35)
                    .addComponent(lblimportetotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblimportetotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblitems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lblimporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblimportetotal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jMenuBar1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar1MouseDragged(evt);
            }
        });
        jMenuBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuBar1MousePressed(evt);
            }
        });

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Add.png"))); // NOI18N
        jMenu1.setText("NUEVO[F4]");
        jMenu1.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/OK.png"))); // NOI18N
        jMenu2.setText("REGISTRAR[F5]");
        jMenu2.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu2MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenuActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Sync.png"))); // NOI18N
        jMenuActualizar.setText("ACTUALIZAR");
        jMenuActualizar.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenuActualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenuActualizar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenuActualizar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenuActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuActualizarMousePressed(evt);
            }
        });
        jMenuBar1.add(jMenuActualizar);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/New document.png"))); // NOI18N
        jMenu5.setText("IMPRIMIR[F7]");
        jMenu5.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu5MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenuAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/No-entry.png"))); // NOI18N
        jMenuAnular.setText("ANULAR");
        jMenuAnular.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenuAnular.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenuAnular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenuAnular.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenuAnular.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenuAnular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuAnularMousePressed(evt);
            }
        });
        jMenuBar1.add(jMenuAnular);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        jMenu4.setText("CERRAR VENTANA");
        jMenu4.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu4MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTablePedidoInternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePedidoInternoMouseClicked
//int selecc = 0;
//        //  System.out.println("Se selecciono el dato "+this.jTablePedidoInterno.getValueAt(this.jTablePedidoInterno.getSelectedRow(),this.jTablePedidoInterno.getSelectedColumn()));
//        if (this.jTablePedidoInterno.getSelectedColumn() == 2) {
//            System.out.println("Se selecciono el dato " + this.jTablePedidoInterno.getValueAt(this.jTablePedidoInterno.getSelectedRow(), 2));
//             selecc=this.jTablePedidoInterno.getSelectedRow();
//        } else {
//            BigDecimal cantidad =
//            BigDecimal.valueOf(Double.parseDouble(this.jTablePedidoInterno.getValueAt(this.jTablePedidoInterno.getSelectedRow(), 2).toString()));
//        
//        BigDecimal precio =
//            BigDecimal.valueOf(Double.parseDouble(this.jTablePedidoInterno.getValueAt(this.jTablePedidoInterno.getSelectedRow(), 4).toString()));
//        
//        
//        this.jTablePedidoInterno.setValueAt(cantidad.multiply(precio),selecc,5);
//        }
    }//GEN-LAST:event_jTablePedidoInternoMouseClicked

    private void jTableMedicamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMedicamentosMouseClicked
        int sel = PedidoInterno.jTableMedicamentos.getSelectedRow();

        this.lblCod.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 0).toString());
        this.lblMedicamento.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 1).toString());
        this.lblLabor.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 2).toString());
        this.lblPresent.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 3).toString());
        this.lblDescrip.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 4).toString());
        this.lblStockDet.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 15).toString());
        this.lblStocks.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 14).toString());
        this.lblDisplay.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 6).toString());
        this.lblPrecioMin.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 8).toString());
        this.lblPrecioNormal.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 9).toString());
        this.lblPrecioUnidad.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 10).toString());
        this.lblPromocion.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 11).toString());
        this.lblAplicaDesde.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 12).toString());
        this.lblBonificacion.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 13).toString());
        this.lblcosto.setText(PedidoInterno.jTableMedicamentos.getValueAt(sel, 7).toString());
        this.lblBonoVenta.setText(this.jTableMedicamentos.getValueAt(sel, 18).toString());
        this.lblComponentes.setText(this.jTableMedicamentos.getValueAt(sel, 17).toString());
    }//GEN-LAST:event_jTableMedicamentosMouseClicked

    private void txtmedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmedKeyTyped
    }//GEN-LAST:event_txtmedKeyTyped

    private void txtmedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmedKeyReleased
if(evt.getExtendedKeyCode()==KeyEvent.VK_F4){
clears();
}
if(evt.getExtendedKeyCode()==KeyEvent.VK_F5){
  if (StockOk() == true) {
            generarVenta();
                   } else {
            JOptionPane.showMessageDialog(this, "productos incompletos.", "Error al intentar registrar", JOptionPane.ERROR_MESSAGE);
        }
}
if(evt.getExtendedKeyCode()==KeyEvent.VK_F7){
if(txtcabecera.getText().length()==3 && this.txtdetalle.getText().length()==8 &&
        jTablePedidoInterno.getRowCount()>0
        ){
 imprimir();
 
}else{
JOptionPane.showMessageDialog(this, "Impresión no válida.","Error en impresión",JOptionPane.ERROR_MESSAGE);
}
}

        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            PedidoInterno.jDialog.setLocationRelativeTo(this);
            this.txtbusq.setText(this.txtmed.getText());
            this.txtbusq.requestFocus();
            controllerMedicamentos.setOp("D");
            controllerMedicamentos.setBusc(this.txtmed.getText());
            controllerMedicamentos.consultarMedicamentos("VENTAS", dtm);
            this.txtmed.setText(null);
            jTableMedicamentos.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableMedicamentos.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            PedidoInterno.jDialog.setVisible(true);

        }
    }//GEN-LAST:event_txtmedKeyReleased

    private void txtmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmedActionPerformed

    private void txtmedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmedKeyPressed
    }//GEN-LAST:event_txtmedKeyPressed

    private void txtbusqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqKeyReleased
if(evt.getExtendedKeyCode()==KeyEvent.VK_F4){
clears();
}
if(evt.getExtendedKeyCode()==KeyEvent.VK_F5){
  if (StockOk() == true) {
            generarVenta();
                   } else {
            JOptionPane.showMessageDialog(this, "productos incompletos.", "Error al intentar registrar", JOptionPane.ERROR_MESSAGE);
        }
}
if(evt.getExtendedKeyCode()==KeyEvent.VK_F7){
if(txtcabecera.getText().length()==3 && this.txtdetalle.getText().length()==8 &&
        jTablePedidoInterno.getRowCount()>0
        ){
 imprimir();
 
}else{
JOptionPane.showMessageDialog(this, "Impresión no válida.","Error en impresión",JOptionPane.ERROR_MESSAGE);
}
}
        controllerMedicamentos.setOp("D");
        controllerMedicamentos.setBusc(this.txtbusq.getText());
        controllerMedicamentos.consultarMedicamentos("VENTAS", dtm);
        jTableMedicamentos.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        jTableMedicamentos.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            this.jTableMedicamentos.requestFocus();
        }
    }//GEN-LAST:event_txtbusqKeyReleased

    private void jTableMedicamentosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableMedicamentosFocusGained
    }//GEN-LAST:event_jTableMedicamentosFocusGained

    private void jTableMedicamentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMedicamentosKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.jDialog.dispose();
        }
        if (evt.getExtendedKeyCode() == KeyEvent.VK_F5) {
            controllerMedicamentos.setOp("Q");
            controllerMedicamentos.setBusc(this.lblCod.getText());
            controllerMedicamentos.consultarMedicamentosDetalles("PEDIDO", dtm);

            controllerMedicamentos.setOp("Y");
            controllerMedicamentos.setBusc(lblComponentes.getText());
            controllerMedicamentos.consultarMedicamentosOpcionalesDetallado(dtm);

            this.jDialogSimilares.setLocationRelativeTo(this);
            this.jTableMedicamentosOp.requestFocus();
            jTableMedicamentosOp.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableMedicamentosOp.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableMedicamentosOp.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableMedicamentosOp.getColumnModel().getColumn(3).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableMedicamentosOp.getColumnModel().getColumn(4).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableMedicamentosOp.getColumnModel().getColumn(5).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableMedicamentosOp.getColumnModel().getColumn(6).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableMedicamentosOp.getColumnModel().getColumn(7).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableDetallesComp.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableDetallesComp.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTableDetallesComp.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            this.jDialogSimilares.setVisible(true);
        }

        if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {
            int sel = this.jTableMedicamentos.getSelectedRow();
            this.lblCod.setText(this.jTableMedicamentos.getValueAt(sel, 0).toString());
            this.lblMedicamento.setText(this.jTableMedicamentos.getValueAt(sel, 1).toString());
            this.lblLabor.setText(this.jTableMedicamentos.getValueAt(sel, 2).toString());
            this.lblPresent.setText(this.jTableMedicamentos.getValueAt(sel, 3).toString());
            this.lblDescrip.setText(this.jTableMedicamentos.getValueAt(sel, 4).toString());
            this.lblStockDet.setText(this.jTableMedicamentos.getValueAt(sel, 15).toString());
            this.lblStocks.setText(this.jTableMedicamentos.getValueAt(sel, 14).toString());
            this.lblDisplay.setText(this.jTableMedicamentos.getValueAt(sel, 6).toString());
            this.lblPrecioMin.setText(this.jTableMedicamentos.getValueAt(sel, 8).toString());
            this.lblPrecioNormal.setText(this.jTableMedicamentos.getValueAt(sel, 9).toString());
            this.lblPrecioUnidad.setText(this.jTableMedicamentos.getValueAt(sel, 10).toString());
            this.lblPromocion.setText(this.jTableMedicamentos.getValueAt(sel, 11).toString());
            this.lblAplicaDesde.setText(this.jTableMedicamentos.getValueAt(sel, 12).toString());
            this.lblBonificacion.setText(this.jTableMedicamentos.getValueAt(sel, 13).toString());
            this.lblcosto.setText(this.jTableMedicamentos.getValueAt(sel, 7).toString());
            this.lblBonoVenta.setText(this.jTableMedicamentos.getValueAt(sel, 18).toString());
            this.lblComponentes.setText(this.jTableMedicamentos.getValueAt(sel, 17).toString());

        }

    }//GEN-LAST:event_jTableMedicamentosKeyReleased

    private void txtcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.txtcantidad.setText(null);
            jDialogAgregar.dispose();
        }
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            if (this.txtcantidad.getText().length() > 0) {
                int cant = 0;
//            int cant = jListCantidad.getSelectedIndex() == 0
//                    ? Integer.parseInt(this.txtcantidad.getText()) * Integer.parseInt(lblDisplay.getText()) : 
//                    Integer.parseInt(this.txtcantidad.getText());

                if (jListCantidad.getSelectedIndex() == 0) {
                    cant = Integer.parseInt(this.txtcantidad.getText()) * Integer.parseInt(lblDisplay.getText());
                }
                if (jListCantidad.getSelectedIndex() == 1) {
                    cant = Integer.parseInt(this.txtcantidad.getText());
                }

                controllerDetallesProformas.setOp("C");
                controllerDetallesProformas.setCab(0);
                controllerDetallesProformas.setDet(0);
                controllerDetallesProformas.setCodM(Integer.parseInt(this.lblCod.getText()));
                controllerDetallesProformas.setPrecioC(BigDecimal.ZERO);
                controllerDetallesProformas.setPrecioV(BigDecimal.ZERO);
                controllerDetallesProformas.setCantV(cant);
                controllerDetallesProformas.setTotalV(BigDecimal.ZERO);
                controllerDetallesProformas.setUniMed("");
                controllerDetallesProformas.setBono(BigDecimal.ZERO);
                ResultSet rs = controllerDetallesProformas.mntoDetallesProformas();
                try {
                    if (rs.next()) {
                        System.out.println(rs.getString("ESTADO") + " <-ESTADO");
                        JOptionPane.showMessageDialog(this, rs.getString("ESTADO"), "Stock Limitado", JOptionPane.ERROR_MESSAGE);

                    } else {

                        agregarMedicamento(evt);

                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    Logger.getLogger(PedidoInterno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }//GEN-LAST:event_txtcantidadKeyReleased

    private void txtcantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcantidadFocusLost
    }//GEN-LAST:event_txtcantidadFocusLost

    private void txtcantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcantidadFocusGained
    }//GEN-LAST:event_txtcantidadFocusGained

    private void ClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClienteKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            controllerClientes.setOp("S");
            controllerClientes.setBusc(this.Cliente.getText());
            controllerClientes.consultarClientes();
            
            if (this.codClienteC.getText().length() == 10) {
                this.Cliente.setEnabled(false);
                this.Dni.setEnabled(false);
 this.RUC.setEnabled(false);
                this.jDialog.setLocationRelativeTo(this);
                this.txtbusq.setText(this.txtmed.getText());
                this.txtbusq.requestFocus();
                controllerMedicamentos.setOp("D");
                controllerMedicamentos.setBusc(this.txtmed.getText());
                controllerMedicamentos.consultarMedicamentos("VENTAS", dtm);
                this.txtmed.setText(null);
                jTableMedicamentos.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
                jTableMedicamentos.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
                this.jDialog.setVisible(true);
                //  this.txtmed.requestFocus();
            }

        }
    }//GEN-LAST:event_ClienteKeyReleased

    private void jTableMedicamentosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMedicamentosKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("<<ENTER>>");
            if (lblDisplay.getText().equals("1")) {
                this.jListCantidad.setSelectedIndex(0);
                this.jListCantidad.setEnabled(false);
                this.chkprecioespecial.setEnabled(true);

            } else {
                this.jListCantidad.setSelectedIndex(1);
                this.jListCantidad.setEnabled(true);

            }
            //  this.txtcantidad.requestFocus();
            this.jDialogAgregar.setLocationRelativeTo(this);

            this.jDialogAgregar.setVisible(true);
            //this.txtcantidad.requestFocus();

        }
    }//GEN-LAST:event_jTableMedicamentosKeyPressed

    private void txtcantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyPressed
    }//GEN-LAST:event_txtcantidadKeyPressed

    private void txtcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyTyped
        validator.solonumeros(evt, this.txtcantidad, 3);        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadKeyTyped

    private void txtmedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmedFocusGained
    }//GEN-LAST:event_txtmedFocusGained

    private void DniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DniKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_F5) {
            if (validator.novacios("10-8", 2, this.Cliente, this.Dni) == false) {
                controllerClientes.setOp("C");
                controllerClientes.setNomb(this.Cliente.getText());
                controllerClientes.setDni(this.Dni.getText());
                controllerClientes.setDir("<<SIN DATOS>>");
                controllerClientes.setFono("<<SIN DATOS>>");
                controllerClientes.setRuc(this.RUC.getText());
                controllerClientes.mntoClientes();

                controllerClientes.setOp("S");
                controllerClientes.setBusc(this.Cliente.getText());
                System.out.println("Nombre enviado a ser buscado : " + this.Cliente.getText());
                controllerClientes.consultarClientes();

                TextAutoCompleterClientes(Cliente);
                if (this.codClienteC.getText().length() == 10) {
                    this.Cliente.setEnabled(false);
                    this.Dni.setEnabled(false);
                     this.RUC.setEnabled(false);
                    this.jDialog.setLocationRelativeTo(this);
                    this.txtbusq.setText(this.txtmed.getText());
                    this.txtbusq.requestFocus();
                    controllerMedicamentos.setOp("D");
                    controllerMedicamentos.setBusc(this.txtmed.getText());
                    controllerMedicamentos.consultarMedicamentos("VENTAS", dtm);
                    this.txtmed.setText(null);
                    jTableMedicamentos.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
                    jTableMedicamentos.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
                    this.jDialog.setVisible(true);
                    // this.txtmed.requestFocus();
                }

            }
        }
    }//GEN-LAST:event_DniKeyReleased

    private void ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClienteActionPerformed

    private void DireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DireccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionKeyReleased

    private void chkmodificarClieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkmodificarClieActionPerformed
        if (this.chkmodificarClie.isSelected()) {

            this.Cliente.setText(null);
            this.Dni.setText(null);
            this.Direccion.setText(null);
            this.codClienteC.setText(null);
            this.Cliente.setEnabled(true);
            this.Dni.setEnabled(true);
            this.chkmodificarClie.setSelected(false);
            this.Cliente.requestFocus();

        }
    }//GEN-LAST:event_chkmodificarClieActionPerformed

    private void DniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DniKeyTyped
        validator.solonumeros(evt, Dni, 8);
    }//GEN-LAST:event_DniKeyTyped

    private void jTablePedidoInternoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePedidoInternoKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            dtm = (DefaultTableModel) this.jTablePedidoInterno.getModel();
            System.out.println("Selecciono : " + this.jTablePedidoInterno.getSelectedRow());

            dtm.removeRow(this.jTablePedidoInterno.getSelectedRow());

            this.lblitems.setText(this.jTablePedidoInterno.getRowCount() + " ITEM(S) AGREGADO(S).");
            sumarTotal();

        }
//     
//        int s = this.jTablePedidoInterno.getSelectedRow();
//        dtm = (DefaultTableModel) this.jTablePedidoInterno.getModel();
//        dtm.removeRow(s);

    }//GEN-LAST:event_jTablePedidoInternoKeyPressed

    private void chkBuscVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBuscVentasActionPerformed
        if (this.chkBuscVentas.isSelected()) {
            this.txtcabecera.setEnabled(true);
            this.txtdetalle.setEnabled(true);

            this.txtcabecera.requestFocus();
        }

    }//GEN-LAST:event_chkBuscVentasActionPerformed

    private void jTableMedicamentosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMedicamentosMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableMedicamentosMouseEntered

    private void txtcabeceraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcabeceraKeyTyped
        validator.solonumeros(evt, txtcabecera, 3);
    }//GEN-LAST:event_txtcabeceraKeyTyped

    private void txtdetalleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdetalleKeyTyped
        validator.solonumeros(evt, txtdetalle, 8);
    }//GEN-LAST:event_txtdetalleKeyTyped

    private void jTableDetallesCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDetallesCompMouseClicked
        //       dtm= (DefaultTableModel) this.jTableDetallesComp.getModel();
        //        if (evt.getButton() == MouseEvent.BUTTON1) {
        //
        //            jTableDetallesComp.getColumnModel().getColumn(0).setMaxWidth(0);
        //            jTableDetallesComp.getColumnModel().getColumn(0).setMinWidth(0);
        //            jTableDetallesComp.getColumnModel().getColumn(0).setPreferredWidth(0);
        //            jTableDetallesComp.getColumnModel().getColumn(0).setWidth(0);
        //            System.out.println("Selecciono : "+this.jTableDetallesComp.getSelectedRow());
        //            dtm.removeRow(this.jTableDetallesComp.getSelectedRow());
        //        }
    }//GEN-LAST:event_jTableDetallesCompMouseClicked

    private void jTableDetallesCompKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableDetallesCompKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            dtm = (DefaultTableModel) this.jTableDetallesComp.getModel();
            System.out.println("Selecciono : " + this.jTableDetallesComp.getSelectedRow());
            dtm.removeRow(this.jTableDetallesComp.getSelectedRow());
        }
    }//GEN-LAST:event_jTableDetallesCompKeyPressed

    private void jTableDetallesCompKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableDetallesCompKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.jDialogSimilares.dispose();
        }
    }//GEN-LAST:event_jTableDetallesCompKeyReleased

    private void jTableMedicamentosOpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMedicamentosOpKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.jDialogSimilares.dispose();
        }
    }//GEN-LAST:event_jTableMedicamentosOpKeyReleased

    private void jTablePedidoInternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePedidoInternoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            if (PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 3).toString().equals("UNID")) {
                int x = JOptionPane.showOptionDialog(null, "Desea cambiar el producto a una caja.", "Advertencia", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (x == 0) {

                    controllerDetallesProformas.setOp("C");
                    controllerDetallesProformas.setCab(0);
                    controllerDetallesProformas.setDet(0);
                    controllerDetallesProformas.setCodM(Integer.parseInt(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 0).toString()));
                    controllerDetallesProformas.setPrecioC(BigDecimal.ZERO);
                    controllerDetallesProformas.setPrecioV(BigDecimal.ZERO);
                    controllerDetallesProformas.setCantV(Integer.parseInt(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 8).toString()) * Integer.parseInt(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 2).toString()));
                    controllerDetallesProformas.setTotalV(BigDecimal.ZERO);
                    controllerDetallesProformas.setUniMed("");
                    controllerDetallesProformas.setBono(BigDecimal.ZERO);
                    ResultSet rs = controllerDetallesProformas.mntoDetallesProformas();
                    try {
                        if (rs.next()) {
                            System.out.println(rs.getString("ESTADO"));
                            JOptionPane.showMessageDialog(this, rs.getString("ESTADO"), "Stock Limitado", JOptionPane.ERROR_MESSAGE);

                        } else {

                            PedidoInterno.jTablePedidoInterno.setValueAt("CAJA", PedidoInterno.jTablePedidoInterno.getSelectedRow(), 3);
                            PedidoInterno.jTablePedidoInterno.setValueAt(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 10).toString(), PedidoInterno.jTablePedidoInterno.getSelectedRow(), 4);
                            BigDecimal cant;
                            BigDecimal prec;
                            cant = new BigDecimal(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 2).toString());
                            prec = new BigDecimal(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 4).toString());
                            //TOTAL
                            PedidoInterno.jTablePedidoInterno.setValueAt(cant.multiply(prec).setScale(2, RoundingMode.HALF_UP), PedidoInterno.jTablePedidoInterno.getSelectedRow(), 5);

                            //BONIFICACION
                            PedidoInterno.jTablePedidoInterno.setValueAt(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 2).toString())).multiply(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 8).toString()))).multiply(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 11).toString()))).setScale(2, RoundingMode.HALF_UP), PedidoInterno.jTablePedidoInterno.getSelectedRow(), 9);

                        }
                    } catch (SQLException | HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Error en Pedido Interno " + ex);
                    }

                }
            } else {
                if (PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 3).toString().equals("CAJA")) {
                    int x = JOptionPane.showOptionDialog(null, "Desea cambiar el producto a unidad", "Advertencia", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (x == 0) {
                        BigDecimal caja;
                        BigDecimal display;
                        caja = new BigDecimal(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 10).toString());
                        display = new BigDecimal(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 8).toString());

                        PedidoInterno.jTablePedidoInterno.setValueAt("UNID", PedidoInterno.jTablePedidoInterno.getSelectedRow(), 3);
                        PedidoInterno.jTablePedidoInterno.setValueAt(caja.divide(display).setScale(2, RoundingMode.HALF_UP), PedidoInterno.jTablePedidoInterno.getSelectedRow(), 4);
                        BigDecimal cant;
                        BigDecimal prec;
                        cant = new BigDecimal(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 2).toString());
                        //    prec = new BigDecimal(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 4).toString());

                        //TOTAL
                        PedidoInterno.jTablePedidoInterno.setValueAt(cant.multiply(caja.divide(display).setScale(2, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_UP), PedidoInterno.jTablePedidoInterno.getSelectedRow(), 5);
                        //BONIFICACION
                        PedidoInterno.jTablePedidoInterno.setValueAt(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 2).toString())).multiply(BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(PedidoInterno.jTablePedidoInterno.getSelectedRow(), 11).toString()))), PedidoInterno.jTablePedidoInterno.getSelectedRow(), 9);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Una bonificacion no puede ser modificada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            sumarTotal();
        }
    }//GEN-LAST:event_jTablePedidoInternoKeyReleased

    private void jTablePedidoInternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePedidoInternoKeyTyped
    }//GEN-LAST:event_jTablePedidoInternoKeyTyped

    private void chkprecioespecialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chkprecioespecialKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.jDialogAgregar.dispose();
            this.txtcantidad.setText(null);
        }
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            agregarMedicamento(evt);
        }

    }//GEN-LAST:event_chkprecioespecialKeyReleased

    private void jListCantidadValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListCantidadValueChanged
        System.out.println(this.jListCantidad.getSelectedValue().toString());
        if (this.jListCantidad.getSelectedValue().toString().equals("CAJA")) {
            this.chkprecioespecial.setEnabled(true);
            //   this.chkprecioespecial.requestFocus();
        } else {
            if (this.jListCantidad.getSelectedValue().toString().equals("UNIDAD")) {
                this.txtcantidad.requestFocus();
                this.chkprecioespecial.setEnabled(false);
            }

        }
    }//GEN-LAST:event_jListCantidadValueChanged

    private void jListCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListCantidadKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.jDialogAgregar.dispose();
            this.txtcantidad.setText(null);
        }

        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            if (this.txtcantidad.getText().length() > 0) {
                int cant = 0;
//            int cant = jListCantidad.getSelectedIndex() == 0
//                    ? Integer.parseInt(this.txtcantidad.getText()) * Integer.parseInt(lblDisplay.getText()) : 
//                    Integer.parseInt(this.txtcantidad.getText());

                if (jListCantidad.getSelectedIndex() == 0) {
                    cant = Integer.parseInt(this.txtcantidad.getText()) * Integer.parseInt(lblDisplay.getText());
                }
                if (jListCantidad.getSelectedIndex() == 1) {
                    cant = Integer.parseInt(this.txtcantidad.getText());
                }

                controllerDetallesProformas.setOp("C");
                controllerDetallesProformas.setCab(0);
                controllerDetallesProformas.setDet(0);
                controllerDetallesProformas.setCodM(Integer.parseInt(this.lblCod.getText()));
                controllerDetallesProformas.setPrecioC(BigDecimal.ZERO);
                controllerDetallesProformas.setPrecioV(BigDecimal.ZERO);
                controllerDetallesProformas.setCantV(cant);
                controllerDetallesProformas.setTotalV(BigDecimal.ZERO);
                controllerDetallesProformas.setUniMed("");
                controllerDetallesProformas.setBono(BigDecimal.ZERO);
                ResultSet rs = controllerDetallesProformas.mntoDetallesProformas();

                try {
                    if (rs.next()) {
                        System.out.println(rs.getString("ESTADO") + " <-ESTADO");
                        JOptionPane.showMessageDialog(this, rs.getString("ESTADO"), "Stock Limitado", JOptionPane.ERROR_MESSAGE);

                    } else {
                        agregarMedicamento(evt);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    Logger.getLogger(PedidoInterno.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_jListCantidadKeyReleased

    private void jTableMedicamentosOpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMedicamentosOpKeyPressed

        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            this.txtbusq.setText(this.jTableMedicamentosOp.getValueAt(this.jTableMedicamentosOp.getSelectedRow(), 1).toString());
            controllerMedicamentos.setOp("D");
            controllerMedicamentos.setBusc(this.jTableMedicamentosOp.getValueAt(this.jTableMedicamentosOp.getSelectedRow(), 1).toString());
            controllerMedicamentos.consultarMedicamentos("VENTAS", dtm);
            this.jTableMedicamentos.requestFocus();

            int sel = this.jTableMedicamentos.getSelectedRow() + 1;
            this.lblCod.setText(this.jTableMedicamentos.getValueAt(sel, 0).toString());
            this.lblMedicamento.setText(this.jTableMedicamentos.getValueAt(sel, 1).toString());
            this.lblLabor.setText(this.jTableMedicamentos.getValueAt(sel, 2).toString());
            this.lblPresent.setText(this.jTableMedicamentos.getValueAt(sel, 3).toString());
            this.lblDescrip.setText(this.jTableMedicamentos.getValueAt(sel, 4).toString());
            this.lblStockDet.setText(this.jTableMedicamentos.getValueAt(sel, 15).toString());
            this.lblStocks.setText(this.jTableMedicamentos.getValueAt(sel, 14).toString());
            this.lblDisplay.setText(this.jTableMedicamentos.getValueAt(sel, 6).toString());
            this.lblPrecioMin.setText(this.jTableMedicamentos.getValueAt(sel, 8).toString());
            this.lblPrecioNormal.setText(this.jTableMedicamentos.getValueAt(sel, 9).toString());
            this.lblPrecioUnidad.setText(this.jTableMedicamentos.getValueAt(sel, 10).toString());
            this.lblPromocion.setText(this.jTableMedicamentos.getValueAt(sel, 11).toString());
            this.lblAplicaDesde.setText(this.jTableMedicamentos.getValueAt(sel, 12).toString());
            this.lblBonificacion.setText(this.jTableMedicamentos.getValueAt(sel, 13).toString());
            this.lblcosto.setText(this.jTableMedicamentos.getValueAt(sel, 7).toString());
            this.lblBonoVenta.setText(this.jTableMedicamentos.getValueAt(sel, 18).toString());
            this.lblComponentes.setText(this.jTableMedicamentos.getValueAt(sel, 17).toString());

            this.jDialogSimilares.dispose();
        }

    }//GEN-LAST:event_jTableMedicamentosOpKeyPressed

    private void txtdetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdetalleKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            controllerProformas.setOp("E");
            controllerProformas.setCabecera(this.txtcabecera.getText());
            controllerProformas.setDetalle(this.txtdetalle.getText());
            controllerProformas.consultarMaestroProformas();

            controllerProformas.setOp("U");
            controllerProformas.setCabecera(this.txtcabecera.getText());
            controllerProformas.setDetalle(this.txtdetalle.getText());
            controllerProformas.consultarProformas(dtm, main.lblpermisos.getText());
            this.jTablePedidoInterno.getColumnModel().getColumn(2).setCellEditor(new MyTableCellEditor());
            this.jTablePedidoInterno.getColumnModel().getColumn(4).setCellEditor(new MyTableCellEditor());
            jTablePedidoInterno.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTablePedidoInterno.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTablePedidoInterno.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTablePedidoInterno.getColumnModel().getColumn(3).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTablePedidoInterno.getColumnModel().getColumn(4).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            jTablePedidoInterno.getColumnModel().getColumn(5).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
            this.lblitems.setText(this.jTablePedidoInterno.getRowCount() + " ITEM(S) AGREGADO(S).");
        }
    }//GEN-LAST:event_txtdetalleKeyReleased

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        if (!main.lblpermisos.getText().equals("ADMIN")) {
            dtm = new DefaultTableModel() {

                boolean[] canEdit = new boolean[]{
                    false, false, true, false, false, false, false, false, false, false, false, false, false
                };

                public boolean isCellEditable(int fila, int column) {
                    return canEdit[column];
                }
            };
            PedidoInterno.chkBuscVentas.setEnabled(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void codClienteCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codClienteCKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_codClienteCKeyReleased

    private void lblcodVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblcodVentaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lblcodVentaKeyReleased

    private void txtbusqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbusqKeyPressed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        clear();
        this.jMenu1.setSelected(false);
    }//GEN-LAST:event_jMenu1MousePressed

    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        if (StockOk() == true) {
            generarVenta();
            this.jMenu2.setSelected(false);
        } else {
            JOptionPane.showMessageDialog(this, "Productos incompletos.", "Error al intentar registrar", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jMenu2MousePressed

    private void jMenuActualizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuActualizarMousePressed
 if(main.lblpermisos.getText().equals("ADMIN")){
 controllerProformas.setOp("U");
        controllerProformas.setCodVenta(PedidoInterno.lblcodVenta.getText());
        controllerProformas.mntoActualizarProforma();
        generarVenta();
        JOptionPane.showMessageDialog(this, "Datos actualizados.", "Actualizados", JOptionPane.INFORMATION_MESSAGE);
        this.jMenuActualizar.setSelected(false);
 }else{
 JOptionPane.showMessageDialog(this, "Su nivel de permisos no admite ACTUALIZAR ventas.","Advertencia",JOptionPane.WARNING_MESSAGE);
 }
        
    }//GEN-LAST:event_jMenuActualizarMousePressed

    private void jMenu4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MousePressed
        this.dispose();
    }//GEN-LAST:event_jMenu4MousePressed

    private void jMenuBar1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MouseDragged
        Point ubicacion = MouseInfo.getPointerInfo().getLocation();//1

        System.out.println("Coordenadas: (" + ubicacion.x + "," + ubicacion.y + ")");//2
        setLocation((ubicacion.x - 0), (ubicacion.y) - 230);//3
        //239 268
    }//GEN-LAST:event_jMenuBar1MouseDragged

    private void jMenuBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MouseClicked

    }//GEN-LAST:event_jMenuBar1MouseClicked

    private void jMenuBar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MousePressed
        this.toFront();
        x = evt.getX();

        y = evt.getY();
    }//GEN-LAST:event_jMenuBar1MousePressed

    private void jMenu5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MousePressed
if(txtcabecera.getText().length()==3 && this.txtdetalle.getText().length()==8 &&
        jTablePedidoInterno.getRowCount()>0
        ){
 imprimir();
 
}else{
JOptionPane.showMessageDialog(this, "Impresión no válida.","Error en impresión",JOptionPane.ERROR_MESSAGE);
}
       
    }//GEN-LAST:event_jMenu5MousePressed

    private void jMenuAnularMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAnularMousePressed
 if(main.lblpermisos.getText().equals("ADMIN")){
        int rr = JOptionPane.showConfirmDialog(this, "Esta seguro que desea anular esta venta.", "Advertencia", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        if (rr == 0 && this.txtcabecera.getText().length()==3 && this.txtdetalle.getText().length()==8) {
            
            controllerProformas.setOp("U");
            controllerProformas.setCodVenta(PedidoInterno.lblcodVenta.getText());
            controllerProformas.mntoActualizarProforma();
            JOptionPane.showMessageDialog(this, "Venta anulada con exito.", "ANULADO", JOptionPane.INFORMATION_MESSAGE);
                        clear();
        }
 }else{
JOptionPane.showMessageDialog(this, "Su nivel de permisos no admite ANULAR ventas.","Advertencia",JOptionPane.WARNING_MESSAGE);
}

    }//GEN-LAST:event_jMenuAnularMousePressed

    private void RUCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RUCKeyReleased
 if (evt.getExtendedKeyCode() == KeyEvent.VK_F5) {
            if (validator.novacios("10-11", 2, this.Cliente, this.RUC) == false) {
                controllerClientes.setOp("C");
                controllerClientes.setNomb(this.Cliente.getText());
                controllerClientes.setDni(this.Dni.getText());
                controllerClientes.setDir("<<SIN DATOS>>");
                controllerClientes.setFono("<<SIN DATOS>>");
                controllerClientes.setRuc(this.RUC.getText());
                controllerClientes.mntoClientes();

                controllerClientes.setOp("S");
                controllerClientes.setBusc(this.Cliente.getText());
                System.out.println("Nombre enviado a ser buscado : " + this.Cliente.getText());
                controllerClientes.consultarClientes();

                TextAutoCompleterClientes(Cliente);
                if (this.codClienteC.getText().length() == 10) {
                    this.Cliente.setEnabled(false);
                    this.Dni.setEnabled(false);
                     this.RUC.setEnabled(false);
                    this.jDialog.setLocationRelativeTo(this);
                    this.txtbusq.setText(this.txtmed.getText());
                    this.txtbusq.requestFocus();
                    controllerMedicamentos.setOp("D");
                    controllerMedicamentos.setBusc(this.txtmed.getText());
                    controllerMedicamentos.consultarMedicamentos("VENTAS", dtm);
                    this.txtmed.setText(null);
                    jTableMedicamentos.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
                    jTableMedicamentos.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
                    this.jDialog.setVisible(true);
                    // this.txtmed.requestFocus();
                }

            }
        }    }//GEN-LAST:event_RUCKeyReleased

    private void RUCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RUCKeyTyped
  validator.solonumeros(evt, RUC, 11);
    }//GEN-LAST:event_RUCKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField Cliente;
    public static javax.swing.JTextField Direccion;
    public static javax.swing.JTextField Dni;
    public static javax.swing.JTextField RUC;
    public static javax.swing.JCheckBox chkBuscVentas;
    private javax.swing.JCheckBox chkmodificarClie;
    private javax.swing.JCheckBox chkprecioespecial;
    public static javax.swing.JTextField codClienteC;
    private javax.swing.JLabel costo;
    public static javax.swing.JDialog jDialog;
    private javax.swing.JDialog jDialogAgregar;
    private javax.swing.JDialog jDialogSimilares;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListCantidad;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    public static javax.swing.JMenu jMenuActualizar;
    public static javax.swing.JMenu jMenuAnular;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JTable jTableDetallesComp;
    public static javax.swing.JTable jTableMedicamentos;
    public static javax.swing.JTable jTableMedicamentosOp;
    public static javax.swing.JTable jTablePedidoInterno;
    private javax.swing.JLabel lblAplicaDesde;
    private javax.swing.JLabel lblBonificacion;
    private javax.swing.JLabel lblBonoVenta;
    private javax.swing.JLabel lblCod;
    private javax.swing.JLabel lblComponentes;
    private javax.swing.JLabel lblDescrip;
    private javax.swing.JLabel lblDisplay;
    private javax.swing.JLabel lblLabor;
    private javax.swing.JLabel lblMedicamento;
    private javax.swing.JLabel lblPrecioMin;
    private javax.swing.JLabel lblPrecioNormal;
    private javax.swing.JLabel lblPrecioUnidad;
    private javax.swing.JLabel lblPresent;
    private javax.swing.JLabel lblPromocion;
    private javax.swing.JLabel lblStockDet;
    private javax.swing.JLabel lblStocks;
    public static javax.swing.JTextField lblcodVenta;
    private javax.swing.JLabel lblcosto;
    public static javax.swing.JTextField lblimporte;
    public static javax.swing.JTextField lblimportetotal;
    private javax.swing.JLabel lblimportetotal1;
    private javax.swing.JLabel lblimportetotal2;
    public static javax.swing.JTextField lblitems;
    private javax.swing.JTextField txtbusq;
    private javax.swing.JTextField txtcabecera;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtdetalle;
    private javax.swing.JTextField txtmed;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.txtmed.requestFocus();
            this.jDialog.dispose();
        }
    }

    public static void sumarTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal sumredondeada = BigDecimal.ZERO;
        BigDecimal c;
        int x = PedidoInterno.jTablePedidoInterno.getRowCount();
        System.out.println("Hay " + x + " fila(s).");

        for (int y = 0; y < x; y++) {
            System.out.println("La tabla :" + y + " es: " + PedidoInterno.jTablePedidoInterno.getValueAt(y, 5).toString());
            c = BigDecimal.valueOf(Double.parseDouble(PedidoInterno.jTablePedidoInterno.getValueAt(y, 5).toString()));
            sum = sum.add(c).setScale(2, RoundingMode.HALF_UP);
            sumredondeada = sumredondeada.add(c);
            System.out.println("sumando sin redondear : " + sum + " redondeando : " + sumredondeada);
            // y++;
        };
        PedidoInterno.lblimportetotal.setText(sum.toString());
        PedidoInterno.lblimporte.setText(sumredondeada.setScale(1, RoundingMode.HALF_UP).toString() + "0");

    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getCellEditorValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean stopCellEditing() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelCellEditing() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
