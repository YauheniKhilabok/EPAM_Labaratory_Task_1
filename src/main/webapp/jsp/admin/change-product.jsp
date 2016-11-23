<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="admin.change.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="admin.change.description" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="admin.change.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/admin-style.css">
    <link rel="stylesheet" href="../../css/pagination.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="../../js/hide.js" type="text/javascript"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?id_product=${productId}&cmd=LoadProductData"/>
<c:import url="navigation.jsp"></c:import>
<main class="container">
    <c:set var="product" scope="page" value="${productData}"/>
    <form class="form-horizontal form-block" action="${pageContext.request.contextPath}\controlServlet"
          method="post">
        <div class="error-message-text-style">${productError}</div>
        <div class="success-message-text-style">${productSuccess}</div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="inputId">ID:</label>
            <div class="col-sm-10">
                <input class="form-control" type="number" value="<c:out value="${product.id}"/>"
                       min="<c:out value="${product.id}"/>" max="<c:out value="${product.id}"/>" id="inputId"
                       name="id_product">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="deliveryDateNew"><fmt:message key="admin.change.label.one"
                                                                                     bundle="${rb}"/></label>
            <div class="col-sm-10">
                <input class="form-control" type="date" value="<c:out value="${product.dateOfDelivery}"/>"
                       id="deliveryDateNew" name="date_of_delivery">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="shelfLifeNew"><fmt:message key="admin.change.label.two"
                                                                                  bundle="${rb}"/></label>
            <div class="col-sm-10">
                <input class="form-control" type="date"
                       value="<c:out value="${product.shelfLife}"/>" id="shelfLifeNew" name="shelf_life">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="inputNameProductNew"><fmt:message key="admin.change.label.three"
                                                                                         bundle="${rb}"/></label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="inputNameProductNew"
                       maxlength="35"
                       value="<c:out value="${product.nameProduct}"/>" name="name_product" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="selTypeNew"><fmt:message key="admin.change.label.four"
                                                                                bundle="${rb}"/></label>
            <div class="col-sm-10">
                <select class="form-control" id="selTypeNew"
                        name="type">
                    <c:forEach var="type" items="${typesList}">
                        <c:set var="firstType" scope="page" value="${product.type.type}"/>
                        <c:set var="secondType" scope="page" value="${type.type}"/>
                        <c:if test="${firstType eq secondType}">
                            <option value="<c:out value="${type.type}"/>" selected>
                                <c:out value="${type.type}"/>
                            </option>
                        </c:if>
                        <c:if test="${firstType ne secondType}">
                            <option value="<c:out value="${type.type}"/>">
                                <c:out value="${type.type}"/>
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="selBrandNew"><fmt:message key="admin.change.label.five"
                                                                                 bundle="${rb}"/></label>
            <div class="col-sm-10">
                <select class="form-control" value="<c:out value="${product.producer.brand}"/>" id="selBrandNew"
                        name="brand">
                    <c:forEach var="producer" items="${producersList}">
                        <c:set var="firstProducer" scope="page" value="${product.producer.brand}"/>
                        <c:set var="secondProducer" scope="page" value="${producer.brand}"/>
                        <c:if test="${firstProducer eq secondProducer}">
                            <option value="<c:out value="${producer.brand}"/>" selected>
                                <c:out value="${producer.brand}"/>
                            </option>
                        </c:if>
                        <c:if test="${firstProducer ne secondProducer}">
                            <option value="<c:out value="${producer.brand}"/>">
                                <c:out value="${producer.brand}"/>
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="inputNumberOfPackagesNew"><fmt:message
                    key="admin.change.label.six" bundle="${rb}"/></label>
            <div class="col-sm-10">
                <input class="form-control" type="number" id="inputNumberOfPackagesNew"
                       min="1" value="<c:out value="${product.numberOfPackages}"/>"
                       name="number_of_packages" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="selUnitNew"><fmt:message key="admin.change.label.seven"
                                                                                bundle="${rb}"/></label>
            <div class="col-sm-10">
                <select class="form-control" value="<c:out value="${product.unit}"/>" id="selUnitNew" name="unit">
                    <c:set var="unit" scope="page" value="${product.unit}"/>
                    <c:set var="firstUnit" scope="page" value="Граммы"/>
                    <c:set var="secondUnit" scope="page" value="Капсулы"/>
                    <c:set var="thirdUnit" scope="page" value="Таблетки"/>
                    <c:set var="fourthUnit" scope="page" value="Миллилитры"/>
                    <c:if test="${unit eq firstUnit}">
                        <option value="Граммы" selected><fmt:message key="admin.change.seven.first"
                                                                     bundle="${rb}"/></option>
                        <option value="Капсулы"><fmt:message key="admin.change.seven.second" bundle="${rb}"/></option>
                        <option value="Таблетки"><fmt:message key="admin.change.seven.third" bundle="${rb}"/></option>
                        <option value="Миллилитры"><fmt:message key="admin.change.seven.fourth"
                                                                bundle="${rb}"/></option>
                    </c:if>
                    <c:if test="${unit eq secondUnit}">
                        <option value="Граммы"><fmt:message key="admin.change.seven.first" bundle="${rb}"/></option>
                        <option value="Капсулы" selected><fmt:message key="admin.change.seven.second"
                                                                      bundle="${rb}"/></option>
                        <option value="Таблетки"><fmt:message key="admin.change.seven.third" bundle="${rb}"/></option>
                        <option value="Миллилитры"><fmt:message key="admin.change.seven.fourth"
                                                                bundle="${rb}"/></option>
                    </c:if>
                    <c:if test="${unit eq thirdUnit}">
                        <option value="Граммы"><fmt:message key="admin.change.seven.first" bundle="${rb}"/></option>
                        <option value="Капсулы"><fmt:message key="admin.change.seven.second" bundle="${rb}"/></option>
                        <option value="Таблетки" selected><fmt:message key="admin.change.seven.third"
                                                                       bundle="${rb}"/></option>
                        <option value="Миллилитры"><fmt:message key="admin.change.seven.fourth"
                                                                bundle="${rb}"/></option>
                    </c:if>
                    <c:if test="${unit eq fourthUnit}">
                        <option value="Граммы"><fmt:message key="admin.change.seven.first" bundle="${rb}"/></option>
                        <option value="Капсулы"><fmt:message key="admin.change.seven.second" bundle="${rb}"/></option>
                        <option value="Таблетки"><fmt:message key="admin.change.seven.third" bundle="${rb}"/></option>
                        <option value="Миллилитры" selected><fmt:message key="admin.change.seven.fourth"
                                                                         bundle="${rb}"/></option>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="inputNumberPerUnitNew"><fmt:message key="admin.change.label.six"
                                                                                           bundle="${rb}"/></label>
            <div class="col-sm-10">
                <input class="form-control" type="number" id="inputNumberPerUnitNew"
                       min="1" value="<c:out value="${product.numberPerUnit}"/>" name="number_per_unit" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="selTasteNew"><fmt:message key="admin.change.label.eighth"
                                                                                 bundle="${rb}"/></label>
            <div class="col-sm-10">
                <select class="form-control" value="<c:out value="${product.taste}"/>" id="selTasteNew" name="taste">
                    <c:set var="taste" scope="page" value="${product.taste}"/>
                    <c:set var="firstTaste" scope="page" value="Без вкуса"/>
                    <c:set var="secondTaste" scope="page" value="Клубника"/>
                    <c:set var="thirdTaste" scope="page" value="Вишня"/>
                    <c:set var="fourthTaste" scope="page" value="Шоколад"/>
                    <c:set var="fifthTaste" scope="page" value="Банан"/>
                    <c:set var="sixthTaste" scope="page" value="Ваниль"/>
                    <c:if test="${taste eq firstTaste}">
                        <option value="Без вкуса" selected><fmt:message key="admin.change.eighth.first"
                                                                        bundle="${rb}"/></option>
                        <option value="Клубника"><fmt:message key="admin.change.eighth.second" bundle="${rb}"/></option>
                        <option value="Вишня"><fmt:message key="admin.change.eighth.third" bundle="${rb}"/></option>
                        <option value="Шоколад"><fmt:message key="admin.change.eighth.fourth" bundle="${rb}"/></option>
                        <option value="Банан"><fmt:message key="admin.change.eighth.fifth" bundle="${rb}"/></option>
                        <option value="Ваниль"><fmt:message key="admin.change.eighth.sixth" bundle="${rb}"/></option>
                    </c:if>
                    <c:if test="${taste eq secondTaste}">
                        <option value="Без вкуса"><fmt:message key="admin.change.eighth.first" bundle="${rb}"/></option>
                        <option value="Клубника" selected><fmt:message key="admin.change.eighth.second"
                                                                       bundle="${rb}"/></option>
                        <option value="Вишня"><fmt:message key="admin.change.eighth.third" bundle="${rb}"/></option>
                        <option value="Шоколад"><fmt:message key="admin.change.eighth.fourth" bundle="${rb}"/></option>
                        <option value="Банан"><fmt:message key="admin.change.eighth.fifth" bundle="${rb}"/></option>
                        <option value="Ваниль"><fmt:message key="admin.change.eighth.sixth" bundle="${rb}"/></option>
                    </c:if>
                    <c:if test="${taste eq thirdTaste}">
                        <option value="Без вкуса"><fmt:message key="admin.change.eighth.first" bundle="${rb}"/></option>
                        <option value="Клубника"><fmt:message key="admin.change.eighth.second" bundle="${rb}"/></option>
                        <option value="Вишня" selected><fmt:message key="admin.change.eighth.third"
                                                                    bundle="${rb}"/></option>
                        <option value="Шоколад"><fmt:message key="admin.change.eighth.fourth" bundle="${rb}"/></option>
                        <option value="Банан"><fmt:message key="admin.change.eighth.fifth" bundle="${rb}"/></option>
                        <option value="Ваниль"><fmt:message key="admin.change.eighth.sixth" bundle="${rb}"/></option>
                    </c:if>
                    <c:if test="${taste eq fourthTaste}">
                        <option value="Без вкуса"><fmt:message key="admin.change.eighth.first" bundle="${rb}"/></option>
                        <option value="Клубника"><fmt:message key="admin.change.eighth.second" bundle="${rb}"/></option>
                        <option value="Вишня"><fmt:message key="admin.change.eighth.third" bundle="${rb}"/></option>
                        <option value="Шоколад" selected><fmt:message key="admin.change.eighth.fourth"
                                                                      bundle="${rb}"/></option>
                        <option value="Банан"><fmt:message key="admin.change.eighth.fifth" bundle="${rb}"/></option>
                        <option value="Ваниль"><fmt:message key="admin.change.eighth.sixth" bundle="${rb}"/></option>
                    </c:if>
                    <c:if test="${taste eq fifthTaste}">
                        <option value="Без вкуса"><fmt:message key="admin.change.eighth.first" bundle="${rb}"/></option>
                        <option value="Клубника"><fmt:message key="admin.change.eighth.second" bundle="${rb}"/></option>
                        <option value="Вишня"><fmt:message key="admin.change.eighth.third" bundle="${rb}"/></option>
                        <option value="Шоколад"><fmt:message key="admin.change.eighth.fourth" bundle="${rb}"/></option>
                        <option value="Банан" selected><fmt:message key="admin.change.eighth.fifth"
                                                                    bundle="${rb}"/></option>
                        <option value="Ваниль"><fmt:message key="admin.change.eighth.sixth" bundle="${rb}"/></option>
                    </c:if>
                    <c:if test="${taste eq sixthTaste}">
                        <option value="Без вкуса"><fmt:message key="admin.change.eighth.first" bundle="${rb}"/></option>
                        <option value="Клубника"><fmt:message key="admin.change.eighth.second" bundle="${rb}"/></option>
                        <option value="Вишня"><fmt:message key="admin.change.eighth.third" bundle="${rb}"/></option>
                        <option value="Шоколад"><fmt:message key="admin.change.eighth.fourth" bundle="${rb}"/></option>
                        <option value="Банан"><fmt:message key="admin.change.eighth.fifth" bundle="${rb}"/></option>
                        <option value="Ваниль" selected><fmt:message key="admin.change.eighth.sixth"
                                                                     bundle="${rb}"/></option>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="inputDiscountNew"><fmt:message key="admin.change.label.nine"
                                                                                      bundle="${rb}"/></label>
            <div class="col-sm-10">
                <input class="form-control" type="number" id="inputDiscountNew" min="0" max="100"
                       value="<c:out value="${product.discounts}"/>" name="discounts" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="inputPriceNew"><fmt:message key="admin.change.label.ten"
                                                                                   bundle="${rb}"/></label>
            <div class="col-sm-10">
                <input class="form-control" type="number" id="inputPriceNew" min="0"
                       step="0.1" value="<c:out value="${product.purchasePrice}"/>"
                       name="purchase_price" required>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="inputDescriptionNew"><fmt:message key="admin.change.label.eleven"
                                                                                         bundle="${rb}"/></label>
            <div class="col-sm-10">
                    <textarea class="form-control" id="inputDescriptionNew" rows="7" maxlength="1000"
                              name="product_description" required><c:out
                            value="${product.productDescription}"/> </textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="inputImagePathNew"><fmt:message key="admin.change.label.twelve"
                                                                                       bundle="${rb}"/></label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="inputImagePathNew"
                       value="<c:out value="${product.pathToImage}"/>"
                       name="path_to_image" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-10">
                <button class="btn btn-primary" type="submit" value="ChangeProduct" name="cmd"><fmt:message
                        key="admin.change.button.one" bundle="${rb}"/></button>
                <button class="btn btn-primary" type="reset"><fmt:message key="admin.change.button.two"
                                                                          bundle="${rb}"/></button>
            </div>
        </div>
    </form>
</main>
<br><br>
<footer class="container-fluid text-center main-footer">
</footer>
</body>
</html>


