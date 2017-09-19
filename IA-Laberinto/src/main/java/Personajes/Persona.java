
package Personajes;

import BusEvento.BusEvento;
import PaqueteFSM.Accion;
import com.google.common.eventbus.Subscribe;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.statefulj.fsm.FSM;
import org.statefulj.fsm.TooBusyException;
import org.statefulj.fsm.model.Action;
import org.statefulj.fsm.model.impl.StateImpl;
import org.statefulj.persistence.annotations.State;
import org.statefulj.persistence.memory.MemoryPersisterImpl;

public class Persona {
  
  @State
  private String nombre;
  private FSM maquinaDeEstado;
  
  public Persona()
  {
    nombre = UUID.randomUUID().toString();
    BusEvento.getBus().register(this);
    //Eventos
    String pausar = "Pausar";
    String iniciar = "Iniciar";
    String finalizar = "Finalizar";

    //Estados

    org.statefulj.fsm.model.State<Persona> movimiento = new StateImpl<Persona>("Movimiento");
    org.statefulj.fsm.model.State<Persona> detenido = new StateImpl<Persona>("Detenido");
    org.statefulj.fsm.model.State<Persona> ganar = new StateImpl<Persona>("Ganar", true);

    //Acciones

    Action<Persona> iniciarAccion = new Accion<Persona>(iniciar);
    Action<Persona> pausarAccion = new Accion<Persona>(pausar);
    Action<Persona> finalizarAccion = new Accion<Persona>(finalizar);

  //Persister
    List<org.statefulj.fsm.model.State<Persona>> estados = new LinkedList<org.statefulj.fsm.model.State<Persona>>();
    MemoryPersisterImpl<Persona> persistidor;
    
    //Transiciones
    movimiento.addTransition(pausar, detenido, pausarAccion);
    movimiento.addTransition(iniciar, movimiento, iniciarAccion);
    movimiento.addTransition(finalizar, ganar, finalizarAccion);

    detenido.addTransition(pausar, detenido, pausarAccion);
    detenido.addTransition(iniciar, movimiento, iniciarAccion);
    detenido.addTransition(finalizar, ganar, finalizarAccion);

    ganar.addTransition(pausar, ganar, finalizarAccion);
    ganar.addTransition(iniciar, ganar, finalizarAccion);
    ganar.addTransition(finalizar, ganar, finalizarAccion);
    
    //Persister
    estados.add(movimiento);
    estados.add(detenido);
    estados.add(ganar);

    persistidor = new MemoryPersisterImpl<Persona>(estados, detenido);
    
    maquinaDeEstado = new FSM("Persona FSM", persistidor);
  
  }
 
    
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  @Subscribe
  public void recibirMensaje(String pMensaje)
  {
    try{
      maquinaDeEstado.onEvent(this, pMensaje);
    } catch (TooBusyException ex) {
      System.out.println(ex.toString());
    }
  }
 
  
}
