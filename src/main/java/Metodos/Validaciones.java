/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Metodos;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class Validaciones {
    public static String regex_pal = "^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*$";
    public static String regex_usu = "^[A-Za-z]{1}[\\w]{5,29}$";
    public static String regex_cor = "^([a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*)(@gmail.com)$";
    public static String regex_pass = "^(\\w|@|\\.|-){8,30}$";
    public static String regex_cos = "^[0-9]{1,5}$";
    public static String regex_float = "^([0-9]*[.])?[0-9]*$";
    public static String regex_cant = "^[0-9]{1,3}$";
    public static String regex_cli = "^[A-Z]{1}[a-z]{2,29}$";
    public static String regex_dir = "^[a-zA-Z]+(\\s[a-zA-Z]+)*$";
    public static String regex_cp = "^[0-9]{5}$";
    public static String regex_tel = "^[0-9]{10}$";
    public static String regex_drop = "(drop)";
    public static String regex_delete = "(delete)";
    public static String regex_update = "(update)";
    public static String regex_create = "(create)";
    public static String regex_select = "(select)";
    public static String regex_cplatillo = "(cplatillo)";
    public static String regex_dcliente = "(dcliente)";
    public static String regex_mnegocio = "(mnegocio)";
    public static String regex_morden = "(morden)";
    public static String regex_dorden = "(dorden)";
    public static String regex_mpagos = "(mpagos)";
    
    public boolean Validar_Registro_Negocio (String neg, String usu, String cor, String pass, String conf_pass, String aviso){       
        if(neg == null || usu == null || cor == null || pass == null || conf_pass == null || aviso == null){
            return false;
        }
        int longitud = neg.length();
        if(longitud < 4 || longitud > 50){
            return false;
        }
        if(!neg.matches(regex_pal)){
            return false;
        }
        neg.toLowerCase();
        if(neg.matches(regex_drop) || neg.matches(regex_delete) || neg.matches(regex_update) || neg.matches(regex_create) || neg.matches(regex_select) || neg.matches(regex_cplatillo) || neg.matches(regex_dorden) || neg.matches(regex_morden) || neg.matches(regex_mnegocio) || neg.matches(regex_dcliente)){
            return false;
        }
        if(!usu.matches(regex_usu)){
            return false;
        }
        usu.toLowerCase();
        if(usu.matches(regex_drop) || usu.matches(regex_delete) || usu.matches(regex_update) || usu.matches(regex_create) || usu.matches(regex_select) || usu.matches(regex_cplatillo) || usu.matches(regex_dorden) || usu.matches(regex_morden) || usu.matches(regex_mnegocio) || usu.matches(regex_dcliente)){
            return false;
        }
        longitud = cor.length();
        if(longitud > 50){
            return false;
        }
        if(!cor.matches(regex_cor)){
            return false;
        }
        cor.toLowerCase();
        if(cor.matches(regex_drop) || cor.matches(regex_delete) || cor.matches(regex_update) || cor.matches(regex_create) || cor.matches(regex_select) || cor.matches(regex_cplatillo) || cor.matches(regex_dorden) || cor.matches(regex_morden) || cor.matches(regex_mnegocio) || cor.matches(regex_dcliente)){
            return false;
        }
        if(!pass.matches(regex_pass)){
            return false;
        }
        if(!pass.equals(conf_pass)){
            return false;
        }
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        if(!aviso.equals("on")){
            return false;
        }
        aviso.toLowerCase();
        if(aviso.matches(regex_drop) || aviso.matches(regex_delete) || aviso.matches(regex_update) || aviso.matches(regex_create) || aviso.matches(regex_select) || aviso.matches(regex_cplatillo) || aviso.matches(regex_dorden) || aviso.matches(regex_morden) || aviso.matches(regex_mnegocio) || aviso.matches(regex_dcliente)){
            return false;
        }
        return true;       
    }
    public boolean Validar_Login_Negocio (String usu, String pass){        
        if(usu == null || pass == null){
            return false;
        }    
        if(!usu.matches(regex_usu)){
            return false;
        }        
        usu.toLowerCase();
        if(usu.matches(regex_drop) || usu.matches(regex_delete) || usu.matches(regex_update) || usu.matches(regex_create) || usu.matches(regex_select) || usu.matches(regex_cplatillo) || usu.matches(regex_dorden) || usu.matches(regex_morden) || usu.matches(regex_mnegocio) || usu.matches(regex_dcliente)){
            return false;
        }
        if(!pass.matches(regex_pass)){
            return false;
        }        
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        return true;       
    }
    public boolean Validar_Registro_Platillo (String nom, String desc, String cost, String tiempo, int longitud2){        
        if(nom == null || desc == null || cost == null || tiempo == null){
            return false;
        }
        int longitud = nom.length();
        if(longitud < 4 || longitud > 50){
            return false;
        }
        if(!nom.matches(regex_pal)){
            return false;
        }
        nom.toLowerCase();
        if(nom.matches(regex_drop) || nom.matches(regex_delete) || nom.matches(regex_update) || nom.matches(regex_create) || nom.matches(regex_select) || nom.matches(regex_cplatillo) || nom.matches(regex_dorden) || nom.matches(regex_morden) || nom.matches(regex_mnegocio) || nom.matches(regex_dcliente)){
            return false;
        }
        longitud = desc.length();
        if(longitud < 4 || longitud > 200){
            return false;
        }
        if(!desc.matches(regex_pal)){
            return false;
        }
        String pass = desc;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        if(!cost.matches(regex_cos)){
            return false;
        }
        pass = cost;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int costo = Integer.parseInt(cost);
        if(costo == 0){
            return false;
        }
        if(!tiempo.matches(regex_cos)){
            return false;
        }
        pass = tiempo;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int indice = Integer.parseInt(tiempo);
        if(indice >= longitud2){
            return false;
        }
        return true;       
    }
    public boolean Validar_Registro_Tiempo (String nom){        
        if(nom == null){
            return false;
        }
        int longitud = nom.length();
        if(longitud < 4 || longitud > 50){
            return false;
        }
        if(!nom.matches(regex_pal)){
            return false;
        }
        nom.toLowerCase();
        if(nom.matches(regex_drop) || nom.matches(regex_delete) || nom.matches(regex_update) || nom.matches(regex_create) || nom.matches(regex_select) || nom.matches(regex_cplatillo) || nom.matches(regex_dorden) || nom.matches(regex_morden) || nom.matches(regex_mnegocio) || nom.matches(regex_dcliente)){
            return false;
        }
        return true;       
    }
    public boolean Validar_Edi_Eli_Platillos(String valor, int longitud){
        if(valor == null){
            return false;
        }
        if(!valor.matches(regex_cos)){
            return false;
        }
        String pass = valor;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int indice = Integer.parseInt(valor);
        if(indice >= longitud){
            return false;
        }
        return true;
    }
    public boolean Validar_Editar_Platillo(String opcion, String valor){
        if(opcion == null || valor == null){
            return false;
        }
        if(!(opcion.equals("Descripcion") || opcion.equals("Costo"))){
            return false;
        }
        String pass = opcion;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int longitud = 0;
        pass = valor;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        if(opcion.equals("Descripcion")){
            longitud = valor.length();
            if(longitud < 4 || longitud > 200){
                return false;
            }
            if(!valor.matches(regex_pal)){
                return false;
            }
        }
        else{
            if(!valor.matches(regex_cos)){
                return false;
            }
            int cos = Integer.parseInt(valor);
            if(cos == 0){
                return false;
            }
        }
        return true;        
    }
    public boolean Validar_Agregar_Platillo_Orden(String tipo, String cant, int longitud){
        if(tipo == null || cant == null){
            return false;
        }
        if(!tipo.matches(regex_cos)){
            return false;
        }
        String pass = tipo;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int val = Integer.parseInt(tipo);
        if(val >= longitud){
            return false;
        }
        if(!cant.matches(regex_cant)){
            return false;
        }
        pass = cant;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int num = Integer.parseInt(cant);
        if(num == 0){
            return false;
        }
        return true;
    }
    public boolean Validar_Cliente(String cli, String tel, String cal, String n_ext, String n_int, String col, int longitud2){
        if(cli == null || tel == null || cal == null || n_ext == null || n_int == null || col == null){
            return false;
        }
        if(!cli.matches(regex_cli)){
            return false;
        }
        String pass = cli;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        if(!tel.matches(regex_tel)){
            return false;
        }
        pass = tel;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        if(!cal.matches(regex_pal) || cal.length() < 4 || cal.length() > 50){
            return false;
        }
        pass = cal;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        if(!n_ext.matches(regex_cos)){
            return false;
        }
        pass = n_ext;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        if(!n_int.equals("") && !n_int.matches(regex_cos)){
            return false;
        }
        pass = n_int;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        if(!col.matches(regex_cant)){
            return false;
        }
        pass = col;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int numero = Integer.parseInt(col);
        if(numero >= longitud2){
            return false;
        }
        return true;
    }
    public boolean Validar_Entrega_Orden(String valor, int longitud){
        if(valor == null){
            return false;
        }
        if(!valor.matches(regex_cos)){
            return false;
        }
        String pass;
        pass = valor;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int indice = Integer.parseInt(valor);
        if(indice >= longitud){
            return false;
        }
        return true;
    }
    public boolean Validar_Entrega_Orden_Dia(String valor){
        if(valor == null){
            return false;
        }
        if(!valor.matches(regex_cos)){
            return false;
        }
        int dia = Integer.parseInt(valor);
        if(dia > 31){
            return false;
        }
        return true;
    }
    public boolean Validar_Entrega_Orden_Mes(String valor){
        if(valor == null){
            return false;
        }
        if(!valor.matches(regex_cos)){
            return false;
        }
        int mes = Integer.parseInt(valor);
        if(mes > 11){
            return false;
        }
        return true;
    }
    public boolean Validar_Delegacion(String del, int longitud){
        if(del == null){
            return false;
        }
        if(!del.matches(regex_cos)){
            return false;
        }
        String pass = del;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int numero = Integer.parseInt(del);
        if(numero >= longitud){
            return false;
        }         
        return true;
    }
    public boolean Validar_Orden(String comentario){
        if(comentario == null){
            return false;
        }
        if((!comentario.matches(regex_pal) &&!comentario.equals(""))){
            return false;
        }
        if(comentario.length() > 200){
            return false;
        }
        return true;
    }
    public boolean Validar_Registro_Pago (String nom, String desc, String cost){        
        if(nom == null || desc == null || cost == null){
            return false;
        }
        int longitud = nom.length();
        if(longitud < 4 || longitud > 50){
            return false;
        }
        if(!nom.matches(regex_pal)){
            return false;
        }
        nom.toLowerCase();
        if(nom.matches(regex_drop) || nom.matches(regex_delete) || nom.matches(regex_update) || nom.matches(regex_create) || nom.matches(regex_select) || nom.matches(regex_cplatillo) || nom.matches(regex_dorden) || nom.matches(regex_morden) || nom.matches(regex_mnegocio) || nom.matches(regex_dcliente)){
            return false;
        }
        longitud = desc.length();
        if(longitud < 4 || longitud > 200){
            return false;
        }
        if(!desc.matches(regex_pal)){
            return false;
        }
        String pass = desc;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        if(!cost.matches(regex_cos)){
            return false;
        }
        pass = cost;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int costo = Integer.parseInt(cost);
        if(costo == 0){
            return false;
        }
        return true;       
    }
    public boolean Validar_Insumo(String nom, String unidad, String cost){
        if(nom == null || unidad == null || cost == null){
            return false;
        }
        int longitud = nom.length();
        if(longitud < 4 || longitud > 50){
            return false;
        }
        if(!nom.matches(regex_pal)){
            return false;
        }
        nom.toLowerCase();
        if(nom.matches(regex_drop) || nom.matches(regex_delete) || nom.matches(regex_update) || nom.matches(regex_create) || nom.matches(regex_select) || nom.matches(regex_cplatillo) || nom.matches(regex_dorden) || nom.matches(regex_morden) || nom.matches(regex_mnegocio) || nom.matches(regex_dcliente)){
            return false;
        }if(!cost.matches(regex_cos)){
            return false;
        }
        String pass = cost;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int costo = Integer.parseInt(cost);
        if(costo <= 0){
            return false;
        }
        if(!unidad.equals("1") && !unidad.equals("2") && !unidad.equals("3")){
            return false;
        }
        return true;
    }
    public boolean Validar_Agregar_Insumo_Platillo(String tipo, String cant, int longitud){
        if(tipo == null || cant == null){
            return false;
        }
        if(!tipo.matches(regex_cos)){
            return false;
        }
        String pass = tipo;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        int val = Integer.parseInt(tipo);
        if(val >= longitud){
            return false;
        }
        if(!cant.matches(regex_float)){
            return false;
        }
        pass = cant;
        pass.toLowerCase();
        if(pass.matches(regex_drop) || pass.matches(regex_delete) || pass.matches(regex_update) || pass.matches(regex_create) || pass.matches(regex_select) || pass.matches(regex_cplatillo) || pass.matches(regex_dorden) || pass.matches(regex_morden) || pass.matches(regex_mnegocio) || pass.matches(regex_dcliente)){
            return false;
        }
        float num = Float.parseFloat(cant);
        if(num == 0){
            return false;
        }
        return true;
    }
}
