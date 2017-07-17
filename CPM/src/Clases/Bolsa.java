package clases;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bolsa<Item> implements Iterable<Item> {
    private int N;               // número de elementos en la bolsa
    private Nodo<Item> primero;    // inicio de la bolsa

    // clase de lista enlazada auxiliar
    private static class Nodo<Item> {
        private Item item;
        private Nodo<Item> prox;
    }

    /**
     *Inicializa una bolsa vacía.
     */
    public Bolsa() {
        primero = null;
        N = 0;
    }

    /**
    * Devuelve true si esta bolsa está vacía.
      *
      * @return <tt> true </ tt> si esta bolsa está vacía;
      * <Tt> false </ tt> de lo contrario
     */
    public boolean estaVacio() {
        return primero == null;
    }

    /**
     * Devuelve el número de artículos en esta bolsa.
      *
      * @ Devolver el número de artículos en esta bolsa
     */
    public int tamanno() {
        return N;
    }

    /**
    * Añade el artículo a esta bolsa.
      *
      * @param item el elemento a agregar a esta bolsa
     */
    public void agregar(Item item) {
        Nodo<Item> anteriorPrimero = primero;
        primero = new Nodo<Item>();
        primero.item = item;
        primero.prox = anteriorPrimero;
        N++;
    }


    /**
     * Devuelve un iterador que itera sobre los elementos de esta bolsa en orden arbitrario.
      *
      * @return un iterador que itera sobre los artículos en esta bolsa en orden arbitrario
     * @return 
     */
    @Override
    public Iterator<Item> iterator()  {
        return new IteradorDeLista<Item>(primero);  
    }

    // Un iterador, no implementa remove () ya que es opcional
    private class IteradorDeLista<Item> implements Iterator<Item> {
        private Nodo<Item> actual;

        public IteradorDeLista(Nodo<Item> primero) {
            actual = primero;
        }

        @Override
        public boolean hasNext()  { return actual != null;                    }
        @Override
        public void remove()      { throw new UnsupportedOperationException();}

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = actual.item;
            actual = actual.prox; 
            return item;
        }
    }
    /*public static void main(String[] args) {
        Bolsa<String> bolsa = new Bolsa<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bolsa.agregar(item);
        }

        StdOut.println("tamaño de bolsa = " + bolsa.tamanno());
        for (String s : bolsa) {
            StdOut.println(s);
        }
    }*/
}
