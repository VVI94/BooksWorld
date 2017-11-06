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
	
// function getSessionUser(){
// $.get('getSession').then(function(data) {
// var user = data
// console.log(user);
// return user.id;
// });
// }
	
	function loadComments(bookId) {
		$.get('getComments?bookId=' + bookId).then( function(data) {
			var result ='';
					
			var comment ='';
			
			for (var index = 0; index < data.length; index++) {
				comment = data[index];			
				result += "<div class=\"container\">"
					result += "<div class=\"row\">"
						result += "<div class=\"col-sm-8\">"
							result += "<div class=\"panel panel-white post panel-shadow\">"
								
								result += "<div class=\"post-heading\" id=\"commentBack\">"
									result += "<div class=\"pull-left image\">"
										result += "<img src=\"avatar?photo="+comment.user.userAvatar+"\" class=\"img-circle avatar\" alt=\"user profile image\" >"
									result += "</div>"		
									result += "<div class=\"pull-left meta\">"
										result += "<div class=\"title h5\">"
											result += "<a href=\"#\"><b>"+comment.user.username+"</b></a> &nbsp; made a post."
										result += "</div>"	
										result += "<h6 class=\"text-muted time\">"+comment.date+"</h6>"																				
									result += "</div>"									
								result += "</div>"
								
								result += "<div class=\"post-description\" id=\"commentDescr\">"	
									result += "<p>" +comment.content+ "</p>"
									console.log("predi length" + comment.id);
									if(comment.replyComments.length > 0){
										result += "<div id =\"bloc1\" class=\"btn btn-primary btn-xs\" data-toggle=\"collapse\" data-target=\"#a"+comment.id+"\">" +
												"Show replies " +
												"<span class=\"badge\">" +comment.replyComments.length+"</span>"
																		 	
										result += "</div>"  
									}else{
																			
										result += "<div id =\"bloc3\" class=\"btn btn-primary btn-xs\" data-toggle=\"collapse\"" +
												"data-target=\"#idto" + comment.id+ "\">Reply" +				
										"</div>"
									}	
									result += "<div id =\"bloc2\" class=\"stats\" align=\"right\">" 
										
										loadLikes(comment.id, 'LIKES')
										
										loadLikes(comment.id, 'DISLIKES')
										
										var id = 'LIKESCount' + comment.id;
										result += "<a class=\"like\" id=\""+id+"\"></a>";
										var ids = 'DISLIKESCount' + comment.id;
										result += "<a class=\"like\" id=\""+ids+"\"></a>";
																			      
									result += "</div>" 																		
								result += "</div>"										
							result += "</div>"	
						result += "</div>"
					result += "</div>"
				result += "</div>"	
					
					
					
					
				result += "<div id=\"idto"+comment.id+"\" class=\"collapse\">"
								
						result +=	"<fieldset>"
							result +="<div class=\"col-lg-12\">"
								
								result +="<textarea class=\"form-control\" id=\"name"+comment.id +"\" rows=\"3\" name=\"content\"></textarea>"
								result +="</div>"
							
									
								result +="<div class=\"form-group\">"
									result +="<div class=\"col-sm-4 col-sm-offset-4\">"
									
										result +="<button class=\"btn btn-success\" id='add' value=\""+comment.id+"\" >Send</button>"
									result += "</div>"	
								result += "</div>"
						result +="</fieldset>"
																													
			result += "</div>"		
			
				
				result += "<div id=\"a" + comment.id +"\" class=\"collapse\">"	
					result += "<div class=\"col-md-12\">"
						result += "<div class=\"container body-content col-md-4\">"	
							
								loadReplies(comment.id);
								
							   result += "<div id=\"replies" + comment.id +"\"></div>"
						result += "</div>"	
					result += "</div>"
						
					result += "<div type=\"button\" class=\"btn btn-primary btn-xs\" data-toggle=\"collapse\" data-target=\"#torepId1" +comment.id +"\">Reply</div>"
						
						result += "<div id=\"torepId1"+comment.id+"\" class=\"collapse\">"
							result +=	"<fieldset>"							
									result +="<div class=\"col-lg-12\">"
									result +="<textarea class=\"form-control\" id=\"name1"+comment.id +"\" rows=\"3\" name=\"content\"></textarea>"
									result +="</div>"
								
										
									result +="<div class=\"form-group\">"
										result +="<div class=\"col-sm-4 col-sm-offset-4\">"
										
											result +="<button class=\"btn btn-success\" id='add' value=\""+comment.id+"\" >Send</button>"
										result += "</div>"	
									result += "</div>"	
							result +="</fieldset>"
													
						result += "</div>"	
																		
					result += "</div>"	
						
						
		
			}
			
			result += "<br>"
			result += "<br>"

	result += "<div class=\"container body-content\">"
					
		result += "<div class=\"row\">"
			result += "<div type=\"button\" class=\"btn btn-primary\" data-toggle=\"collapse\" data-target=\"#write5\">Write a comment</div>"
									
		result += "</div>"
						
		result += "<div id=\"write5\" class=\"collapse\" >"
		result += "<br/>"
			result += "<div class=\"col-lg-12\">"
					//result += "<textarea class=\"form-control\" rows=\"3\" name=\"content\"></textarea>"
					result +="<textarea class=\"form-control\" id=\"name1"+bookId +"\" rows=\"3\" name=\"content\"></textarea>"	
			result += "</div>"
			result += "<div class=\"form-group\">"
				result += "<div class=\"col-sm-4 col-sm-offset-4\">"
								
						result +="<button class=\"btn btn-success\" id='addNewComment' value=\""+bookId+"\" >Send</button>"
								//result += "<input type="submit" class="btn btn-success" value="Send"/>"
						
				result += "</div>"
			result += "</div>"
		result += " </div>"
				result += "</div>"
						
			result += "</div>"	
		result += "</div>"	
			result += "</div>"						
			
				
			
				
			$('#comments1').html(result);
			
			$('.like').on('click', function() {
				console.log('vlizam li v clicka')
				var input = $(this).attr('id');
				var commentId = input.split("Count")[1];
				var type = input.split("Count")[0];
				console.log("kakvo predi post" + input)
				
				var data ={
					input:input
				}
						
				$.post('getLikes', {data: JSON.stringify(data)}).then(function() {
											
						loadLikes(commentId, 'LIKES');
						loadLikes(commentId, 'DISLIKES');
					});
														
			});	
			
			$('button').on('click', function() {
				
				
				var id = $(this).attr('value');
							
				var name = "name" + id;
				
				var name1 = "name1" + id;
				
				var data  = {
					name: $("#"+name).val(),
					name1:$("#"+name1).val(),
					commentId: id
				}; 
				
				var url = 'getReplies';
				if($(this).attr('id')=== 'addNewComment'){
					url = 'getComments'
				}
				
				if($(this).attr('id') !== "buyButton"){
					
					$.post(url, {data: JSON.stringify(data)}).then(function() {
						loadReplies(id);
						loadComments(bookId);
					});
				}
			});
			
		});
		
		
	}
		
	var bookId = getUrlParameter('book');
	loadComments(bookId);

	function loadLikes(commentId, type){
		$.get('getLikes?commentId=' + commentId + '&type=' + type).then( function(data) {
			var likes = data;
			var result = '';
			if(type ==='LIKES'){
			 result += "<img alt=\"like\" width=\"30 px\" height=\"100%\"  src=\"images/static/like.png\">"
			}else{
				if(type ==='DISLIKES'){
					result += "<img alt=\"like\" width=\"30 px\" height=\"100%\"  src=\"images/static/dislike.png\">"
				}else{
					if(type ==='LIKESreply'){
						result += "<img alt=\"like\" width=\"15 px\" height=\"100%\"  src=\"images/static/like.png\">"
					}else{
						result += "<img alt=\"like\" width=\"15 px\" height=\"100%\"  src=\"images/static/dislike.png\">"
					}
				}
			}
				if(likes>0){
				 result +="<sub><span class=\"badge\">"+likes+"</span></sub>"
				}
			
			 var id ='';
			if(type ===('LIKES')){
			 id = 'LIKESCount' + commentId;
			}else{
			 id = 'DISLIKESCount' + commentId;
			}
			console.log("kakvo id izprashtam"+id);
			$("#"+id).html(result);
			
		});
		
		
	}
	
	
	
	function loadReplies(commentId){
		console.log(commentId)
		$.get('getReplies?commentId=' + commentId).then( function(data) {
			
			var reply = '';
			var result = '';
			for (var index = 0; index < data.length; index++) {				
				reply = data[index];
				result += "<div class=\"col-md-12\">"
					result += "<div class=\"container\">"						
						result += "<div class=\"row\">"
							result += "<div class=\"col-sm-1\">"
								result += "<div class=\"thumbnail\">"
									result += "<img class=\"img-responsive user-photo\" src=\"avatar?photo="+reply.user.userAvatar+"\">"
									
								result += "</div>"	
							result += "</div>"		
							
							result += "<div class=\"col-sm-6\">"	
								result += "<div class=\"panel panel-default\" id=\"replyBack\">"
									result += "<div class=\"panel-heading\">"
										result += "<strong>"+reply.user.username+"</strong>"
										result += "<span class=\"text-muted\"> &nbsp; commented on "+reply.date+"</span>"
									result += "</div>"
									
									result += "<div class=\"panel-body\">"
										result += reply.content
										result += "<div  class=\"stats\" align=\"right\">"
											
											loadLikes(reply.id, 'LIKES')
											
											loadLikes(reply.id, 'DISLIKES')
											
											var id = 'LIKESCount' + reply.id;
											result += "<a class=\"likeReply\" id=\""+id+"\"></a>";
											var ids = 'DISLIKESCount' + reply.id;
											result += "<a class=\"likeReply\" id=\""+ids+"\"></a>";
											
										result += "</div>"												
									result += "</div>"	
										
									
								result += "</div>"
							result += "</div>"	
						result += "</div>"		
					result += "</div>"	
				result += "</div>"				
			}
			
			var id = 'replies' + commentId;
			$("#" + id).html(result);
			
			
			$('.likeReply').off().on('click', function() {
				console.log('vlizam li v clicka2')
				
				var input = $(this).attr('id');
				var commentId = input.split("Count")[1];
				var type = input.split("Count")[0];
				console.log("kakvo predi post" + input)
				
				
				
				var data ={
					input:input
				}
							
				
				$.post('likes', {data: JSON.stringify(data)}).then(function(e) {
											
						loadLikes(commentId, 'LIKES');
						loadLikes(commentId, 'DISLIKES');
						return
					});
													
				return;
			});
		});
	}
	
	
});


function change()
{
    var elem = document.getElementById("myButton1");
    if (elem.value=="Hide comments") elem.value = "Show comments";
    else elem.value = "Hide comments";
}

