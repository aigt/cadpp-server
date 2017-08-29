$(document).ready(fillForm);

function fillForm() {
	var pagepointer
	if (isNaN(parseInt(urlParams['page'])))
		pagepointer = '?page=1';
	else
		pagepointer = '?page='.concat(urlParams['page']);

	$.ajax({
		url : 'json/users'.concat(pagepointer),
		type : 'GET',
		dataType : 'json',
		timeout : 100000,
		contentType : "application/json",
		success : function(data) {
			var sb = new StringBuffer();
			var content = data;
			var pathname = $(location).attr('pathname')
			for (var i = 0; i < content.length; i++) {
				var userpathname = pathname.concat('/')
						.concat(content[i].login);

				sb.append('<tr>');

				// buttons column
				sb.append('<td align="center">');
				// profile button
				sb.append('<a class="btn btn-primary" href="').append(
						userpathname).append('">');
				sb.append('<em class="fa fa-user"></em>');
				sb.append('</a>');
				// edit profile button
				sb.append('<a class="btn btn-default" href="').append(
						userpathname).append('?edit">');
				sb.append('<em class="fa fa-pencil"></em>');
				sb.append('</a>');
				// delete profile button
				sb.append('<a class="btn btn-danger" href="').append(
						userpathname).append('?delete">');
				sb.append('<em class="fa fa-trash"></em>');
				sb.append('</a>');
				// buttons column end
				sb.append('</td>');

				// other columns
				sb.append('<td class="hidden-xs">').append(content[i].id)
						.append('</td>');
				sb.append('<td>').append(content[i].login).append('</td>');
				sb.append('<td>').append(content[i].name).append('</td>');
				sb.append('<td>').append(content[i].surname).append('</td>');
				sb.append('<td>').append(content[i].roles).append('</td>');

			}

			$('#usersListTable').html(sb.toString());
		}
	});
}