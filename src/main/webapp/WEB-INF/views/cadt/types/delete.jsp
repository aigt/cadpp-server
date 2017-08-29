<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ru">
<%@ include file="../../../jspf/head.jspf"%>
<body>
	<%@ include file="../../../jspf/navbar.jspf"%>

	<div class="page-content container">
		<div class="row">

			<form class="form-horizontal"
				action="JavaScript:function eptyfunc(){return;};">
				<fieldset>

					<!-- Form Name -->
					<legend>Подтверждение удаления пользователя</legend>

					<div>Логин: <span id="login_label"></span></div>
					<div>Имя: <span id="name_label"></span></div>
					<div>Фамилия: <span id="surname_label"></span></div>

					<div id="messagePanel"></div>

					<!-- Button -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="sendButton">Удалить:</label>
						<div class="col-md-4">
							<button id="sendButton" name="sendButton" class="btn btn-danger">Удалить</button>
						</div>
					</div>

				</fieldset>
			</form>

		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/users/delete.js"></script>
</body>
</html>