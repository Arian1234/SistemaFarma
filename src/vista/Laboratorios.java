/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.controllerLaboratorio;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AMolinaDeveloper
 */
public class Laboratorios extends javax.swing.JInternalFrame {

    public DefaultTableModel dtm;
    private int x;
    private int y;

    /**
     * Creates new form NewJInternalFrame
     */
    public Laboratorios() {
        initComponents();
    }

    public void clear() {
        this.txtcodlabor.setText(null);
        this.Nombre.setText(null);
        this.Descripción.setText(null);
        this.Nombre.requestFocus();

    }

    public void insertLaboratorios() {
        if (validator.novacios("1", 1, this.Nombre) == false) {

            controllerLaboratorio.setOp("I");
            controllerLaboratorio.setCod(0);
            controllerLaboratorio.setNomb(this.Nombre.getText());
            controllerLaboratorio.setDescrip(this.Descripción.getText());
            this.txtmsg.setText(controllerLaboratorio.mntoLaboratorio());

            controllerLaboratorio.setOp("D");
            controllerLaboratorio.setBusc(this.Nombre.getText());
            controllerLaboratorio.consultarLaboratorio("laboratorio", dtm);
            clear();
        }
    }

    public void updateLaboratorios() {
        if (validator.novacios("1", 1, this.Nombre) == false) {

            controllerLaboratorio.setOp("U");
            controllerLaboratorio.setCod(Integer.parseInt(this.txtcodlabor.getText()));
            controllerLaboratorio.setNomb(this.Nombre.getText());
            controllerLaboratorio.setDescrip(this.Descripción.getText());
            this.txtmsg.setText(controllerLaboratorio.mntoLaboratorio());

            controllerLaboratorio.setOp("D");
            controllerLaboratorio.setBusc(this.Nombre.getText());
            controllerLaboratorio.consultarLaboratorio("laboratorio", dtm);
            clear();

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
        Nombre = new javax.swing.JTextField();
        Descripción = new javax.swing.JTextField();
        txtcodlabor = new javax.swing.JTextField();
        txtmsg = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtbusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLaborat = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registros de Laboratorios");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/connections.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(923, 476));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrar laboratorios o marcas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        Nombre.setName("Nombre");
        Nombre.setBackground(new java.awt.Color(244, 245, 242));
        Nombre.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Nombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Nombre.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Nombre", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Nombre.setCaretColor(new java.awt.Color(51, 204, 255));
        Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NombreKeyPressed(evt);
            }
        });

        Descripción.setBackground(new java.awt.Color(244, 245, 242));
        Descripción.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        Descripción.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Descripción.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Descripción", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        Descripción.setCaretColor(new java.awt.Color(51, 204, 255));
        Descripción.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DescripciónKeyPressed(evt);
            }
        });

        txtcodlabor.setEditable(false);
        txtcodlabor.setBackground(new java.awt.Color(255, 255, 204));
        txtcodlabor.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        txtcodlabor.setMaximumSize(new java.awt.Dimension(100, 28));
        txtcodlabor.setMinimumSize(new java.awt.Dimension(100, 28));
        txtcodlabor.setPreferredSize(new java.awt.Dimension(100, 28));

        txtmsg.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        txtmsg.setForeground(new java.awt.Color(102, 0, 0));
        txtmsg.setText("DATOS CARGADOS.");
        txtmsg.setMaximumSize(new java.awt.Dimension(171, 16));
        txtmsg.setMinimumSize(new java.awt.Dimension(171, 16));
        txtmsg.setPreferredSize(new java.awt.Dimension(171, 16));
        txtmsg.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtcodlabor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmsg, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Descripción, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Descripción, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodlabor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmsg, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(245, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros de laboratorios o marcas", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 1, 12))); // NOI18N

        txtbusqueda.setBackground(new java.awt.Color(255, 204, 204));
        txtbusqueda.setFont(new java.awt.Font("Calibri Light", 2, 12)); // NOI18N
        txtbusqueda.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtbusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Realizar búsqueda de laboratorio o marca", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri Light", 0, 12))); // NOI18N
        txtbusqueda.setCaretColor(new java.awt.Color(51, 204, 255));
        txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyPressed(evt);
            }
        });

        jTableLaborat.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jTableLaborat.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableLaborat.setSelectionBackground(new java.awt.Color(255, 0, 51));
        jTableLaborat.getTableHeader().setReorderingAllowed(false);
        jTableLaborat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLaboratMouseClicked(evt);
            }
        });
        jTableLaborat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableLaboratKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLaborat);
        if (jTableLaborat.getColumnModel().getColumnCount() > 0) {
            jTableLaborat.getColumnModel().getColumn(0).setResizable(false);
            jTableLaborat.getColumnModel().getColumn(1).setResizable(false);
            jTableLaborat.getColumnModel().getColumn(2).setResizable(false);
            jTableLaborat.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtbusqueda)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jMenu1.setText("NUEVO[F4]");
        jMenu1.setFont(new java.awt.Font("Calibri Light", 0, 11)); // NOI18N
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jMenu1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jMenu1MouseExited(evt);
            }
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableLaboratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLaboratMouseClicked
        int sel = this.jTableLaborat.getSelectedRow();
        this.txtcodlabor.setText(this.jTableLaborat.getValueAt(sel, 0).toString());
        this.Nombre.setText(this.jTableLaborat.getValueAt(sel, 1).toString());
        this.Descripción.setText(this.jTableLaborat.getValueAt(sel, 2).toString());


    }//GEN-LAST:event_jTableLaboratMouseClicked

    private void NombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_F4) {
            clear();
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_F5) {
                insertLaboratorios();
            } else {
                if (evt.getExtendedKeyCode() == KeyEvent.VK_F6) {
                    updateLaboratorios();
                }
            }

        }
    }//GEN-LAST:event_NombreKeyPressed

    private void DescripciónKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DescripciónKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_F4) {
            clear();
        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_F5) {
                insertLaboratorios();
            } else {
                if (evt.getExtendedKeyCode() == KeyEvent.VK_F6) {
                    updateLaboratorios();
                }
            }

        }
    }//GEN-LAST:event_DescripciónKeyPressed

    private void jTableLaboratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableLaboratKeyPressed
        if (evt.getExtendedKeyCode() == KeyEvent.VK_F4) {
            clear();
        }
    }//GEN-LAST:event_jTableLaboratKeyPressed

    private void txtbusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyPressed
        controllerLaboratorio.setOp("D");
        controllerLaboratorio.setBusc(this.txtbusqueda.getText());
        controllerLaboratorio.consultarLaboratorio("laboratorio", dtm);
    }//GEN-LAST:event_txtbusquedaKeyPressed

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        clear();
        this.jMenu1.setSelected(false);
    }//GEN-LAST:event_jMenu1MousePressed

    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        insertLaboratorios();
        this.jMenu2.setSelected(false);
    }//GEN-LAST:event_jMenu2MousePressed

    private void jMenu3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MousePressed
        if (this.txtcodlabor.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Por favor seleccione un laboratorio de la tabla.", "Seleccione laboratorio", JOptionPane.ERROR_MESSAGE);
        } else {
            updateLaboratorios();;
        }
        this.jMenu3.setSelected(false);
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

    private void jMenu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Descripción;
    private javax.swing.JTextField Nombre;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableLaborat;
    private javax.swing.JTextField txtbusqueda;
    private javax.swing.JTextField txtcodlabor;
    private javax.swing.JLabel txtmsg;
    // End of variables declaration//GEN-END:variables
}
