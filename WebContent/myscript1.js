$(function() {

	$('#search').on('keyup', function() {
		var name = this.value; // $(this).val()


		$.get('http://localhost:8080/BooksWorld/search?name=' + name, function(data){
			var result = '';
			var image = '';
			
			for (var index = 0; index < data.length; index++) {
				var book = data[index];
				result += "<option>" + book.title + " (Author: " + book.author.firstName + " "+ book.author.lastName+")" + "</option>";
				
				image += "<img src =\"avatar?photo=" +book.photo + "\" height=\"80px\" width=\"60\" />";
				
			}
			$('#searchResult').html(image);
			$('#searchResult1').html(result);
		});
	});
});
