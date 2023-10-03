/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("form").submit(function(e){
        var regex_plat = /^[a-zA-Z0-9]+(\s[a-zA-Z0-9]+)*$/
        var regex_cos = /^[0-9]{1,4}$/
        var nom = document.getElementById('Nombre_Platillo').value
        var desc = document.getElementById('Descripcion_Platillo').value
        var cost = document.getElementById('Precio_Platillo').value
        if(!regex_plat.test(nom) || nom.length > 50 || nom.length < 4){
            alert("El nombre del platillo solo debe de tener caracteres alfanumericos y debe de ser de 4 a 50 caracteres")
            e.preventDefault()
            return false
        }     
        if(!regex_plat.test(desc) || desc.length > 200 || desc.length < 4){
            alert("La descripcion del platillo solo debe de tener caracteres alfanumericos y debe de ser de 4 a 200 caracteres")
            e.preventDefault()
            return false
        }
        if(!regex_cos.test(cost)){
            alert("El precio solo debe de tener numeros y debe de ser menor o igual a 9999")
            e.preventDefault()
            return false
        }
    })
})
