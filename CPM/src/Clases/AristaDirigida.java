package clases;

public class AristaDirigida { 
    private final int v;
    private final int w;
    private final double peso;

    /**
   * Inicializa un vértice dirigido desde el vértice <tt> v </ tt> hacia vértice <tt> w </ tt> con
      * El peso <tt> dado </ tt>.
      * @param v el vértice de la cola
      * @param w el vértice de la cabeza
      * @param peso el peso del borde dirigido
      * @throws IndexOutOfBoundsException si <tt> v </ tt> o <tt> w </ tt>
      * Es un entero negativo
      * @throws IllegalArgumentException si <tt> peso </ tt> es <tt> NaN </ tt>
     */
    public AristaDirigida(int v, int w, double peso) {
        if (v < 0) throw new IndexOutOfBoundsException(
                "Los nombres de los vértices deben ser enteros no negativos");
        if (w < 0) throw new IndexOutOfBoundsException(
                "Los nombres de los vértices deben ser enteros no negativos");
        if (Double.isNaN(peso)) throw new IllegalArgumentException(
                "Peso es NaN");
        this.v = v;
        this.w = w;
        this.peso = peso;
    }

    /**
  * Devuelve el vértice de la cola del borde dirigido.
      * @ Devolver el vértice de la cola del borde dirigido
     */
    public int desde() {
        return v;
    }

    /**
     * Devuelve el vértice de la cabeza del borde dirigido.
      * @ Devolver el vértice de la cabeza del borde dirigido
     */
    public int hacia() {
        return w;
    }

    /**
    * Devuelve el peso del borde dirigido.
      * @ Devolver el peso del borde dirigido
     */
    public double peso() {
        return peso;
    }

    /**
        * Devuelve una representación de cadena del borde dirigido.
      * @return una representación de cadena del borde dirigido
     */
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", peso);
    }
    /*public static void main(String[] args) {
        AristaDirigida a = new AristaDirigida(12, 34, 5.67);
        StdOut.println(a);
    }*/
}
