/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpm;

/**
 *
 * @author Seven
 */
public class Actividad {
    public String id;
    public String nombre = null;
    public String tiempo;
    public String precedentes = null;
    
    public Actividad(){
        
    }
    
    public Actividad(String id_act, String name, String time, String tex) {
        this.id = id_act;
        this.nombre = name;
        this.tiempo = time;
        this.precedentes = tex;
    }
    
    public String getData(){
        return "Actividad : " + nombre +
                "tiempo : " + tiempo +
                "Descripcion: " + precedentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getPrecedentes() {
        return precedentes;
    }

    public void setPrecedentes(String precedentes) {
        this.precedentes = precedentes;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
