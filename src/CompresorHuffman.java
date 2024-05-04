import java.io.*;
import java.util.*;

public class CompresorHuffman {

    // Método principal para comprimir un archivo de texto
    public static void comprimir(String archivoEntrada, String archivoSalida) {
        try {
            // Leer el archivo de entrada y contar las frecuencias de los caracteres
            Map<Character, Integer> frecuencias = contarFrecuencias(archivoEntrada);
            
            // Construir el árbol de Huffman a partir de las frecuencias
            ArbolHuffman arbolHuffman = construirArbolHuffman(frecuencias);
            
            // Generar el código de Huffman para cada carácter del árbol
            Map<Character, String> codigosHuffman = arbolHuffman.generarCodigoHuffman();


            System.out.println("Mapa de códigos Huffman:");
            for (Map.Entry<Character, String> entry : codigosHuffman.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            
            // Escribir el código de Huffman y el archivo comprimido
            escribirArchivoComprimido(archivoEntrada, archivoSalida, codigosHuffman);
            
            System.out.println("Compresión completada.");
        } catch (IOException e) {
            System.err.println("Error al comprimir el archivo: " + e.getMessage());
        }
    }

// Método para contar las frecuencias de todos los caracteres presentes en el archivo de texto
private static Map<Character, Integer> contarFrecuencias(String archivoEntrada) throws IOException {
    Map<Character, Integer> frecuencias = new HashMap<>();
    try (FileInputStream fis = new FileInputStream(archivoEntrada)) {
        int caracter;
        while ((caracter = fis.read()) != -1) {
            char c = (char) caracter;
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }
    }
    return frecuencias;
}



    // Método para construir el árbol de Huffman a partir de las frecuencias
    private static ArbolHuffman construirArbolHuffman(Map<Character, Integer> frecuencias) {
        ArbolHuffman arbolHuffman = new ArbolHuffman();
        arbolHuffman.construirArbol(frecuencias.values().stream().mapToInt(Integer::intValue).toArray());
        return arbolHuffman;
    }

    // Método para escribir el código de Huffman y el archivo comprimido
    private static void escribirArchivoComprimido(String archivoEntrada, String archivoSalida, Map<Character, String> codigosHuffman) throws IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
             BitOutputStream escritor = new BitOutputStream(new FileOutputStream(archivoSalida))) {
            
            // Escribir los códigos de Huffman en el archivo comprimido
            escribirCodigosHuffman(escritor, codigosHuffman);
            
            // Escribir el contenido del archivo comprimido
            escribirContenidoComprimido(lector, escritor, codigosHuffman);
        }
    }

    // Método para escribir los códigos de Huffman en el archivo comprimido
    private static void escribirCodigosHuffman(BitOutputStream escritor, Map<Character, String> codigosHuffman) throws IOException {
        escritor.escribirEntero(codigosHuffman.size());
        for (Map.Entry<Character, String> entry : codigosHuffman.entrySet()) {
            escritor.escribirCaracter(entry.getKey());
            escritor.escribirCadena(entry.getValue());
        }
    }

    // Método para escribir el contenido del archivo comprimido
    private static void escribirContenidoComprimido(BufferedReader lector, BitOutputStream escritor, Map<Character, String> codigosHuffman) throws IOException {
        int caracter;
        while ((caracter = lector.read()) != -1) {
            char c = (char) caracter;
            String codigo = codigosHuffman.get(c);
            if (codigo != null) {
                escritor.escribirCadena(codigo);
            } else {
                throw new IOException("El código Huffman para el carácter '" + c + "' es nulo.");
            }
        }
    }
    

    // Clase auxiliar para escribir bits en un archivo binario
    private static class BitOutputStream implements Closeable {
        private OutputStream salida;
        private int buffer;
        private int numBitsEnBuffer;

        // Constructor
        public BitOutputStream(OutputStream salida) {
            this.salida = salida;
            this.buffer = 0;
            this.numBitsEnBuffer = 0;
        }

        // Método para escribir un bit en el archivo
        public void escribirBit(int bit) throws IOException {
            if (numBitsEnBuffer == 8) {
                limpiarBuffer();
            }
            buffer |= (bit & 1) << (7 - numBitsEnBuffer);
            numBitsEnBuffer++;
        }

        // Método para escribir una cadena de bits en el archivo
        public void escribirCadena(String cadena) throws IOException {
            for (char bit : cadena.toCharArray()) {
                escribirBit(bit - '0');
            }
        }

        // Método para escribir un entero en el archivo (como cadena binaria)
        public void escribirEntero(int valor) throws IOException {
            escribirCadena(Integer.toBinaryString(valor));
        }

        // Método para escribir un carácter en el archivo
        public void escribirCaracter(char c) throws IOException {
            salida.write(c);
        }

        // Método para limpiar el buffer y escribir los bits pendientes en el archivo
        private void limpiarBuffer() throws IOException {
            if (numBitsEnBuffer > 0) {
                salida.write(buffer);
                buffer = 0;
                numBitsEnBuffer = 0;
            }
        }

        // Cerrar el flujo de salida
        @Override
        public void close() throws IOException {
            limpiarBuffer();
            salida.close();
        }
    }
}
