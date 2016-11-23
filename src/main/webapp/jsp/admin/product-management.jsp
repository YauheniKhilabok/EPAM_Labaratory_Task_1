<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="admin.product.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="admin.product.description" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="admin.product.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/admin-style.css">
    <link rel="stylesheet" href="../../css/pagination.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="../../js/hide.js" type="text/javascript"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=ShowProductsList"/>
<c:import url="navigation.jsp"></c:import>
<main class="container-fluid">
    <div class="supplier-margin-bottom">
        <input class="btn btn-primary" type="button"
               value="<fmt:message key="admin.product.button.one" bundle="${rb}"/>" onclick="show_product_add_form()">
        <input class="btn btn-primary" type="button"
               value="<fmt:message key="admin.product.button.two" bundle="${rb}"/>"
               onclick="show_product_set_discount_form()">
    </div>
    <div class="div-display-none" id="product_add_form">
        <form class="form-horizontal form-block" action="${pageContext.request.contextPath}\controlServlet"
              method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="deliveryDate"><fmt:message key="admin.product.form.label.one"
                                                                                      bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="date" id="deliveryDate" name="date_of_delivery">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="shelfLife"><fmt:message key="admin.product.form.label.two"
                                                                                   bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="date" id="shelfLife" name="shelf_life">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputNameProduct"><fmt:message
                        key="admin.product.form.label.three" bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" id="inputNameProduct"
                           maxlength="35"
                           placeholder="<fmt:message key="admin.product.placeholder.one" bundle="${rb}"/>"
                           name="name_product" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="selType"><fmt:message key="admin.product.form.label.four"
                                                                                 bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <select class="form-control" id="selType" name="type">
                        <c:forEach var="type" items="${typesList}">
                            <option value="<c:out value="${type.type}"/>"><c:out value="${type.type}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="selBrand"><fmt:message key="admin.product.form.label.five"
                                                                                  bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <select class="form-control" id="selBrand" name="brand">
                        <c:forEach var="producer" items="${producersList}">
                            <option value="<c:out value="${producer.brand}"/>"><c:out
                                    value="${producer.brand}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputNumberOfPackages"><fmt:message
                        key="admin.product.form.label.six" bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="number" id="inputNumberOfPackages"
                           min="1" placeholder="<fmt:message key="admin.product.placeholder.two" bundle="${rb}"/>"
                           name="number_of_packages" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="selUnit"><fmt:message key="admin.product.form.label.seven"
                                                                                 bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <select class="form-control" id="selUnit" name="unit">
                        <option value="Граммы"><fmt:message key="admin.product.seven.one" bundle="${rb}"/></option>
                        <option value="Капсулы"><fmt:message key="admin.product.seven.two" bundle="${rb}"/></option>
                        <option value="Таблетки"><fmt:message key="admin.product.seven.three" bundle="${rb}"/></option>
                        <option value="Миллилитры"><fmt:message key="admin.product.seven.four" bundle="${rb}"/></option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputNumberPerUnit"><fmt:message
                        key="admin.product.form.label.six" bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="number" id="inputNumberPerUnit"
                           min="1" placeholder="<fmt:message key="admin.product.placeholder.three" bundle="${rb}"/>"
                           name="number_per_unit" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="selTaste"><fmt:message key="admin.product.form.label.nine"
                                                                                  bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <select class="form-control" id="selTaste" name="taste">
                        <option value="Без вкуса"><fmt:message key="admin.product.nine.one" bundle="${rb}"/></option>
                        <option value="Клубника"><fmt:message key="admin.product.nine.two" bundle="${rb}"/></option>
                        <option value="Вишня"><fmt:message key="admin.product.nine.three" bundle="${rb}"/></option>
                        <option value="Шоколад"><fmt:message key="admin.product.nine.four" bundle="${rb}"/></option>
                        <option value="Банан"><fmt:message key="admin.product.nine.five" bundle="${rb}"/></option>
                        <option value="Ваниль"><fmt:message key="admin.product.nine.six" bundle="${rb}"/></option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputDiscount"><fmt:message
                        key="admin.product.form.label.ten" bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="number" id="inputDiscount" min="0" max="100"
                           placeholder="<fmt:message key="admin.product.placeholder.four" bundle="${rb}"/>"
                           name="discounts" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputPrice"><fmt:message
                        key="admin.product.form.label.eleven" bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="number" id="inputPrice" min="0"
                           step="0.1" placeholder="<fmt:message key="admin.product.placeholder.five" bundle="${rb}"/>"
                           name="purchase_price" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputDescription"><fmt:message
                        key="admin.product.form.label.twelve" bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="inputDescription" rows="7" maxlength="1000"
                              placeholder="<fmt:message key="admin.product.placeholder.six" bundle="${rb}"/>"
                              name="product_description" required></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputImagePath"><fmt:message
                        key="admin.product.form.label.thirteen" bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" id="inputImagePath"
                           placeholder="<fmt:message key="admin.product.placeholder.seven" bundle="${rb}"/>"
                           name="path_to_image" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <button class="btn btn-primary" type="submit" value="AddProduct" name="cmd"><fmt:message
                            key="admin.products.button.three" bundle="${rb}"/></button>
                    <button class="btn btn-default" type="reset"><fmt:message key="admin.products.button.four"
                                                                              bundle="${rb}"/></button>
                </div>
            </div>
        </form>
    </div>
    <div class="div-display-none" id="product_set_discount_form">
        <form class="form-horizontal form-block" action="${pageContext.request.contextPath}\controlServlet"
              method="get">
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputSetId">ID:</label>
                <div class="col-sm-10">
                    <input class="form-control" type="number" id="inputSetId" min="1"
                           placeholder="<fmt:message key="admin.product.placeholder.eighth" bundle="${rb}"/>"
                           name="id_product" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputSetDiscount"><fmt:message
                        key="admin.product.form.label.fourteen" bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="number" id="inputSetDiscount" min="0" max="100"
                           placeholder="<fmt:message key="admin.product.placeholder.nine" bundle="${rb}"/>"
                           name="discounts" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <button class="btn btn-default" type="submit" value="SetDiscount" name="cmd"><fmt:message
                            key="admin.products.button.five" bundle="${rb}"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="button-margin-bottom">
        <div class="div-pos">
            <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                <input type="hidden" value="up" name="sorting">
                <button class="btn btn-default" type="submit" value="SortProductsByShelfLife" name="cmd">
                    <fmt:message key="admin.products.button.six" bundle="${rb}"/>&nbsp;<span
                        class="glyphicon glyphicon-chevron-up"></span></button>
            </form>
        </div>
        <div>
            <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                <input type="hidden" value="down" name="sorting">
                <button class="btn btn-default" type="submit" value="SortProductsByShelfLife" name="cmd">
                    <fmt:message key="admin.products.button.six" bundle="${rb}"/>&nbsp;<span
                        class="glyphicon glyphicon-chevron-down"></span></button>
            </form>
        </div>
    </div>
    <div class="main-user-management-style">
        <div class="error-message-text-style">${productError}</div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="admin.products.table.label.one" bundle="${rb}"/></th>
                <th><fmt:message key="admin.products.table.label.two" bundle="${rb}"/></th>
                <th><fmt:message key="admin.products.table.label.three" bundle="${rb}"/></th>
                <th><fmt:message key="admin.products.table.label.four" bundle="${rb}"/></th>
                <th><fmt:message key="admin.products.table.label.five" bundle="${rb}"/></th>
                <th><fmt:message key="admin.products.table.label.six" bundle="${rb}"/></th>
                <th><fmt:message key="admin.products.table.label.seven" bundle="${rb}"/></th>
                <th><fmt:message key="admin.products.table.label.eighth" bundle="${rb}"/></th>
                <th><fmt:message key="admin.products.table.label.nine" bundle="${rb}"/></th>
                <th><fmt:message key="admin.products.table.label.ten" bundle="${rb}"/></th>
                <th><fmt:message key="admin.products.table.label.eleven" bundle="${rb}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${productsList}">
                <tr>
                    <td><c:out value="${product.id}"/></td>
                    <td><c:out value="${product.shelfLife}"/></td>
                    <td><c:out value="${product.nameProduct}"/></td>
                    <td><c:out value="${product.type.type}"/></td>
                    <td><c:out value="${product.producer.brand}"/></td>
                    <td><c:out value="${product.taste}"/></td>
                    <td><c:out value="${product.numberOfPackages}"/></td>
                    <td><c:out value="${product.discounts}"/></td>
                    <td><c:out value="${product.purchasePrice}"/></td>
                    <td><c:out value="${product.status}"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                            <input type="hidden" value="<c:out value="${product.id}"/>" name="id_product">
                            <button class="btn btn-primary" type="submit" value="DeleteProduct" name="cmd"><fmt:message
                                    key="admin.products.button.seven" bundle="${rb}"/>
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                            <input type="hidden" value="<c:out value="${product.id}"/>" name="id_product">
                            <button class="btn btn-primary" type="submit" value="LoadProductData" name="cmd">
                                <fmt:message key="admin.products.button.eighth" bundle="${rb}"/>
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
        <input type="text" class="form-control input-border"
               placeholder="<fmt:message key="admin.product.placeholder.ten" bundle="${rb}"/>" name="name_product"
               required>
        <button type="submit" class="btn btn-default" value="SearchProductsForAdmin" name="cmd"><fmt:message
                key="admin.products.button.nine" bundle="${rb}"/> <span
                class="glyphicon glyphicon-search"></span></button>
    </form>
</footer>
</body>
</html>


