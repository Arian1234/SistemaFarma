package vista;

import com.mxrck.autocompleter.TextAutoCompleter;
import controlador.controllerCompras;
import controlador.controllerDetalleCompras;
import controlador.controllerMedicamentos;
import controlador.controllerProveedores;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.modelProveedores;

/**
 *
 * @author AMolinaDeveloper
 */
public class Compras extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    public DefaultTableModel dtm;

    /**
     * Creates new form Compras
     */
    public Compras() {
        initComponents();
        TextAutoCompleterProveedores(this.proveedor);
    }

    public static boolean isFechaValida(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }

    public static boolean validaFechaMascara(String sFecha, String sMascara) {
        boolean retorno = false;
        try {
            //Convertir la fecha al Calendar
            java.util.Locale locInstancia = new java.util.Locale("es", "CL");
            java.text.DateFormat dfInstancia;
            java.util.Date dInstancia;
            dfInstancia = new java.text.SimpleDateFormat(sMascara, locInstancia);
            dInstancia = (java.util.Date) dfInstancia.parse(sFecha);
            java.util.Calendar cal = java.util.Calendar.getInstance(locInstancia);
            cal.setTime(dInstancia); //setear la fecha en cuestion al calendario
            retorno = true;
        } catch (java.text.ParseException excep) {
            retorno = false; //unparseable date
        } finally {
            return retorno;
        }
    }

    public void agregarMedicamento(KeyEvent evt) {
        String unid;
        int cant = 0;
        BigDecimal bon, bono;
        bon = new BigDecimal(this.txtbono.getText()).setScale(2, RoundingMode.HALF_UP);;
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER
                && this.txtcantidad.getText().length() > 0
                && this.txtprecioMinimo.getText().length() > 0
                && this.txtprecioNormal.getText().length() > 0
                && this.txtprecio.getText().length() > 0) {

            if (this.jListCantidad.getSelectedValue().toString().equals("CAJA")) {

                cant = Integer.parseInt(this.txtcantidad.getText()) * Integer.parseInt(lblDisplay.getText());
                bono = bon;
                unid = "CAJA";
            } else {
                cant = Integer.parseInt(this.txtcantidad.getText());
                //     cant = Integer.parseInt(this.txtcantidad.getText()) * Integer.parseInt(lblDisplay.getText());
                bono = bon;
                unid = "UNID";
            }

            if (cant >= Integer.parseInt(this.lblAplicaDesde.getText())) {
            }
            dtm = (DefaultTableModel) this.jTableCompras.getModel();
            Object[] obj = new Object[10];

            obj[0] = this.lblCod.getText();
            obj[1] = this.lblMedicamento.getText();
            obj[2] = this.txtcantidad.getText();
            obj[3] = unid;
            obj[4] = this.txtprecio.getText();
            obj[6] = this.txtprecioMinimo.getText();
            obj[7] = this.txtprecioNormal.getText();
            obj[8] = bono.toString();
            obj[9] = cant;
            BigDecimal canti = null, precio = null;
            canti = BigDecimal.valueOf(Double.parseDouble(this.txtcantidad.getText()));
            precio = BigDecimal.valueOf(Double.parseDouble(obj[4].toString()));
            if (this.jListCantidad.getSelectedValue().toString().equals("CAJA")) {
                obj[5] = canti.multiply(precio).setScale(2, RoundingMode.HALF_UP);

            } else {

                obj[5] = (precio.divide(BigDecimal.valueOf(Double.parseDouble(this.lblDisplay.getText())), 4, RoundingMode.HALF_UP)).multiply(canti).setScale(2, RoundingMode.HALF_UP);
            }

            dtm.addRow(obj);

            System.out.println(this.jTableCompras.getRowCount());
            this.lblitems.setText(this.jTableCompras.getRowCount() + " ITEM(S) AGREGADO(S).");
            sumarTotal();

            this.txtcantidad.setText(null);
            this.txtprecioMinimo.setText(null);
            this.txtprecioNormal.setText(null);
            this.txtprecio.setText(null);
            this.txtbono.setText(null);
            this.jDialogAgregar.dispose();
            this.txtbusq.setText(null);
            this.txtbusq.requestFocus();

        }
    }

    public void clear() {
        this.proveedor.setText(null);
        this.ruc.setText(null);
        this.Direccion.setText(null);
        this.codProvee.setText(null);
        this.txtmed.setText(null);
        this.txtmed.setEnabled(false);
        this.txtserie.setText(null);
        this.lblitems.setText("<<SIN DATOS>>");
        this.lblimportetotal.setText("<<SIN DATOS>>");
        this.lblcosto.setText("<<SIN DATOS>>");
        this.lblCod.setText("<<SIN DATOS>>");
        this.lblMedicamento.setText("<<SIN DATOS>>");
        this.lblLabor.setText("<<SIN DATOS>>");
        this.lblPresent.setText("<<SIN DATOS>>");
        this.lblDescrip.setText("<<SIN DATOS>>");
        this.lblStocks.setText("<<SIN DATOS>>");
        this.lblStockDeta.setText("<<SIN DATOS>>");
        this.lblDisplay.setText("<<SIN DATOS>>");
        this.lblPrecioMin.setText("<<SIN DATOS>>");
        this.lblPrecioNormal.setText("<<SIN DATOS>>");
        this.lblPrecioUnidad.setText("<<SIN DATOS>>");
        this.lblPromocion.setText("<<SIN DATOS>>");
        this.lblAplicaDesde.setText("<<SIN DATOS>>");
        this.lblBonificacion.setText("<<SIN DATOS>>");
        this.txtfecha.setText(null);
        this.txtDocumento.setText(null);
        this.proveedor.setEnabled(true);
        this.ruc.setEnabled(true);
        validator.vaciar_jtable(jTableCompras);
        validator.vaciar_jtable(jTableMedicamentos);
    }

    private void sumarTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal sumredondeada = BigDecimal.ZERO;
        BigDecimal c;
        int x = jTableCompras.getRowCount();
        System.out.println("Hay " + x + " fila(s).");

        for (int y = 0; y < x; y++) {
            System.out.println("La tabla :" + y + " es: " + jTableCompras.getValueAt(y, 5).toString());
            c = BigDecimal.valueOf(Double.parseDouble(jTableCompras.getValueAt(y, 5).toString()));
            sum = sum.add(c).setScale(2, RoundingMode.HALF_UP);
            sumredondeada = sumredondeada.add(c).setScale(1, RoundingMode.HALF_UP);
            System.out.println("sumando sin redondear : " + sum + " redondeando : " + sumredondeada);
            // y++;
        };
        this.lblimportetotal.setText(sum.toString());

    }

    public final void TextAutoCompleterProveedores(JTextField busc) {
        TextAutoCompleter Tac = new TextAutoCompleter(busc);

        modelProveedores.setOp("D");
        modelProveedores.setBusc("");
        ResultSet rs = modelProveedores.queryProveedores();
        try {
            Tac.removeAllItems();
            Tac.setMode(0);
            while (rs.next()) {
                Object[] obj = new Object[1];
                obj[0] = rs.getString("PROVEEDOR");
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
        lblStockDeta = new javax.swing.JLabel();
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
        lblbono = new javax.swing.JLabel();
        jDialogAgregar = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        txtcantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        txtprecioMinimo = new javax.swing.JTextField();
        txtprecioNormal = new javax.swing.JTextField();
        txtbono = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListCantidad = new javax.swing.JList();
        jDialogFecha = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        txtfecha = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        jDialogProveedores = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        txtcorrelativo = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProveedores = new javax.swing.JTable();
        txtProveedor = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblseleccion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        proveedor = new javax.swing.JTextField();
        chkBuscCompras = new javax.swing.JCheckBox();
        txtmed = new javax.swing.JTextField();
        ruc = new javax.swing.JTextField();
        codProvee = new javax.swing.JTextField();
        chkmodificarProvee = new javax.swing.JCheckBox();
        Direccion = new javax.swing.JTextField();
        txtserie = new javax.swing.JTextField();
        Fecha = new javax.swing.JTextField();
        chkBuscCompras1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCompras = new javax.swing.JTable();
        jLabel39 = new javax.swing.JLabel();
        lblimportetotal = new javax.swing.JTextField();
        lblimportetotal3 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblitems = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jDialog.setTitle("Agregar un producto");
        jDialog.setMinimumSize(new java.awt.Dimension(789, 187));
        jDialog.setModal(true);
        jDialog.setUndecorated(true);
        jDialog.setResizable(false);
        jDialog.setType(java.awt.Window.Type.UTILITY);

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        txtbusq.setBackground(new java.awt.Color(255, 204, 204));
        txtbusq.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtbusq.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtbusq.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Realizar búsqueda del producto", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtbusq.setCaretColor(new java.awt.Color(51, 204, 255));
        txtbusq.addKeyListener(new java.awt.event.KeyAdapter() {
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMedicamentos.setToolTipText("");
        jTableMedicamentos.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableMedicamentos.setShowHorizontalLines(false);
        jTableMedicamentos.getTableHeader().setResizingAllowed(false);
        jTableMedicamentos.getTableHeader().setReorderingAllowed(false);
        jTableMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMedicamentosMouseClicked(evt);
            }
        });
        jTableMedicamentos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableMedicamentosFocusGained(evt);
            }
        });
        jTableMedicamentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableMedicamentosKeyReleased(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableMedicamentosKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTableMedicamentos);
        if (jTableMedicamentos.getColumnModel().getColumnCount() > 0) {
            jTableMedicamentos.getColumnModel().getColumn(0).setResizable(false);
            jTableMedicamentos.getColumnModel().getColumn(1).setResizable(false);
        }

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

        lblMedicamento.setFont(new java.awt.Font("Calibri Light", 1, 11)); // NOI18N
        lblMedicamento.setText("<<SIN DATOS>>");
        lblMedicamento.setMaximumSize(new java.awt.Dimension(129, 14));
        lblMedicamento.setMinimumSize(new java.awt.Dimension(129, 14));
        lblMedicamento.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 0, 0));
        jLabel9.setText("LABORATORIO :");

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

        lblStockDeta.setFont(new java.awt.Font("Calibri Light", 1, 11)); // NOI18N
        lblStockDeta.setText("<<SIN DATOS>>");
        lblStockDeta.setMaximumSize(new java.awt.Dimension(129, 14));
        lblStockDeta.setMinimumSize(new java.awt.Dimension(129, 14));
        lblStockDeta.setPreferredSize(new java.awt.Dimension(129, 14));

        lblStocks.setFont(new java.awt.Font("Calibri Light", 1, 11)); // NOI18N
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

        lblPromocion.setFont(new java.awt.Font("Calibri Light", 1, 11)); // NOI18N
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
        costo.setForeground(new java.awt.Color(153, 0, 0));
        costo.setText("COSTO :");

        lblcosto.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblcosto.setText("<<SIN DATOS>>");
        lblcosto.setMaximumSize(new java.awt.Dimension(129, 14));
        lblcosto.setMinimumSize(new java.awt.Dimension(129, 14));
        lblcosto.setPreferredSize(new java.awt.Dimension(129, 14));

        jLabel32.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(153, 0, 0));
        jLabel32.setText("BONO X VENT :");

        lblbono.setFont(new java.awt.Font("Calibri Light", 3, 11)); // NOI18N
        lblbono.setForeground(new java.awt.Color(204, 0, 0));
        lblbono.setText("<<SIN DATOS>>");
        lblbono.setMaximumSize(new java.awt.Dimension(129, 14));
        lblbono.setMinimumSize(new java.awt.Dimension(129, 14));
        lblbono.setPreferredSize(new java.awt.Dimension(129, 14));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addComponent(txtbusq))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPresent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblStockDeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblStocks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLabor, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel29)
                            .addComponent(jLabel27)
                            .addComponent(jLabel25)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPrecioMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPrecioNormal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPrecioUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPromocion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAplicaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBonificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(costo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblbono, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtbusq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblCod, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblLabor, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lblPresent, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(lblDescrip, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(lblStocks, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lblStockDeta, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32)
                            .addComponent(lblbono, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lblDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(costo)
                            .addComponent(lblcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(lblPrecioMin, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(lblPrecioNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(lblPrecioUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(lblPromocion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(lblAplicaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(lblBonificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogLayout = new javax.swing.GroupLayout(jDialog.getContentPane());
        jDialog.getContentPane().setLayout(jDialogLayout);
        jDialogLayout.setHorizontalGroup(
            jDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialogLayout.setVerticalGroup(
            jDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialogAgregar.setTitle("Ingresar cantidades");
        jDialogAgregar.setMinimumSize(new java.awt.Dimension(524, 158));
        jDialogAgregar.setModal(true);
        jDialogAgregar.setUndecorated(true);
        jDialogAgregar.setPreferredSize(new java.awt.Dimension(524, 158));

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar cantidad de productos comprados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 13))); // NOI18N

        txtcantidad.setBackground(new java.awt.Color(255, 204, 204));
        txtcantidad.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtcantidad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtcantidad.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Cantidad", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
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

        txtprecio.setBackground(new java.awt.Color(255, 255, 204));
        txtprecio.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtprecio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtprecio.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Precio Costo por caja", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtprecio.setCaretColor(new java.awt.Color(51, 204, 255));
        txtprecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtprecioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtprecioFocusLost(evt);
            }
        });
        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprecioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprecioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });

        txtprecioMinimo.setBackground(new java.awt.Color(255, 255, 204));
        txtprecioMinimo.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtprecioMinimo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtprecioMinimo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Precio Minimo por caja", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtprecioMinimo.setCaretColor(new java.awt.Color(51, 204, 255));
        txtprecioMinimo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtprecioMinimoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtprecioMinimoFocusLost(evt);
            }
        });
        txtprecioMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprecioMinimoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprecioMinimoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioMinimoKeyTyped(evt);
            }
        });

        txtprecioNormal.setBackground(new java.awt.Color(255, 255, 204));
        txtprecioNormal.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtprecioNormal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtprecioNormal.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Precio Normal por caja", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtprecioNormal.setCaretColor(new java.awt.Color(51, 204, 255));
        txtprecioNormal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtprecioNormalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtprecioNormalFocusLost(evt);
            }
        });
        txtprecioNormal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprecioNormalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprecioNormalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioNormalKeyTyped(evt);
            }
        });

        txtbono.setBackground(new java.awt.Color(204, 255, 255));
        txtbono.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtbono.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtbono.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Bonificacion Por Venta[CAJA]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtbono.setCaretColor(new java.awt.Color(51, 204, 255));
        txtbono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtbonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtbonoFocusLost(evt);
            }
        });
        txtbono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbonoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbonoKeyTyped(evt);
            }
        });

        jListCantidad.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jListCantidad.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "CAJA", "UNIDAD" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListCantidad.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListCantidad.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jListCantidad.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListCantidadValueChanged(evt);
            }
        });
        jListCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jListCantidadKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jListCantidad);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtprecioMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtprecioNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbono, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprecioMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecioNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogAgregarLayout = new javax.swing.GroupLayout(jDialogAgregar.getContentPane());
        jDialogAgregar.getContentPane().setLayout(jDialogAgregarLayout);
        jDialogAgregarLayout.setHorizontalGroup(
            jDialogAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialogAgregarLayout.setVerticalGroup(
            jDialogAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialogFecha.setTitle("Ingresar Fecha & Documento");
        jDialogFecha.setMinimumSize(new java.awt.Dimension(400, 99));
        jDialogFecha.setModal(true);
        jDialogFecha.setUndecorated(true);

        jPanel5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar la fecha y número correlativo de la compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 13))); // NOI18N

        txtfecha.setBackground(new java.awt.Color(255, 255, 204));
        txtfecha.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtfecha.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtfecha.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Ingrese Fecha [YYYY-MM-DD]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtfecha.setCaretColor(new java.awt.Color(51, 204, 255));
        txtfecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtfechaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfechaFocusLost(evt);
            }
        });
        txtfecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfechaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfechaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfechaKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel8.setText("[Esc] para cancelar");

        txtDocumento.setBackground(new java.awt.Color(255, 204, 204));
        txtDocumento.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtDocumento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDocumento.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Ingrese Serie del Documento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtDocumento.setCaretColor(new java.awt.Color(51, 204, 255));
        txtDocumento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDocumentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDocumentoFocusLost(evt);
            }
        });
        txtDocumento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))
                .addGap(10, 10, 10))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogFechaLayout = new javax.swing.GroupLayout(jDialogFecha.getContentPane());
        jDialogFecha.getContentPane().setLayout(jDialogFechaLayout);
        jDialogFechaLayout.setHorizontalGroup(
            jDialogFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogFechaLayout.setVerticalGroup(
            jDialogFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialogProveedores.setBackground(new java.awt.Color(153, 153, 153));
        jDialogProveedores.setMinimumSize(new java.awt.Dimension(458, 303));
        jDialogProveedores.setModal(true);
        jDialogProveedores.setUndecorated(true);

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));

        txtcorrelativo.setBackground(new java.awt.Color(255, 204, 204));
        txtcorrelativo.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        txtcorrelativo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtcorrelativo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Número correlativo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtcorrelativo.setCaretColor(new java.awt.Color(51, 204, 255));
        txtcorrelativo.setEnabled(false);
        txtcorrelativo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcorrelativoKeyReleased(evt);
            }
        });

        jTableProveedores.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProveedores.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableProveedores.getTableHeader().setReorderingAllowed(false);
        jTableProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableProveedoresMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTableProveedores);
        if (jTableProveedores.getColumnModel().getColumnCount() > 0) {
            jTableProveedores.getColumnModel().getColumn(0).setResizable(false);
            jTableProveedores.getColumnModel().getColumn(1).setResizable(false);
            jTableProveedores.getColumnModel().getColumn(2).setResizable(false);
            jTableProveedores.getColumnModel().getColumn(3).setResizable(false);
        }

        txtProveedor.setBackground(new java.awt.Color(255, 255, 153));
        txtProveedor.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        txtProveedor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Buscar por proveedor", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtProveedor.setCaretColor(new java.awt.Color(51, 204, 255));
        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProveedorKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Apply.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblseleccion.setFont(new java.awt.Font("Calibri Light", 0, 10)); // NOI18N
        lblseleccion.setForeground(new java.awt.Color(255, 255, 255));
        lblseleccion.setText("<<SIN SELECCION>>");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtcorrelativo, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
            .addComponent(txtProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblseleccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(txtcorrelativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(lblseleccion))
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialogProveedoresLayout = new javax.swing.GroupLayout(jDialogProveedores.getContentPane());
        jDialogProveedores.getContentPane().setLayout(jDialogProveedoresLayout);
        jDialogProveedoresLayout.setHorizontalGroup(
            jDialogProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogProveedoresLayout.setVerticalGroup(
            jDialogProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Registrar Compra");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/display.png"))); // NOI18N
        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(975, 437));
        setPreferredSize(new java.awt.Dimension(975, 437));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        proveedor.setName("proveedor");
        proveedor.setBackground(new java.awt.Color(244, 245, 242));
        proveedor.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        proveedor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        proveedor.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Proveedor", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        proveedor.setCaretColor(new java.awt.Color(51, 204, 255));
        proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedorActionPerformed(evt);
            }
        });
        proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                proveedorKeyReleased(evt);
            }
        });

        chkBuscCompras.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        chkBuscCompras.setText("REALIZAR BUSQUEDA DE COMPRAS");
        chkBuscCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBuscComprasActionPerformed(evt);
            }
        });

        txtmed.setBackground(new java.awt.Color(244, 245, 242));
        txtmed.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtmed.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtmed.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Productos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtmed.setCaretColor(new java.awt.Color(51, 204, 255));
        txtmed.setEnabled(false);
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

        ruc.setName("ruc [F5]");
        ruc.setBackground(new java.awt.Color(255, 153, 153));
        ruc.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        ruc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        ruc.setToolTipText("");
        ruc.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Ruc", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        ruc.setCaretColor(new java.awt.Color(51, 204, 255));
        ruc.setEnabled(false);
        ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rucKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rucKeyTyped(evt);
            }
        });

        codProvee.setEditable(false);
        codProvee.setBackground(new java.awt.Color(255, 255, 204));
        codProvee.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        codProvee.setMaximumSize(new java.awt.Dimension(100, 28));
        codProvee.setMinimumSize(new java.awt.Dimension(100, 28));
        codProvee.setPreferredSize(new java.awt.Dimension(100, 28));

        chkmodificarProvee.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        chkmodificarProvee.setText("MODIFICAR EL PROVEEDOR");
        chkmodificarProvee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkmodificarProveeActionPerformed(evt);
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

        txtserie.setBackground(new java.awt.Color(255, 255, 204));
        txtserie.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        txtserie.setEnabled(false);
        txtserie.setMaximumSize(new java.awt.Dimension(100, 28));
        txtserie.setMinimumSize(new java.awt.Dimension(100, 28));
        txtserie.setPreferredSize(new java.awt.Dimension(100, 28));
        txtserie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtserieKeyReleased(evt);
            }
        });

        ruc.setName("ruc [F5]");
        Fecha.setBackground(new java.awt.Color(255, 255, 153));
        Fecha.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        Fecha.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Fecha.setToolTipText("");
        Fecha.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Fecha", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Fecha.setCaretColor(new java.awt.Color(51, 204, 255));
        Fecha.setEnabled(false);
        Fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FechaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FechaKeyTyped(evt);
            }
        });

        chkBuscCompras1.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        chkBuscCompras1.setText("MODIFICAR FECHA");
        chkBuscCompras1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBuscCompras1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Direccion))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtmed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkBuscCompras1)
                        .addGap(63, 63, 63)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(codProvee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkmodificarProvee)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkBuscCompras))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(codProvee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkmodificarProvee)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkBuscCompras1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkBuscCompras)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Items de la Compra", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        jTableCompras.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTableCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "###", "Producto", "Cantidad", "Unid.", "Precio", "Total", "precioMinimo", "PrecioNormal", "Bono", "cantidadTotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCompras.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableCompras.getTableHeader().setReorderingAllowed(false);
        jTableCompras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableComprasMouseClicked(evt);
            }
        });
        jTableCompras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableComprasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCompras);
        if (jTableCompras.getColumnModel().getColumnCount() > 0) {
            jTableCompras.getColumnModel().getColumn(0).setResizable(false);
            jTableCompras.getColumnModel().getColumn(1).setResizable(false);
            jTableCompras.getColumnModel().getColumn(2).setResizable(false);
            jTableCompras.getColumnModel().getColumn(3).setResizable(false);
            jTableCompras.getColumnModel().getColumn(4).setResizable(false);
            jTableCompras.getColumnModel().getColumn(5).setResizable(false);
            jTableCompras.getColumnModel().getColumn(6).setResizable(false);
            jTableCompras.getColumnModel().getColumn(7).setResizable(false);
            jTableCompras.getColumnModel().getColumn(8).setResizable(false);
            jTableCompras.getColumnModel().getColumn(9).setResizable(false);
        }

        jLabel39.setFont(new java.awt.Font("Calibri Light", 1, 13)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(153, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Monto total :");

        lblimportetotal.setEditable(false);
        lblimportetotal.setBackground(new java.awt.Color(255, 255, 153));
        lblimportetotal.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N

        lblimportetotal3.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        lblimportetotal3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblimportetotal3.setText(" sol(es)");
        lblimportetotal3.setMaximumSize(new java.awt.Dimension(157, 14));
        lblimportetotal3.setMinimumSize(new java.awt.Dimension(157, 14));
        lblimportetotal3.setPreferredSize(new java.awt.Dimension(157, 14));

        jLabel36.setFont(new java.awt.Font("Calibri Light", 1, 13)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(153, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Items:");

        lblitems.setEditable(false);
        lblitems.setBackground(new java.awt.Color(255, 153, 102));
        lblitems.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblimportetotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblimportetotal3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblitems, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel36)
                    .addComponent(lblimportetotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblimportetotal3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblitems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
        jMenu1.setText("NUEVO");
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
        jMenu2.setText("REGISTRAR");
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

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Sync.png"))); // NOI18N
        jMenu5.setText("ACTUALIZAR");
        jMenu5.setToolTipText("");
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

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/No-entry.png"))); // NOI18N
        jMenu3.setText("ANULAR");
        jMenu3.setToolTipText("");
        jMenu3.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu3MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

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
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proveedorActionPerformed

    private void proveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_proveedorKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            controllerProveedores.setOp("E");
            controllerProveedores.setBusc(this.proveedor.getText());
            controllerProveedores.consultarProveedores();
            if (Compras.codProvee.getText().length() == 5) {
                this.proveedor.setEnabled(false);
                Compras.ruc.setEnabled(false);
                this.jDialogFecha.setLocationRelativeTo(this);
                this.jDialogFecha.setVisible(true);
            }

        }
    }//GEN-LAST:event_proveedorKeyReleased

    private void txtmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmedActionPerformed

    private void txtmedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmedFocusGained
    }//GEN-LAST:event_txtmedFocusGained

    private void txtmedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmedKeyPressed
    }//GEN-LAST:event_txtmedKeyPressed

    private void txtmedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmedKeyTyped
    }//GEN-LAST:event_txtmedKeyTyped

    private void txtmedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmedKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            this.jDialog.setLocationRelativeTo(this);
            this.txtbusq.setText(this.txtmed.getText());
            this.txtbusq.requestFocus();
            controllerMedicamentos.setOp("D");
            controllerMedicamentos.setBusc(this.txtmed.getText());
            controllerMedicamentos.consultarMedicamentos("COMPRAS", dtm);
            this.txtmed.setText(null);
            this.jDialog.setVisible(true);
        }
    }//GEN-LAST:event_txtmedKeyReleased

    private void rucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rucKeyReleased

    }//GEN-LAST:event_rucKeyReleased

    private void rucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rucKeyTyped
        validator.solonumeros(evt, ruc, 8);
    }//GEN-LAST:event_rucKeyTyped

    private void chkmodificarProveeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkmodificarProveeActionPerformed
        if (this.chkmodificarProvee.isSelected()) {

            this.proveedor.setText(null);
            this.ruc.setText(null);
            this.Direccion.setText(null);
            this.codProvee.setText(null);
            this.proveedor.setEnabled(true);
            this.chkmodificarProvee.setSelected(false);
            this.proveedor.requestFocus();
            this.txtmed.setEnabled(false);
            this.txtfecha.setText(null);
            this.txtDocumento.setText(null);
            this.Fecha.setText(null);
            this.txtserie.setText(null);
        }
    }//GEN-LAST:event_chkmodificarProveeActionPerformed

    private void DireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DireccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionKeyReleased

    private void jTableComprasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableComprasMouseClicked

    }//GEN-LAST:event_jTableComprasMouseClicked

    private void jTableComprasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableComprasKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            dtm = (DefaultTableModel) this.jTableCompras.getModel();
            System.out.println("Selecciono : " + this.jTableCompras.getSelectedRow());
            dtm.removeRow(this.jTableCompras.getSelectedRow());

            this.lblitems.setText(this.jTableCompras.getRowCount() + " ITEM(S) AGREGADO(S).");
            sumarTotal();
        }

    }//GEN-LAST:event_jTableComprasKeyPressed

    private void txtbusqKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusqKeyReleased
        controllerMedicamentos.setOp("D");
        controllerMedicamentos.setBusc(this.txtbusq.getText());
        controllerMedicamentos.consultarMedicamentos("COMPRAS", dtm);
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            Compras.jTableMedicamentos.requestFocus();
        }
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            Compras.jDialog.dispose();
        }
    }//GEN-LAST:event_txtbusqKeyReleased

    private void jTableMedicamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMedicamentosMouseClicked
        int sel = Compras.jTableMedicamentos.getSelectedRow();

        this.lblCod.setText(Compras.jTableMedicamentos.getValueAt(sel, 0).toString());
        this.lblMedicamento.setText(Compras.jTableMedicamentos.getValueAt(sel, 1).toString());
        this.lblLabor.setText(Compras.jTableMedicamentos.getValueAt(sel, 2).toString());
        this.lblPresent.setText(Compras.jTableMedicamentos.getValueAt(sel, 3).toString());
        this.lblDescrip.setText(Compras.jTableMedicamentos.getValueAt(sel, 4).toString());
        this.lblStocks.setText(Compras.jTableMedicamentos.getValueAt(sel, 14).toString());
        this.lblStockDeta.setText(Compras.jTableMedicamentos.getValueAt(sel, 15).toString());
        this.lblDisplay.setText(Compras.jTableMedicamentos.getValueAt(sel, 6).toString());
        this.lblPrecioMin.setText(Compras.jTableMedicamentos.getValueAt(sel, 8).toString());
        this.lblPrecioNormal.setText(Compras.jTableMedicamentos.getValueAt(sel, 9).toString());
        this.lblPrecioUnidad.setText(Compras.jTableMedicamentos.getValueAt(sel, 10).toString());
        this.lblPromocion.setText(Compras.jTableMedicamentos.getValueAt(sel, 11).toString());
        this.lblAplicaDesde.setText(Compras.jTableMedicamentos.getValueAt(sel, 12).toString());
        this.lblBonificacion.setText(Compras.jTableMedicamentos.getValueAt(sel, 13).toString());
        this.lblcosto.setText(Compras.jTableMedicamentos.getValueAt(sel, 7).toString());
        this.lblbono.setText(this.jTableMedicamentos.getValueAt(sel, 18).toString());
    }//GEN-LAST:event_jTableMedicamentosMouseClicked

    private void jTableMedicamentosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableMedicamentosFocusGained
    }//GEN-LAST:event_jTableMedicamentosFocusGained

    private void jTableMedicamentosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMedicamentosKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("<<ENTER>>");

            this.jDialogAgregar.setLocationRelativeTo(this);
            this.txtbono.setText(BigDecimal.valueOf(Double.parseDouble(this.lblbono.getText())).multiply(BigDecimal.valueOf(Double.parseDouble(this.lblDisplay.getText()))).toString());
            this.txtprecioMinimo.setText(this.lblPrecioMin.getText());
            this.txtprecioNormal.setText(this.lblPrecioNormal.getText());
            this.jListCantidad.setSelectedIndex(0);
            this.jDialogAgregar.setVisible(true);

        }
    }//GEN-LAST:event_jTableMedicamentosKeyPressed

    private void jTableMedicamentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMedicamentosKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            Compras.jDialog.dispose();
        }

        if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {
            int sel = this.jTableMedicamentos.getSelectedRow();

            this.lblCod.setText(this.jTableMedicamentos.getValueAt(sel, 0).toString());
            this.lblMedicamento.setText(this.jTableMedicamentos.getValueAt(sel, 1).toString());
            this.lblLabor.setText(this.jTableMedicamentos.getValueAt(sel, 2).toString());
            this.lblPresent.setText(this.jTableMedicamentos.getValueAt(sel, 3).toString());
            this.lblDescrip.setText(this.jTableMedicamentos.getValueAt(sel, 4).toString());
            this.lblStockDeta.setText(this.jTableMedicamentos.getValueAt(sel, 15).toString());
            this.lblStocks.setText(this.jTableMedicamentos.getValueAt(sel, 14).toString());
            this.lblDisplay.setText(this.jTableMedicamentos.getValueAt(sel, 6).toString());
            this.lblPrecioMin.setText(this.jTableMedicamentos.getValueAt(sel, 8).toString());
            this.lblPrecioNormal.setText(this.jTableMedicamentos.getValueAt(sel, 9).toString());
            this.lblPrecioUnidad.setText(this.jTableMedicamentos.getValueAt(sel, 10).toString());
            this.lblPromocion.setText(this.jTableMedicamentos.getValueAt(sel, 11).toString());
            this.lblAplicaDesde.setText(this.jTableMedicamentos.getValueAt(sel, 12).toString());
            this.lblBonificacion.setText(this.jTableMedicamentos.getValueAt(sel, 13).toString());
            this.lblcosto.setText(this.jTableMedicamentos.getValueAt(sel, 7).toString());
            this.lblbono.setText(this.jTableMedicamentos.getValueAt(sel, 18).toString());
        }
    }//GEN-LAST:event_jTableMedicamentosKeyReleased

    private void txtcantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcantidadFocusGained
    }//GEN-LAST:event_txtcantidadFocusGained

    private void txtcantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcantidadFocusLost
    }//GEN-LAST:event_txtcantidadFocusLost

    private void txtcantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyPressed
    }//GEN-LAST:event_txtcantidadKeyPressed

    private void txtcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.txtcantidad.setText(null);
            jDialogAgregar.dispose();
        }
    }//GEN-LAST:event_txtcantidadKeyReleased

    private void txtcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyTyped
        validator.solonumeros(evt, this.txtcantidad, 3);
    }//GEN-LAST:event_txtcantidadKeyTyped

    private void txtprecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtprecioFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioFocusGained

    private void txtprecioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtprecioFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioFocusLost

    private void txtprecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioKeyPressed

    private void txtprecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.txtcantidad.setText(null);
            jDialogAgregar.dispose();
        }
    }//GEN-LAST:event_txtprecioKeyReleased

    private void txtprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyTyped
        validator.soloDecimales(evt, txtprecio);
    }//GEN-LAST:event_txtprecioKeyTyped

    private void txtprecioMinimoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtprecioMinimoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioMinimoFocusLost

    private void txtprecioMinimoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtprecioMinimoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioMinimoFocusGained

    private void txtprecioMinimoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioMinimoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioMinimoKeyPressed

    private void txtprecioMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioMinimoKeyTyped
        validator.soloDecimales(evt, txtprecioMinimo);
    }//GEN-LAST:event_txtprecioMinimoKeyTyped

    private void txtprecioMinimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioMinimoKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.txtcantidad.setText(null);
            jDialogAgregar.dispose();
        }
    }//GEN-LAST:event_txtprecioMinimoKeyReleased

    private void txtprecioNormalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtprecioNormalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioNormalFocusLost

    private void txtprecioNormalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtprecioNormalFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioNormalFocusGained

    private void txtprecioNormalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioNormalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioNormalKeyPressed

    private void txtprecioNormalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioNormalKeyTyped
        validator.soloDecimales(evt, txtprecioNormal);
    }//GEN-LAST:event_txtprecioNormalKeyTyped

    private void txtprecioNormalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioNormalKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.txtcantidad.setText(null);
            jDialogAgregar.dispose();
        }
    }//GEN-LAST:event_txtprecioNormalKeyReleased

    private void chkBuscComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBuscComprasActionPerformed
        if (this.chkBuscCompras.isSelected()) {
            this.txtserie.setEnabled(true);
            this.txtserie.requestFocus();
        }
    }//GEN-LAST:event_chkBuscComprasActionPerformed

    private void FechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FechaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaKeyReleased

    private void FechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FechaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaKeyTyped

    private void txtbonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtbonoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbonoFocusLost

    private void txtbonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtbonoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbonoFocusGained

    private void txtbonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbonoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbonoKeyPressed

    private void txtbonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbonoKeyTyped

    private void txtbonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbonoKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.txtcantidad.setText(null);
            jDialogAgregar.dispose();
        }
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            this.agregarMedicamento(evt);
        }
    }//GEN-LAST:event_txtbonoKeyReleased

    private void txtfechaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfechaFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaFocusGained

    private void txtfechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfechaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaFocusLost

    private void txtfechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaKeyPressed

    private void txtfechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.jDialogFecha.dispose();
        }
    }//GEN-LAST:event_txtfechaKeyReleased

    private void txtfechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaKeyTyped

    private void txtDocumentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDocumentoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoFocusGained

    private void txtDocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDocumentoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoFocusLost

    private void txtDocumentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoKeyPressed

    private void txtDocumentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.jDialogFecha.dispose();
        }

        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            controllerCompras.setOp("S");
            controllerCompras.setProveedor(this.codProvee.getText());
            controllerCompras.setSerie(this.txtDocumento.getText());
            controllerCompras.setFecha(this.txtfecha.getText());
            String rs = controllerCompras.consultarComprasGeneradas();

        }
    }//GEN-LAST:event_txtDocumentoKeyReleased

    private void txtDocumentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoKeyTyped

    private void chkBuscCompras1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBuscCompras1ActionPerformed
        if (this.chkBuscCompras1.isSelected() == true) {
            this.jDialogFecha.setLocationRelativeTo(this);
            this.jDialogFecha.setVisible(true);
            this.chkBuscCompras1.setSelected(false);
        }
    }//GEN-LAST:event_chkBuscCompras1ActionPerformed

    private void jListCantidadValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListCantidadValueChanged
        System.out.println(this.jListCantidad.getSelectedValue().toString());
        if (this.jListCantidad.getSelectedValue().toString().equals("CAJA")) {
            //agregarMedicamentos();

        } else {
            if (this.jListCantidad.getSelectedValue().toString().equals("UNIDAD")) {
                this.txtcantidad.requestFocus();
                //   this.chkprecioespecial.setEnabled(false);
            }

        }
    }//GEN-LAST:event_jListCantidadValueChanged

    private void jListCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListCantidadKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.jDialogAgregar.dispose();
            this.txtcantidad.setText(null);
            this.txtprecio.setText(null);
            this.txtprecioMinimo.setText(null);
            this.txtprecioNormal.setText(null);
            this.txtbono.setText(null);
        }


    }//GEN-LAST:event_jListCantidadKeyReleased

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        clear();
        this.jMenu1.setSelected(false);
    }//GEN-LAST:event_jMenu1MousePressed

    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        try {
            if (Compras.codProvee.getText().length() == 5 && Compras.jTableCompras.getRowCount() > 0 && this.txtserie.getText().length() > 0
                    && this.txtfecha.getText().length() > 0 && this.txtserie.getText().length() > 0) {
                System.out.println("Datos validos");
                controllerCompras.setOp("I");
                controllerCompras.setCod(0);
                controllerCompras.setCodProvee(Integer.parseInt(Compras.codProvee.getText()));
                controllerCompras.setSerie(this.txtserie.getText());
                controllerCompras.setFecha(this.txtfecha.getText());
                controllerCompras.setTotal(BigDecimal.valueOf(Double.parseDouble(lblimportetotal.getText())));
                String xx = controllerCompras.mntoCompras();

                int x = 0;
                while (x < jTableCompras.getRowCount()) {
                    System.out.println("Recorro la fila " + x + " de " + jTableCompras.getRowCount());
                    controllerDetalleCompras.setOp("I");
                    controllerDetalleCompras.setCod(0);
                    controllerDetalleCompras.setCodMed(Integer.parseInt(this.jTableCompras.getValueAt(x, 0).toString()));
                    controllerDetalleCompras.setPrecio(BigDecimal.valueOf(Double.parseDouble(this.jTableCompras.getValueAt(x, 4).toString())));
                    controllerDetalleCompras.setCantidad(Integer.parseInt(this.jTableCompras.getValueAt(x, 2).toString()));
                    controllerDetalleCompras.setCodProvee(Integer.parseInt(this.codProvee.getText()));
                    controllerDetalleCompras.setSerie((this.txtserie.getText()));

                    controllerDetalleCompras.setPrecioM(BigDecimal.valueOf(Double.parseDouble(this.jTableCompras.getValueAt(x, 6).toString())));
                    controllerDetalleCompras.setPrecioN(BigDecimal.valueOf(Double.parseDouble(this.jTableCompras.getValueAt(x, 7).toString())));
                    controllerDetalleCompras.setBono(BigDecimal.valueOf(Double.parseDouble(this.jTableCompras.getValueAt(x, 8).toString())).setScale(3, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(Double.parseDouble(this.lblDisplay.getText())), 3, RoundingMode.HALF_UP).setScale(3, RoundingMode.HALF_UP));
                    controllerDetalleCompras.setCantUnid(Integer.parseInt(this.jTableCompras.getValueAt(x, 9).toString()));
                    controllerDetalleCompras.setUnidMed(this.jTableCompras.getValueAt(x, 3).toString());
                    controllerDetalleCompras.setTotal(BigDecimal.valueOf(Double.parseDouble(this.jTableCompras.getValueAt(x, 5).toString())));
                    System.out.println(controllerDetalleCompras.mntoDetallesCompras());
                    x++;
                }
                JOptionPane.showMessageDialog(null, "Compra generada exitosamente.", "Compra guardada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Datos invalidos");
            }
        } catch (NumberFormatException | HeadlessException ex) {
            System.err.toString();
        }

        this.jMenu2.setSelected(false);
    }//GEN-LAST:event_jMenu2MousePressed

    private void jMenu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MousePressed
        if (!this.txtserie.getText().isEmpty() && this.codProvee.getText().length() == 5 && Compras.jTableCompras.getRowCount()>0) {
            controllerDetalleCompras.setOp("DELETE");
            controllerDetalleCompras.setCorrelativo(this.txtserie.getText());
            controllerDetalleCompras.setProvee(this.codProvee.getText());
            controllerDetalleCompras.mntoActualizarCompras();
            JOptionPane.showMessageDialog(this, "Exito al anular compra.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

        }else{
           JOptionPane.showMessageDialog(this, "No es posible anular.", "Error en anulación", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenu3MousePressed

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

    private void txtserieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtserieKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            if (!this.txtserie.getText().isEmpty()) {
                this.lblseleccion.setText("<<SIN SELECCION>>");
                this.txtcorrelativo.setText(this.txtserie.getText());
                this.jDialogProveedores.setLocationRelativeTo(this);
                controllerProveedores.setOp("D");
                controllerProveedores.setBusc("");
                controllerProveedores.consultarProveedor("COMPRA", dtm);
                this.jDialogProveedores.setVisible(true);
            }
        }
    }//GEN-LAST:event_txtserieKeyReleased

    private void txtcorrelativoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorrelativoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorrelativoKeyReleased

    private void txtProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyReleased
        controllerProveedores.setOp("D");
        controllerProveedores.setBusc(this.txtProveedor.getText());
        controllerProveedores.consultarProveedor("COMPRA", dtm);
    }//GEN-LAST:event_txtProveedorKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            controllerDetalleCompras.setOp("MAESTRO");
            controllerDetalleCompras.setCorrelativo(this.txtserie.getText());
            controllerDetalleCompras.setProvee(this.jTableProveedores.getValueAt(this.jTableProveedores.getSelectedRow(), 0).toString());
            ResultSet rs = controllerDetalleCompras.mntoActualizarCompras();
            if (rs.next()) {
                Compras.codProvee.setText(rs.getString("CODPROVEE"));
                this.proveedor.setText(rs.getString("PROVEEDOR"));
                this.lblimportetotal.setText(rs.getString("TOTAL"));
                this.Fecha.setText(rs.getString("FECHA"));
                this.ruc.setText(rs.getString("RUC"));
                this.Direccion.setText(rs.getString("DIRECCION"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
        }
        controllerDetalleCompras.consultarDetallesCompras(this.txtserie.getText(), this.jTableProveedores.getValueAt(this.jTableProveedores.getSelectedRow(), 0).toString(), dtm, "");
        this.txtserie.setEnabled(false);
        this.jDialogProveedores.dispose();
        this.chkBuscCompras.setSelected(false);
        this.txtmed.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.jDialogProveedores.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTableProveedoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProveedoresMousePressed
        this.lblseleccion.setText("Proveedor seleccionado con codigo : " + this.jTableProveedores.getValueAt(this.jTableProveedores.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_jTableProveedoresMousePressed

    private void jMenu5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MousePressed
        if (!this.txtserie.getText().isEmpty() && this.codProvee.getText().length() == 5
                && Compras.jTableCompras.getRowCount()>0
                ) {
            controllerDetalleCompras.setOp("DELETE");
            controllerDetalleCompras.setCorrelativo(this.txtserie.getText());
            controllerDetalleCompras.setProvee(this.codProvee.getText());
            controllerDetalleCompras.mntoActualizarCompras();
            //JOptionPane.showMessageDialog(this, "Exito al anular compra.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            try {
                if (Compras.codProvee.getText().length() == 5
                        && Compras.jTableCompras.getRowCount() > 0
                        && this.Fecha.getText().length() > 0
                        && !this.txtserie.getText().isEmpty()) {
                    System.out.println("Datos validos");
                    controllerCompras.setOp("I");
                    controllerCompras.setCod(0);
                    controllerCompras.setCodProvee(Integer.parseInt(Compras.codProvee.getText()));
                    controllerCompras.setSerie(this.txtserie.getText());
                    controllerCompras.setFecha(this.Fecha.getText());
                    controllerCompras.setTotal(BigDecimal.valueOf(Double.parseDouble(lblimportetotal.getText())));
                    String xx = controllerCompras.mntoCompras();

                    int x = 0;
                    while (x < jTableCompras.getRowCount()) {
                        System.out.println("Recorro la fila " + x + " de " + jTableCompras.getRowCount());
                        controllerDetalleCompras.setOp("I");
                        controllerDetalleCompras.setCod(0);
                        controllerDetalleCompras.setCodMed(Integer.parseInt(this.jTableCompras.getValueAt(x, 0).toString()));
                        controllerDetalleCompras.setPrecio(BigDecimal.valueOf(Double.parseDouble(this.jTableCompras.getValueAt(x, 4).toString())));
                        controllerDetalleCompras.setCantidad(Integer.parseInt(this.jTableCompras.getValueAt(x, 2).toString()));
                        controllerDetalleCompras.setCodProvee(Integer.parseInt(this.codProvee.getText()));
                        controllerDetalleCompras.setSerie((this.txtserie.getText()));

                        controllerDetalleCompras.setPrecioM(BigDecimal.valueOf(Double.parseDouble(this.jTableCompras.getValueAt(x, 6).toString())));
                        controllerDetalleCompras.setPrecioN(BigDecimal.valueOf(Double.parseDouble(this.jTableCompras.getValueAt(x, 7).toString())));
                        controllerDetalleCompras.setBono(BigDecimal.valueOf(Double.parseDouble(this.jTableCompras.getValueAt(x, 8).toString())).setScale(3, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(Double.parseDouble(this.lblDisplay.getText())), 3, RoundingMode.HALF_UP).setScale(3, RoundingMode.HALF_UP));
                        controllerDetalleCompras.setCantUnid(Integer.parseInt(this.jTableCompras.getValueAt(x, 9).toString()));
                        controllerDetalleCompras.setUnidMed(this.jTableCompras.getValueAt(x, 3).toString());
                        controllerDetalleCompras.setTotal(BigDecimal.valueOf(Double.parseDouble(this.jTableCompras.getValueAt(x, 5).toString())));
                        System.out.println(controllerDetalleCompras.mntoDetallesCompras());
                        x++;
                    }
                    JOptionPane.showMessageDialog(null, "Actualización generada exitosamente.", "Compra actualizada", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("Datos invalidos");
                }
            } catch (NumberFormatException | HeadlessException ex) {
                System.err.toString();
            }
        }else{
           JOptionPane.showMessageDialog(null, "Error al intentar actualizar.", "Error al intentar actualizar", JOptionPane.ERROR_MESSAGE);  
        }

        this.jMenu2.setSelected(false);

    }//GEN-LAST:event_jMenu5MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField Direccion;
    public static javax.swing.JTextField Fecha;
    private javax.swing.JCheckBox chkBuscCompras;
    private javax.swing.JCheckBox chkBuscCompras1;
    private javax.swing.JCheckBox chkmodificarProvee;
    public static javax.swing.JTextField codProvee;
    private javax.swing.JLabel costo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JDialog jDialog;
    private javax.swing.JDialog jDialogAgregar;
    public static javax.swing.JDialog jDialogFecha;
    private javax.swing.JDialog jDialogProveedores;
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
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListCantidad;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JTable jTableCompras;
    public static javax.swing.JTable jTableMedicamentos;
    public static javax.swing.JTable jTableProveedores;
    private javax.swing.JLabel lblAplicaDesde;
    private javax.swing.JLabel lblBonificacion;
    private javax.swing.JLabel lblCod;
    private javax.swing.JLabel lblDescrip;
    private javax.swing.JLabel lblDisplay;
    private javax.swing.JLabel lblLabor;
    private javax.swing.JLabel lblMedicamento;
    private javax.swing.JLabel lblPrecioMin;
    private javax.swing.JLabel lblPrecioNormal;
    private javax.swing.JLabel lblPrecioUnidad;
    private javax.swing.JLabel lblPresent;
    private javax.swing.JLabel lblPromocion;
    private javax.swing.JLabel lblStockDeta;
    private javax.swing.JLabel lblStocks;
    private javax.swing.JLabel lblbono;
    private javax.swing.JLabel lblcosto;
    public static javax.swing.JTextField lblimportetotal;
    private javax.swing.JLabel lblimportetotal3;
    public static javax.swing.JTextField lblitems;
    private javax.swing.JLabel lblseleccion;
    private javax.swing.JTextField proveedor;
    public static javax.swing.JTextField ruc;
    public static javax.swing.JTextField txtDocumento;
    public static javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtbono;
    public static javax.swing.JTextField txtbusq;
    private javax.swing.JTextField txtcantidad;
    public static javax.swing.JTextField txtcorrelativo;
    public static javax.swing.JTextField txtfecha;
    public static javax.swing.JTextField txtmed;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtprecioMinimo;
    private javax.swing.JTextField txtprecioNormal;
    public static javax.swing.JTextField txtserie;
    // End of variables declaration//GEN-END:variables
}
