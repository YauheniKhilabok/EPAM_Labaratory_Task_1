<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="admin.types.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="admin.types.description" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="admin.types.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/admin-style.css">
    <link rel="stylesheet" href="../../css/pagination.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="../../js/hide.js" type="text/javascript"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=ShowTypesList"/>
<c:import url="navigation.jsp"></c:import>
<main class="container">
    <div class="supplier-margin-bottom">
        <input class="btn btn-primary" type="submit" value="<fmt:message key="admin.types.button.one" bundle="${rb}"/>"
               onclick="show_type_add_form()">
        <input class="btn btn-primary" type="button" value="<fmt:message key="admin.types.button.two" bundle="${rb}"/>"
               onclick="show_type_change_form()">
    </div>
    <div class="div-display-none" id="type_add_form">
        <form class="form-horizontal form-block" action="${pageContext.request.contextPath}\controlServlet"
              method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputType"><fmt:message key="admin.types.label.one"
                                                                                   bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" id="inputType"
                           placeholder="<fmt:message key="admin.types.placeholder.one" bundle="${rb}"/>"
                           name="type"
                           maxlength="45" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputDescription"><fmt:message key="admin.types.label.two"
                                                                                          bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <textarea class="form-control textarea-width" id="inputDescription" rows="7" maxlength="1000"
                              name="type_description"
                              placeholder="<fmt:message key="admin.types.placeholder.two" bundle="${rb}"/>"
                              required></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <button class="btn btn-primary" type="submit" value="AddType" name="cmd"><fmt:message
                            key="admin.types.button.three" bundle="${rb}"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="div-display-none" id="type_change_form">
        <form class="form-horizontal form-block" action="${pageContext.request.contextPath}\controlServlet"
              method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputId">ID:</label>
                <div class="col-sm-10">
                    <input class="form-control" type="number" id="inputId" name="id_type" min="1"
                           max="${maxTypeId}"
                           placeholder="<fmt:message key="admin.types.placeholder.three" bundle="${rb}"/>"
                           required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputNewType"><fmt:message key="admin.types.label.one"
                                                                                      bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" id="inputNewType"
                           placeholder="<fmt:message key="admin.types.placeholder.four" bundle="${rb}"/>" name="type"
                           maxlength="45" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="inputNewDescription"><fmt:message key="admin.types.label.two"
                                                                                             bundle="${rb}"/></label>
                <div class="col-sm-10">
                    <textarea class="form-control textarea-width" id="inputNewDescription" rows="7" maxlength="1000"
                              name="type_description"
                              placeholder="<fmt:message key="admin.types.placeholder.five" bundle="${rb}"/>"
                              required></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10">
                    <button class="btn btn-primary" type="submit" value="ChangeType" name="cmd"><fmt:message
                            key="admin.types.button.four" bundle="${rb}"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="main-user-management-style">
        <div class="error-message-text-style"><p>${errorAddType}</p></div>
        <div class="error-message-text-style"><p>${errorProcessingType}</p></div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="admin.types.table.label.one" bundle="${rb}"/></th>
                <th><fmt:message key="admin.types.table.label.two" bundle="${rb}"/></th>
                <th><fmt:message key="admin.types.table.label.three" bundle="${rb}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="type" items="${typesList}">
                <tr>
                    <td><c:out value="${type.id}"/></td>
                    <td><c:out value="${type.type}"/></td>
                    <td><c:out value="${type.typeDescription}"/></td>
                    <td>
                        <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                            <input type="hidden" value="<c:out value="${type.id}"/>" name="id_type">
                            <button class="btn btn-primary" type="submit" value="DeleteType" name="cmd"><fmt:message
                                    key="admin.types.button.five" bundle="${rb}"/>
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


