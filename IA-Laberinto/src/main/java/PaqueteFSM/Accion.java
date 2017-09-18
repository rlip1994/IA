
package PaqueteFSM;

import org.statefulj.fsm.RetryException;
import org.statefulj.fsm.model.Action;


public class Accion<T> implements Action<T>{

  private String nombre;

  public Accion(String nombre) {
    this.nombre = nombre;
  }
  
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  
  public void execute(T pPersona, String pEvento, Object... os) throws RetryException {
    switch(nombre){
      case "Iniciar":
        empezarACaminar(pPersona,pEvento,os);
        break;
        
      case "Pausar":
        empezarADetenerse(pPersona,pEvento,os);
        break;
        
      case "Ganar":
        empezarAGanar(pPersona,pEvento,os);
        break;
    }      
  }
  
  
  private void empezarACaminar(T stateful, String event, Object[] args){
    System.out.println("Caminando : " + nombre);
  }
  
  private void empezarADetenerse(T stateful, String event, Object[] args){
    System.out.println("Deteniendose : " + nombre);
  }
  
  private void empezarAGanar(T stateful, String event, Object[] args){
    System.out.println("Ganado : " + nombre);
  }
         
}
