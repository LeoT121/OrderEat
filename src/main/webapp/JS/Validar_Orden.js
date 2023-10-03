/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("form").submit(function(e){
        var cliente = document.getElementById('Cliente').value
        var tel = document.getElementById('Telefono').value
        var calle = document.getElementById('Calle').value
        var num_int = document.getElementById('N_Int').value
        var num_ext = document.getElementById('N_Ext').value
        var col = document.getElementById('Col').value
        var regex_cli = /^[A-Z]{1}[a-z]{2,29}$/
        var regex_calle = /^[a-zA-Z0-9]+(\s[a-zA-Z0-9]+)*$/
        var regex_num = /^[0-9]{1,4}$/
        var regex_alc = /^[a-zA-Z]+(\s[a-zA-Z]+)*$/
        var regex_cp = /^[0-9]{5}$/
        var regex_tel = /^[0-9]{10}$/
        if(!regex_cli.test(cliente)){
            alert("El nombre del cliente debe ser solo uno y debe de iniciar con una letra mayuscula y las demas minusculas, debe de ser entre 3 a 30 caracteres")
            e.preventDefault()
            return false
        }
        if(!regex_tel.test(tel)){
            alert("El telefono solo debe de contener numeros y debe de ser de 10 numeros")
            e.preventDefault()
            return false
        }
        if(!regex_calle.test(calle) || calle.length > 50 || calle.length < 4){
            alert("El nombre de la calle solo debe de contener caracteres alfanumericos y debe de ser de 4 a 50 caracteres")
            e.preventDefault()
            return false
        }
        if(!regex_num.test(num_ext)){
            alert("El numero exterior solo debe de contener numeros y debe de ser de 1 a 4 numeros")
            alert(num_ext)
            e.preventDefault()
            return false
        }
        if(num_int != "" && !regex_num.test(num_int)){
            alert("El numero interior solo debe de contener numeros y debe de ser de 1 a 4 numeros")
            e.preventDefault()
            return false
        }
        if(!regex_num.test(col)){
            alert("Debes de elegir una colonia")
            e.preventDefault()
            return false
        }    
        
    })
})
