<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="articles.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<fmt:message key="articles.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="articles.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="articles.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/bootstrap-responsive.css">
    <link rel="stylesheet" href="../../css/main-style.css">
    <link rel="stylesheet" href="https://www.google.com/fonts#ChoosePlace:select/Collection:Oswald">
    <script type="text/javascript" src="../../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="../../js/scrolltop.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="/jsp/main/articles.jsp"/>
<c:import url="header.jsp"></c:import>
<main class="container">
    <c:import url="navigation.jsp"></c:import>
    <div>
        <div class="hero-unit greeting-indent text-font text-align text-indent">
            <div class="log-position"><img src="../../img/logo.png" alt="sports-nutrition.by"></div>
            <section>
                <h2><fmt:message key="articles.main.header" bundle="${rb}"/></h2>
                <article>
                    <h3><fmt:message key="articles.average.header.first" bundle="${rb}"/></h3>
                    <figure>
                        <img class="article-image-size-one" src="../../img/sport-nutrition.jpg"
                             alt="<fmt:message key="articles.alt.one" bundle="${rb}"/>">
                        <figcaption><i>
                            <small><fmt:message key="articles.figcaption.first" bundle="${rb}"/></small>
                        </i></figcaption>
                    </figure>
                    <details>
                        <summary><fmt:message key="articles.summary.first" bundle="${rb}"/></summary>
                        <p><fmt:message key="articles.text.one" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.protein" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.two" bundle="${rb}"/></p>
                        <figure>
                            <img class="article-size-image" src="../../img/whey.jpg"
                                 alt="<fmt:message key="articles.alt.two" bundle="${rb}"/>">
                            <figcaption>
                                <i>
                                    <small><fmt:message key="articles.figcaption.second" bundle="${rb}"/></small>
                                </i>
                            </figcaption>
                        </figure>
                        <h4><fmt:message key="articles.header.gainer" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.three" bundle="${rb}"/></p>
                        <figure>
                            <img class="article-size-image" src="../../img/gainer.jpg"
                                 alt="<fmt:message key="articles.alt.three" bundle="${rb}"/>">
                            <figcaption>
                                <i>
                                    <small><fmt:message key="articles.figcaption.third" bundle="${rb}"/>
                                    </small>
                                </i>
                            </figcaption>
                        </figure>
                        <h4><fmt:message key="articles.header.aminoacids" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.four" bundle="${rb}"/></p>
                        <figure>
                            <img class="article-size-image" src="../../img/amino.jpg"
                                 alt="<fmt:message key="articles.alt.four" bundle="${rb}"/>">
                            <figcaption>
                                <i>
                                    <small><fmt:message key="articles.figcaption.fourth" bundle="${rb}"/>
                                    </small>
                                </i>
                            </figcaption>
                        </figure>
                        <h4><fmt:message key="articles.header.creatine" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.five" bundle="${rb}"/></p>
                        <figure>
                            <img class="article-size-image" src="../../img/creatine.jpg"
                                 alt="<fmt:message key="articles.alt.five" bundle="${rb}"/>">
                            <figcaption>
                                <i>
                                    <small><fmt:message key="articles.figcaption.six" bundle="${rb}"/>
                                    </small>
                                </i>
                            </figcaption>
                        </figure>
                    </details>
                </article>
                <article>
                    <h3><fmt:message key="articles.alt.one" bundle="${rb}"/> &laquo;<fmt:message
                            key="articles.average.header.second" bundle="${rb}"/>&raquo;</h3>
                    <details>
                        <summary><fmt:message key="articles.summary.first" bundle="${rb}"/></summary>
                        <p><fmt:message key="articles.text.six" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.first" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.seven" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.second" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.eight" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.third" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.nine" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.fourth" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.ten" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.fifth" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.eleven" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.sixth" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.twelve" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.seventh" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.thirteen" bundle="${rb}"/></p>
                    </details>
                </article>
            </section>
            <section>
                <h2><fmt:message key="articles.header.average.third" bundle="${rb}"/></h2>
                <article>
                    <h3><fmt:message key="articles.header.small" bundle="${rb}"/></h3>
                    <figure>
                        <img class="article-image-size-three" src="../../img/bodybuilding.jpg" alt="Бодибилдинг">
                        <figcaption><i>
                            <small><fmt:message key="articles.figcaption.seven" bundle="${rb}"/></small>
                        </i></figcaption>
                    </figure>
                    <details>
                        <summary><fmt:message key="articles.summary.first" bundle="${rb}"/></summary>
                        <h4><fmt:message key="articles.header.small.one" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.fourteen" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.two" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.fifteen" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.three" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.sixteen" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.four" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.seventeen" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.five" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.eighteen" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.six" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.nineteen" bundle="${rb}"/></p>
                        <h4><fmt:message key="articles.header.small.seven" bundle="${rb}"/></h4>
                        <p><fmt:message key="articles.text.twenty" bundle="${rb}"/></p>
                    </details>
                </article>
                <article>
                    <h3><fmt:message key="articles.header.average.fourth" bundle="${rb}"/></h3>
                    <figure>
                        <img class="article-image-size-four" src="../../img/muscles.jpg"
                             alt="<fmt:message key="articles.alt.six" bundle="${rb}"/>">
                        <figcaption><i>
                            <small><fmt:message key="articles.figcaption.eight" bundle="${rb}"/></small>
                        </i></figcaption>
                    </figure>
                    <details>
                        <summary><fmt:message key="articles.summary.first" bundle="${rb}"/></summary>
                        <p><fmt:message key="articles.text.twenty.one" bundle="${rb}"/></p>
                        <p><fmt:message key="articles.text.twenty.two" bundle="${rb}"/></p>
                        <p><fmt:message key="articles.text.twenty.three" bundle="${rb}"/></p>
                        <p><fmt:message key="articles.text.twenty.four" bundle="${rb}"/></p>
                        <p><fmt:message key="articles.text.twenty.five" bundle="${rb}"/></p>
                        <p><fmt:message key="articles.text.twenty.six" bundle="${rb}"/></p>
                        <p><fmt:message key="articles.text.twenty.seven" bundle="${rb}"/></p>
                        <p><fmt:message key="articles.text.twenty.eight" bundle="${rb}"/></p>
                        <p><fmt:message key="articles.text.twenty.nine" bundle="${rb}"/></p>
                    </details>
                </article>
            </section>
        </div>
    </div>
</main>
<c:import url="footer.jsp"></c:import>
</body>
</html>