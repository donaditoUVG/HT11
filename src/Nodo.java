public class Nodo implements Comparable<Nodo> {
    private char caracter;
    private int frecuencia;
    private Nodo izquierdo;
    private Nodo derecho;

    // Constructor para nodos hoja
    public Nodo(char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Constructor para nodos internos (con hijos)
    public Nodo(int frecuencia, Nodo izquierdo, Nodo derecho) {
        this.caracter = '\0'; // Carácter nulo para nodos internos
        this.frecuencia = frecuencia;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    // Métodos getter y setter
    public char getCaracter() {
        return caracter;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    // Método para verificar si el nodo es una hoja (no tiene hijos)
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    // Comparación de nodos según su frecuencia (implementación de Comparable)
    @Override
    public int compareTo(Nodo otroNodo) {
        return this.frecuencia - otroNodo.frecuencia;
    }

    // Sobrecarga del método toString para facilitar la visualización del árbol
    @Override
    public String toString() {
        return "(" + caracter + ", " + frecuencia + ")";
    }
}
