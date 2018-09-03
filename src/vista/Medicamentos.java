package vista;

import com.mxrck.autocompleter.TextAutoCompleter;
import controlador.controllerCompuestos;
import controlador.controllerDetallesMedicamentos;
import controlador.controllerLaboratorio;
import controlador.controllerMedicamentos;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.modelCompuestos;
import modelo.modelLaboratorio;

/**
 *
 * @author AMolinaDeveloper
 */
public class Medicamentos extends javax.swing.JInternalFrame {

    private int x;
    private int y;
    private DefaultTableModel dtm;
    public static String foco = "";
    public int totalFilas;
    public TextAutoCompleter Tac, TacC;

    /**
     * Creates new form NewJInternalFrame
     */
    public Medicamentos() {
        initComponents();

        TextAutoCompleter(this.Laboratorio);
        TextAutoCompleterCompuestos(this.Buscar);
        jTableDetallesComp.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableDetallesComp.getColumnModel().getColumn(0).setMinWidth(0);
        jTableDetallesComp.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTableDetallesComp.getColumnModel().getColumn(0).setWidth(0);
        jTableDetallesComp.getColumnModel().getColumn(1).setMaxWidth(0);
        jTableDetallesComp.getColumnModel().getColumn(1).setMinWidth(0);
        jTableDetallesComp.getColumnModel().getColumn(1).setPreferredWidth(0);
        jTableDetallesComp.getColumnModel().getColumn(1).setWidth(0);
    }

