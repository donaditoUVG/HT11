import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Aquí va el de descompresión...
 */
public class HuffmanDecodingTest {

    @Test
    public void testDecodeMessage() {
        //objeto HuffmanDecoding
        HuffmanDecoding huffmanDecoding = new HuffmanDecoding();

        // Construir el árbol de Huffman a partir del árbol binario que nos brindó Moisés
        String binaryTree = "0010010000000001010001011010010100101000100101100111101110010010110100110110000100000101101000100101110101101100101100100000101001001101110101101100011010111001101010011011011110010010110111010110111100101110100101101101101100101";
        huffmanDecoding.buildTreeFromBinary(binaryTree);

        // Decodificar el binario
        String encodedMessage = "10000001100011101101111001100011111101111100011010110000100011101101101111000100101101110001111001110100101000000111111010010101101001010101011111110011100100011011110001011111011110010110110001001100010000100010010101110111001001111100100111110010111011011100111001111101010101011010110111001101100001";
        String expectedDecodedMessage = "hi my name is Jose Donado I am currently reading El conde de Montecristo.";
        String actualDecodedMessage = huffmanDecoding.decodeMessage(encodedMessage);

        // Verificar que el texto es idéntico
        assertEquals(expectedDecodedMessage, actualDecodedMessage);
    }
}
