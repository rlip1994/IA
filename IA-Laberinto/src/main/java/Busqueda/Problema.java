/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Test
 */
public class Problema {

  private int n;
  private int m;
  private Map<Integer, ArrayList<Integer>> vertices;
  private int inicio;
  private ArrayList<Integer> metas;

  public Problema(int n, int m, Map<Integer, ArrayList<Integer>> vertices, int inicio, ArrayList<Integer> metas) {
    this.n = n; //cantidad de nodos
    this.m = m; //cantidad de vertices
    this.vertices = vertices;
    this.inicio = inicio;
    this.metas = metas;
  }

  public int getN() {
    return n;
  }

  public void setN(int n) {
    this.n = n;
  }

  public int getM() {
    return m;
  }

  public void setM(int m) {
    this.m = m;
  }

  public Map getVertices() {
    return vertices;
  }

  public void setVertices(Map vertices) {
    this.vertices = vertices;
  }

  public int getInicio() {
    return inicio;
  }

  public void setInicio(int inicio) {
    this.inicio = inicio;
  }

  public ArrayList<Integer> getMetas() {
    return metas;
  }

  public void setMetas(ArrayList<Integer> metas) {
    this.metas = metas;
  }

  public boolean esMeta(Nodo nodo) {
    return metas.contains(nodo.getEstado());
  }

  public ArrayList<Integer> getVertices(int nodo) {
    if (!vertices.containsKey (nodo)) {
            return new ArrayList<Integer> (); //Return an empty list
        }

        return vertices.get(nodo);
  }
  
  
  
}


