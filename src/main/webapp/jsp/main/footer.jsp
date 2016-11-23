<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title>Footer</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<footer class="container">
    <div class="container text-center">
        <h3 class="color-text"><fmt:message key="footer.signature" bundle="${rb}"/></h3>
        <br>
        <div class="row">
            <div class="span2">
                <img src="../../img/brand01.png" class="img-responsive share-image-size" alt="Trec Nutrition"
                     title="Trec Nutrition">
            </div>
            <div class="span2">
                <img src="../../img/brand02.png" class="img-responsive share-image-size" alt="Ultimate Nutrition"
                     title="Ultimate Nutrition">
            </div>
            <div class="span2">
                <img src="../../img/brand03.png" class="img-responsive share-image-size" alt="Dymatize"
                     title="Dymatize">
            </div>
            <div class="span2">
                <img src="../../img/brand04.png" class="img-responsive share-image-size" alt="Maxler" title="Maxler">
            </div>
            <div class="span2">
                <img src="../../img/brand05.png" class="img-responsive share-image-size" alt="Pure Protein"
                     title="Pure Protein">
            </div>
            <div class="span2">
                <img src="../../img/brand06.png" class="img-responsive share-image-size" alt="Titan Lab"
                     title="Titan Lab">
            </div>
        </div>
    </div>
    <br><br><br>
    <script type="text/javascript">(function () {
        if (window.pluso)
            if (typeof window.pluso.start == "function")
                return;
        if (window.ifpluso == undefined) {
            window.ifpluso = 1;
            var d = document, s = d.createElement('script'), g = 'getElementsByTagName';
            s.type = 'text/javascript';
            s.charset = 'UTF-8';
            s.async = true;
            s.src = ('https:' == window.location.protocol ? 'https' : 'http') + '://share.pluso.ru/pluso-like.js';
            var h = d[g]('body')[0];
            h.appendChild(s);
        }
    })();
    </script>
    <div class=pluso-position>
        <div class="pluso" data-background="transparent" data-options="big,square,line,horizontal,nocounter,theme=01"
             data-services="vkontakte,twitter,facebook,google"></div>
        <p class="color-text">&copy; Company 2016 <span class="text-info"><fmt:message key="footer.status" bundle="${rb}"/> ${ctg:notnull(role)}</span></p>
    </div>
    <div id="toTop"><img src="../../img/scroll.png" alt="<fmt:message key="footer.alt.up" bundle="${rb}"/>"></div>
</footer>
</body>
</html>
