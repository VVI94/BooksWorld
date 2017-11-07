<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<head>
<link type="text/css" rel="stylesheet" href= "css/cart.css"/>  
<script type="text/javascript">
	function numberLenght(){
		
		if (this.value > 99) {
			this.value = '99';
		} else if (this.value < 0){
			this.value = '0';
		}
		
		$("#maxPrice");
		
	}
</script>

<style type="text/css">

#price{
display: inline;
}
.total{
display: inline;
background-color: orange;
color: white;  
}
.d{
display: inline;
}

.BGN{
display: inline;
}

#totalBuy{
display: inline;
}

#close{
color: red;
cursor: pointer;
}
</style>

<c:if test="${sessionScope.user == null}">
	<meta http-equiv="refresh" content="0; url=http://localhost:8080/BooksWorld/error404.html" />
</c:if> 
</head>

<div class="container body-content span=8 offset=2">
	
	<div class="row">
		<div id ="cartBooks" ></div>
	</div>
		
</div>

<br>
<br>
<br>
			<div align="right">
				<button id="buyButton22" class="button yellow" value="<c:out value='${book.id}'></c:out>">
					<div class="tittle">BUY</div>
					<div class="price"><h3 id ="totalBuy"></h3><h6 class="BGN"> &nbsp; BGN</h6></div>
				</button>
			</div>

<script src='loadCart.js'></script> 