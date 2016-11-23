<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="user.index.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="user.index.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="user.index.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="user.index.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/user-style.css">
    <link rel="stylesheet" href="../../css/pagination.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../js/scrolltop.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=PrintProductsForUser"/>
<c:import url="navigation.jsp"></c:import>
<main class="container">
    <div class="button-margin-bottom">
        <div class="div-pos">
            <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                <input type="hidden" value="up" name="sorting">
                <button class="btn btn-success" type="submit" value="SortProductsByPrice" name="cmd"><fmt:message
                        key="user.index.button.one" bundle="${rb}"/>&nbsp;<span
                        class="glyphicon glyphicon-chevron-up"></span></button>
            </form>
        </div>
        <div>
            <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                <input type="hidden" value="down" name="sorting">
                <button class="btn btn-success" type="submit" value="SortProductsByPrice" name="cmd"><fmt:message
                        key="user.index.button.one" bundle="${rb}"/>&nbsp;<span
                        class="glyphicon glyphicon-chevron-down"></span></button>
            </form>
        </div>
    </div>
    <div class="row">
        <c:forEach var="products" items="${productsList}">
            <div class="col-sm-3">
                <div class="panel panel-primary">
                    <div class="panel-heading"><c:out value="${products.nameProduct}"/>
                    </div>
                    <div class="panel-body img-size">
                        <img src="<c:out value="${products.pathToImage}"/>"
                             class="img-size-for-catalog"
                             alt="<fmt:message key="user.index.alt" bundle="${rb}"/>">
                    </div>
                    <div class="panel-footer">
                        <p><strong><fmt:message key="user.index.label" bundle="${rb}"/> </strong><c:out
                                value="${products.type.type}"/></p>
                        <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                            <input type="hidden" value="<c:out value="${products.id}"/>" name="id_product">
                            <button class="btn btn-success" type="submit" value="GetMoreInformationAboutProduct"
                                    name="cmd"><fmt:message key="user.index.button.two" bundle="${rb}"/>&nbsp;<span
                                    class="glyphicon glyphicon-arrow-down"></span>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="pagination-position">
            <div class="pagination">
                <ul>
                    <c:if test="${currentPage gt 1}">
                        <li><a class="greencurve"
                               href="controlServlet?page=${currentPage - 1}">&laquo;<fmt:message
                                key="user.index.previous" bundle="${rb}"/></a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${numberPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="pagination-color">${i}</li>
                            </c:when>
                            <c:otherwise>
                                <li><a class="greencurve" href="controlServlet?page=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage lt numberPages}">
                        <li><a class="greencurve"
                               href="controlServlet?page=${currentPage + 1}"><fmt:message key="user.index.next"
                                                                                          bundle="${rb}"/>&raquo;</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</main>
<br><br>
<c:import url="footer.jsp"></c:import>
</body>
</html>


