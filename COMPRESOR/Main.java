import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Leer el archivo de entrada
        File inputFile = new File("C:\\Users\\lirof\\OneDrive\\Escritorio\\HDTULTIMATE\\HT11\\texto.txt");

        // Calcular las frecuencias de los caracteres
        Map<Character, Integer> frequencies = new HashMap<>();
        try {
            for (char c : Files.readString(inputFile.toPath()).toCharArray()) {
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crear el árbol de Huffman
        HuffmanTree tree = new HuffmanTree();
        tree.buildTree(frequencies);

        // Generar los códigos de Huffman
        Map<Character, String> codes = tree.generateCodes();

        // Escribir el archivo comprimido
        try {
            String compressedText = compressText(inputFile, codes);
            System.out.println("Mensaje comprimido: " + compressedText);
            Files.write(Paths.get("C:\\Users\\lirof\\OneDrive\\Escritorio\\HDTULTIMATE\\HT11\\salida.huff"), compressedText.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Escribir el árbol de Huffman en un archivo
        try {
            String binaryTree = tree.getBinaryTree();
            System.out.println("Árbol de Huffman: " + binaryTree);
            Files.write(Paths.get("C:\\Users\\lirof\\OneDrive\\Escritorio\\HDTULTIMATE\\HT11\\arbol.tree"), binaryTree.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calcular y mostrar el ratio de compresión
        try {
            double compressionRatio = calculateCompressionRatio(inputFile, codes);
            System.out.println("Ratio de compresión: " + compressionRatio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String compressText(File inputFile, Map<Character, String> codes) throws IOException {
        StringBuilder compressedText = new StringBuilder();
        String text = Files.readString(inputFile.toPath());
        for (char c : text.toCharArray()) {
            compressedText.append(codes.get(c));
        }
        return compressedText.toString();
    }

    private static double calculateCompressionRatio(File inputFile, Map<Character, String> codes) throws IOException {
        String originalText = Files.readString(inputFile.toPath());
        String compressedText = compressText(inputFile, codes);
        return (double) compressedText.length() / originalText.length();
    }
}