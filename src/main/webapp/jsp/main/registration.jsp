<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="registration.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<fmt:message key="registration.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="registration.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="registration.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/bootstrap-responsive.css">
    <link rel="stylesheet" href="../../css/main-style.css">
    <link rel="stylesheet" href="https://www.google.com/fonts#ChoosePlace:select/Collection:Oswald">
    <script type="text/javascript" src="../../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="../../js/scrolltop.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="/jsp/main/registration.jsp"/>
<c:import url="header.jsp"></c:import>
<main class="container">
    <c:import url="navigation.jsp"></c:import>
    <div class="main-background">
        <div class="log-position"><img src="../../img/logo.png" alt="sports-nutrition.by"></div>
        <div class="reg-style">
            <div class="message-pos">
                <span class="text-success">${successRegistrationMessage}</span>
                <span class="text-error">${errorRegistrationMessage}</span>
            </div>
            <div class="form-padding">
                <form class="form-size" action="${pageContext.request.contextPath}\controlServlet" method="post">
                    <div>
                        <label class="control-label input-size" for="inputName"><fmt:message
                                key="registration.form.label.first" bundle="${rb}"/></label>
                        <input class="input-size" type="text" id="inputName"
                               placeholder="<fmt:message key="registration.form.placeholder.first" bundle="${rb}"/>"
                               name="name_user" pattern="^[A-Za-zА-Яа-яЁё\s]+$"
                               autofocus required>
                    </div>
                    <div>
                        <label class="control-label input-size" for="inputEmail"><fmt:message
                                key="registration.form.label.second" bundle="${rb}"/></label>
                        <input class="input-size" type="email" id="inputEmail"
                               placeholder="<fmt:message key="registration.form.placeholder.second" bundle="${rb}"/>"
                               name="email"
                               pattern="^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$"
                               required>
                    </div>
                    <div>
                        <label class="control-label input-size" for="inputPhone"><fmt:message
                                key="registration.form.label.third" bundle="${rb}"/></label>
                        <input class="input-size" type="tel" id="inputPhone" placeholder="375-xx-xxx-xx-xx"
                               name="phone" pattern="^375-[0-9]{2}-[0-9]{3}-[0-9]{2}-[0-9]{2}$">
                    </div>
                    <div>
                        <label class="control-label input-size" for="inputAddress"><fmt:message
                                key="registration.form.label.fourth" bundle="${rb}"/></label>
                        <textarea class="input-size" rows="5" id="inputAddress"
                                  placeholder="<fmt:message key="registration.form.placeholder.third" bundle="${rb}"/>"
                                  name="address" maxlength="255" required></textarea>
                    </div>
                    <div>
                        <label class="control-label input-size" for="inputLogin"><fmt:message
                                key="registration.form.label.fifth" bundle="${rb}"/></label>
                        <input class="input-size" type="text" id="inputLogin"
                               placeholder="<fmt:message key="registration.form.placeholder.fourth" bundle="${rb}"/>"
                               name="login" pattern="^[a-zA-Zа-яА-ЯЁё][a-zA-Zа-яА-ЯЁё0-9-_\.]{1,35}$"
                               required>
                    </div>
                    <div>
                        <label class="control-label input-size" for="inputPassword"><fmt:message
                                key="registration.form.label.sixth" bundle="${rb}"/></label>
                        <input class="input-size" type="password" id="inputPassword"
                               placeholder="<fmt:message key="registration.form.placeholder.fifth" bundle="${rb}"/>"
                               name="password" minlength="6" maxlength="35"
                               pattern="^(?=.*\d)(?=.*[a-zа-яё])(?=.*[A-ZА-ЯЁ])(?!.*\s).*$" required>
                    </div>
                    <div>
                        <label class="control-label input-size" for="inputConfirmPassword"><fmt:message
                                key="registration.form.label.seventh" bundle="${rb}"/></label>
                        <input class="input-size" type="password" id="inputConfirmPassword"
                               placeholder="<fmt:message key="registration.form.placeholder.sixth" bundle="${rb}"/>"
                               name="confirmPassword" minlength="6" maxlength="35"
                               pattern="^(?=.*\d)(?=.*[a-zа-яё])(?=.*[A-ZА-ЯЁ])(?!.*\s).*$"
                               required>
                    </div>
                    <div>
                        <div class="btn-add-pos">
                            <button class="btn btn-primary btn-add-size" type="submit" value="Registration" name="cmd">
                                <fmt:message key="registration.button.one" bundle="${rb}"/>
                            </button>
                        </div>
                        <div class="btn-add-pos">
                            <button class="btn btn-primary btn-add-size" type="reset"><fmt:message
                                    key="registration.button.two" bundle="${rb}"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
<c:import url="footer.jsp"></c:import>
</body>
</html>