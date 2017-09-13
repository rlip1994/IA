/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Test
 */
public class Laberinto {

  private ArrayList<ArrayList<Integer>> matriz = new ArrayList<ArrayList<Integer>>();

  public Laberinto(String laberintoAscii) {
    GeneraLaberinto(laberintoAscii);
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
