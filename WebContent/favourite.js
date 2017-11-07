$(function() {
	

	function loadFavouriteBooks(books){

		var result = '';
		var book = '';
				
			$.get('favBooks').then(function(data){
				
				if(books.length > 0){
					
					console.log("tuka li sym")
					for (var index = 0; index < books.length; index++) {	
						book = books[index]
						
						if(book.title === 'skip' && books.length === 1){
							$("#favBooks").html("nqma tiq knigi");
							return;
						}else{	
						
							if(book.title !== 'skip'){	
								
								
								result += "<div id =\"pic\" class=\"col-md-4\" align=\"center\">"
									
									result += "<div class=\"col-md-6\" align=\"center\">"
										result += "	<article>"	
										result += "<a href=\"BookInfo?book="+book.id+"\"><img height=\"250px\" width=\"100%\" src=\"avatar?photo="+book.photo+"\"></a>"
														
										result += "	</article>"
									result += "</div>"
									
												result += "<div class=\"col-md-6\" align=\"left\">"
												result += "	<header>"
													result += "<h4><strong>"+book.title+"</strong></h4>"
													result += "	</header>"
									
												result += "	<h5>By:</h5>"
												result += "<h5><i>"+ book.author.firstName + " " +book.author.lastName+"</i></h5>"
												result += "<h6><strong>Category:&nbsp;&nbsp;&nbsp; </strong><i>"+book.category+"</i></h6>"												 
												result += "<h6><strong>Year:&nbsp;&nbsp;&nbsp; </strong><i>"+book.year+"</i></h6>"
								 
											result += " <div class=\"col-md-6\" align=\"left\">"						
											result += "</div>"
											result += " <div class=\"col-md-6\" align=\"right\">"
												 
												result += "	<span class=\"tags\">"
												result += "	<span class=\"price-tag\"><a>"+book.price+"</a></span> "   
												result += "	</span> "
											result += "</div>"	
										result +="</div>"
									result += "</div>"								
								
					
							}
						}	
					}
				}else{	
				
				for (var index = 0; index < data.length; index++) {	
					
					book = data[index];
					
					
					result += "<div id =\"pic\" class=\"col-md-4\" align=\"center\">"
						
						result += "<div class=\"col-md-6\" align=\"center\">"
							result += "	<article>"	
							result += "<a href=\"BookInfo?book="+book.id+"\"><img height=\"250px\" width=\"100%\" src=\"avatar?photo="+book.photo+"\"></a>"
											
							result += "	</article>"
						result += "</div>"
						
									result += "<div class=\"col-md-6\" align=\"left\">"
									result += "	<header>"
										result += "	<h4><strong>"+book.title+"</strong></h4>"
							result += "	</header>"
						
									result += "	<h5>By:</h5>"
									result += "<h5><i>"+book.author.firstName + " " +book.author.lastName+"</i></h5>"
									result += "<h6><strong>Category:&nbsp;&nbsp;&nbsp; </strong><i>"+book.category+"</i></h6>"												 
									result += " <h6><strong>Year:&nbsp;&nbsp;&nbsp; </strong><i>"+book.year+"</i></h6>"
					 
								result += " <div class=\"col-md-6\" align=\"left\">"						
								result += "</div>"
								result += " <div class=\"col-md-6\" align=\"right\">"
									 
									result += "	<span class=\"tags\">"
									result += "	<span class=\"price-tag\"><a>"+book.price+"</a></span> "   
									result += "	</span> "
								result += "</div>	"	
							result +="</div>"
						result += "</div>"
					
					
//					book = data[index]
//								
//						result += "<div class=\"col-md-2\" align=\"center\">"
//							result+= "<article>"
//								result += "<header>"
//								result += book.title
//								result += "</header>"
//								result += "<a href=\"BookInfo?book="+book.id+"\"> <img height=\"250px\" width=\"100%\" src=\"avatar?photo="+book.photo+"\" ></a>"
//										  
//								result+= "</article>"			
//																				
//						 result += "</div>"
								
					}
						
				}
				$("#favBooks").html(result);
				
			});
			
			
	
		
	}
	

	$('#try').off().on('click', function() {
		
		
			
		var categories = [];
		
		$.get('getCategory').then(function(data) {
			
			for (var index = 0; index < data.length; index++) {	
				
				var category = data[index];

				if($('#' + category).is(':checked')){
					categories.push(category)
				}
			}
			

			var data = {
				categories : categories,
				author : $('#author').val(),
				minPrice :$('#minPrice').val(),
				maxPrice :$('#maxPrice').val(), 
				minYear :$('#minYear').val(), 
				maxYear :$('#maxYear').val(), 
			}
			
					
			$.post('favBooks', {data: JSON.stringify(data)}).then(function(books) {
				
				loadFavouriteBooks(books);
				
			});
			
		})
		

		
			
	});
	var books = '';
	loadFavouriteBooks(books);

});
