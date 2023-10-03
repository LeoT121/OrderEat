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
public class VWCliente {
    private int id_mcli, numext_dcli, numint_dcli, cp;
    private String nom_mcli, calle_dcli,tel_dcli, colonia, municipio;
    
    public VWCliente Cliente (int id) throws SQLException{
        VWCliente clie = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vwcliente where id_mcli=?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                clie = new VWCliente();
                clie.setCalle_dcli(rs.getString("calle_dcli"));
                clie.setColonia(rs.getString("nombre"));
                clie.setCp(rs.getInt("codigo_postal"));
                clie.setId_mcli(id);
                clie.setMunicipio(rs.getString("nom_municipio"));
                clie.setNom_mcli(rs.getString("nom_mcli"));
                clie.setNumext_dcli(rs.getInt("numext_dcli"));
                clie.setNumint_dcli(rs.getInt("numint_dcli"));
                clie.setTel_dcli(rs.getString("tel_dcli"));
                break;
            }
            
        }
        catch (SQLException sq){
            
            clie = null;
        }
        catch (Exception e){
            clie = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
            } catch (Exception e){
                clie = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return clie;
    }

    public VWCliente() {
    }

    public VWCliente(int id_mcli, int numext_dcli, int numint_dcli, int cp, String nom_mcli, String calle_dcli, String tel_dcli, String colonia, String municipio) {
        this.id_mcli = id_mcli;
        this.numext_dcli = numext_dcli;
        this.numint_dcli = numint_dcli;
        this.cp = cp;
        this.nom_mcli = nom_mcli;
        this.calle_dcli = calle_dcli;
        this.tel_dcli = tel_dcli;
        this.colonia = colonia;
        this.municipio = municipio;
    }

    public int getId_mcli() {
        return id_mcli;
    }

    public void setId_mcli(int id_mcli) {
        this.id_mcli = id_mcli;
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

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
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

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
}
