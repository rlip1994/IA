/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusEvento;

import com.google.common.eventbus.EventBus;

/**
 *
 * @author Test
 */
public class BusEvento {
    private static EventBus instancia;
    // ----------------------------------------
    private BusEvento() {
      
    }
    // ----------------------------------------
     public static EventBus getBus()
    {
         if (instancia == null) {
             instancia = new EventBus();
        }
         return instancia;
    }
}
