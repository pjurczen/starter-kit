$(document).ready(function() {
	$("#bookRemove").click(function () {
		var dataSet = {
			id : tmpBookId
		};
		$.ajax({
			type : "DELETE",
			url : "/workshop/books/remove/",
			data : JSON.stringify(dataSet),
		    contentType : "application/json",
		    dataType : "json",
		    complete: function(data) {
		    		window.location.assign("/workshop/books/");
		        }
		});
	});
});