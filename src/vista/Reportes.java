package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Conexion;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author AMolinaDeveloper
 */
public class Reportes extends javax.swing.JInternalFrame {

    /**
     * Creates new form Reportes
     */
    public Reportes() {
        initComponents();
    }
     public void Report(String fileName, String fecha) {
        try {

            JasperPrint print;
            Map parametro = new HashMap();
            parametro.put("fecha", fecha);
           

            print = JasperFillManager.fillReport(fileName, parametro, Conexion.Conexion());
           JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer,BorderLayout.CENTER);
                  } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
     
         public void ReportDesde(String fileName, String fechainicial,String fechafinal) {
        try {

            JasperPrint print;
            Map parametro = new HashMap();
            parametro.put("fechaInicial", fechainicial);
            parametro.put("fechaFinal", fechafinal);

            print = JasperFillManager.fillReport(fileName, parametro, Conexion.Conexion());
           JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer,BorderLayout.CENTER);
                  } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
         
         
             public void ReportBonosDesde(String fileName, String fechainicial,String fechafinal,String codusu) {
        try {

            JasperPrint print;
            Map parametro = new HashMap();
            parametro.put("fechaInicial", fechainicial);
            parametro.put("fechaFinal", fechafinal);
            parametro.put("usuario", codusu);
            print = JasperFillManager.fillReport(fileName, parametro, Conexion.Conexion());
           JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer,BorderLayout.CENTER);
                  } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

         public void ReportMedicamentos(String fileName) {
        try {

            JasperPrint print;
            Map parametro = new HashMap();
         
            print = JasperFillManager.fillReport(fileName,null, Conexion.Conexion());
           JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer,BorderLayout.CENTER);
                  } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
         public void ReportMedicamentosXLabor(String fileName,String labor){
              try {

            JasperPrint print;
            Map parametro = new HashMap();
            parametro.put("lab", labor);
           

            print = JasperFillManager.fillReport(fileName, parametro, Conexion.Conexion());
           JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer,BorderLayout.CENTER);
                  } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
         }
         
             public void ReportActivos(String fileName){
              try {

            JasperPrint print;
            Map parametro = new HashMap();
            
           

            print = JasperFillManager.fillReport(fileName, null, Conexion.Conexion());
           JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer,BorderLayout.CENTER);
                  } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
         }
             
               public void ReportCompras(String fileName){
              try {

            JasperPrint print;
          
           
           

            print = JasperFillManager.fillReport(fileName, null, Conexion.Conexion());
           JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer,BorderLayout.CENTER);
                  } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
         }
               
               
                  public void ReportComprasDesde(String fileName,String fechaInicial,String fechaFinal){
              try {

            JasperPrint print;
            Map parametro = new HashMap();
            parametro.put("fechaInicial", fechaInicial);
            parametro.put("fechaFinal", fechaFinal);
           

            print = JasperFillManager.fillReport(fileName, parametro, Conexion.Conexion());
           JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer,BorderLayout.CENTER);
                  } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
         }
                  
                  
                       public void ReportComprasXDoc(String fileName, String doc) {
        try {

            JasperPrint print;
            Map parametro = new HashMap();
            parametro.put("doc", doc);
           

            print = JasperFillManager.fillReport(fileName, parametro, Conexion.Conexion());
           JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer,BorderLayout.CENTER);
                  } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
                       
                       
                       
      public void ReportSeguimientoVentas(String fileName, String fechainicial,String fechafinal,String med) {
        try {

            JasperPrint print;
            Map parametro = new HashMap();
            parametro.put("fechaInicial", fechainicial);
            parametro.put("fechaFinal", fechafinal);
            parametro.put("med", med);
            print = JasperFillManager.fillReport(fileName, parametro, Conexion.Conexion());
           JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.add(viewer,BorderLayout.CENTER);
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

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reportes de la empresa");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/img/connections.png"))); // NOI18N
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 974, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
