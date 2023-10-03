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
public class VWOrden {
    private int id_mord, id_mneg, est_mord, numext_dcli, numint_dcli, codigo_postal, id_mcli;
    private String cos_mord, com_mord, nom_mcli, calle_dcli, tel_dcli, nombre, nom_municipio;
    AES aes = new AES();
    
    public Vector<VWOrden> Ordenes_Historial_Personalizado(int neg, int id, int dia, int mes) throws SQLException{
        Vector<VWOrden> ord = new Vector<VWOrden>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "";
            if(id == -1 && dia == -1 && mes == -1){
                q = "select * from vworden where id_mneg = ?";
                ps = con.prepareStatement(q);
                ps.setInt(1, neg);
                ///System.out.println("Sin atributos");
            }
            else if(id != -1 && dia == -1 && mes == -1){
                q = "select * from vworden where id_mneg = ? and id_mcli = ?";
                ps = con.prepareStatement(q);
                ps.setInt(1, neg);
                ps.setInt(2, id);
                ///System.out.println("Cliente");
            }
            else if(id != -1 && dia != -1 && mes == -1){
                q = "select * from vworden where id_mneg = ? and id_mcli = ? and dia_mord = ?";
                ps = con.prepareStatement(q);
                ps.setInt(1, neg);
                ps.setInt(2, id);
                ps.setInt(3, dia);
                ///System.out.println("Cliente, dia");
            }
            else if(id != -1 && dia == -1 && mes != -1){
                q = "select * from vworden where id_mneg = ? and id_mcli = ? and mes_mord = ?";
                ps = con.prepareStatement(q);
                ps.setInt(1, neg);
                ps.setInt(2, id);
                ps.setInt(3, mes);
                ///System.out.println("Cliente, mes");
            }
            else if(id != -1){
                q = "select * from vworden where id_mneg = ? and id_mcli = ? and mes_mord = ? and dia_mord = ?";
                ps = con.prepareStatement(q);
                ps.setInt(1, neg);
                ps.setInt(2, id);
                ps.setInt(3, mes);
                ps.setInt(4, dia);
                ///System.out.println("Cliente, dia, mes");
            }
            else if(mes != -1 && dia == -1){
                q = "select * from vworden where id_mneg = ? and mes_mord = ?";
                ps = con.prepareStatement(q);
                ps.setInt(1, neg);
                ps.setInt(2, mes);
                ///System.out.println("Mes");
            }
            else if(mes == -1 && dia != -1){
                q = "select * from vworden where id_mneg = ? and dia_mord = ?";
                ps = con.prepareStatement(q);
                ps.setInt(1, neg);
                ps.setInt(2, dia);
                ///System.out.println("Dia");
            }
            else{
                q = "select * from vworden where id_mneg = ? and dia_mord = ? and mes_mord = ?";
                ps = con.prepareStatement(q);
                ps.setInt(1, neg);
                ps.setInt(2, dia);
                ps.setInt(3, mes);
                ///System.out.println("Dia, Mes");

            }
            rs = ps.executeQuery();
            
            while(rs.next()){                
                VWOrden vword = new VWOrden();
                vword.setId_mcli(rs.getInt("id_mcli"));
                vword.setCalle_dcli(rs.getString("calle_dcli"));
                vword.setCodigo_postal(rs.getInt("codigo_postal"));
                vword.setCom_mord(rs.getString("com_mord"));
                vword.setCos_mord(aes.getAESDecrypt(rs.getString("cos_mord")));
                vword.setEst_mord(rs.getInt("est_mord"));
                vword.setId_mneg(neg);
                vword.setId_mord(rs.getInt("id_mord"));
                vword.setNom_mcli(rs.getString("nom_mcli"));
                vword.setNom_municipio(rs.getString("nom_municipio"));
                vword.setNombre(rs.getString("nombre"));
                vword.setNumext_dcli(rs.getInt("numext_dcli"));
                vword.setNumint_dcli(rs.getInt("numint_dcli"));
                vword.setTel_dcli(rs.getString("tel_dcli"));
                ord.add(vword);
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            ord = null;
        }
        catch(Exception e){
            ord = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                ord = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        if(ord == null){
            System.out.println("Hubo un problema en la base de datos xd");
        }
        return ord;
    }
    
    public Vector<VWOrden> Ordenes_Actuales(int neg) throws SQLException{
        Vector<VWOrden> ord = new Vector<VWOrden>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vworden where id_mneg = ? and est_mord = 1";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, neg);
            rs = ps.executeQuery();
            
            while(rs.next()){                
                VWOrden vword = new VWOrden();
                vword.setId_mcli(rs.getInt("id_mcli"));
                vword.setCalle_dcli(rs.getString("calle_dcli"));
                vword.setCodigo_postal(rs.getInt("codigo_postal"));
                vword.setCom_mord(rs.getString("com_mord"));
                vword.setCos_mord(aes.getAESDecrypt(rs.getString("cos_mord")));
                vword.setEst_mord(1);
                vword.setId_mneg(neg);
                vword.setId_mord(rs.getInt("id_mord"));
                vword.setNom_mcli(rs.getString("nom_mcli"));
                vword.setNom_municipio(rs.getString("nom_municipio"));
                vword.setNombre(rs.getString("nombre"));
                vword.setNumext_dcli(rs.getInt("numext_dcli"));
                vword.setNumint_dcli(rs.getInt("numint_dcli"));
                vword.setTel_dcli(rs.getString("tel_dcli"));
                ord.add(vword);
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            ord = null;
        }
        catch(Exception e){
            ord = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                ord = null;
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

    public Vector<VWOrden> Ordenes_Pagos(int neg) throws SQLException{
        Vector<VWOrden> ord = new Vector<VWOrden>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vworden where id_mneg = ? and est_mord = 2";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, neg);
            rs = ps.executeQuery();
            
            while(rs.next()){                
                VWOrden vword = new VWOrden();
                vword.setId_mcli(rs.getInt("id_mcli"));
                vword.setCalle_dcli(rs.getString("calle_dcli"));
                vword.setCodigo_postal(rs.getInt("codigo_postal"));
                vword.setCom_mord(rs.getString("com_mord"));
                vword.setCos_mord(aes.getAESDecrypt(rs.getString("cos_mord")));
                vword.setEst_mord(2);
                vword.setId_mneg(neg);
                vword.setId_mord(rs.getInt("id_mord"));
                vword.setNom_mcli(rs.getString("nom_mcli"));
                vword.setNom_municipio(rs.getString("nom_municipio"));
                vword.setNombre(rs.getString("nombre"));
                vword.setNumext_dcli(rs.getInt("numext_dcli"));
                vword.setNumint_dcli(rs.getInt("numint_dcli"));
                vword.setTel_dcli(rs.getString("tel_dcli"));
                ord.add(vword);
            }
        }
        catch(SQLException sq){
            System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());
            ord = null;
        }
        catch(Exception e){
            ord = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                ord = null;
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
    
    public Vector<VWOrden> Ordenes_Historial(int neg) throws SQLException{
        Vector<VWOrden> ord = new Vector<VWOrden>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vworden where id_mneg = ?";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, neg);
            rs = ps.executeQuery();
            
            while(rs.next()){                
                VWOrden vword = new VWOrden();
                vword.setId_mcli(rs.getInt("id_mcli"));
                vword.setCalle_dcli(rs.getString("calle_dcli"));
                vword.setCodigo_postal(rs.getInt("codigo_postal"));
                vword.setCom_mord(rs.getString("com_mord"));
                vword.setCos_mord(aes.getAESDecrypt(rs.getString("cos_mord")));
                vword.setEst_mord(rs.getInt("est_mord"));
                vword.setId_mneg(neg);
                vword.setId_mord(rs.getInt("id_mord"));
                vword.setNom_mcli(rs.getString("nom_mcli"));
                vword.setNom_municipio(rs.getString("nom_municipio"));
                vword.setNombre(rs.getString("nombre"));
                vword.setNumext_dcli(rs.getInt("numext_dcli"));
                vword.setNumint_dcli(rs.getInt("numint_dcli"));
                vword.setTel_dcli(rs.getString("tel_dcli"));
                ord.add(vword);
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            ord = null;
        }
        catch(Exception e){
            ord = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                ord = null;
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
    
    public VWOrden() {
    }

    public VWOrden(int id_mord, int id_mneg, int est_mord, int numext_dcli, int numint_dcli, int codigo_postal, int id_mcli, String cos_mord, String com_mord, String nom_mcli, String calle_dcli, String tel_dcli, String nombre, String nom_municipio) {
        this.id_mord = id_mord;
        this.id_mneg = id_mneg;
        this.est_mord = est_mord;
        this.numext_dcli = numext_dcli;
        this.numint_dcli = numint_dcli;
        this.codigo_postal = codigo_postal;
        this.id_mcli = id_mcli;
        this.cos_mord = cos_mord;
        this.com_mord = com_mord;
        this.nom_mcli = nom_mcli;
        this.calle_dcli = calle_dcli;
        this.tel_dcli = tel_dcli;
        this.nombre = nombre;
        this.nom_municipio = nom_municipio;
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

    public int getEst_mord() {
        return est_mord;
    }

    public void setEst_mord(int est_mord) {
        this.est_mord = est_mord;
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

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public int getId_mcli() {
        return id_mcli;
    }

    public void setId_mcli(int id_mcli) {
        this.id_mcli = id_mcli;
    }

    public String getCos_mord() {
        return cos_mord;
    }

    public void setCos_mord(String cos_mord) {
        this.cos_mord = cos_mord;
    }

    public String getCom_mord() {
        return com_mord;
    }

    public void setCom_mord(String com_mord) {
        this.com_mord = com_mord;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNom_municipio() {
        return nom_municipio;
    }

    public void setNom_municipio(String nom_municipio) {
        this.nom_municipio = nom_municipio;
    }

    
    
}
