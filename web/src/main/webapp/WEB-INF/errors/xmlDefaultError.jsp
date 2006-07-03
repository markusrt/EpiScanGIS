<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/xml; charset=UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<result> 
	<logic:messagesPresent message="true">
		<html:messages id="message"
			property="<%= org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE %>"
			message="true">
			<error><bean:write name="message" /></error>
		</html:messages>
	</logic:messagesPresent> 
</result>