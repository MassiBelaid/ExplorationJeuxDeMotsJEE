<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>IntractionMots</title>
</head>
<body>

	<form method="post">
		<label for="terme">Entrez un terme : </label>
        <input type="text" name="terme" id="terme" />
        <input id="buttonValider" type="submit" />
	</form>
	
	<c:if test="${ empty listeTR }" var="variable">
    <p>Terme non valide</p>
	</c:if>
	
	<p id="chargement"></p>
	<c:forEach var="current" items="${listeTR}" >

    <p>${current}</p>

 </c:forEach>
 
 
 
 <script src="<c:url value='resources/js/acceuil.js'/>"></script>
 
</body>
</html>