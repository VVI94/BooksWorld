<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

 <div class="container body-content span=8 offset=2">
 	<div class="well">
 		<div class="row">
 			<form action="sort">
	 		<c:forEach items="${requestScope.categories.keySet()}" var="category">	
		 		<div class="col-md-2" align="center"> 
		 			<c:if test="${requestScope.markCategories.contains(category)}">
			 		<input id = "<c:out value='${category}'></c:out>" type="checkbox"
			 		name ="<c:out value='${category}'></c:out>" value="true"
			 		checked="checked"> 
			 		</c:if>	
			 		<c:if test="${!requestScope.markCategories.contains(category)}">
			 		<input id = "<c:out value='${category}'></c:out>" type="checkbox"
			 		name ="<c:out value='${category}'></c:out>" value="true"> 
			 		</c:if>
			 		<label for = "<c:out value='${category}'></c:out>" ><c:out value='${category}'></c:out></label>	
			 	</div> 	
			</c:forEach>
			<input type="submit" value="Enter">
			</form>
		</div>
	</div>
	 	<div class="well">
 		<div class="row">

				<c:if test="${!requestScope.sortBy.equals('title') || requestScope.sortBy == null }">
			 		<div class="col-md-2" align="center">
			 			<a href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=title">title</a>
			 		</div> 
		 		</c:if>
				<c:if test="${requestScope.sortBy.equals('title')}">
			 		<div class="col-md-2" align="center">
			 			<a href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=titleDesc">title</a>
			 		</div> 
		 		</c:if>	
		 		<c:if test="${!requestScope.sortBy.equals('publisher') || requestScope.sortBy == null}">	 		
			 		<div class="col-md-2" align="center">
			 			<a href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=publisher">publisher</a>
			 		</div> 
		 		</c:if>	
		 		<c:if test="${requestScope.sortBy.equals('publisher')}">	 		
			 		<div class="col-md-2" align="center">
			 			<a href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=publisherDesc">publisher</a>
			 		</div> 
		 		</c:if>
		 		<c:if test="${!requestScope.sortBy.equals('year') || requestScope.sortBy == null}">			 		
			 		<div class="col-md-2" align="center">
			 			<a href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=year">year</a>
			 		</div> 
		 		</c:if>
		 		<c:if test="${requestScope.sortBy.equals('year')}">			 		
			 		<div class="col-md-2" align="center">
			 			<a href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=yearDesc">year</a>
			 		</div> 
		 		</c:if>	
		 		<c:if test="${!requestScope.sortBy.equals('price') || requestScope.sortBy == null}">			 		
			 		<div class="col-md-2" align="center">
			 			<a href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=price">price</a>
				 	</div> 
			 	</c:if>	
		 		<c:if test="${requestScope.sortBy.equals('price')}">			 		
			 		<div class="col-md-2" align="center">
			 			<a href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=priceDesc">price</a>
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
					<a href ="BookInfo?book=<c:out value="${book.id}"></c:out>" >
					<img height="250px" width="150px" src="avatar?photo=<c:out value="${book.photo}"></c:out>"></a>
	
				</article>
			</div>
		</c:forEach>
	</div>
</div>