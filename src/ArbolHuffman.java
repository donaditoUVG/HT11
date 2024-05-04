import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;

public class ArbolHuffman {
    private Nodo raiz;

    // Constructor
    public ArbolHuffman() {
        this.raiz = null;
    }

    /**
     * Método para construir el árbol de Huffman con base en un arreglo de frecuencias
     * @param frecuencias
     */
    // 
    public void construirArbol(int[] frecuencias) {
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();

        // Crear un nodo hoja para cada carácter con frecuencia mayor que cero. Luego se van a ir sumando los pesos
        for (char i = 0; i < frecuencias.length; i++) {
            if (frecuencias[i] > 0) {
                colaPrioridad.offer(new Nodo((char) i, frecuencias[i]));
            }
        }

        // Construir el árbol fusionando dos nodos de menor frecuencia. La prioridad se le da a los nodos de menor frecuencia
        while (colaPrioridad.size() > 1) {
            Nodo nodo1 = colaPrioridad.poll(); //Poll de Priority queue para extraer nodos de menor frecuencia de la cola.
            Nodo nodo2 = colaPrioridad.poll();
            Nodo nodoFusionado = new Nodo(nodo1.getFrecuencia() + nodo2.getFrecuencia(), nodo1, nodo2);
            colaPrioridad.offer(nodoFusionado);
        }

        // La raíz del árbol será el único nodo restante en la cola de prioridad. La suma más grande
        raiz = colaPrioridad.poll();
    }

    // Método para generar el código de Huffman recursivamente (lo obtuve de ChatGPT)
    private void generarCodigoHuffman(Nodo nodo, String codigo, Map<Character, String> mapaCodigos) {
        if (nodo != null) {
            if (nodo.esHoja()) {
                mapaCodigos.put(nodo.getCaracter(), codigo);
            } else {
                generarCodigoHuffman(nodo.getIzquierdo(), codigo + "0", mapaCodigos);
                generarCodigoHuffman(nodo.getDerecho(), codigo + "1", mapaCodigos);
            }
        }
    }

    // Método para generar el código de Huffman para cada carácter del árbol
    public Map<Character, String> generarCodigoHuffman() {
        Map<Character, String> mapaCodigos = new HashMap<>();
        generarCodigoHuffman(raiz, "", mapaCodigos);
        return mapaCodigos;
    }
}
