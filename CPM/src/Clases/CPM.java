/******************************************************************************
 *  Compilation:  javac CPM.java
 *  Execution:    java CPM < input.txt
 *  Dependencies: EdgeWeightedDigraph.java AcyclicLP.java StdOut.java
 *  Data files:   jobsPC.txt
 *
 *  Critical path method.
 *
 *  % java CPM < jobsPC.txt
 *   job   start  finish
 *  --------------------
 *     0     0.0    41.0
 *     1    41.0    92.0
 *     2   123.0   173.0
 *     3    91.0   127.0
 *     4    70.0   108.0
 *     5     0.0    45.0
 *     6    70.0    91.0
 *     7    41.0    73.0
 *     8    91.0   123.0
 *     9    41.0    70.0
 *  Finish time:   173.0
 *
 ******************************************************************************/

package clases;

import cpm_interfaz.Actividad;
import cpm_interfaz.Respuesta;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CPM {

    // Esta clase no puede ser instanciada
    public CPM() { }

    /**
     *  Lee las restricciones de prioridad desde la entrada estándar
   E imprime una programación factible hacia la salida estándar.
     * @param actividades
     * @param tabla
     * @param resp
     * @param d
     */
    public void FuncionCPM(TextField actividades, TableView<Actividad> tabla, TableView<Respuesta> resp, TextField d) {
        
        try{
            // número de puestos de trabajo
            int N = Integer.parseInt(actividades.getText());

            // fuente y sumidero
            int inicio = 2 * N;
            int fin = 2 * N + 1;

            //almacenar nombres de actividad
            String[] names = new String[N];

            // crear red
            DigrafoAristaPonderada G = new DigrafoAristaPonderada(2 * N + 2);
            for (int i = 0; i < N; i++) {
                names[i] = tabla.getItems().get(i).getNombre();

                double dur = Double.parseDouble(tabla.getItems().get(i).getTiempo());
                G.agregarArista(new AristaDirigida(inicio, i, 0.0));
                G.agregarArista(new AristaDirigida(i + N, fin, 0.0));
                G.agregarArista(new AristaDirigida(i, i + N, dur));

                String pres = tabla.getItems().get(i).getPrecedentes();
                if (!pres.equals("")) {
                    String[] lista = pres.split(",");

                    int[] lista_pre = new int[lista.length];
                    for (int j = 0; j < lista_pre.length; j++) {
                        lista_pre[j] = Integer.parseInt(lista[j]);
                    }
                    // restricciones de precedencia
                    int m = lista_pre.length;
                    for (int j = 0; j < m; j++) {
                        G.agregarArista(new AristaDirigida(N + i, lista_pre[j], 0.0));
                    }
                }
            }

            // calcular la ruta más larga
            LPAciclico cml = new LPAciclico(G, inicio);

            for (int i = 0; i < N; i++) {
                resp.getItems().add(new Respuesta(String.valueOf(i), names[i], String.valueOf(cml.distanciaHacia(i)), String.valueOf(cml.distanciaHacia(i + N))));
            }
            d.setText(String.valueOf(cml.distanciaHacia(fin)));
            //StdOut.printf("Tiempo de terminación: %7.3f\n", cml.distanciaHacia(fin));
        }catch(NumberFormatException e){
            System.out.println("Error");
        }
    }
       

}