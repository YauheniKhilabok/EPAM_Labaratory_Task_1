<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title>Footer</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<footer class="container-fluid text-center">
    <form class="form-inline" action="${pageContext.request.contextPath}\controlServlet" method="get">
        <input type="text" class="form-control input-border"
               placeholder="<fmt:message key="user.footer.input.label" bundle="${rb}"/>" name="name_product" required>
        <button class="btn btn-success" type="submit" value="SearchProductsByName" name="cmd"><fmt:message
                key="user.footer.button" bundle="${rb}"/> <span
                class="glyphicon glyphicon-search"></span>
        </button>
    </form>
</footer>
</body>
</html>
