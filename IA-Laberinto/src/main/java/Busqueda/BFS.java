/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Busqueda;

import java.util.ArrayList;

/**
 *
 * @author Test
 */
public class BFS {
  
  
  private int Cost = 6;
  

 
  public Nodo nodoHijo(Problema problema, Nodo padre, int accion){
   // Nodo nodoBebe = new Nodo(problema.resultado(padre, accion),padre.getCosto()+this.Cost);
    return null; //nodoBebe;
  }

  public ArrayList<Integer> breadthFirstSearch(Problema problema){
    return null;
  }

  /**
    * BFS modificado para almacenar multiples costos
    
  def breadthFirstSearch(problem:Problem): Seq[Int] = {
    val root      = Node(Problema.initialState, 0)
    var costs     = new mutable.HashMap[Int, Int]
    var explored  = new mutable.HashSet[Int]
    val frontier  = new mutable.Queue[Node]
    frontier += root

    while(frontier.nonEmpty) {
      val node = frontier.dequeue()
      explored += node.state
      val actions = Problema.actions(node)
      actions.foreach( action => {
        val child = childNode(problem, node, action)
        val notChecked = !explored.contains(child.state) && frontier.find(_.state == child.state).isEmpty
        if (notChecked) {
          if (Problema.goalTest(child)) {
            if (costs.getOrElse(child.state, Int.MaxValue) > child.cost)
              costs += (child.state -> child.cost)
          }
          frontier += child
        }
      })
    }

    //Maps goals to cost, -1 if it is not in the cost map
    Problema.goals.map( goal => costs.getOrElse(goal, -1) )
  }

  object ProblemConstruction {

    private def tuple(stringTuple: String): (Int, Int) = {
      val values = stringTuple.split("\\s+").toSeq
      (values.head.toInt, values.tail.head.toInt)
    }

    private def readProblem(input: Iterator[String]): Problem = {
      val (nodesCount, edgesCount) = tuple(input.next().trim())
      val edgesMap = new mutable.HashMap[Int, mutable.Set[Int]] with mutable.MultiMap[Int, Int]
      for (_ - 1 to edgesCount) {
        val edge = tuple(input.next())
        val reverseEdge = (edge._2, edge._1)

        edgesMap.addBinding(edge._1, edge._2)
        edgesMap.addBinding(reverseEdge._1, reverseEdge._2)
      }

      val start = input.next().trim().toInt
      Problem(nodesCount, edgesCount, edgesMap.toMap, start, (1 to nodesCount).filter(_ != start))
    }

    def readProblems(input: Iterator[String]): Seq[Problem] = {
      val q = input.next().trim().toInt
      var problems = Seq.empty[Problem]
      for (a - 1 to q) {
        problems = problems :+ readProblem(input)
      }
      problems
    }
  }

  def resolve(lines:Iterator[String]) : (Seq[Problem], Seq[String]) = {
    val problems = ProblemConstruction.readProblems(lines)
    var solutions = Seq.empty[String]
    problems.foreach{ problem =>
      solutions = solutions :+ breadthFirstSearch(problem).mkString(" ")
    }
    (problems, solutions)
  }

}

object Solution {
  def main(args: Array[String]): Unit = {
    val bfs = new BFSShortReach()
    val input = Source.fromInputStream(System.in)
    val (_, solutions) = bfs.resolve(input.getLines())
    solutions.foreach(println)
  }
  
  */
  
}
