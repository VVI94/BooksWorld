<%@ page language="java" contentType="text/html; charset = UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" rel="stylesheet" href= "css/style1.css"/> 
<link type="text/css" rel="stylesheet" href= "css/raiting.css"/>  

<script type="text/javascript">

function change()
{
    var elem = document.getElementById("myButton1");
    if (elem.value=="Hide comments") elem.value = "Show comments";
    else elem.value = "Hide comments";
}
</script>


 <div class="container body-content span=8 offset=2">


	<div class="row">
		
		<div style="float: left;" class="col-md-6" >
		
			<article>
				<div align="center">
					<header>
						<c:out value="${book.title}"></c:out>
					</header>	
							
					<img height="650px" width="500px" src="avatar?book=<c:out value="${book.id}"></c:out>">
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
	<c:if test="${book.comments.size() >0}">		
	
		<input data-toggle="collapse" data-target="#comments" class="btn-primary" onclick="change()"
		type="button" value="Show comments" id="myButton1" ></input>
		<span class="badge"><c:out value="${book.comments.size()}"></c:out></span>								 	
  
	</c:if>
	<div id="comments" class="collapse">
		<br>
		<br>
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
			                        <a href="likes?type=like&commentId=<c:out value='${comment.id}'></c:out>&book=<c:out value ='${book.id}'></c:out>">
			                            <img alt="like" width="50 px" height="40 px" 
								        src="images/static/like.jpg">
								        <c:if test="${comment.likes>0}">
								        <sub><span class="badge"><c:out value='${comment.likes}'></c:out></span></sub>
								        </c:if>
			                        </a>
			                        <a href="likes?type=<c:out value='dislike'></c:out>&commentId=<c:out value='${comment.id}'></c:out>&book=<c:out value ='${book.id}'></c:out>">
			                            <img alt="dislike" width="50 px" height="40 px" 
			                            src="images/static/dislike.jpg">
								        <c:if test="${comment.dislikes>0}">
								        <sub><span class="badge"><c:out value='${comment.dislikes}'></c:out></span></sub>
			                       		</c:if>
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
								                        <a href="likes?type=like&commentId=<c:out value='${reply.id}'></c:out>&book=<c:out value='${book.id}'></c:out>">							                        	
								                            <img alt="like" width="30 px" height="20 px" 
								                            src="images/static/like.jpg">
								                            <c:if test="${reply.likes>0}">
								                            <span><sub><c:out value='${reply.likes}'></c:out></sub></span>
								                            </c:if>
								                        </a>
								                        <a href="likes?type=dislike&commentId=<c:out value='${reply.id}'></c:out>&book=<c:out value='${book.id}'></c:out>">
								                            <img alt="dislike" width="30 px" height="20 px" 							                            
								                            src="images/static/dislike.jpg">
								                            <c:if test="${reply.dislikes>0}">
								                            <span><sub><c:out value='${reply.dislikes}'></c:out></sub></span>
								                            </c:if>
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

				
		</c:forEach>

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
	</div>
</div>
<c:if test="${book.comments.size() ==0}">
	<br>
	<br>
	<br>
	 <div class="container body-content">
		<form class="form-horizontal" action="AddComment" method="POST">
			<div class="row">
				<button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#write1">
				Write first comment
				</button>
				<div id="write1" class="collapse" >
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
</c:if>		
	
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

		