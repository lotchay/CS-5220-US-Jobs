// new version
$(function() {
	
	/**
	 * Configures the search box to auto complete for job postings. The url that
	 * source points to returns an array of strings (list or array is fine), which represents
	 * the autocompletion results to choose from. for example, if i type in 's', then i might get
	 * ['software engineer', 'salesman'], and these two would show up in the autocomplete dropdown.
	 */
	$("#searchKey").autocomplete({
		 source: "service/search/jobs",
		 minLength: 1
	});
	
	/**
	 * When search type is job postings, enable the autocomplete
	 * but otherwise disable.
	 */
	$("#searchType").change(function() {
		var searchType = $(this).val();
		if (searchType == 'Job Postings') {
			$("#searchKey").autocomplete("enable");
		} else {
			$("#searchKey").autocomplete("disable");
		}
	});	
});

 //old version
//$(document).ready(function(){
//	$("#searchKey").keyup(auto);
//});
//
//function auto(){
//	var searchKey = $("#searchKey").val();
//	var searchType = $("#searchType").val();
//	var searchLoc = $("#searchLoc").val();
//	$("#searchKey").autocomplete({
//		minLength: 3,
//		source: function(request, response){
//			if (searchType == "Job Postings"){
//				$.ajax({
//					type: "POST",
//					url: "/usjobs/service/search/jobs",
//					data: {
//						searchKey: searchKey,
//						searchLoc: searchLoc,
//					},
//					dataType: "json",
//					success: function(data){
//						console.log(data);
//						response(data);
//					}
//				});
//			}
//		},
//		focus: function(event, ui){
//			$("#searchKey").val(ui.item.value);
//			return false;
//		},
//		select: function(event, ui){
//			$("#searchKey").val(ui.item.value);
//			return false;
//		}
//	})
//	.autocomplete("instance")._renderItem = function(ul, item){
//		return $("<li>").append("<div>" + item.value + "</div>").appendTo(ul);
//	};
//}