/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Control.Conexion;
import Metodos.AES;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class VWPlatillo implements Serializable{
    private int id_cpla, id_mneg;
    private String nom_cpla, des_cpla, cos_cpla, nom_ctie;
    AES aes = new AES();
    
    public Vector<VWPlatillo> Platillos2(int negocio) throws SQLException{
        Vector<VWPlatillo> platillos = new Vector<VWPlatillo>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vwplat where id_mneg = ? ";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, negocio);
            rs = ps.executeQuery();
            
            while(rs.next()){
                VWPlatillo plat = new VWPlatillo();
                plat.setId_cpla(rs.getInt("id_cpla"));
                plat.setId_mneg(negocio);
                plat.setNom_ctie(rs.getString("nom_ctie"));
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

    public VWPlatillo() {
    }

    public VWPlatillo(int id_cpla, int id_mneg, String nom_cpla, String des_cpla, String cos_cpla, String nom_ctie) {
        this.id_cpla = id_cpla;
        this.id_mneg = id_mneg;
        this.nom_cpla = nom_cpla;
        this.des_cpla = des_cpla;
        this.cos_cpla = cos_cpla;
        this.nom_ctie = nom_ctie;
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

    public String getNom_ctie() {
        return nom_ctie;
    }

    public void setNom_ctie(String nom_ctie) {
        this.nom_ctie = nom_ctie;
    }
    
}
