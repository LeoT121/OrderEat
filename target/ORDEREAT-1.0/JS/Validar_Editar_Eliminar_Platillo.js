/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $("form").submit(function(e){
        if(!$('input[name=Platillo]:radio').is(':checked')){
            alert("Debes de elegir un platillo para poder editarlo")
            e.preventDefault()
            return false
        }
    })
})

