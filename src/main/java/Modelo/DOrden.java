/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Control.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class DOrden {
    private int id_dord, id_mord, id_cpla, cant_dord;

    public static int Registrar_DOrden(DOrden ord) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into dorden(id_mord, id_cpla, cant_dord)"
                    + "values(?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, ord.getId_mord());
            ps.setInt(2, ord.getId_cpla());
            ps.setInt(3, ord.getCant_dord());
            estatus = ps.executeUpdate();
        } 
        catch(SQLException sq){
   
        } 
        catch(Exception e){
           
        } 
        finally{
            try{
                ps.close();
                con.close();
            } catch(Exception e){
                
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return estatus;
    }
    
    public DOrden() {
    }

    public DOrden(int id_dord, int id_mord, int id_cpla, int cant_dord) {
        this.id_dord = id_dord;
        this.id_mord = id_mord;
        this.id_cpla = id_cpla;
        this.cant_dord = cant_dord;
    }

    public int getId_dord() {
        return id_dord;
    }

    public void setId_dord(int id_dord) {
        this.id_dord = id_dord;
    }

    public int getId_mord() {
        return id_mord;
    }

    public void setId_mord(int id_mord) {
        this.id_mord = id_mord;
    }

    public int getId_cpla() {
        return id_cpla;
    }

    public void setId_cpla(int id_cpla) {
        this.id_cpla = id_cpla;
    }

    public int getCant_dord() {
        return cant_dord;
    }

    public void setCant_dord(int cant_dord) {
        this.cant_dord = cant_dord;
    }
}
