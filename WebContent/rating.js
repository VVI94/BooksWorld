$(function() {

	var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
	
	
	var bookId = getUrlParameter('book');

	loadRaiting();
	loadAvgRating()
	
	function loadAvgRating() {
		
		var result = '';
		$.get('rating?avg=true&bookId=' +bookId).then(function(data){
			
			var avgRating = data;
			
			
			result += "<h3>"+avgRating.toFixed(1)+"</h3>"
			
			console.log("rating")
			
			$('#avgRating').html(result);
		});
	}
	
	function loadRaiting(){
		
		var result = '';
	
		
		$.get('rating?bookId=' +bookId).then(function(data) {
			
			
			if(data <= 0){
				
					result += " <input id=\"star5\" name=\"star\" type=\"radio\" value=\"5\" class=\"radio-btn hide\" />"
					result += "  <label for=\"star5\">☆</label>"
					result += " <input id=\"star4\" name=\"star\" type=\"radio\" value=\"4\" class=\"radio-btn hide\" />"
					result += "  <label for=\"star4\">☆</label>"
					result += "  <input id=\"star3\" name=\"star\" type=\"radio\" value=\"3\" class=\"radio-btn hide\" />"
					result += "  <label for=\"star3\">☆</label>"
					result += " <input id=\"star2\" name=\"star\" type=\"radio\" value=\"2\" class=\"radio-btn hide\" />"
					result += " <label for=\"star2\">☆</label>"
					result += " <input id=\"star1\" name=\"star\" type=\"radio\" value=\"1\" class=\"radio-btn hide\"  />"
					result += " <label for=\"star1\">☆</label>"
					result += "  <div class=\"clear\"></div>"
				
				
			}else{
				
				if(data == 5){
					result += " <input id=\"star5\" name=\"star\" type=\"radio\" value=\"5\" class=\"radio-btn hide\" checked=\"checked\"/>"
				}else{
					result += " <input id=\"star5\" name=\"star\" type=\"radio\" value=\"5\" class=\"radio-btn hide\" />"
				}
				result += "  <label for=\"star5\">☆</label>"
				if(data == 4){
					result += " <input id=\"star4\" name=\"star\" type=\"radio\" value=\"4\" class=\"radio-btn hide\" checked=\"checked\"/>"
				}else{
					result += " <input id=\"star4\" name=\"star\" type=\"radio\" value=\"4\" class=\"radio-btn hide\" />"
				}
				result += "  <label for=\"star4\">☆</label>"
				
				if(data == 3){
					result += " <input id=\"star3\" name=\"star\" type=\"radio\" value=\"3\" class=\"radio-btn hide\" checked=\"checked\"/>"
				}else{
					result += " <input id=\"star3\" name=\"star\" type=\"radio\" value=\"3\" class=\"radio-btn hide\" />"
				}
				result += "  <label for=\"star3\">☆</label>"
				if(data == 2){
					result += " <input id=\"star2\" name=\"star\" type=\"radio\" value=\"2\" class=\"radio-btn hide\" checked=\"checked\"/>"
				}else{
					result += " <input id=\"star2\" name=\"star\" type=\"radio\" value=\"2\" class=\"radio-btn hide\" />"
				}
				result += "  <label for=\"star2\">☆</label>"	
				if(data == 1){
					result += " <input id=\"star1\" name=\"star\" type=\"radio\" value=\"1\" class=\"radio-btn hide\" checked=\"checked\"/>"
				}else{
					result += " <input id=\"star1\" name=\"star\" type=\"radio\" value=\"1\" class=\"radio-btn hide\" />"
				}
				result += "  <label for=\"star1\">☆</label>"	
				result += "  <div class=\"clear\"></div>"				
			}
					
			$('.rating').html(result);
			
			$('.radio-btn').on('click', function() {
				console.log($(this).val())
				var data = {
						
					rating: $(this).val(),
					bookId: bookId
				}
				
				$.post('rating', {data: JSON.stringify(data)}).then(function() {
					
					loadRaiting();
					loadAvgRating()
				});
				
			})
		});
				
	}
	
	
});
