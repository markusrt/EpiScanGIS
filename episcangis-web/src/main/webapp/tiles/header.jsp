<%@ page language="java"%>

<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>

<span id="title"><bean:message key="global.title" /></span>
<span id="login_logoff"> <logic:present name="user">
	<a href="Logout.do">Logout</a>
	<bean:write name="user" property="realName" />
</logic:present> <logic:notPresent name="user">
	<a href="Login.do">Login</a> or 
	<a href="RegisterUser.do">Register</a>
</logic:notPresent> </span>
