package clases;

public class DigrafoAristaPonderada {
    private static final String NUEVALINEA = System.getProperty(
            "line.separator");

    private final int V;
    private int E;
    private Bolsa<AristaDirigida>[] ady;
    
    /**
     * Inicializa un digrafo ponderado de borde vacío con vértices <tt> V </ tt> y aristas 0.
      *
      * @param V el número de vértices
      * @throws IllegalArgumentException si <tt> V </ tt> <0
     */
    public DigrafoAristaPonderada(int V) {
        if (V < 0) throw new IllegalArgumentException(
                "Los números de los vértices en el Digrafo no deben"
                        + "ser negativos");
        this.V = V;
        this.E = 0;
        ady = (Bolsa<AristaDirigida>[]) new Bolsa[V];
        for (int v = 0; v < V; v++)
            ady[v] = new Bolsa<AristaDirigida>();
    }

    /**
    * Inicializa un digrafo ponderado en el borde aleatorio con vértices <tt> V </ tt> y aristas <em> E </ em>.
      *
      * @param V el número de vértices
      * @param E el número de aristas
      * @throws IllegalArgumentException si <tt> V </ tt> <0
      * @throws IllegalArgumentException si <tt> E </ tt> <0
     */
    public DigrafoAristaPonderada(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException(
                "Los números de los vértices en el Digrafo no deben"
                        + "ser negativos");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double peso = .01 * StdRandom.uniform(100);
            AristaDirigida a = new AristaDirigida(v, w, peso);
            agregarArista(a);
        }
    }

    /**  
      * Inicializa un digraph ponderado de borde desde un flujo de entrada.
      * El formato es el número de vértices <em> V </ em>, Seguido por el número de aristas <em> E </ em>,
      * Seguido de <em> E </ em> pares de vértices y pesos de los bordes,
      * Con cada entrada separada por espacios en blanco.
      *
      * @param entrada el flujo de entrada
      * @throws IndexOutOfBoundsException si los extremos de cualquier borde no están dentro del rango prescrito
      * @throws IllegalArgumentException si el número de vértices o aristas es negativo
     */
    public DigrafoAristaPonderada(In entrada) {
        this(entrada.readInt());
        int E = entrada.readInt();
        if (E < 0) throw new IllegalArgumentException(
                "El núemro de aristas no debe ser negativo");
        for (int i = 0; i < E; i++) {
            int v = entrada.readInt();
            int w = entrada.readInt();
            if (v < 0 || v >= V) 
                throw new IndexOutOfBoundsException(
                        "vértice " + v + " no está entre 0 y " + (V-1));
            if (w < 0 || w >= V) 
                throw new IndexOutOfBoundsException(
                        "vértice " + w + " no está entre 0 y " + (V-1));
            double peso = entrada.readDouble();
            agregarArista(new AristaDirigida(v, w, peso));
        }
    }

    /**
    * Inicializa un nuevo digrafo ponderado por el borde que es una copia profunda de <tt> G </ tt>.
      *
      * @param G el digrafo ponderado por el borde hacia copia
     */
    public DigrafoAristaPonderada(DigrafoAristaPonderada G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            //Reversa para que la lista de adyacencia esté en el mismo orden que la original
            Pila<AristaDirigida> reverso = new Pila<AristaDirigida>();
            for (AristaDirigida e : G.ady[v]) {
                reverso.push(e);
            }
            for (AristaDirigida e : reverso) {
                ady[v].agregar(e);
            }
        }
    }

    /**
     * Devuelve el número de vértices en este digrafo ponderado por el borde.
      *
      * @ Devolver el número de vértices en este digrafo ponderado por el borde
     */
    public int V() {
        return V;
    }

    /**
    * Devuelve el número de aristas en este digrafo ponderado por el borde.
      *
      * @ Devolver el número de aristas en este digraf ponderado
     */
    public int E() {
        return E;
    }

    // Lanzar un IndexOutOfBoundsException a menos que 0 <= v <V
    private void validarVertice(int v) {
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException(
                    "vértice" + v + " no está entre 0 y " + (V-1));
    }

    /**
     * Añade el borde dirigido <tt> e </ tt> hacia este digrafo ponderado por el borde.
      *
      * @param a the edge
      * @throws IndexOutOfBoundsException a menos que los extremos del borde estén entre 0 y V-1
     */
    public void agregarArista(AristaDirigida a) {
        int v = a.desde();
        int w = a.hacia();
        validarVertice(v);
        validarVertice(w);
        ady[v].agregar(a);
        E++;
    }


    /**
     * Devuelve el incidente aristas dirigido desde vértice <tt> v </ tt>.
      *
      * @param v el vértice
      * @return el incidente aristas dirigido desde el vértice <tt> v </ tt> como un iterable
      * @throws IndexOutOfBoundsException a menos que 0 <= v <V
     */
    public Iterable<AristaDirigida> ady(int v) {
        validarVertice(v);
        return ady[v];
    }

    /**
     * Devuelve el número de aristas dirigidas incidente desde vértice <tt> v </ tt>.
      * Esto se conoce como el <em> gradoSalida </ em> del vértice <tt> v </ tt>.
      *
      * @param v el vértice
      * @ Devolver el gradoSalida de vértice <tt> v </ tt>
      * @throws IndexOutOfBoundsException a menos que 0 <= v <V
     */
    public int gradoSalida(int v) {
        validarVertice(v);
        return ady[v].tamanno();
    }

    /**
    * Devuelve todos los aristas dirigidos en este digrafo ponderado por el borde.
      * Para iterar sobre los aristas en este digrafo ponderado, use foreach notation:
  <Tt> para (AristaDirigida e: G.aristas ()) </ tt>.
      *
      * @return todos los aristas en este digraph ponderado, como un iterable
     */
    public Iterable<AristaDirigida> aristas() {
        Bolsa<AristaDirigida> lista = new Bolsa<AristaDirigida>();
        for (int v = 0; v < V; v++) {
            for (AristaDirigida e : ady(v)) {
                lista.agregar(e);
            }
        }
        return lista;
    } 

    /**
  * Devuelve una representación de cadena de este digrafo ponderado por el borde.
      *
      * @ Devolver el número de vértices <em> V </ em>, seguido por el número de aristas <em> E </ em>,
      * Seguido por las <em> V </ em> listas de adyacencia de aristas
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NUEVALINEA);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (AristaDirigida e : ady[v]) {
                s.append(e + "  ");
            }
            s.append(NUEVALINEA);
        }
        return s.toString();
    }
    /*public static void main(String[] args) {

        // create random DAG with V vertices and E aristas; then add F random aristas
        int V = Integer.parseInt(args[0]);
        int A = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);
        DigrafoAristaPonderada G = new DigrafoAristaPonderada(V);
        int[] vertices = new int[V];
        for (int i = 0; i < V; i++)
            vertices[i] = i;
        StdRandom.shuffle(vertices);
        for (int i = 0; i < A; i++) {
            int v, w;
            do {
                v = StdRandom.uniform(V);
                w = StdRandom.uniform(V);
            } while (v >= w);
            double peso = StdRandom.uniform();
            G.agregarArista(new AristaDirigida(v, w, peso));
        }

        // add F extra aristas
        for (int i = 0; i < F; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double peso = StdRandom.uniform(0.0, 1.0);
            G.agregarArista(new AristaDirigida(v, w, peso));
        }

        StdOut.println(G);

        // find a directed ciclo
        AristaPonderadaDeCicloDirigido descubridor = 
                new AristaPonderadaDeCicloDirigido(G);
        if (descubridor.tieneCiclo()) {
            StdOut.print("Ciclo: ");
            for (AristaDirigida a : descubridor.ciclo()) {
                StdOut.print(a + " ");
            }
            StdOut.println();
        }

        // or give topologial sort
        else {
            StdOut.println("No existen ciclos dirigidos");
        }
    }*/
    
}