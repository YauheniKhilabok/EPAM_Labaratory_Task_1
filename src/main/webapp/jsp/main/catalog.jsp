<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent" var="rb"/>
<html lang="ru">
<head>
    <title><fmt:message key="catalog.title" bundle="${rb}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<fmt:message key="catalog.description" bundle="${rb}"/>">
    <meta name="keywords"
          content="<fmt:message key="catalog.keywords" bundle="${rb}"/>">
    <meta name="author" content="<fmt:message key="catalog.author" bundle="${rb}"/>">
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="../../css/bootstrap.css">
    <link rel="stylesheet" href="../../css/bootstrap-responsive.css">
    <link rel="stylesheet" href="../../css/main-style.css">
    <link rel="stylesheet" href="https://www.google.com/fonts#ChoosePlace:select/Collection:Oswald">
    <script type="text/javascript" src="../../js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="../../js/scrolltop.js"></script>
</head>
<body>
<c:set var="pageURL" scope="session" value="/jsp/main/catalog.jsp"/>
<c:import url="header.jsp"></c:import>
<main class="container">
    <c:import url="navigation.jsp"></c:import>
    <div class="container">
        <div class="hero-unit">
            <div class="row">
                <div class="folder-padding">
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.one" bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder01.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.one" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="Proteins" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.two" bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder02.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.two" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="AminoAcids" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.three"
                                                                    bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder03.png" class="img-responsive" alt="BCAA">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden"
                                           value="<fmt:message key="catalog.panel.label.three" bundle="${rb}"/>"
                                           name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.four"
                                                                    bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder04.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.four" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="Gainers" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.five"
                                                                    bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder05.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.five" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="Creatine" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.six" bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder06.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.six" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="Testosterone" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.seven"
                                                                    bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder07.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.seven" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="Complexes" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.eight"
                                                                    bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder08.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.eight" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="WeightLoss" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.nine"
                                                                    bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder09.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.nine" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="Chondroprotectors" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.ten" bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder10.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.ten" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="Vitamins" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.eleven"
                                                                    bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder11.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.eleven" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="Energy" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.twelve"
                                                                    bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder12.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.twelve" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="SpecialPreparations" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="span3">
                        <div class="panel panel-primary type-frame-style">
                            <div class="panel-heading"><fmt:message key="catalog.panel.label.thirteen"
                                                                    bundle="${rb}"/></div>
                            <div class="panel-body"><img src="../../img/folder13.png" class="img-responsive"
                                                         alt="<fmt:message key="catalog.panel.label.thirteen" bundle="${rb}"/>">
                            </div>
                            <div class="panel-footer">
                                <form action="${pageContext.request.contextPath}\controlServlet" method="get">
                                    <input type="hidden" value="Bars" name="type">
                                    <button class="btn btn-success" type="submit" value="ShowCatalogForGuests"
                                            name="cmd">
                                        <fmt:message key="catalog.button" bundle="${rb}"/>
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br><br>
</main>
<c:import url="footer.jsp"></c:import>
</body>
</html>