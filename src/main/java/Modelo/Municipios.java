/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Control.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class Municipios {
    private int id, estado;
    private String nombre;
    
    public Municipios Mun_Clie (int id) throws SQLException{
        Municipios mun = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from municipios where id = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                mun = new Municipios();
                mun.setEstado(rs.getInt("estado"));
                mun.setId(id);
                mun.setNombre(rs.getString("nom_municipio"));
                break;
            }
            
        }
        catch (SQLException sq){
            ///System.out.println(sq.getStackTrace());
            mun = null;
        }
        catch (Exception e){
            ///System.out.println(e.getMessage());
            mun = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e){
                mun = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return mun;
    }
    
    public Vector<Municipios> Delegaciones() throws SQLException{
        Vector<Municipios> mun = new Vector<Municipios>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from municipios";            
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                Municipios muni = new Municipios();
                muni.setId(rs.getInt("id"));
                muni.setEstado(rs.getInt("estado"));
                muni.setNombre(rs.getString("nom_municipio"));
                mun.add(muni);                
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            mun = null;
        }
        catch(Exception e){
            mun = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                mun = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return mun;
    }

    public Municipios(int id, int estado, String nombre) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
    }

    public Municipios() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
