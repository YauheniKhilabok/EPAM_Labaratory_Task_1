<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="admin.users.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="admin.users.description" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="admin.users.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/admin-style.css">
    <link rel="stylesheet" href="../../css/pagination.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=ShowUsersList"/>
<c:import url="navigation.jsp"></c:import>
<main class="container-fluid">
    <div class="button-margin-bottom">
        <div class="div-pos">
            <form action="${pageContext.request.contextPath}\controlServlet" method="post">
                <input type="hidden" value="up" name="sorting">
                <button class="btn btn-default" type="submit" value="SortUsersByName" name="cmd"><fmt:message
                        key="admin.users.button.one" bundle="${rb}"/>&nbsp;<span
                        class="glyphicon glyphicon-chevron-up"></span></button>
            </form>
        </div>
        <div>
            <form action="${pageContext.request.contextPath}\controlServlet" method="post">
                <input type="hidden" value="down" name="sorting">
                <button class="btn btn-default" type="submit" value="SortUsersByName" name="cmd"><fmt:message
                        key="admin.users.button.one" bundle="${rb}"/>&nbsp;<span
                        class="glyphicon glyphicon-chevron-down"></span></button>
            </form>
        </div>
    </div>
    <div class="main-user-management-style">
        <div class="error-message-text-style"><p>${errorDeleteUser}</p></div>
        <div class="error-message-text-style"><p>${warningSearchUsers}</p></div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="admin.users.table.label.one" bundle="${rb}"/></th>
                <th>Email</th>
                <th><fmt:message key="admin.users.table.label.two" bundle="${rb}"/></th>
                <th><fmt:message key="admin.users.table.label.three" bundle="${rb}"/></th>
                <th><fmt:message key="admin.users.table.label.four" bundle="${rb}"/></th>
                <th><fmt:message key="admin.users.table.label.five" bundle="${rb}"/></th>
                <th><fmt:message key="admin.users.table.label.six" bundle="${rb}"/></th>
                <th><fmt:message key="admin.users.table.label.four" bundle="${rb}"/></th>
                <th><fmt:message key="admin.users.table.label.five" bundle="${rb}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${usersList}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.nameUser}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.address}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <td><c:out value="${user.status}"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                            <input type="hidden" value="<c:out value="${user.id}"/>" name="id_user">
                            <button class="btn btn-primary" type="submit" value="DeleteUser" name="cmd"><fmt:message
                                    key="admin.users.button.two" bundle="${rb}"/></button>
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                            <input type="hidden" value="<c:out value="${user.id}"/>" name="id_user">
                            <button class="btn btn-default" type="submit" value="ChangeUserRole" name="cmd"><fmt:message
                                    key="admin.users.button.three" bundle="${rb}"/>
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                            <input type="hidden" value="<c:out value="${user.id}"/>" name="id_user">
                            <button class="btn btn-primary" type="submit" value="ChangeUserStatus" name="cmd">
                                <fmt:message key="admin.users.button.three" bundle="${rb}"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:import url="pagination.jsp"></c:import>
    </div>
</main>
<br><br>
<footer class="container-fluid text-center main-footer">
    <form class="form-inline" action="${pageContext.request.contextPath}\controlServlet" method="post">
        <input type="text" class="form-control input-border" placeholder="ФИО" name="name_user" required>
        <button type="submit" class="btn btn-default" value="SearchUsersByName" name="cmd"><fmt:message
                key="admin.users.button.four" bundle="${rb}"/><span
                class="glyphicon glyphicon-search"></span>
        </button>
    </form>
</footer>
</body>
</html>


