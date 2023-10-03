/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
    $("form").submit(function(e){
        var regex_num = /^[0-9]{1,3}$/
        var cant = document.getElementById('Cantidad').value
        if(!regex_num.test($('select[id=Platillo]').val())){
            e.preventDefault()
            alert("Debes de elegir un platillo")
            return false;
        }
        if(!regex_num.test(cant)){
            e.preventDefault()
            alert("La cantidad debe de ser numeros positivos menores a 999")
            return false
        }
    })
})