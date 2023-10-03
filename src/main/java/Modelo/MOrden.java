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

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class MOrden {
    private int id_mord, id_mneg, id_mcli, est_mord;
    private String com_mord, cos_mord;
    
    public MOrden Consultar_Orden(int id) throws SQLException{
        MOrden ord = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            System.out.println(id);
            String q = "select * from morden where id_mord = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                ord = new MOrden();
                ord.setId_mneg(rs.getInt("id_mneg"));
                ord.setId_mord(rs.getInt("id_mord"));
                ord.setId_mcli(rs.getInt("id_mcli"));                       
                ord.setEst_mord(rs.getInt("est_mord"));
                ord.setCos_mord(rs.getString("cos_mord"));
                break;
            }
            
        }
        catch (SQLException sq){
            System.out.println(sq.getStackTrace());
            ord = null;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            ord = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e){
                System.out.println("Mamo xd");
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return ord;
    }    
    public static int Max_Cliente() throws SQLException{
        int id = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select max(id_mord) from morden";
            
            ps = con.prepareStatement(q);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("max(id_mord)");
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
    public static int Cambio_Historial(int id_mord) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "update morden set est_mord = 3 "
                    + "where id_mord = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_mord);
            estatus = ps.executeUpdate();
        } catch(SQLException sq){
            System.out.println(sq.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                ps.close();
                con.close();
            } catch(Exception e){
                System.out.println(e.getMessage());
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
    public static int Cambio_Entregado(int id_mord) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "update morden set est_mord = 2 "
                    + "where id_mord = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_mord);
            estatus = ps.executeUpdate();
        } catch(SQLException sq){
            System.out.println(sq.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            try{
                ps.close();
                con.close();
            } catch(Exception e){
                System.out.println(e.getMessage());
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
    public static int Registrar_Orden(MOrden ord, int dia, int mes, int anio) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        
        try{
            con = Conexion.getConexion();
            String q = "insert into morden(id_mneg, id_mcli, cos_mord, est_mord, dia_mord, mes_mord, anio_mord, com_mord)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, ord.getId_mneg());
            ps.setInt(2, ord.getId_mcli());
            ps.setString(3, ord.getCos_mord());
            ps.setInt(4, ord.getEst_mord());
            ps.setInt(5, dia);
            ps.setInt(6, mes);
            ps.setInt(7, anio);
            ps.setString(8, ord.getCom_mord());
            estatus = ps.executeUpdate();
        } 
        catch(SQLException sq){
            /**System.out.println("Error de la base de datos");
            System.out.println(sq.getMessage());*/
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

    public MOrden() {
    }

    public MOrden(int id_mord, int id_mneg, int id_mcli, int est_mord, String com_mord, String cos_mord) {
        this.id_mord = id_mord;
        this.id_mneg = id_mneg;
        this.id_mcli = id_mcli;
        this.est_mord = est_mord;
        this.com_mord = com_mord;
        this.cos_mord = cos_mord;
    }

    public int getId_mord() {
        return id_mord;
    }

    public void setId_mord(int id_mord) {
        this.id_mord = id_mord;
    }

    public int getId_mneg() {
        return id_mneg;
    }

    public void setId_mneg(int id_mneg) {
        this.id_mneg = id_mneg;
    }

    public int getId_mcli() {
        return id_mcli;
    }

    public void setId_mcli(int id_mcli) {
        this.id_mcli = id_mcli;
    }

    public int getEst_mord() {
        return est_mord;
    }

    public void setEst_mord(int est_mord) {
        this.est_mord = est_mord;
    }

    public String getCom_mord() {
        return com_mord;
    }

    public void setCom_mord(String com_mord) {
        this.com_mord = com_mord;
    }

    public String getCos_mord() {
        return cos_mord;
    }

    public void setCos_mord(String cos_mord) {
        this.cos_mord = cos_mord;
    }

    
}
