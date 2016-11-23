<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="pagination.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="pagination-position">
    <div class="pagination">
        <ul>
            <c:if test="${currentPage gt 1}">
                <li><a class="bluecurve"
                       href="controlServlet?page=${currentPage - 1}">&laquo;<fmt:message key="pagination.previous"
                                                                                         bundle="${rb}"/></a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${numberPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li>${i}</li>
                    </c:when>
                    <c:otherwise>
                        <li><a class="bluecurve" href="controlServlet?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt numberPages}">
                <li><a class="bluecurve"
                       href="controlServlet?page=${currentPage + 1}"><fmt:message key="pagination.next"
                                                                                  bundle="${rb}"/>&raquo;</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>
