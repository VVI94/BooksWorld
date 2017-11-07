<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    

<style type="text/css">

#cartBadge{
font-style: italic;
color: blue;
background-color: #FBDC62;
}

#userDrop{
display: inline;
}

#userOption{
text-transform: none;
}

</style>

<script type="text/javascript">
$(document).ready(function() {
	  $('input[name=chooseOption]').on('input', function() {
	    var userText = $(this).val();
	    var selectedOption = $('option[value="'+$(this).val()+'"]');
		
	    $("#searchResult1").find("option").each(function() {
	      if ($(this).val() == userText) {
	    	  window.location.href = "BookInfo?book="+selectedOption.attr('id')+"";
	      }
	    })
	  })
	});

</script>


        <header>
            <div class="navbar navbar-default navbar-fixed-top text-uppercase">
               
                    <div class="navbar-header">
                        <a href="./" class="btn btn-primary" ><b class="fa fa-home fa-fw fa-3x" aria-hidden="true"></b>&nbsp; Home</a>
                    </div>
           
                     <div class="container">
                    <div class="navbar-brand">                  
                        <div class="search">
                        	<span class="fa fa-search"></span>
                       		<input type="search" id="search" list='searchResult1'  placeholder="Search" name="chooseOption">
                       		<datalist id="searchResult1">
                       	
							</datalist>

						</div> 				
                    </div>

                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav navbar-right">                                                                                   
                            <c:if test="${sessionScope.user != null}"> 
                            <li>                           
                            <a data-toggle="dropdown"  href="#"><i class="fa fa-user fa-fw"></i><c:out value="${sessionScope.user.username}"></c:out></a>
                           
							  <ul class="dropdown-menu">
							  	<li><a id="userOption" href="#"><b class="fa fa-eye fa-fw"></b> Profile</a></li>
							  	<li><a id="userOption" href="./favouriteBooks"><b class="fa fa-heart fa-fw"></b> Favourites</a></li>
							  	<c:if test="${sessionScope.user.isAdmin()}">
							  	<li class="divider"></li>
							    <li><a id="userOption" href="./UploadBook"><b class="fa fa-pencil fa-fw"></b> Add new book</a></li>
								<li><a id="userOption" href="#"><b class="fa fa-trash-o fa-fw"></b> Delete</a></li> 
							    <li><a id="userOption" href="#"><b class="fa fa-ban fa-fw"></b> Ban</a></li> 
							    </c:if>
							    <li class="divider"></li>
							    <li><a id="userOption" href="./logout"><b class="fa fa-key fa-fw"></b> Logout</a></li>
							  </ul>
                                                      
                            </li>                                              
                            <li><a href="./cart" >
 								<b class="fa fa-shopping-cart fa-2x" aria-hidden="true"></b>
 							<!--	<sub><span id="cartBadge" class="badge">3</span></sub></a> -->
 								<sub><span id="cartBadge1"></span></sub></a>
 								</li> 
 								                             
                            </c:if>  
                            <c:if test="${sessionScope.user == null}">  
                            <li><a href="#"><b class="fa fa-user fa-fw"></b>User</a></li>                   
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
        <div class="row" id ="searchResult">
</div> 
 
 	<script src='myscript1.js'></script>
 	<script src='addToCart.js'></script>       
       