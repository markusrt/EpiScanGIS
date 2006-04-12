<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<logic:notPresent name="org.apache.struts.action.MESSAGE" scope="application">
  <font color="red">
    ERROR:  Application resources not loaded -- check servlet container
    logs for error messages.
  </font>
</logic:notPresent>

<h3>
  <bean:message key="incidencemap.heading"/> <bean:write name="incidenceFormBean" property="year"/>
</h3>
<p>
<img 
  src="<html:rewrite page='/maps/incidence/'/><bean:write name="incidenceFormBean" property="year"/>.jpg"
  alt="Map"/>
</p>