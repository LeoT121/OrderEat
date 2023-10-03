/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Control.Conexion;
import Metodos.AES;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class CPlatillo {
    
    private int id_cpla, id_mneg, id_ctie;
    private String nom_cpla, des_cpla, cos_cpla;
    AES aes = new AES();
    
    public static int Detalle (String nombre) throws SQLException{
        int id = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from cplatillo where nom_cpla = ?";
            
            ps = con.prepareStatement(q);
            ps.setString(1, nombre);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("id_cpla");
                break;
            }
            
        }catch(SQLException sq){
            System.out.println(sq.getMessage());
        }finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                System.out.println(e.getMessage());
            
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return id;
    }
    
    public static boolean Verificar_Eliminar(int id) throws SQLException{
        boolean bandera = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from dorden where id_cpla = ?";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            bandera = true;
            while(rs.next()){
                bandera = false;
                
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            bandera = false;
        }
        catch(Exception e){
            bandera = false;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
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
    
    public Vector<CPlatillo> Platillos(int negocio) throws SQLException{
        Vector<CPlatillo> platillos = new Vector<CPlatillo>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from cplatillo where id_mneg = ? ";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, negocio);
            rs = ps.executeQuery();
            
            while(rs.next()){
                CPlatillo plat = new CPlatillo();
                plat.setId_cpla(rs.getInt("id_cpla"));
                plat.setId_mneg(negocio);
                plat.setId_ctie(rs.getInt("id_ctie"));
                plat.setNom_cpla(rs.getString("nom_cpla"));
                plat.setDes_cpla(rs.getString("des_cpla"));
                plat.setCos_cpla(aes.getAESDecrypt(rs.getString("cos_cpla")));
                platillos.add(plat);
                
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            platillos = null;
        }
        catch(Exception e){
            platillos = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                platillos = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return platillos;
    }
    
    public boolean Consultar_Disponibilidad(String nom, int id) throws SQLException{
        boolean bandera = true;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from cplatillo where nom_cpla = ? and id_mneg = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, nom);
            ps.setInt(2, id);
            rs = ps.executeQuery();
            while(rs.next()){
                bandera = false;
                break;
            }   
            
        }catch (SQLException sq){
            bandera = false;
        }
        catch (Exception e){
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
    
    public static int Registrar_Platillo (CPlatillo cpla) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into cplatillo(id_ctie, id_mneg, nom_cpla, des_cpla, cos_cpla)"
                    + "values(?, ?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, cpla.getId_ctie());
            ps.setInt(2, cpla.getId_mneg());
            ps.setString(3, cpla.getNom_cpla());
            ps.setString(4, cpla.getDes_cpla());
            ps.setString(5, cpla.getCos_cpla());
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

    public static int Eliminar_Platillo(int id) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "delete from cplatillo where id_cpla = ? ";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            estatus = ps.executeUpdate();
        } catch(SQLException sq){
            ///System.out.println(sq.getMessage());
        } catch(Exception e){
            ///System.out.println(e.getMessage());
        } finally{
            try{
                ps.close();
                con.close();
            } catch(Exception e){
                ///System.out.println(e.getMessage());
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
    
    public static int Editar_Platillo_Desc (String valor, int id) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "update cplatillo set des_cpla = ? " 
                    + "where id_cpla = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, valor);
            ps.setInt(2, id);
            estatus = ps.executeUpdate();
        } catch(SQLException sq){
            ///System.out.println(sq.getMessage());
        } catch(Exception e){
            ///System.out.println(e.getMessage());
        } finally{
            try{
                ps.close();
                con.close();
            } catch(Exception e){
                ///System.out.println(e.getMessage());
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
    
    public static int Editar_Platillo_Costo (String valor, int id) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "update cplatillo set  cos_cpla = ? "
                    + "where id_cpla = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, valor);
            ps.setInt(2, id);
            estatus = ps.executeUpdate();
        } catch(SQLException sq){
            ///System.out.println(sq.getMessage());
        } catch(Exception e){
            ///System.out.println(e.getMessage());
        } finally{
            try{
                ps.close();
                con.close();
            } catch(Exception e){
                ///System.out.println(e.getMessage());
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

    public CPlatillo() {
    }

    public CPlatillo(int id_cpla, int id_mneg, int id_ctie, String nom_cpla, String des_cpla, String cos_cpla) {
        this.id_cpla = id_cpla;
        this.id_mneg = id_mneg;
        this.id_ctie = id_ctie;
        this.nom_cpla = nom_cpla;
        this.des_cpla = des_cpla;
        this.cos_cpla = cos_cpla;
    }

    public int getId_cpla() {
        return id_cpla;
    }

    public void setId_cpla(int id_cpla) {
        this.id_cpla = id_cpla;
    }

    public int getId_mneg() {
        return id_mneg;
    }

    public void setId_mneg(int id_mneg) {
        this.id_mneg = id_mneg;
    }

    public int getId_ctie() {
        return id_ctie;
    }

    public void setId_ctie(int id_ctie) {
        this.id_ctie = id_ctie;
    }

    public String getNom_cpla() {
        return nom_cpla;
    }

    public void setNom_cpla(String nom_cpla) {
        this.nom_cpla = nom_cpla;
    }

    public String getDes_cpla() {
        return des_cpla;
    }

    public void setDes_cpla(String des_cpla) {
        this.des_cpla = des_cpla;
    }

    public String getCos_cpla() {
        return cos_cpla;
    }

    public void setCos_cpla(String cos_cpla) {
        this.cos_cpla = cos_cpla;
    }
    
    
}
