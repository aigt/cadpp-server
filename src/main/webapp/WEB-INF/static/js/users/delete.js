$(document).ready(function() {
	
	$( "#sendButton" ).bind( "click", deleteUser);
	var login = getLoginInPathname($(location).attr('pathname'));

	$.ajax({
		url : '../json/users/'.concat(login),
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			$('#login_label').text(data.login);
			$('#name_label').text(data.name);
			$('#surname_label').text(data.surname);
		}
	});

});

var getLoginInPathname = function(pathnm) {
	var login = pathnm.substring(pathnm.lastIndexOf('/') + 1, pathnm.length);
	return login;
}

var deleteUser = function() {
	var login = getLoginInPathname($(location).attr('pathname'));

	$.ajax({
		url : '../json/users/'.concat(login),
		type : 'DELETE',
		timeout : 100000,
		success : function(data) {
			if (data == true)
				$("#messagePanel").text(
						'Пользователь успешно удалён');
			else
				$("#messagePanel").text(
						'Что-то пошло не так и и пользователь не удалён');
		}
	});

	event.getPreventDefault(); // disable normal form submit behavior
	return false; // prevent further bubbling of event
}