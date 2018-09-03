/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author AlvaroMolina
 */
public class always {

    public void block(final JFrame f) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        f.toFront();
                        f.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);//evita cerra jframe con ALT+C
                        f.setExtendedState(MAXIMIZED_BOTH);//maximizado
                        f.setAlwaysOnTop(true);//siempre al frente
                    }
                }, 50, 20, TimeUnit.MILLISECONDS); //comienza dentro de 1/2 segundo y luego se repite cada N segundos
    }
}
