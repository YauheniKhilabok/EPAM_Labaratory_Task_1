<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title>Header</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="../../index.jsp">sports-nutrition.by</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="active"><a href="../../index.jsp#map"><fmt:message key="header.map.title"
                                                                                  bundle="${rb}"/></a></li>
                    <li><a href="../../index.jsp#contacts"><i class="icon-user icon-white"></i>&nbsp;<fmt:message
                            key="header.contacts.title" bundle="${rb}"/></a></li>
                </ul>
                <c:choose>
                    <c:when test="${authorized eq false}">
                        <form class="navbar-form pull-right" action="${pageContext.request.contextPath}\controlServlet"
                              method="post">
                            <input type="hidden" value="/index.jsp" name="page">
                            <input class="span2" type="tel"
                                   placeholder="<fmt:message key="header.input.login.placeholder" bundle="${rb}"/>"
                                   name="login" required>
                            <input class="span2" type="password"
                                   placeholder="<fmt:message key="header.input.password.placeholder" bundle="${rb}"/>"
                                   name="password" required>
                            <button class="btn" type="submit" value="Authorization" name="cmd"><fmt:message
                                    key="header.button.enter" bundle="${rb}"/></button>
                            <a href="/jsp/main/registration.jsp" class="btn btn-primary"><fmt:message
                                    key="header.link.registration" bundle="${rb}"/></a>
                        </form>
                        <span class="text-error">${errorAuthorizationMessage}</span>
                    </c:when>
                    <c:when test="${authorized eq true}">
                        <c:set var="admin" scope="session" value="admin"/>
                        <c:if test="${role eq admin}">
                            <form class="navbar-form pull-right"
                                  action="${pageContext.request.contextPath}\controlServlet"
                                  method="get">
                                <a href="/jsp/admin/admin-index.jsp" class="btn btn-primary"><i
                                        class="icon-th icon-white"></i><fmt:message key="header.link.menu"
                                                                                    bundle="${rb}"/></a>
                                <button class="btn" type="submit" value="Exit" name="cmd"><i
                                        class="icon-off"></i><fmt:message key="header.button.exit" bundle="${rb}"/>
                                </button>
                            </form>
                            <span class="text-success"><ctg:hello nameUser="${nameUser}" role="${role}"/></span>
                        </c:if>
                        <c:set var="user" scope="session" value="user"/>
                        <c:if test="${role eq user}">
                            <form class="navbar-form pull-right"
                                  action="${pageContext.request.contextPath}\controlServlet"
                                  method="get">
                                <button class="btn btn-primary" type="submit" value="PrintProductsForUser" name="cmd"><i
                                        class="icon-th icon-white"></i><fmt:message key="header.link.menu"
                                                                                    bundle="${rb}"/>
                                </button>
                                <button class="btn" type="submit" value="Exit" name="cmd"><i
                                        class="icon-off"></i><fmt:message key="header.button.exit" bundle="${rb}"/>
                                </button>
                            </form>
                            <span class="text-success"><ctg:hello nameUser="${nameUser}" role="${role}"/></span>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <form class="navbar-form pull-right" action="${pageContext.request.contextPath}\controlServlet"
                              method="post">
                            <input type="hidden" value="/index.jsp" name="page">
                            <input class="span2" type="tel"
                                   placeholder="<fmt:message key="header.input.login.placeholder" bundle="${rb}"/>"
                                   name="login" required>
                            <input class="span2" type="password"
                                   placeholder="<fmt:message key="header.input.password.placeholder" bundle="${rb}"/>"
                                   name="password" required>
                            <button class="btn" type="submit" value="Authorization" name="cmd"><fmt:message
                                    key="header.button.enter" bundle="${rb}"/></button>
                            <a href="/jsp/main/registration.jsp" class="btn btn-primary"><fmt:message
                                    key="header.link.registration" bundle="${rb}"/></a>
                        </form>
                        <span class="text-error">${errorAuthorizationMessage}</span>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</header>
</body>
</html>
