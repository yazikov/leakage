<%--
  Created by IntelliJ IDEA.
  User: nikolay
  Date: 06.09.15
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
  <jsp:body>
    <%--<div class="col1">--%>
      <%--<div class="col1_resize"></div>--%>
      <%--<div class="block info_block">--%>
        <%--<div class="panel panel-default">--%>
          <%--<div class="panel-heading">Информация о датчике</div>--%>
          <%--<div class="panel-body">--%>
            <%--<div id="sensor-info" class="alert"></div>--%>
          <%--</div>--%>
        <%--</div>--%>
      <%--</div>--%>
      <%--<div class="block data_block">--%>
        <%--<div class="panel panel-default">--%>
          <%--<div class="panel-heading">Список датчиков</div>--%>
          <%--<div class="panel-body">--%>
            <%--<t:tree />--%>
          <%--</div>--%>
        <%--</div>--%>
      <%--</div>--%>
    <%--</div>--%>
    <div class="col col-center col-xs-9" data-size="9">
      <div class="block">
        <div class="panel panel-default">
          <div class="panel-heading text-center">Карта данных о протечках</div>
          <div class="panel-body">
            <div class="map_container">
              <t:map cutId="0" height="496" />
            </div>
          </div>
        </div>
      </div>

    </div>
    <div class="col col-xs-3">
    <div class="block">
      <div class="block">
        <div class="panel panel-default">
          <div class="panel-heading text-center"><t:btnHide /> Оперативный журнал событий</div>
          <div class="panel-body">
            <div id="sensor-logging">

            </div>
          </div>
        </div>
      </div>
    </div>

  </jsp:body>
</t:page>