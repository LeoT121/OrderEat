/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class Tokens {
    private String token;
    private long tiempo;

    public Tokens(String token, long tiempo) {
        this.token = token;
        this.tiempo = tiempo;
    }

    public Tokens() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }
        
}
