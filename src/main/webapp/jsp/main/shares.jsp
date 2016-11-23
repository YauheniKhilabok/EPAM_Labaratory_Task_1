<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="shares.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<fmt:message key="shares.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="shares.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="shares.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/bootstrap-responsive.css">
    <link rel="stylesheet" href="../../css/main-style.css">
    <link rel="stylesheet" href="../../css/immersive-slider.css">
    <link rel="stylesheet" href="https://www.google.com/fonts#ChoosePlace:select/Collection:Oswald">
    <script type="text/javascript" src="../../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="../../js/jquery.immersive-slider.js"></script>
    <script type="text/javascript" src="../../js/slider.js"></script>
    <script type="text/javascript" src="../js/scrolltop.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="/jsp/main/shares.jsp"/>
<c:import url="header.jsp"></c:import>
<main class="container">
    <c:import url="navigation.jsp"></c:import>
    <div id="slider">
        <div class="main slider-size">
            <div class="page_container">
                <div id="immersive_slider">
                    <div class="slide" data-blurred="../../img/shares-main.jpg">
                        <div class="content">
                            <h2>www.sports-nutrition.by</h2>
                            <p><fmt:message key="shares.text.first" bundle="${rb}"/></p>
                        </div>
                        <div class="image">
                            <img src="../../img/shares01.jpg" alt="Slide 1">
                        </div>
                    </div>
                    <div class="slide" data-blurred="../../img/shares-main.jpg">
                        <div class="content">
                            <h2>www.sports-nutrition.by</h2>
                            <p><fmt:message key="shares.text.second" bundle="${rb}"/></p>
                        </div>
                        <div class="image">
                            <img src="../../img/shares02.jpg" alt="Slide 2">
                        </div>
                    </div>
                    <div class="slide" data-blurred="../../img/shares-main.jpg">
                        <div class="content">
                            <h2>www.sports-nutrition.by</h2>
                            <p><fmt:message key="shares.text.third" bundle="${rb}"/></p>
                        </div>
                        <div class="image">
                            <img src="../../img/shares03.jpg" alt="Slider 3">
                        </div>
                    </div>
                    <div class="is-nav">
                        <a href="#" class="is-prev">&laquo;</a>
                        <a href="#" class="is-next">&raquo;</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="benefits">
            <div class="page_container"></div>
        </div>
    </div>
</main>
<c:import url="footer.jsp"></c:import>
</body>
</html>