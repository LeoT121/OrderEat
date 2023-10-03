/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("form").submit(function(e){
        var regex_plat = /^[a-zA-Z0-9]+(\s[a-zA-Z0-9]+)*$/
        var regex_cos = /^[0-9]{1,4}$/
        var tipo = document.getElementById('Valor_Cambiar').value
        var valor = document.getElementById('Nuevo_Valor').value
        if(tipo == 'Descripcion'){
            if(!regex_plat.test(valor) || valor.length > 200 || valor.length < 4){
                alert("La descripcion del platillo solo debe de tener caracteres alfanumericos y debe de ser de 4 a 200 caracteres")
                e.preventDefault()
                return false
            }   
        }
        else if(tipo == 'Costo'){
            if(!regex_cos.test(valor)){
                alert("El precio solo debe de tener numeros y debe de ser menor o igual a 9999")
                e.preventDefault()
                return false
            }
            
        }
        else{
            e.preventDefault()
            return false
        }
    })
})
