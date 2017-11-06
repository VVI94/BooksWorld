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
                        <a href="./" class="btn btn-primary" ><i class="fa fa-home fa-fw fa-3x" aria-hidden="true"></i>&nbsp; Home</a>
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
							  	<li><a id="userOption" href="#"><i class="fa fa-eye fa-fw"></i> Profile</a></li>
							  	<li><a id="userOption" href="./favouriteBooks"><i class="fa fa-heart fa-fw"></i> Favourites</a></li>
							    <li><a id="userOption" href="./UploadBook"><i class="fa fa-pencil fa-fw"></i> Add new book</a></li>
			<!--				<li><a id="userOption" href="#"><i class="fa fa-trash-o fa-fw"></i> Delete</a></li> -->
			<!--			    <li><a id="userOption" href="#"><i class="fa fa-ban fa-fw"></i> Ban</a></li> 		-->
							    <li class="divider"></li>
							    <li><a id="userOption" href="./logout"><i class="fa fa-key fa-fw"></i> Logout</a></li>
							  </ul>
                                                      
                            </li>                                              
                            <li><a href="#" >
 								<i class="fa fa-shopping-cart fa-2x" aria-hidden="true"></i>
 								<sub><span id="cartBadge" class="badge">3</span></sub></a> </li> 
 								                             
                            </c:if>  
                            <c:if test="${sessionScope.user == null}">  
                            <li><a href="#"><i class="fa fa-user fa-fw"></i>User</a></li>                   
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
       