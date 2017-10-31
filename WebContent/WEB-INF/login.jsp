<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<c:if test="${ requestScope.error == null }">
			<h1>Welcome, please login</h1>
		</c:if>
		<c:if test="${ requestScope.error != null }">
			<h1 style="color: red">Sorry, username or password missmatch. Reason: ${requestScope.error }</h1>
		</c:if>
		<form action="login" method="post">
			Username<input type="text" name="username"><br>
			Password<input type="password" name="password"><br>
			<input type="submit" value="Login"><br>
		</form>
		Don`t have an account yet? Please register <a href="userRegister.jsp">here</a>.
</body>
</html>