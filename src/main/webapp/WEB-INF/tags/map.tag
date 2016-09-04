<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="cutId" required="false" type="java.lang.Integer" %>
<%@ attribute name="height" required="false" type="java.lang.Integer" %>

<spring:url value="/resources/js/map.js" var="mapJs" />


<svg id="map" width="1024" height="${height}" class="map map_${cutId}">
    <c:forEach items="${regions}" var="region" varStatus="i">
        <a xlink:href="<spring:url value="/cut/${region.id}" />" xlink:title="${region.title}" target="_blank">
            <g>
                <polygon points="${region.coord}" fill="rgba(255,255,255,0.5)" stroke="blue">

                </polygon>
            </g>
        </a>
    </c:forEach>

</svg>

</audio>

<script src="${mapJs}"></script>
