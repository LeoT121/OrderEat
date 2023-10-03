/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    $("form").submit(function(e){
        var regex_plat = /^[a-zA-Z0-9]+(\s[a-zA-Z0-9]+)*$/
        var nom = document.getElementById('Comentario').value
        if(!regex_plat.test(nom) && nom != ""){
            alert("El comentario solo debe de tener caracteres alfanumericos")
            e.preventDefault()
            return false
        }
        if(nom.length > 200){
            alert("El comentario debe ser menor o igual a 200 caracteres")
            e.prevenDefault()
            return false
        }
    })
})