<%@ page language="java" contentType="text/html; charset = UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href= "css/style1.css"/> 
<link type="text/css" rel="stylesheet" href= "css/raiting.css"/>  
	<script src='myscript.js' type="text/javascript">

	</script>

<style type="text/css">

.like{
    cursor: pointer;
}
.likeReply{
    cursor: pointer;
}

#commentBack {
    background-image:url('./images/static/comment.jpg');
}
#commentDescr {
    background-image:url('./images/static/comment.jpg');
}

</style>

 <div class="container body-content span=8 offset=2">


	<div class="row">
		
		<div style="float: left;" class="col-md-6" >
		
			<article>
				<div align="center">
					<header>
						<c:out value="${book.title}"></c:out>
					</header>	
							
					<img height="650px" width="100%" src="avatar?photo=<c:out value="${book.photo}"></c:out>">
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
			<h3>Description:</h3>
			<c:out value="${book.description}"></c:out>
		</div>
			
	
	</div>
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

<script src='myscript.js'></script> 
		