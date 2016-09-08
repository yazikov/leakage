<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="cutId" required="false" type="java.lang.Integer" %>
<%@ attribute name="height" required="false" type="java.lang.Integer" %>

<spring:url value="/resources/js/map.js" var="mapJs" />


<svg id="map" width="100%" height="100%" class="map map_${cutId}" viewBox="0 0 1024 496" preserveAspectRatio="none">
    <c:forEach items="${regions}" var="region" varStatus="i">
        <%--<a xlink:href="<spring:url value="/cut/${region.id}" />" xlink:title="${region.title}" target="_blank">--%>
            <g class="region" data-id="${region.id}">
                <polygon points="${region.coord}" fill="rgba(255,255,255,0.5)" stroke="blue"></polygon>
            </g>
        <%--</a>--%>
        

    </c:forEach>

</svg>

</audio>

<script src="${mapJs}"></script>
