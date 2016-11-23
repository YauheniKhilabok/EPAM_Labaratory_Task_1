<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="diet.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<fmt:message key="diet.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="diet.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="diet.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/bootstrap-responsive.css">
    <link rel="stylesheet" href="../../css/main-style.css">
    <link rel="stylesheet" href="https://www.google.com/fonts#ChoosePlace:select/Collection:Oswald">
    <script type="text/javascript" src="../../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="../../js/scrolltop.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="/jsp/main/diet.jsp"/>
<c:import url="header.jsp"></c:import>
<main class="container">
    <c:import url="navigation.jsp"></c:import>
    <div>
        <div class="hero-unit greeting-indent text-font text-align">
            <div class="log-position"><img src="../../img/logo.png" alt="sports-nutrition.by"></div>
            <section>
                <h2><fmt:message key="diet.header.main" bundle="${rb}"/></h2>
                <article>
                    <h3><fmt:message key="diet.header.big" bundle="${rb}"/></h3>
                    <p class="text-indent"><img class="diet-image" src="../../img/food01.jpg"
                                                alt="<fmt:message key="diet.alt.one" bundle="${rb}"/>"><fmt:message
                            key="diet.text.one" bundle="${rb}"/></p>
                    <h4><fmt:message key="diet.header.average.one" bundle="${rb}"/></h4>
                    <p class="text-indent"><strong><fmt:message key="diet.header.list.one" bundle="${rb}"/></strong></p>
                    <ol>
                        <li><fmt:message key="diet.list.one.first" bundle="${rb}"/>
                        </li>
                        <li><fmt:message key="diet.list.one.second" bundle="${rb}"/>
                        </li>
                        <li><fmt:message key="diet.list.one.third" bundle="${rb}"/>
                        </li>
                    </ol>
                    <p class="text-indent"><fmt:message key="diet.text.two" bundle="${rb}"/></p>
                    <h4><fmt:message key="diet.header.average.two" bundle="${rb}"/>/h4>
                        <p class="text-indent"><img class="diet-image" src="../../img/food02.jpg"
                                                    alt="<fmt:message key="diet.alt.one" bundle="${rb}"/>"><fmt:message
                                key="diet.text.three" bundle="${rb}"/></p>
                        <p class="text-indent"><fmt:message key="diet.text.four" bundle="${rb}"/></p>
                        <p class="text-indent"><strong><fmt:message key="diet.header.list.two" bundle="${rb}"/></strong>
                        </p>
                        <ol>
                            <li><fmt:message key="diet.list.two.first" bundle="${rb}"/>
                            </li>
                            <li><fmt:message key="diet.list.two.second" bundle="${rb}"/>
                            </li>
                            <li><fmt:message key="diet.list.two.third" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.two.fourth" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.two.fifth" bundle="${rb}"/></li>
                        </ol>
                        <p class="text-indent"><strong><fmt:message key="diet.header.list.three"
                                                                    bundle="${rb}"/></strong></p>
                        <ol>
                            <li><fmt:message key="diet.list.three.first" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.three.second" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.three.third" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.three.fourth" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.three.fifth" bundle="${rb}"/></li>
                        </ol>
                        <p class="text-indent"><strong><fmt:message key="diet.header.list.four"
                                                                    bundle="${rb}"/></strong></p>
                        <ol>
                            <li><fmt:message key="diet.list.four.first" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.four.second" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.four.third" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.four.fourth" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.four.fifth" bundle="${rb}"/></li>
                        </ol>
                        <p class="text-indent"><strong><fmt:message key="diet.header.list.five"
                                                                    bundle="${rb}"/></strong></p>
                        <ol>
                            <li><fmt:message key="diet.list.five.first" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.five.second" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.five.third" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.five.fourth" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.five.fifth" bundle="${rb}"/></li>
                        </ol>
                        <h4><fmt:message key="diet.header.average.three" bundle="${rb}"/></h4>
                        <p class="text-indent"><fmt:message key="diet.text.five" bundle="${rb}"/></p>
                        <p class="text-indent"><fmt:message key="diet.header.list.six"
                                                            bundle="${rb}"/></p>
                        <ul>
                            <li><fmt:message key="diet.list.six.first" bundle="${rb}"/>
                            </li>
                            <li><fmt:message key="diet.list.six.first" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.six.second" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.six.third" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.six.fourth" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.six.fifth" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.six.sixth" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.six.seventh" bundle="${rb}"/></li>
                            <li><fmt:message key="diet.list.six.eighth" bundle="${rb}"/></li>
                        </ul>
                        <p class="text-indent"><fmt:message key="diet.text.six" bundle="${rb}"/></p>
                        <p class="text-indent"><fmt:message key="diet.text.seven" bundle="${rb}"/></p>
                        <p class="text-indent"><fmt:message key="diet.text.eighth" bundle="${rb}"/></p>
                </article>
                <div class="video-position">
                    <iframe class="video-size" src="https://www.youtube.com/embed/ml_5xqEvosY" allowfullscreen></iframe>
                </div>
            </section>
        </div>
    </div>
</main>
<c:import url="footer.jsp"></c:import>
</body>
</html>