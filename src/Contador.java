import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Contador {
    public static void main(String[] args) {
        String nombreArchivo = "C:/Users/josep/OneDrive - UVG/SEMESTRE III/Poo/HT11/HT11/LIB/texto.txt";
        Map<Character, Integer> frecuenciaLetras = new HashMap<>();
        try {
            contarFrecuencia(nombreArchivo, frecuenciaLetras);
            imprimirFrecuenciaLetras(frecuenciaLetras);

            

            

        } catch (IOException e) {
            System.err.println("Error de lectura: " + e.getMessage());
        }
    }

/**
 * Función para leer el archivo y contar cuántas veces aparecen las letras
 * El Hashmap almacena como key a la letra (caracter) y de Key su frecuencia
 * @param nombreArchivo
 * @param frecuenciaLetras
 * @throws IOException
 */
    public static void contarFrecuencia(String nombreArchivo, Map<Character, Integer> frecuenciaLetras) throws IOException{
        FileReader archivo = new FileReader(nombreArchivo);
        BufferedReader lector = new BufferedReader(archivo);

        String linea;
        while ((linea = lector.readLine()) != null) {
            for (char letra : linea.toCharArray()) {
                if (letra != ' ') { // Ignora los espacios en blanco
                    frecuenciaLetras.put(letra, frecuenciaLetras.getOrDefault(letra, 0) + 1);
                }
            }
        }

            lector.close();
    }

    // Función para imprimir la frecuencia de las letras
    public static void imprimirFrecuenciaLetras(Map<Character, Integer> frecuenciaLetras) {
        System.out.println("Letra  |" + "Frecuencia");
        for (Map.Entry<Character, Integer> entry : frecuenciaLetras.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " veces");
        }
    }

}