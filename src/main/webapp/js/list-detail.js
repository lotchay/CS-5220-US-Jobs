function showEditForm() {
	alert('${jobPosting}');
}

$(document).ready(function() {

	var optionsChildren = $("#options-list").children();
	var detailChildren = $("#detail").children();

	// hide all detail views but the first option's.
	optionsChildren.each(function(index) {
		$(this).click(function() {
			filterHides($(this));
		});
		if (index !== 0) {
			hideOrShow($(this), "hide");
		}
	});

	// hides or shows an element based on flag.
	function hideOrShow(element, flag) {
		var prefix = element.attr('id');
		if (flag === "hide") {
			$("#" + prefix + "Detail").hide();
		} else {
			$("#" + prefix + "Detail").show();
		}
	}

	// hides all detail views except the one whose option is chosen.
	function filterHides(element) {
		var prefixMatch = element.attr('id') + "Detail";
		detailChildren.each(function() {
			if ($(this).attr('id') === prefixMatch) {
				$(this).fadeIn();
			} else {
				$(this).hide();
			}
		});
	}
})