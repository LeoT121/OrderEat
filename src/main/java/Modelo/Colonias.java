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
public class Colonias {
    private int id, municipio, codigo_postal;
    private String nombre, ciudad, asentamiento;
    
    public Colonias Col_Clie (int id) throws SQLException{
        Colonias col = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from colonias where id=?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                col = new Colonias();
                col.setId(id);
                col.setMunicipio(rs.getInt("municipio"));
                col.setCodigo_postal(rs.getInt("codigo_postal"));
                col.setNombre(rs.getString("nombre"));
                col.setAsentamiento(rs.getString("asentamiento"));
                col.setCiudad(rs.getString("ciudad"));
                break;
            }
            
        }
        catch (SQLException sq){
            ///System.out.println(sq.getStackTrace());
            col = null;
        }
        catch (Exception e){
            ///System.out.println(e.getMessage());
            col = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e){
                col = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return col;
    }
    
    public Vector<Colonias> Col(int id_del) throws SQLException{
        Vector<Colonias> vcol = new Vector<Colonias>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from colonias where municipio = ? ";            
            ps = con.prepareStatement(q);
            ps.setInt(1, id_del);
            rs = ps.executeQuery();
            while(rs.next()){
                Colonias colo = new Colonias();
                colo.setId(rs.getInt("id"));
                colo.setMunicipio(id_del);
                colo.setCodigo_postal(rs.getInt("codigo_postal"));
                colo.setNombre(rs.getString("nombre"));
                colo.setAsentamiento(rs.getString("asentamiento"));
                colo.setCiudad(rs.getString("ciudad"));
                vcol.add(colo);
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            vcol = null;
        }
        catch(Exception e){
            vcol = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                vcol = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return vcol;
    }

    public Colonias() {
    }

    public Colonias(int id, int municipio, int codigo_postal, String nombre, String ciudad, String asentamiento) {
        this.id = id;
        this.municipio = municipio;
        this.codigo_postal = codigo_postal;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.asentamiento = asentamiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getAsentamiento() {
        return asentamiento;
    }

    public void setAsentamiento(String asentamiento) {
        this.asentamiento = asentamiento;
    }
}
