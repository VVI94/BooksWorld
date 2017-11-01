<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>

<body>
 <div class="container body-content span=8 offset=2">
	<div class="well">
		<fieldset>
			<div  align="center">

			<c:if test="${error == null }">
				<h1>Welcome, please login</h1>
			</c:if>
			<c:if test="${error != null }">
				<h1 style="color: red">Sorry, username or password missmatch. Reason: ${error }</h1>
			</c:if>
			</div>
			<br/>
			<br/>
			<br/>
			<form action="login" method="post" class="form-horizontal">
			 <div class="form-group" align="center">
				<label class="col-sm-4 control-label">Username</label>
				<div class="col-sm-4">
				<input class="form-control" type="text" name="username" id="user" placeholder="username" required="required">
				</div>
			</div>
			 <div class="form-group" align="center">
				<label class="col-sm-4 control-label">Password</label>
				<div class="col-sm-4">
				<input class="form-control" type="password" name="password" id="pass" placeholder="password" required="required">
				</div>
			</div>
			<div class="form-group" align="right">
				<label class="col-sm-4 control-label"></label>
				<div class="col-sm-4">
				Don`t have an account yet? Please register <a href="./Register">here</a>.
				<br>
				<input type="submit" value="Login"><br>
				
			
				</div>
			</div>	
			</form>

		</fieldset>
	</div>
</div>		
</body>
</html>