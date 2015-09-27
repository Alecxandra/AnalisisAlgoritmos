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

    public double minimo(double x, double y) {
        if (x > y) {
            return y;
        }
        return x;
    }

    public double fuerzaBruta(puntos[] puntos) {
        double distancia_minima = Double.MAX_VALUE;
        for (int i = 0; i < puntos.length; i++) {
            for (int j = i + 1; j < puntos.length; j++) {
                double dist = distance(puntos[i], puntos[j]);
                  if (dist < distancia_minima) {
                    distancia_minima = dist;
                    pares[0] = puntos[i];
                    pares[1] = puntos[j];
                    
                }
            }
        }
        
        return distancia_minima;
    }

    public double ultimos_puntos(puntos[] cercanos, int tam, double minimo) {
        for (int i = 0; i < tam; i++) {
            for (int j = i + 1; j < tam; j++) {
                double dist = distance(cercanos[i], cercanos[j]);
                if (dist < minimo) {
                    minimo = dist;
                    pares[0] = cercanos[i];
                    pares[1] = cercanos[j];
                    
                }
            }
        }
        
        return minimo;
    }

    public double mas_cerca(puntos[] Px, puntos[] Py, int n) {
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
        
        double distizquierda = mas_cerca(Px, izquierda, izquierda.length);
        System.out.println("pares 1------");
        System.out.println(pares[0].getX()+","+pares[0].getY());
        System.out.println(pares[1].getX()+","+pares[1].getY());
        double disderecha = mas_cerca(Px, derecha, derecha.length);
        System.out.println("pares 2------");
        System.out.println(pares[0].getX()+","+pares[0].getY());
        System.out.println(pares[1].getX()+","+pares[1].getY()); 
        //--encontrando la distancia mas pequeña
        double Smallestd = minimo(distizquierda, disderecha);

        //---Creando un arreglo con los puntos cercanos a Smallestd----
        //la linea que pasa en medio del punto
        puntos[] enmedio = new puntos[n];
        int indice = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(Py[i].getX() - punto_medio.getX()) < Smallestd) {
                enmedio[indice] = Py[i];
                indice++;
            }
        }

        double ultimo = ultimos_puntos(enmedio, indice, Smallestd);

        return minimo(Smallestd, ultimo);
    }

}
