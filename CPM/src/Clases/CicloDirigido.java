/******************************************************************************
 *  Compilation:  javac DirectedCycle.java
 *  Execution:    java DirectedCycle input.txt
 *  Dependencies: Digraph.java Pila.java StdOut.java In.java
 *  Data files:   tinyDG.txt
 *                tinyDAG.txt
 *
 *  Finds a directed cycle in a digraph.
 *  Runs in O(E + V) time.
 *
 *  % java DirectedCycle tinyDG.txt 
 *  Directed cycle: 3 5 4 3 
 *
 *  %  java DirectedCycle tinyDAG.txt 
 *  No directed cycle
 *
 ******************************************************************************/

package clases;

public class CicloDirigido {
    private boolean[] marcado;        //  marcado [v] = ¿ha sido marcado el vértice v?
    private int[] aristaHacia;            // edgeTo [v] = vértice anterior en el camino a v
    private boolean[] enPila;       // onStack [v] = es el vértice de la pila?
    private Pila<Integer> ciclo;    // ciclo dirigido (o nulo si no dicho ciclo)

    /**
      * Determina si el digrafo <tt> G </ tt> tiene un ciclo dirigido y, en caso afirmativo,
  Encuentra tal ciclo.
      * @param G el dígrafo
     */
    public CicloDirigido(Digrafo G) {
        marcado  = new boolean[G.V()];
        enPila = new boolean[G.V()];
        aristaHacia  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marcado[v] && ciclo == null) dfs(G, v);
    }

    // Verifique que el algoritmo calcule el orden topológico o encuentre un ciclo dirigido
    private void dfs(Digrafo G, int v) {
        enPila[v] = true;
        marcado[v] = true;
        for (int w : G.ady(v)) {

            // Cortocircuito si dirigido ciclo encontrado
            if (ciclo != null) return;

            //Encontró nuevo vértice, así que se repite
            else if (!marcado[w]) {
                aristaHacia[w] = v;
                dfs(G, w);
            }

            // Rastrear atrás ciclo dirigido
            else if (enPila[w]) {
                ciclo = new Pila<Integer>();
                for (int x = v; x != w; x = aristaHacia[x]) {
                    ciclo.push(x);
                }
                ciclo.push(w);
                ciclo.push(v);
                assert revisar();
            }
        }
        enPila[v] = false;
    }

    /**
     * ¿Tiene el digrafo un ciclo dirigido?
      * @return <tt> true </ tt> si el digraph tiene un ciclo dirigido, <tt> false </ tt> de lo contrario
     */
    public boolean tieneCiclo() {
        return ciclo != null;
    }

    /**
       * Devuelve un ciclo dirigido si el digrafo tiene un ciclo dirigido, y <tt> nulo </ tt> de lo contrario.
      * @ Devolver un ciclo dirigido (como iterable) si el digraph tiene un ciclo dirigido,
     Y <tt> null </ tt> de lo contrario
     */
    public Iterable<Integer> ciclo() {
        return ciclo;
    }


    // certifica que el digraph tiene un ciclo dirigido si informa uno
    private boolean revisar() {

        if (tieneCiclo()) {
            // verificar ciclo
            int primero = -1, ultimo = -1;
            for (int v : ciclo()) {
                if (primero == -1) primero = v;
                ultimo = v;
            }
            if (primero != ultimo) {
                System.err.printf("el ciclo comienza con %d y termina con %d\n",
                        primero, ultimo);
                return false;
            }
        }
        return true;
    }
    /*public static void main(String[] args) {
        In entrada = new In(args[0]);
        Digrafo G = new Digrafo(entrada);

        CicloDirigido descubridor = new CicloDirigido(G);
        if (descubridor.tieneCiclo()) {
            StdOut.print("Ciclo Dirigido: ");
            for (int v : descubridor.ciclo()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No hay ciclos dirigidos");
        }
        StdOut.println();
    }*/

}
