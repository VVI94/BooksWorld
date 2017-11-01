<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>
  <div class="container body-content span=8 offset=2">
			<div class="well">
                <form action = "Register" method = "post"
         											class="form-horizontal" enctype="multipart/form-data">
                    <fieldset>
                        <legend>
                        <c:if test="${user == null}">New User</c:if>
                         <c:if test="${user != null}">Edit User</c:if>
                        </legend>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Username</label>
                            <div class="col-sm-4 ">
                                <input type="text" class="form-control" name ="username" placeholder="Post Username" required="required" 
                                value="<c:if test="${user != null}"><c:out value="${user.username}"></c:out></c:if>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Password</label>
                            <div class="col-sm-4 ">
                                <input type="password" class="form-control" name ="password" placeholder="Password" required="required"
                                value="<c:if test="${user != null}"><c:out value="${user.password()}"></c:out></c:if>">
                            </div>
                        </div>
                     
                         <div class="form-group">
                            <label class="col-sm-4 control-label">Email</label>
                            <div class="col-sm-4 ">
                                <input type="email" class="form-control" name ="email" placeholder="Email" required="required"
                                value="<c:if test="${user != null}"><c:out value="${user.email}"></c:out></c:if>">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-4 control-label">First name</label>
                            <div class="col-sm-4 ">
                                <input type="text" class="form-control" name ="firstname" placeholder="First name" required="required"
                                value="<c:if test="${user != null}"><c:out value="${user.firstname}"></c:out></c:if>">
                            </div>
                        </div>
                        
                         <div class="form-group">
                            <label class="col-sm-4 control-label">Last name</label>
                            <div class="col-sm-4 ">
                                <input type="text" class="form-control" name ="lastname" placeholder="Last name" required="required"
                                value="<c:if test="${user != null}"><c:out value="${user.lastname}"></c:out></c:if>">
                            </div>
                        </div>
                        
                         <div class="form-group">
                            <label class="col-sm-4 control-label">Address</label>
                            <div class="col-sm-4 ">
                                <input type="text" class="form-control" name ="address" placeholder="Address" required="required"
                                value="<c:if test="${user != null}"><c:out value="${user.address}"></c:out></c:if>">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-4 control-label">Telephone</label>
                            <div class="col-sm-4 ">
                                <input type="text" class="form-control" name ="telephone" placeholder="Telephone" required="required"
                                value="<c:if test="${user != null}"><c:out value="${user.telephone}"></c:out></c:if>">
                            </div>
                        </div>
                        
                        
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Upload avatar</label>
                            <div class="col-sm-6">
                            <c:if test="${user != null}">
                            	<input type="file" name ="picture">
                            </c:if>
                            <c:if test="${user == null}">
                                <input type="file" name ="picture" required="required">
                            </c:if>
                            </div>
                        </div>
                      

                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-4">

                               <a href="javascript:history.go(-1)" class="btn btn-default" > Cancel </a>
                               
                               <c:if test="${user != null}">
                              	 <input type="hidden" name = "userID" value="${user.id}">
                              	 <input type="hidden" name = "userAvatar" value="${user.userAvatar}">
                               </c:if>
	
                               <input type="submit" class="btn btn-primary" value ="Submit">

                            </div>
                        </div>
                                          
                    </fieldset>
               </form>
            
                
        </div>
	</div>
</body>
</html>