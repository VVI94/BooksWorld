$(function() {
	
	
	function loadShopCart(data){
	 	var result = '';
		
		if(data > 0){
			result += "<span id=\"cartBadge\" class=\"badge\">"+data+"</span>"
		}
				
		$("#cartBadge1").html(result);
	}
	

	$('#buyButton').on('click', function(data){
						
		var data = {
				
				bookId : $('#buyButton').val(),
				quantity : $('#buyQuantity').val(),
				forLoad : false
			}
			
		
		$.post('shopCart', {data: JSON.stringify(data)}).then(function(data) {
			
			
			loadShopCart(data);
			
		});
		
	});
	
	function getSize(){
		var size1 ='';		
		var data ={
				
			forLoad : true
		}
		
		$.post('shopCart', {data: JSON.stringify(data)}).then(function(data1) {
			
			loadShopCart(data1);
		
		});				
	}

	getSize();	
	
	
});
