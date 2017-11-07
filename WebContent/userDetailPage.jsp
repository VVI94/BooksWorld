<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Profile</title>
</head>
<body>
<div class="container body-content span=8 offset=2">


	<div class="row">
		
		<div style="float: left;" class="col-md-6" >
		
			<article>
				<div align="center">
					<header>
						<c:out value="${user.firstname}  ${user.lastname}"></c:out>
					</header>	
					<div class="img-holder">		
					<img height="650px" width="100%" src="avatar?photo=<c:out value="${user.picture}"></c:out>">
					
					
				</div>	
				<div align="center">
				    <form>				     
				       <div class="rating" >				        
				            <input id="star5" name="star" type="radio" value="5" class="radio-btn hide" />
				            <label for="star5">☆</label>
				            <input id="star4" name="star" type="radio" value="4" class="radio-btn hide" />
				            <label for="star4">☆</label>
				            <input id="star3" name="star" type="radio" value="3" class="radio-btn hide" />
				            <label for="star3">☆</label>
				            <input id="star2" name="star" type="radio" value="2" class="radio-btn hide" />
				            <label for="star2">☆</label>
				            <input id="star1" name="star" type="radio" value="1" class="radio-btn hide" />
				            <label for="star1">☆</label>
				            <div class="clear"></div>
				        </div>				        
				    </form>					
				</div>		
				
		 		
			</article>
		</div>
				
		<div style="float: right;" class="col-md-6" align="left">
		<label>Username</label>
		<c:out value="${user.username}"></c:out>
		<label>Email</label>
		<c:out value="${user.email}"></c:out>
		<label>Address</label>
		<c:out value="${user.address}"></c:out>
		<label>Telephone</label>
		<c:out value="${user.telephone}"></c:out>
			<h3>Description:</h3>
			<c:out value="${user.description}"></c:out>
		</div>
</body>
</html>