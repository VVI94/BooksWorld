<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Book world</title>
        <link type="text/css" rel="stylesheet" href= "css/style.css"/> 
        <link type="text/css" rel="stylesheet" href= "css/search.css"/>       
        <script src="scripts/jquery-3.2.1.min.js"></script>
  		<script src="scripts/bootstrap.js"></script>
 
</head>

<body>


	<jsp:include page="fragments/header.jsp"></jsp:include>
	

	
	<jsp:include page="${view}"></jsp:include>
	
	<br>
	<br>
	<br>
	<br>

	<jsp:include page="fragments/footer.jsp"></jsp:include>


</body>

</html>