/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Test
 */
public class Problema {

  private int n;
  private int m;
  private Map vertices;
  private int inicio;
  private ArrayList<Integer> metas;

  public Problema(int n, int m, Map vertices, int inicio, ArrayList<Integer> metas) {
    this.n = n;
    this.m = m;
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

  public boolean pruebaMeta(Nodo nodo) {
    return metas.contains(nodo.getEstado());
  }

  public ArrayList<Integer> acciones(Nodo nodo) {
    ArrayList<Integer> vecinos = new ArrayList<Integer>();
    for (int i = 0; i < vertices.size(); i++) {
      //vertices.get(i).
      //vecinos.add()
    }

    return new ArrayList<Integer>();
            /**
             * def actions(node:Node) : Seq[Int] = edges.getOrElse(node.state, Seq.empty).toSeq*
             */
  
  }

  public int resultado(Nodo padre, int accion) {
    return accion;
  }
}