    public void updateMedicamentos() {
        try {
            int x = jTableDetallesComp.getRowCount();
            int y = 0;
            int[] R = new int[x];
            String concat = "";
            while (y < x) {
                R[y] = Integer.parseInt(this.jTableDetallesComp.getValueAt(y, 1).toString());
                y++;
            }
            System.out.println("la tabla tiene filas por actualizar" + x);
            int arregloOrdenado[] = burbuja(R);
            String arreglo = "";
            //imprimimos el arreglo ordenado.
            for (int i = 0; i < arregloOrdenado.length; i++) {
                arreglo = arreglo + "-" + arregloOrdenado[i];

            }

            System.out.print(arreglo + " <-esta arregladito");
            if (jTableDetallesComp.getRowCount() != 0) {

                controllerMedicamentos.setOp("U");
                controllerMedicamentos.setCod(Integer.parseInt(this.txtcodmed.getText()));
                controllerMedicamentos.setNomb(this.Medicamento.getText());
                controllerMedicamentos.setCodlabor(Integer.parseInt(this.lblcodlabor.getText()));
                controllerMedicamentos.setPresent(this.Presentacion.getText());
                controllerMedicamentos.setDescrip(this.Descripcion.getText());
                controllerMedicamentos.setFraccionable(this.Fraccionable.isSelected() ? "SI" : "NO");
                controllerMedicamentos.setUnidxcaja(Integer.parseInt(this.CantDisplay.getText()));
                controllerMedicamentos.setPreccosto(BigDecimal.valueOf(Double.parseDouble(this.PrecCosto.getText())));
                controllerMedicamentos.setPrecmin(BigDecimal.valueOf(Double.parseDouble(this.PrecMinimo.getText())));
                controllerMedicamentos.setPrecnormal(BigDecimal.valueOf(Double.parseDouble(this.PrecNormal.getText())));
                controllerMedicamentos.setPromocion(this.Promocion.isSelected() ? "SI" : "NO");
                controllerMedicamentos.setActivaprom(Integer.parseInt(this.AplicaDesde.getText()));
                controllerMedicamentos.setRegalo(Integer.parseInt(this.Adicional.getText()));
                controllerMedicamentos.setStock(Integer.parseInt(this.Stock.getText()));
                controllerMedicamentos.setStockmin(Integer.parseInt(this.Alerta.getText()));

                controllerMedicamentos.setCompsc(arreglo);
                controllerMedicamentos.setBono(BigDecimal.valueOf(Double.parseDouble(this.Bono.getText())).divide(BigDecimal.valueOf(Double.parseDouble(CantDisplay.getText()))));
                controllerMedicamentos.mntoMedicamentos();

                int codMed = Integer.parseInt(txtcodmed.getText());
                System.out.println("El codigo del producto es : " + codMed);

                int xx = jTableDetallesComp.getRowCount();
                int yy = 0;

                while (yy < xx) {
                    //Si tenemos las misma cantidad de filas.
                    //entonces no hacemos nada ANORMAL
                    if (xx == totalFilas) {
                        System.out.println("Nada anormal");
                        controllerDetallesMedicamentos.setOp("U");
                        controllerDetallesMedicamentos.setCod(Integer.parseInt(this.txtcodmed.getText()));
                        controllerDetallesMedicamentos.setCodmed(codMed);
                        controllerDetallesMedicamentos.setCodcomp(Integer.parseInt(this.jTableDetallesComp.getValueAt(yy, 1).toString()));
                        controllerDetallesMedicamentos.mntoDetallesMedic();
                        System.out.println("voy x : " + yy + " y mi codigo es : " + codMed);
                        yy++;
                    } else {
                        System.out.println("Entramos al else de algo anormal");
                        if (xx > totalFilas || xx < totalFilas) {
                            System.out.println("Entramos al if de algo anormal");
                            controllerDetallesMedicamentos.setOp("D");
                            controllerDetallesMedicamentos.setCod(0);
                            controllerDetallesMedicamentos.setCodmed(codMed);
                            controllerDetallesMedicamentos.setCodcomp(Integer.parseInt(this.jTableDetallesComp.getValueAt(yy, 1).toString()));
                            controllerDetallesMedicamentos.mntoDetallesMedic();
                            System.out.println("Tenemos " + (xx - totalFilas) + " x actualizar" + " vamos en " + yy);
                            yy++;

                        }
                        if (xx == yy) {
                            yy = 0;

                            while (yy < xx) {
                                System.out.print("Inserto...");
                                controllerDetallesMedicamentos.setOp("I");
                                controllerDetallesMedicamentos.setCod(0);
                                controllerDetallesMedicamentos.setCodmed(codMed);
                                controllerDetallesMedicamentos.setCodcomp(Integer.parseInt(this.jTableDetallesComp.getValueAt(yy, 1).toString()));
                                this.txtmsgdetalles.setText(controllerDetallesMedicamentos.mntoDetallesMedic());
                                System.out.println("voy x : " + yy + " y mi codigo es : " + codMed);
                                yy++;
                            }

                        }

                    }

                }
//CARGO LA TABLA 
                controllerMedicamentos.setOp("D");
                controllerMedicamentos.setBusc(this.Medicamento.getText());
                controllerMedicamentos.consultarMedicamentos("MEDIC", dtm);
                JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.", "Producto actualizado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Debe agregarse compuestos del \n producto.", "Agregar componentes", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar descripción\n" + ex, "Error al actualizar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public final void TextAutoCompleter(JTextField busc) {
        Tac = new TextAutoCompleter(busc);
        //controllerLaboratorio cl = new controllerLaboratorio();
        modelLaboratorio.setOp("D");
        modelLaboratorio.setBusc("");
        ResultSet rs = modelLaboratorio.queryLaboratorio();
        try {
            Tac.removeAllItems();

            while (rs.next()) {
                Object[] obj = new Object[1];
                obj[0] = rs.getString("LABORATORIO");
                Tac.addItem(obj[0]);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public final void TextAutoCompleterCompuestos(JTextField busc) {
        TacC = new TextAutoCompleter(busc);
        //controllerLaboratorio cl = new controllerLaboratorio();
        modelCompuestos.setOp("D");
        modelCompuestos.setBusc("");
        ResultSet rs = modelCompuestos.queryCompuestos();
        try {
            TacC.removeAllItems();
            while (rs.next()) {
                Object[] obj = new Object[1];
                obj[0] = rs.getString("COMPONENTE");
                TacC.addItem(obj[0]);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void insertLabor() {

        controllerLaboratorio.setOp("I");
        controllerLaboratorio.setNomb(this.Laboratorio.getText());
        controllerLaboratorio.setDescrip("<<SIN DATOS>>");
        txtmsg.setText(controllerLaboratorio.mntoLaboratorio());
        Tac.addItem(Laboratorio.getText().toUpperCase());

        controllerLaboratorio.setOp("S");
        controllerLaboratorio.setBusc(this.Laboratorio.getText());
        this.lblcodlabor.setText(controllerLaboratorio.consultarLaboratorio());
        this.Presentacion.requestFocus();
    }

    public void insertComponente() {

        controllerCompuestos.setOp("I");
        controllerCompuestos.setNomb(this.Buscar.getText());
        controllerCompuestos.setDescrip("<<SIN DATOS>>");
        txtmsg.setText(controllerCompuestos.mntoCompuestos());
        TacC.addItem(this.Buscar.getText().toUpperCase());

        try {

            modelCompuestos.setOp("S");
            modelCompuestos.setBusc(this.Buscar.getText());
            ResultSet rs = modelCompuestos.queryCompuestos();

            if (rs.next()) {
                int r = this.jTableMedicamentosOp.getRowCount();
                System.out.println("next : " + rs.getString("###") + " Row : " + r);
                dtm = (DefaultTableModel) this.jTableDetallesComp.getModel();

                Object[] obj = new Object[3];
                obj[0] = rs.getString("###");
                obj[1] = rs.getString("###");
                obj[2] = rs.getString("COMPONENTE");
                dtm.addRow(obj);
                this.Buscar.setText(null);
                this.Buscar.requestFocus();

            }

        } catch (SQLException ex) {
            Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int[] burbuja(int[] arreglo) {
        int auxiliar;
        int[] arregloOrdenado;
        for (int i = 1; i < arreglo.length; i++) {
            for (int j = 0; j < arreglo.length - i; j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    auxiliar = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = auxiliar;
                }
            }
        }
        arregloOrdenado = arreglo;
        return arregloOrdenado;
    }

    public void clear() {
        this.txtcodmed.setText(null);
        this.Medicamento.setText(null);
        this.lblcodlabor.setText("<<SIN DATOS>>");
        this.Laboratorio.setText(null);
        this.Presentacion.setText(null);
        this.Descripcion.setText(null);
        this.Bono.setText("0.00");
        this.Alerta.setText(null);
        this.Stock.setText(null);
        this.StockDetallado.setText(null);
        this.StockDetallado.setEnabled(false);
        this.Buscar.setText(null);
        this.Fraccionable.setSelected(false);
        this.Promocion.setSelected(false);
        this.CantDisplay.setText("1");
        this.CantDisplay.setEnabled(false);
        this.AplicaDesde.setText("0");
        this.AplicaDesde.setEnabled(false);
        this.Adicional.setText("0");
        this.Adicional.setEnabled(false);
        this.PrecCosto.setText(null);
        this.PrecMinimo.setText(null);
        this.PrecNormal.setText(null);
        this.PrecUnidad.setText(null);
        this.Medicamento.requestFocus();
        validator.vaciar_jtable(jTableDetallesComp);
        validator.vaciar_jtable(jTableMedicamentosOp);
    }

    public void insertMedicamentos() {
        try {
            if (this.Medicamento.getText().length() > 0
                    && this.Laboratorio.getText().length() > 0
                    && this.Presentacion.getText().length() > 0
                    && this.Descripcion.getText().length() > 0
                    && this.CantDisplay.getText().length() > 0
                    && this.Alerta.getText().length() > 0
                    && this.Stock.getText().length() > 0
                    && this.PrecCosto.getText().length() > 0
                    && this.PrecMinimo.getText().length() > 0
                    && this.PrecNormal.getText().length() > 0
                    && this.lblcodlabor.getText().length() == 5
                    && this.AplicaDesde.getText().length() > 0
                    && this.Adicional.getText().length() > 0
                    && jTableDetallesComp.getRowCount() > 0) {
                int x = jTableDetallesComp.getRowCount();
                int y = 0;
                int[] R = new int[x];
                String concat = "";
                while (y < x) {
                    R[y] = Integer.parseInt(this.jTableDetallesComp.getValueAt(y, 0).toString());
                    y++;
                }
                System.out.println("la tabla tiene filas " + x);
                int arregloOrdenado[] = burbuja(R);
                String arreglo = "";
                //imprimimos el arreglo ordenado.
                for (int i = 0; i < arregloOrdenado.length; i++) {
                    arreglo = arreglo + "-" + arregloOrdenado[i];

                }

                System.out.print(arreglo + " y arregladito");

                controllerMedicamentos.setOp("I");
                controllerMedicamentos.setCod(0);
                controllerMedicamentos.setNomb(this.Medicamento.getText());
                controllerMedicamentos.setCodlabor(Integer.parseInt(this.lblcodlabor.getText()));
                controllerMedicamentos.setPresent(this.Presentacion.getText());
                controllerMedicamentos.setDescrip(this.Descripcion.getText());
                controllerMedicamentos.setFraccionable(this.Fraccionable.isSelected() ? "SI" : "NO");
                controllerMedicamentos.setUnidxcaja(Integer.parseInt(this.CantDisplay.getText()));
                controllerMedicamentos.setPreccosto(BigDecimal.valueOf(Double.parseDouble(this.PrecCosto.getText())).setScale(2, RoundingMode.HALF_UP));
                controllerMedicamentos.setPrecmin(BigDecimal.valueOf(Double.parseDouble(this.PrecMinimo.getText())).setScale(2, RoundingMode.HALF_UP));
                controllerMedicamentos.setPrecnormal(BigDecimal.valueOf(Double.parseDouble(this.PrecNormal.getText())).setScale(2, RoundingMode.HALF_UP));
                controllerMedicamentos.setPromocion(this.Promocion.isSelected() ? "SI" : "NO");
                controllerMedicamentos.setActivaprom(Integer.parseInt(this.AplicaDesde.getText()));
                controllerMedicamentos.setRegalo(Integer.parseInt(this.Adicional.getText()));
                controllerMedicamentos.setStock(Integer.parseInt(this.Stock.getText()));
                controllerMedicamentos.setStockmin(Integer.parseInt(this.Alerta.getText()));

                controllerMedicamentos.setCompsc(arreglo);
                controllerMedicamentos.setBono(BigDecimal.valueOf(Double.parseDouble(this.Bono.getText())).divide(BigDecimal.valueOf(Double.parseDouble(this.CantDisplay.getText())), 2, RoundingMode.HALF_UP));
                txtmsg.setText(controllerMedicamentos.mntoMedicamentos());
// FIN DE INSERCION DEL MEDICAMENTOS
                //INICIO DE INSERCION DETALLES
                controllerMedicamentos.setOp("D");
                controllerMedicamentos.setBusc(this.Medicamento.getText());
                int codMed = Integer.parseInt(controllerMedicamentos.consultarCodigoMedicamento());

                int xx = jTableDetallesComp.getRowCount();
                int yy = 0;

                while (yy < xx) {
                    controllerDetallesMedicamentos.setOp("I");
                    controllerDetallesMedicamentos.setCod(0);
                    controllerDetallesMedicamentos.setCodmed(codMed);
                    controllerDetallesMedicamentos.setCodcomp(Integer.parseInt(this.jTableDetallesComp.getValueAt(yy, 1).toString()));
                    this.txtmsgdetalles.setText(controllerDetallesMedicamentos.mntoDetallesMedic());
                    System.out.println("voy x : " + yy + " y mi codigo es : " + codMed);
                    yy++;
                }
//CARGO LA TABLA 
                controllerMedicamentos.setOp("D");
                controllerMedicamentos.setBusc("");
                controllerMedicamentos.consultarMedicamentos("MEDIC", dtm);
Preferences prefs;
        prefs = Preferences.userRoot().node("configuracion");
        
         if (prefs.get("sistema", "farma").equals("comerc")) {
             JOptionPane.showMessageDialog(this, "Nuevo producto ingresado \n" + this.Medicamento.getText() + "\n" + this.Presentacion.getText(), "Nuevo producto", JOptionPane.INFORMATION_MESSAGE);
         }else{
                JOptionPane.showMessageDialog(this, "Nuevo producto ingresado \n" + this.Medicamento.getText() + "\n" + this.Presentacion.getText(), "Nuevo producto", JOptionPane.INFORMATION_MESSAGE);
         }
         
            } else {
                JOptionPane.showMessageDialog(this, "Todas las casillas deben de tener datos, \n tambien la tabla de componentes.", "Datos incompletos", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException | HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Error al generar la inserción. \n" + ex, "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Medicamento = new javax.swing.JTextField();
        txtcodmed = new javax.swing.JTextField();
        Laboratorio = new javax.swing.JTextField();
        Presentacion = new javax.swing.JTextField();
        CantDisplay = new javax.swing.JTextField();
        Descripcion = new javax.swing.JTextField();
        Fraccionable = new javax.swing.JCheckBox();
        Promocion = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDetallesComp = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableMedicamentosOp = new javax.swing.JTable();
        Buscar = new javax.swing.JTextField();
        AplicaDesde = new javax.swing.JTextField();
        Adicional = new javax.swing.JTextField();
        lblcodlabor = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        Alerta = new javax.swing.JTextField();
        Stock = new javax.swing.JTextField();
        StockDetallado = new javax.swing.JTextField();
        PrecNormal = new javax.swing.JTextField();
        PrecUnidad = new javax.swing.JTextField();
        PrecCosto = new javax.swing.JTextField();
        PrecMinimo = new javax.swing.JTextField();
        Bono = new javax.swing.JTextField();
        BonoXUnid = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtbusqueda = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableMedicamentos = new javax.swing.JTable();
        txtmsg = new javax.swing.JLabel();
        txtmsgdetalles = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registros de Productos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/connections.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1009, 564));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
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

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar medicamentos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        Medicamento.setBackground(new java.awt.Color(244, 245, 242));
        Medicamento.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Medicamento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Medicamento.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Medicamento", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Medicamento.setCaretColor(new java.awt.Color(51, 204, 255));
        Medicamento.setNextFocusableComponent(Laboratorio);
        Medicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MedicamentoActionPerformed(evt);
            }
        });

        txtcodmed.setEditable(false);
        txtcodmed.setBackground(new java.awt.Color(255, 255, 153));
        txtcodmed.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        txtcodmed.setFocusable(false);
        txtcodmed.setMaximumSize(new java.awt.Dimension(100, 28));
        txtcodmed.setMinimumSize(new java.awt.Dimension(100, 28));
        txtcodmed.setPreferredSize(new java.awt.Dimension(100, 28));

        Laboratorio.setBackground(new java.awt.Color(255, 204, 204));
        Laboratorio.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Laboratorio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Laboratorio.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Laborat. o Marca -[F5] para agregar", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Laboratorio.setCaretColor(new java.awt.Color(51, 204, 255));
        Laboratorio.setNextFocusableComponent(Presentacion);
        Laboratorio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                LaboratorioFocusGained(evt);
            }
        });
        Laboratorio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LaboratorioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                LaboratorioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                LaboratorioKeyTyped(evt);
            }
        });

        Presentacion.setBackground(new java.awt.Color(244, 245, 242));
        Presentacion.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Presentacion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Presentacion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Presentacion", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Presentacion.setCaretColor(new java.awt.Color(51, 204, 255));
        Presentacion.setNextFocusableComponent(Descripcion);

        CantDisplay.setBackground(new java.awt.Color(255, 255, 204));
        CantDisplay.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        CantDisplay.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CantDisplay.setText("1");
        CantDisplay.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Cant. x Caja", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        CantDisplay.setCaretColor(new java.awt.Color(51, 204, 255));
        CantDisplay.setEnabled(false);
        CantDisplay.setNextFocusableComponent(Promocion);
        CantDisplay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantDisplayKeyTyped(evt);
            }
        });

        Descripcion.setBackground(new java.awt.Color(244, 245, 242));
        Descripcion.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Descripcion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Descripcion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Descripción", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Descripcion.setCaretColor(new java.awt.Color(51, 204, 255));
        Descripcion.setNextFocusableComponent(Fraccionable);

        Fraccionable.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        Fraccionable.setText("Fraccionable");
        Fraccionable.setNextFocusableComponent(Promocion);
        Fraccionable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FraccionableActionPerformed(evt);
            }
        });

        Promocion.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        Promocion.setText("Promoción");
        Promocion.setNextFocusableComponent(Buscar);
        Promocion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PromocionActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregar Componentes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        jTableDetallesComp.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
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
        });
        jScrollPane1.setViewportView(jTableDetallesComp);
        jTableDetallesComp.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        jTableDetallesComp.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        jTableDetallesComp.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        if (jTableDetallesComp.getColumnModel().getColumnCount() > 0) {
            jTableDetallesComp.getColumnModel().getColumn(0).setResizable(false);
            jTableDetallesComp.getColumnModel().getColumn(1).setResizable(false);
            jTableDetallesComp.getColumnModel().getColumn(2).setResizable(false);
        }

        jTableMedicamentosOp.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTableMedicamentosOp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicamentos"
            }
        ));
        jTableMedicamentosOp.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableMedicamentosOp.getTableHeader().setReorderingAllowed(false);
        jTableMedicamentosOp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableMedicamentosOpMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(jTableMedicamentosOp);
        Medicamentos.jTableMedicamentosOp.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));

        Buscar.setBackground(new java.awt.Color(255, 204, 204));
        Buscar.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Buscar.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Buscar.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Buscar | Agregar con <<F5>>", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Buscar.setCaretColor(new java.awt.Color(51, 204, 255));
        Buscar.setNextFocusableComponent(Alerta);
        Buscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BuscarFocusGained(evt);
            }
        });
        Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Buscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        AplicaDesde.setBackground(new java.awt.Color(255, 255, 204));
        AplicaDesde.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        AplicaDesde.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        AplicaDesde.setText("0");
        AplicaDesde.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "AplicaDesde", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        AplicaDesde.setCaretColor(new java.awt.Color(51, 204, 255));
        AplicaDesde.setEnabled(false);
        AplicaDesde.setNextFocusableComponent(Adicional);
        AplicaDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AplicaDesdeActionPerformed(evt);
            }
        });
        AplicaDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AplicaDesdeKeyTyped(evt);
            }
        });

        Adicional.setBackground(new java.awt.Color(255, 255, 204));
        Adicional.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Adicional.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Adicional.setText("0");
        Adicional.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Adicional", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Adicional.setCaretColor(new java.awt.Color(51, 204, 255));
        Adicional.setEnabled(false);
        Adicional.setNextFocusableComponent(Buscar);
        Adicional.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AdicionalKeyTyped(evt);
            }
        });

        lblcodlabor.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        lblcodlabor.setForeground(new java.awt.Color(102, 0, 0));
        lblcodlabor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcodlabor.setText("00000");
        lblcodlabor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Precios & Cantidades", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        Alerta.setBackground(new java.awt.Color(244, 245, 242));
        Alerta.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Alerta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Alerta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Alerta", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Alerta.setCaretColor(new java.awt.Color(51, 204, 255));
        Alerta.setNextFocusableComponent(Stock);
        Alerta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AlertaKeyTyped(evt);
            }
        });

        Stock.setBackground(new java.awt.Color(244, 245, 242));
        Stock.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Stock.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Stock.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Stock", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Stock.setCaretColor(new java.awt.Color(51, 204, 255));
        Stock.setNextFocusableComponent(PrecCosto);
        Stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                StockKeyTyped(evt);
            }
        });

        StockDetallado.setBackground(new java.awt.Color(255, 255, 204));
        StockDetallado.setFont(new java.awt.Font("Calibri Light", 2, 11)); // NOI18N
        StockDetallado.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        StockDetallado.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "StockDetallado", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        StockDetallado.setCaretColor(new java.awt.Color(51, 204, 255));
        StockDetallado.setEnabled(false);

        PrecNormal.setBackground(new java.awt.Color(244, 245, 242));
        PrecNormal.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        PrecNormal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        PrecNormal.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "P Normal[CAJA]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        PrecNormal.setCaretColor(new java.awt.Color(51, 204, 255));
        PrecNormal.setNextFocusableComponent(Bono);
        PrecNormal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PrecNormalKeyTyped(evt);
            }
        });

        PrecUnidad.setBackground(new java.awt.Color(255, 255, 204));
        PrecUnidad.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        PrecUnidad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        PrecUnidad.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "P Unidad", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        PrecUnidad.setCaretColor(new java.awt.Color(51, 204, 255));
        PrecUnidad.setEnabled(false);

        PrecCosto.setBackground(new java.awt.Color(244, 245, 242));
        PrecCosto.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        PrecCosto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        PrecCosto.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "P Costo[CAJA]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        PrecCosto.setCaretColor(new java.awt.Color(51, 204, 255));
        PrecCosto.setNextFocusableComponent(PrecMinimo);
        PrecCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PrecCostoKeyTyped(evt);
            }
        });

        PrecMinimo.setBackground(new java.awt.Color(244, 245, 242));
        PrecMinimo.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        PrecMinimo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        PrecMinimo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "P Min[CAJA]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        PrecMinimo.setCaretColor(new java.awt.Color(51, 204, 255));
        PrecMinimo.setNextFocusableComponent(PrecNormal);
        PrecMinimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecMinimoActionPerformed(evt);
            }
        });
        PrecMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PrecMinimoKeyTyped(evt);
            }
        });

        Bono.setBackground(new java.awt.Color(244, 245, 242));
        Bono.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Bono.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Bono.setText("0.00");
        Bono.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Bono x Venta[CAJA]", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Bono.setCaretColor(new java.awt.Color(51, 204, 255));
        Bono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BonoKeyTyped(evt);
            }
        });

        BonoXUnid.setBackground(new java.awt.Color(255, 255, 204));
        BonoXUnid.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        BonoXUnid.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        BonoXUnid.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Bono x Unidad", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        BonoXUnid.setCaretColor(new java.awt.Color(51, 204, 255));
        BonoXUnid.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(Alerta, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StockDetallado, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrecCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrecMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrecNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Bono, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BonoXUnid, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrecUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Bono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(PrecUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(BonoXUnid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(PrecNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(PrecMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(PrecCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(Alerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(StockDetallado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtcodmed, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Medicamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblcodlabor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Laboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Promocion)
                                .addGap(14, 14, 14)
                                .addComponent(AplicaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Adicional, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(Fraccionable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CantDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblcodlabor)
                                    .addComponent(Laboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtcodmed, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Fraccionable)
                            .addComponent(CantDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AplicaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Adicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Promocion))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros de medicamentos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        txtbusqueda.setBackground(new java.awt.Color(255, 204, 204));
        txtbusqueda.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtbusqueda.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtbusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("Button.background"), 1, true), "Realizar búsqueda de medicamento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtbusqueda.setCaretColor(new java.awt.Color(51, 204, 255));
        txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyTyped(evt);
            }
        });

        jTableMedicamentos.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTableMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Compuesto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMedicamentos.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableMedicamentos.getTableHeader().setReorderingAllowed(false);
        jTableMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMedicamentosMouseClicked(evt);
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
        if (jTableMedicamentos.getColumnModel().getColumnCount() > 0) {
            jTableMedicamentos.getColumnModel().getColumn(0).setResizable(false);
        }

        txtmsg.setFont(new java.awt.Font("Calibri Light", 1, 10)); // NOI18N
        txtmsg.setForeground(new java.awt.Color(102, 0, 0));
        txtmsg.setText("DATOS CARGADOS.");
        txtmsg.setMaximumSize(new java.awt.Dimension(254, 16));
        txtmsg.setMinimumSize(new java.awt.Dimension(254, 16));
        txtmsg.setPreferredSize(new java.awt.Dimension(254, 16));
        txtmsg.setRequestFocusEnabled(false);

        txtmsgdetalles.setFont(new java.awt.Font("Calibri Light", 1, 10)); // NOI18N
        txtmsgdetalles.setForeground(new java.awt.Color(102, 0, 0));
        txtmsgdetalles.setText("DATOS CARGADOS.");
        txtmsgdetalles.setMaximumSize(new java.awt.Dimension(254, 16));
        txtmsgdetalles.setMinimumSize(new java.awt.Dimension(254, 16));
        txtmsgdetalles.setPreferredSize(new java.awt.Dimension(254, 16));
        txtmsgdetalles.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(txtmsg, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtmsgdetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(272, 272, 272))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmsg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmsgdetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar1MouseDragged(evt);
            }
        });
        jMenuBar1.addMouseListener(new java.awt.event.MouseAdapter() {
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

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Sync.png"))); // NOI18N
        jMenu3.setText("ACTUALIZAR[F6]");
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
        jMenu4.setText("CERRAR LA VENTANA");
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMedicamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMedicamentosMouseClicked

        int sel = this.jTableMedicamentos.getSelectedRow();
        this.txtcodmed.setText(this.jTableMedicamentos.getValueAt(sel, 0).toString());
        this.Medicamento.setText(this.jTableMedicamentos.getValueAt(sel, 1).toString());
        this.Laboratorio.setText(this.jTableMedicamentos.getValueAt(sel, 2).toString());
        this.Presentacion.setText(this.jTableMedicamentos.getValueAt(sel, 3).toString());
        this.Descripcion.setText(this.jTableMedicamentos.getValueAt(sel, 4).toString());
        this.Fraccionable.setSelected(this.jTableMedicamentos.getValueAt(sel, 5).toString().equals("SI") ? true : false);
        this.CantDisplay.setText(this.jTableMedicamentos.getValueAt(sel, 6).toString());
        this.PrecCosto.setText(this.jTableMedicamentos.getValueAt(sel, 7).toString());
        this.PrecMinimo.setText(this.jTableMedicamentos.getValueAt(sel, 8).toString());
        this.PrecNormal.setText(this.jTableMedicamentos.getValueAt(sel, 9).toString());
        this.PrecUnidad.setText(this.jTableMedicamentos.getValueAt(sel, 10).toString());
        this.Promocion.setSelected(this.jTableMedicamentos.getValueAt(sel, 11).toString().equals("SI") ? true : false);
        this.AplicaDesde.setText(this.jTableMedicamentos.getValueAt(sel, 12).toString());
        this.Adicional.setText(this.jTableMedicamentos.getValueAt(sel, 13).toString());
        this.Stock.setText(this.jTableMedicamentos.getValueAt(sel, 14).toString());
        this.StockDetallado.setText(this.jTableMedicamentos.getValueAt(sel, 15).toString());
        this.Alerta.setText(this.jTableMedicamentos.getValueAt(sel, 16).toString());
        this.BonoXUnid.setText(this.jTableMedicamentos.getValueAt(sel, 18).toString());
        this.Bono.setText(BigDecimal.valueOf(Double.parseDouble(this.jTableMedicamentos.getValueAt(sel, 18).toString())).multiply(BigDecimal.valueOf(Double.parseDouble(this.jTableMedicamentos.getValueAt(sel, 6).toString()))).setScale(2, RoundingMode.HALF_UP).toString());
        controllerLaboratorio.setOp("S");
        controllerLaboratorio.setBusc(this.Laboratorio.getText());
        this.lblcodlabor.setText(controllerLaboratorio.consultarLaboratorio());

        controllerMedicamentos.setOp("Q");
        controllerMedicamentos.setBusc(this.txtcodmed.getText());
        controllerMedicamentos.consultarMedicamentosDetalles("MEDICAMENTOS", dtm);

        controllerMedicamentos.setOp("R");
        controllerMedicamentos.setBusc(this.jTableMedicamentos.getValueAt(this.jTableMedicamentos.getSelectedRow(), 17).toString());
        controllerMedicamentos.consultarMedicamentosOpcionales(dtm);

        totalFilas = this.jTableDetallesComp.getRowCount();
        System.out.println("Inicialmente tenemos " + totalFilas + " fila(s). ");
        this.jTableMedicamentos.requestFocus();
    }//GEN-LAST:event_jTableMedicamentosMouseClicked

    private void PrecMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecMinimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrecMinimoActionPerformed

    private void LaboratorioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaboratorioKeyTyped
    }//GEN-LAST:event_LaboratorioKeyTyped

    private void LaboratorioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaboratorioKeyPressed

    }//GEN-LAST:event_LaboratorioKeyPressed

    private void LaboratorioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LaboratorioKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_F5) {
            insertLabor();

        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            controllerLaboratorio.setOp("S");
            controllerLaboratorio.setBusc(this.Laboratorio.getText());
            this.lblcodlabor.setText(controllerLaboratorio.consultarLaboratorio());
            this.Presentacion.requestFocus();
        }
    }//GEN-LAST:event_LaboratorioKeyReleased

    private void BuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BuscarKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_F5) {
            if (this.Buscar.getText().length() > 0) {
                insertComponente();
            } else {
                JOptionPane.showMessageDialog(null, "Tamaño mínimo de componente es de 1 caracter.", "Error al ingresar componente", JOptionPane.ERROR_MESSAGE);
            }

        }
        try {

            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                modelCompuestos.setOp("S");
                modelCompuestos.setBusc(this.Buscar.getText());
                ResultSet rs = modelCompuestos.queryCompuestos();

                if (rs.next()) {
                    int r = this.jTableMedicamentosOp.getRowCount();
                    System.out.println("next : " + rs.getString("###") + " Row : " + r);
                    dtm = (DefaultTableModel) this.jTableDetallesComp.getModel();

                    Object[] obj = new Object[3];
                    obj[0] = rs.getString("###");
                    obj[1] = rs.getString("###");
                    obj[2] = rs.getString("COMPONENTE");
                    dtm.addRow(obj);
                    this.Buscar.setText(null);
                    this.Buscar.requestFocus();

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Medicamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BuscarKeyReleased

    private void FraccionableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FraccionableActionPerformed
        this.CantDisplay.setEnabled(this.Fraccionable.isSelected());

        this.CantDisplay.setText(this.Fraccionable.isSelected() ? "" : "1");
        this.CantDisplay.requestFocus();
    }//GEN-LAST:event_FraccionableActionPerformed

    private void PromocionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PromocionActionPerformed
        this.AplicaDesde.setEnabled(this.Promocion.isSelected());
        this.AplicaDesde.setText(null);
        this.Adicional.setText(null);
        this.AplicaDesde.requestFocus();
        this.Adicional.setEnabled(this.Promocion.isSelected());
    }//GEN-LAST:event_PromocionActionPerformed

    private void LaboratorioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LaboratorioFocusGained
    }//GEN-LAST:event_LaboratorioFocusGained

    private void BuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BuscarFocusGained
    }//GEN-LAST:event_BuscarFocusGained

    private void txtbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            controllerMedicamentos.setOp("D");
            controllerMedicamentos.setBusc(this.txtbusqueda.getText());
            controllerMedicamentos.consultarMedicamentos("MEDIC", dtm);
        }
    }//GEN-LAST:event_txtbusquedaKeyReleased

    private void jTableDetallesCompKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableDetallesCompKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
            dtm = (DefaultTableModel) this.jTableDetallesComp.getModel();
            System.out.println("Selecciono : " + this.jTableDetallesComp.getSelectedRow());
            dtm.removeRow(this.jTableDetallesComp.getSelectedRow());
        }
    }//GEN-LAST:event_jTableDetallesCompKeyPressed

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

    private void AplicaDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AplicaDesdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AplicaDesdeActionPerformed

    private void jTableMedicamentosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMedicamentosKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_F4) {
            clear();
        }
    }//GEN-LAST:event_jTableMedicamentosKeyPressed

    private void BuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BuscarKeyPressed

    }//GEN-LAST:event_BuscarKeyPressed

    private void MedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MedicamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MedicamentoActionPerformed

    private void AlertaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlertaKeyTyped
        validator.solonumeros(evt, Alerta, 2);
    }//GEN-LAST:event_AlertaKeyTyped

    private void StockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StockKeyTyped
        validator.solonumeros(evt, Stock, 5);
    }//GEN-LAST:event_StockKeyTyped

    private void PrecCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrecCostoKeyTyped
        validator.soloDecimales(evt, PrecCosto);
    }//GEN-LAST:event_PrecCostoKeyTyped

    private void PrecMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrecMinimoKeyTyped
        validator.soloDecimales(evt, PrecMinimo);
    }//GEN-LAST:event_PrecMinimoKeyTyped

    private void PrecNormalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PrecNormalKeyTyped
        validator.soloDecimales(evt, PrecNormal);
    }//GEN-LAST:event_PrecNormalKeyTyped

    private void CantDisplayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantDisplayKeyTyped
        validator.solonumeros(evt, CantDisplay, 3);
    }//GEN-LAST:event_CantDisplayKeyTyped

    private void AplicaDesdeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AplicaDesdeKeyTyped
        validator.solonumeros(evt, AplicaDesde, 3);
    }//GEN-LAST:event_AplicaDesdeKeyTyped

    private void AdicionalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AdicionalKeyTyped
        validator.solonumeros(evt, Adicional, 3);
    }//GEN-LAST:event_AdicionalKeyTyped

    private void txtbusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyTyped

    }//GEN-LAST:event_txtbusquedaKeyTyped

    private void jTableMedicamentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableMedicamentosKeyReleased
        if (evt.getKeyCode() == 38 || evt.getKeyCode() == 40) {
            int sel = this.jTableMedicamentos.getSelectedRow();
            this.txtcodmed.setText(this.jTableMedicamentos.getValueAt(sel, 0).toString());
            this.Medicamento.setText(this.jTableMedicamentos.getValueAt(sel, 1).toString());
            this.Laboratorio.setText(this.jTableMedicamentos.getValueAt(sel, 2).toString());
            this.Presentacion.setText(this.jTableMedicamentos.getValueAt(sel, 3).toString());
            this.Descripcion.setText(this.jTableMedicamentos.getValueAt(sel, 4).toString());
            this.Fraccionable.setSelected(this.jTableMedicamentos.getValueAt(sel, 5).toString().equals("SI") ? true : false);
            this.CantDisplay.setText(this.jTableMedicamentos.getValueAt(sel, 6).toString());
            this.PrecCosto.setText(this.jTableMedicamentos.getValueAt(sel, 7).toString());
            this.PrecMinimo.setText(this.jTableMedicamentos.getValueAt(sel, 8).toString());
            this.PrecNormal.setText(this.jTableMedicamentos.getValueAt(sel, 9).toString());
            this.PrecUnidad.setText(this.jTableMedicamentos.getValueAt(sel, 10).toString());
            this.Promocion.setSelected(this.jTableMedicamentos.getValueAt(sel, 11).toString().equals("SI") ? true : false);
            this.AplicaDesde.setText(this.jTableMedicamentos.getValueAt(sel, 12).toString());
            this.Adicional.setText(this.jTableMedicamentos.getValueAt(sel, 13).toString());
            this.Stock.setText(this.jTableMedicamentos.getValueAt(sel, 14).toString());
            this.StockDetallado.setText(this.jTableMedicamentos.getValueAt(sel, 15).toString());
            this.Alerta.setText(this.jTableMedicamentos.getValueAt(sel, 16).toString());
            this.BonoXUnid.setText(this.jTableMedicamentos.getValueAt(sel, 18).toString());
        }
    }//GEN-LAST:event_jTableMedicamentosKeyReleased

    private void BonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BonoKeyTyped
        validator.soloDecimales(evt, Bono);
    }//GEN-LAST:event_BonoKeyTyped

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        clear();
        this.jMenu1.setSelected(false);
    }//GEN-LAST:event_jMenu1MousePressed

    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        insertMedicamentos();
        controllerMedicamentos.setOp("D");
        controllerMedicamentos.setBusc(this.txtbusqueda.getText());
        controllerMedicamentos.consultarMedicamentos("MEDIC", dtm);
        clear();
        this.jMenu2.setSelected(false);

    }//GEN-LAST:event_jMenu2MousePressed

    private void jMenu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MousePressed
        if (this.txtcodmed.getText().length() == 0) {
            
            JOptionPane.showMessageDialog(null, "Por favor seleccione un producto de la tabla.", "Seleccione producto", JOptionPane.ERROR_MESSAGE);
        } else {
            updateMedicamentos();
        }
        this.jMenu3.setSelected(false);
    }//GEN-LAST:event_jMenu3MousePressed

    private void jMenu4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MousePressed
        this.dispose();
    }//GEN-LAST:event_jMenu4MousePressed

    private void jMenuBar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MousePressed
        this.toFront();
        x = evt.getX();

        y = evt.getY();
    }//GEN-LAST:event_jMenuBar1MousePressed

    private void jMenuBar1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MouseDragged
        Point ubicacion = MouseInfo.getPointerInfo().getLocation();//1

        System.out.println("Coordenadas: (" + ubicacion.x + "," + ubicacion.y + ")");//2
        setLocation((ubicacion.x - 0), (ubicacion.y) - 230);//3
        //239 268
    }//GEN-LAST:event_jMenuBar1MouseDragged

    private void jTableMedicamentosOpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMedicamentosOpMousePressed
        controllerMedicamentos.setOp("D");
        controllerMedicamentos.setBusc(Medicamentos.jTableMedicamentosOp.getValueAt(Medicamentos.jTableMedicamentosOp.getSelectedRow(), 1).toString());
        controllerMedicamentos.consultarMedicamentos("MEDIC", dtm);
    }//GEN-LAST:event_jTableMedicamentosOpMousePressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        Preferences prefs;
        prefs = Preferences.userRoot().node("configuracion");
        if (prefs.get("sistema", "farma").equals("comerc")) {
            Medicamento.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Productos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
            this.setTitle("Registros de Productos");
            jTableMedicamentosOp.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{}, new String[]{"Productos"}));
            Medicamentos.jTableMedicamentosOp.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
txtbusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("Button.background"), 1, true), "Realizar búsqueda de producto", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

//        this.jPanel4.setEnabled(false);
//        this.Buscar.setEnabled(false);
//        this.jTableDetallesComp.setEnabled(false);
//        this.jTableMedicamentosOp.setEnabled(false);
        }
    }//GEN-LAST:event_formInternalFrameOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Adicional;
    private javax.swing.JTextField Alerta;
    private javax.swing.JTextField AplicaDesde;
    private javax.swing.JTextField Bono;
    private javax.swing.JTextField BonoXUnid;
    private javax.swing.JTextField Buscar;
    private javax.swing.JTextField CantDisplay;
    private javax.swing.JTextField Descripcion;
    private javax.swing.JCheckBox Fraccionable;
    private javax.swing.JTextField Laboratorio;
    public static javax.swing.JTextField Medicamento;
    private javax.swing.JTextField PrecCosto;
    private javax.swing.JTextField PrecMinimo;
    private javax.swing.JTextField PrecNormal;
    private javax.swing.JTextField PrecUnidad;
    private javax.swing.JTextField Presentacion;
    private javax.swing.JCheckBox Promocion;
    private javax.swing.JTextField Stock;
    private javax.swing.JTextField StockDetallado;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable jTableDetallesComp;
    public static javax.swing.JTable jTableMedicamentos;
    public static javax.swing.JTable jTableMedicamentosOp;
    private javax.swing.JLabel lblcodlabor;
    private javax.swing.JTextField txtbusqueda;
    private javax.swing.JTextField txtcodmed;
    private javax.swing.JLabel txtmsg;
    private javax.swing.JLabel txtmsgdetalles;
    // End of variables declaration//GEN-END:variables
}
