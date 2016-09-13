<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="cutId" required="false" type="java.lang.Integer" %>
<%@ attribute name="height" required="false" type="java.lang.Integer" %>

<spring:url value="/resources/js/map.js" var="mapJs" />


<svg id="map" width="100%" height="100%" class="map map_${cutId}" viewBox="0 0 1024 496" preserveAspectRatio="none">
    <c:forEach items="${regions}" var="region" varStatus="i">
        <%--<a xlink:href="<spring:url value="/cut/${region.id}" />" xlink:title="${region.title}" target="_blank">--%>
            <g class="region" data-id="${region.id}">
                <c:forEach items="${region.blocks}" var="block" varStatus="j">
                    <rect class="block-rect" data-id="${block.id}" data-type="${block.type}" data-criterion="${block.criterion}"
                          data-value="${block.measParamSys.valueMeas}"
                          x="${block.x}" y="${block.y}" width="${block.width}" height="${block.height}" fill="${block.color}" stroke="black"></rect>
                    <text x="${block.x + (block.title.length() > 1 ? 2 : 7)}" y="${block.y + 15}">${block.title}</text>
                </c:forEach>
                <rect class="region-rect" x="${region.x}" y="${region.y}" width="${region.width}" height="${region.height}" fill="rgba(255,255,255,0.5)" stroke="blue"></rect>
                <text x="${region.x + region.offset}" y="${region.y + 42}" font-size="26" font-weight="bold">${region.title}</text>
                <title>БСР-${region.title}</title>
            </g>
        <%--</a>--%>
        

    </c:forEach>

</svg>

</audio>

<script src="${mapJs}"></script>
