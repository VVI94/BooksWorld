<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

     <div class="container body-content span=8 offset=2">
			<div class="well">
                <form action = "UploadBook" method = "post"
         											class="form-horizontal" enctype="multipart/form-data">
                    <fieldset>
                        <legend>
                        <c:if test="${book == null}">New Book</c:if>
                         <c:if test="${book != null}">Edit Book</c:if>
                        </legend>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Title</label>
                            <div class="col-sm-4 ">
                                <input type="text" class="form-control" name ="title" placeholder="Post Title" required="required" 
                                value="<c:if test="${book != null}"><c:out value="${book.title}"></c:out></c:if>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Author</label>
                            <div class="col-sm-4 ">
                                <input type="text" class="form-control" name ="firstName" placeholder="First name" required="required"
                                value="<c:if test="${book != null}"><c:out value="${book.firstName()}"></c:out></c:if>">
                                <input type="text" class="form-control" name ="lastName" placeholder="Last name" required="required"
                                value="<c:if test="${book != null}"><c:out value="${book.lastName()}"></c:out></c:if>">
                            </div>
                        </div>
                     
                         <div class="form-group">
                            <label class="col-sm-4 control-label">Year</label>
                            <div class="col-sm-4 ">
                                <input type="number" class="form-control" name ="year" placeholder="Year" required="required"
                                value="<c:if test="${book != null}"><c:out value="${book.year}"></c:out></c:if>">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Publisher</label>
                            <div class="col-sm-4 ">
                                <input type="text" class="form-control" name ="publisher" placeholder="Publisher" required="required"
                                value="<c:if test="${book != null}"><c:out value="${book.publisher}"></c:out></c:if>">
                            </div>
                        </div>
                        
                         <div class="form-group">
                            <label class="col-sm-4 control-label">Category</label>
                            <div class="col-sm-4 ">
                                <input type="text" class="form-control" list="categories" name ="category" placeholder="Category" required="required"
                                value="<c:if test="${book != null}"><c:out value="${book.category}"></c:out></c:if>">
                                	<datalist id="categories">
										<c:forEach items="${categories.keySet()}" var="category">
  											<option><c:out value="${category}"></c:out></option>
										</c:forEach>
									</datalist>
                            </div>
                        </div>
                        
                         <div class="form-group">
                            <label class="col-sm-4 control-label">Price</label>
                            <div class="col-sm-4 ">
                                <input type="number" step="0.01" class="form-control" name ="price" placeholder="Price" required="required"
                                value="<c:if test="${book != null}"><c:out value="${book.price}"></c:out></c:if>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Description</label>
                            <div class="col-sm-6">
                                <textarea class="form-control" rows="5" name ="description" required="required">
                                <c:if test="${book != null}"><c:out value="${book.description}"></c:out></c:if></textarea>
                            </div>
                        </div>
                        
                        
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Upload image</label>
                            <div class="col-sm-6">
                            <c:if test="${book != null}">
                            	<input type="file" name ="image">
                            </c:if>
                            <c:if test="${book == null}">
                                <input type="file" name ="image" required="required">
                            </c:if>
                            </div>
                        </div>
                      

                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-4">

                               <a href="javascript:history.go(-1)" class="btn btn-default" > Cancel </a>
                               
                               <c:if test="${book != null}">
                              	 <input type="hidden" name = "bookId" value="${book.id}">
                              	 <input type="hidden" name = "bookPhoto" value="${book.photo}">
                               </c:if>
	
                               <input type="submit" class="btn btn-primary" value ="Submit">

                            </div>
                        </div>
                                                                  
                    </fieldset>
               </form>
            
                
        </div>
	</div>
					
    

