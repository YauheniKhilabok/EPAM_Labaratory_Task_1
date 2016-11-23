<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="admin.producers.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="admin.producers.description" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="admin.producers.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/admin-style.css">
    <link rel="stylesheet" href="../../css/pagination.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="../../js/hide.js" type="text/javascript"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=ShowProducersList"/>
<c:import url="navigation.jsp"></c:import>
<main class="container">
    <div class="supplier-margin-bottom">
        <input class="btn btn-primary" type="submit"
               value="<fmt:message key="admin.producers.button.one" bundle="${rb}"/>"
               onclick="show_supplier_add_form()">
        <input class="btn btn-primary" type="button"
               value="<fmt:message key="admin.producers.button.two" bundle="${rb}"/>"
               onclick="show_supplier_change_form()">
    </div>
    <div class="div-display-none" id="supplier_add_form">
        <form class="form-horizontal form-block" action="${pageContext.request.contextPath}\controlServlet"
              method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="selRegion"><fmt:message key="admin.producers.form.label.one"
                                                                                   bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <select class="form-control" id="selRegion" name="production_region">
                        <option value="Америка"><fmt:message key="admin.producers.one.one" bundle="${rb}"/></option>
                        <option value="Европа"><fmt:message key="admin.producers.one.two" bundle="${rb}"/></option>
                        <option value="Россия"><fmt:message key="admin.producers.one.three" bundle="${rb}"/></option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputBrand"><fmt:message key="admin.producers.form.label.two"
                                                                                    bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" id="inputBrand"
                           placeholder="<fmt:message key="admin.producers.placeholder.one" bundle="${rb}"/>"
                           name="brand"
                           maxlength="35" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <button class="btn btn-primary" type="submit" value="AddProducer" name="cmd"><fmt:message
                            key="admin.producers.button.three" bundle="${rb}"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="div-display-none" id="supplier_change_form">
        <form class="form-horizontal form-block" action="${pageContext.request.contextPath}\controlServlet"
              method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputId">ID:</label>
                <div class="col-sm-10">
                    <input class="form-control" type="number" id="inputId" name="id_producer" min="1"
                           max="${maxProducerId}"
                           placeholder="<fmt:message key="admin.producers.placeholder.two" bundle="${rb}"/>"
                           required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="sel2Region"><fmt:message key="admin.producers.form.label.one"
                                                                                    bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <select class="form-control" id="sel2Region" name="production_region">
                        <option value="Америка"><fmt:message key="admin.producers.one.one" bundle="${rb}"/></option>
                        <option value="Европа"><fmt:message key="admin.producers.one.two" bundle="${rb}"/></option>
                        <option value="Россия"><fmt:message key="admin.producers.one.three" bundle="${rb}"/></option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="input2Brand"><fmt:message
                        key="admin.producers.form.label.two" bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" id="input2Brand"
                           placeholder="<fmt:message key="admin.producers.placeholder.three" bundle="${rb}"/>"
                           maxlength="35" name="brand" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <button class="btn btn-primary" type="submit" value="ChangeProducer" name="cmd"><fmt:message
                            key="admin.producers.button.four" bundle="${rb}"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="main-user-management-style">
        <div class="error-message-text-style"><p>${errorAddProducer}</p></div>
        <div class="error-message-text-style"><p>${errorProcessingProducer}</p></div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="admin.producers.table.one" bundle="${rb}"/></th>
                <th><fmt:message key="admin.producers.table.two" bundle="${rb}"/></th>
                <th><fmt:message key="admin.producers.table.three" bundle="${rb}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="producer" items="${producersList}">
                <tr>
                    <td><c:out value="${producer.id}"/></td>
                    <td><c:out value="${producer.productionRegion}"/></td>
                    <td><c:out value="${producer.brand}"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                            <input type="hidden" value="<c:out value="${producer.id}"/>" name="id_producer">
                            <button class="btn btn-primary" type="submit" value="DeleteProducer" name="cmd"><fmt:message
                                    key="admin.producers.button.five" bundle="${rb}"/>
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
</footer>
</body>
</html>


