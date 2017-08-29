<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ru">
<%@ include file="../../jspf/head.jspf"%>
<body>
	<%@ include file="../../jspf/navbar.jspf"%>

	<div class="page-content container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">

				<div class="panel panel-default panel-table">
					<div class="panel-heading">
						<div class="row">
							<div class="col col-xs-6">
								<h3 class="panel-title">Список пользователей</h3>
								<div id="messagePanel"></div>
							</div>
							<div class="col col-xs-6 text-right">
								<button type="button" class="btn btn-sm btn-primary btn-create"
									onclick="window.location.href='${contextPath}/users?new'">
									Добавить нового</button>
							</div>
						</div>
					</div>
					<div class="panel-body table-responsive">
						<table class="table table-striped table-bordered table-list">
							<thead>
								<tr>
									<th><em class="fa fa-cog"></em></th>
									<th class="hidden-xs">ID</th>
									<th>Логин</th>
									<th>Имя</th>
									<th>Фамилия</th>
									<th>Роли</th>
								</tr>
							</thead>
							<tbody id="usersListTable"></tbody>
						</table>
					</div>

					<div class="panel-footer">
						<div class="row">
							<div class="col col-xs-4">Страница ${currentPage} из
								${pageCount}</div>
							<div class="col col-xs-8">
								<ul class="pagination hidden-xs pull-right">

									<%
										Long pcount = (Long) request.getAttribute("pageCount");
										Integer cpage = (Integer) request.getAttribute("currentPage");
										for (Long i = 1l; i <= pcount; i += 1) {
									%>
									<li
										<%
				if (i.equals(new Long(cpage))) {
					out.print(" class=\"active\"");
				}%>><a
										href="${contextPath}/users?page=<%=i%>"><%=i%></a></li>
									<%
										}
									%>
								</ul>
								<ul class="pagination visible-xs pull-right">
									<li
										<%if (cpage <= 1) {
				out.print(" class=\"hidden\"");
			}%>><a
										href="${contextPath}/users?page=<%=cpage-1%>">«</a></li>
									<li
										<%if (cpage >= pcount) {
				out.print(" class=\"hidden\"");
			}%>><a
										href="${contextPath}/users?page=<%=cpage+1%>">»</a></li>
								</ul>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script src="${pageContext.request.contextPath}/js/users/list.js"></script>
</body>
</html>