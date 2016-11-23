<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="basket.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="basket.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="basket.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="basket.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/user-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="../../js/hide.js" type="text/javascript"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=PrintOrdersForUser"/>
<c:import url="navigation.jsp"></c:import>
<main class="container">
    <div class="main-basket-style">
        <div class="main-basket-margin">
            <c:if test="${isEmptyList eq true}">
                <div class="alert alert-warning"><b>${warningPrintOrdersMessage}</b></div>
            </c:if>
            <c:if test="${isEmptyList eq false}">
                <h3 class="header-pos"><fmt:message key="basket.header.main" bundle="${rb}"/></h3>
                <div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th><fmt:message key="basket.label.one" bundle="${rb}"/></th>
                            <th><fmt:message key="basket.label.two" bundle="${rb}"/></th>
                            <th><fmt:message key="basket.label.three" bundle="${rb}"/></th>
                            <th><fmt:message key="basket.label.four" bundle="${rb}"/></th>
                            <th><fmt:message key="basket.label.five" bundle="${rb}"/></th>
                            <th><fmt:message key="basket.label.six" bundle="${rb}"/></th>
                            <th><fmt:message key="basket.label.seven" bundle="${rb}"/></th>
                        </tr>
                        </thead>
                        <c:forEach var="order" items="${ordersList}">
                            <tbody>
                            <tr>
                                <td><c:out value="${order.id}"/></td>
                                <td><c:out value="${order.leadTime}"/></td>
                                <td><c:out value="${order.deliveryCondition}"/></td>
                                <td><c:out value="${order.status}"/></td>
                                <td><c:out value="${order.typeOfPayment}"/></td>
                                <td><c:out value="${order.totalCost}"/></td>
                                <td>
                                    <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                        <input type="hidden" value="<c:out value="${order.id}"/>" name="id_order">
                                        <button class="btn btn-primary" type="submit" value="DeleteOrder" name="cmd">
                                            <fmt:message key="basket.button.one" bundle="${rb}"/>
                                            <span class="glyphicon glyphicon-trash"></span></button>
                                    </form>
                                </td>
                                <td>
                                    <button class="btn btn-primary" onclick="show_change_order_form()"><fmt:message
                                            key="basket.button.two" bundle="${rb}"/><span
                                            class="glyphicon glyphicon-edit"></span></button>

                                </td>
                            </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                    <div class="error-message-text-style">${errorChangeOrderMessage}</div>
                    <div class="order-form-size div-display-none" id="change_order">
                        <form action="${pageContext.request.contextPath}\controlServlet" method="post">
                            <div class="form-group">
                                <label for="inputId"><fmt:message key="basket.form.label.one" bundle="${rb}"/></label>
                                <input class="form-control" type="number" id="inputId" name="id_order" autofocus
                                       required>
                            </div>
                            <div class="form-group">
                                <label for="sel1"><fmt:message key="basket.form.label.two" bundle="${rb}"/></label>
                                <select class="form-control" id="sel1" name="delivery_condition">
                                    <option value="Обычная доставка"><fmt:message key="basket.form.input.one"
                                                                                  bundle="${rb}"/></option>
                                    <option value="Без доставки"><fmt:message key="basket.form.input.two"
                                                                              bundle="${rb}"/></option>
                                    <option value="Срочная доставка"><fmt:message key="basket.form.input.three"
                                                                                  bundle="${rb}"/></option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="sel2"><fmt:message key="basket.form.label.three" bundle="${rb}"/></label>
                                <select class="form-control" id="sel2" name="type_of_payment">
                                    <option value="Наличными"><fmt:message key="basket.form.input.four"
                                                                           bundle="${rb}"/></option>
                                    <option value="Банковской картой"><fmt:message key="basket.form.input.five"
                                                                                   bundle="${rb}"/></option>
                                </select>
                            </div>
                            <div class="form-group">
                                <button input class="btn btn-success" type="submit" value="ChangeOrder" name="cmd">
                                    <fmt:message key="basket.button.three" bundle="${rb}"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</main>
<br><br>
<c:import url="footer.jsp"></c:import>
</body>
</html>


