
package Conexion;

import javax.swing.JOptionPane;

public class Pruebas {
    
    public static void main(String[] args) {
        
          
         
         String idComponente = JOptionPane.showInputDialog("Digite el id");
         String estado = JOptionPane.showInputDialog("Digite el estado");
         String servicio = "http://localhost/ServicioxDh/CxCxA?c="+idComponente+"&eC="+estado;
         String respuestaServicio = HttpRequest.get(servicio).body();
         System.out.println(respuestaServicio);
    }
    
}
