<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ru">
<%@ include file="../../../jspf/head.jspf"%>
<body>
	<%@ include file="../../../jspf/navbar.jspf"%>

	<div class="page-content container">
		<div class="row">
			<h1>${pageTitle}-редактирование</h1>
			<p>Логин: ${user.login}</p>
			<p>Имя: ${user.name}</p>
			<p>Фамилия: ${user.surname}</p>

			<form class="form-horizontal"
				action="JavaScript:function eptyfunc(){return;};">
				<fieldset>

					<!-- Form Name -->
					<legend>Создать нового пользователя</legend>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="textinput">Логин</label>
						<div class="col-md-4">
							<input id="login_input" name="textinput" type="text"
								placeholder="Логин" class="form-control input-md disabled"
								required=""> <span class="help-block">Логин</span>
						</div>
					</div>

					<!-- Password input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="passwordinput">Пароль</label>
						<div class="col-md-4">
							<input id="password_input" name="passwordinput" type="password"
								placeholder="Пароль" class="form-control input-md" required="">
							<span class="help-block">Пароль</span>
						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="textinput">Фамилия</label>
						<div class="col-md-4">
							<input id="name_input" name="textinput" type="text"
								placeholder="Фамилия" class="form-control input-md" required="">
							<span class="help-block">Фамилия</span>
						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="textinput">Имя</label>
						<div class="col-md-4">
							<input id="surname_input" name="textinput" type="text"
								placeholder="Имя" class="form-control input-md" required="">
							<span class="help-block">Имя</span>
						</div>
					</div>

					<!-- Multiple Checkboxes -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="checkboxes">Права</label>
						<div class="col-md-4">
							<div class="checkbox">
								<label for="checkboxes-0"> <input id="user_input" type="checkbox"
									name="checkboxes" id="checkboxes-0" value="1"> Пользователь
								</label>
							</div>
							<div class="checkbox">
								<label for="checkboxes-1"> <input id="admin_input" type="checkbox"
									name="checkboxes" id="checkboxes-1" value="2"> Администратор								</label>
							</div>
						</div>
					</div>

					<div id="messagePanel"></div>

					<!-- Button -->
					<div class="form-group">
						<label class="col-md-4 control-label" for="sendButton">Создать:</label>
						<div class="col-md-4">
							<button id="sendButton" name="sendButton" class="btn btn-success">Создать</button>
						</div>
					</div>

				</fieldset>
			</form>

		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/users/new.js"></script>
</body>
</html>