$(function() {
	

	function loadFavouriteBooks(books){

		var result = '';
		var book = '';
				
			$.get('favBooks').then(function(data){
				
				if(books.length > 0){
				
					for (var index = 0; index < books.length; index++) {	
						book = books[index]
						if(book.title === 'skip' && books.length === 1){
							$("#favBooks").html("nqma tiq knigi");
							return;
						}else{	
						
							if(book.title !== 'skip'){	
							result += "<div class=\"col-md-2\" align=\"center\">"
								result+= "<article>"
									result += "<header>"
									result += book.title
									result += "</header>"
									result += "<a href=\"BookInfo?book="+book.id+"\"> <img height=\"250px\" width=\"100%\" src=\"avatar?photo="+book.photo+"\" ></a>"
											  
									result+= "</article>"			
																					
							 result += "</div>"
							}
						}	
					}
				}else{	
				
				for (var index = 0; index < data.length; index++) {	
					book = data[index]
								
						result += "<div class=\"col-md-2\" align=\"center\">"
							result+= "<article>"
								result += "<header>"
								result += book.title
								result += "</header>"
								result += "<a href=\"BookInfo?book="+book.id+"\"> <img height=\"250px\" width=\"100%\" src=\"avatar?photo="+book.photo+"\" ></a>"
										  
								result+= "</article>"			
																				
						 result += "</div>"
								
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
