<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    

 <script type="text/javascript">
 function refreshSearch() {
	var name = $("#search").val();
	$.get("http://localhost:8080/BooksWorld/search?name=" + name, function(data) {
		$("#searchResult").empty();
		
		for(var index in data){			
			var book = data[index]
			var title = document.createElement("p");
			title.style = "display:inline"
			title.innerHTML = book.title;
			$("#searchResult").append(title)
			
			var photo = document.createElement("img");
			photo.style = "display:inline"
			photo.src ="http://localhost:8080/BooksWorld/avatar?book=" + book.id;
			photo.width = 100;
			photo.height = 100;
			$("#searchResult").append(photo);			
		}

	});
}
 
 </script>
 
        <header>
            <div class="navbar navbar-default navbar-fixed-top text-uppercase">
                <div class="container">
                    <div class="navbar-header" align="left">
                        <a href="./" ><img align="bottom" alt="Book World" src="images/static/Books-1-icon.png" width="80" height="50"></a>
                    </div>
                    
                    <div class="navbar-brand">                  
                        <div class="search">
                        	<span class="fa fa-search"></span>
                       		<input type="search" id="search" oninput="refreshSearch()"  placeholder="Search">
						</div> 				
                    </div>

                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#">Welcome(User)</a></li>                            
                            <li><a href="./UploadBook" class="btn">Add new book</a></li>                         
                            <li><a href="#">Login</a></li>
                        </ul>
                    </div>
                </div>
 
            </div>
            	<br>
	<br>
	<br>
	<br>
	<div  id ="searchResult"></div>
        </header>

        
       