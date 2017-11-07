<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<head>

<SCRIPT type="text/javascript">
    function ValidateFileUpload() {
        var fuData = document.getElementById('fileChooser');
        var FileUploadPath = fuData.value;

//To check if user upload any file
        if (FileUploadPath == '') {
            alert("Please upload an image");

        } else {
            var Extension = FileUploadPath.substring(
                    FileUploadPath.lastIndexOf('.') + 1).toLowerCase();

//The file uploaded is an image

if (Extension == "gif" || Extension == "png" || Extension == "bmp"
                    || Extension == "jpeg" || Extension == "jpg") {

// To Display
                if (fuData.files && fuData.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function(e) {
                        $('#blah').attr('src', e.target.result);
                    }

                    reader.readAsDataURL(fuData.files[0]);
                }

            } 

//The file upload is NOT an image
else {
                alert("Photo only allows file types of GIF, PNG, JPG, JPEG and BMP. ");

            }
        }
    }
</SCRIPT>


<c:if test="${sessionScope.user == null || !sessionScope.user.isAdmin()}">
	<meta http-equiv="refresh" content="0; url=http://localhost:8080/BooksWorld/error404.html" />
</c:if>
</head>

 <div class="container body-content span=8 offset=2">
 <fieldset>
 	<legend>
         <c:if test="${book == null}">New Book</c:if>
         <c:if test="${book != null}">Edit Book</c:if>
     </legend>	

     <form action = "UploadBook" method = "post"
         											class="form-horizontal" enctype="multipart/form-data">
         									
        											
    	 <div class="row">
     		<div class="well">
     
     
     
    		<div class="col-lg-6" align="center">
     					
			
                
                    
                        

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Title</label>
                            <div class="col-sm-8 ">
                                <input type="text" class="form-control" name ="title" placeholder="Post Title" required="required" 
                                value="<c:if test="${book != null}"><c:out value="${book.title}"></c:out></c:if>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Author</label>
                            <div class="col-sm-8 ">
                                <input type="text" class="form-control" name ="firstName" placeholder="First name" required="required"
                                value="<c:if test="${book != null}"><c:out value="${book.firstName()}"></c:out></c:if>">
                                <input type="text" class="form-control" name ="lastName" placeholder="Last name" required="required"
                                value="<c:if test="${book != null}"><c:out value="${book.lastName()}"></c:out></c:if>">
                            </div>
                        </div>
                     
                         <div class="form-group">
                            <label class="col-sm-4 control-label">Year</label>
                            <div class="col-sm-8 ">
                                <input type="number" class="form-control" name ="year" placeholder="Year" required="required" 
                                min="0" max="2017"
                                onKeyUp="if(this.value>2017){this.value='2017';}else if(this.value<0){this.value='0';}"
                                value="<c:if test="${book != null}"><c:out value="${book.year}"></c:out></c:if>">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Publisher</label>
                            <div class="col-sm-8 ">
                                <input type="text" class="form-control" name ="publisher" placeholder="Publisher" required="required"
                                value="<c:if test="${book != null}"><c:out value="${book.publisher}"></c:out></c:if>">
                            </div>
                        </div>
                        
                         <div class="form-group">
                            <label class="col-sm-4 control-label">Category</label>
                            <div class="col-sm-8 ">
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
                            <div class="col-sm-8 ">
                                <input type="number" step="0.01" class="form-control" name ="price" placeholder="Price" required="required" 
                                min="0" max="999"
                                onKeyUp="if(this.value>999){this.value='999';}else if(this.value<0){this.value='0';}"
                                value="<c:if test="${book != null}"><c:out value="${book.price}"></c:out></c:if>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Description</label>
                            <div class="col-sm-8">
                                <textarea class="form-control" rows="5" name ="description" required="required">
                                <c:if test="${book != null}"><c:out value="${book.description}"></c:out></c:if></textarea>
                            </div>
                        </div>
                        
       
            </div>
            
       
       <div class="col-lg-6" align="center"> 
            <img src="images/static/default-book-cover.png" id="blah" width="400" height="100%">
                        
        </div>
           
                					  <div class="form-group" align="right">
                         					   <div class="col-sm-7 col-sm-offset-4">
				                              <div class="form-group">
				                            <label class="col-sm-4 control-label">Upload image</label>
				                            <div class="col-sm-6">
				                            <c:if test="${book != null}">
				                            	
				                            	<input type="file" name="dataFile" id="fileChooser" onchange="return ValidateFileUpload()" />
				                            </c:if>
				                            <c:if test="${book == null}">
				                                
				                                <input type="file" name="dataFile" id="fileChooser" onchange="return ValidateFileUpload()" required="required"/>
				                            </c:if>
				                            </div>
				                       		 </div>
                       		 
						
                               <a href="javascript:history.go(-1)" class="btn btn-default" > Cancel </a>
                               
                               <c:if test="${book != null}">
                              	 <input type="hidden" name = "bookId" value="${book.id}">
                              	 <input type="hidden" name = "bookPhoto" value="${book.photo}">
                               </c:if>
	
                               <input type="submit" class="btn btn-primary" value ="Submit">

                            </div>
                        </div>
                        
          </div>    
         </div>
      </form>
                
    </fieldset>     
</div>
					
    

