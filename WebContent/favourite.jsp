<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
#searchPanel {
	background-color: #4682B4;
}

body{
background-image: url("images/static/fav.jpg");

}

#tuk{

background-color: rgba(255, 256, 255, 0.8);

}


</style>

 

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
						placeholder="from"
						value="<c:if test="${minYear != null}"><c:out value="${minYear}"></c:out></c:if>">
					<label style="color: #FFFAF0;">-</label> <input type="number"
						name="maxYear" id="maxYear" size="4" maxlength="4" placeholder="to"
						value="<c:if test="${maxYear != null}"><c:out value="${maxYear}"></c:out></c:if>">
				</div>
				<div class="col-md-4" align="center">
					<span style="color: #FFFAF0;"><strong>Price:</strong></span> <input
						type="number" name="minPrice" id="minPrice" size="4" max="9999"
						placeholder="min"
						value="<c:if test="${minPrice != null}"><c:out value="${minPrice}"></c:out></c:if>">
					<label style="color: #FFFAF0;">-</label> <input type="number"
						name="maxPrice" id="maxPrice" size="4" max="99" placeholder="max"						
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