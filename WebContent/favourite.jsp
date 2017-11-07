<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<script type="text/javascript">

</script>

<style type="text/css">
#searchPanel {
	background-color: #4682B4;
}

body{
background-image: url("images/static/fav.jpg");

}

#tuk{

background-color: rgba(255, 256, 255, 0.8);

}

	.tags a {
		float: left;
		position: relative;
		width: auto;
		height: 30px;
		margin-left: 20px;
		padding: 0 12px;
		line-height: 30px;
		background: #1f8dd6;
		color: #fff;
		font-size: 18px;
		font-weight: 600;
		text-decoration: none;
		margin-top:50px;		
	}

	.tags a:before {
		content: "";
		position: absolute;
		top: 0;		
		width: 0;
		height: 0;		
		border-style: solid;		
	}
	
	.tags a:after {
		content: "";
		position: absolute;
		top: 13px;
		width: 4px;
		height: 4px;
		-moz-border-radius: 2px;
		-webkit-border-radius: 2px;
		border-radius: 2px;
		background: #fff;
		-moz-box-shadow: -1px -1px 2px #004977;
		-webkit-box-shadow: -1px -1px 2px #004977;
		box-shadow: -1px -1px 2px #004977;
	}	
	
	.tags a:hover {
		background: #1d85ca;
	}	
	

	.price-tag a {		
		-moz-border-radius-bottomright: 4px;
		-webkit-border-bottom-right-radius: 4px;
		border-bottom-right-radius: 4px;
		-moz-border-radius-topright: 4px;
		-webkit-border-top-right-radius: 4px;
		border-top-right-radius: 4px;
	}
	

	.price-tag a:before {		
		left: -15px;	
		border-color: transparent #1f8dd6 transparent transparent;
		border-width: 15px 15px 15px 0;
	}
	

	.price-tag a:after {		
		left: -2px;
	}
	
	.price-tag a:hover:before {
		border-color: transparent #1d85ca transparent transparent;
	}


	.post-tag a {		
		-moz-border-radius-bottomleft: 4px;
		-webkit-border-bottom-left-radius: 4px;
		border-bottom-left-radius: 4px;
		-moz-border-radius-topleft: 4px;
		-webkit-border-top-left-radius: 4px;
		border-top-left-radius: 4px;
	}
	

	.post-tag a:before {		
		right: -15px;	
		border-color: transparent transparent transparent #1f8dd6;
		border-width: 15px 0 15px 15px;
	}
	

	.post-tag a:after {		
		right: -2px;
	}
	
	.post-tag a:hover:before {
		border-color: transparent transparent transparent #1d85ca;
	}
	
	#pic{
	display:inline-block;
    font-family:arial;
    margin-top:30px;
}

</style>

<c:if test="${sessionScope.user == null}">
	<meta http-equiv="refresh" content="0; url=http://localhost:8080/BooksWorld/error404.html" />
</c:if>
</head>
<div class="container body-content span=8 offset=2">

	<div id="tuk">
		<div class="panel panel-default" id="searchPanel">
			<br>
			<div class="row">
				<c:forEach items="${requestScope.categories.keySet()}"
					var="category">
					<div class="col-md-2" align="center">
						<c:if test="${requestScope.markCategories.contains(category)}">
							<input id="<c:out value='${category}'></c:out>" type="checkbox"
								name="<c:out value='${category}'></c:out>" value="true"
								checked="checked">
						</c:if>
						<c:if test="${!requestScope.markCategories.contains(category)}">
							<input id="<c:out value='${category}'></c:out>" type="checkbox"
								name="<c:out value='${category}'></c:out>" value="true">
						</c:if>
						<label style="color: #FAF0E6;"
							for="<c:out value='${category}'></c:out>"><c:out
								value='${category}'></c:out></label>
					</div>

				</c:forEach>
			</div>
			<br>
			<div class="row">
				<div class="col-md-4" align="center">
					<span style="color: #FFFAF0;"><strong>Author:</strong></span> <input
						type="search" name="author" id="author" placeholder="full name"
						value="<c:if test="${author != null}"><c:out value="${author}"></c:out></c:if>">
				</div>
				<div class="col-md-4" align="center">
					<span style="color: #FFFAF0;"><strong>Year:</strong></span> <input
						type="number" name="minYear" id="minYear" size="4" maxlength="4"
						onKeyUp="if(this.value>2017){this.value='2017';}else if(this.value<0){this.value='0';}"
						placeholder="from"
						value="<c:if test="${minYear != null}"><c:out value="${minYear}"></c:out></c:if>">
					<label style="color: #FFFAF0;">-</label> <input  type="number" 
						onKeyUp="if(this.value>2017){this.value='2017';}else if(this.value<0){this.value='0';}"
						name="maxYear" id="maxYear" size="4" maxlength="4" placeholder="to"
						value="<c:if test="${maxYear != null}"><c:out value="${maxYear}"></c:out></c:if>">
				</div>
				<div class="col-md-4" align="center">
					<span style="color: #FFFAF0;"><strong>Price:</strong></span> <input oninput="numberLenght()"
						onKeyUp="if(this.value>9999){this.value='9999';}else if(this.value<0){this.value='0';}"
						type="number" name="minPrice" id="minPrice" size="4" max="9999"
						placeholder="min"
						value="<c:if test="${minPrice != null}"><c:out value="${minPrice}"></c:out></c:if>">
					<label style="color: #FFFAF0;">-</label> <input type="number" 
						onKeyUp="if(this.value>9999){this.value='9999';}else if(this.value<0){this.value='0';}"
						name="maxPrice" id="maxPrice" size="4" max="9999" placeholder="max"						
						value="<c:if test="${maxPrice != null}"><c:out value="${maxPrice}"></c:out></c:if>">
				</div>
			</div>
			<div class="row" align="right">
				<input id="try" type="submit" value="Enter">
			</div>
		</div>
	
	<div class="row">
		<div id ="favBooks"></div>
	</div>
	
	<br>
	<br>
	<br>
	</div>
</div>

<script src='favourite.js'></script> 