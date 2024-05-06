import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ArbolHuffman {
    private Nodo raiz;
    private int[] frecuencias;

    // Constructor
    public ArbolHuffman() {
        this.raiz = null;
    }

    /**
     * Método para construir el árbol de Huffman con base en un arreglo de frecuencias
     * @param frecuencias
     */
    public void construirArbol(int[] frecuencias) {
        this.frecuencias = frecuencias;
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();

        // Crear un nodo hoja para cada carácter con frecuencia mayor que cero. Luego se van a ir sumando los pesos
        for (int i = 0; i < frecuencias.length; i++) {
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

    // Método para generar el código de Huffman para cada carácter del árbol
    public Map<Character, String> generarCodigoHuffman() {
        Map<Character, String> mapaCodigos = new HashMap<>();
        generarCodigoHuffman(raiz, "", mapaCodigos);
        return mapaCodigos;
    }

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

    public static void main(String[] args) {
        String rutaArchivo = "C:\\Users\\lirof\\OneDrive\\Escritorio\\HDT9\\HT11\\lib\\texto.txt";
        String texto;
        try {
            texto = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        int[] frecuencias = new int[256];

        // Calcular frecuencias de los caracteres en el texto
        for (char c : texto.toCharArray()) {
            frecuencias[c]++;
        }

        // Construir el árbol de Huffman
        ArbolHuffman arbol = new ArbolHuffman();
        arbol.construirArbol(frecuencias);

        // Generar el código de Huffman para cada carácter
        Map<Character, String> codigos = arbol.generarCodigoHuffman();

        // Comprimir el texto usando el código de Huffman
        StringBuilder textoComprimido = new StringBuilder();
        for (char c : texto.toCharArray()) {
            textoComprimido.append(codigos.get(c));
        }

        // Guardar el texto comprimido en un archivo
        String rutaArchivoSalida = "C:\\Users\\lirof\\OneDrive\\Escritorio\\HDT9\\HT11\\salida.huff";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivoSalida))) {
            writer.write(textoComprimido.toString());
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
