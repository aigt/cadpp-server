$(document).ready(function() {
	$("#sendButton").bind("click", createUser);
});

var getLoginInPathname = function(pathnm) {
	var login = pathnm.substring(pathnm.lastIndexOf('/') + 1, pathnm.length);
	return login;
}

var createUser = function() {
	var login = $('#login_input').val();

	var user = {};
	user['login'] = $('#login_input').val();
	user['password'] = $('#password_input').val();
	user['name'] = $('#name_input').val();
	user['surname'] = $('#surname_input').val();
	var roles = [];
	if ($("#user_input").is(":checked")) {
		roles.push('USER');
	}
	if ($("#admin_input").is(":checked")) {
		roles.push('ADMIN');
	}
	user['roles'] = roles;

	$.ajax({
		url : 'json/users/'.concat(login),
		type : 'POST',
		data : JSON.stringify(user),
		dataType : 'json',
		timeout : 100000,
		contentType : "application/json",
		success : function(data) {
			if (data == true)
				$("#messagePanel").text('Пользователь успешно создан');
			else
				$("#messagePanel").text(
						'Что-то пошло не так и пользователь несоздан');
		}
	});

	event.getPreventDefault(); // disable normal form submit behavior
	return false; // prevent further bubbling of event
}