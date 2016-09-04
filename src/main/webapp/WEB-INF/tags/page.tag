<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="subTitle" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="notShow" required="false" type="java.lang.Boolean" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>АСИУ</title>

    <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resources/js/jquery-1.11.3.min.js" var="jqueryJs" />
    <spring:url value="/resources/js/jquery-ui/jquery-ui.min.js" var="jqueryUIJs" />
    <spring:url value="/resources/css/jquery-ui/jquery-ui.min.css" var="jqueryUICss" />
    <spring:url value="/resources/js/main.js" var="mainJs" />
    <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/resources/js/validator.min.js" var="validatorJs" />

    <spring:url value="/resources/js/select2/select2.css" var="select2Css" />
    <spring:url value="/resources/js/select2/select2-bootstrap.css" var="select2BootstrapCss" />

    <spring:url value="/resources/js/select2/select2.min.js" var="select2Js" />

    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${jqueryUICss}" rel="stylesheet" />
    <link href="${select2Css}" rel="stylesheet" />
    <link href="${select2BootstrapCss}" rel="stylesheet" />
    <link href="${mainCss}" rel="stylesheet" />

    <script src="${jqueryJs}"></script>
    <script src="${jqueryUIJs}"></script>
    <script src="${select2Js}"></script>
    <script src="${bootstrapJs}"></script>
    <script src="${validatorJs}"></script>

    <spring:url value="/" var="baseURL"/>

    <script>
        var baseURL = '${baseURL}';
    </script>

</head>

<body>
<div class="wrapper">
    <c:if test="${!notShow}">
    <header>
        <div class="header_logo" onclick="location.href = baseURL"></div>
        <div class="header_content">
            <div class="btn-group" role="group" aria-label="...">
                <div class="btn-group">
                    <button class="btn btn-default dropdown-toggle menu_btn" type="button" id="dropdownMenu0" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        Разрезы
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu0">
                        <li><a href="<spring:url value="/cut/1" />" target="_blank">Разрез 1</a></li>
                        <li><a href="<spring:url value="/cut/2" />" target="_blank">Разрез 2</a></li>
                        <li><a href="<spring:url value="/cut/3" />" target="_blank">Разрез 3</a></li>
                        <li><a href="<spring:url value="/cut/4" />" target="_blank">Разрез 4</a></li>
                        <li><a href="<spring:url value="/cut/5" />" target="_blank">Разрез 5</a></li>
                        <li><a href="<spring:url value="/cut/6" />" target="_blank">Разрез 6</a></li>
                        <li><a href="<spring:url value="/cut/7" />" target="_blank">Разрез 7</a></li>
                        <li><a href="<spring:url value="/cut/8" />" target="_blank">Разрез 8</a></li>
                        <li><a href="<spring:url value="/cut/9" />" target="_blank">Разрез 9</a></li>
                        <li><a href="<spring:url value="/cut/10" />" target="_blank">Разрез 10</a></li>
                    </ul>
                </div>
                <div class="btn-group">
                    <a class="btn btn-default menu_btn" href="<spring:url value="/enter/operateJournal" />" target="_blank">
                        Оперативный диспетчерский журнал
                    </a>
                </div>
                <div class="btn-group">
                    <a class="btn btn-default menu_btn" href="<spring:url value="/enter/trends" />" target="_blank">
                        Тренды
                    </a>
                </div>
                <%--<div class="btn-group">--%>
                    <%--<a class="btn btn-default menu_btn" href="<spring:url value="/virtual/sensors" />" target="_blank">--%>
                        <%--Н/А КИА--%>
                    <%--</a>--%>
                <%--</div>--%>

            </div>
        </div>
    </header>
    </c:if>

    <div class="container-wrapper row">
        <jsp:doBody/>
    </div>

</div>

<c:if test="${!notShow}">
    <footer>
        <div>Inversion sensor</div>
    </footer>
</c:if>


<script src="${mainJs}"></script>

</body>
</html>