/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import clases.CPM;
import cpm_interfaz.Actividad;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Seven
 */
public class EventNewTxt implements EventHandler<MouseEvent> {
    
    TableView<Actividad> aux;
    TextField tf_filas;
    int num_filas;
    File bloc;
    
    public EventNewTxt() {
        
    }
    

    public EventNewTxt(TableView<Actividad> tabla, TextField tf) {
        this.aux = tabla;
        this.tf_filas = tf;
    }

    @Override
    public void handle(MouseEvent event){
        //num_filas = Integer.parseInt(tf_filas.getText());
        //CrearArchivo();
        //LlenarData();
        CPM funcion = new CPM();
        funcion.FuncionCPM(tf_filas, aux);
    }
    
    void CrearArchivo(){
        try {
            bloc = new File("C:\\Users\\Seven\\Documents\\GitHub\\pruebaED.txt");
            if (bloc.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente");
            } else {
               if(bloc.exists())
                    System.out.println("El fichero ya existe");
               else
                    System.out.println("No ha podido ser creado el fichero");
            }
        } catch (IOException ex) {
            Logger.getLogger(EventNewTxt.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR AL CREAR ARCHIVO");
        }
    }
    
    void LlenarData(){
        if(bloc.exists()){
            FileWriter fichero = null;
            PrintWriter pw = null;
            try {
                fichero = new FileWriter(bloc.getAbsolutePath());
                pw = new PrintWriter(fichero);
                pw.println(num_filas);
                for (int i = 0; i < num_filas; i++) {
                    pw.println(aux.getItems().get(i).getData());
                }

            } catch (IOException e) {
                System.out.println("Error al escribir");
            } finally {
                try {
                    // Nuevamente aprovechamos el finally para 
                    // asegurarnos que se cierra el fichero.
                    if (null != fichero) {
                        fichero.close();
                    }
                } catch (IOException e2) {
                    System.out.println("Error al cerrar");
                }
            }
        }
    }
    
}
