/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpm_interfaz;

import Eventos.EventCreate;
import Eventos.EventOutput;
import Eventos.EventWriteDes;
import Eventos.EventWriteN;
import Eventos.EventWriteTime;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Seven
 */
public class CPM_Interfaz extends Application {
    
    TableView<Actividad> table;
    TableView<Respuesta> respuesta = new TableView<>();
    
    @Override
    public void start(Stage primaryStage) {
        /*-----------Contenedor Interno 1----------*/
        GridPane grid_uno = new GridPane();
        grid_uno.setHgap(5);
        
        Text tx_title = new Text("Número de actividades: ");
        tx_title.setFill(Color.WHITESMOKE);
        tx_title.setFont(Font.font(null, FontWeight.BOLD, 13));
        
        TextField tf_filas = new TextField();
        tf_filas.setPrefHeight(5);
        tf_filas.setFocusTraversable(false);
        
        Button btn_create = new Button("Crear");
        btn_create.setFocusTraversable(false);
        
        grid_uno.add(tx_title, 0, 0);
        grid_uno.add(tf_filas, 1, 0);
        grid_uno.add(btn_create, 2, 0);
        
        
        /*-----------Contenedor Interno 2----------*/
        GridPane grid_dos = new GridPane();
        grid_dos.setVgap(5);
        
        table = new TableView<>();
        table.setMinSize(580, 500);
        table.setEditable(true);
        //table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        btn_create.setOnMousePressed(new EventCreate(tf_filas, table));     
        aux();
        
        Button btn_ejecutar = new Button("Ejecutar");
        btn_ejecutar.setOnMouseClicked(new EventOutput(table, tf_filas, respuesta));
        
        grid_dos.add(table, 0, 0);
        grid_dos.add(btn_ejecutar, 0, 1);
        
        
        /*-----------Contenedor Principal----------*/
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        
        grid.add(grid_uno, 0, 0); // objeto-columna-fila
        grid.add(grid_dos, 0, 1, 1, 1);//grid.add(grid_dos, 0, 1);  
        
        
        Scene scene = new Scene(grid, 590, 575); //ancho - alto
        
        primaryStage.setTitle("Critical Path Method");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("Recursos/gantt_24.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
        
        grid.setStyle("-fx-background-color: black");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
    public void aux(){
        TableColumn<Actividad, String> colId = new TableColumn<>("Nro");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        //colId.setCellFactory(TextFieldTableCell.forTableColumn());
        colId.setMinWidth(100);
        colId.setSortable(false);
        
        
        TableColumn<Actividad, String> colNombre = new TableColumn<>("Actividad");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setCellFactory(TextFieldTableCell.forTableColumn());
        colNombre.setMinWidth(200);
        colNombre.setOnEditCommit(new EventWriteN()); //Evento para escritura
        
        
        TableColumn<Actividad, String> colTime = new TableColumn<>("Duracion");
        colTime.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
        colTime.setCellFactory(TextFieldTableCell.forTableColumn());
        colTime.setMinWidth(80);
        colTime.setOnEditCommit(new EventWriteTime()); //Evento para escritura
        
        TableColumn<Actividad, String> colDesc = new TableColumn<>("Precedencia de Actividades");
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descrip"));
        colDesc.setCellFactory(TextFieldTableCell.forTableColumn());
        colDesc.setMinWidth(200);
        colDesc.setOnEditCommit(new EventWriteDes()); //Evento para escritura
        
        table.getColumns().addAll(colId, colNombre, colTime, colDesc);
    }
    
}
