<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="products.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<fmt:message key="products.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="products.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="products.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/bootstrap-responsive.css">
    <link rel="stylesheet" href="../../css/main-style.css">
    <link rel="stylesheet" href="../../css/pagination.css">
    <link rel="stylesheet" href="https://www.google.com/fonts#ChoosePlace:select/Collection:Oswald">
    <script type="text/javascript" src="../../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="../../js/scrolltop.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?type=${type}&cmd=ShowCatalogForGuests"/>
<c:import url="header.jsp"></c:import>
<main class="container">
    <c:import url="navigation.jsp"></c:import>
    <div class="container">
        <div class="hero-unit">
            <div class="row folder-padding">
                <c:set var="firstPage" scope="page" value="1"/>
                <c:if test="${authorized ne true and currentPage eq firstPage or currentPage eq null}">
                    <div class="alert alert-info">
                        <p><strong><fmt:message key="products.warning.part.one" bundle="${rb}"/></strong> <fmt:message
                                key="products.warning.part.two" bundle="${rb}"/>
                            <strong><fmt:message key="products.warning.part.three" bundle="${rb}"/></strong>.
                        </p>
                    </div>
                </c:if>
                <c:forEach var="products" items="${productsList}">
                    <div class="span4">
                        <div class="panel panel-primary products-frame-style">
                            <div class="panel-heading"><strong><c:out value="${products.nameProduct}"/></strong>
                            </div>
                            <div class="panel-body"><img src="<c:out value="${products.pathToImage}"/>"
                                                         class="img-size-for-catalog"
                                                         alt="<fmt:message key="products.alt.one" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <p>
                                    <small><b><fmt:message key="products.label.one" bundle="${rb}"/> </b> <c:out
                                            value="${products.producer.brand}"/></small>
                                </p>
                                <p>
                                    <small><b><fmt:message key="products.label.two" bundle="${rb}"/> </b>
                                        <c:out value="${products.numberPerUnit}"/>
                                        (<c:out value="${products.unit}"/>)
                                    </small>
                                </p>
                                <p>
                                    <small><b><fmt:message key="products.label.three" bundle="${rb}"/></b> <c:out
                                            value="${products.taste}"/></small>
                                </p>
                                <p>
                                    <b>
                                        <small class="text-success"><c:out value="${products.status}"/></small>
                                    </b>
                                </p>
                                <p>
                                    <small><b><fmt:message key="products.label.four" bundle="${rb}"/></b> <c:out
                                            value="${products.discounts}"/>%
                                    </small>
                                </p>
                                <p>
                                    <small><b><fmt:message key="products.label.five" bundle="${rb}"/></b> <c:out
                                            value="${products.purchasePrice}"/> руб.
                                    </small>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="pagination-position">
                    <div class="pagination">
                        <ul>
                            <c:if test="${currentPage gt 1}">
                                <li><a class="blackcurve"
                                       href="controlServlet?page=${currentPage - 1}">&laquo;<fmt:message
                                        key="products.page.previous" bundle="${rb}"/></a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${numberPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <li>${i}</li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a class="blackcurve" href="controlServlet?page=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${currentPage lt numberPages}">
                                <li><a class="blackcurve"
                                       href="controlServlet?page=${currentPage + 1}"><fmt:message
                                        key="products.page.next" bundle="${rb}"/>&raquo;</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br><br>
</main>
<c:import url="footer.jsp"></c:import>
</body>
</html>