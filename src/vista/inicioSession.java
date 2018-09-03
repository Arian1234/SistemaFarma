package vista;

import at.jta.Key;
import at.jta.RegistryErrorException;
import at.jta.Regor;
import controlador.controllerUsuarios;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Conexion;

/**
 *
 * @author AMolinaDeveloper
 */
public final class inicioSession extends javax.swing.JFrame {

    //  private static String estado;
    private static Preferences prefs;
    private int x;
    public static boolean band = false;
    private int tiempo = 5;
    private static String permisos;

    /**
     * Creates new form inicioSession
     */
    public inicioSession() {
        this.setUndecorated(true);//quita bordes a jframe
        initComponents();
        consultarConexion();

        new always().block(this);
        this.txtusuusu.requestFocus();

    }

//    public void block() {
//        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//        scheduler.scheduleAtFixedRate(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        front();
//                    }
//                }, 500, 50, TimeUnit.MILLISECONDS); //comienza dentro de 1/2 segundo y luego se repite cada N segundos
//    }
    /**
     *
     */
    public void front() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);//maximizado
        this.toFront();
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("vista/img/grafico.png"));

        return retValue;
    }

    public void consultarConexion() {
        if (Conexion.Conexion() == null) {
            System.out.println("Sin conexion...");

            this.jDialog1.setLocationRelativeTo(null);
            this.jTabbedPane1.setEnabledAt(0, true);
            this.jDialog1.setVisible(true);
        } else {

            System.out.println("Conectado...");

        }
        this.btningresar.requestFocus();
    }

    public static void inicio() {
//Creamos un Thread para mejorar el ejemplo
        final Thread t;
        //Inicializamos
        t = new Thread(new Runnable() {
            // INICIAMOS LAS PREFERENCIAS
            //Implementamos el método run()
            @Override
            public void run() {

                try {
                    if (permisos.equals("ADMIN")) {
                        System.out.println("soy un pinche admin");
                        main.jDialog11.dispose();
                    } else {
                        main.menuAjustes.setEnabled(false);
                        String tipoUsuario = permisos.equals("SUPER") ? "suprv" : "dpch";
                        System.out.println(tipoUsuario);

                        Regor reg = null;
                        Key key = null;
                        String SBK = "Software\\MySoftware\\config\\" + tipoUsuario;
                        reg = new Regor();
                        key = reg.openKey(Regor.HKEY_CURRENT_USER, SBK);
                        //Permite mostrar el valor del progreso
                        main.jProgressBar1.setStringPainted(true);
                        int x = 1;
                        //Utilizamos un while para emular el valor mínimo y máximo
                        //En este caso 0 - 100
                        while (x <= 100) {
                            main.jProgressBar1.setValue(x);
                            main.jProgressBar1.setString(x + "%");

                            switch (x) {
                                case 1:
                                    main.jMenuItemClientes.setEnabled(reg.readAnyValueString(key, "clie").equals("Y"));
                                    main.lblsesion.setText("cargando configuraciones del sistema.");
                                    break;
                                case 4:
                                    main.menuMedicamento.setEnabled(reg.readAnyValueString(key, "prd").equals("Y"));
                                    main.lblsesion.setText("cargando configuraciones del sistema..");
                                    break;
                                case 8:
                                    main.menuCompuestos.setEnabled(reg.readAnyValueString(key, "cmpst").equals("Y"));
                                    main.lblsesion.setText("cargando configuraciones del sistema...");
                                    break;
                                case 12:
                                    main.menuLaboratorios.setEnabled(reg.readAnyValueString(key, "mrc").equals("Y"));
                                    main.lblsesion.setText("cargando configuraciones del sistema...");
                                    break;
                                //
                                //
                                //
                                case 16:
                                    main.menuProveedores.setEnabled(reg.readAnyValueString(key, "prvee").equals("Y"));
                                    main.lblsesion.setText("cargando datos del sistema.");
                                    break;
                                case 20:
                                    main.menuUsuarios.setEnabled(reg.readAnyValueString(key, "usurs").equals("Y"));
                                    main.lblsesion.setText("cargando datos del sistema..");
                                    break;
                                case 24:
                                    main.menuPedidos.setEnabled(reg.readAnyValueString(key, "pdds").equals("Y"));
                                    main.lblsesion.setText("cargando datos del sistema...");
                                    break;
                                case 28:
                                    main.menuCompras.setEnabled(reg.readAnyValueString(key, "cmps").equals("Y"));
                                    main.lblsesion.setText("cargando datos del sistema...");
                                    break;
                                //
                                //
                                //
                                case 32:
                                    main.jMenuItemCompuestos.setEnabled(reg.readAnyValueString(key, "Bxcmpst").equals("Y"));
                                    main.lblsesion.setText("cargando reportes.");
                                    break;
                                case 36:
                                    main.menuBuscador.setEnabled(reg.readAnyValueString(key, "bscdr").equals("Y"));
                                    main.lblsesion.setText("cargando reportes..");
                                    break;
                                case 40:
                                    main.rptActivos.setEnabled(reg.readAnyValueString(key, "rptactv").equals("Y"));
                                    main.lblsesion.setText("cargando reportes...");
                                    break;
                                case 44:
                                    main.rptInventarioC.setEnabled(reg.readAnyValueString(key, "rptinvt").equals("Y"));
                                    main.lblsesion.setText("cargando reportes...");
                                    break;
                                //
                                //
                                //
                                case 48:
                                    main.rptInventarioCS.setEnabled(reg.readAnyValueString(key, "rptinvtcstck").equals("Y"));
                                    main.lblsesion.setText("cargando interfaces del usuario.");
                                    break;
                                case 52:
                                    main.rptInventarioSS.setEnabled(reg.readAnyValueString(key, "rptinvtsstck").equals("Y"));
                                    main.lblsesion.setText("cargando interfaces del usuario..");
                                    break;
                                case 56:
                                    main.rptInventarioEA.setEnabled(reg.readAnyValueString(key, "rptinvtedalrt").equals("Y"));
                                    main.lblsesion.setText("cargando interfaces del usuario...");
                                    break;
                                case 60:
                                    main.rptFiltradoXLab.setEnabled(reg.readAnyValueString(key, "rptinvtFxlabrt").equals("Y"));
                                    main.lblsesion.setText("cargando interfaces del usuario...");
                                    break;

                                //
                                //
                                //
                                case 64:
                                    main.rptSeguimientoMed.setEnabled(reg.readAnyValueString(key, "rptsdmedmts").equals("Y"));
                                    main.lblsesion.setText("probando si la conexion es estable.");
                                    break;
                                case 68:
                                    main.rptVentasAnio.setEnabled(reg.readAnyValueString(key, "rptvntsanio").equals("Y"));
                                    main.lblsesion.setText("probando si la conexion es estable..");
                                    break;
                                case 72:
                                    main.rptVentasDH.setEnabled(reg.readAnyValueString(key, "rptvntDH").equals("Y"));
                                    main.lblsesion.setText("probando si la conexion es estable...");
                                    break;
                                case 76:
                                    main.RptVentasDia.setEnabled(reg.readAnyValueString(key, "rptVDD").equals("Y"));
                                    main.lblsesion.setText("probando si la conexion es estable...");
                                    break;
                                //
                                //
                                //
                                case 80:
                                    main.rptVentasXUsu.setEnabled(reg.readAnyValueString(key, "RptvntDHxUsrio").equals("Y"));
                                    main.lblsesion.setText("probando si la conexion es estable...");
                                    break;
                                case 84:
                                    main.rptCompras.setEnabled(reg.readAnyValueString(key, "rptCrealzd").equals("Y"));
                                    main.lblsesion.setText("Estamos listos.");
                                    break;
                                case 88:
                                    main.rptComprasDH.setEnabled(reg.readAnyValueString(key, "rptCDH").equals("Y"));
                                    main.lblsesion.setText("Estamos listos.");
                                    break;
                                case 92:
                                    main.rptComprasXDoc.setEnabled(reg.readAnyValueString(key, "prtCxdcmt").equals("Y"));
                                    main.lblsesion.setText("Estamos listos.");
                                    break;
                            }
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                            }
                           
                            x++;
                        }
                        main.jDialog11.dispose();
                    }
                } catch (RegistryErrorException ex) {
                    Logger.getLogger(inicioSession.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        //Se ejecuta el Thread
        t.start();
    }

    public void ingresar() {
        try {
            String pwd = new String(this.txtpwdusu.getPassword());
            controllerUsuarios.setBusc("");
            controllerUsuarios.setOp("L");
            controllerUsuarios.setUser(this.txtusuusu.getText());
            controllerUsuarios.setPwd(pwd);
            ResultSet rs = controllerUsuarios.consultarSession();
            if (rs.next()) {
                System.out.println("Accediste al ResultSet");
                // this.toBack();
                // JOptionPane.showMessageDialog(null, "Acaba de acceder al sistema \n " + rs.getString("NOMBRE"), "Bienvenido ", JOptionPane.INFORMATION_MESSAGE);
                this.lblmessage.setText("Acaba de acceder al sistema.");

                permisos = (rs.getString("PERMISOS"));
                System.out.println("aqui si me place " + permisos);
                main m = new main();

                m.lblcodigo.setText(rs.getString("###"));
                m.lblusuario.setText(rs.getString("NOMBRE"));
                m.lblpermisos.setText(rs.getString("PERMISOS"));

                this.dispose();
                m.setVisible(true);

            } else {
                //this.toBack();
                // JOptionPane.showMessageDialog(null, "Usted no tiene acceso al sistema.", "Error al solicitar acceso", JOptionPane.ERROR_MESSAGE);
                this.lblmessage.setText("A ocurrido un error al intentar acceder al sistema,por favor verificar el usuario y la contrasena.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicioSession.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rdtodo = new javax.swing.JRadioButton();
        rdprueba = new javax.swing.JRadioButton();
        btningresar1 = new javax.swing.JButton();
        btncancelar1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        RazonSocial = new javax.swing.JTextField();
        Direccion = new javax.swing.JTextField();
        Ruc = new javax.swing.JTextField();
        Frase = new javax.swing.JTextField();
        impresora = new javax.swing.JTextField();
        btnregistrar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        txtusuarioregister = new javax.swing.JTextField();
        txthostregister = new javax.swing.JTextField();
        txtdatabaseregister = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jPanel18 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        rdVentas = new javax.swing.JRadioButton();
        rdFarmaceutica = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jDialog2 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtusuusu = new javax.swing.JTextField();
        txtpwdusu = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        btningresar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        lblmessage = new javax.swing.JLabel();

        jDialog1.setTitle("Registrar nueva conexion");
        jDialog1.setAlwaysOnTop(true);
        jDialog1.setMinimumSize(new java.awt.Dimension(886, 583));
        jDialog1.setModal(true);
        jDialog1.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDialog1.setUndecorated(true);
        jDialog1.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(204, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        jTabbedPane1.setEnabled(false);
        jTabbedPane1.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 51));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 36)); // NOI18N
        jLabel1.setText("Desea activar todas las funciones del sistema");

        rdtodo.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroup1.add(rdtodo);
        rdtodo.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        rdtodo.setText("Deseo tener todas las funciones.");
        rdtodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdtodoActionPerformed(evt);
            }
        });

        rdprueba.setBackground(new java.awt.Color(255, 255, 153));
        buttonGroup1.add(rdprueba);
        rdprueba.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        rdprueba.setText("Solo deseo probarlo.");

        btningresar1.setFont(new java.awt.Font("Calibri Light", 0, 20)); // NOI18N
        btningresar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/okas.png"))); // NOI18N
        btningresar1.setText("Siguiente");
        btningresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btningresar1ActionPerformed(evt);
            }
        });

        btncancelar1.setFont(new java.awt.Font("Calibri Light", 0, 20)); // NOI18N
        btncancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Closes.png"))); // NOI18N
        btncancelar1.setText("Salir de la configuracion");
        btncancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdtodo)
                .addGap(184, 184, 184)
                .addComponent(rdprueba)
                .addContainerGap(131, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(btncancelar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btningresar1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(68, 68, 68))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdtodo)
                    .addComponent(rdprueba))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btningresar1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btncancelar1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jTabbedPane1.addTab("Activacion", jPanel4);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 1, true), "Registre los datos de su empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 1, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        RazonSocial.setName("RazonSocial");
        RazonSocial.setBackground(new java.awt.Color(255, 153, 255));
        RazonSocial.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        RazonSocial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RazonSocial.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Razón social de la empresa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        RazonSocial.setCaretColor(new java.awt.Color(51, 204, 255));
        RazonSocial.setNextFocusableComponent(Ruc);
        RazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RazonSocialKeyReleased(evt);
            }
        });

        Direccion.setName("Direccion");
        Direccion.setBackground(new java.awt.Color(255, 153, 255));
        Direccion.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Direccion.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Dirección de la empresa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Direccion.setCaretColor(new java.awt.Color(51, 204, 255));
        Direccion.setNextFocusableComponent(Frase);
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
        Ruc.setNextFocusableComponent(Direccion);
        Ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                RucKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RucKeyTyped(evt);
            }
        });

        Frase.setName("Frase");
        Frase.setBackground(new java.awt.Color(255, 204, 204));
        Frase.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Frase.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Frase.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Frases en pie del ticket`s", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
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
        impresora.setBackground(new java.awt.Color(255, 153, 153));
        impresora.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        impresora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        impresora.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Nombre de la Impresora", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
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

        btnregistrar.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        btnregistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Apply.png"))); // NOI18N
        btnregistrar.setText("Registrar ");
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/Close.png"))); // NOI18N
        btncancelar.setText("Volver");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        RazonSocial.setName("RazonSocial");
        txtusuarioregister.setBackground(new java.awt.Color(255, 255, 204));
        txtusuarioregister.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtusuarioregister.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtusuarioregister.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtusuarioregister.setCaretColor(new java.awt.Color(51, 204, 255));
        txtusuarioregister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtusuarioregisterKeyReleased(evt);
            }
        });

        txtpwdregister.setBackground(new java.awt.Color(255, 255, 153));
        txtpwdregister.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtpwdregister.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Contraseña", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtpwdregister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpwdregisterKeyPressed(evt);
            }
        });

        RazonSocial.setName("RazonSocial");
        txthostregister.setBackground(new java.awt.Color(255, 255, 204));
        txthostregister.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txthostregister.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txthostregister.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Host", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txthostregister.setCaretColor(new java.awt.Color(51, 204, 255));
        txthostregister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txthostregisterKeyReleased(evt);
            }
        });

        RazonSocial.setName("RazonSocial");
        txtdatabaseregister.setBackground(new java.awt.Color(255, 255, 153));
        txtdatabaseregister.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtdatabaseregister.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdatabaseregister.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Database", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtdatabaseregister.setCaretColor(new java.awt.Color(51, 204, 255));
        txtdatabaseregister.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdatabaseregisterKeyReleased(evt);
            }
        });

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true), "PERMISOS DE LOS USUARIOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        jPanel19.setBackground(new java.awt.Color(255, 255, 204));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Reportes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        jCheckBox12.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox12.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox12.setText("Reportes de activos de la entidad");

        jCheckBox13.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox13.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox13.setText("Reporte de inventario[COMPLETO]");

        jCheckBox22.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox22.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox22.setText("Reporte de inventario[CON STOCK]");

        jCheckBox23.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox23.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox23.setText("Reporte de inventario[SIN STOCK]");

        jCheckBox24.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox24.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox24.setText("Reporte de inventario[ESTADO ALERTA]");

        jCheckBox25.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox25.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox25.setText("Reporte de inventario filtrado por laboratorio");

        jCheckBox26.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox26.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox26.setText("Seguimiento de medicamentos");

        jCheckBox27.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox27.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox27.setText("Ventas del año");

        jCheckBox17.setBackground(new java.awt.Color(255, 153, 51));
        jCheckBox17.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox17.setText("Ventas desde-hasta");

        jCheckBox16.setBackground(new java.awt.Color(255, 153, 51));
        jCheckBox16.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox16.setText("Ventas del dia");

        jCheckBox15.setBackground(new java.awt.Color(255, 153, 51));
        jCheckBox15.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox15.setText("Ventas desde-hasta por USUARIOS");

        jCheckBox18.setBackground(new java.awt.Color(255, 255, 102));
        jCheckBox18.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox18.setText("Compras realizadas");

        jCheckBox20.setBackground(new java.awt.Color(255, 255, 102));
        jCheckBox20.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox20.setText("Compras por documento");

        jCheckBox19.setBackground(new java.awt.Color(255, 255, 102));
        jCheckBox19.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox19.setText("Compras desde-hasta");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox27)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox25)
                            .addComponent(jCheckBox26)
                            .addComponent(jCheckBox24, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox12)
                            .addComponent(jCheckBox13)
                            .addComponent(jCheckBox22, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox17)
                            .addComponent(jCheckBox16)
                            .addComponent(jCheckBox15)
                            .addComponent(jCheckBox19)
                            .addComponent(jCheckBox20)
                            .addComponent(jCheckBox18))))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jCheckBox12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox27))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jCheckBox17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox15)
                        .addGap(13, 13, 13)
                        .addComponent(jCheckBox18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox20)))
                .addGap(116, 116, 116))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 204));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Formularios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        jCheckBox2.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox2.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox2.setText("Clientes");

        jCheckBox3.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox3.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox3.setText("Medicamentos o productos");

        jCheckBox4.setBackground(new java.awt.Color(255, 51, 51));
        jCheckBox4.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox4.setText("Compuestos(**)");

        jCheckBox5.setBackground(new java.awt.Color(255, 153, 51));
        jCheckBox5.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox5.setText("Usuarios");

        jCheckBox6.setBackground(new java.awt.Color(255, 153, 51));
        jCheckBox6.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox6.setText("Proveedores");

        jCheckBox7.setBackground(new java.awt.Color(255, 153, 51));
        jCheckBox7.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox7.setText("Laboratorio o marca");

        jCheckBox8.setBackground(new java.awt.Color(255, 255, 102));
        jCheckBox8.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox8.setText("Generar pedido interno");

        jCheckBox9.setBackground(new java.awt.Color(255, 255, 102));
        jCheckBox9.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox9.setText("Registrar compras realizadas");

        jCheckBox10.setBackground(new java.awt.Color(255, 255, 102));
        jCheckBox10.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox10.setText("Buscar por compuestos(**)");

        jCheckBox11.setBackground(new java.awt.Color(0, 204, 51));
        jCheckBox11.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jCheckBox11.setText("Abrir el buscador");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox11))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox4)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox5)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox10)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox11))
        );

        jLabel12.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jLabel12.setText("** Solo en modo farmaceutico.");

        jPanel16.setBackground(new java.awt.Color(255, 255, 102));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adaptar el sistema para un entidad de tipo:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N

        rdVentas.setBackground(new java.awt.Color(255, 255, 102));
        rdVentas.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rdVentas.setText("Ventas o comercio");

        rdFarmaceutica.setBackground(new java.awt.Color(255, 255, 102));
        rdFarmaceutica.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        rdFarmaceutica.setSelected(true);
        rdFarmaceutica.setText("Farmaceutica");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addComponent(rdFarmaceutica)
                .addGap(41, 41, 41)
                .addComponent(rdVentas)
                .addGap(189, 189, 189))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rdVentas)
                .addComponent(rdFarmaceutica))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(104, 104, 104))
        );

        jComboBox1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SUPERVISOR", "DESPACHADOR" }));

        jButton3.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jButton3.setText("GUARDAR PERMISOS");

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtusuarioregister, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                    .addComponent(Ruc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(impresora, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(txtpwdregister))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txthostregister, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdatabaseregister, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RazonSocial))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(Direccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Frase, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(0, 388, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(237, 237, 237)
                        .addComponent(btncancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnregistrar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18))))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtusuarioregister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpwdregister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txthostregister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdatabaseregister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(impresora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Frase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar)
                    .addComponent(btnregistrar)
                    .addComponent(jLabel4)))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(93, 93, 93)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(61, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Registrar los datos solicitados", jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialog2.setAlwaysOnTop(true);
        jDialog2.setMinimumSize(new java.awt.Dimension(400, 102));
        jDialog2.setUndecorated(true);

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addContainerGap(334, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ingrese su usuario & contraseña");
        setBackground(new java.awt.Color(102, 102, 255));
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(352, 158));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jPanel1.setMinimumSize(new java.awt.Dimension(352, 197));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Inicio de sesión", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Calibri Light", 0, 13), new java.awt.Color(51, 51, 51))); // NOI18N
        jPanel2.setFont(new java.awt.Font("Britannic Bold", 0, 14)); // NOI18N

        txtusuusu.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        txtusuusu.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(87, 177, 227), 1, true), "Usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 14))); // NOI18N
        txtusuusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuusuActionPerformed(evt);
            }
        });

        txtpwdusu.setFont(new java.awt.Font("Calibri Light", 0, 13)); // NOI18N
        txtpwdusu.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(87, 177, 227), 1, true), "Contraseña", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 14))); // NOI18N
        txtpwdusu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpwdusuKeyReleased(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/red.png"))); // NOI18N

        btningresar.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        btningresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/okas.png"))); // NOI18N
        btningresar.setText("Ingresar al sistema");
        btningresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btningresarActionPerformed(evt);
            }
        });

        btnsalir.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/salir.png"))); // NOI18N
        btnsalir.setText("Salir de sistema");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btningresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnsalir))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtpwdusu, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtusuusu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(2, 2, 2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtusuusu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpwdusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btningresar)
                    .addComponent(btnsalir)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        lblmessage.setFont(new java.awt.Font("Calibri Light", 0, 20)); // NOI18N
        lblmessage.setForeground(new java.awt.Color(255, 255, 255));
        lblmessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblmessage.setText("Por favor ingrese su usuario y contrasena");
        lblmessage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblmessage.setMaximumSize(new java.awt.Dimension(51, 23));
        lblmessage.setMinimumSize(new java.awt.Dimension(51, 23));
        lblmessage.setPreferredSize(new java.awt.Dimension(51, 23));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(289, 289, 289)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblmessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(273, 273, 273))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(lblmessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresarActionPerformed
        ingresar();
    }//GEN-LAST:event_btningresarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed
        if (this.txtdatabaseregister.getText().length() > 0 && this.txthostregister.getText().length() > 0 && this.txtusuarioregister.getText().length() > 0
                && this.txtpwdregister.getText().length() > 0) {

            String password = new String(this.txtpwdregister.getPassword());

            Conexion.setDb(this.txtdatabaseregister.getText());
            Conexion.setHost(this.txthostregister.getText());
            Conexion.setUser(this.txtusuarioregister.getText());
            Conexion.setPwd(password);
            Conexion.WriteConn();

            prefs = Preferences.userRoot().node("configuracion");
            prefs.get("direccion", "<<SIN DIRECCION>>");
            prefs.get("razon", "<<SIN RAZON SOCIAL>>");
            prefs.get("ruc", "<<SIN RUC>>");
            prefs.get("frase", "<<SIN FRASE DE PIE DE TICKET>>");
            prefs.put("direccion", this.Direccion.getText().toUpperCase());
            prefs.put("razon", this.RazonSocial.getText().toUpperCase());
            prefs.put("ruc", this.Ruc.getText());
            prefs.put("frase", this.Frase.getText().toUpperCase());
            prefs.put("impresora", this.impresora.getText());
            this.txtdatabaseregister.setText(null);
            this.txthostregister.setText(null);
            this.txtpwdregister.setText(null);
            this.txtusuarioregister.setText(null);
            this.jDialog1.dispose();
            if (Conexion.Conexion() != null) {
                prefs.put("act", "69");
                this.toBack();
                JOptionPane.showMessageDialog(this, "Registro exitoso,se reiniciara el sistema.", "...:::Exito:::...", JOptionPane.INFORMATION_MESSAGE);

            } else {
                prefs.put("act", "0");
                //   JOptionPane.showMessageDialog(this, "Verificar los datos ingresados.","...:::Verificar tus datos:::...",JOptionPane.ERROR_MESSAGE);
            }
            consultarConexion();
        } else {
            this.toBack();
            JOptionPane.showMessageDialog(this, "Usuario,contraseña,host y database deben contener caracteres", "...:::Datos invalidos:::...", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnregistrarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        this.jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtusuusuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuusuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuusuActionPerformed

    private void txtpwdusuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpwdusuKeyReleased
        if (evt.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            ingresar();
        }
    }//GEN-LAST:event_txtpwdusuKeyReleased

    private void RazonSocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RazonSocialKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_RazonSocialKeyReleased

    private void DireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionActionPerformed

    private void DireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DireccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_DireccionKeyReleased

    private void RucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RucKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_RucKeyReleased

    private void RucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RucKeyTyped
        validator.solonumeros(evt, Ruc, 11);
    }//GEN-LAST:event_RucKeyTyped

    private void FraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FraseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FraseActionPerformed

    private void FraseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FraseKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_FraseKeyReleased

    private void txtusuarioregisterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuarioregisterKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioregisterKeyReleased

    private void txtpwdregisterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpwdregisterKeyPressed

    }//GEN-LAST:event_txtpwdregisterKeyPressed

    private void txthostregisterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txthostregisterKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txthostregisterKeyReleased

    private void txtdatabaseregisterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdatabaseregisterKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdatabaseregisterKeyReleased

    private void impresoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impresoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_impresoraActionPerformed

    private void impresoraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_impresoraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_impresoraKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void rdtodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdtodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdtodoActionPerformed

    private void btningresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresar1ActionPerformed
        prefs = Preferences.userRoot().node("configuracion");
        if (this.rdprueba.isSelected()) {

            prefs.get("act", "0");
            prefs.put("act", "0");
            Conexion.setDb("SistemaFarma");
            Conexion.setHost("localhost");
            Conexion.setUser("UsuarioTest");
            Conexion.setPwd("PasswordSistema");
            Conexion.WriteConn();
            this.jDialog1.dispose();
            consultarConexion();
        } else {
            if (this.rdtodo.isSelected()) {
                this.jTabbedPane1.setSelectedIndex(1);

            }
        }
    }//GEN-LAST:event_btningresar1ActionPerformed

    private void btncancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelar1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btncancelar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(inicioSession.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicioSession().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Direccion;
    private javax.swing.JTextField Frase;
    private javax.swing.JTextField RazonSocial;
    private javax.swing.JTextField Ruc;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btncancelar1;
    private javax.swing.JButton btningresar;
    private javax.swing.JButton btningresar1;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JButton btnsalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField impresora;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog1;
    public static javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblmessage;
    private javax.swing.JRadioButton rdFarmaceutica;
    private javax.swing.JRadioButton rdVentas;
    private javax.swing.JRadioButton rdprueba;
    private javax.swing.JRadioButton rdtodo;
    private javax.swing.JTextField txtdatabaseregister;
    private javax.swing.JTextField txthostregister;
    public static final javax.swing.JPasswordField txtpwdregister = new javax.swing.JPasswordField();
    private javax.swing.JPasswordField txtpwdusu;
    private javax.swing.JTextField txtusuarioregister;
    private javax.swing.JTextField txtusuusu;
    // End of variables declaration//GEN-END:variables

}
