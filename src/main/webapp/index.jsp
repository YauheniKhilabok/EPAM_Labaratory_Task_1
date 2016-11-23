<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="index.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<fmt:message key="index.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="index.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="index.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap-responsive.css">
    <link rel="stylesheet" href="css/main-style.css">
    <link rel="stylesheet" href="css/immersive-slider.css">
    <link rel="stylesheet" href="https://www.google.com/fonts#ChoosePlace:select/Collection:Oswald">
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="js/jquery.immersive-slider.js"></script>
    <script type="text/javascript" src="js/slider.js"></script>
    <script type="text/javascript" src="js/scrolltop.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="/index.jsp"/>
<c:import url="jsp/main/header.jsp"></c:import>
<main class="container">
    <c:import url="/jsp/main/navigation.jsp"></c:import>
    <div id="slider">
        <div class="main slider-size">
            <div class="page_container">
                <div id="immersive_slider">
                    <div class="slide" data-blurred="img/slider-background.jpg">
                        <div class="content">
                            <h2>www.sports-nutrition.by</h2>
                            <p><fmt:message key="index.slider.text.first" bundle="${rb}"/></p>
                        </div>
                        <div class="image">
                            <img src="img/slider-01.jpg" alt="Slide 1">
                        </div>
                    </div>
                    <div class="slide" data-blurred="img/slider-background.jpg">
                        <div class="content">
                            <h2>www.sports-nutrition.by</h2>
                            <p><fmt:message key="index.slider.text.second" bundle="${rb}"/></p>
                        </div>
                        <div class="image">
                            <img src="img/slider-02.jpg" alt="Slide 2">
                        </div>
                    </div>
                    <div class="slide" data-blurred="img/slider-background.jpg">
                        <div class="content">
                            <h2>www.sports-nutrition.by</h2>
                            <p><fmt:message key="index.slider.text.third" bundle="${rb}"/></p>
                        </div>
                        <div class="image">
                            <img src="img/slider-03.jpg" alt="Slider 3">
                        </div>
                    </div>
                    <div class="slide" data-blurred="img/slider-background.jpg">
                        <div class="content">
                            <h2>www.sports-nutrition.by</h2>
                            <p><fmt:message key="index.slider.text.fourth" bundle="${rb}"/></p>
                        </div>
                        <div class="image">
                            <img src="img/slider-04.jpg" alt="Slider 4">
                        </div>
                    </div>
                    <div class="slide" data-blurred="img/slider-background.jpg">
                        <div class="content">
                            <h2>www.sports-nutrition.by</h2>
                            <p><fmt:message key="index.slider.text.fifth" bundle="${rb}"/></p>
                        </div>
                        <div class="image">
                            <img src="img/slider-05.jpg" alt="Slider 5">
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
    <div>
        <div class="hero-unit greeting-indent text-font text-align text-indent">
            <img src="img/logo.png" alt="sports-nutrition.by">
            <h2><fmt:message key="index.greeting.title" bundle="${rb}"/></h2>
            <p class="lead"><fmt:message key="index.greeting.text" bundle="${rb}"/></p>
            <details class="lead">
                <summary><fmt:message key="index.details" bundle="${rb}"/></summary>
                <p><fmt:message key="index.details.text" bundle="${rb}"/></p>
            </details>
            <figure>
                <img src="img/Denis-Gusev.jpg" alt="<fmt:message key="index.alt.first" bundle="${rb}"/>">
                <figcaption><fmt:message key="index.figcaption" bundle="${rb}"/></figcaption>
            </figure>
        </div>
        <div class="row-fluid color-text">
            <div class="span4">
                <h2><fmt:message key="index.info.title.first" bundle="${rb}"/></h2>
                <p><fmt:message key="index.info.text.first" bundle="${rb}"/></p>
                <p><a class="btn btn-success" href="jsp/main/training.jsp"><fmt:message key="index.info.details"
                                                                                        bundle="${rb}"/> &raquo;</a></p>
            </div>
            <div class="span4">
                <h2><fmt:message key="index.info.title.second" bundle="${rb}"/></h2>
                <p><dfn><fmt:message key="index.info.dfn.first" bundle="${rb}"/></dfn><fmt:message
                        key="index.info.text.second" bundle="${rb}"/></p><br>
                <p><a class="btn btn-success" href="jsp/main/diet.jsp"><fmt:message key="index.info.details"
                                                                                    bundle="${rb}"/> &raquo;</a></p>
            </div>
            <div class="span4">
                <h2><fmt:message key="index.info.title.third" bundle="${rb}"/></h2>
                <p><dfn><fmt:message key="index.info.dfn.second" bundle="${rb}"/></dfn><fmt:message
                        key="index.info.text.third" bundle="${rb}"/></p><br>
                <p><a class="btn btn-success" href="jsp/main/articles.jsp"><fmt:message key="index.info.details"
                                                                                        bundle="${rb}"/> &raquo;</a></p>
            </div>
        </div>
        <hr>
        <div id="map">
            <iframe class="location-map"
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2351.363426518607!2d27.512796515438076!3d53.88974414157077!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbd003cdfdd471%3A0xfe7e07e2b1d17f35!2z0YPQuy4g0JvQtdGA0LzQvtC90YLQvtCy0LAgMjEsINCc0LjQvdGB0Lo!5e0!3m2!1sru!2sby!4v1469703890082"></iframe>
        </div>
    </div>
    <div id="contacts" class="partfolio-position color-text">
        <figure>
            <a href="https://vk.com/evgeniy_hilobok" target="_blank"><img class="img-rounded size-partfolio-image"
                                                                          src="img/Yauheni-Khilabok.jpg"
                                                                          alt="<fmt:message key="index.alt.second" bundle="${rb}"/>"></a>
            <figcaption><fmt:message key="index.figcaption.admin" bundle="${rb}"/></figcaption>
        </figure>
        <div>
            <img src="img/ico_velcom.png" alt="<fmt:message key="index.alt.third" bundle="${rb}"/>">
            <span>+375(29)311-17-65</span>
        </div>
        <div>
            <img src="img/ico-mail.png" alt="E-mail">
            <span>khilobok95@gmail.com</span>
        </div>
        <div>
            <img src="img/ico-skype.png" alt="Skype">
            <span>smile9577</span>
        </div>
    </div>
</main>
<c:import url="jsp/main/footer.jsp"></c:import>
</body>
</html>