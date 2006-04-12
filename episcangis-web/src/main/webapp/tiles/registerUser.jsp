<%@ page language="java"%>

<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<h1><bean:message key="page.registerUser.title" /></h1>
<html:form action="/ManageUsers?method=register" focus="username">
	<table id="userForm">
		<tr>
			<th><bean:message key="user.username" /></th>
			<td>
        <html:text errorStyleId="formInputError" property="username" 
          size="16" />
      </td>
			<td id="formError"><html:errors property="username"/></td>
		</tr>
		<tr>
			<th><bean:message key="user.password" /></th>
			<td>
        <html:password errorStyleId="formInputError" property="password" 
          size="16" redisplay="false" />
      </td>
			<td id="formError"><html:errors property="password"/></td>
		</tr>
		<tr>
			<th><bean:message key="user.confirmPassword" /></th>
			<td>
        <html:password errorStyleId="formInputError" property="confirmPassword" 
          size="16" redisplay="false"/>
      </td>
			<td id="formError"><html:errors property="confirmPassword"/></td>
		</tr>
		<tr>
			<th><bean:message key="user.fullName" /></th>
			<td>
        <html:text errorStyleId="formInputError" property="fullName" 
          size="25" />
      </td>
			<td id="formError"><html:errors property="fullName"/></td>
		</tr>
		<tr>
			<th><bean:message key="user.phone" /></th>
			<td>
        <html:text errorStyleId="formInputError" property="phone" 
          size="25" />
      </td>
			<td id="formError"><html:errors property="phone"/></td>
		</tr>
		<tr>
			<th><bean:message key="user.email" /></th>
			<td>
        <html:text errorStyleId="formInputError" property="email" 
          size="25" />
      </td>
			<td id="formError"><html:errors property="email"/></td>
		</tr>
		<tr>
			<th><bean:message key="user.organization" /></th>
			<td>
        <html:text errorStyleId="formInputError" property="organization" 
          size="25" />
      </td>
			<td id="formError"><html:errors property="organization"/></td>
		</tr>
		<tr>
			<th colspan="2"><bean:message key="user.message" /></th>
			<td id="formError"><html:errors property="message"/></td>
		</tr>
		<tr>
			<td colspan="2">
        <html:textarea errorStyleId="formInputError" property="message" 
          cols="40" rows="20" />
      </td>
      <td></td>
		</tr>
    <tr><th colspan="3">
      <html:submit><bean:message key="page.registerUser.submit" /></html:submit>
      </th></tr>
	</table>
</html:form>
