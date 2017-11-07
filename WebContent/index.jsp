<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript">

</script>

<style type="text/css">
#searchPanel {
	background-color: #4682B4;
}

input[type=number] {
	width: 60px;
}

#pic{
	display:inline-block;
    font-family:arial;
    margin-top:30px;
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
	

	
</style>

 

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
						onKeyUp="if(this.value>2017){this.value='2017';}else if(this.value<0){this.value='0';}"
						value="<c:if test="${minYear != null}"><c:out value="${minYear}"></c:out></c:if>">
					<label style="color: #FFFAF0;">-</label> <input type="number"
						name="maxYear" id="year" size="4" maxlength="4" placeholder="to"
						onKeyUp="if(this.value>2017){this.value='2017';}else if(this.value<0){this.value='0';}"
						value="<c:if test="${maxYear != null}"><c:out value="${maxYear}"></c:out></c:if>">
				</div>
				<div class="col-md-4" align="center">
					<span style="color: #FFFAF0;"><strong>Price:</strong></span> <input
						type="number" name="minPrice" size="4" max="9999"
						placeholder="min"
						onKeyUp="if(this.value>999){this.value='999';}else if(this.value<0){this.value='0';}"
						value="<c:if test="${minPrice != null}"><c:out value="${minPrice}"></c:out></c:if>">
					<label style="color: #FFFAF0;">-</label> <input type="number"
						name="maxPrice" size="4" max="99" placeholder="max"
						id = "maxPrice"
						onKeyUp="if(this.value>999){this.value='999';}else if(this.value<0){this.value='0';}"
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
				<div class="col-md-3" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=title">
						<i class="fa fa-book fa-fw fa-2x" aria-hidden="true"></i></a>
						
				</div>
			</c:if>
			<c:if test="${requestScope.sortBy.equals('title')}">
				<div class="col-md-3" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=titleDesc">
						<i class="fa fa-book fa-fw fa-2x" aria-hidden="true"></i></a>
				</div>
			</c:if>
			<c:if
				test="${!requestScope.sortBy.equals('publisher') || requestScope.sortBy == null}">
				<div class="col-md-3" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=publisher">
						<i class="fa fa-edit fa-fw fa-2x" aria-hidden="true"></i></a>
				</div>
			</c:if>
			<c:if test="${requestScope.sortBy.equals('publisher')}">
				<div class="col-md-3" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=publisherDesc">
						<i class="fa fa-edit fa-fw fa-2x" aria-hidden="true"></i></a>
				</div>
			</c:if>
			<c:if
				test="${!requestScope.sortBy.equals('year') || requestScope.sortBy == null}">
				<div class="col-md-3" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=year">
						<i class="fa fa-clock-o fa-fw fa-2x" aria-hidden="true"></i></a>
				</div>
			</c:if>
			<c:if test="${requestScope.sortBy.equals('year')}">
				<div class="col-md-3" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=yearDesc">
						<i class="fa fa-clock-o fa-fw fa-2x" aria-hidden="true"></i></a>
				</div>
			</c:if>
			<c:if
				test="${!requestScope.sortBy.equals('price') || requestScope.sortBy == null}">
				<div class="col-md-3" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=price">
						<i class="fa fa-money fa-fw fa-2x" aria-hidden="true"></i></a>
				</div>
				
			</c:if>
			<c:if test="${requestScope.sortBy.equals('price')}">
				<div class="col-md-3" align="center">
					<a
						href="sort?<c:out value="${requestScope.url}"></c:out>sortBy=priceDesc">
						<i class="fa fa-money fa-fw fa-2x" aria-hidden="true"></i></a>
				</div>
			</c:if>
		</div>
	</div>

	<div class="row">
		<c:forEach items="${requestScope.books}" var="book">
		<div id ="pic" class="col-md-4" align="center">
			
				<div class="col-md-6" align="center">
					<article>	
						<a href="BookInfo?book=<c:out value="${book.id}"></c:out>"> <img
							height="250px" width="100%"
							src="avatar?photo=<c:out value="${book.photo}"></c:out>"></a>
					</article>
				</div>
				
			<div class="col-md-6" align="left">
				<header>
				<h4><strong><c:out value="${book.title}"></c:out></strong></h4>
				</header>
				
				<h5>By:</h5>
				<h5><i><c:out value="${book.author.firstName}">
			 </c:out> <c:out value="${book.author.lastName}"></c:out></i></h5>
			
			<h6><strong>Category:&nbsp;&nbsp;&nbsp; </strong><i><c:out value="${book.category}">
			 </c:out></i></h6>
			 
			 <h6><strong>Year:&nbsp;&nbsp;&nbsp; </strong><i><c:out value="${book.year}">
			 </c:out></i></h6>
			 
			 
			 <div class="col-md-6" align="left">
				
			</div>
			 <div class="col-md-6" align="right">
			 
				<span class="tags">
	    			<span class="price-tag"><a>${book.price}</a></span>    
	   			</span> 
			</div>		

			</div>
		</div>
			
		</c:forEach>
	</div>
</div>