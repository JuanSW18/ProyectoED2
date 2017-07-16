/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpm_interfaz;

/**
 *
 * @author Seven
 */
public class Respuesta {
    String num_act;
    String actividad;
    String tiempo_inicio;
    String tiempo_final;

    public Respuesta(String num_filas, String acti, String t_inicio, String t_final) {
        this.num_act = num_filas;
        this.actividad = acti;
        this.tiempo_inicio = t_inicio;
        this.tiempo_final = t_final;
    }

    public String getNum_act() {
        return num_act;
    }

    public void setNum_act(String num_act) {
        this.num_act = num_act;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getTiempo_inicio() {
        return tiempo_inicio;
    }

    public void setTiempo_inicio(String tiempo_inicio) {
        this.tiempo_inicio = tiempo_inicio;
    }

    public String getTiempo_final() {
        return tiempo_final;
    }

    public void setTiempo_final(String tiempo_final) {
        this.tiempo_final = tiempo_final;
    }
    
    
}
