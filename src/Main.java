public class Main {
    public static void main(String[] args) {
        String archivoEntrada = "C:/Users/josep/OneDrive - UVG/SEMESTRE III/Poo/HT11/HT11/lib/texto.txt"; //archivo de entrada
        String archivoSalida = "salida.huff"; // Nombre del archivo compreso
        
        CompresorHuffman.comprimir(archivoEntrada, archivoSalida);
    }
}
