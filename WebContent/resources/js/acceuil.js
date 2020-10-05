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


/*
function makeHttpObject() {
	  try {return new XMLHttpRequest();}
	  catch (error) {}
	  try {return new ActiveXObject("Msxml2.XMLHTTP");}
	  catch (error) {}
	  try {return new ActiveXObject("Microsoft.XMLHTTP");}
	  catch (error) {}

	  throw new Error("Could not create HTTP request object.");
	}

	var request = makeHttpObject();
	request.open("GET", "Access-Control-Allow-Origin: http://www.jeuxdemots.org/rezo-dump.php?gotermsubmit=Chercher&gotermrel=chat", true);
	request.send(null);
	request.onreadystatechange = function() {
	  if (request.readyState == 4)
	    console.log(request.responseText);
	};

*/