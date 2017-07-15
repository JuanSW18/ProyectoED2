/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import cpm.Actividad;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Seven
 */
public class EventCreate implements EventHandler<MouseEvent>{
    
    TextField aux = null;
    TableView tb_aux;    
    int num;

    public EventCreate(TextField aux, TableView tabla) {
        this.aux = aux;
        this.tb_aux = tabla;
    }

    @Override
    public void handle(MouseEvent event) {
        try{
            num = Integer.parseInt(aux.getText());
            for(int i=0; i<num; i++)
                tb_aux.getItems().add(new Actividad(String.valueOf(i+1), "", "", ""));
            
        }catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Ingrese numero de actividades");
            alert.setContentText(null);
            alert.setResizable(false);
            alert.showAndWait();
        }
    }
    
}
