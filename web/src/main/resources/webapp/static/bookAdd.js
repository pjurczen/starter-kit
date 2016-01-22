$(document).ready(function() {
	$("#bookAdd").click(function () {
		var dataSet = {
				title : $("#title").val(),
				authors : $("#authors").val()
		};
		if((dataSet.title != "") || (dataSet.authors != "")) {
			$.ajax({
				type : "POST",
				url : "/workshop/books/",
				data : JSON.stringify(dataSet),
			    contentType : "application/json",
			    dataType : "json",
			    complete: function(data) {
			    		window.location.assign("/workshop/books/");
			        }
			});	
		}
	});
});