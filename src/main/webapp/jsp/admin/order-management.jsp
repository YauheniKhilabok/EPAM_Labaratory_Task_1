<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="admin.order.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="admin.order.description" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="admin.order.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/admin-style.css">
    <link rel="stylesheet" href="../../css/pagination.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="../../js/hide.js" type="text/javascript"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=ShowOrdersList"/>
<c:import url="navigation.jsp"></c:import>
<main class="container-fluid">
    <div class="button-margin-bottom">
        <div class="div-pos">
            <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                <input type="hidden" value="up" name="sorting">
                <button class="btn btn-default" type="submit" value="SortByLeadTime"
                        name="cmd"><fmt:message key="admin.button.one" bundle="${rb}"/>&nbsp;<span
                        class="glyphicon glyphicon-chevron-up"></span></button>
            </form>
        </div>
        <div>
            <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                <input type="hidden" value="down" name="sorting">
                <button class="btn btn-default" type="submit" value="SortByLeadTime" name="cmd">
                    <fmt:message key="admin.button.one" bundle="${rb}"/>&nbsp;<span
                        class="glyphicon glyphicon-chevron-down"></span>
                </button>
            </form>
        </div>
    </div>
    <div class="main-user-management-style">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="admin.label.one" bundle="${rb}"/></th>
                <th><fmt:message key="admin.label.two" bundle="${rb}"/></th>
                <th><fmt:message key="admin.label.three" bundle="${rb}"/></th>
                <th><fmt:message key="admin.label.four" bundle="${rb}"/></th>
                <th><fmt:message key="admin.label.five" bundle="${rb}"/></th>
                <th><fmt:message key="admin.label.six" bundle="${rb}"/></th>
                <th><fmt:message key="admin.label.seven" bundle="${rb}"/></th>
                <th><fmt:message key="admin.label.eighth" bundle="${rb}"/></th>
                <th><fmt:message key="admin.label.nine" bundle="${rb}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="orderProduct" items="${orderProductList}">
                <tr>
                    <td><c:out value="${orderProduct.order.id}"/></td>
                    <td><c:out value="${orderProduct.order.user.nameUser}"/></td>
                    <td><c:out value="${orderProduct.product.nameProduct}"/></td>
                    <td><c:out value="${orderProduct.numberOfPackages}"/></td>
                    <td><c:out value="${orderProduct.cost}"/></td>
                    <td><c:out value="${orderProduct.order.leadTime}"/></td>
                    <td><c:out value="${orderProduct.order.deliveryCondition}"/></td>
                    <td><c:out value="${orderProduct.order.typeOfPayment}"/></td>
                    <td><c:out value="${orderProduct.order.status}"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}\controlServlet" method="post">
                            <input type="hidden" value="<c:out value="${orderProduct.order.id}"/>" name="id_order">
                            <button class="btn btn-primary" type="submit" value="CheckoutProcess" name="cmd">
                                <fmt:message key="admin.button.two" bundle="${rb}"/>
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
    <form class="form-inline" action="${pageContext.request.contextPath}\controlServlet" method="get">
        <button type="submit" class="btn btn-default" value="SearchUnprocessedOrders" name="cmd"><fmt:message
                key="admin.button.three" bundle="${rb}"/><span
                class="glyphicon glyphicon-search"></span></button>
    </form>
</footer>
</body>
</html>


