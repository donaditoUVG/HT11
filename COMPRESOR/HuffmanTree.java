import java.util.*;

public class HuffmanTree {
    private HuffmanNode root;

    // Método para construir el árbol de Huffman a partir de las frecuencias de los caracteres
    public void buildTree(Map<Character, Integer> frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(Comparator.comparingInt(HuffmanNode::getFrequency));

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            HuffmanNode node = new HuffmanNode(entry.getKey(), entry.getValue());
            queue.add(node);
        }

        while (queue.size() > 1) {
            HuffmanNode node1 = queue.poll();
            HuffmanNode node2 = queue.poll();

            HuffmanNode newNode = new HuffmanNode('\0', node1.getFrequency() + node2.getFrequency());
            newNode.setLeft(node1);
            newNode.setRight(node2);

            queue.add(newNode);
        }

        root = queue.poll();
    }

    // Método para generar los códigos de Huffman
    public Map<Character, String> generateCodes() {
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodesHelper(root, "", huffmanCodes);
        return huffmanCodes;
    }

    // Método auxiliar para generar los códigos de Huffman
    private void generateCodesHelper(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            huffmanCodes.put(node.getCharacter(), code);
        }

        generateCodesHelper(node.getLeft(), code + "0", huffmanCodes);
        generateCodesHelper(node.getRight(), code + "1", huffmanCodes);
    }

    // Método para obtener el árbol de Huffman en formato binario
    public String getBinaryTree() {
        return getBinaryTreeHelper(root);
    }

    // Método auxiliar para obtener el árbol de Huffman en formato binario
    private String getBinaryTreeHelper(HuffmanNode node) {
        if (node == null) {
            return "";
        }

        String result = "";
        if (node.getLeft() == null && node.getRight() == null) {
            result += "1";
            result += String.format("%8s", Integer.toBinaryString(node.getCharacter())).replace(' ', '0');
        } else {
            result += "0";
        }

        result += getBinaryTreeHelper(node.getLeft());
        result += getBinaryTreeHelper(node.getRight());

        return result;
    }

    // Inner class representing a node in the Huffman tree
    private class HuffmanNode {
        private char character;
        private int frequency;
        private HuffmanNode left;
        private HuffmanNode right;

        public HuffmanNode(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        public char getCharacter() {
            return character;
        }

        public int getFrequency() {
            return frequency;
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
    }
}