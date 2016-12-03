$(document).ready(function(){
	$("#searchKey").keyup(auto);
});

function auto(){
	var searchKey = $("#searchKey").val();
	var searchType = $("#searchType").val();
	var searchLoc = $("#searchLoc").val();
	$("#searchKey").autocomplete({
		minLength: 3,
		source: function(request, response){
			if (searchType == "Job Postings"){
				$.ajax({
					type: "POST",
					url: "/usjobs/service/search/jobs",
					data: {
						searchKey: searchKey,
						searchLoc: searchLoc,
					},
					dataType: "json",
					success: function(data){
						console.log(data);
						response(data);
					}
				});
			}
		},
		focus: function(event, ui){
			$("#searchKey").val(ui.item.value);
			return false;
		},
		select: function(event, ui){
			$("#searchKey").val(ui.item.value);
			return false;
		}
	})
	.autocomplete("instance")._renderItem = function(ul, item){
		return $("<li>").append("<div>" + item.value + "</div>").appendTo(ul);
	};
}