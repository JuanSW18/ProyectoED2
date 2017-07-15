/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import cpm.Actividad;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;

/**
 *
 * @author Seven
 */
public class EventWriteN implements EventHandler<CellEditEvent<Actividad, String>>{

    @Override
    public void handle(CellEditEvent<Actividad, String> t) {
        ((Actividad) t.getTableView().getItems().
            get(t.getTablePosition().getRow())
                ).setNombre(t.getNewValue());
    }
    
}
