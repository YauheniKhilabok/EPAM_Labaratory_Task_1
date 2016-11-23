<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="description.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="description.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="description.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="description.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/user-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?id_product=${productId}&cmd=GetMoreInformationAboutProduct"/>
<c:import url="navigation.jsp"></c:import>
<main class="container">
    <div class="main-order-style">
        <div class="main-order-margin">
            <h3><fmt:message key="description.header.main" bundle="${rb}"/></h3>
            <c:set var="product" scope="page" value="${productData}"/>
            <img src="<c:out value="${product.pathToImage}"/>" class="img-responsive"
                 alt="<fmt:message key="description.alt" bundle="${rb}"/>">
            <p><b><fmt:message key="description.label.one" bundle="${rb}"/></b> <c:out value="${product.nameProduct}"/>
            </p>
            <p><b><fmt:message key="description.label.two" bundle="${rb}"/></b> <c:out value="${product.type.type}"/>
            </p>
            <p><b><fmt:message key="description.label.three" bundle="${rb}"/></b> <c:out
                    value="${product.producer.productionRegion}"/></p>
            <p><b><fmt:message key="description.label.four" bundle="${rb}"/></b> <c:out
                    value="${product.producer.brand}"/></p>
            <p><b><fmt:message key="description.label.five" bundle="${rb}"/></b> <c:out
                    value="${product.dateOfDelivery}"/></p>
            <p><b><fmt:message key="description.label.six" bundle="${rb}"/></b> <c:out value="${product.shelfLife}"/>
            </p>
            <p><b><fmt:message key="description.label.seven" bundle="${rb}"/></b> <c:out
                    value="${product.numberOfPackages}"/> <fmt:message key="description.label.eighth" bundle="${rb}"/>
            </p>
            <p><b><fmt:message key="description.label.nine" bundle="${rb}"/></b> <c:out value="${product.unit}"/></p>
            <p><b><fmt:message key="description.label.ten" bundle="${rb}"/></b> <c:out
                    value="${product.numberPerUnit}"/></p>
            <p><b><fmt:message key="description.label.eleven" bundle="${rb}"/></b> <c:out value="${product.taste}"/></p>
            <p><b><fmt:message key="description.label.twelve" bundle="${rb}"/></b> <c:out value="${product.discounts}"/>%
            </p>
            <p><b><fmt:message key="description.label.thirteen" bundle="${rb}"/></b> <c:out
                    value="${product.purchasePrice}"/> <fmt:message key="description.label.fourteen" bundle="${rb}"/>
            </p>
            <p><b><fmt:message key="description.label.fifteen" bundle="${rb}"/></b> <c:out value="${product.status}"/>
            </p>
            <p><b><fmt:message key="description.label.sixteen" bundle="${rb}"/></b><c:out
                    value="${product.productDescription}"/></p>
        </div>
        <div class="success-message-text-style"><b>${successAddProductToListMessage}</b></div>
        <form class="order-form-margin" action="${pageContext.request.contextPath}\controlServlet" method="post">
            <input type="hidden" value="<c:out value="${product.id}"/>" name="id_product">
            <label for="number-packages"><fmt:message key="description.label.seventeen" bundle="${rb}"/></label>
            <input class="btn btn-success" type="number" id="number-packages"
                   name="number_of_packages" value="1"
                   min="1" max="<c:out value="${product.numberOfPackages}"/>" required>
            <button class="btn btn-success" type="submit" value="AddProductToList" name="cmd"><fmt:message
                    key="description.button" bundle="${rb}"/></button>
        </form>
    </div>
</main>
<br><br>
<c:import url="footer.jsp"></c:import>
</body>
</html>


