/******************************************************************************
 *  Compilation:  javac ST.java
 *  Execution:    java ST
 *  Dependencies: StdIn.java StdOut.java
 *  
 *  Sorted symbol table implementation using a java.util.TreeMap.
 *  Does not allow duplicates.
 *
 *  % java ST
 *
 ******************************************************************************/

package clases;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class TS<Clave extends Comparable<Clave>, Valor> implements Iterable<Clave> {

    private TreeMap<Clave, Valor> ts;

    /**
     * Initializes an empty symbol table.
     */
    public TS() {
        ts = new TreeMap<Clave, Valor>();
    }


    /**
     * Returns the value associated with the given key.
     *
     * @param  clave the key
     * @return the value associated with the given key if the key is in the symbol table;
     *         <tt>null</tt> if the key is not in the symbol table
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Valor get(Clave clave) {
        if (clave == null) throw new NullPointerException(
                "llamado a get() con clave null");
        return ts.get(clave);
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is <tt>null</tt>, this effectively deletes the key from the symbol table.cla@param  key the key
     * @param  val the value
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void insertar(Clave cla, Valor val) {
        if (cla == null) throw new NullPointerException(
                "llamado a insertar() con clave null");
        if (val == null) ts.remove(cla);
        else             ts.put(cla, val);
    }

    /**
     * Removes the key and associated value from this symbol table.
     * (if the key is in this symbol table).
     *
     * @param  cla the key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void eliminar(Clave cla) {
        if (cla == null) throw new NullPointerException(
                "llamado a eliminar() con clave nula");
        ts.remove(cla);
    }

    /**
     * Returns true if this symbol table contain the given key.
     *
     * cla  key the key
     * @return <tt>true</tt> if this symbol table contiene <tt>key</tt> and
     *         <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contiene(Clave cla) {
        if (cla == null) throw new NullPointerException(
                "llamado a contiene() con clave nula");
        return ts.containsKey(cla);
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int tamanno() {
        return ts.size();
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return <tt>true</tt> if this symbol table is empty and <tt>false</tt> otherwise
     */
    public boolean estaVacio() {
        return tamanno() == 0;
    }

    /**
     * Returns all claves in this symbol table.
     * <p>
 To iterate over all of the claves in the symbol table named <tt>st</tt>,
     * use the foreach notation: <tt>for (Key key : st.claves())</tt>.
     *
     * @return all claves in the symbol table
     */
    public Iterable<Clave> claves() {
        return ts.keySet();
    }

    /**
     * Returns all of the claves in this symbol table.
     * To iterate over all of the claves in a symbol table named <tt>st</tt>, use the
     * foreach notation: <tt>for (Key key : st)</tt>.
     * <p>
     * This method is provided for backward compatibility with the version from
     * <em>Introduction to Programming in Java: An Interdisciplinary Approach.</em>
     *
     * @return     an iterator to all of the claves in the symbol table
     * @deprecated Use {@link #keys} instead.
     */
    public Iterator<Clave> iterator() {
        return ts.keySet().iterator();
    }

    /**
     * Returns the smallest key in this symbol table.
     *
     * @return the smallest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Clave min() {
        if (estaVacio()) throw new NoSuchElementException(
                "llamado a min() con una tabla de símbolos vacía");
        return ts.firstKey();
    }

    /**
     * Returns the largest key in this symbol table.
     *
     * @return the largest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Clave max() {
        if (estaVacio()) throw new NoSuchElementException(
                "llamado a max() con una tabla de símbolo vacía");
        return ts.lastKey();
    }

    /**
     * Returns the smallest key in this symbol table greater than or equal to <tt>key</tt>.cla@param  key the key
     * @return the smallest key in this symbol table greater than or equal to <tt>key</tt>
     * @throws NoSuchElementException if there is no such key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Clave ceiling(Clave cla) {
        if (cla == null) throw new NullPointerException(
                "llamdo a ceiling() con clave nula");
        Clave k = ts.ceilingKey(cla);
        if (k == null) throw new NoSuchElementException(
                "todas las claves son menores que " + cla);
        return k;
    }

    /**
     * Returns the largest key in this symbol table less than or equal to <tt>key</tt>.
     *
     * @param  cla the key
     * @return the largest key in this symbol table less than or equal to <tt>key</tt>
     * @throws NoSuchElementException if there is no such key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public Clave floor(Clave cla) {
        if (cla == null) throw new NullPointerException(
                "llamado a floor() con clave nula");
        Clave c = ts.floorKey(cla);
        if (c == null) throw new NoSuchElementException(
                "todas las claves son mayores que " + cla);
        return c;
    }
     /*public static void main(String[] args) {
        TS<String, Integer> ts = new TS<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            ts.insertar(key, i);
        }
        for (String s : ts.claves())
            StdOut.println(s + " " + ts.get(s));
    }*/
}
