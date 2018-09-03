
package vista;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

/**
 *
 * @author AlvaroMolina
 */
public class jBlocked {
    private JFrame jframe=null;

    /**
 * Constructor de clase
 */
    public jBlocked( JFrame f )
    {
        this.jframe = f;
    }

    /**
 * ejecuta una tarea cada "n" tiempo
 * Para evitar que el usuario utilice las teclas (WINDOWS + D)(TAB) y asi perder el foco
 * de la aplicación, cada 50 milisegundos se envia el JFrame al frente y se cambia su propiedad a maximizado
 */
    public void block()
    {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate( 
            new Runnable() 
            {
                @Override
                public void run() {                   
                    front(); 
                }
              }, 500, 50 , TimeUnit.MILLISECONDS ); //comienza dentro de 1/2 segundo y luego se repite cada N segundos
    }

    /**
 * 
 */
    public void front()
    {
        jframe.setExtendedState( JFrame.MAXIMIZED_BOTH );//maximizado
        jframe.toFront();
    }

}
