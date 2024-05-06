import java.io.*;
import java.util.*;

public class DescompresorHuffman {

    public static void descomprimir(String archivoEntrada, String archivoSalida) {
        try {
            // Leer el archivo comprimido
            BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));

            // Leer el mapa de códigos de Huffman
            Map<String, Character> codigosHuffman = leerCodigosHuffman(lector);

            // Leer y descomprimir el contenido
            String contenidoComprimido = lector.readLine();
            String contenidoDescomprimido = descomprimirContenido(contenidoComprimido, codigosHuffman);

            // Escribir el contenido descomprimido en el archivo de salida
            try (FileWriter escritor = new FileWriter(archivoSalida)) {
                escritor.write(contenidoDescomprimido);
            }

            System.out.println("Descompresión completada.");
        } catch (IOException e) {
            System.err.println("Error al descomprimir el archivo: " + e.getMessage());
        }
    }

    private static Map<String, Character> leerCodigosHuffman(BufferedReader lector) throws IOException {
        Map<String, Character> codigosHuffman = new HashMap<>();
        String linea;
        while ((linea = lector.readLine()) != null && !linea.isEmpty()) {
            char c = linea.charAt(0);
            String codigo = linea.substring(2);
            codigosHuffman.put(codigo, c);
        }
        return codigosHuffman;
    }

    private static String descomprimirContenido(String contenidoComprimido, Map<String, Character> codigosHuffman) {
        StringBuilder contenidoDescomprimido = new StringBuilder();
        StringBuilder codigoActual = new StringBuilder();
        for (char bit : contenidoComprimido.toCharArray()) {
            codigoActual.append(bit);
            Character c = codigosHuffman.get(codigoActual.toString());
            if (c != null) {
                contenidoDescomprimido.append(c);
                codigoActual.setLength(0);  // Limpiar el código actual
            }
        }
        return contenidoDescomprimido.toString();
    }
}