<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>IntractionMots</title>
</head>
<body>
	<p><c:out value="Bonjour !" /></p>
	<br>
	
	<c:forEach var="current" items="${listeTR}" >

    <p>${current}</p>

 </c:forEach>
 
 
 
 <script src="<c:url value='resources/js/acceuil.js'/>"></script>
 
</body>
</html>