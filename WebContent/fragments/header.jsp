<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    

        <header>
            <div class="navbar navbar-default navbar-fixed-top text-uppercase">
               
                    <div class="navbar-header">
                        <a href="./" ><img align="bottom" alt="Book World" src="./images/static/B.png" width="60" height="100%"></a>
                    </div>
           
                     <div class="container">
                    <div class="navbar-brand">                  
                        <div class="search">
                        	<span class="fa fa-search"></span>
                       		<input type="search" id="search" list='searchResult1'  placeholder="Search">
                       		<datalist id="searchResult1">
                       	
							</datalist>

						</div> 				
                    </div>

                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav navbar-right">                                                                                   
                            <c:if test="${sessionScope.user != null}"> 
                            <li><a href="#">Welcome ( <c:out value="${sessionScope.user.username}"></c:out> )</a></li> 
                            <li><a href="./UploadBook" class="btn">Add new book</a></li>                    
                            <li><a href="./logout">Logout</a></li>
                            </c:if>  
                            <c:if test="${sessionScope.user == null}">  
                            <li><a href="#">Welcome(User)</a></li>                     
                            <li><a href="./login">Login</a></li>
                            </c:if>  
                        </ul>
                    </div>
                </div>
 
            </div>
            	<br>
	<br>
	<br>
	<br>
		
        </header>
 
 	<script src='myscript1.js'></script>       
       