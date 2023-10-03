/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("form").submit(function(e){
        var regex_num = /^[0-9]{1,4}$/
        if(!regex_num.test($('select[id=Alcaldia]').val())){
            e.preventDefault()
            alert("Debes de elegir una alcaldia")
            return false;
        }
    })
})