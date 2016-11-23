<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="training.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<fmt:message key="training.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="training.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="training.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/bootstrap-responsive.css">
    <link rel="stylesheet" href="../../css/main-style.css">
    <link rel="stylesheet" href="https://www.google.com/fonts#ChoosePlace:select/Collection:Oswald">
    <script type="text/javascript" src="../../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="../../js/scrolltop.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="/jsp/main/training.jsp"/>
<c:import url="header.jsp"></c:import>
<main class="container">
    <c:import url="navigation.jsp"></c:import>
    <div>
        <div class="hero-unit greeting-indent text-font text-align text-indent">
            <div class="log-position"><img src="../../img/logo.png" alt="sports-nutrition.by"></div>
            <section>
                <h2><fmt:message key="training.header.main" bundle="${rb}"/></h2>
                <article>
                    <h3><fmt:message key="training.header.big.one" bundle="${rb}"/></h3>
                    <details>
                        <summary><fmt:message key="training.summary" bundle="${rb}"/></summary>
                        <p><fmt:message key="training.text.one" bundle="${rb}"/></p>
                        <dl>
                            <dt><fmt:message key="training.header.list.one" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.one" bundle="${rb}"/></dd>
                            <dt><fmt:message key="training.header.list.two" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.two" bundle="${rb}"/></dd>
                            <dt><fmt:message key="training.header.list.three" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.three" bundle="${rb}"/></dd>
                            <dt><fmt:message key="training.header.list.four" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.four" bundle="${rb}"/></dd>
                            <dt><fmt:message key="training.header.list.five" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.five" bundle="${rb}"/></dd>
                            <dt><fmt:message key="training.header.list.six" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.six" bundle="${rb}"/></dd>
                        </dl>
                        <h4><fmt:message key="training.header.average.one" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.header.list.seven" bundle="${rb}"/></p>
                        <ol>
                            <li><fmt:message key="training.text.list.seven.first" bundle="${rb}"/></li>
                            <li><fmt:message key="training.text.list.seven.second" bundle="${rb}"/></li>
                            <li><fmt:message key="training.text.list.seven.third" bundle="${rb}"/></li>
                        </ol>
                        <p><strong><fmt:message key="training.strong.one" bundle="${rb}"/></strong> <fmt:message
                                key="training.text.two" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.three" bundle="${rb}"/></p>
                        <p><strong><fmt:message key="training.strong.two" bundle="${rb}"/></strong> <fmt:message
                                key="training.text.four" bundle="${rb}"/></p>
                        <p><strong><fmt:message key="training.strong.three" bundle="${rb}"/></strong> <fmt:message
                                key="training.text.five" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.six" bundle="${rb}"/></p>
                        <div class="video-position">
                            <iframe class="video-size" src="https://www.youtube.com/embed/vPI0BJbGybU"
                                    allowfullscreen></iframe>
                        </div>
                    </details>
                </article>
                <article>
                    <h3><fmt:message key="training.header.big.two" bundle="${rb}"/></h3>
                    <details>
                        <summary><fmt:message key="training.summary" bundle="${rb}"/></summary>
                        <p><fmt:message key="training.text.seven" bundle="${rb}"/></p>
                        <dl>
                            <dt><fmt:message key="training.header.list.one" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.eighth.first" bundle="${rb}"/></dd>
                            <dt><fmt:message key="training.header.list.two" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.eighth.second" bundle="${rb}"/></dd>
                            <dt><fmt:message key="training.header.list.three" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.eighth.third" bundle="${rb}"/></dd>
                            <dt><fmt:message key="training.header.list.four" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.eighth.fourth" bundle="${rb}"/></dd>
                            <dt><fmt:message key="training.header.list.five" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.eighth.fifth" bundle="${rb}"/></dd>
                            <dt><fmt:message key="training.header.list.six" bundle="${rb}"/></dt>
                            <dd><fmt:message key="training.text.list.eighth.sixth" bundle="${rb}"/></dd>
                        </dl>
                        <h4><fmt:message key="training.header.average.two" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.eighth" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.nine" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.ten" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.three" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.eleven" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.twelve" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.thirteen" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.fourteen" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.fifteen" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.four" bundle="${rb}"/></h4>
                        <figure>
                            <img src="../../img/chest-exercises.jpg"
                                 alt="<fmt:message key="training.alt.one" bundle="${rb}"/>">
                            <figcaption>
                                <small><fmt:message key="training.figcaption.one" bundle="${rb}"/></small>
                            </figcaption>
                        </figure>
                        <p><fmt:message key="training.text.sixteen" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.seventeen" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.eighteen" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.five" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.nineteen" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.twenty" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.six" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.twenty.one" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.twenty.two" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.twenty.three" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.seven" bundle="${rb}"/></h4>
                        <figure>
                            <img src="../../img/push-ups.jpg"
                                 alt="<fmt:message key="training.alt.two" bundle="${rb}"/>">
                            <figcaption>
                                <small><fmt:message key="training.figcaption.two" bundle="${rb}"/></small>
                            </figcaption>
                        </figure>
                        <p><fmt:message key="training.text.twenty.four" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.twenty.five" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.twenty.six" bundle="${rb}"/></p>
                        <div class="video-position">
                            <iframe class="video-size" src="https://www.youtube.com/embed/TQXPND7Nxis"
                                    allowfullscreen></iframe>
                        </div>
                    </details>
                </article>
                <article>
                    <h3><fmt:message key="training.header.big.three" bundle="${rb}"/></h3>
                    <details>
                        <summary><fmt:message key="training.summary" bundle="${rb}"/></summary>
                        <p><fmt:message key="training.text.twenty.seven" bundle="${rb}"/></p>
                        <p><strong><fmt:message key="training.strong.four" bundle="${rb}"/></strong><fmt:message
                                key="training.text.twenty.eighth" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.twenty.nine" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.thirty" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.thirty.one" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.eighth" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.thirty.two" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.nine" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.thirty.three" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.thirty.four" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.thirty.five" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.ten" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.thirty.six" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.eleven" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.thirty.seven" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.thirty.eighth" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.twelve" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.thirty.nine" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.forty" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.thirteen" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.forty.one" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.forty.two" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.forty.three" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.fourteen" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.forty.four" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.forty.five" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.fifteen" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.forty.six" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.forty.seven" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.sixteen" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.forty.eighth" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.forty.nine" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.fifty" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.seventeen" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.fifty.one" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.fifty.two" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.eighteen" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.fifty.three" bundle="${rb}"/>
                        </p>
                        <p><fmt:message key="training.text.fifty.four" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.fifty.five" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.fifty.six" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.nineteen" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.fifty.seven" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.fifty.eighth" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.fifty.nine" bundle="${rb}"/></p>
                        <div class="video-position">
                            <iframe class="video-size" src="https://www.youtube.com/embed/klssdO_jB88"
                                    allowfullscreen></iframe>
                        </div>
                    </details>
                </article>
                <article>
                    <h3><fmt:message key="training.header.big.four" bundle="${rb}"/></h3>
                    <details>
                        <summary><fmt:message key="training.summary" bundle="${rb}"/></summary>
                        <p><fmt:message key="training.text.sixty" bundle="${rb}"/></p>
                        <p><strong><fmt:message key="training.strong.four" bundle="${rb}"/></strong><fmt:message
                                key="training.text.sixty.one" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.sixty.two" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.sixty.three" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.sixty.four" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.sixty.five" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.sixty.six" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.twenty" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.sixty.seven" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.twenty.one" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.sixty.eighth" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.sixty.nine" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.twenty.two" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.seventy" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.seventy.one" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.twenty.three" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.seventy.two" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.seventy.three" bundle="${rb}"/></p>
                        <figure>
                            <img src="../../img/french-press.jpg"
                                 alt="<fmt:message key="training.alt.three" bundle="${rb}"/>">
                            <figcaption>
                                <small><fmt:message key="training.figcaption.three" bundle="${rb}"/></small>
                            </figcaption>
                        </figure>
                        <h4><fmt:message key="training.header.average.twenty.four" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.seventy.four" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.twenty.five" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.seventy.five" bundle="${rb}"/></p>
                        <h4><fmt:message key="training.header.average.twenty.six" bundle="${rb}"/></h4>
                        <p><fmt:message key="training.text.seventy.six" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.seventy.seven" bundle="${rb}"/></p>
                        <p><fmt:message key="training.text.seventy.eighth" bundle="${rb}"/></p>
                        <div class="video-position">
                            <iframe class="video-size" src="https://www.youtube.com/embed/eScZ68Xr_mQ"
                                    allowfullscreen></iframe>
                        </div>
                    </details>
                </article>
            </section>
        </div>
    </div>
</main>
<c:import url="footer.jsp"></c:import>
</body>
</html>