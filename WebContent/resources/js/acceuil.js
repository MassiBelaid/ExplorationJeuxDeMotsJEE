console.log("Bien ICI");

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