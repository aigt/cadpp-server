$(document).ready(function() {

	var login = getLoginInPathname($(location).attr('pathname'));

	$.ajax({
		url : '../json/users/'.concat(login),
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			$('#login_input').val(data.login);
			$('#name_input').val(data.name);
			$('#surname_input').val(data.surname);
		}
	});

});

var getLoginInPathname = function(pathnm) {
	var login = pathnm.substring(pathnm.lastIndexOf('/') + 1, pathnm.length);
	return login;
}

var sendUserData = function() {
	var login = getLoginInPathname($(location).attr('pathname'));

	var user = {};
	user['password'] = $('#password_input').val();
	user['name'] = $('#name_input').val();
	user['surname'] = $('#surname_input').val();

	$.ajax({
		url : '../json/users/'.concat(login),
		type : 'PUT',
		data : JSON.stringify(user),
		dataType : 'json',
		timeout : 100000,
		contentType : "application/json",
		success : function(data) {
			if (data == true)
				$("#messagePanel").text(
						'Информация о пользователе успешно обновлена');
			else
				$("#messagePanel").text(
						'Что-то пошло не так и информация о пользователе не была обновлена');
		}
	});

	event.getPreventDefault(); // disable normal form submit behavior
	return false; // prevent further bubbling of event
}