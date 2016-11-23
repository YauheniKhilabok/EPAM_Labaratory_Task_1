<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="admin.navigation.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header class="jumbotron">
    <div class="locale-position">
        <label class="control-label input-size"><fmt:message key="user.navigation.form.label" bundle="${rb}"/></label>
        <div class="locale-width">
            <div class="locale-first-button">
                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                    <input type="hidden" value="ru_RU" name="locale">
                    <button class="btn btn-default" type="submit" value="ChangeLocale" name="cmd">RU</button>
                </form>
            </div>
            <div class="locale-second-button">
                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                    <input type="hidden" value="en_US" name="locale">
                    <button class="btn btn-primary" type="submit" value="ChangeLocale" name="cmd">US</button>
                </form>
            </div>
        </div>
    </div>
    <div class="container text-center">
        <img src="../../img/logo.png" alt="sports-nutrition.by">
    </div>
</header>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="../../index.jsp">sports-nutrition.by</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/jsp/admin/admin-index.jsp"><fmt:message key="admin.navigation.label.one"
                                                                                     bundle="${rb}"/></a></li>
                <li>
                    <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                        <button class="btn-lg btn-link" type="submit" value="ShowProducersList" name="cmd"><span
                                class="glyphicon glyphicon-globe"></span>&nbsp;
                            <fmt:message key="admin.navigation.label.two" bundle="${rb}"/>
                        </button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                        <button class="btn-lg btn-link" type="submit" value="ShowTypesList" name="cmd"><span
                                class="glyphicon glyphicon-th-list"></span>&nbsp;
                            <fmt:message key="admin.navigation.label.three" bundle="${rb}"/>
                        </button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                        <button class="btn-lg btn-link" type="submit" value="ShowUsersList" name="cmd"><span
                                class="glyphicon glyphicon-user"></span>&nbsp;
                            <fmt:message key="admin.navigation.label.four" bundle="${rb}"/>
                        </button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                        <button class="btn-lg btn-link" type="submit" value="ShowProductsList" name="cmd"><span
                                class="glyphicon glyphicon-piggy-bank"></span>&nbsp;<fmt:message
                                key="admin.navigation.label.five" bundle="${rb}"/>
                        </button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                        <button class="btn-lg btn-link" type="submit" value="ShowOrdersList" name="cmd"><span
                                class="glyphicon glyphicon-shopping-cart"></span>&nbsp;<fmt:message
                                key="admin.navigation.label.six" bundle="${rb}"/>
                        </button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                        <button class="btn-lg btn-link" type="submit" value="ShowAdminStatistics" name="cmd"><span
                                class="glyphicon glyphicon-signal"></span>&nbsp;<fmt:message
                                key="admin.navigation.label.seven" bundle="${rb}"/>
                        </button>
                    </form>
                </li>
                <li>
                    <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                        <button class="btn-lg btn-link" type="submit" value="PrintReviews" name="cmd"><span
                                class="glyphicon glyphicon-pencil"></span>&nbsp;<fmt:message
                                key="admin.navigation.label.eighth" bundle="${rb}"/>
                        </button>
                    </form>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                        <button class="btn-lg btn-link" type="submit" value="Exit" name="cmd"><span
                                class="glyphicon glyphicon-off"></span>&nbsp;<fmt:message
                                key="admin.navigation.label.nine" bundle="${rb}"/>
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
