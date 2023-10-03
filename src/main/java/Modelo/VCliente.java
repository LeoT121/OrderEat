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
public class VCliente {
    private String nom_dcli, calle_dcli, tel_dcli, cp_dcli, mun_dcli, est_dcli, col_dcli, cos_mord;
    private int numext_dcli, numint_dcli, id_mord, id_mneg;
    private boolean est_mord;
    AES aes = new AES();
    
    public Vector<VCliente> Detalle_Cliente_Entregado(int neg) throws SQLException{
        Vector<VCliente> vcli = new Vector<VCliente>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vcliente where id_mneg = ? and est_mord = 1";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, neg);
            rs = ps.executeQuery();
            
            while(rs.next()){
                VCliente cli = new VCliente();
                cli.setNom_dcli(rs.getString("nom_dcli"));
                cli.setCalle_dcli(rs.getString("calle_dcli"));
                cli.setCp_dcli(rs.getString("cp_dcli"));
                cli.setEst_dcli(rs.getString("est_dcli"));
                cli.setEst_mord(false);
                cli.setId_mneg(neg);
                cli.setId_mord(rs.getInt("id_mord"));
                cli.setMun_dcli(rs.getString("mun_dcli"));
                cli.setNumext_dcli(rs.getInt("numext_dcli"));
                cli.setNumint_dcli(rs.getInt("numint_dcli"));
                cli.setTel_dcli(rs.getString("tel_dcli"));
                cli.setCol_dcli(rs.getString("col_dcli"));
                cli.setCos_mord(aes.getAESDecrypt(rs.getString("cos_mord")));
                vcli.add(cli);
                
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            vcli = null;
        }
        catch(Exception e){
            vcli = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                vcli = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return vcli;
    }
    
    public Vector<VCliente> Detalle_Cliente(int neg) throws SQLException{
        Vector<VCliente> vcli = new Vector<VCliente>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConexion();
            String q = "select * from vcliente where id_mneg = ? and est_mord = 0";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, neg);
            rs = ps.executeQuery();
            
            while(rs.next()){
                VCliente cli = new VCliente();
                cli.setNom_dcli(rs.getString("nom_dcli"));
                cli.setCalle_dcli(rs.getString("calle_dcli"));
                cli.setCp_dcli(rs.getString("cp_dcli"));
                cli.setEst_dcli(rs.getString("est_dcli"));
                cli.setEst_mord(false);
                cli.setId_mneg(neg);
                cli.setId_mord(rs.getInt("id_mord"));
                cli.setMun_dcli(rs.getString("mun_dcli"));
                cli.setNumext_dcli(rs.getInt("numext_dcli"));
                cli.setNumint_dcli(rs.getInt("numint_dcli"));
                cli.setTel_dcli(rs.getString("tel_dcli"));
                cli.setCol_dcli(rs.getString("col_dcli"));
                cli.setCos_mord(aes.getAESDecrypt(rs.getString("cos_mord")));
                vcli.add(cli);
                
            }
        }
        catch(SQLException sq){
            /**System.out.println("Error al consultar los productos");
            System.out.println(sq.getMessage());*/
            vcli = null;
        }
        catch(Exception e){
            vcli = null;
        }
        finally{
            try{
                rs.close();
                ps.close();
                con.close();
                
            
            }catch(Exception e){
                vcli = null;
            }
            if (con != null && !con.isClosed()) {
                try {
                    con.close();
                }catch (SQLException e) {
                }
            }
        }
        return vcli;
    }

    public VCliente(String nom_dcli, String calle_dcli, String tel_dcli, String cp_dcli, String mun_dcli, String est_dcli, String col_dcli, int numext_dcli, int numint_dcli, int id_mord, int id_mneg, String cos_mord, boolean est_mord) {
        this.nom_dcli = nom_dcli;
        this.calle_dcli = calle_dcli;
        this.tel_dcli = tel_dcli;
        this.cp_dcli = cp_dcli;
        this.mun_dcli = mun_dcli;
        this.est_dcli = est_dcli;
        this.col_dcli = col_dcli;
        this.numext_dcli = numext_dcli;
        this.numint_dcli = numint_dcli;
        this.id_mord = id_mord;
        this.id_mneg = id_mneg;
        this.cos_mord = cos_mord;
        this.est_mord = est_mord;
    }

    public VCliente() {
    }

    public String getNom_dcli() {
        return nom_dcli;
    }

    public void setNom_dcli(String nom_dcli) {
        this.nom_dcli = nom_dcli;
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

    public String getCp_dcli() {
        return cp_dcli;
    }

    public void setCp_dcli(String cp_dcli) {
        this.cp_dcli = cp_dcli;
    }

    public String getMun_dcli() {
        return mun_dcli;
    }

    public void setMun_dcli(String mun_dcli) {
        this.mun_dcli = mun_dcli;
    }

    public String getEst_dcli() {
        return est_dcli;
    }

    public void setEst_dcli(String est_dcli) {
        this.est_dcli = est_dcli;
    }

    public String getCol_dcli() {
        return col_dcli;
    }

    public void setCol_dcli(String col_dcli) {
        this.col_dcli = col_dcli;
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

    public String getCos_mord() {
        return cos_mord;
    }

    public void setCos_mord(String cos_mord) {
        this.cos_mord = cos_mord;
    }

    public boolean isEst_mord() {
        return est_mord;
    }

    public void setEst_mord(boolean est_mord) {
        this.est_mord = est_mord;
    }
}
