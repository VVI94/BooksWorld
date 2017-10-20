<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

 <div class="container body-content span=8 offset=2">
 


</div>

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

<div class="container body-content">
	<c:if test="${book.comments.size() >0}">
		<h2>Comments:</h2>
	</c:if>
	
	<c:forEach items="${book.comments}" var="comment">
		 <article>
		 	<div class="form-group col-md-12">
	            <div class="form-group col-md-6">
	                 <ul class="nav nav-pills">
	                       <li><c:out value="${comment.user.username}"></c:out></li>
	                     
	                  </ul>
	                    <div class="well">
	                       <c:out value="${comment.content}"></c:out>
	                   </div>
	                   <div align="right"><c:out value="${comment.date}"></c:out></div>
	              </div>
	          </div>
	      </article>
	      
	      
	      <c:if test="${comment.replyComments.size() > 0}">
				 <button type="button" class="btn btn-primary btn-xs"
					 data-toggle="collapse"
					data-target="#<c:out value='${comment.id}'></c:out>">Show replies 
					<span class="badge"><c:out value="${comment.replyComments.size()}"></c:out></span>					
					
				</button>     		
      		
      		
      		</c:if>
      		
      				 <c:if test="${comment.replyComments.size() == 0}">
      					<div class="col-md-12">
									
							<button type="button" class="btn btn-primary btn-xs" data-toggle="collapse"
							data-target="#<c:out value='id${comment.id}'></c:out>">Reply
							</button>
							<br/>
							<br/>
							<div id="<c:out value='id${comment.id}'></c:out>" class="collapse">
								<form class="form-horizontal"
								action="AddComment"
								method="POST">
									<div class="col-lg-6">
										<textarea class="form-control " rows="3" name="content"></textarea>
									</div>
									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-4">
											<a class="btn btn-default" href="BookInfo?book=<c:out value="${book.id}"></c:out>">Cancel</a>
											<input type="hidden" name="book" value="${book.id}">
											<input type="hidden" name="comment" value="${comment.id}">
											<input type="submit" class="btn btn-success" value="Send"/>
										</div>
									</div>
								</form>
							</div>
						</div>
      		
      				</c:if>
      		
		    <div id="<c:out value='${comment.id}'></c:out>" class="collapse">
			    <div class="col-md-12">
			        <div class="container body-content col-md-4">
				      	<c:forEach items="${comment.replyComments}" var="reply">
				      	
							 <div class="col-md-12">
								<c:out value="${reply.user.username}:"></c:out>					                                           						                                            
								<c:out value="${reply.content}"></c:out>
								
							                                            
							 </div>	
	                                       	      		
						</c:forEach>
						
						
					</div>
						
						<div class="col-md-12">
									
							<button type="button" class="btn btn-primary btn-xs" data-toggle="collapse"
							data-target="#<c:out value='id${comment.id}'></c:out>">Reply
							</button>
							<br/>
							<br/>
							<div id="<c:out value='id${comment.id}'></c:out>" class="collapse">
								<form class="form-horizontal"
								action="AddComment"
								method="POST">
									<div class="col-lg-12">
										<textarea class="form-control " rows="3" name="content"></textarea>
									</div>
									<div class="form-group">
										<div class="col-sm-12 col-sm-offset-4">
											<a class="btn btn-default" href="BookInfo?book=<c:out value="${book.id}"></c:out>">Cancel</a>
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

<br>
<br>
<br>

	    <div class="container body-content">
	        <form class="form-horizontal" action="AddComment" method="POST">
	            <div class="row">
	                <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#write">Write a
	                    comment
	                </button>
	                <div id="write" class="collapse" >
	                    <br/>
	                    <div class="col-lg-6">
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
