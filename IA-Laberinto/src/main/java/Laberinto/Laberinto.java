/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laberinto;

import BusEvento.BusEvento;
import PaqueteFSM.Accion;
import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.statefulj.fsm.FSM;
import org.statefulj.fsm.TooBusyException;
import org.statefulj.fsm.model.Action;
import org.statefulj.fsm.model.impl.StateImpl;
import org.statefulj.persistence.memory.MemoryPersisterImpl;

/**
 *
 * @author Test
 */
public class Laberinto {

  private ArrayList<ArrayList<Integer>> matriz = new ArrayList<ArrayList<Integer>>();
  private String nombre;
  private FSM maquinaDeEstado;
  

  public Laberinto(String laberintoAscii) {
    nombre = UUID.randomUUID().toString();
    BusEvento.getBus().register(this);
    GeneraLaberinto(laberintoAscii);
    
    //Eventos
    String pausar = "Pausar";
    String iniciar = "Iniciar";
    String actualiza = "Actualiza";


    //Estados

    org.statefulj.fsm.model.State<Laberinto> actualizado = new StateImpl<Laberinto>("Actualiza");
    org.statefulj.fsm.model.State<Laberinto> detenido = new StateImpl<Laberinto>("Pausa");
    org.statefulj.fsm.model.State<Laberinto> inicio = new StateImpl<Laberinto>("Iniciar");


    //Acciones

    Action<Laberinto> iniciarAccion = new Accion<Laberinto>(iniciar);
    Action<Laberinto> pausarAccion = new Accion<Laberinto>(pausar);
    Action<Laberinto> actualizaAccion = new Accion<Laberinto>(actualiza);


  //Persister
    List<org.statefulj.fsm.model.State<Laberinto>> estados = new LinkedList<org.statefulj.fsm.model.State<Laberinto>>();
    MemoryPersisterImpl<Laberinto> persistidor;
    
    //Transiciones
    movimiento.addTransition(pausar, detenido, pausarAccion);
    movimiento.addTransition(iniciar, movimiento, iniciarAccion);

    detenido.addTransition(pausar, detenido, pausarAccion);
    detenido.addTransition(iniciar, movimiento, iniciarAccion);



    //Persister
    estados.add(movimiento);
    estados.add(detenido);
   

    persistidor = new MemoryPersisterImpl<Laberinto>(estados, detenido);
    
    maquinaDeEstado = new FSM("Laberinto FSM", persistidor);
    
    
  }

  private void GeneraLaberinto(String laberintoAscii) {
    ArrayList<String> matrizAscii = (ArrayList<String>) StringALista(laberintoAscii);
    ArrayList<Integer> fila = new ArrayList<Integer>();
    for (int i = 0; i < matrizAscii.size(); i++) {

      if (matrizAscii.get(i).equals(" ")) {
        fila.add(0);

      }
      if (matrizAscii.get(i).equals("+") || matrizAscii.get(i).equals("-") || matrizAscii.get(i).equals("|")) {
        fila.add(1);

      }
      if (matrizAscii.get(i).equals("\n")) {

        this.matriz.add(new ArrayList<Integer>(fila));
        fila.clear();
      }

    }
    this.matriz.add(new ArrayList<Integer>(fila));

  }

  private List<String> StringALista(String laberintoAscii) {
    return new ArrayList<String>(Arrays.asList(laberintoAscii.split("")));
  }

  public void setMatriz(ArrayList<ArrayList<Integer>> matriz) {
    this.matriz = matriz;
  }

  public ArrayList<ArrayList<Integer>> getMatriz() {
    return matriz;
  }
  
  public int getFilas(){
    return this.matriz.size();
  }
  
  public int getColumnas(){
    return this.matriz.get(0).size();
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
  
  @Override
  public String toString(){
    String resultado = "";
    for (int x = 0; x < matriz.size(); x++) {
      for (int y = 0; y < matriz.get(0).size(); y++) {
        resultado=resultado+matriz.get(x).get(y);
      }
      resultado=resultado+"\n";
    }
    return resultado;
  }

}
