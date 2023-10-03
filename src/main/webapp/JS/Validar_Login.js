/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("form").submit(function(e){
        var regex_usu = /^[A-Za-z]{1}[\w]{5,29}$/
        var regex_pass = /^(\w|@|\.|-){8,30}$/
        var nom_usu = document.getElementById('Nombre_Usuario').value
        var pass = document.getElementById('Password').value
        if(!regex_usu.test(nom_usu)){
            alert("La sintaxis del nombre de usuario no cumple con la sintaxis que se maneja en la pagina de Ordereat")
            e.preventDefault()
            return false
        }     
        if(!regex_pass.test(pass)){
            alert("La sintaxis de la contraseña no cumple con la sintaxis que se maneja en la pagina de Ordereat")
            e.preventDefault()
            return false
        }
    })
})

