<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="change.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="change.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="change.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="change.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/user-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=LoadUserInformation"/>
<c:import url="navigation.jsp"></c:import>
<main class="container">
    <div class="main-order-style">
        <div class="main-order-margin">
            <h3 class="header-pos"><fmt:message key="change.header.main" bundle="${rb}"/></h3>
            <c:set var="user" scope="page" value="${userData}"/>
            <form class="form-horizontal change-padding" action="${pageContext.request.contextPath}\controlServlet"
                  method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="inputName"><fmt:message key="change.form.label.one"
                                                                                       bundle="${rb}"/></label>
                    <div class="col-sm-10">
                        <input class="form-control" type="text" id="inputName" value="<c:out value="${user.nameUser}"/>"
                               name="name_user" pattern="^[A-Za-zА-Яа-яЁё\s]+$" autofocus required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="inputEmail">Email:</label>
                    <div class="col-sm-10">
                        <input class="form-control" type="email" id="inputEmail" value="<c:out value="${user.email}"/>"
                               name="email"
                               pattern="^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$"
                               required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="inputPhone"><fmt:message key="change.form.label.two"
                                                                                        bundle="${rb}"/></label>
                    <div class="col-sm-10">
                        <input class="form-control" type="tel" id="inputPhone" value="<c:out value="${user.phone}"/>"
                               name="phone" pattern="^375-[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}$" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="inputAddress"><fmt:message key="change.form.label.three"
                                                                                          bundle="${rb}"/></label>
                    <div class="col-sm-10">
          <textarea class="form-control" rows="5" id="inputAddress"
                    name="address" maxlength="255" required><c:out value="${user.address}"/></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="inputPassword"><fmt:message key="change.form.label.four"
                                                                                           bundle="${rb}"/></label>
                    <div class="col-sm-10">
                        <input type="hidden" value="<c:out value="${user.password}"/>" name="oldPassword">
                        <input class="form-control" type="password" id="inputPassword"
                               minlength="6" maxlength="35" name="newPassword"
                               pattern="^(?=.*\d)(?=.*[a-zа-яё])(?=.*[A-ZА-ЯЁ])(?!.*\s).*$">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-success" type="submit" value="ChangeUserData" name="cmd"><fmt:message
                                key="change.button.one" bundle="${rb}"/> <span
                                class="glyphicon glyphicon-edit"></span></button>
                        <input class="btn btn-success" type="reset"
                               value="<fmt:message key="change.button.two" bundle="${rb}"/>">
                    </div>
                </div>
            </form>
            <div class="alert alert-danger"><span>${errorChangeUserData}</span></div>
            <h4><fmt:message key="change.header.big" bundle="${rb}"/></h4>
            <form class="form-horizontal change-padding" action="${pageContext.request.contextPath}\controlServlet"
                  method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="avatar"><fmt:message key="change.form.label.five"
                                                                                    bundle="${rb}"/></label>
                    <input type="file" size="60" multiple accept="image/*" name="avatar" id="avatar"/>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-success" type="submit" value="AddUserAvatar" name="cmd"><fmt:message
                                key="change.button.three" bundle="${rb}"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</main>
<br><br>
<c:import url="footer.jsp"></c:import>
</body>
</html>


