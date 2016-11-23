<!DOCTYPE html>
<%@ page language="java" isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="error.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../css/bootstrap.css">
</head>
<body>
<div>
    <p><fmt:message key="error.label.one.part.first" bundle="${rb}"/> ${pageContext.errorData.requestURI}
        <fmt:message key="error.label.one.part.second" bundle="${rb}"/></p>
    <p><fmt:message key="error.label.two" bundle="${rb}"/> ${pageContext.errorData.servletName}</p>
    <p><fmt:message key="error.label.three" bundle="${rb}"/> ${pageContext.errorData.statusCode}</p>
    <p class="text-error"><fmt:message key="error.label.four" bundle="${rb}"/> ${pageContext.exception}</p>
    <p class="text-error"><fmt:message key="error.label.five" bundle="${rb}"/> ${pageContext.exception.message}</p>
    <p>${accessError}</p>
    <a class="btn btn-primary" href="../../index.jsp"><fmt:message key="error.button" bundle="${rb}"/></a>
</div>
</body>
</html>
