
public class HuffmanDecoding {
    private HuffmanNode root;

    // Método para construir el árbol de Huffman a partir del árbol binario dado
    public void buildTreeFromBinary(String binaryTree) {
        int[] index = {0};
        root = buildTreeHelper(binaryTree, index);
    }

    // Método auxiliar para construir el árbol de Huffman a partir del árbol binario dado
    private HuffmanNode buildTreeHelper(String binaryTree, int[] index) {
        if (index[0] >= binaryTree.length()) {
            return null;
        }

        char bit = binaryTree.charAt(index[0]++);
        if (bit == '1') {
            char character = (char) Integer.parseInt(binaryTree.substring(index[0], index[0] + 8), 2);
            index[0] += 8;
            return new HuffmanNode(character, 0);
        } else {
            HuffmanNode node = new HuffmanNode('\0', 0);
            node.setLeft(buildTreeHelper(binaryTree, index));
            node.setRight(buildTreeHelper(binaryTree, index));
            return node;
        }
    }

    // Método para decodificar el mensaje binario utilizando el árbol de Huffman
    public String decodeMessage(String encodedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encodedMessage.toCharArray()) {
            if (bit == '0') {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }

            if (current.isLeaf()) {
                decodedMessage.append(current.getCharacter());
                current = root;
            }
        }
        return decodedMessage.toString();
    }

    // Inner class representing a node in the Huffman tree
    private static class HuffmanNode {
        private char character;
        private HuffmanNode left;
        private HuffmanNode right;

        public HuffmanNode(char character, int frequency) {
            this.character = character;
        }

        public char getCharacter() {
            return character;
        }

        public HuffmanNode getLeft() {
            return left;
        }

        public void setLeft(HuffmanNode left) {
            this.left = left;
        }

        public HuffmanNode getRight() {
            return right;
        }

        public void setRight(HuffmanNode right) {
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }
    
    public static void main(String[] args) {
        HuffmanDecoding huffmanDecoding = new HuffmanDecoding();
        String binaryTree = "0010010000000001010001011010010100101000100101100111101110010010110100110110000100000101101000100101110101101100101100100000101001001101110101101100011010111001101010011011011110010010110111010110111100101110100101101101101100101";
        huffmanDecoding.buildTreeFromBinary(binaryTree);
        String encodedMessage = "10000001100011101101111001100011111101111100011010110000100011101101101111000100101101110001111001110100101000000111111010010101101001010101011111110011100100011011110001011111011110010110110001001100010000100010010101110111001001111100100111110010111011011100111001111101010101011010110111001101100001";
        String decodedMessage = huffmanDecoding.decodeMessage(encodedMessage);
        System.out.println("Mensaje decodificado: " + decodedMessage);
    }
}
