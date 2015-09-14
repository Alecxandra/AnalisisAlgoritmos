
package analisis_proyecto1;

import analisis_proyecto1.ADTTree;

/**
 * Procesador de árboles
 * @author EdilsonFernando
 */
public interface TreeProcessor {
    
    /**
     * Las clases que implementen este método tendrán las instrucciones de 
     * como procesar cada nodo de un árbol específico.
     * @param obj Árbol a procesar.
     */
    public void process(ADTTree obj);
}
