// Add an admin user using AJAX
function viewUser() {

	var userId = $(this).closest('a').attr('data-userId');

	$.ajax({

		url: '/usjobs/service/user/' + userId,
		dataType: 'json',
		success: function( user ) {

			$('#user-display td[data-field=userId]').html( user.id ),
			$('#user-display td[data-field=username]').html( user.username ),
			$('#user-display td[data-field=password]').html( user.password ),
			$('#user-display td[data-field=firstName]').html( user.firstName ),
			$('#user-display td[data-field=lastName]').html( user.lastName ),
			$('#user-display td[data-field=email]').html( user.email )
		}
	})

	$('#user-display').dialog('open');
}

function toggleUser() {

	var userId = $(this).closest('a').attr('data-userId');

	$.ajax({

		url: '/usjobs/service/user/toggle/' + userId,
		method: 'PUT',
		context: $(this),
		success: function() {

			var newHtml;

			if ( $(this).hasClass('disable') ) {

				$(this).removeClass('btn-danger disable').addClass('btn-warning enable');
				newHtml = "<i class=\"fa fa-level-up\" aria-hidden=\"true\"></i>&nbsp;&nbsp;Enable";

			} else if ( $(this).hasClass('enable') ) {

				$(this).removeClass('btn-warning enable').addClass('btn-danger disable');
				newHtml = "<i class=\"fa fa-level-down\" aria-hidden=\"true\"></i>&nbsp;&nbsp;Disable";

			} else {

				console.log('Could not determine toggle type.');
			}

			$(this).html( newHtml );
		}
	});
}

function addUser() {

	var user = {
			username: $('input[name=username]').val(),
			password: $('input[name=password]').val(),
			firstName: $('input[name=firstName]').val(),
			lastName: $('input[name=lastName]').val(),
			email: $('input[name=email]').val()
	};

	$.ajax({

		url: '/usjobs/service/users',
		method: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify( user ),
		success: function( user ) {

			var newRow = $("<tr data-user-id='" + user.id + "'><td data-field='id'>" + 
					user.id + "</td><td data-field='username'>" +
					user.username + "</td><td data-field='password'>" + user.password + 
					"</td><td data-field='firstName'>" + user.firstName +
					"</td><td data-field='lastName'>" + user.lastName + 
					"</td><td data-field='email'>" + user.email + 
					"</td><td data-field='userRole'>" + user.userRoles + "</td>" +
					"<a data-userId='" + user.id + "' class='btn btn-info view' role='button'>" +
						"<i class='fa fa-street-view'" +
						"aria-hidden='true'></i>&nbsp;&nbsp; View</a>" +
					"<a data-userId='" + user.id + "' class='btn btn-success edit' role='button'>" +
						"<i class='fa fa-pencil-square-o' aria-hidden='true'>" +
						"</i>&nbsp;&nbsp;Edit</a>" +
					"<a data-userId='" + user.id + "' class='btn btn-warning enable' role='button'>" +
					"<i class='fa fa-level-up' aria-hidden='true'>" +
					"</i>&nbsp;&nbsp;Enable</a></td></tr>"
			);

			$('#users').append(newRow);

			// Need to re-attach event handlers to account for this newly added row.
		    $('.view').unbind('click.namespace').bind('click.namespace', viewUser);
		    $('.edit').unbind('click.namespace').bind('click.namespace', editHandler);
		    $('.disable, .enable').unbind('click.namespace').bind('click.namespace', toggleUser);
		    
		}
	});
}

function editUser() {

	var userId = $('input[name=userId]').val();

	var user = {
			username: $('input[name=username]').val(),
			password: $('input[name=password]').val(),
			firstName: $('input[name=firstName]').val(),
			lastName: $('input[name=lastName]').val(),
			email: $('input[name=email]').val()
	};

	$.ajax({

		url: '/usjobs/service/user/' + userId,
		method: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify( user ),
		success: function() {

			var editRow = $('#users tr[data-user-id=' + userId + ']');
			
			editRow.find('td[data-field=username]').html( user.username );
			editRow.find('td[data-field=password]').html( user.password );
			editRow.find('td[data-field=firstName]').html( user.firstName );
			editRow.find('td[data-field=lastName]').html( user.lastName );
			editRow.find('td[data-field=email]').html( user.email );
		}
	});
}

function editHandler() {

	var userId = $(this).closest('tr').attr('data-user-id');
	var username = $(this).closest('tr').children('td[data-field=username]').html();
	var password = $(this).closest('tr').children('td[data-field=password]').html();
	var firstName = $(this).closest('tr').children('td[data-field=firstName]').html();
	var lastName = $(this).closest('tr').children('td[data-field=lastName]').html();
	var email = $(this).closest('tr').children('td[data-field=email]').html();

	$('#user-form input[name=username]').val( username );
	$('#user-form input[name=password]').val( password );
	$('#user-form input[name=firstName]').val( firstName );
	$('#user-form input[name=lastName]').val( lastName );
	$('#user-form input[name=email]').val( email );

	$('#user-form input[name=userId]').val( userId );

	$('#user-form').dialog('open');
}

$( function() {

	$('#user-form').dialog({
		autoOpen: false,
		minWidth: 500,
		title: "Add User / Edit User",
		close: function( event, ui ) {
			$('input[name=userId]').val('')
		},
		buttons : {
			"Save" : function() {
				if ( ! $('input[name=userId]').val() ) {
					addUser();
				} else {
					editUser();
				}
				$("#user-form").dialog("close");
			},
			Cancel : function() {
				$(this).dialog("close");
			}
		}
	});

	$('#user-display').dialog({
		autoOpen: false,
		minWidth: 500,
		title: "Display User"
	});

	// Need to re-attach event handlers to account for this newly added row.
    $('.view').unbind('click.namespace').bind('click.namespace', viewUser);
    $('.edit').unbind('click.namespace').bind('click.namespace', editHandler);
    $('.disable, .enable').unbind('click.namespace').bind('click.namespace', toggleUser);

	$('#add').click( function() {

		$('#addForm')[0].reset();
		$('#user-form').dialog('open');
	});
});
