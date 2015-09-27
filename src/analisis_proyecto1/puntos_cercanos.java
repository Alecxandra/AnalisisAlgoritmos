/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis_proyecto1;

import java.util.ArrayList;

/**
 *
 * @author alecx
 */
public class puntos_cercanos {
   puntos[] puntos= new puntos[2];
    double distancia=0;

    public puntos_cercanos(puntos[] puntos, double distancia) {
     this.puntos= puntos;
     this.distancia= distancia;
    }

    public void setPuntos(puntos[] puntos) {
        this.puntos = puntos;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public puntos[] getPuntos() {
        return puntos;
    }

    public double getDistancia() {
        return distancia;
    }
    
    
    
    
}
