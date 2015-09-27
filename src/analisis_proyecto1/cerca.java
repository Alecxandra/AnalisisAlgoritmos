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
public class cerca {
    puntos[] pares = new puntos[2];
    ArrayList<Integer> medianas = new ArrayList();
    ArrayList<puntos> puntosoriginales = new ArrayList();
    ArrayList<bitacora_puntos> bitacora = new ArrayList();

    public ArrayList<bitacora_puntos> getBitacora() {
        return bitacora;
    }
    public cerca(ArrayList<puntos> puntosoriginales) {
        this.puntosoriginales = puntosoriginales;
    }

    public puntos[] getPares() {
        return pares;
    }

    public ArrayList<Integer> getMedianas() {
        return medianas;
    }

    public void sortx(puntos[] puntos) {
        for (int i = 0; i < puntos.length - 1; i++) {
            int minimo = i;
            for (int j = i + 1; j < puntos.length; j++) {
                if (puntos[j].getX() < puntos[minimo].getX()) {
                    minimo = j;
                }

            }
            if (i != minimo) {
                puntos temp = puntos[i];
                puntos[i] = puntos[minimo];
                puntos[minimo] = temp;
            }
        }

    }

    public void sorty(puntos[] puntos) {
        for (int i = 0; i < puntos.length - 1; i++) {
            int minimo = i;
            for (int j = i + 1; j < puntos.length; j++) {
                if (puntos[j].getY() < puntos[minimo].getY()) {
                    minimo = j;
                }

            }
            if (i != minimo) {
                puntos temp = puntos[i];
                puntos[i] = puntos[minimo];
                puntos[minimo] = temp;
            }
        }

    }

    public double distance(puntos p1, puntos p2) {
        double suma = Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2);
        double result = Math.sqrt(suma);
        return result;
    }

    public puntos_cercanos minimo(puntos_cercanos x, puntos_cercanos y) {
        if (x.getDistancia() > y.getDistancia()) {
            return y;
        }
        return x;
    }

    public puntos_cercanos fuerzaBruta(puntos[] puntos) {
        puntos[] p= new puntos[2];
        double distancia_minima = Double.MAX_VALUE;
        for (int i = 0; i < puntos.length; i++) {
            for (int j = i + 1; j < puntos.length; j++) {
                double dist = distance(puntos[i], puntos[j]);
                  if (dist < distancia_minima) {
                    distancia_minima = dist;
                    p[0] = puntos[i];
                    p[1] = puntos[j];
                    
                }
            }
        }
        puntos_cercanos x =new puntos_cercanos(p,distancia_minima);
        return x;
    }

    public puntos_cercanos ultimos_puntos(puntos[] cercanos, int tam, puntos_cercanos minimo) {
        puntos[] p= new puntos[2];
        boolean entro=false;
        double distancia_minima= minimo.getDistancia();
        for (int i = 0; i < tam; i++) {
            for (int j = i + 1; j < tam; j++) {
                double dist = distance(cercanos[i], cercanos[j]);
                if (dist < distancia_minima) {
                    distancia_minima = dist;
                    p[0] = cercanos[i];
                    p[1] = cercanos[j];
                    entro=true;
                }
            }
        }
        if(entro==true){
        puntos_cercanos x = new puntos_cercanos(p,distancia_minima);
        return x;
        }
        return minimo;
    }

    public puntos_cercanos mas_cerca(puntos[] Px, puntos[] Py, int n) {
        //---La mitad del plano de los puntos
        if (n <= 3) {
           return fuerzaBruta(Py);
        }

        int mediana = n / 2;
        puntos punto_medio = Px[mediana];
        puntos[] copia = Py;
        sortx(copia);
        int rectam = ((copia[copia.length - 1].getX() + copia[0].getX()) / 2);
        medianas.add(rectam);

        //---diviendo el arreglo de puntos en dos
        puntos[] izquierda = new puntos[mediana + 1];
        puntos[] derecha = new puntos[n - mediana - 1];

        //--indices--
        int inderecho = -1;
        int inizquierdo = -1;
        //-----ubicando los puntos en su parte correspondiente-------

        for (int i = 0; i < (mediana + 1); i++) {
            inizquierdo += 1;
            izquierda[inizquierdo] = Py[i];
        }

        for (int i = mediana + 1; i < n; i++) {
            inderecho += 1;
            derecha[inderecho] = Py[i];
        }
        //--llamando a la funcion para que calcule el minimo de las dos mitades

        sorty(izquierda);
        sorty(derecha);
        
        puntos_cercanos distizquierda = mas_cerca(Px, izquierda, izquierda.length);
        puntos_cercanos disderecha = mas_cerca(Px, derecha, derecha.length);
        
        //--encontrando la distancia mas pequeña
        puntos_cercanos Smallestd = minimo(distizquierda, disderecha);
        
        //---Creando un arreglo con los puntos cercanos a Smallestd----
        //la linea que pasa en medio del punto
        puntos[] enmedio = new puntos[n];
        int indice = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(Py[i].getX() - punto_medio.getX()) < Smallestd.getDistancia()) {
                enmedio[indice] = Py[i];
                indice++;
            }
        }

        puntos_cercanos ultimo = ultimos_puntos(enmedio, indice, Smallestd);
        puntos_cercanos min= minimo(Smallestd, ultimo);
         System.out.println("----Antes de retorno----------");
         System.out.println(min.getPuntos()[0].getX()+","+min.getPuntos()[0].getY());
         System.out.println(min.getPuntos()[1].getX()+","+min.getPuntos()[1].getY());
         bitacora.add(new bitacora_puntos(min.getPuntos()[0],min.getPuntos()[1]));
        return min;
    }

}
