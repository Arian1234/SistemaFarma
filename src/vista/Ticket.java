package vista;

import java.io.IOException;
import java.util.prefs.Preferences;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.PrintException;

/**
 *
 * @author AlvaroMolina
 */
public class Ticket {

    private static Preferences prefs;
 //Ticket attribute content

    private String contentTicket = 
            "" + (char) 27 + (char) 112 + (char) 0 + (char) 10 + (char) 100 + "{{nameLocal}}\n\n"
            + "      R.U.C. {{ruc}}\n"
            + "{{expedition}}\n"
            + //"Sunampe - Calle santo tomas N344 \n"+
            "===============================\n"
            + // "Periban. Mich. {{dateTime}}\n"+
            //"RFC: XAXX010101000\n"+
            "Cliente: {{box}} \n"
            + "Numero: {{ticket}}\n"
            + "Le atendio: {{cajero}}\n"
            + //"{{dateTime}}\n"+
            "COD   MEDIC   PREC   CANT   TOT\n"
            + "===============================\n"
            + "{{items}}\n"
            + "===============================\n"
            + "TOTAL: {{subTotal}}\n"
            + //"IGV: {{tax}}\n"+
            //"TOTAL: {{total}}\n\n"+
            //"RECIBIDO: {{recibo}}\n"+
            //"CAMBIO: {{change}}\n\n"+
            "===============================\n"
            + "{{frase}}\n"
           // + "\t::::::{{nameLocal}}::::::\n"
            + "\n"
            + "ESTE DOCUMENTO ES UNICAMENTE\n"
            + "PARA LA REALIZACION DEL DESPACHO.\n"
            + ":::SIN VALIDEZ TRIBUTARIA:::\n"
            + "\n\n\n ";

//El constructor que setea los valores a la instancia
    Ticket(String nameLocal, String expedition, String box, String ticket, String caissier, String dateTime, String items, String subTotal, String tax, String total) {
        prefs = Preferences.userRoot().node("configuracion");
        this.contentTicket = this.contentTicket.replace("{{nameLocal}}", prefs.get("razon", "<<SIN RAZON SOCIAL>>"));
        this.contentTicket = this.contentTicket.replace("{{ruc}}", prefs.get("ruc", "<<SIN RUC>>"));
        this.contentTicket = this.contentTicket.replace("{{expedition}}", prefs.get("direccion", "<<SIN DIRECCION>>"));
        this.contentTicket = this.contentTicket.replace("{{frase}}", prefs.get("frase", "<<SIN FRASE DE PIE DE TICKET>>"));
        this.contentTicket = this.contentTicket.replace("{{box}}", box);
        this.contentTicket = this.contentTicket.replace("{{ticket}}", ticket);
        this.contentTicket = this.contentTicket.replace("{{cajero}}", caissier);
        this.contentTicket = this.contentTicket.replace("{{dateTime}}", dateTime);
        this.contentTicket = this.contentTicket.replace("{{items}}", items);
        this.contentTicket = this.contentTicket.replace("{{subTotal}}", subTotal);
        this.contentTicket = this.contentTicket.replace("{{tax}}", tax);
        this.contentTicket = this.contentTicket.replace("{{total}}", total);
    }

    public void print() throws IOException {

        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null); //nos da el array de los servicios de impresion

//Creamos un arreglo de tipo byte
//y le agregamos el string convertido (cuerpo del ticket) a bytes tal como
//lo maneja la impresora(mas bien ticketera :p)
        byte[] bytes = this.contentTicket.getBytes();

//Especificamos el tipo de dato a imprimir
//Tipo: bytes; Subtipo: autodetectado
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

        Doc doc = new SimpleDoc(bytes, flavor, null);
//Creamos un trabajo de impresión
        DocPrintJob job = null;
        if (services.length > 0) {
            for (int i = 0; i < services.length; i++) {
                if (services[i].getName().equals("POS-58")) {//aqui escribimos/elegimos la impresora por la que queremos imprimir
                    job = services[i].createPrintJob();// System.out.println(i+": "+services[i].getName());
                }
            }
        }

//Imprimimos dentro de un try obligatoriamente
        try {
            job.print(doc, null);
        } catch (PrintException ex) {
            System.out.println(ex);
        }
    }
}
