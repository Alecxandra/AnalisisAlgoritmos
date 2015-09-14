/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisis_proyecto1;

import edu.uci.ics.jung.graph.DelegateTree;
import java.util.ArrayList;

/**
 *
 * @author alecx
 */
public class Heapsort {
    int[] numeros;
    int N;
    ArrayList<BinaryTree> arboles;
    ArrayList<DelegateTree<String, String>> arbolesDelegate;

    public Heapsort(int[] numeros) {
        this.numeros = numeros;
        arboles = new ArrayList<>();
        arbolesDelegate = new ArrayList();
    }

    public ArrayList<BinaryTree> getArboles() {
        return arboles;
    }

    public void setArboles(ArrayList<BinaryTree> arboles) {
        this.arboles = arboles;
    }

    public void Intercambiar(int i, int j) {
        int tmp = numeros[i];
        numeros[i] = numeros[j];
        numeros[j] = tmp;
    }

    public void Maximo(int i) {
        int izquierda = 2 * i;
        int derecha = 2 * i + 1;
        int maximo = i;
        if (izquierda <= N && numeros[izquierda] > numeros[i]) {
            maximo = izquierda;
        }
        if (derecha <= N && numeros[derecha] > numeros[maximo]) {
            maximo = derecha;
        }
        if (maximo != i) {
            Intercambiar(i, maximo);
            Maximo(maximo);
        }
    }

    public void heap() {
        N = numeros.length - 1;
        for (int i = N / 2; i >= 0; i--) {
            Maximo(i);
        }
    }

    public void ordenar() {
        heap();
        for (int i = N; i > 0; i--) {
            Intercambiar(0, i);
            N = N - 1;
            Maximo(0);
            
            //Creando arbol
            BinaryTree currentBinary = new BinaryTree(numeros[0]);
            heapTree(currentBinary, 0);
            arboles.add(currentBinary);
            
            //Creando delegate tree
            DelegateTree<String, String> delegate = new DelegateTree();
            delegate.setRoot(Integer.toString(numeros[0]));
            heapTree(delegate, 0);
            arbolesDelegate.add(delegate);
        }
    }
    
    public void heapTree(BinaryTree tree, int pos) {
        if (left(pos) < numeros.length) {
            BinaryTree leftC = new BinaryTree(numeros[left(pos)]);
            leftC.setFather(tree);
            tree.setLeftChild(leftC);
            heapTree(leftC, left(pos));
        }
        
        if (right(pos) < numeros.length) {
            BinaryTree rightC = new BinaryTree(numeros[right(pos)]);
            rightC.setFather(tree);
            tree.setRightChild(rightC);
            heapTree(rightC, right(pos));
        }
    }
    
    public void heapTree(DelegateTree<String, String> tree, int pos) {
        if (left(pos) < numeros.length) {
            tree.addChild(numeros[pos] + "-" + numeros[left(pos)], Integer.toString(numeros[pos]), Integer.toString(numeros[left(pos)]));
            heapTree(tree, left(pos));
        }
        
        if (right(pos) < numeros.length) {
            tree.addChild(numeros[pos] + "-" + numeros[right(pos)], Integer.toString(numeros[pos]), Integer.toString(numeros[right(pos)]));
            heapTree(tree, right(pos));
        }
    }
    
    int left(int i) {
        return 2*i + 1;
    }
    int right(int i) {
        return 2*i + 2;
    }

    public ArrayList<DelegateTree<String, String>> getArbolesDelegate() {
        return arbolesDelegate;
    }
    
    
  
}
