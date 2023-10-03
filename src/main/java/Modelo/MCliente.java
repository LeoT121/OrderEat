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
public class MCliente {
    private int id_mcli, id_mneg, id_col, numext_dcli, numint_dcli;
    private String nom_mcli, calle_dcli,tel_dcli;
    
    public Vector<MCliente> Clientes(int negocio) throws SQLException{
        Vector<MCliente> clientes = new Vector<MCliente>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from mcliente where id_mneg = ? ";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, negocio);
            rs = ps.executeQuery();
            
            while(rs.next()){
                MCliente mcli = new MCliente();
                mcli.setCalle_dcli(rs.getString("calle_dcli"));
                mcli.setId_col(rs.getInt("id_col"));
                mcli.setId_mcli(rs.getInt("id_mcli"));
                mcli.setId_mneg(negocio);
                mcli.setNom_mcli(rs.getString("nom_mcli"));
                mcli.setNumext_dcli(rs.getInt("numext_dcli"));
                mcli.setNumint_dcli(rs.getInt("numint_dcli"));
                mcli.setTel_dcli(rs.getString("tel_dcli"));
                clientes.add(mcli);
                
            }
        }
        catch(SQLException sq){
            ///System.out.println("Error al consultar los productos");
            ///System.out.println(sq.getMessage());
            clientes = null;
        }
        catch(Exception e){
            clientes = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                clientes = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return clientes;
    }
    
    public boolean Consultar_Disponibilidad(String nom, int id) throws SQLException{
        boolean bandera = true;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from mcliente where nom_mcli = ? and id_mneg = ?";
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
    
    public static int Registrar_Cliente (MCliente mcli) throws SQLException{
        int estatus = 0;
        PreparedStatement ps = null;
        Connection con = null;
        try{
            con = Conexion.getConexion();
            String q = "insert into mcliente(id_mneg, id_col, nom_mcli, calle_dcli, tel_dcli, numext_dcli, numint_dcli)"
                    + "values(?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, mcli.getId_mneg());
            ps.setInt(2, mcli.getId_col());
            ps.setString(3, mcli.getNom_mcli());
            ps.setString(4, mcli.getCalle_dcli());
            ps.setString(5, mcli.getTel_dcli());
            ps.setInt(6, mcli.getNumext_dcli());
            ps.setInt(7, mcli.getNumint_dcli());
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

    public MCliente() {
    }

    public MCliente(int id_mcli, int id_mneg, int id_col, int numext_dcli, int numint_dcli, String nom_mcli, String calle_dcli, String tel_dcli) {
        this.id_mcli = id_mcli;
        this.id_mneg = id_mneg;
        this.id_col = id_col;
        this.numext_dcli = numext_dcli;
        this.numint_dcli = numint_dcli;
        this.nom_mcli = nom_mcli;
        this.calle_dcli = calle_dcli;
        this.tel_dcli = tel_dcli;
    }

    public int getId_mcli() {
        return id_mcli;
    }

    public void setId_mcli(int id_mcli) {
        this.id_mcli = id_mcli;
    }

    public int getId_mneg() {
        return id_mneg;
    }

    public void setId_mneg(int id_mneg) {
        this.id_mneg = id_mneg;
    }

    public int getId_col() {
        return id_col;
    }

    public void setId_col(int id_col) {
        this.id_col = id_col;
    }

    public int getNumext_dcli() {
        return numext_dcli;
    }

    public void setNumext_dcli(int numext_dcli) {
        this.numext_dcli = numext_dcli;
    }

    public int getNumint_dcli() {
        return numint_dcli;
    }

    public void setNumint_dcli(int numint_dcli) {
        this.numint_dcli = numint_dcli;
    }

    public String getNom_mcli() {
        return nom_mcli;
    }

    public void setNom_mcli(String nom_mcli) {
        this.nom_mcli = nom_mcli;
    }

    public String getCalle_dcli() {
        return calle_dcli;
    }

    public void setCalle_dcli(String calle_dcli) {
        this.calle_dcli = calle_dcli;
    }

    public String getTel_dcli() {
        return tel_dcli;
    }

    public void setTel_dcli(String tel_dcli) {
        this.tel_dcli = tel_dcli;
    }
    
    
    
}
