console.log("Bien ICI");


/*window.onload = function(){
  console.log("c'est correcte");
chargement.textContent = "";
}*/

var chargement = document.getElementById("chargement");
var buttonValiderElt = document.getElementById("buttonValider");

buttonValiderElt.addEventListener("click", function(e){
	var chargement = document.getElementById("chargement");
	chargement.textContent = "Chargement ...";
	
});

/*if (document.readyState === 'complete') {
  var chargement = document.getElementById("chargement");
	chargement.textContent = "Chargement ...";
}*/


window.addEventListener("DOMContentLoaded", (event) => {
    console.log("DOM entièrement chargé et analysé");
	var chargement = document.getElementById("chargement");
	chargement.textContent = "";
  });


