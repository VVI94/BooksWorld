<%@ page language="java" contentType="text/html; charset = UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href= "css/style1.css"/> 
<link type="text/css" rel="stylesheet" href= "css/raiting.css"/>  
<link type="text/css" rel="stylesheet" href= "css/cart.css"/>   

 <head>   
<script type="text/javascript">
$(function(){
	var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
	
	
	var bookId = getUrlParameter('book');
	
	
	loadInFavouriteIcon()
	
	
	function loadInFavouriteIcon() {
	
		var result = '';
		var isInFavourite ='';
		$.get('favourite?id=' + bookId).then(function(data){
			isInFavourite = data;
			
			if(isInFavourite === 'true'){
				result += "<a class=\"link\" ><b class=\"fa fa-heart fa-fw fa-3x\" ></b></a>";
			}else{
				result += "<a class=\"link\" ><b class=\"fa fa-heart-o fa-fw fa-3x\" ></b></a>";
			}
			
			$("#fav").html(result);
								
		});

			$('#fav').off().on('click',function(){
				
				var data  = {
						id: bookId
					
					}; 
							
									
						$.post('favourite', {data: JSON.stringify(data)}).then(function() {
							loadInFavouriteIcon()
							
						});			
				
			});
	}
	
});

</script>

<style type="text/css">
.link{
cursor: pointer;
}
.BGN{
display: inline;
}

.like{
    cursor: pointer;
}
.likeReply{
    cursor: pointer;
}

#commentBack {
    background-color: threedhighlight;
}
#commentDescr {
    background-color: none;
}

.img-holder {position: relative;}
.img-holder .link {
    position: absolute;
    bottom: 650px;
    right: 0px; 
    color: red;
}

.img-holder1 {position: relative;}
.img-holder1 .link {
    position: absolute;
    bottom: 25px;
    right: 35px; 
    color: red;
}

#close{
color: red;
cursor: pointer;
}

#buyQuantity, #buyButton{
	display:inline-block;
    font-family:arial;
    margin-top:25px;
 
}

 #buyButton{
	display:inline-block;

    margin-top:25px;
}

#bookTitle{
 margin-bottom:50px;
font-size: 60px;
text-decoration: underline;
}


</style>

<c:if test="${sessionScope.user == null}">
	<meta http-equiv="refresh" content="0; url=http://localhost:8080/BooksWorld/error404.html" />
</c:if>
</head>
 <div class="container body-content span=8 offset=2">
	<div align="center" class="row">
					<header>
						<h1 id ="bookTitle"><strong><i><c:out value="${book.title}"></c:out></i></strong></h1>
					</header>
	</div>						
	<div class="row">
		
		<div style="float: left;" class="col-md-6" >
		
			<article>
				<div align="center">
					
					<div class="img-holder">		
					<img height="700px" width="100%" src="avatar?photo=<c:out value="${book.photo}"></c:out>">
					<div id="fav" ></div>
					</div>
					
				</div>	
					<div class="row">
					
					
				<div class="col-md-6" align="left">
				    <form>				     
				       <div class="rating" >				        			           
				       </div>				        
				    </form>					
				</div>	
				
				
				<div id="buyQuantity1" class="col-md-6" align="right">
					<div class="col-md-12">
						<input value ="1" id="buyQuantity" type="number" size="2" min ="1" max="99" onKeyUp="if(this.value>99){this.value='99';}else if(this.value<0){this.value='0';}">				
					</div>
				</div>	
				</div>
		 		
			</article>
		</div>
				
		<div style="float: right;" class="col-md-6" align="left">
		<div class="row">
			<div class="col-md-8" align="left">
			 <h3><strong>Author:&nbsp;&nbsp;&nbsp; </strong><i><c:out value="${book.author.firstName}">
			 </c:out> <c:out value="${book.author.lastName}"></c:out></i></h3>
			
			<h3><strong>Category:&nbsp;&nbsp;&nbsp; </strong><i><c:out value="${book.category}">
			 </c:out></i></h3>
			 
			 <h3><strong>Year:&nbsp;&nbsp;&nbsp; </strong><i><c:out value="${book.year}">
			 </c:out></i></h3>
			
			</div>
			<div class="col-md-4" align="right">
					<div class="img-holder1" align="right">		
					<img height="100px" width="100" src="images/static/rating.jpg">
					<a class="link" ><i id="avgRating" ></i></a>
					</div>
					<div align="left">
					
					</div>
			 
			</div>	
		</div>	
			<h3><strong>Publisher:&nbsp;&nbsp;&nbsp; </strong><i><c:out value="${book.publisher}"></c:out></i></h3>
			
	
		
			<h3>Description:</h3>
			<c:out value="${book.description}"></c:out>
		</div>
			
	
	</div>
	
			
		
			<div class="col-md-12" align="right">
				<button id="buyButton" class="button yellow" value="<c:out value='${book.id}'></c:out>">
					<div class="tittle">Add to cart</div>
					<div class="price"><c:out value="${book.price}" ></c:out><h6 class="BGN"> &nbsp; BGN</h6></div>
				</button>
			</div>
		
	
	<div class="container body-content span=8 offset=2">
		
		
			<input data-toggle="collapse" data-target="#comments" class="btn-primary" onclick="change()"
			type="button" value="Show comments" id="myButton1" ></input>
			<span class="badge"><c:out value="${book.comments.size()}"></c:out></span>								 	
	  
		
		<div id="comments" class="collapse">
		
		<div id="comments1">
		
		</div>
		
		</div>
	
	</div>
	
	<c:if test="${sessionScope.user.isAdmin()}">
	<div  align="right" >
		<form id="bloc2" action="UploadBook">
			<input type="hidden" name="bookId" value="${book.id}">	    
		    <input type="submit" class="btn btn-primary" value="Edit"/>
		</form>
		<form id="bloc2" action="deleteBook" method="POST">
			<input type="hidden" name="bookId" value="${book.id}">	    
		    <input type="submit" class="btn btn-danger" value="Delete"/>
		</form>
	</div>
	</c:if>
</div>

<script src='myscript.js'></script> 
<script src='addToCart.js'></script> 
<script src='rating.js'></script> 
		