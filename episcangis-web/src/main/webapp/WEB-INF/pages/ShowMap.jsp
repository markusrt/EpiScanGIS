<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define name="map" id="map" type="de.meningococcus.episcangis.webapp.NRZMMapBean" />
<html:html locale="true">
<head>
<title>
  <bean:message key="global.title" />: 
  <bean:message key="showmap.heading" />
</title>
<link rel="stylesheet" type="text/css" href="<html:rewrite href='css/epidegis-web.css'/>" TITLE="Style">
</head>
<body bgcolor="white">
  <logic:notPresent name="org.apache.struts.action.MESSAGE"
    scope="application">
    <font color="red"> ERROR: Application resources not loaded -- check
    servlet container logs for error messages. </font>
  </logic:notPresent>

  <h3><bean:message key="global.title" />: <bean:message key="showmap.heading" /></h3>
  <table class="mapDisplay">
    <tr>
      <td><html:img src="GetMap.do"/></td>
      <td valign="top" class="legend"><logic:iterate id="layer" name="map"
        property="activeLayers">
        <img src="<bean:write name='layer' property='legendurl'/>" alt="legendUrl"/>
        <br />
      </logic:iterate></td>
    </tr>
  </table>
  <br/>
  <table class="information">
    <tr>
      <td class="map">
        <h4 colspan="2">
           Global map information:
        <h4>
        <div>
          <table>
            <tr>
              <td class="property"><bean:message key="map.area" />:</td>
              <td class="value"><bean:write name="map" property="area" /></td>
            </tr>
            <tr>
              <td class="property"><bean:message key="map.observationperiod" />:</td>
              <td class="value"><bean:message key="map.observationperiod.range"
                arg0='<%= map.getFromMonth() + " " + map.getFromYear() %>'
                arg1='<%= map.getToMonth() + " " + map.getToYear() %>' arg2='' /></td>
            </tr>
            <tr>
              <td class="property"><bean:message key="map.age" />:</td>
              <td class="value"><bean:message key="map.age.range"
                arg0='<%= map.getFromAge() %>'
                arg1='<%= map.getToAge() %>' /></td>
            </tr>
          </table>
        </div>
      </td>
      <td class="layer">
        <div class="heading">Layer information:</div>
        <logic:iterate id="layer" name="map" property="activeLayers">
          <div class="name">- <bean:write name="layer" property="title"/>:</div>
          <div>
            <div class="description">
              <strong>Description:</strong>
              <bean:write name="layer" property="description"/>
            </div>
            <div>
            <table class="parameters">
              <tr>
                <th>Parameter</th>
                <th>Value</th>
              </tr>
              <logic:iterate id="parameter" name="layer" property="parameters">
                <bean:define name="parameter" id="p" 
                  type="org.epidegis.webapp.AbstractParameter" />
                  <%
                  if(p.getName().startsWith("layerparameter")) {
                  %>
                  <tr>
                    <bean:define id="valueParameter" name="map" 
                      property="<%=p.getName()%>"
                      type="org.epidegis.webapp.IValueParameter"/>
                    <td class="property">
                      <bean:write name="valueParameter" property="title"/>:
                    </td>
                    <td class="value">
                      <bean:write name="valueParameter" property="aliasValue"/>
                    </td>
                  </tr>
                  <%
                  }
                  %>
              </logic:iterate>
            </table>
            </div>
          </div>
        </logic:iterate>
      </td>
    </tr>
  </table>

</body>
</html:html>
