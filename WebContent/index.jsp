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

input[type=number] {
	width: 60px;
}

#try {
	background-image: url('./images/static/books.jpg');
}
</style>

 <div class="row" id ="searchResult">
</div> 

<div class="container body-content span=8 offset=2">

	<form action="sort">
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
						type="number" name="minYear" id="year" size="4" maxlength="4"
						placeholder="from"
						value="<c:if test="${minYear != null}"><c:out value="${minYear}"></c:out></c:if>">
					<label style="color: #FFFAF0;">-</label> <input type="number"
						name="maxYear" id="year" size="4" maxlength="4" placeholder="to"
						value="<c:if test="${maxYear != null}"><c:out value="${maxYear}"></c:out></c:if>">
				</div>
				<div class="col-md-4" align="center">
					<span style="color: #FFFAF0;"><strong>Price:</strong></span> <input
						type="number" name="minPrice" size="4" max="9999"
						placeholder="min"
						value="<c:if test="${minPrice != null}"><c:out value="${minPrice}"></c:out></c:if>">
					<label style="color: #FFFAF0;">-</label> <input type="number"
						name="maxPrice" size="4" max="99" placeholder="max"
						onKeyUp="numberLenght()" id = "maxPrice"
						value="<c:if test="${maxPrice != null}"><c:out value="${maxPrice}"></c:out></c:if>">
				</div>
			</div>
			<div class="row" align="right">
				<input id="try" type="submit" value="Enter">
			</div>
		</div>
	</form>


	<div class="well">
		<div class="row">

			<c:if
				test="${!requestScope.sortBy.equals('title') || requestScope.sortBy == null }">
				<div class="col-md-2" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=title">title</a>
				</div>
			</c:if>
			<c:if test="${requestScope.sortBy.equals('title')}">
				<div class="col-md-2" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=titleDesc">title</a>
				</div>
			</c:if>
			<c:if
				test="${!requestScope.sortBy.equals('publisher') || requestScope.sortBy == null}">
				<div class="col-md-2" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=publisher">publisher</a>
				</div>
			</c:if>
			<c:if test="${requestScope.sortBy.equals('publisher')}">
				<div class="col-md-2" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=publisherDesc">publisher</a>
				</div>
			</c:if>
			<c:if
				test="${!requestScope.sortBy.equals('year') || requestScope.sortBy == null}">
				<div class="col-md-2" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=year">year</a>
				</div>
			</c:if>
			<c:if test="${requestScope.sortBy.equals('year')}">
				<div class="col-md-2" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=yearDesc">year</a>
				</div>
			</c:if>
			<c:if
				test="${!requestScope.sortBy.equals('price') || requestScope.sortBy == null}">
				<div class="col-md-2" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=price">price</a>
				</div>
			</c:if>
			<c:if test="${requestScope.sortBy.equals('price')}">
				<div class="col-md-2" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=priceDesc">price</a>
				</div>
			</c:if>
		</div>
	</div>

	<div class="row">
		<c:forEach items="${requestScope.books}" var="book">
			<div class="col-md-2" align="center">
				<article>
					<header>
						<c:out value="${book.title}"></c:out>
					</header>
					<a href="BookInfo?book=<c:out value="${book.id}"></c:out>"> <img
						height="250px" width="100%"
						src="avatar?photo=<c:out value="${book.photo}"></c:out>"></a>

				</article>
			</div>
		</c:forEach>
	</div>
</div>