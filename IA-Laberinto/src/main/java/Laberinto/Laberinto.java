/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laberinto;

import BusEvento.BusEvento;
import PaqueteFSM.Accion;
import com.google.common.eventbus.Subscribe;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

  public Laberinto() {
    nombre = UUID.randomUUID().toString();
    BusEvento.getBus().register(this);
   

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
    actualizado.addTransition(actualiza, actualizado, actualizaAccion);
    actualizado.addTransition(iniciar, actualizado, actualizaAccion);
    actualizado.addTransition(pausar, detenido, pausarAccion);

    detenido.addTransition(actualiza, detenido, pausarAccion);
    detenido.addTransition(iniciar, actualizado, actualizaAccion);
    detenido.addTransition(pausar, detenido, pausarAccion);

    inicio.addTransition(iniciar, actualizado, iniciarAccion);
    inicio.addTransition(actualiza, inicio, pausarAccion);
    inicio.addTransition(pausar, detenido, pausarAccion);

    //Persister
    estados.add(inicio);
    estados.add(actualizado);

    estados.add(detenido);

    persistidor = new MemoryPersisterImpl<Laberinto>(estados, detenido);

    maquinaDeEstado = new FSM("Laberinto FSM", persistidor);

  }

  public void GeneraLaberinto(String laberintoAscii) {
    ArrayList<String> matrizAscii = (ArrayList<String>) stringALista(laberintoAscii);
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

  public void ponerPersona() {
    boolean flag = true;
    while (flag) {
      int fila = (int) (Math.random() * matriz.size()) + 1;
      int columna = (int) (Math.random() * matriz.get(0).size()) + 1;
      if (matriz.get(fila).get(columna) == 0) {
        matriz.get(fila).add(columna, 9);
        flag = false;
      }
    }

  }

  public String cargarArchivo(String ubicacion) throws FileNotFoundException, IOException {
    String cadena;
    String resultado="";
    FileReader f = new FileReader(ubicacion);
    BufferedReader b = new BufferedReader(f);
    while ((cadena = b.readLine()) != null) {
      resultado=resultado+cadena;
      
    }
    b.close();
    
    return resultado;
  }

  private List<String> stringALista(String laberintoAscii) {
    return new ArrayList<String>(Arrays.asList(laberintoAscii.split("")));
  }

  public void setMatriz(ArrayList<ArrayList<Integer>> matriz) {
    this.matriz = matriz;
  }

  public ArrayList<ArrayList<Integer>> getMatriz() {
    return matriz;
  }

  public int getFilas() {
    return this.matriz.size();
  }

  public int getColumnas() {
    return this.matriz.get(0).size();
  }

  @Subscribe
  public void recibirMensaje(String pMensaje) {
    try {
      maquinaDeEstado.onEvent(this, pMensaje);
    } catch (TooBusyException ex) {
      System.out.println(ex.toString());
    }
  }

  @Override
  public String toString() {
    String resultado = "";
    for (int x = 0; x < matriz.size(); x++) {
      for (int y = 0; y < matriz.get(0).size(); y++) {
        resultado = resultado + matriz.get(x).get(y);
      }
      resultado = resultado + "\n";
    }
    return resultado;
  }

}
