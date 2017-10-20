<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Book world</title>
        <link type="text/css" rel="stylesheet" href= "css/style.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 

</head>

<body>


	<jsp:include page="fragments/header.jsp"></jsp:include>
	
	<br>
	<br>
	<br>
	<br>
	
	<jsp:include page="${view}"></jsp:include>
	
	<br>
	<br>
	<br>
	<br>

	<jsp:include page="fragments/footer.jsp"></jsp:include>


</body>

</html>