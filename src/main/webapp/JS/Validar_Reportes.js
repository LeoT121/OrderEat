/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){

    $("form").submit(function(e){
        $('form1').click(function(){
            if(!$('input[name=Opcion]:radio').is(':checked')){
                alert("Debes de elegir una orden para marcarla como entregada")
                e.preventDefault()
                return false
            }
            var mensaje = "Esta seguro que ya se entrego la orden";
            var opcion = confirm(mensaje)
            if(!opcion){
                e.preventDefault()
                return false
            }
        })        
        $('form2').click(function(){
            if(!$('input[name=Opcion2]:radio').is(':checked')){
                alert("Debes de elegir una orden para marcarla como entregada")
                e.preventDefault()
                return false
            }
            var mensaje = "Esta seguro que ya se entrego la orden";
            var opcion = confirm(mensaje)
            if(!opcion){
                e.preventDefault()
                return false
            }
        })        
    })
})