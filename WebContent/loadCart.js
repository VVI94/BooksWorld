$(function() {
		
	loadBooksFromCart();
		
	function loadBooksFromCart(){
		
		$.post('cart').then(function(stocks){
			var stock = '';
			var result = '';
			var totSum = 0;
			for (var index = 0; index < stocks.length; index++) {
				
				stock = stocks[index];

				
				totSum += Number(stock.book.price) * Number(stock.quantity)
				
				result += "<div class=\"col-md-6\" align=\"center\">"
					result += "<header>"
						result += "<strong><h3>"+stock.book.title+"</h3></strong>"
					result += "</header>"
				result += "<fieldset>"	
				result += "<div class=\"col-md-4\" align=\"center\">"
					result+= "<article>"
						
						result += "<a href=\"BookInfo?book="+stock.book.id+"\"> <img height=\"250px\" width=\"100%\" src=\"avatar?photo="+stock.book.photo+"\" ></a>"
								  
						result+= "</article>"			
																		
				 result += "</div>"
					 result += "<div class=\"col-md-1\" align=\"center\">" 					 
					result += "</div>"
						result += "<div class=\"col-md-6\" align=\"left\">" 
						
							
							result += "<div clas=\"close\" align=\"right\">" 
								
							
							result +="<i id = \"close\" name = \""+stock.book.id+"\" class=\"fa fa-times fa-fw fa-2x\" aria-hidden=\"true\">"				
							
							result += "</i></div>"
							result += "<strong>Author: </strong><br>"
							result += "<i>" +stock.book.author.firstName+"</i>";
							result += " ";
							result += "<i>" +stock.book.author.lastName+"</i>";
							result += "<br><br>"
							result += "Category: <br>"
							result += stock.book.category;
							result += "<br><br><br>"
							result += "Price : "	
							result += "<h2 id=\"price\"><strong><i>&nbsp;&nbsp;&nbsp;"+stock.book.price.toFixed(2)+"&nbsp;</i></h2><sub>BGN</sub></strong>"
							result += "<br><br>"
							result += "<div id=\"price\" align=\"left\">"
							result += "<input id=\""+stock.book.id+"\" name=\""+stock.book.price+"\" class=\"quantity\"  type =\"number\" min =\"1\" max=\"99\" value=\""+stock.quantity+"\" onKeyUp=\"if(this.value>99){this.value=\"99\";}else if(this.value<0){this.value=\"0\";}\">"						
							result +="</div>"
								result += "<div align=\"right\">"
									var tot = (Number(stock.book.price) * Number(stock.quantity)).toFixed(2);

								result += "<button class=\"total\"><h5 class=\"d\" id =\"price"+stock.book.id+"\" name=\""+tot+"\" >"+tot+"</h5><sub>&nbsp;BGN</sub></button>"
									result +="</div>"
						result += "</div>"
							
					result += "</fieldset>"		
				result += "</div>"
		
			}
			
			$("#cartBooks").html(result);
			
			printTotalSum(totSum)
			
			removeBookFromCart()
			
			calcolateSum()
					
		});
		
		
	}
	
	
	function calcolateSum(){
		$('.quantity').on('input', function() {
			
			var ids = [];
			var price = $(this).attr('name');
			var quantity = $(this).val();
			if(quantity > 0 && quantity <= 99){
				
						
				var id = "price" + $(this).attr('id')
				printTotal(quantity, price, id)
				var sum = 0;
				var count = 1;
				var prev = 0;
				  $('.d').each(function() {
					  if(typeof($(this).attr('name'))!== 'undefined'){
						 
						  var b = $.inArray($(this).attr('id'), ids)
						
						  
						  if(b>=0){
							  sum-= Number(prev)
						  }
						  ids.push($(this).attr('id'))
						prev =  $(this).attr('name') 
						
				        sum +=Number(($(this).attr('name')));					       
				       
						  
						 
					  }
				  	});
				
				 
				  printTotalSum(sum)				  
				  
			}
		})	
	}

	function printTotal(quantity, price, id){
		var total = (price * quantity).toFixed(2);
		$("#" + id).html("<h5 id=\""+id+"\" class=\"d\" name=\""+total+"\">"+total+"</h5>");
	}	

	function printTotalSum(sum){		
		$("#totalBuy").html("<h3 class=\"p\" id=\"totalBuy\">"+sum.toFixed(2)+"</h3>");
	}
	
	
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
	
	function removeBookFromCart(){
		$('i').on('click', function() {
			if (confirm('Are you sure?')) {
				console.log($(this).attr('name'))
				
				
					bookId = $(this).attr('name')
				
					var self = this;
				
				$.ajax({
					url : 'cart?bookId=' + bookId,
					type : 'DELETE',
					success : function(result) {
						$(self).parent().parent().parent().remove();
						calcolateSum()
						loadBooksFromCart()
						getSize()
					}
					
				});
			}
		});
	}

	getSize();	
	
});
