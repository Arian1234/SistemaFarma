package vista;

import at.jta.Key;
import at.jta.RegistryErrorException;
import at.jta.Regor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlvaroMolina
 */
public class preferencias {

    public static void preferenciasSave(String tipoUsuario) {
        try {
            Regor reg = null;
            Key key = null;
            String SBK = "Software\\MySoftware\\config\\" + tipoUsuario;

            reg = new Regor();

            //create a subkey
            if (reg.openKey(Regor.HKEY_CURRENT_USER, SBK) == null) {
                reg.createKey(Regor.HKEY_CURRENT_USER, SBK);
                System.out.println("Se esta creando una nueva sub-llave en el registro");
            }
            //open a subkey
            key = reg.openKey(Regor.HKEY_CURRENT_USER, SBK);
 System.out.println("Estamos entrando en la sub-llave");
            if (reg.readValueAsString(key, "clie") == null) {
                 System.out.println("Se esta creando las nuevas llaves...");
                //save an entry type REG_SZ
                //FRM Clientes
                reg.saveValue(key, "clie", "N");
                //FRM Medicamentos O Productos
                reg.saveValue(key, "prd", "Y");
                //FRM Compuestos
                reg.saveValue(key, "cmpst", "N");
                //FRM Laboratorio O Marca
                reg.saveValue(key, "mrc", "N");
                //FRM Proveedores
                reg.saveValue(key, "prvee", "N");
                //FRM Usuarios
                reg.saveValue(key, "usurs", "N");
                //FRM Pedidos
                reg.saveValue(key, "pdds", "N");
                //FRM Compras
                reg.saveValue(key, "cmps", "N");

                //FRM Buscar x compuestos
                reg.saveValue(key, "Bxcmpst", "N");
                //FRM Buscador
                reg.saveValue(key, "bscdr", "N");

                //Reportes
                //Reporte de activos
                reg.saveValue(key, "rptactv", "N");
                //Reporte inventario [COMPLETO]
                reg.saveValue(key, "rptinvt", "N");
                //Reporte inventario [SOLO CON STOCK]
                reg.saveValue(key, "rptinvtcstck", "N");
                //Reporte inventario [SIN STOCK]
                reg.saveValue(key, "rptinvtsstck", "N");
                //Reporte inventario [ESTADO DE ALERTA]
                reg.saveValue(key, "rptinvtedalrt", "N");
                //Reporte inventario [FILTRADO x LABORATORIO]
                reg.saveValue(key, "rptinvtFxlabrt", "N");

                //Reporte seguimiento de medicamentos
                reg.saveValue(key, "rptsdmedmts", "N");
                //Reporte ventas del año
                reg.saveValue(key, "rptvntsanio", "N");
                //Reporte ventas desde-hasta
                reg.saveValue(key, "rptvntDH", "N");
                //Reporte ventas del dia
                reg.saveValue(key, "rptVDD", "N");
                //Reporte ventas desde-hasta x usuario
                reg.saveValue(key, "RptvntDHxUsrio", "N");
                //Reporte Compras realizadas
                reg.saveValue(key, "rptCrealzd", "N");
                //Reporte desde-hasta
                reg.saveValue(key, "rptCDH", "N");
                //Reporte Compras x documento
                reg.saveValue(key, "prtCxdcmt", "N");

            } else {
                System.out.println("Ya existe sub-claves creadas.");
            }

            //save an entry type REG_DWORD
            // reg.saveDword(key, "Count", "1");
            //delete an entry any type
            //reg.deleteEntry(key, "KeyActivation");
            //delete a subkey
            // reg.delKey(Regor.HKEY_CURRENT_USER, SBK);
        } catch (RegistryErrorException ex) {
            Logger.getLogger(preferencias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
       public static void preferenciasUpdate(String tipoUsuario) {
        try {
            Regor reg = null;
            Key key = null;
            String SBK = "Software\\MySoftware\\config\\" + tipoUsuario;

            reg = new Regor();

            //create a subkey
            if (reg.openKey(Regor.HKEY_CURRENT_USER, SBK) == null) {
                reg.createKey(Regor.HKEY_CURRENT_USER, SBK);
                System.out.println("Se esta creando una nueva sub-llave en el registro");
            }
            //open a subkey
            key = reg.openKey(Regor.HKEY_CURRENT_USER, SBK);
 System.out.println("Estamos entrando en la sub-llave");
            if (reg.readValueAsString(key, "clie") == null) {
                 System.out.println("Se esta creando las nuevas llaves...");
                //save an entry type REG_SZ
                //FRM Clientes
                reg.saveValue(key, "clie", "N");
                //FRM Medicamentos O Productos
                reg.saveValue(key, "prd", "Y");
                //FRM Compuestos
                reg.saveValue(key, "cmpst", "N");
                //FRM Laboratorio O Marca
                reg.saveValue(key, "mrc", "N");
                //FRM Proveedores
                reg.saveValue(key, "prvee", "N");
                //FRM Usuarios
                reg.saveValue(key, "usurs", "N");
                //FRM Pedidos
                reg.saveValue(key, "pdds", "N");
                //FRM Compras
                reg.saveValue(key, "cmps", "N");

                //FRM Buscar x compuestos
                reg.saveValue(key, "Bxcmpst", "N");
                //FRM Buscador
                reg.saveValue(key, "bscdr", "N");

                //Reportes
                //Reporte de activos
                reg.saveValue(key, "rptactv", "N");
                //Reporte inventario [COMPLETO]
                reg.saveValue(key, "rptinvt", "N");
                //Reporte inventario [SOLO CON STOCK]
                reg.saveValue(key, "rptinvtcstck", "N");
                //Reporte inventario [SIN STOCK]
                reg.saveValue(key, "rptinvtsstck", "N");
                //Reporte inventario [ESTADO DE ALERTA]
                reg.saveValue(key, "rptinvtedalrt", "N");
                //Reporte inventario [FILTRADO x LABORATORIO]
                reg.saveValue(key, "rptinvtFxlabrt", "N");

                //Reporte seguimiento de medicamentos
                reg.saveValue(key, "rptsdmedmts", "N");
                //Reporte ventas del año
                reg.saveValue(key, "rptvntsanio", "N");
                //Reporte ventas desde-hasta
                reg.saveValue(key, "rptvntDH", "N");
                //Reporte ventas del dia
                reg.saveValue(key, "rptVDD", "N");
                //Reporte ventas desde-hasta x usuario
                reg.saveValue(key, "RptvntDHxUsrio", "N");
                //Reporte Compras realizadas
                reg.saveValue(key, "rptCrealzd", "N");
                //Reporte desde-hasta
                reg.saveValue(key, "rptCDH", "N");
                //Reporte Compras x documento
                reg.saveValue(key, "prtCxdcmt", "N");

            } else {
                System.out.println("Ya existe sub-claves creadas.");
            }

            //save an entry type REG_DWORD
            // reg.saveDword(key, "Count", "1");
            //delete an entry any type
            //reg.deleteEntry(key, "KeyActivation");
            //delete a subkey
            // reg.delKey(Regor.HKEY_CURRENT_USER, SBK);
        } catch (RegistryErrorException ex) {
            Logger.getLogger(preferencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       
       
       
          public static void privilegiosQuery(final String tipoUsuario) {
 
//Creamos un Thread para mejorar el ejemplo
        final Thread t;
        //Inicializamos
        t = new Thread(new Runnable() {
            // INICIAMOS LAS PREFERENCIAS
            //Implementamos el método run()
            @Override
            public void run() {

                try {
                
                //        main.menuAjustes.setEnabled(false);
                      //  String tipoUsuario = permisos.equals("SUPER") ? "suprv" : "dpch";
                        System.out.println(tipoUsuario);

                        Regor reg = null;
                        Key key = null;
                        String SBK = "Software\\MySoftware\\config\\" + tipoUsuario;
                        reg = new Regor();
                        key = reg.openKey(Regor.HKEY_CURRENT_USER, SBK);
                        //Permite mostrar el valor del progreso
                        main.jProgressBar2.setStringPainted(true);
                        int x = 1;
                        //Utilizamos un while para emular el valor mínimo y máximo
                        //En este caso 0 - 100
                        while (x <= 100) {
                            main.jProgressBar2.setValue(x);
                            main.jProgressBar2.setString(x + "%");

                            switch (x) {
                                case 1:
                                    main.chkClientes.setSelected(reg.readAnyValueString(key, "clie").equals("Y"));
                                    main.lblsesion1.setText(".");
                                    System.out.println("E cargado un :"+reg.readAnyValueString(key, "clie").equals("Y"));
                                    break;
                                case 4:
                                    main.chkMedOProds.setSelected(reg.readAnyValueString(key, "prd").equals("Y"));
                                    main.lblsesion1.setText("....");
                                    break;
                                case 8:
                                    main.chkCompuestos.setSelected(reg.readAnyValueString(key, "cmpst").equals("Y"));
                                    main.lblsesion1.setText("........");
                                    break;
                                case 12:
                                    main.chkLaborMarcas.setSelected(reg.readAnyValueString(key, "mrc").equals("Y"));
                                    main.lblsesion1.setText("............");
                                    break;
                                //
                                //
                                //
                                case 16:
                                    main.chkProveedores.setSelected(reg.readAnyValueString(key, "prvee").equals("Y"));
                                    main.lblsesion1.setText("................");
                                    break;
                                case 20:
                                    main.chkUsuarios.setSelected(reg.readAnyValueString(key, "usurs").equals("Y"));
                                    main.lblsesion1.setText("....................");
                                    break;
                                case 24:
                                    main.chkPedidosInternos.setSelected(reg.readAnyValueString(key, "pdds").equals("Y"));
                                    main.lblsesion1.setText("........................");
                                    break;
                                case 28:
                                    main.chkCompras.setSelected(reg.readAnyValueString(key, "cmps").equals("Y"));
                                    main.lblsesion1.setText("............................");
                                    break;
                                //
                                //
                                //
                                case 32:
                                    main.chkBuscarXCompuest.setSelected(reg.readAnyValueString(key, "Bxcmpst").equals("Y"));
                                    main.lblsesion1.setText("................................");
                                    break;
                                case 36:
                                    main.chkBuscador.setSelected(reg.readAnyValueString(key, "bscdr").equals("Y"));
                                    main.lblsesion1.setText("....................................");
                                    break;
                                case 40:
                                    main.chkRptActivos.setSelected(reg.readAnyValueString(key, "rptactv").equals("Y"));
                                    main.lblsesion1.setText("........................................");
                                    break;
                                case 44:
                                    main.chkRptInvComplt.setSelected(reg.readAnyValueString(key, "rptinvt").equals("Y"));
                                    main.lblsesion1.setText("............................................");
                                    break;
                                //
                                //
                                //
                                case 48:
                                    main.chkRptConStock.setSelected(reg.readAnyValueString(key, "rptinvtcstck").equals("Y"));
                                    main.lblsesion1.setText("................................................");
                                    break;
                                case 52:
                                    main.chkRptSinStock.setSelected(reg.readAnyValueString(key, "rptinvtsstck").equals("Y"));
                                    main.lblsesion1.setText("............................................");
                                    break;
                                case 56:
                                    main.chkRptEstAlert.setSelected(reg.readAnyValueString(key, "rptinvtedalrt").equals("Y"));
                                    main.lblsesion1.setText("........................................");
                                    break;
                                case 60:
                                    main.chkRptFiltradXLab.setSelected(reg.readAnyValueString(key, "rptinvtFxlabrt").equals("Y"));
                                    main.lblsesion1.setText("....................................");
                                    break;

                                //
                                //
                                //
                                case 64:
                                    main.chkSegimitMedic.setSelected(reg.readAnyValueString(key, "rptsdmedmts").equals("Y"));
                                    main.lblsesion1.setText("................................");
                                    break;
                                case 68:
                                    main.chkRptVentaAnio.setSelected(reg.readAnyValueString(key, "rptvntsanio").equals("Y"));
                                    main.lblsesion1.setText("............................");
                                    break;
                                case 72:
                                    main.chkRptVentasDH.setSelected(reg.readAnyValueString(key, "rptvntDH").equals("Y"));
                                    main.lblsesion1.setText("........................");
                                    break;
                                case 76:
                                    main.chkRptVentasDia.setSelected(reg.readAnyValueString(key, "rptVDD").equals("Y"));
                                    main.lblsesion1.setText("....................");
                                    break;
                                //
                                //
                                //
                                case 80:
                                    main.chkRptVentasDUsuario.setSelected(reg.readAnyValueString(key, "RptvntDHxUsrio").equals("Y"));
                                    main.lblsesion1.setText("................");
                                    break;
                                case 84:
                                    main.chkRptCompras.setSelected(reg.readAnyValueString(key, "rptCrealzd").equals("Y"));
                                    main.lblsesion1.setText("............");
                                    break;
                                case 88:
                                    main.chkRptComprasDH.setSelected(reg.readAnyValueString(key, "rptCDH").equals("Y"));
                                    main.lblsesion1.setText("........");
                                    break;
                                case 92:
                                    main.chkRptComprasXDoc.setSelected(reg.readAnyValueString(key, "prtCxdcmt").equals("Y"));
                                    main.lblsesion1.setText("....");
                                    break;
                            }
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                            }
                           
                            x++;
                        }
                        main.jDialog12.dispose();
//                        main.jDialog8.setLocationRelativeTo(main.jDesktopPane);
//                       main.jDialog8.setVisible(true);
                    
                } catch (RegistryErrorException ex) {
                    Logger.getLogger(inicioSession.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        //Se ejecuta el Thread
        t.start();
    }

}
