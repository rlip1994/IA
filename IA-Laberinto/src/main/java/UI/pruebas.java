
package UI;

import Laberinto.Laberinto;
import Personajes.Persona;
import com.google.common.eventbus.EventBus;


public class pruebas {

  public static void main(String[] args) {
    Laberinto lab = new Laberinto("+-+-+-+-+-+\n"
            + "  |   |   |\n"
            + "+ + + + + +\n"
            + "|   | | | |\n"
            + "+-+-+ + + +\n"
            + "|   |   | |\n"
            + "+-+ + +-+ +\n"
            + "|   |   | |\n"
            + "+ +-+-+-+ +\n"
            + "|          \n"
            + "+-+-+-+-+-+");

   Persona personaJr = new Persona();
        
  
    EventBus EventBusJr = new EventBus();
    EventBusJr.register(personaJr);
    
    EventBusJr.post("Iniciar");

  } //Cierre del main

}
