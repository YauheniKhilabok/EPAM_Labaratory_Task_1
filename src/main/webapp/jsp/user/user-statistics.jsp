<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="statistics.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="statistics.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="statistics.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="statistics.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/user-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load('visualization', '1.0', {
            'packages': ['corechart']
        });
        google.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['<fmt:message key="statistics.label.one" bundle="${rb}"/>', '<fmt:message key="statistics.label.two" bundle="${rb}"/>'],
                <c:forEach items="${userChart}" var="entry">
                ['${entry.type}', ${entry.averagePrice}],
                </c:forEach>
            ]);
            var options = {
                'title': '<fmt:message key="statistics.label.three" bundle="${rb}"/>',
                is3D: true,
                pieSliceText: 'label',
                tooltip: {showColorCode: true},
                'width': 500,
                'height': 500
            };
            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=ShowStatisticForUser"/>
<c:import url="navigation.jsp"></c:import>
<main class="container main-size">
    <div class="div-statistics">
        <div id="chart_div"></div>
    </div>
</main>
<br><br>
<c:import url="footer.jsp"></c:import>
</body>
</html>


