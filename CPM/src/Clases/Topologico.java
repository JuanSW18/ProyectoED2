package clases;

public class Topologico {
    private Iterable<Integer> orden;  // Orden topológica
    private int[] rango;      // rango [v] = posición del vértice v en orden topológico

    /**
      * Determina si el digrafo <tt> G </ tt> tiene una orden topológica y, si es así,
  Encuentra tal orden topológica.
      * @param G el dígrafo
     */
    public Topologico(Digrafo G) {
        CicloDirigido descubridor = new CicloDirigido(G);
        if (!descubridor.tieneCiclo()) {
            OrdenPrimeraProfundidad dfs = new OrdenPrimeraProfundidad(G);
            orden = dfs.postReverso();
            rango = new int[G.V()];
            int i = 0;
            for (int v : orden)
                rango[v] = i++;
        }
    }

    /**
     * Determina si el digrafo ponderado por el borde <tt> G </ tt> tiene una topología
  Orden y, si es así, encuentra tal orden.
      * @param G el digrafo ponderado por el borde
     */
    public Topologico(DigrafoAristaPonderada G) {
        AristaPonderadaDeCicloDirigido descubridor = 
                new AristaPonderadaDeCicloDirigido(G);
        if (!descubridor.tieneCiclo()) {
            OrdenPrimeraProfundidad dfs = new OrdenPrimeraProfundidad(G);
            orden = dfs.postReverso();
        }
    }

    /**
    * Devuelve un orden topológico si el digrafo tiene un orden topológico,
  Y <tt> null </ tt> de lo contrario.
      * @ Devolver un orden topológico de los vértices (como un interable) si el
     Digraph tiene un orden topológico (o equivalentemente, si el digraph es un DAG),
     Y <tt> null </ tt> de lo contrario
     */
    public Iterable<Integer> orden() {
        return orden;
    }

    /**
    ¿Tiene el digraph un orden topológico?
      * @return <tt> true </ tt> si el digraph tiene un orden topológico (o equivalentemente,
     Si el digraph es un DAG), y <tt> false </ tt> de lo contrario
     */
    public boolean tieneOrden() {
        return orden != null;
    }

    /**
     * El rango de vértice <tt> v </ tt> en el orden topológico;
  -1 si el digraph no es un DAG
      * @ Devolver la posición de vértice <tt> v </ tt> en un orden topológico
     Del dígrafo; -1 si el digraph no es un DAG
      * @throws IndexOutOfBoundsException a menos que <tt> v </ tt> esté entre 0 y
      * <Em> V </ em> y menos; 1
     */
    public int rango(int v) {
        validarVertice(v);
        if (tieneOrden()) return rango[v];
        else              return -1;
    }

    // Lanzar un IndexOutOfBoundsException a menos que 0 <= v <V
    private void validarVertice(int v) {
        int V = rango.length;
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException(
                    "vértice " + v + " no está entre 0 y " + (V-1));
    }
    /**
     * Unit tests the <tt>Topologico</tt> data type.
     */
    /*public static void main(String[] args) {
        String nombreArchivo  = args[0];
        String delimitador = args[1];
        DigrafoDeSimbolos gs = new DigrafoDeSimbolos(nombreArchivo, delimitador);
        Topologico topologico = new Topologico(gs.G());
        for (int v : topologico.orden()) {
            StdOut.println(gs.nombre(v));
        }
    }*/
}
