import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Donado
 * Pruebas para el método de compresión
 */
public class HuffmanTreeTest {

    @Test
    public void testGenerateCodes() {
        // Crear un objeto HuffmanTree
        HuffmanTree huffmanTree = new HuffmanTree();

        // Construcción del árbol  Huffman con la frecuencias de los caracteres como valores de cada key
        Map<Character, Integer> frequencies = new HashMap<>();
        frequencies.put('a', 5);
        frequencies.put('b', 9);
        frequencies.put('c', 12);
        frequencies.put('d', 13);
        frequencies.put('e', 16);
        frequencies.put('f', 45);
        huffmanTree.buildTree(frequencies);

        // códigos de Huffman
        Map<Character, String> expectedCodes = new HashMap<>();
        expectedCodes.put('a', "1100");
        expectedCodes.put('b', "1101");
        expectedCodes.put('c', "100");
        expectedCodes.put('d', "101");
        expectedCodes.put('e', "111");
        expectedCodes.put('f', "0");
        Map<Character, String> actualCodes = huffmanTree.generateCodes();

        // Verificar si los códigos generados son los requeridos
        assertEquals(expectedCodes, actualCodes);
    }
}
