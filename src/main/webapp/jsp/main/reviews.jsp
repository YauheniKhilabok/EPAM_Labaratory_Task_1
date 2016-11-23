<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="reviews.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<fmt:message key="reviews.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="reviews.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="reviews.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/bootstrap-responsive.css">
    <link rel="stylesheet" href="../../css/main-style.css">
    <link rel="stylesheet" href="../../css/pagination.css">
    <link rel="stylesheet" href="https://www.google.com/fonts#ChoosePlace:select/Collection:Oswald">
    <script type="text/javascript" src="../../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="../../js/scrolltop.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="controlServlet?cmd=PrintReviews"/>
<c:import url="header.jsp"></c:import>
<main class="container">
    <c:import url="navigation.jsp"></c:import>
    <div>
        <div class="hero-unit greeting-indent text-font">
            <c:set var="firstPage" scope="page" value="1"/>
            <c:if test="${authorized ne true and currentPage eq firstPage or currentPage eq null}">
                <div class="alert alert-info">
                    <p><strong><fmt:message key="reviews.warning.part.one" bundle="${rb}"/></strong> <fmt:message
                            key="reviews.warning.part.two" bundle="${rb}"/>
                        <strong><fmt:message key="reviews.warning.part.three" bundle="${rb}"/></strong>.
                    </p>
                </div>
            </c:if>
            <c:if test="${currentPage eq firstPage or currentPage eq null}">
                <p class="text-success">${successCommentMessage}</p>
                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                    <button class="btn-large btn-info" type="submit" value="PrintReviews" name="cmd"><i
                            class="icon-eye-open icon-white"></i>&nbsp;<fmt:message key="reviews.button.one"
                                                                                    bundle="${rb}"/>
                    </button>
                </form>
                <p><span class="badge">${numberOfReplies}</span> <fmt:message key="reviews.label.comments.first"
                                                                              bundle="${rb}"/></p><br>
            </c:if>
            <c:forEach var="reviews" items="${reviewsList}">
                <div id="comments">
                    <div>
                        <img src="<c:out value="${reviews.userAvatar}"/>" class="img-circle" height="65" width="65"
                             alt="Avatar">
                    </div>
                    <div>
                        <h4><c:out value="${reviews.userName}"/>
                            <small><c:out value="${reviews.sendingTime}"/></small>
                        </h4>
                        <c:if test="${user_id ne reviews.userId}">
                            <p><c:out value="${reviews.message}"/></p>
                        </c:if>
                    </div>
                    <c:if test="${user_id eq reviews.userId and role eq user}">
                        <div>
                            <form action="${pageContext.request.contextPath}\controlServlet" method="post">
                                <input type="hidden" value="<c:out value="${reviews.id}"/>" name="id_review">
                            <textarea class="form-control textarea-width" rows="7" maxlength="1500" name="new_message"
                                      required><c:out
                                    value="${reviews.message}"/></textarea>
                                <button class="btn-inverse btn-mini" type="submit" value="DeleteComment" name="cmd">
                                    <fmt:message key="reviews.button.two" bundle="${rb}"/>
                                </button>
                                <button class="btn-inverse btn-mini" type="submit" value="ChangeComment" name="cmd">
                                    <fmt:message key="reviews.button.three" bundle="${rb}"/>
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${role ne null and role eq admin}">
                        <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                            <input type="hidden" value="<c:out value="${reviews.id}"/>" name="id_review">
                            <button class="btn-inverse btn-mini" type="submit" value="DeleteComment" name="cmd">
                                <fmt:message key="reviews.button.four" bundle="${rb}"/>
                            </button>
                        </form>
                    </c:if>
                </div>
            </c:forEach>
            <div class="pagination-position">
                <div class="pagination">
                    <ul>
                        <c:if test="${currentPage gt 1}">
                            <li><a class="blackcurve"
                                   href="controlServlet?page=${currentPage - 1}">&laquo;<fmt:message
                                    key="reviews.previous" bundle="${rb}"/></a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${numberPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li>${i}</li>
                                </c:when>
                                <c:otherwise>
                                    <li><a class="blackcurve" href="controlServlet?page=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${currentPage lt numberPages}">
                            <li><a class="blackcurve" href="controlServlet?page=${currentPage + 1}"><fmt:message
                                    key="reviews.next" bundle="${rb}"/>&raquo;</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
            <c:if test="${authorized eq true and currentPage eq numberPages}">
                <c:if test="${role eq user}">
                    <h4><fmt:message key="reviews.label.comments.second" bundle="${rb}"/></h4>
                    <form action="${pageContext.request.contextPath}\controlServlet" method="post">
                        <div class="form-group">
                        <textarea class="form-control textarea-width" rows="7" maxlength="1500" name="message" autofocus
                                  required></textarea>
                        </div>
                        <button type="submit" class="btn btn-success" value="Comment" name="cmd"><fmt:message
                                key="reviews.button.five" bundle="${rb}"/></button>
                        <input type="reset" class="btn btn-primary"
                               value="<fmt:message key="reviews.button.six" bundle="${rb}"/>">
                    </form>
                </c:if>
            </c:if>
        </div>
    </div>
</main>
<c:import url="footer.jsp"></c:import>
</body>
</html>