<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="navigation.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="hero-unit headpiece">
    <div class="locale-position">
        <label class="control-label input-size"><fmt:message key="navigation.form.locale.label" bundle="${rb}"/></label>
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
</div>
<nav class="masthead">
    <div class="navbar menu">
        <div class="navbar-inner menu-inner">
            <div class="container">
                <ul class="nav men">
                    <li class="active"><a href="/index.jsp"><i class="icon-home icon-black"></i>&nbsp;<fmt:message
                            key="navigation.link.main" bundle="${rb}"/></a>
                    </li>
                    <li><a href="/jsp/main/catalog.jsp"><i class="icon-folder-open icon-black"></i>&nbsp;<fmt:message
                            key="navigation.link.catalog" bundle="${rb}"/></a>
                    </li>
                    <li><a href="/jsp/main/shares.jsp"><i class="icon-gift icon-black"></i>&nbsp;<fmt:message
                            key="navigation.link.shares" bundle="${rb}"/></a></li>
                    <li><a href="/jsp/main/training.jsp"><i class="icon-heart icon-black"></i>&nbsp;<fmt:message
                            key="navigation.link.training" bundle="${rb}"/></a>
                    </li>
                    <li><a href="/jsp/main/diet.jsp"><i class="icon-ok icon-black"></i>&nbsp;<fmt:message
                            key="navigation.link.diet" bundle="${rb}"/></a></li>
                    <li><a href="/jsp/main/articles.jsp"><i class="icon-list icon-black"></i>&nbsp;<fmt:message
                            key="navigation.link.articles" bundle="${rb}"/></a></li>
                    <li><a href="/jsp/main/reviews.jsp"><i class="icon-pencil icon-black"></i>&nbsp;<fmt:message
                            key="navigation.link.reviews" bundle="${rb}"/></a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
</body>
</html>
