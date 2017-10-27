<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href= "css/style1.css"/> 

 <div class="container body-content span=8 offset=2">

	<div class="row">
		
		<div style="float: left;" class="col-md-6" align="center">
			<article>
				<header>
				<c:out value="${book.title}"></c:out>
				</header>					
				<img height="650px" width="500px" src="avatar?book=<c:out value="${book.id}"></c:out>">
							
			</article>
		</div>
				
		<div style="float: right;" class="col-md-6" align="left">
			<h3>Description:</h3>
			<c:out value="${book.description}"></c:out>
		</div>
			
	
	</div>
</div>

<div class="container body-content span=8 offset=2">
	<c:if test="${book.comments.size() >0}">
		<h2>Comments:</h2>
	</c:if>
	
	<c:forEach items="${book.comments}" var="comment">

		 <div class="container">
		    <div class="row">
		        <div class="col-sm-8">
		            <div class="panel panel-white post panel-shadow">
		                <div class="post-heading">
		                    <div class="pull-left image">
		                        <img src="http://bootdey.com/img/Content/user_1.jpg" class="img-circle avatar" alt="user profile image">
		                    </div>
		                    <div class="pull-left meta">
		                        <div class="title h5">
		                            <a href="#"><b><c:out value="${comment.user.username}"></c:out></b></a>
		                            made a post.
		                        </div>
		                        <h6 class="text-muted time"><c:out value="${comment.date}"></c:out></h6>
		                    </div>
		                </div> 
		                <div class="post-description"> 
		                    <p><c:out value="${comment.content}"></c:out></p>
		
							<c:if test="${comment.replyComments.size() > 0}">
			         
								<div id ="bloc1" class="btn btn-primary btn-xs" data-toggle="collapse"
									data-target="#<c:out value='${comment.id}'></c:out>">Show replies 
									<span class="badge"><c:out value="${comment.replyComments.size()}"></c:out></span>							 	
								</div>       		
							</c:if>
							<c:if test="${comment.replyComments.size() == 0}">
										
								<div id ="bloc3" class="btn btn-primary btn-xs" data-toggle="collapse"
										data-target="#<c:out value='id${comment.id}'></c:out>">Reply							
								</div>
		      		
		      				</c:if>
						    <div id ="bloc2" class="stats" align="right">   		
		                        <a href="#">
		                            <img alt="like" width="50 px" height="40 px" src="images/static/like.jpg"><span class="badge">2</span>
		                        </a>
		                        <a href="#">
		                            <img alt="dislike" width="50 px" height="40 px" src="images/static/dislike.jpg"><span class="badge">12</span>
		                        </a>
		                    </div>                    
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
	      	            	     				 
										
			<div id="<c:out value='id${comment.id}'></c:out>" class="collapse">
				<form class="form-horizontal" action="AddComment" method="POST">
					<div class="container">	
					<div class="row">
					<div class="col-lg-12">
						<textarea class="form-control" rows="3" cols="150" name="content"></textarea>
					</div>	
					</div>	
					</div>
					<div class="form-group">
						<div class="col-sm-10 col-sm-offset-4">
							<a class="btn btn-default" href="BookInfo?book=<c:out value="${book.id}"></c:out>">Cancel</a>
							<input type="hidden" name="book" value="${book.id}">
							<input type="hidden" name="comment" value="${comment.id}">
							<input type="submit" class="btn btn-success" value="Send"/>
						</div>
					</div>
				</form>
			</div>
		
      		
      			
      		
		<div id="<c:out value='${comment.id}'></c:out>" class="collapse">
			<div class="col-md-12">
				<div class="container body-content col-md-4">
					<c:forEach items="${comment.replyComments}" var="reply">				      	
						<div class="col-md-12">
							<div class="container">						
								<div class="row">
									<div class="col-sm-1">
										<div class="thumbnail">
											<img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
										</div>
									</div>
								
									<div class="col-sm-6">
										<div class="panel panel-default">
											<div class="panel-heading">
												<strong><c:out value="${reply.user.username}"></c:out>	</strong>
												 <span class="text-muted">commented on <c:out value="${reply.date}"></c:out></span>
											</div>
											<div class="panel-body">
												<c:out value="${reply.content}"></c:out>
												<div  class="stats" align="right">   		
							                        <a href="#">
							                            <img alt="like" width="30 px" height="20 px" src="images/static/like.jpg"><span>2</span>
							                        </a>
							                        <a href="#">
							                            <img alt="dislike" width="30 px" height="20 px" src="images/static/dislike.jpg"><span>12</span>
							                        </a>
							                    </div> 												
											</div>
										</div>

									</div>								
								</div>
							
							</div>                                        
						</div>	                                       	      		
					</c:forEach>						
				</div>
					
				<br>
						
				<div class="col-md-12">									
					<button type="button" class="btn btn-primary btn-xs" data-toggle="collapse"
							data-target="#<c:out value='repId${comment.id}'></c:out>">Reply
					</button>
					<br/>
					<br/>
					<div id="<c:out value='repId${comment.id}'></c:out>" class="collapse">
						<form class="form-horizontal" action="AddComment" method="POST">
							<div class="col-lg-12">
								<textarea class="form-control " rows="3" name="content"></textarea>
							</div>
							<div class="form-group">
								<div class="col-sm-12 col-sm-offset-4">
									<a class="btn btn-default" href="BookInfo?book=
										<c:out value="${book.id}"></c:out>">Cancel
									</a>
									<input type="hidden" name="book" value="${book.id}">
									<input type="hidden" name="comment" value="${comment.id}">
									<input type="submit" class="btn btn-success" value="Send"/>
								</div>
							</div>
						</form>
					</div>
				</div>
			
			</div>	
		</div>
			
			
		<br>
		<br>
		<br>
			
	</c:forEach>
</div>

 <div class="container body-content">
	<form class="form-horizontal" action="AddComment" method="POST">
		<div class="row">
			<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#write">
			Write a comment
			</button>
			<div id="write" class="collapse" >
			<br/>
				<div class="col-lg-12">
					<textarea class="form-control " rows="3" name="content"></textarea>
				</div>
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-4">
						<input type="hidden" name="book" value="${book.id}">
						<a class="btn btn-default" href="BookInfo?book=<c:out value="${book.id}"></c:out>">Cancel</a>
						<input type="submit" class="btn btn-success" value="Send"/>
					</div>
				</div>
			 </div>
		</div>
	</form>
</div>
		
		
<div class="container body-content" align="right" >
	<form action="deleteBook" method="POST">
		<input type="hidden" name="bookId" value="${book.id}">	    
	    <input type="submit" class="btn btn-danger" value="Delete"/>
	</form>
</div>
		