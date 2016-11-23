<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="checkout.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="checkout.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="checkout.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="checkout.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/user-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="../../js/hide.js" type="text/javascript"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=ShowTempProductsOrder"/>
<c:import url="navigation.jsp"></c:import>
<main class="container">
    <div class="main-basket-style">
        <div class="main-basket-margin">
            <c:if test="${isEmptyTempList eq true}">
                <c:if test="${isSuccessAdd eq null or isSuccessAdd eq false}">
                    <div class="alert alert-warning"><b>${warningShowTempProductsOrderMessage}</b></div>
                </c:if>
                <c:if test="${isSuccessAdd eq true}">
                    <div class="success-message-text-style"><b>${successCheckoutMessage}</b></div>
                </c:if>
            </c:if>
            <c:if test="${isEmptyTempList eq false}">
                <h3 class="header-pos"><fmt:message key="checkout.header.main" bundle="${rb}"/></h3>
                <div class="margin-top">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th><fmt:message key="checkout.table.one" bundle="${rb}"/></th>
                            <th><fmt:message key="checkout.table.two" bundle="${rb}"/></th>
                            <th><fmt:message key="checkout.table.three" bundle="${rb}"/></th>
                            <th><fmt:message key="checkout.table.four" bundle="${rb}"/></th>
                            <th><fmt:message key="checkout.table.five" bundle="${rb}"/></th>
                            <th><fmt:message key="checkout.table.six" bundle="${rb}"/></th>
                            <th><fmt:message key="checkout.table.seven" bundle="${rb}"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="orderProduct" items="${tempOrderProductList}">
                            <tr>
                                <td><c:out value="${orderProduct.product.nameProduct}"/></td>
                                <td><c:out value="${orderProduct.product.type.type}"/></td>
                                <td><c:out value="${orderProduct.product.producer.brand}"/></td>
                                <td><c:out value="${orderProduct.product.taste}"/></td>
                                <td><c:out value="${orderProduct.numberOfPackages}"/></td>
                                <td><c:out value="${orderProduct.cost}"/></td>
                                <td>
                                    <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                        <input type="hidden" value="<c:out value="${orderProduct.product.id}"/>"
                                               name="id_product">
                                        <button class="btn btn-success" type="submit" value="DeleteTempProductsOrder"
                                                name="cmd">
                                            <fmt:message key="checkout.button.one" bundle="${rb}"/> <span
                                                class="glyphicon glyphicon-trash"></span></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div>
                        <input class="btn btn-success" type="submit"
                               value="<fmt:message key="checkout.button.two" bundle="${rb}"/>"
                               onclick="show_checkout_form()">
                    </div>
                    <div class="order-form-size div-display-none" id="checkout">
                        <form action="${pageContext.request.contextPath}\controlServlet" method="post">
                            <div class="form-group">
                                <label for="sel1"><fmt:message key="checkout.form.select.label.one"
                                                               bundle="${rb}"/></label>
                                <select class="form-control" id="sel1" name="delivery_condition">
                                    <option value="Обычная доставка"><fmt:message key="checkout.form.input.one"
                                                                                  bundle="${rb}"/></option>
                                    <option value="Без доставки"><fmt:message key="checkout.form.input.two"
                                                                              bundle="${rb}"/></option>
                                    <option value="Срочная доставка"><fmt:message key="checkout.form.input.three"
                                                                                  bundle="${rb}"/></option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="sel2"><fmt:message key="checkout.form.select.label.two"
                                                               bundle="${rb}"/></label>
                                <select class="form-control" id="sel2" name="type_of_payment">
                                    <option value="Наличными"><fmt:message key="checkout.form.input.four"
                                                                           bundle="${rb}"/></option>
                                    <option value="Банковской картой"><fmt:message key="checkout.form.input.five"
                                                                                   bundle="${rb}"/></option>
                                </select>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-success" type="submit" value="Checkout" name="cmd"><fmt:message
                                        key="checkout.button.three" bundle="${rb}"/>
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


