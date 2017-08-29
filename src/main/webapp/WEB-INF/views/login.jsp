<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html lang="ru">
<%@ include file="../jspf/head.jspf" %>
	<body>
	
	<div class="container">
    	<div class="row">
    		<div class="col-md-4 col-md-offset-4">
            	<div class="content-wrap">
                        <h1>Представьтесь</h1>
                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success">
                                <p><span class="glyphicon glyphicon-chevron-down"></span>Вы успешно вышли.</p>
                            </div>
                        </c:if>
                        <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
                            <div class="form-group">
                                Логин: <input id="j_username" name="j_username" class="form-control" required="required" type="text" placeholder="Username">
                            </div>
                            <div class="form-group">
                                Пароль: <input id="j_password" name="j_password" class="form-control" required="required" type="password" placeholder="Password">
                            </div>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p><span class="glyphicon glyphicon-remove"></span>Не правильный логин или пароль</p>
                                </div>
                            </c:if>
                            <div class="action">
                                <button class="btn btn-primary signup" type="submit">Войти</button>
                            </div>
                        </form>
                    </div>
                    <p/>
                <div class="already">
                    <p>Или подайте заявку на регистрацию  <a href="${pageContext.request.contextPath}/register"><button class="btn btn-info btn-xs">Регистрация</button></p>
                    </a>
                </div>
        </div>
    </div>
</div>
	</body>
	
</html>
