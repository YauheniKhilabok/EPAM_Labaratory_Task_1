<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="admin.statistics.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="<fmt:message key="admin.statistics.description" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="admin.statistics.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/admin-style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="../../js/hide.js" type="text/javascript"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load('visualization', '1.0', {
            'packages': ['corechart']
        });
        google.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['<fmt:message key="admin.statistics.label.one" bundle="${rb}"/>', '<fmt:message key="admin.statistics.label.two" bundle="${rb}"/>'],
                <c:forEach items="${adminChart}" var="entry">
                ['${entry.type}', ${entry.count}],
                </c:forEach>
            ]);
            var options = {
                'title': '<fmt:message key="admin.statistics.label.three" bundle="${rb}"/>',
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
<c:set var="pageURL" scope="session" value="controlServlet?cmd=ShowAdminStatistics"/>
<c:import url="navigation.jsp"></c:import>
<main class="container">
    <div class="main-admin-style">
        <h2><fmt:message key="admin.statistics.header.big.one" bundle="${rb}"/></h2>
        <div class="div-statistics">
            <div id="chart_div"></div>
        </div>
        <input class="btn btn-primary" type="button"
               value="<fmt:message key="admin.statistics.button.one" bundle="${rb}"/>"
               onclick="show_statistics_add_form()">
        <div class="div-display-none" id="statistics_add_form">
            <form class="form-horizontal form-block" action="${pageContext.request.contextPath}\controlServlet"
                  method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="beginningReportPeriod"><fmt:message
                            key="admin.statistics.form.label.one" bundle="${rb}"/></label>
                    <div class="col-sm-10">
                        <input class="form-control" type="date" id="beginningReportPeriod"
                               name="beginning_report_period">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="endReportPeriod"><fmt:message
                            key="admin.statistics.form.label.two" bundle="${rb}"/></label>
                    <div class="col-sm-10">
                        <input class="form-control" type="date" id="endReportPeriod" name="end_report_period">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="amountPurchasedGoods"><fmt:message
                            key="admin.statistics.form.label.three" bundle="${rb}"/></label>
                    <div class="col-sm-10">
                        <input class="form-control" type="number" id="amountPurchasedGoods"
                               min="1"
                               placeholder="<fmt:message key="admin.statistics.form.placeholder.one" bundle="${rb}"/>"
                               name="amount_purchased_goods"
                               required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="consumption"><fmt:message
                            key="admin.statistics.form.label.four" bundle="${rb}"/></label>
                    <div class="col-sm-10">
                        <input class="form-control" type="number" step="0.1" id="consumption"
                               min="1"
                               placeholder="<fmt:message key="admin.statistics.form.placeholder.two" bundle="${rb}"/>"
                               name="consumption" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <button class="btn btn-primary" type="submit" value="AddPeriod" name="cmd"><fmt:message
                                key="admin.statistics.button.two" bundle="${rb}"/></button>
                        <button class="btn btn-default" type="reset"><fmt:message key="admin.statistics.button.three"
                                                                                  bundle="${rb}"/></button>
                    </div>
                </div>
            </form>
        </div>
        <h2><fmt:message key="admin.statistics.header.big.three" bundle="${rb}"/></h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th><fmt:message key="admin.statistics.label.four" bundle="${rb}"/></th>
                <th><fmt:message key="admin.statistics.label.five" bundle="${rb}"/></th>
                <th><fmt:message key="admin.statistics.label.six" bundle="${rb}"/></th>
                <th><fmt:message key="admin.statistics.label.seven" bundle="${rb}"/></th>
                <th><fmt:message key="admin.statistics.label.eighth" bundle="${rb}"/></th>
                <th><fmt:message key="admin.statistics.label.nine" bundle="${rb}"/></th>
                <th><fmt:message key="admin.statistics.label.ten" bundle="${rb}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="statistics" items="${statisticsList}">
                <tr>
                    <td><c:out value="${statistics.beginningReportPeriod}"/> - <c:out
                            value="${statistics.endReportPeriod}"/></td>
                    <td><c:out value="${statistics.amountPurchasedGoods}"/></td>
                    <td><c:out value="${statistics.amountSoldGoods}"/></td>
                    <td><c:out value="${statistics.consumption}"/></td>
                    <td><c:out value="${statistics.income}"/></td>
                    <td><c:out value="${statistics.profit}"/></td>
                    <td><c:out value="${statistics.status}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</main>
<br><br>
<footer class="container-fluid text-center">
</footer>
</body>
</html>


