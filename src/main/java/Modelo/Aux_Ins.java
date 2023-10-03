/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class Aux_Ins implements Serializable{
    private int id_mins;
    private String name_mins;
    private float can_mins;

    public Aux_Ins(int id_mins, float can_mins, String name_mins) {
        this.id_mins = id_mins;
        this.can_mins = can_mins;
        this.name_mins = name_mins;
    }

    public Aux_Ins() {
    }

    public int getId_mins() {
        return id_mins;
    }

    public void setId_mins(int id_mins) {
        this.id_mins = id_mins;
    }
    public String getName_mins() {
        return name_mins;
    }

    public void setName_mins(String name_mins) {
        this.name_mins = name_mins;
    }
    

    public float getCan_mins() {
        return can_mins;
    }

    public void setCan_mins(float can_mins) {
        this.can_mins = can_mins;
    }
    
}
