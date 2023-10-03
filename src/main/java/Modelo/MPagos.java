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
public class MPagos {
    private int id_mpag, id_mneg;
    private String tit_mpag, des_mpag, cos_mpag;
    AES aes = new AES();
    
    public static int Eliminar_Pago(int id) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "delete from mpagos where id_mpag = ?";
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
    
    public Vector<MPagos> Pagos(int negocio) throws SQLException{
        
        Vector<MPagos> pagos = new Vector<MPagos>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from mpagos where id_mneg = ?";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, negocio);
            rs = ps.executeQuery();
            
            while(rs.next()){
                MPagos pag = new MPagos();
                pag.setId_mpag(rs.getInt("id_mpag"));
                pag.setId_mneg(negocio);
                pag.setTit_mpag(aes.getAESDecrypt(rs.getString("tit_mpag")));
                pag.setDes_mpag(aes.getAESDecrypt(rs.getString("des_mpag")));
                pag.setCos_mpag(aes.getAESDecrypt(rs.getString("cos_mpag")));
                pagos.add(pag);
                
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            pagos = null;
        }
        catch(Exception e){
            pagos = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                pagos = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return pagos;
    }
    
    public boolean Consultar_Disponibilidad(String nom, int id) throws SQLException{
        boolean bandera = true;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from mpagos where tit_mpag = ? and id_mneg = ?";
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

    public static int Registrar_Pago (MPagos pag) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into mpagos(id_mneg, tit_mpag, des_mpag, cos_mpag)"
                    + "values(?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, pag.getId_mneg());
            ps.setString(2, pag.getTit_mpag());
            ps.setString(3, pag.getDes_mpag());
            ps.setString(4, pag.getCos_mpag());
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
    
    public MPagos() {
    }

    public MPagos(int id_mpag, int id_mneg, String cos_mpag, String tit_mpag, String des_mpag) {
        this.id_mpag = id_mpag;
        this.id_mneg = id_mneg;
        this.cos_mpag = cos_mpag;
        this.tit_mpag = tit_mpag;
        this.des_mpag = des_mpag;
    }

    public int getId_mpag() {
        return id_mpag;
    }

    public void setId_mpag(int id_mpag) {
        this.id_mpag = id_mpag;
    }

    public int getId_mneg() {
        return id_mneg;
    }

    public void setId_mneg(int id_mneg) {
        this.id_mneg = id_mneg;
    }

    public String getCos_mpag() {
        return cos_mpag;
    }

    public void setCos_mpag(String cos_mpag) {
        this.cos_mpag = cos_mpag;
    }

    public String getTit_mpag() {
        return tit_mpag;
    }

    public void setTit_mpag(String tit_mpag) {
        this.tit_mpag = tit_mpag;
    }

    public String getDes_mpag() {
        return des_mpag;
    }

    public void setDes_mpag(String des_mpag) {
        this.des_mpag = des_mpag;
    }
}
