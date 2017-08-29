<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  trimDirectiveWhitespaces="true"  %>
<!DOCTYPE html>
<html lang="ru">
<%@ include file="../../jspf/head.jspf"%>
<body>
	<%@ include file="../../jspf/navbar.jspf"%>

	<div class="container">
		<div class="row">
			<h1>${pageTitle}<a class="btn btn-default"
					href="${contextPath}/users/${user.login}?edit"><em
					class="fa fa-pencil"></em></a>
			</h1>
			<p>Логин: ${user.login}</p>
			<p>Имя: ${user.name}</p>
			<p>Фамилия: ${user.surname}</p>
		</div>
	</div>
</body>
</html>