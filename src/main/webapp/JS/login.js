/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*===== FOCUS =====*/
var inputs = document.querySelectorAll(".form__input")

/*=== Add focus ===*/
function addfocus(){
    var parent = this.parentNode.parentNode
    parent.classList.add("focus")
}

/*=== Remove focus ===*/
function remfocus(){
    var parent = this.parentNode.parentNode
    if(this.value == ""){
        parent.classList.remove("focus")
    }
}

/*=== To call function===*/
inputs.forEach(input=>{
    input:addEventListener("focus",addfocus),
    input:addEventListener("blur",remfocus)
})