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
<p class="text-error"><fmt:message key="error.label.six" bundle="${rb}"/></p>
<a class="btn btn-primary" href="../../index.jsp"><fmt:message key="error.button" bundle="${rb}"/></a>
</body>
</html>
