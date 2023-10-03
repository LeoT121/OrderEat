/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("form").submit(function(e){
        var regex_nomneg = /^[a-zA-Z0-9]+(\s[a-zA-Z0-9]+)*$/
        var regex_usu = /^[A-Za-z]{1}[\w]{5,29}$/
        var regex_cor = /^([a-zA-Z0-9]+(\.[a-zA-Z0-9]+)*)(@gmail.com)$/
        var regex_pass = /^(\w|@|\.|-){8,30}$/
        var nom_neg = document.getElementById('Nombre_Negocio').value
        var nom_usu = document.getElementById('Nombre_Usuario').value
        var cor = document.getElementById('Correo_Negocio').value
        var pass = document.getElementById('Password').value
        var conf_pass = document.getElementById('Confirmar_Password').value
        if(!regex_nomneg.test(nom_neg)){
            alert("El nombre del negocio solo debe de contener caracteres alfanumericos y espacios")
            $('#Nombre_Negocio').focus()
            e.preventDefault()
            return false
        }
        if(nom_neg.length < 4 || nom_neg.length > 50){
            alert("El nombre del negocio debe ser de 4 a 30 caracteres")
            e.preventDefault()
            return false
        }
        if(!regex_usu.test(nom_usu)){
            alert("El nombre de usuario debe ser de 6 a 30 caracteres, ademas de que solo se aceptan caracteres alfanumeros y guion bajo, tambien debe de iniciar con una letra")
            e.preventDefault()
            return false
        }
        if(!regex_cor.test(cor) || cor.length > 50){
            alert("El correo debe de ser de gmail, ademas de cumplir con la sintaxis de dicho correo, el correo debe de ser menor o igual a 50 caracteres")
            e.preventDefault()
            return false
        }
        if(!regex_pass.test(pass)){
            alert("La contraseña debe ser de 8 a 30 caracteres, ademas de que solo se aceptan caracteres alfanumericos, guion, guion bajo, arroba y punto")
            e.preventDefault()
            return false
        }
        if(conf_pass != pass){
            alert("Las contraseñas no coinciden")
            e.preventDefault()
            return false
        }
        if(!($('#Confirmar_Aviso').prop('checked'))){
            alert("Debe de leer el aviso de privacidad y aceptar el aviso")
            e.preventDefault()
            return false
        }
    })
})
