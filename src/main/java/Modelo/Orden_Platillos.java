package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class Orden_Platillos {
    private String Nom_pla;
    private int id_cpla, cant, cost;

    public Orden_Platillos(String Nom_pla, int id_cpla, int cant, int cost) {
        this.Nom_pla = Nom_pla;
        this.id_cpla = id_cpla;
        this.cant = cant;
        this.cost = cost;
    }

    public Orden_Platillos() {
    }

    public String getNom_pla() {
        return Nom_pla;
    }

    public void setNom_pla(String Nom_pla) {
        this.Nom_pla = Nom_pla;
    }

    public int getId_cpla() {
        return id_cpla;
    }

    public void setId_cpla(int id_cpla) {
        this.id_cpla = id_cpla;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
