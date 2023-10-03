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
public class MInsumos {
    private int id_mins, id_mneg, id_cuni, cos_mins;
    private float cant_mins, per_mins;
    private String nom_mins;

    public int Registrar_Insumo(MInsumos mins) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into minsumos(id_mneg, id_cuni, cant_mins, nom_mins, cos_mins, per_mins)"
                    + "values(?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, mins.getId_mneg());
            ps.setInt(2, mins.getId_cuni());
            ps.setFloat(3, mins.getCant_mins());
            ps.setString(4, mins.getNom_mins());
            ps.setInt(5, mins.getCos_mins());
            ps.setFloat(6, mins.getPer_mins());
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
    
    public boolean Consultar_Disponibilidad(String nom) throws SQLException{
        boolean bandera = true;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from minsumos where nom_mins = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, nom);
            rs = ps.executeQuery();
            while(rs.next()){
                bandera = false;
                break;
            }
            
        }
        catch (SQLException sq){
            ///System.out.println(sq.getStackTrace());
            bandera = false;
        }
        catch (Exception e){
            ///System.out.println(e.getMessage());
            bandera = false;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e){
                bandera = false;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return bandera;
    }
    
    public int Agregar_Cantidad (float cantidad, int id) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConexion();
            String q = "update minsumos set cant_mins = ? where id_mins = ?";
            ps = con.prepareStatement(q);
            ps.setFloat(1, cantidad);
            ps.setInt(2, id);
            estatus = ps.executeUpdate();
            
        }
        catch (SQLException sq){
            ///System.out.println(sq.getStackTrace());
        }
        catch (Exception e){
            ///System.out.println(e.getMessage());
        }
        finally{
            try{
                ps.close();
                con.close();
            } catch (Exception e){
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
    
    public int Agregar_Perdida2 (float perdida, int id) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        System.out.println(perdida);
        System.out.println(id);
        try{
            con = Conexion.getConexion();            
            String q = "update minsumos set per_mins = ? where id_mins = ?";
            ps = con.prepareStatement(q);
            ps.setFloat(1, perdida);
            ps.setInt(2, id);
            estatus = ps.executeUpdate();
            System.out.println("Se va a hacer la query dentro del trycatch ya al final xd");
            System.out.println(estatus);
            System.out.println("Ya no quiero vivir xd");
            
        }
        catch (SQLException sq){
            System.out.println(sq.getStackTrace());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            try{
                ps.close();
                con.close();
            } catch (Exception e){
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
    
    public int Agregar_Perdida (float cantidad, int id) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConexion();
            String q = "update minsumos set cant_mins = ? where id_mins = ?";
            ps = con.prepareStatement(q);
            ps.setFloat(1, cantidad);
            ps.setInt(2, id);
            estatus = ps.executeUpdate();
            
            
        }
        catch (SQLException sq){
            ///System.out.println(sq.getStackTrace());
        }
        catch (Exception e){
            ///System.out.println(e.getMessage());
        }
        finally{
            try{
                ps.close();
                con.close();
            } catch (Exception e){
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
    
    public Vector<MInsumos> Insumos(int negocio) throws SQLException{
        Vector<MInsumos> insumo = new Vector<MInsumos>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from minsumos where id_mneg = ?";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, negocio);
            rs = ps.executeQuery();
            
            while(rs.next()){
                MInsumos ins = new MInsumos();
                ins.setId_mneg(negocio);
                ins.setCant_mins(rs.getFloat("cant_mins"));
                ins.setCos_mins(rs.getInt("cos_mins"));
                ins.setId_cuni(rs.getInt("id_cuni"));
                ins.setId_mins(rs.getInt("id_mins"));
                ins.setNom_mins(rs.getString("nom_mins"));
                ins.setPer_mins(rs.getFloat("per_mins"));
                insumo.add(ins);
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            insumo = null;
        }
        catch(Exception e){
            insumo = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                insumo = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return insumo;
    }
    
    public MInsumos() {
    }

    public MInsumos(int id_mins, int id_mneg, int id_cuni, int cos_mins, float cant_mins, String nom_mins, float per_mins) {
        this.id_mins = id_mins;
        this.id_mneg = id_mneg;
        this.id_cuni = id_cuni;
        this.cos_mins = cos_mins;
        this.cant_mins = cant_mins;
        this.nom_mins = nom_mins;
        this.per_mins = per_mins;
    }

    public int getId_mins() {
        return id_mins;
    }

    public void setId_mins(int id_mins) {
        this.id_mins = id_mins;
    }
    
    public float getPer_mins() {
        return per_mins;
    }

    public void setPer_mins(float per_mins) {
        this.per_mins = per_mins;
    }

    public int getId_mneg() {
        return id_mneg;
    }

    public void setId_mneg(int id_mneg) {
        this.id_mneg = id_mneg;
    }

    public int getId_cuni() {
        return id_cuni;
    }

    public void setId_cuni(int id_cuni) {
        this.id_cuni = id_cuni;
    }

    public int getCos_mins() {
        return cos_mins;
    }

    public void setCos_mins(int cos_mins) {
        this.cos_mins = cos_mins;
    }

    public float getCant_mins() {
        return cant_mins;
    }

    public void setCant_mins(float cant_mins) {
        this.cant_mins = cant_mins;
    }

    public String getNom_mins() {
        return nom_mins;
    }

    public void setNom_mins(String nom_mins) {
        this.nom_mins = nom_mins;
    }
    
}
