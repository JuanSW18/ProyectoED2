/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import clases.CPM;
import cpm_interfaz.Actividad;
import cpm_interfaz.Respuesta;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Seven
 */
public class EventOutput implements EventHandler<MouseEvent> {
    
    TableView<Actividad> aux;
    TextField tf_filas;
    int num_filas;
    //File bloc;
    TableView<Respuesta> tabla_resp;
    TextField tf_dur = new TextField();;
    
    public EventOutput() {
        
    }
    

    public EventOutput(TableView<Actividad> tabla, TextField tf, TableView<Respuesta> r) {
        this.aux = tabla;
        this.tf_filas = tf;
        this.tabla_resp = r;
    }

    @Override
    public void handle(MouseEvent event){
        CrearTabla();
        CPM funcion = new CPM();
        funcion.FuncionCPM(tf_filas, aux, tabla_resp, tf_dur);
        VentanaRespuesta();
    }
    
    public void VentanaRespuesta(){
        GridPane g2 = new GridPane();
        g2.setVgap(5);
        g2.setHgap(5);
        
        Text txt = new Text("Duracion: ");
        tf_dur.setPrefHeight(5);
        tf_dur.setEditable(false);
        
        g2.add(txt, 0, 0);
        g2.add(tf_dur, 1, 0);
        
        Button btn_imprimir = new Button("Imprimir", new ImageView(new Image("Recursos/print_24.png")));
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        
        
        grid.add(tabla_resp, 0, 0);
        grid.add(g2, 0, 1);
        grid.add(btn_imprimir, 0,2);
        Scene scene = new Scene(grid, 350, 500);
        
        Stage stage2 = new Stage();
        stage2.setTitle("Tabla de Resultados");
        stage2.setScene(scene);
        stage2.getIcons().add(new Image("Recursos/table_24.png"));
        stage2.setResizable(false);
        //stage2.initModality(Modality.APPLICATION_MODAL);
        stage2.showAndWait();
    }
    
    public void CrearTabla(){
        tabla_resp.setMaxSize(335, 500);
        TableColumn<Respuesta, String> colId = new TableColumn<>("Nro");
        colId.setCellValueFactory(new PropertyValueFactory<>("num_act"));
        colId.setMinWidth(40);
        
        TableColumn<Respuesta, String> colNombre = new TableColumn<>("Actividad");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("actividad"));
        colNombre.setMinWidth(200);
        
        TableColumn<Respuesta, String> colTime = new TableColumn<>("Inicio");
        colTime.setCellValueFactory(new PropertyValueFactory<>("tiempo_inicio"));
        colTime.setMinWidth(40);
        
        TableColumn<Respuesta, String> colDesc = new TableColumn<>("Fin");
        colDesc.setCellValueFactory(new PropertyValueFactory<>("tiempo_final"));
        colDesc.setMinWidth(40);
        
        
        tabla_resp.getColumns().addAll(colId, colNombre, colTime, colDesc);
    }
    
}
