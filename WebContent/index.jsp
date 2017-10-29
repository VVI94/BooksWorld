<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <div class="container body-content span=8 offset=2">
 

</div>

<div class="row">
	<c:forEach items="${requestScope.books}" var="book">
		<div class="col-md-2" align="center">
			<article>
                 <header>
					<c:out value="${book.title}"></c:out>
				</header>	
				<a href ="BookInfo?book=<c:out value="${book.id}"></c:out>" >
				<img height="250px" width="150px" src="avatar?book=<c:out value="${book.id}"></c:out>"></a>

			</article>
		</div>
	</c:forEach>
</div>
