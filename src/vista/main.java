package vista;

import controlador.controllerClientes;
import controlador.controllerCompuestos;
import controlador.controllerLaboratorio;
import controlador.controllerMedicamentos;
import controlador.controllerProveedores;
import controlador.controllerUsuarios;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;

/**
 *
 * @author AMolinaDeveloper
 */
public final class main extends javax.swing.JFrame implements KeyListener {

    private DefaultTableModel dtm;
    private static Preferences prefs;
    private String xx;
    private int seg = 0;

    /**
     * Creates new form NewJFrame
     */
    public main() {
        initComponents();

        Timer();

        this.addKeyListener(this);
        this.setExtendedState(MAXIMIZED_BOTH);

        loader();
        this.jDialog11.setLocationRelativeTo(this.jDesktopPane);
        this.jDialog11.setVisible(true);
//pantallaResolucion();

    }

    public void pantallaResolucion() {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);
        JOptionPane.showMessageDialog(this, "Hoy tu ancho es :" + screenSize.width + " y tu alto es : " + screenSize.height);
        this.setSize(screenSize);
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("vista/img/grafico.png"));

        return retValue;
    }

    public final void loader() {
        if (this.cmbPermisos.getSelectedIndex() == 0) {
            this.lblPermisos.setText("suprv");
        } else {
            this.lblPermisos.setText("dpch");
        }

        prefs = Preferences.userRoot().node("configuracion");
        prefs.get("direccion", "<<SIN DIRECCION>>");
        prefs.get("razon", "<<SIN RAZON SOCIAL>>");
        prefs.get("ruc", "<<SIN RUC>>");
        prefs.get("frase", "<<SIN FRASE DE PIE DE TICKET>>");
        this.setTitle(prefs.get("razon", "<<SIN RAZON SOCIAL>>") + "***" + prefs.get("ruc", "<<SIN RUC>>"));
        this.RazonSocial.setText(prefs.get("razon", "<<SIN RAZON SOCIAL>>"));
        this.Ruc.setText(prefs.get("ruc", "<<SIN RUC>>"));
        this.Direccion.setText(prefs.get("direccion", "<<SIN DIRECCION>>"));
        this.Frase.setText(prefs.get("frase", "<<SIN FRASE DE PIE DE TICKET>>"));
        this.impresora.setText(prefs.get("impresora", "<<SIN IMPRESORA>>"));
        if (prefs.get("act", "0").equals("69")) {
            this.menuAjustes2.setEnabled(false);
        }

        if (prefs.get("act", "0").equals("0")) {
            this.menuReportes.setEnabled(false);
            this.menuAjustes.setEnabled(false);
            this.menuCompras.setEnabled(false);
        }
        if (prefs.get("sistema", "farma").equals("farma")) {
            this.rdFarmaceutica.setSelected(true);
            this.rdVentas.setSelected(false);

        } else {
            this.menuMedicamento.setText("Productos");
            this.menuCompuestos.setVisible(false);

            this.jMenuItemCompuestos.setVisible(false);

            this.rdVentas.setSelected(true);
            this.rdFarmaceutica.setSelected(false);
        }

    }

    public final void RegistroDelSistema() {
        prefs = Preferences.userRoot().node("configuracion");
        prefs.put("direccion", this.Direccion.getText().toUpperCase());
        prefs.put("razon", this.RazonSocial.getText().toUpperCase());
        prefs.put("ruc", this.Ruc.getText());
        prefs.put("frase", this.Frase.getText().toUpperCase());
        prefs.put("impresora", this.impresora.getText());
        prefs.put("sistema", this.rdFarmaceutica.isSelected() ? "farma" : "comerc");
        JOptionPane.showMessageDialog(this, "Los datos acaban de ser actualizados,para ver algunos cambios\n sera necesario cerrar y volver a abrir el sistema.", "Datos actualizados", JOptionPane.INFORMATION_MESSAGE);
        loader();
    }

    public final void Timer() {
        // Clase en la que está el código a ejecutar 
        TimerTask timerTask = new TimerTask() {
            public void run() {
                notificaciones();

                //    Toolkit.getDefaultToolkit().beep();
                System.out.println("Timer en ejecucion ...");
                // if(this.)
            }
        };

        // Aquí se pone en marcha el timer cada segundo. 
        Timer timer = new Timer();
        // Dentro de 0 milisegundos avísame cada 1000 milisegundos 
        // 900000 es 15 minutos
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void notificaciones() {
        try {
            //Fecha actual desglosada:
            Calendar fecha = Calendar.getInstance();
            int año = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH) + 1;
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            int hora = fecha.get(Calendar.HOUR_OF_DAY);
            int minuto = fecha.get(Calendar.MINUTE);
            int segundo = fecha.get(Calendar.SECOND);
            if (hora == 12) {
                xx = "m";
            } else {
                if (hora > 12) {
                    xx = "pm";
                } else {
                    xx = "am";
                }
            }
            this.jMenu3.setText("Hoy es: " + año + "/" + mes + "/" + dia + "\n   -   La hora es: " + hora + ":" + minuto + ":" + segundo + " " + xx);

        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
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

        jDialog1 = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        txtfecha = new javax.swing.JTextField();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu13 = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();
        jDialog2 = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        txtFechaDesde = new javax.swing.JTextField();
        txtFechaHasta = new javax.swing.JTextField();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu17 = new javax.swing.JMenu();
        jMenu18 = new javax.swing.JMenu();
        jDialog3 = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        txtFechaDesde1 = new javax.swing.JTextField();
        txtFechaHasta1 = new javax.swing.JTextField();
        txtcodusuario = new javax.swing.JTextField();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu19 = new javax.swing.JMenu();
        jMenu20 = new javax.swing.JMenu();
        jDialog4 = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLaboratorios = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        rdTodos = new javax.swing.JRadioButton();
        rdConStock = new javax.swing.JRadioButton();
        rdBajoStock = new javax.swing.JRadioButton();
        rdSinStock = new javax.swing.JRadioButton();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu12 = new javax.swing.JMenu();
        jMenu16 = new javax.swing.JMenu();
        jDialog5 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        txtFechaDesde2 = new javax.swing.JTextField();
        txtFechaHasta2 = new javax.swing.JTextField();
        jMenuBar8 = new javax.swing.JMenuBar();
        jMenu23 = new javax.swing.JMenu();
        jMenu24 = new javax.swing.JMenu();
        jDialog6 = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        txtdoc = new javax.swing.JTextField();
        jMenuBar9 = new javax.swing.JMenuBar();
        jMenu25 = new javax.swing.JMenu();
        jMenu26 = new javax.swing.JMenu();
        jDialog7 = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        fechaInicio = new javax.swing.JTextField();
        fechaFinal = new javax.swing.JTextField();
        med = new javax.swing.JTextField();
        lab = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu11 = new javax.swing.JMenu();
        jMenu14 = new javax.swing.JMenu();
        jDialog8 = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        RazonSocial = new javax.swing.JTextField();
        Direccion = new javax.swing.JTextField();
        Ruc = new javax.swing.JTextField();
        Frase = new javax.swing.JTextField();
        impresora = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        rdVentas = new javax.swing.JRadioButton();
        rdFarmaceutica = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel20 = new javax.swing.JPanel();
        PaneReportes = new javax.swing.JPanel();
        chkRptActivos = new javax.swing.JCheckBox();
        chkRptInvComplt = new javax.swing.JCheckBox();
        chkRptConStock = new javax.swing.JCheckBox();
        chkRptSinStock = new javax.swing.JCheckBox();
        chkRptEstAlert = new javax.swing.JCheckBox();
        chkRptFiltradXLab = new javax.swing.JCheckBox();
        chkSegimitMedic = new javax.swing.JCheckBox();
        chkRptVentaAnio = new javax.swing.JCheckBox();
        chkRptVentasDH = new javax.swing.JCheckBox();
        chkRptVentasDia = new javax.swing.JCheckBox();
        chkRptVentasDUsuario = new javax.swing.JCheckBox();
        chkRptCompras = new javax.swing.JCheckBox();
        chkRptComprasXDoc = new javax.swing.JCheckBox();
        chkRptComprasDH = new javax.swing.JCheckBox();
        PaneFormularios = new javax.swing.JPanel();
        chkClientes = new javax.swing.JCheckBox();
        chkMedOProds = new javax.swing.JCheckBox();
        chkCompuestos = new javax.swing.JCheckBox();
        chkUsuarios = new javax.swing.JCheckBox();
        chkProveedores = new javax.swing.JCheckBox();
        chkLaborMarcas = new javax.swing.JCheckBox();
        chkPedidosInternos = new javax.swing.JCheckBox();
        chkCompras = new javax.swing.JCheckBox();
        chkBuscarXCompuest = new javax.swing.JCheckBox();
        chkBuscador = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        cmbPermisos = new javax.swing.JComboBox();
        btnguardar1 = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnGuardarPermisos = new javax.swing.JButton();
        lblPermisos = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog9 = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        txtanio = new javax.swing.JTextField();
        jMenuBar7 = new javax.swing.JMenuBar();
        jMenu21 = new javax.swing.JMenu();
        jMenu22 = new javax.swing.JMenu();
        jDialog10 = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btncerrar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jDialog11 = new javax.swing.JDialog();
        jPanel21 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        lblsesion = new javax.swing.JLabel();
        jDialog12 = new javax.swing.JDialog();
        jPanel22 = new javax.swing.JPanel();
        jProgressBar2 = new javax.swing.JProgressBar();
        lblsesion1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jDesktopPane = new javax.swing.JDesktopPane();
        logo = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPaneStock = new javax.swing.JEditorPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jEditorPaneAdvertencias = new javax.swing.JEditorPane();
        jLabel5 = new javax.swing.JLabel();
        lblcodigo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblpermisos = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        menuClientes = new javax.swing.JMenu();
        jMenuItemClientes = new javax.swing.JMenuItem();
        menuMedicamento = new javax.swing.JMenuItem();
        menuCompuestos = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuLaboratorios = new javax.swing.JMenuItem();
        menuProveedores = new javax.swing.JMenuItem();
        menuUsuarios = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        menuPedidos = new javax.swing.JMenuItem();
        menuCompras = new javax.swing.JMenuItem();
        jMenuItemCompuestos = new javax.swing.JMenuItem();
        menuReportes = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        rptActivos = new javax.swing.JMenuItem();
        rptInventarioC = new javax.swing.JMenuItem();
        rptInventarioCS = new javax.swing.JMenuItem();
        rptInventarioSS = new javax.swing.JMenuItem();
        rptInventarioEA = new javax.swing.JMenuItem();
        rptFiltradoXLab = new javax.swing.JMenuItem();
        rptSeguimientoMed = new javax.swing.JMenuItem();
        rptVentasAnio = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        rptVentasDH = new javax.swing.JMenuItem();
        RptVentasDia = new javax.swing.JMenuItem();
        rptVentasXUsu = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        rptCompras = new javax.swing.JMenuItem();
        rptComprasDH = new javax.swing.JMenuItem();
        rptComprasXDoc = new javax.swing.JMenuItem();
        menuHerramientas = new javax.swing.JMenu();
        menuAjustes = new javax.swing.JMenuItem();
        menuAjustes1 = new javax.swing.JMenuItem();
        menuAjustes2 = new javax.swing.JMenuItem();
        menuBuscador = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        jDialog1.setMinimumSize(new java.awt.Dimension(245, 127));
        jDialog1.setModal(true);
        jDialog1.setUndecorated(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSCAR POR FECHA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        txtfecha.setBackground(new java.awt.Color(255, 255, 204));
        txtfecha.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtfecha.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtfecha.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Fecha YYYY-MM-DD", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtfecha.setCaretColor(new java.awt.Color(51, 204, 255));
        txtfecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfechaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtfecha, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar4MouseDragged(evt);
            }
        });
        jMenuBar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuBar4MousePressed(evt);
            }
        });

        jMenu13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/zoom in.png"))); // NOI18N
        jMenu13.setText("BUSCAR");
        jMenu13.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu13.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu13MousePressed(evt);
            }
        });
        jMenuBar4.add(jMenu13);

        jMenu15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        jMenu15.setText("CERRAR VENTANA");
        jMenu15.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu15.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu15.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu15MousePressed(evt);
            }
        });
        jMenu15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu15ActionPerformed(evt);
            }
        });
        jMenuBar4.add(jMenu15);

        jDialog1.setJMenuBar(jMenuBar4);

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog2.setMinimumSize(new java.awt.Dimension(275, 172));
        jDialog2.setModal(true);
        jDialog2.setUndecorated(true);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSCAR POR FECHA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        txtFechaDesde.setBackground(new java.awt.Color(255, 255, 204));
        txtFechaDesde.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtFechaDesde.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFechaDesde.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Fecha Desde YYYY-MM-DD", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtFechaDesde.setCaretColor(new java.awt.Color(51, 204, 255));
        txtFechaDesde.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaDesdeKeyReleased(evt);
            }
        });

        txtFechaHasta.setBackground(new java.awt.Color(255, 255, 204));
        txtFechaHasta.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtFechaHasta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFechaHasta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Fecha Hasta YYYY-MM-DD", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtFechaHasta.setCaretColor(new java.awt.Color(51, 204, 255));
        txtFechaHasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaHastaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtFechaDesde, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
            .addComponent(txtFechaHasta)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar5MouseDragged(evt);
            }
        });
        jMenuBar5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuBar5MousePressed(evt);
            }
        });

        jMenu17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/zoom in.png"))); // NOI18N
        jMenu17.setText("BUSCAR");
        jMenu17.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu17.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu17.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu17MousePressed(evt);
            }
        });
        jMenuBar5.add(jMenu17);

        jMenu18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        jMenu18.setText("CERRAR VENTANA");
        jMenu18.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu18.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu18.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu18MousePressed(evt);
            }
        });
        jMenu18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu18ActionPerformed(evt);
            }
        });
        jMenuBar5.add(jMenu18);

        jDialog2.setJMenuBar(jMenuBar5);

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog3.setMinimumSize(new java.awt.Dimension(285, 227));
        jDialog3.setModal(true);
        jDialog3.setUndecorated(true);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSCAR POR FECHA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        txtFechaDesde1.setBackground(new java.awt.Color(255, 255, 204));
        txtFechaDesde1.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtFechaDesde1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFechaDesde1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Fecha Desde YYYY-MM-DD", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtFechaDesde1.setCaretColor(new java.awt.Color(51, 204, 255));
        txtFechaDesde1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaDesde1KeyReleased(evt);
            }
        });

        txtFechaHasta1.setBackground(new java.awt.Color(255, 255, 204));
        txtFechaHasta1.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtFechaHasta1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFechaHasta1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Fecha Hasta YYYY-MM-DD", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtFechaHasta1.setCaretColor(new java.awt.Color(51, 204, 255));
        txtFechaHasta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaHasta1KeyReleased(evt);
            }
        });

        txtcodusuario.setBackground(new java.awt.Color(255, 255, 204));
        txtcodusuario.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtcodusuario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtcodusuario.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Codigo Usuario", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtcodusuario.setCaretColor(new java.awt.Color(51, 204, 255));
        txtcodusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodusuarioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtcodusuario, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
            .addComponent(txtFechaHasta1)
            .addComponent(txtFechaDesde1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(txtFechaDesde1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaHasta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcodusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jMenuBar6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar6MouseDragged(evt);
            }
        });
        jMenuBar6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuBar6MousePressed(evt);
            }
        });

        jMenu19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/zoom in.png"))); // NOI18N
        jMenu19.setText("BUSCAR");
        jMenu19.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu19.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu19.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu19MousePressed(evt);
            }
        });
        jMenuBar6.add(jMenu19);

        jMenu20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        jMenu20.setText("CERRAR VENTANA");
        jMenu20.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu20.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu20.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu20MousePressed(evt);
            }
        });
        jMenu20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu20ActionPerformed(evt);
            }
        });
        jMenuBar6.add(jMenu20);

        jDialog3.setJMenuBar(jMenuBar6);

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jDialog4.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialog4.setMinimumSize(new java.awt.Dimension(290, 399));
        jDialog4.setModal(true);
        jDialog4.setUndecorated(true);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SELECCIONE LABORATORIOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        jTableLaboratorios.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jTableLaboratorios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableLaboratorios);

        jPanel13.setBackground(new java.awt.Color(255, 255, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "FILTRAR BUSQUEDA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));

        rdTodos.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(rdTodos);
        rdTodos.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rdTodos.setText("Todos");
        rdTodos.setFocusable(false);
        rdTodos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdTodos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rdTodos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        rdTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdTodosActionPerformed(evt);
            }
        });
        jPanel13.add(rdTodos);

        rdConStock.setBackground(new java.awt.Color(204, 255, 204));
        buttonGroup1.add(rdConStock);
        rdConStock.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rdConStock.setText("Con stock");
        rdConStock.setFocusable(false);
        rdConStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdConStock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rdConStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel13.add(rdConStock);

        rdBajoStock.setBackground(new java.awt.Color(255, 204, 102));
        buttonGroup1.add(rdBajoStock);
        rdBajoStock.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rdBajoStock.setSelected(true);
        rdBajoStock.setText("Bajo Stock");
        rdBajoStock.setFocusable(false);
        rdBajoStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdBajoStock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rdBajoStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel13.add(rdBajoStock);

        rdSinStock.setBackground(new java.awt.Color(255, 153, 153));
        buttonGroup1.add(rdSinStock);
        rdSinStock.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rdSinStock.setText("Sin Stock");
        rdSinStock.setFocusable(false);
        rdSinStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rdSinStock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rdSinStock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel13.add(rdSinStock);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar3MouseDragged(evt);
            }
        });
        jMenuBar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar3MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuBar3MousePressed(evt);
            }
        });

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/zoom in.png"))); // NOI18N
        jMenu12.setText("BUSCAR");
        jMenu12.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu12.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu12.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu12MousePressed(evt);
            }
        });
        jMenuBar3.add(jMenu12);

        jMenu16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        jMenu16.setText("CERRAR VENTANA");
        jMenu16.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu16.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu16MousePressed(evt);
            }
        });
        jMenuBar3.add(jMenu16);

        jDialog4.setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog5.setMinimumSize(new java.awt.Dimension(233, 161));
        jDialog5.setModal(true);
        jDialog5.setUndecorated(true);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSCAR POR FECHA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        txtFechaDesde2.setBackground(new java.awt.Color(255, 255, 204));
        txtFechaDesde2.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtFechaDesde2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFechaDesde2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Fecha Desde YYYY-MM-DD", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtFechaDesde2.setCaretColor(new java.awt.Color(51, 204, 255));
        txtFechaDesde2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaDesde2KeyReleased(evt);
            }
        });

        txtFechaHasta2.setBackground(new java.awt.Color(255, 255, 204));
        txtFechaHasta2.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtFechaHasta2.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFechaHasta2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Fecha Hasta YYYY-MM-DD", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtFechaHasta2.setCaretColor(new java.awt.Color(51, 204, 255));
        txtFechaHasta2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaHasta2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(txtFechaDesde2)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txtFechaHasta2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txtFechaDesde2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaHasta2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar8MouseDragged(evt);
            }
        });
        jMenuBar8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar8MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuBar8MousePressed(evt);
            }
        });

        jMenu23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/zoom in.png"))); // NOI18N
        jMenu23.setText("BUSCAR");
        jMenu23.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu23.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu23.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu23MousePressed(evt);
            }
        });
        jMenuBar8.add(jMenu23);

        jMenu24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        jMenu24.setText("CERRAR VENTANA");
        jMenu24.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu24.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu24.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu24MousePressed(evt);
            }
        });
        jMenu24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu24ActionPerformed(evt);
            }
        });
        jMenuBar8.add(jMenu24);

        jDialog5.setJMenuBar(jMenuBar8);

        javax.swing.GroupLayout jDialog5Layout = new javax.swing.GroupLayout(jDialog5.getContentPane());
        jDialog5.getContentPane().setLayout(jDialog5Layout);
        jDialog5Layout.setHorizontalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog5Layout.setVerticalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog6.setMinimumSize(new java.awt.Dimension(245, 119));
        jDialog6.setModal(true);
        jDialog6.setUndecorated(true);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSCAR POR DOCUMENTO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        txtdoc.setBackground(new java.awt.Color(255, 255, 204));
        txtdoc.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtdoc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtdoc.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Ingrese Nº documento", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtdoc.setCaretColor(new java.awt.Color(51, 204, 255));
        txtdoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdocKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtdoc, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtdoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jMenuBar9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar9MouseDragged(evt);
            }
        });
        jMenuBar9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar9MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuBar9MousePressed(evt);
            }
        });

        jMenu25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/zoom in.png"))); // NOI18N
        jMenu25.setText("BUSCAR");
        jMenu25.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu25.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu25.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu25MousePressed(evt);
            }
        });
        jMenuBar9.add(jMenu25);

        jMenu26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        jMenu26.setText("CERRAR VENTANA");
        jMenu26.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu26.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu26.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu26MousePressed(evt);
            }
        });
        jMenu26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu26ActionPerformed(evt);
            }
        });
        jMenuBar9.add(jMenu26);

        jDialog6.setJMenuBar(jMenuBar9);

        javax.swing.GroupLayout jDialog6Layout = new javax.swing.GroupLayout(jDialog6.getContentPane());
        jDialog6.getContentPane().setLayout(jDialog6Layout);
        jDialog6Layout.setHorizontalGroup(
            jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog6Layout.setVerticalGroup(
            jDialog6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jDialog7.setMinimumSize(new java.awt.Dimension(358, 251));
        jDialog7.setModal(true);
        jDialog7.setUndecorated(true);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SEGUIMIENTO DE MEDICAMENTOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        fechaInicio.setBackground(new java.awt.Color(255, 255, 204));
        fechaInicio.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        fechaInicio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fechaInicio.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Fecha Desde YYYY-MM-DD", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        fechaInicio.setCaretColor(new java.awt.Color(51, 204, 255));
        fechaInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fechaInicioKeyReleased(evt);
            }
        });

        fechaFinal.setBackground(new java.awt.Color(255, 255, 204));
        fechaFinal.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        fechaFinal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        fechaFinal.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Fecha Hasta YYYY-MM-DD", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        fechaFinal.setCaretColor(new java.awt.Color(51, 204, 255));
        fechaFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                fechaFinalKeyReleased(evt);
            }
        });

        med.setBackground(new java.awt.Color(255, 204, 255));
        med.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        med.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        med.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Nombre del medicamento", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        med.setCaretColor(new java.awt.Color(51, 204, 255));
        med.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                medKeyReleased(evt);
            }
        });

        lab.setBackground(new java.awt.Color(255, 204, 255));
        lab.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        lab.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lab.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Nombre del laboratorio", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        lab.setCaretColor(new java.awt.Color(51, 204, 255));
        lab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                labKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(med, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addComponent(lab, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaInicio)
                    .addComponent(fechaFinal))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(med, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuBar2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar2MouseDragged(evt);
            }
        });
        jMenuBar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuBar2MousePressed(evt);
            }
        });

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/zoom in.png"))); // NOI18N
        jMenu11.setText("BUSCAR");
        jMenu11.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu11.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu11.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu11MousePressed(evt);
            }
        });
        jMenuBar2.add(jMenu11);

        jMenu14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        jMenu14.setText("CERRAR VENTANA");
        jMenu14.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu14.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu14.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu14MousePressed(evt);
            }
        });
        jMenuBar2.add(jMenu14);

        jDialog7.setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout jDialog7Layout = new javax.swing.GroupLayout(jDialog7.getContentPane());
        jDialog7.getContentPane().setLayout(jDialog7Layout);
        jDialog7Layout.setHorizontalGroup(
            jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jDialog7Layout.setVerticalGroup(
            jDialog7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog8.setTitle("Datos de la Empresa");
        jDialog8.setMinimumSize(new java.awt.Dimension(695, 726));
        jDialog8.setModal(true);
        jDialog8.setUndecorated(true);
        jDialog8.setResizable(false);
        jDialog8.setType(java.awt.Window.Type.UTILITY);
        jDialog8.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                jDialog8WindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                jDialog8WindowOpened(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true), "DATOS DE LA EMPRESA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 1, 14))); // NOI18N

        RazonSocial.setName("RazonSocial");
        RazonSocial.setBackground(new java.awt.Color(255, 204, 204));
        RazonSocial.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        RazonSocial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RazonSocial.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Razón social de la empresa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        RazonSocial.setCaretColor(new java.awt.Color(51, 204, 255));
        RazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RazonSocialKeyReleased(evt);
            }
        });

        Direccion.setName("Direccion");
        Direccion.setBackground(new java.awt.Color(255, 204, 102));
        Direccion.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Direccion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Dirección de la empresa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Direccion.setCaretColor(new java.awt.Color(51, 204, 255));
        Direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DireccionActionPerformed(evt);
            }
        });
        Direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DireccionKeyReleased(evt);
            }
        });

        Ruc.setName("RUC");
        Ruc.setBackground(new java.awt.Color(255, 204, 204));
        Ruc.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Ruc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ruc.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "RUC de la empresa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Ruc.setCaretColor(new java.awt.Color(51, 204, 255));
        Ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RucKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RucKeyTyped(evt);
            }
        });

        Frase.setName("Frase");
        Frase.setBackground(new java.awt.Color(255, 204, 102));
        Frase.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Frase.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Frase.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Frase a ser insertada en pie de página de ticket`s", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Frase.setCaretColor(new java.awt.Color(51, 204, 255));
        Frase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FraseActionPerformed(evt);
            }
        });
        Frase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FraseKeyReleased(evt);
            }
        });

        Frase.setName("Frase");
        impresora.setBackground(new java.awt.Color(255, 255, 153));
        impresora.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        impresora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        impresora.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Nombre de la impresora", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        impresora.setCaretColor(new java.awt.Color(51, 204, 255));
        impresora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impresoraActionPerformed(evt);
            }
        });
        impresora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                impresoraKeyReleased(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(255, 255, 102));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adaptar el sistema para un entidad de tipo:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        rdVentas.setBackground(new java.awt.Color(255, 255, 102));
        buttonGroup2.add(rdVentas);
        rdVentas.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rdVentas.setText("Ventas o comercio");

        rdFarmaceutica.setBackground(new java.awt.Color(255, 255, 102));
        buttonGroup2.add(rdFarmaceutica);
        rdFarmaceutica.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rdFarmaceutica.setSelected(true);
        rdFarmaceutica.setText("Farmaceutica");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdFarmaceutica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(rdVentas)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rdVentas)
                .addComponent(rdFarmaceutica))
        );

        jPanel17.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 51), 1, true), "DESEA ACTIVAR EL USO EXCLUSIVO DEL SISTEMA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        jTextArea1.setBackground(new java.awt.Color(255, 255, 153));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Al activarse el \"Uso exclusivo del sistema\" este sistema se iniciara cada vez que \nencienda la pc,bloqueara la pantalla para que los usuarios puedan usar unicamente el \nsistema,activando esta opcion evita distracciones de parte del personal encargado de esta\naplicacion.");
        jTextArea1.setEnabled(false);
        jScrollPane5.setViewportView(jTextArea1);

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 204));
        jCheckBox1.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jCheckBox1.setText("Activar el uso exclusivo del sistema");
        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCheckBox1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jCheckBox1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true), "PERMISOS DE LOS USUARIOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        PaneReportes.setBackground(new java.awt.Color(255, 255, 204));
        PaneReportes.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Reportes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        chkRptActivos.setBackground(new java.awt.Color(255, 51, 51));
        chkRptActivos.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptActivos.setText("Reportes de activos de la entidad");

        chkRptInvComplt.setBackground(new java.awt.Color(255, 51, 51));
        chkRptInvComplt.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptInvComplt.setText("Reporte de inventario[COMPLETO]");

        chkRptConStock.setBackground(new java.awt.Color(255, 51, 51));
        chkRptConStock.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptConStock.setText("Reporte de inventario[CON STOCK]");

        chkRptSinStock.setBackground(new java.awt.Color(255, 51, 51));
        chkRptSinStock.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptSinStock.setText("Reporte de inventario[SIN STOCK]");

        chkRptEstAlert.setBackground(new java.awt.Color(255, 51, 51));
        chkRptEstAlert.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptEstAlert.setText("Reporte de inventario[ESTADO ALERTA]");

        chkRptFiltradXLab.setBackground(new java.awt.Color(255, 51, 51));
        chkRptFiltradXLab.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptFiltradXLab.setText("Reporte de inventario filtrado por laboratorio");

        chkSegimitMedic.setBackground(new java.awt.Color(255, 51, 51));
        chkSegimitMedic.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkSegimitMedic.setText("Seguimiento de medicamentos");

        chkRptVentaAnio.setBackground(new java.awt.Color(255, 51, 51));
        chkRptVentaAnio.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptVentaAnio.setText("Ventas del año");

        chkRptVentasDH.setBackground(new java.awt.Color(255, 153, 51));
        chkRptVentasDH.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptVentasDH.setText("Ventas desde-hasta");

        chkRptVentasDia.setBackground(new java.awt.Color(255, 153, 51));
        chkRptVentasDia.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptVentasDia.setText("Ventas del dia");

        chkRptVentasDUsuario.setBackground(new java.awt.Color(255, 153, 51));
        chkRptVentasDUsuario.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptVentasDUsuario.setText("Ventas desde-hasta por USUARIOS");

        chkRptCompras.setBackground(new java.awt.Color(255, 255, 102));
        chkRptCompras.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptCompras.setText("Compras realizadas");

        chkRptComprasXDoc.setBackground(new java.awt.Color(255, 255, 102));
        chkRptComprasXDoc.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptComprasXDoc.setText("Compras por documento");

        chkRptComprasDH.setBackground(new java.awt.Color(255, 255, 102));
        chkRptComprasDH.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkRptComprasDH.setText("Compras desde-hasta");

        javax.swing.GroupLayout PaneReportesLayout = new javax.swing.GroupLayout(PaneReportes);
        PaneReportes.setLayout(PaneReportesLayout);
        PaneReportesLayout.setHorizontalGroup(
            PaneReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneReportesLayout.createSequentialGroup()
                .addGroup(PaneReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkRptSinStock, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkRptVentaAnio)
                    .addGroup(PaneReportesLayout.createSequentialGroup()
                        .addGroup(PaneReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkRptFiltradXLab)
                            .addComponent(chkSegimitMedic)
                            .addComponent(chkRptEstAlert, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkRptActivos)
                            .addComponent(chkRptInvComplt)
                            .addComponent(chkRptConStock, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PaneReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkRptVentasDH)
                            .addComponent(chkRptVentasDia)
                            .addComponent(chkRptVentasDUsuario)
                            .addComponent(chkRptComprasDH)
                            .addComponent(chkRptComprasXDoc)
                            .addComponent(chkRptCompras))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        PaneReportesLayout.setVerticalGroup(
            PaneReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneReportesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PaneReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneReportesLayout.createSequentialGroup()
                        .addComponent(chkRptActivos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRptInvComplt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRptConStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRptSinStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRptEstAlert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRptFiltradXLab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkSegimitMedic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRptVentaAnio))
                    .addGroup(PaneReportesLayout.createSequentialGroup()
                        .addComponent(chkRptVentasDH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRptVentasDia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRptVentasDUsuario)
                        .addGap(13, 13, 13)
                        .addComponent(chkRptCompras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRptComprasDH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkRptComprasXDoc)))
                .addGap(116, 116, 116))
        );

        PaneFormularios.setBackground(new java.awt.Color(255, 255, 204));
        PaneFormularios.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Formularios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        chkClientes.setBackground(new java.awt.Color(255, 51, 51));
        chkClientes.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkClientes.setText("Clientes");

        chkMedOProds.setBackground(new java.awt.Color(255, 51, 51));
        chkMedOProds.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkMedOProds.setText("Medicamentos o productos");

        chkCompuestos.setBackground(new java.awt.Color(255, 51, 51));
        chkCompuestos.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkCompuestos.setText("Compuestos(**)");

        chkUsuarios.setBackground(new java.awt.Color(255, 153, 51));
        chkUsuarios.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkUsuarios.setText("Usuarios");

        chkProveedores.setBackground(new java.awt.Color(255, 153, 51));
        chkProveedores.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkProveedores.setText("Proveedores");

        chkLaborMarcas.setBackground(new java.awt.Color(255, 153, 51));
        chkLaborMarcas.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkLaborMarcas.setText("Laboratorio o marca");

        chkPedidosInternos.setBackground(new java.awt.Color(255, 255, 102));
        chkPedidosInternos.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkPedidosInternos.setText("Generar pedido interno");

        chkCompras.setBackground(new java.awt.Color(255, 255, 102));
        chkCompras.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkCompras.setText("Registrar compras realizadas");

        chkBuscarXCompuest.setBackground(new java.awt.Color(255, 255, 102));
        chkBuscarXCompuest.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkBuscarXCompuest.setText("Buscar por compuestos(**)");

        chkBuscador.setBackground(new java.awt.Color(0, 204, 51));
        chkBuscador.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        chkBuscador.setText("Abrir el buscador");

        javax.swing.GroupLayout PaneFormulariosLayout = new javax.swing.GroupLayout(PaneFormularios);
        PaneFormularios.setLayout(PaneFormulariosLayout);
        PaneFormulariosLayout.setHorizontalGroup(
            PaneFormulariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneFormulariosLayout.createSequentialGroup()
                .addGroup(PaneFormulariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkClientes)
                    .addComponent(chkMedOProds)
                    .addComponent(chkCompuestos)
                    .addComponent(chkLaborMarcas)
                    .addComponent(chkProveedores)
                    .addComponent(chkUsuarios)
                    .addComponent(chkPedidosInternos)
                    .addComponent(chkCompras)
                    .addComponent(chkBuscarXCompuest)
                    .addComponent(chkBuscador))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        PaneFormulariosLayout.setVerticalGroup(
            PaneFormulariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneFormulariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkMedOProds)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCompuestos)
                .addGap(18, 18, 18)
                .addComponent(chkLaborMarcas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkProveedores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkUsuarios)
                .addGap(18, 18, 18)
                .addComponent(chkPedidosInternos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCompras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkBuscarXCompuest)
                .addGap(18, 18, 18)
                .addComponent(chkBuscador)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel12.setText("** Solo en modo farmaceutico.");

        cmbPermisos.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        cmbPermisos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SUPERVISOR", "DESPACHADOR" }));
        cmbPermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPermisosActionPerformed(evt);
            }
        });

        btnguardar1.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        btnguardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        btnguardar1.setText("CANCELAR");
        btnguardar1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnguardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnguardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar1ActionPerformed(evt);
            }
        });

        btnguardar.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/OK.png"))); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnGuardarPermisos.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        btnGuardarPermisos.setText("GUARDAR PERMISOS");
        btnGuardarPermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPermisosActionPerformed(evt);
            }
        });

        lblPermisos.setText("molina");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addComponent(PaneFormularios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PaneReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(lblPermisos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarPermisos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnguardar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(PaneReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cmbPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarPermisos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnguardar)
                            .addComponent(btnguardar1)
                            .addComponent(lblPermisos)))
                    .addComponent(PaneFormularios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(impresora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                        .addComponent(Direccion, javax.swing.GroupLayout.Alignment.LEADING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Frase)))
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addComponent(RazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Frase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(impresora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jDialog8Layout = new javax.swing.GroupLayout(jDialog8.getContentPane());
        jDialog8.getContentPane().setLayout(jDialog8Layout);
        jDialog8Layout.setHorizontalGroup(
            jDialog8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog8Layout.setVerticalGroup(
            jDialog8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog9.setMinimumSize(new java.awt.Dimension(245, 127));
        jDialog9.setModal(true);
        jDialog9.setUndecorated(true);

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSCAR POR AÑO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        txtanio.setBackground(new java.awt.Color(255, 255, 204));
        txtanio.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtanio.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtanio.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Insertar el año", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtanio.setCaretColor(new java.awt.Color(51, 204, 255));
        txtanio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtanioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtanio, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(txtanio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jMenuBar7MouseDragged(evt);
            }
        });
        jMenuBar7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar7MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuBar7MousePressed(evt);
            }
        });

        jMenu21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/zoom in.png"))); // NOI18N
        jMenu21.setText("BUSCAR");
        jMenu21.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu21.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu21.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu21MousePressed(evt);
            }
        });
        jMenuBar7.add(jMenu21);

        jMenu22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        jMenu22.setText("CERRAR VENTANA");
        jMenu22.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu22.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu22.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu22MousePressed(evt);
            }
        });
        jMenu22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu22ActionPerformed(evt);
            }
        });
        jMenuBar7.add(jMenu22);

        jDialog9.setJMenuBar(jMenuBar7);

        javax.swing.GroupLayout jDialog9Layout = new javax.swing.GroupLayout(jDialog9.getContentPane());
        jDialog9.getContentPane().setLayout(jDialog9Layout);
        jDialog9Layout.setHorizontalGroup(
            jDialog9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jDialog9Layout.setVerticalGroup(
            jDialog9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog10.setMinimumSize(new java.awt.Dimension(803, 463));
        jDialog10.setModal(true);
        jDialog10.setUndecorated(true);

        jPanel15.setBackground(new java.awt.Color(24, 83, 138));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 83, 138), 3, true));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Versión:");

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SistemaFarma");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Desarrollado por:");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Facebook:");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/farmacia.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("4.7.0");

        jLabel10.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Alvaro Emanuel Molina Cruz");

        jLabel11.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("https://web.facebook.com/SistemasInformaticosEmpresariales/");

        btncerrar.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        btncerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        btncerrar.setText("Cerrar la ventana");
        btncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncerrarActionPerformed(evt);
            }
        });

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("Reglas del sistema\n\n1.-Solo los usuarios ADMIN pueden crear otros usuarios.\n2.-Solo los usuarios ADMIN pueden brindar PERMISOS a otros usuarios.\n3.-Solo los usuarios ADMIN pueden activar y desactivar el \"modo exclusivo del sistema\".\n4.-Solo los usuarios ADMIN pueden cerrar el sistema en \"modo exclusivo del sistema\".");
        jScrollPane3.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btncerrar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11)))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jDialog10Layout = new javax.swing.GroupLayout(jDialog10.getContentPane());
        jDialog10.getContentPane().setLayout(jDialog10Layout);
        jDialog10Layout.setHorizontalGroup(
            jDialog10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog10Layout.setVerticalGroup(
            jDialog10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jDialog11.setAlwaysOnTop(true);
        jDialog11.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jDialog11.setEnabled(false);
        jDialog11.setFocusable(false);
        jDialog11.setMinimumSize(new java.awt.Dimension(485, 52));
        jDialog11.setModal(true);
        jDialog11.setUndecorated(true);
        jDialog11.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                jDialog11WindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                jDialog11WindowOpened(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(51, 51, 51));

        jProgressBar1.setBackground(new java.awt.Color(102, 0, 255));
        jProgressBar1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(153, 0, 0));
        jProgressBar1.setStringPainted(true);

        lblsesion.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        lblsesion.setForeground(new java.awt.Color(255, 255, 255));
        lblsesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsesion.setText("cargando configuraciones del sistema.");
        lblsesion.setMinimumSize(new java.awt.Dimension(4, 17));
        lblsesion.setPreferredSize(new java.awt.Dimension(4, 17));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblsesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(lblsesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jDialog11Layout = new javax.swing.GroupLayout(jDialog11.getContentPane());
        jDialog11.getContentPane().setLayout(jDialog11Layout);
        jDialog11Layout.setHorizontalGroup(
            jDialog11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog11Layout.setVerticalGroup(
            jDialog11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog12.setAlwaysOnTop(true);
        jDialog12.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jDialog12.setEnabled(false);
        jDialog12.setFocusable(false);
        jDialog12.setMinimumSize(new java.awt.Dimension(485, 52));
        jDialog12.setModal(true);
        jDialog12.setUndecorated(true);
        jDialog12.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                jDialog12WindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                jDialog12WindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                jDialog12WindowOpened(evt);
            }
        });

        jPanel22.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));

        jProgressBar2.setBackground(new java.awt.Color(102, 0, 255));
        jProgressBar2.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jProgressBar2.setForeground(new java.awt.Color(153, 0, 0));
        jProgressBar2.setStringPainted(true);

        lblsesion1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblsesion1.setForeground(new java.awt.Color(51, 51, 51));
        lblsesion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsesion1.setText("....");
        lblsesion1.setMinimumSize(new java.awt.Dimension(4, 17));
        lblsesion1.setPreferredSize(new java.awt.Dimension(4, 17));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblsesion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addComponent(lblsesion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jDialog12Layout = new javax.swing.GroupLayout(jDialog12.getContentPane());
        jDialog12.getContentPane().setLayout(jDialog12Layout);
        jDialog12Layout.setHorizontalGroup(
            jDialog12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog12Layout.setVerticalGroup(
            jDialog12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("[Agregar titulo]");
        setBackground(new java.awt.Color(255, 0, 51));
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(1020, 725));
        setUndecorated(true);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowDeiconified(java.awt.event.WindowEvent evt) {
                formWindowDeiconified(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        jPanel1.setName(""); // NOI18N

        jDesktopPane.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/farmacia.png"))); // NOI18N
        logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jDesktopPane.add(logo);
        logo.setBounds(0, 390, 260, 140);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jDesktopPane.add(jButton3);
        jButton3.setBounds(580, 80, 73, 23);

        jPanel4.setBackground(java.awt.Color.white);
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        jButton1.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/salir.png"))); // NOI18N
        jButton1.setText("Salir de sistema");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/cambio.png"))); // NOI18N
        jButton2.setText("Cerrar sesion");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(700, 163));

        jPanel11.setBackground(new java.awt.Color(51, 0, 51));

        jEditorPaneStock.setEditable(false);
        jEditorPaneStock.setBackground(new java.awt.Color(255, 255, 156));
        jEditorPaneStock.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jScrollPane2.setViewportView(jEditorPaneStock);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Stock", jPanel11);

        jEditorPaneAdvertencias.setBackground(new java.awt.Color(255, 102, 102));
        jEditorPaneAdvertencias.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jEditorPaneAdvertencias.setForeground(new java.awt.Color(153, 0, 0));
        jScrollPane4.setViewportView(jEditorPaneAdvertencias);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Advertencias", jPanel12);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/documento.png"))); // NOI18N
        jLabel5.setText("CODIGO");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel5.setMaximumSize(new java.awt.Dimension(85, 31));
        jLabel5.setMinimumSize(new java.awt.Dimension(85, 31));
        jLabel5.setPreferredSize(new java.awt.Dimension(85, 31));

        lblcodigo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblcodigo.setForeground(new java.awt.Color(102, 102, 102));
        lblcodigo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblcodigo.setText("009");
        lblcodigo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblcodigo.setMaximumSize(new java.awt.Dimension(68, 31));
        lblcodigo.setMinimumSize(new java.awt.Dimension(68, 31));
        lblcodigo.setPreferredSize(new java.awt.Dimension(68, 31));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/jefe.png"))); // NOI18N
        jLabel4.setText("USUARIO");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblusuario.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblusuario.setForeground(new java.awt.Color(102, 102, 102));
        lblusuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblusuario.setText("admin");
        lblusuario.setMaximumSize(new java.awt.Dimension(300, 31));
        lblusuario.setMinimumSize(new java.awt.Dimension(300, 31));
        lblusuario.setPreferredSize(new java.awt.Dimension(300, 31));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/cargo.png"))); // NOI18N
        jLabel6.setText("PERMISOS");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel6.setMaximumSize(new java.awt.Dimension(85, 31));
        jLabel6.setMinimumSize(new java.awt.Dimension(85, 31));
        jLabel6.setPreferredSize(new java.awt.Dimension(85, 31));

        lblpermisos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblpermisos.setForeground(new java.awt.Color(102, 102, 102));
        lblpermisos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblpermisos.setText("admin");
        lblpermisos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblpermisos.setMaximumSize(new java.awt.Dimension(135, 31));
        lblpermisos.setMinimumSize(new java.awt.Dimension(135, 31));
        lblpermisos.setPreferredSize(new java.awt.Dimension(135, 31));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblpermisos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblpermisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jDesktopPane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
        );

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/libra (1).png"))); // NOI18N
        jMenu2.setText("Registros");
        jMenu2.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        menuClientes.setText("Principales");
        menuClientes.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        jMenuItemClientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemClientes.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jMenuItemClientes.setText("Clientes");
        jMenuItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesActionPerformed(evt);
            }
        });
        menuClientes.add(jMenuItemClientes);

        menuMedicamento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuMedicamento.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuMedicamento.setText("Medicamentos");
        menuMedicamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMedicamentoActionPerformed(evt);
            }
        });
        menuClientes.add(menuMedicamento);

        menuCompuestos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuCompuestos.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuCompuestos.setText("Compuestos");
        menuCompuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCompuestosActionPerformed(evt);
            }
        });
        menuClientes.add(menuCompuestos);

        jMenu2.add(menuClientes);

        jMenu5.setText("Secundarios");
        jMenu5.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        menuLaboratorios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuLaboratorios.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuLaboratorios.setText("Laboratorios o marcas");
        menuLaboratorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLaboratoriosActionPerformed(evt);
            }
        });
        jMenu5.add(menuLaboratorios);

        menuProveedores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuProveedores.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuProveedores.setText("Proveedores");
        menuProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProveedoresActionPerformed(evt);
            }
        });
        jMenu5.add(menuProveedores);

        menuUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuUsuarios.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuUsuarios.setText("Usuarios");
        menuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUsuariosActionPerformed(evt);
            }
        });
        jMenu5.add(menuUsuarios);

        jMenu2.add(jMenu5);

        jMenuBar1.add(jMenu2);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/controlar.png"))); // NOI18N
        jMenu7.setText("Procesos");
        jMenu7.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        menuPedidos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuPedidos.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuPedidos.setText("Generar Pedido Interno");
        menuPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPedidosActionPerformed(evt);
            }
        });
        jMenu7.add(menuPedidos);

        menuCompras.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuCompras.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuCompras.setText("Registrar compras realizadas");
        menuCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuComprasActionPerformed(evt);
            }
        });
        jMenu7.add(menuCompras);

        jMenuItemCompuestos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCompuestos.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jMenuItemCompuestos.setText("Buscar por compuestos");
        jMenuItemCompuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCompuestosActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItemCompuestos);

        jMenuBar1.add(jMenu7);

        menuReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/dinero.png"))); // NOI18N
        menuReportes.setText("Reportes");
        menuReportes.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        jMenu4.setText("Reportes de la empresa");
        jMenu4.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        rptActivos.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptActivos.setText("Reporte de activos de la entidad");
        rptActivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptActivosActionPerformed(evt);
            }
        });
        jMenu4.add(rptActivos);

        rptInventarioC.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptInventarioC.setText("Reporte de inventario[COMPLETO]");
        rptInventarioC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptInventarioCActionPerformed(evt);
            }
        });
        jMenu4.add(rptInventarioC);

        rptInventarioCS.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptInventarioCS.setText("Reporte de inventario[SOLO CON STOCK]");
        rptInventarioCS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptInventarioCSActionPerformed(evt);
            }
        });
        jMenu4.add(rptInventarioCS);

        rptInventarioSS.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptInventarioSS.setText("Reporte de inventario[SIN STOCK]");
        rptInventarioSS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptInventarioSSActionPerformed(evt);
            }
        });
        jMenu4.add(rptInventarioSS);

        rptInventarioEA.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptInventarioEA.setText("Reporte de inventario[ESTADO DE ALERTA]");
        rptInventarioEA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptInventarioEAActionPerformed(evt);
            }
        });
        jMenu4.add(rptInventarioEA);

        rptFiltradoXLab.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        rptFiltradoXLab.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptFiltradoXLab.setText("Reporte de inventario FILTRADO por laboratorio");
        rptFiltradoXLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptFiltradoXLabActionPerformed(evt);
            }
        });
        jMenu4.add(rptFiltradoXLab);

        rptSeguimientoMed.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptSeguimientoMed.setText("Reporte de seguimiento de medicamentos");
        rptSeguimientoMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptSeguimientoMedActionPerformed(evt);
            }
        });
        jMenu4.add(rptSeguimientoMed);

        rptVentasAnio.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptVentasAnio.setText("Reporte de ventas del año");
        rptVentasAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptVentasAnioActionPerformed(evt);
            }
        });
        jMenu4.add(rptVentasAnio);

        menuReportes.add(jMenu4);

        jMenu1.setText("Reportes de Ventas");
        jMenu1.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        rptVentasDH.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptVentasDH.setText("Reporte de ventas Desde-Hasta");
        rptVentasDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptVentasDHActionPerformed(evt);
            }
        });
        jMenu1.add(rptVentasDH);

        RptVentasDia.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        RptVentasDia.setText("Reporte de ventas del día");
        RptVentasDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RptVentasDiaActionPerformed(evt);
            }
        });
        jMenu1.add(RptVentasDia);

        rptVentasXUsu.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptVentasXUsu.setText("Reporte de ventas por Usuario Desde-Hasta");
        rptVentasXUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptVentasXUsuActionPerformed(evt);
            }
        });
        jMenu1.add(rptVentasXUsu);

        menuReportes.add(jMenu1);

        jMenu6.setText("Reportes de compras");
        jMenu6.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        rptCompras.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptCompras.setText("Reporte de compras");
        rptCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptComprasActionPerformed(evt);
            }
        });
        jMenu6.add(rptCompras);

        rptComprasDH.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptComprasDH.setText("Reporte de compras desde-hasta");
        rptComprasDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptComprasDHActionPerformed(evt);
            }
        });
        jMenu6.add(rptComprasDH);

        rptComprasXDoc.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rptComprasXDoc.setText("Reporte de compras por documento");
        rptComprasXDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rptComprasXDocActionPerformed(evt);
            }
        });
        jMenu6.add(rptComprasXDoc);

        menuReportes.add(jMenu6);

        jMenuBar1.add(menuReportes);

        menuHerramientas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/tienda.png"))); // NOI18N
        menuHerramientas.setText("Herramientas");
        menuHerramientas.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        menuAjustes.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuAjustes.setText("Ajustes del sistema");
        menuAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAjustesActionPerformed(evt);
            }
        });
        menuHerramientas.add(menuAjustes);

        menuAjustes1.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuAjustes1.setText("Acerca del sistema");
        menuAjustes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAjustes1ActionPerformed(evt);
            }
        });
        menuHerramientas.add(menuAjustes1);

        menuAjustes2.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuAjustes2.setText("Liberar todas las opciones del sistema ");
        menuAjustes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAjustes2ActionPerformed(evt);
            }
        });
        menuHerramientas.add(menuAjustes2);

        menuBuscador.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        menuBuscador.setText("Abrir el buscador");
        menuBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBuscadorActionPerformed(evt);
            }
        });
        menuHerramientas.add(menuBuscador);

        jMenuBar1.add(menuHerramientas);

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/info.png"))); // NOI18N
        jMenu10.setText("Mostrar productos con bajo stock");
        jMenu10.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jMenu10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu10MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu10MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu10);

        jMenu8.setText("                   ");
        jMenuBar1.add(jMenu8);

        jMenu3.setText("jMenu3");
        jMenu3.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesActionPerformed
        Clientes cl = new Clientes();
        main.jDesktopPane.add(cl);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = main.jDesktopPane.getSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = cl.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        cl.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        controllerClientes.setOp("D");
        controllerClientes.setBusc("");
        controllerClientes.consultarClientes(dtm);
        cl.setUI(null);

        cl.setVisible(true);

    }//GEN-LAST:event_jMenuItemClientesActionPerformed

    private void menuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUsuariosActionPerformed

        usuarios usu = new usuarios();
        main.jDesktopPane.add(usu);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = main.jDesktopPane.getSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = usu.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        usu.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        controllerUsuarios.consultarSession(dtm, "", "");
        usu.setUI(null);
        usu.setVisible(true);
    }//GEN-LAST:event_menuUsuariosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void menuCompuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCompuestosActionPerformed
        Compuestos Comp = new Compuestos();
        this.jDesktopPane.add(Comp);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = this.jDesktopPane.getSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = Comp.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        Comp.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        controllerCompuestos.setOp("D");
        controllerCompuestos.setBusc("");
        controllerCompuestos.consultarCompuestos(dtm);
        //y para finalizar la hacemos visible
        Comp.setUI(null);
        Comp.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_menuCompuestosActionPerformed

    private void menuLaboratoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLaboratoriosActionPerformed
        Laboratorios Lab = new Laboratorios();
        this.jDesktopPane.add(Lab);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = this.jDesktopPane.getSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = Lab.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        Lab.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        //y para finalizar la hacemos visible
        controllerLaboratorio.setOp("D");
        controllerLaboratorio.setBusc("");
        controllerLaboratorio.consultarLaboratorio("laboratorio", dtm);
        Lab.setUI(null);
        Lab.setVisible(true);

    }//GEN-LAST:event_menuLaboratoriosActionPerformed

    private void menuMedicamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMedicamentoActionPerformed
        Medicamentos Med = new Medicamentos();
        this.jDesktopPane.add(Med);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = this.jDesktopPane.getSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = Med.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        Med.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        controllerMedicamentos.setOp("D");
        controllerMedicamentos.setBusc("");
        controllerMedicamentos.consultarMedicamentos("MEDIC", dtm);
        //y para finalizar la hacemos visible
        //table.setAutoResizeMode(JTable.AUTO_RESIZE_ON);
        Med.jTableMedicamentos.getColumnModel().getColumn(0).setPreferredWidth(100);

        Med.setUI(null);
        Med.setVisible(true);

    }//GEN-LAST:event_menuMedicamentoActionPerformed

    private void menuProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProveedoresActionPerformed
        Proveedores pr = new Proveedores();
        this.jDesktopPane.add(pr);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = this.jDesktopPane.getSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = pr.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        pr.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        //y para finalizar la hacemos visible
        controllerProveedores.setOp("D");
        controllerProveedores.setBusc("");
        controllerProveedores.consultarProveedor("PROVEE", dtm);
        pr.setUI(null);
        pr.setVisible(true);
    }//GEN-LAST:event_menuProveedoresActionPerformed

    private void menuPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPedidosActionPerformed
        PedidoInterno vnt = new PedidoInterno();
        this.jDesktopPane.add(vnt);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = this.jDesktopPane.getSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = vnt.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        vnt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        //y para finalizar la hacemos visible
        if (!main.lblpermisos.getText().equals("ADMIN")) {
            PedidoInterno.jMenuActualizar.setEnabled(false);
            PedidoInterno.jMenuAnular.setEnabled(false);
        }
        vnt.setUI(null);
        vnt.setVisible(true);
    }//GEN-LAST:event_menuPedidosActionPerformed

    private void RptVentasDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RptVentasDiaActionPerformed
        this.jDialog1.setLocationRelativeTo(this);
        this.jDialog1.setVisible(true);
    }//GEN-LAST:event_RptVentasDiaActionPerformed

    private void jMenuItemCompuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCompuestosActionPerformed
        BuscarMedicamentos Bm = new BuscarMedicamentos();
        this.jDesktopPane.add(Bm);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = this.jDesktopPane.getSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = Bm.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        Bm.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        //y para finalizar la hacemos visible
        BuscarMedicamentos.jTableComponentes.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        BuscarMedicamentos.jTableComponentes.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        Bm.setUI(null);
        Bm.setVisible(true);

    }//GEN-LAST:event_jMenuItemCompuestosActionPerformed

    private void menuComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuComprasActionPerformed
        Compras c = new Compras();
        this.jDesktopPane.add(c);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = this.jDesktopPane.getSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = c.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        c.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        //y para finalizar la hacemos visible
        c.setUI(null);
        Compras.jTableCompras.getColumnModel().getColumn(0).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        Compras.jTableCompras.getColumnModel().getColumn(1).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        Compras.jTableCompras.getColumnModel().getColumn(2).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        Compras.jTableCompras.getColumnModel().getColumn(3).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        Compras.jTableCompras.getColumnModel().getColumn(4).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        Compras.jTableCompras.getColumnModel().getColumn(5).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        Compras.jTableCompras.getColumnModel().getColumn(6).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        Compras.jTableCompras.getColumnModel().getColumn(7).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        Compras.jTableCompras.getColumnModel().getColumn(8).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        Compras.jTableCompras.getColumnModel().getColumn(9).setHeaderRenderer(new MyRenderer(Color.DARK_GRAY, Color.WHITE));
        c.setVisible(true);

    }//GEN-LAST:event_menuComprasActionPerformed

    private void txtfechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
            this.txtfecha.setText(null);
            this.jDialog1.dispose();
        }

    }//GEN-LAST:event_txtfechaKeyReleased

    private void txtFechaDesdeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaDesdeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaDesdeKeyReleased

    private void txtFechaHastaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaHastaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaHastaKeyReleased

    private void rptVentasDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptVentasDHActionPerformed
        this.jDialog2.setLocationRelativeTo(this);
        this.jDialog2.setVisible(true);
    }//GEN-LAST:event_rptVentasDHActionPerformed

    private void txtFechaDesde1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaDesde1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaDesde1KeyReleased

    private void txtFechaHasta1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaHasta1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaHasta1KeyReleased

    private void txtcodusuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodusuarioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodusuarioKeyReleased

    private void rptVentasXUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptVentasXUsuActionPerformed
        this.jDialog3.setLocationRelativeTo(this);
        this.jDialog3.setVisible(true);
    }//GEN-LAST:event_rptVentasXUsuActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        inicioSession i = new inicioSession();
        i.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void rptFiltradoXLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptFiltradoXLabActionPerformed
        this.jDialog4.setLocationRelativeTo(this);
        controllerLaboratorio.setOp("D");
        controllerLaboratorio.setBusc("");
        controllerLaboratorio.consultarLaboratorio("main", dtm);
        this.jDialog4.setVisible(true);
    }//GEN-LAST:event_rptFiltradoXLabActionPerformed

    private void rptInventarioCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptInventarioCActionPerformed
        Report rpt = new Report();
        String[] a = new String[1];
        a[0] = "op";
        rpt.generadorReportes("Report\\rptInventario.jasper", 1, a, "TODO");
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);

    }//GEN-LAST:event_rptInventarioCActionPerformed

    private void txtFechaDesde2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaDesde2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaDesde2KeyReleased

    private void txtFechaHasta2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaHasta2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaHasta2KeyReleased

    private void rptComprasDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptComprasDHActionPerformed
        this.jDialog5.setLocationRelativeTo(this);
        this.jDialog5.setVisible(true);
    }//GEN-LAST:event_rptComprasDHActionPerformed

    private void txtdocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdocKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdocKeyReleased

    private void rptComprasXDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptComprasXDocActionPerformed
        this.jDialog6.setLocationRelativeTo(this);
        this.jDialog6.setVisible(true);
    }//GEN-LAST:event_rptComprasXDocActionPerformed

    private void fechaInicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaInicioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicioKeyReleased

    private void fechaFinalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fechaFinalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinalKeyReleased

    private void medKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_medKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_medKeyReleased

    private void rptSeguimientoMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptSeguimientoMedActionPerformed
        this.jDialog7.setLocationRelativeTo(this);
        this.jDialog7.setVisible(true);
    }//GEN-LAST:event_rptSeguimientoMedActionPerformed

    private void RazonSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RazonSocialKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_RazonSocialKeyReleased

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        if (validator.novacios("3-11-5-5", 4, this.RazonSocial, this.Ruc, this.Direccion, this.Frase) == false) {
            RegistroDelSistema();

            main.jDialog8.dispose();

        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void DireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DireccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionKeyReleased

    private void DireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionActionPerformed

    private void RucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RucKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_RucKeyReleased

    private void FraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FraseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FraseActionPerformed

    private void FraseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FraseKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_FraseKeyReleased

    private void menuAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAjustesActionPerformed
        loader();

        this.jDialog12.setLocationRelativeTo(this);
        this.jDialog12.setVisible(true);

    }//GEN-LAST:event_menuAjustesActionPerformed

    private void RucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RucKeyTyped
        validator.solonumeros(evt, Ruc, 11);
    }//GEN-LAST:event_RucKeyTyped

    private void jMenu10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu10MouseClicked

    }//GEN-LAST:event_jMenu10MouseClicked

    private void jMenu10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu10MousePressed
        controllerMedicamentos.setOp("M");
        controllerMedicamentos.setBusc("");
        controllerMedicamentos.consultarMedicamentosStock(main.jEditorPaneStock);
        this.jMenu10.setSelected(false);
    }//GEN-LAST:event_jMenu10MousePressed

    private void btnguardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar1ActionPerformed
        this.jDialog8.dispose();
    }//GEN-LAST:event_btnguardar1ActionPerformed

    private void rptInventarioCSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptInventarioCSActionPerformed
        Report rpt = new Report();
        String[] a = new String[1];
        a[0] = "op";
        rpt.generadorReportes("Report\\rptInventario.jasper", 1, a, "CONSTOCK");
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);

    }//GEN-LAST:event_rptInventarioCSActionPerformed

    private void rptInventarioSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptInventarioSSActionPerformed
        Report rpt = new Report();
        String[] a = new String[1];
        a[0] = "op";
        rpt.generadorReportes("Report\\rptInventario.jasper", 1, a, "SINSTOCK");
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);

    }//GEN-LAST:event_rptInventarioSSActionPerformed

    private void rptInventarioEAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptInventarioEAActionPerformed
        Report rpt = new Report();
        String[] a = new String[1];
        a[0] = "op";
        rpt.generadorReportes("Report\\rptInventario.jasper", 1, a, "ALERTA");
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);

    }//GEN-LAST:event_rptInventarioEAActionPerformed

    private void labKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_labKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_labKeyReleased

    private void jMenu11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu11MousePressed
        Report rpt = new Report();
        String[] a = new String[4];
        a[0] = "fechaInicio";
        a[1] = "fechaFinal";
        a[2] = "med";
        a[3] = "lab";
        rpt.generadorReportes("Report\\rptSeguimiento.jasper", 4, a, this.fechaInicio.getText(), this.fechaFinal.getText(), this.med.getText(), this.lab.getText());
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);
        this.jDialog7.dispose();
        this.jMenu1.setSelected(false);
    }//GEN-LAST:event_jMenu11MousePressed

    private void jMenu14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu14MousePressed
        this.jDialog7.dispose();
    }//GEN-LAST:event_jMenu14MousePressed

    private void jMenuBar2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar2MouseDragged

    }//GEN-LAST:event_jMenuBar2MouseDragged

    private void jMenuBar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar2MouseClicked

    }//GEN-LAST:event_jMenuBar2MouseClicked

    private void jMenuBar2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar2MousePressed

    }//GEN-LAST:event_jMenuBar2MousePressed

    private void jMenu12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu12MousePressed
        int[] filas = this.jTableLaboratorios.getSelectedRows();
        System.out.println(filas.length);
        for (int i = 0; i < filas.length; i++) {

            System.out.println("seleciono :" + filas[i]);
            System.out.println("Con el codigo " + this.jTableLaboratorios.getValueAt(filas[i], 0).toString());
            Report rpt = new Report();
            String[] a = new String[2];
            a[0] = "op";
            a[1] = "lab";
            if (this.rdTodos.isSelected()) {
                rpt.generadorReportes("Report\\rptMedXLab.jasper", 2, a, "TODO", main.jTableLaboratorios.getValueAt(filas[i], 0).toString());
            }
            if (this.rdConStock.isSelected()) {
                rpt.generadorReportes("Report\\rptMedXLab.jasper", 2, a, "CONSTOCK", main.jTableLaboratorios.getValueAt(filas[i], 0).toString());

            }
            if (this.rdBajoStock.isSelected()) {
                rpt.generadorReportes("Report\\rptMedXLab.jasper", 2, a, "BAJOSTOCK", main.jTableLaboratorios.getValueAt(filas[i], 0).toString());

            }
            if (this.rdSinStock.isSelected()) {
                rpt.generadorReportes("Report\\rptMedXLab.jasper", 2, a, "SINSTOCK", main.jTableLaboratorios.getValueAt(filas[i], 0).toString());

            }
            this.jDesktopPane.add(rpt);
            Dimension pantalla = this.jDesktopPane.getSize();
            Dimension ventana = rpt.getSize();
            rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            rpt.setVisible(true);
        }

        this.jMenu1.setSelected(false);
        this.jDialog4.dispose();
    }//GEN-LAST:event_jMenu12MousePressed

    private void jMenu16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu16MousePressed
        this.jDialog4.dispose();
    }//GEN-LAST:event_jMenu16MousePressed

    private void jMenuBar3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar3MouseDragged

    }//GEN-LAST:event_jMenuBar3MouseDragged

    private void jMenuBar3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar3MouseClicked

    }//GEN-LAST:event_jMenuBar3MouseClicked

    private void jMenuBar3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar3MousePressed

    }//GEN-LAST:event_jMenuBar3MousePressed

    private void rdTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdTodosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdTodosActionPerformed

    private void rptActivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptActivosActionPerformed
        Report rpt = new Report();
        String[] a = new String[1];
        a[0] = "op";
        rpt.generadorReportes("Report\\rptActivos.jasper", 1, a, "CONSTOCK");
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);
    }//GEN-LAST:event_rptActivosActionPerformed

    private void jMenu13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu13MousePressed
        Report rpt = new Report();
        String[] a = new String[1];
        a[0] = "fecha";
        rpt.generadorReportes("Report\\rptVentasHoy.jasper", 1, a, this.txtfecha.getText());
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);
        this.jDialog1.dispose();
    }//GEN-LAST:event_jMenu13MousePressed

    private void jMenu15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu15MousePressed
        this.jDialog1.dispose();
    }//GEN-LAST:event_jMenu15MousePressed

    private void jMenuBar4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar4MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar4MouseDragged

    private void jMenuBar4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar4MouseClicked

    private void jMenuBar4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar4MousePressed

    private void jMenu15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu15ActionPerformed

    }//GEN-LAST:event_jMenu15ActionPerformed

    private void jMenu17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu17MousePressed
        Report rpt = new Report();
        String[] a = new String[2];
        a[0] = "fecha";
        a[1] = "hasta";
        //  rpt.generadorReportes("Report\\rptVentasDesdeHasta.jasper", 2, a, this.txtFechaDesde.getText(), this.txtFechaHasta.getText());
        rpt.generadorReportes("Report\\rptVentasDesdeHasta.jasper", 2, a, this.txtFechaDesde.getText(), this.txtFechaHasta.getText());
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);
        this.jDialog2.dispose();
    }//GEN-LAST:event_jMenu17MousePressed

    private void jMenu18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu18MousePressed
        this.jDialog2.dispose();
    }//GEN-LAST:event_jMenu18MousePressed

    private void jMenu18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu18ActionPerformed

    private void jMenuBar5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar5MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar5MouseDragged

    private void jMenuBar5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar5MouseClicked

    private void jMenuBar5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar5MousePressed

    private void jMenu19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu19MousePressed
        Report rpt = new Report();
        String[] a = new String[3];
        a[0] = "fecha";
        a[1] = "hasta";
        a[2] = "usu";
        rpt.generadorReportes("Report\\rptVentasUsuarios.jasper", 3, a, this.txtFechaDesde1.getText(), this.txtFechaHasta1.getText(), this.txtcodusuario.getText());
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);
        this.jDialog3.dispose();

    }//GEN-LAST:event_jMenu19MousePressed

    private void jMenu20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu20MousePressed
        this.jDialog3.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu20MousePressed

    private void jMenu20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu20ActionPerformed

    private void jMenuBar6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar6MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar6MouseDragged

    private void jMenuBar6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar6MouseClicked

    private void jMenuBar6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar6MousePressed

    private void txtanioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtanioKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtanioKeyReleased

    private void jMenu21MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu21MousePressed
        Report rpt = new Report();
        String[] a = new String[1];
        a[0] = "anio";
        rpt.generadorReportes("Report\\rptAnual.jasper", 1, a, this.txtanio.getText());
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);
        this.jDialog9.dispose();
    }//GEN-LAST:event_jMenu21MousePressed

    private void jMenu22MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu22MousePressed
        this.jDialog9.dispose();
    }//GEN-LAST:event_jMenu22MousePressed

    private void jMenu22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu22ActionPerformed

    private void jMenuBar7MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar7MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar7MouseDragged

    private void jMenuBar7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar7MouseClicked

    private void jMenuBar7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar7MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar7MousePressed

    private void rptVentasAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptVentasAnioActionPerformed
        this.jDialog9.setLocationRelativeTo(this);
        this.jDialog9.setVisible(true);
    }//GEN-LAST:event_rptVentasAnioActionPerformed

    private void rptComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rptComprasActionPerformed
        Report rpt = new Report();
        String[] a = new String[1];
        a[0] = "op";
        rpt.generadorReportes("Report\\rptCompras.jasper", 1, a, "TODO");
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);
    }//GEN-LAST:event_rptComprasActionPerformed

    private void jMenu23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu23MousePressed
        Report rpt = new Report();
        String[] a = new String[2];
        a[0] = "fechaInicio";
        a[1] = "fechaFin";
        rpt.generadorReportes("Report\\rptComprasDesdeHasta.jasper", 2, a, this.txtFechaDesde2.getText(), this.txtFechaHasta2.getText());
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);
        this.jDialog5.dispose();

    }//GEN-LAST:event_jMenu23MousePressed

    private void jMenu24MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu24MousePressed
        this.jDialog5.dispose();
    }//GEN-LAST:event_jMenu24MousePressed

    private void jMenu24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu24ActionPerformed

    private void jMenuBar8MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar8MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar8MouseDragged

    private void jMenuBar8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar8MouseClicked

    private void jMenuBar8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar8MousePressed

    private void jMenu25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu25MousePressed
        Report rpt = new Report();
        String[] a = new String[1];
        a[0] = "doc";
        rpt.generadorReportes("Report\\rptComprasXDoc.jasper", 1, a, this.txtdoc.getText());
        this.jDesktopPane.add(rpt);
        Dimension pantalla = this.jDesktopPane.getSize();
        Dimension ventana = rpt.getSize();
        rpt.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        rpt.setVisible(true);
        this.jDialog6.dispose();

    }//GEN-LAST:event_jMenu25MousePressed

    private void jMenu26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu26MousePressed
        this.jDialog6.dispose();
    }//GEN-LAST:event_jMenu26MousePressed

    private void jMenu26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu26ActionPerformed

    private void jMenuBar9MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar9MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar9MouseDragged

    private void jMenuBar9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar9MouseClicked

    private void jMenuBar9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar9MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar9MousePressed

    private void impresoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impresoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_impresoraActionPerformed

    private void impresoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_impresoraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_impresoraKeyReleased

    private void menuAjustes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAjustes1ActionPerformed
        this.jDialog10.setLocationRelativeTo(this);
        this.jDialog10.setVisible(true);
    }//GEN-LAST:event_menuAjustes1ActionPerformed

    private void btncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncerrarActionPerformed
        this.jDialog10.dispose();
    }//GEN-LAST:event_btncerrarActionPerformed

    private void menuAjustes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAjustes2ActionPerformed
        Conexion.setDb("null");
        Conexion.setHost("null");
        Conexion.setUser("null");
        Conexion.setPwd("PasswordSistema");
        Conexion.WriteConn();
        JOptionPane.showMessageDialog(this, "El sistema se reiniciara para ingresar algunos datos.", "...:::Informacion del sistema:::...", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }//GEN-LAST:event_menuAjustes2ActionPerformed

    private void menuBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBuscadorActionPerformed
        Buscador cl = new Buscador();
        main.jDesktopPane.add(cl);
        //este metodo devuelve el tamaño de la pantalla
        Dimension pantalla = main.jDesktopPane.getSize();
        //obtenemos el tamaño de la ventana
        Dimension ventana = cl.getSize();
        //para centrar la ventana lo hacemos con el siguiente calculo
        cl.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
        // controllerClientes.setOp("D");
        // controllerClientes.setBusc("");
        // controllerClientes.consultarClientes(dtm);
        cl.setUI(null);

        cl.setVisible(true);

    }//GEN-LAST:event_menuBuscadorActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged

    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (this.jCheckBox1.isSelected()) {
            FileDialog dialogoArchivo;
            dialogoArchivo = new FileDialog(this, "Seleccionar la localizacion de la ruta del sistema", FileDialog.LOAD);
            dialogoArchivo.setVisible(true);
            if (dialogoArchivo.getFile() != null) { /* Validar que se haya Seleccionado un Archivo*/

                String directorio = dialogoArchivo.getDirectory();
                String nombreArchivo = dialogoArchivo.getFile();
                String rutatotal = directorio + nombreArchivo;
                String ruta = new File(dialogoArchivo.getFile()).getAbsolutePath();
                //JOptionPane.showMessageDialog(this, ruta);
                JOptionPane.showMessageDialog(this, rutatotal);
            } else {
                System.out.println("No Seleccionó Archivo");
            }
//JOptionPane.showMessageDialog(this,"Seleccionado puto");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jDialog11WindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog11WindowOpened
        // AQUI NO
    }//GEN-LAST:event_jDialog11WindowOpened

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // DA VIDEO DESPUES DE ABRIR EL FORM
    }//GEN-LAST:event_formWindowOpened

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        // TAMPOCO
    }//GEN-LAST:event_formWindowLostFocus

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowStateChanged

    private void formWindowDeiconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeiconified
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowDeiconified

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void jDialog11WindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog11WindowActivated
        inicioSession.inicio();         // TODO add your handling code here:
    }//GEN-LAST:event_jDialog11WindowActivated

    private void btnGuardarPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPermisosActionPerformed

    }//GEN-LAST:event_btnGuardarPermisosActionPerformed

    private void jDialog8WindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog8WindowActivated
//preferencias.privilegiosQuery("dpch");  

    }//GEN-LAST:event_jDialog8WindowActivated

    private void jDialog12WindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog12WindowActivated
        preferencias.privilegiosQuery(this.lblPermisos.getText());        // TODO add your handling code here:
        System.out.println("estoy asi actualmente: " + this.lblPermisos.getText());
    }//GEN-LAST:event_jDialog12WindowActivated

    private void jDialog12WindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog12WindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_jDialog12WindowOpened

    private void jDialog8WindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog8WindowOpened
//       this.jDialog12.setLocationRelativeTo(this);
//        this.jDialog12.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jDialog8WindowOpened

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        preferencias.preferenciasSave("suprv");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jDialog12WindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog12WindowClosed
        jDialog8.setLocationRelativeTo(this);
        jDialog8.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jDialog12WindowClosed

    private void cmbPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPermisosActionPerformed
   if (this.cmbPermisos.getSelectedIndex() == 0) {
            this.lblPermisos.setText("suprv");
        } else {
            this.lblPermisos.setText("dpch");
   } 
   this.PaneReportes.setEnabled(false);
   preferencias.privilegiosQuery(this.lblPermisos.getText());
  // this.PaneReportes.setEnabled(true);
    }//GEN-LAST:event_cmbPermisosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Direccion;
    private javax.swing.JTextField Frase;
    private javax.swing.JPanel PaneFormularios;
    private javax.swing.JPanel PaneReportes;
    private javax.swing.JTextField RazonSocial;
    public static javax.swing.JMenuItem RptVentasDia;
    private javax.swing.JTextField Ruc;
    private javax.swing.JButton btnGuardarPermisos;
    private javax.swing.JButton btncerrar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnguardar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public static javax.swing.JCheckBox chkBuscador;
    public static javax.swing.JCheckBox chkBuscarXCompuest;
    public static javax.swing.JCheckBox chkClientes;
    public static javax.swing.JCheckBox chkCompras;
    public static javax.swing.JCheckBox chkCompuestos;
    public static javax.swing.JCheckBox chkLaborMarcas;
    public static javax.swing.JCheckBox chkMedOProds;
    public static javax.swing.JCheckBox chkPedidosInternos;
    public static javax.swing.JCheckBox chkProveedores;
    public static javax.swing.JCheckBox chkRptActivos;
    public static javax.swing.JCheckBox chkRptCompras;
    public static javax.swing.JCheckBox chkRptComprasDH;
    public static javax.swing.JCheckBox chkRptComprasXDoc;
    public static javax.swing.JCheckBox chkRptConStock;
    public static javax.swing.JCheckBox chkRptEstAlert;
    public static javax.swing.JCheckBox chkRptFiltradXLab;
    public static javax.swing.JCheckBox chkRptInvComplt;
    public static javax.swing.JCheckBox chkRptSinStock;
    public static javax.swing.JCheckBox chkRptVentaAnio;
    public static javax.swing.JCheckBox chkRptVentasDH;
    public static javax.swing.JCheckBox chkRptVentasDUsuario;
    public static javax.swing.JCheckBox chkRptVentasDia;
    public static javax.swing.JCheckBox chkSegimitMedic;
    public static javax.swing.JCheckBox chkUsuarios;
    private javax.swing.JComboBox cmbPermisos;
    private javax.swing.JTextField fechaFinal;
    private javax.swing.JTextField fechaInicio;
    private javax.swing.JTextField impresora;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    public static javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog10;
    public static javax.swing.JDialog jDialog11;
    public static javax.swing.JDialog jDialog12;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JDialog jDialog6;
    private javax.swing.JDialog jDialog7;
    public static javax.swing.JDialog jDialog8;
    private javax.swing.JDialog jDialog9;
    public static javax.swing.JEditorPane jEditorPaneAdvertencias;
    public static javax.swing.JEditorPane jEditorPaneStock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu20;
    private javax.swing.JMenu jMenu21;
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu23;
    private javax.swing.JMenu jMenu24;
    private javax.swing.JMenu jMenu25;
    private javax.swing.JMenu jMenu26;
    private javax.swing.JMenu jMenu3;
    public static javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    public static javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JMenuBar jMenuBar7;
    private javax.swing.JMenuBar jMenuBar8;
    private javax.swing.JMenuBar jMenuBar9;
    public static javax.swing.JMenuItem jMenuItemClientes;
    public static javax.swing.JMenuItem jMenuItemCompuestos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    public static javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public static javax.swing.JProgressBar jProgressBar1;
    public static javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTable jTableLaboratorios;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField lab;
    private javax.swing.JLabel lblPermisos;
    public static javax.swing.JLabel lblcodigo;
    public static javax.swing.JLabel lblpermisos;
    public static javax.swing.JLabel lblsesion;
    public static javax.swing.JLabel lblsesion1;
    public static javax.swing.JLabel lblusuario;
    public static javax.swing.JLabel logo;
    private javax.swing.JTextField med;
    public static javax.swing.JMenuItem menuAjustes;
    public static javax.swing.JMenuItem menuAjustes1;
    public static javax.swing.JMenuItem menuAjustes2;
    public static javax.swing.JMenuItem menuBuscador;
    private javax.swing.JMenu menuClientes;
    public static javax.swing.JMenuItem menuCompras;
    public static javax.swing.JMenuItem menuCompuestos;
    public static javax.swing.JMenu menuHerramientas;
    public static javax.swing.JMenuItem menuLaboratorios;
    public static javax.swing.JMenuItem menuMedicamento;
    public static javax.swing.JMenuItem menuPedidos;
    public static javax.swing.JMenuItem menuProveedores;
    public static javax.swing.JMenu menuReportes;
    public static javax.swing.JMenuItem menuUsuarios;
    private javax.swing.JRadioButton rdBajoStock;
    private javax.swing.JRadioButton rdConStock;
    private javax.swing.JRadioButton rdFarmaceutica;
    private javax.swing.JRadioButton rdSinStock;
    private javax.swing.JRadioButton rdTodos;
    private javax.swing.JRadioButton rdVentas;
    public static javax.swing.JMenuItem rptActivos;
    public static javax.swing.JMenuItem rptCompras;
    public static javax.swing.JMenuItem rptComprasDH;
    public static javax.swing.JMenuItem rptComprasXDoc;
    public static javax.swing.JMenuItem rptFiltradoXLab;
    public static javax.swing.JMenuItem rptInventarioC;
    public static javax.swing.JMenuItem rptInventarioCS;
    public static javax.swing.JMenuItem rptInventarioEA;
    public static javax.swing.JMenuItem rptInventarioSS;
    public static javax.swing.JMenuItem rptSeguimientoMed;
    public static javax.swing.JMenuItem rptVentasAnio;
    public static javax.swing.JMenuItem rptVentasDH;
    public static javax.swing.JMenuItem rptVentasXUsu;
    private javax.swing.JTextField txtFechaDesde;
    private javax.swing.JTextField txtFechaDesde1;
    private javax.swing.JTextField txtFechaDesde2;
    private javax.swing.JTextField txtFechaHasta;
    private javax.swing.JTextField txtFechaHasta1;
    private javax.swing.JTextField txtFechaHasta2;
    private javax.swing.JTextField txtanio;
    private javax.swing.JTextField txtcodusuario;
    private javax.swing.JTextField txtdoc;
    private javax.swing.JTextField txtfecha;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyPressed(KeyEvent e) {
        if (KeyEvent.VK_F5 == e.getKeyCode()) {
            JOptionPane.showMessageDialog(null, "Bien ahi");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
