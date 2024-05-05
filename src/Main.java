import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        String archivoEntrada = "C:/Users/josep/OneDrive - UVG/SEMESTRE III/Poo/HT11/HT11/lib/texto.txt"; //archivo de entrada
        String archivoSalida = "salida.huff"; // Nombre del archivo comprimido
        
        try {
            byte[] contenido = Files.readAllBytes(Paths.get(archivoEntrada));
            String texto = new String(contenido);
            System.out.println("Contenido del archivo:");
            System.out.println(texto);
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        CompresorHuffman.comprimir(archivoEntrada, archivoSalida);
    }
}
