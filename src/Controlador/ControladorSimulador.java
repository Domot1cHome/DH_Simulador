package Controlador;

import Conexion.HttpRequest;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class ControladorSimulador extends Thread {

    int time = 250;
    
    private String ruta = "http://localhost/DH_Web/api/Simulador.php?id=";
    private JToggleButton BotonLuz, BotonCamara, BotonAire, BotonOscuro, BotonVentilador;
    private ImageIcon luzOn, luzOff, camaraOn, camaraOff, aireOn, aireOff, oscuro, ventiladorOn, ventiladorOff;

    public ControladorSimulador(JToggleButton luz, JToggleButton camara, JToggleButton aire, JToggleButton ventilador, JToggleButton oscuro) {
        this.BotonLuz = luz;
        this.BotonCamara = camara;
        this.BotonAire = aire;
        this.BotonVentilador = ventilador;
        this.BotonOscuro = oscuro;

        EnrutarIconos();

    }

    public void EnrutarIconos() {
        aireOn = new ImageIcon("src/Img/aireOn.png");
        aireOff = new ImageIcon("src/Img/aireOff.png");
        luzOn = new ImageIcon("src/Img/luzOn.png");
        luzOff = new ImageIcon("src/Img/luzOff.png");
        camaraOn = new ImageIcon("src/Img/camaraOn.png");
        camaraOff = new ImageIcon("src/Img/camaraOff.png");
        oscuro = new ImageIcon("src/Img/oscuro.png");
        ventiladorOn = new ImageIcon("src/Img/ventiladorOn.gif");
        ventiladorOff = new ImageIcon("src/Img/ventiladorOff.png");
    }

    public void ControlarLampara(int respuesta) {

        if (respuesta == 1) {
            BotonLuz.setIcon(luzOn);
            BotonOscuro.setIcon(null);
        } else {
            BotonLuz.setIcon(luzOff);
            BotonOscuro.setIcon(oscuro);
        }

    }

    public void ControlarCamara(int respuesta) {

        if (respuesta == 1) {
            BotonCamara.setIcon(camaraOn);
        } else {
            BotonCamara.setIcon(camaraOff);
        }
    }

    public void ControlarAire(int respuesta) {
        if (respuesta == 1) {
            BotonAire.setIcon(aireOn);
        } else {
            BotonAire.setIcon(aireOff);
        }
    }

    public void ControlarVentilador(int respuesta) {
        if (respuesta == 1) {
            BotonVentilador.setIcon(ventiladorOn);
        } else {
            BotonVentilador.setIcon(ventiladorOff);
        }
    }

    @Override
    public void run() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                lampara();
            }
        }).start();
        System.out.println("Empezó");

        new Thread(new Runnable() {
            @Override
            public void run() {
                aire();
            }
        }).start();
        System.out.println("Empezó");

        new Thread(new Runnable() {
            @Override
            public void run() {

                ventilador();
            }
        }).start();
        System.out.println("Empezó");

    }

    public void lampara() {

        while (true) {

            try {

                Thread.sleep(time);

                try {

                    String servicio = ruta+"1";
                    String respuestaServicio = HttpRequest.get(servicio).body();
                    String[] arregloInstruccion = respuestaServicio.split("_");
                    System.out.println(Arrays.toString(arregloInstruccion));
                    int idBoton = Integer.parseInt(arregloInstruccion[2]);
                    int estadoBoton = Integer.parseInt(arregloInstruccion[3]);

                    System.out.println(idBoton + " " + estadoBoton);

                    ControlarLampara(estadoBoton);

                } catch (Exception e) {
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorSimulador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void ventilador() {

        while (true) {

            try {

                Thread.sleep(time);

                try {

                    String servicio = ruta+"2";
                    String respuestaServicio = HttpRequest.get(servicio).body();
                    String[] arregloInstruccion = respuestaServicio.split("_");
                    System.out.println(Arrays.toString(arregloInstruccion));
                    int idBoton = Integer.parseInt(arregloInstruccion[2]);
                    int estadoBoton = Integer.parseInt(arregloInstruccion[3]);

                    System.out.println(idBoton + " " + estadoBoton);

                    ControlarVentilador(estadoBoton);

                } catch (Exception e) {
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorSimulador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void aire() {

        while (true) {

            try {

                Thread.sleep(time);

                try {

                    String servicio = ruta+"3";
                    String respuestaServicio = HttpRequest.get(servicio).body();
                    String[] arregloInstruccion = respuestaServicio.split("_");
                    System.out.println(Arrays.toString(arregloInstruccion));
                    int idBoton = Integer.parseInt(arregloInstruccion[2]);
                    int estadoBoton = Integer.parseInt(arregloInstruccion[3]);

                    System.out.println(idBoton + " " + estadoBoton);

                    ControlarAire(estadoBoton);

                } catch (Exception e) {
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorSimulador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
