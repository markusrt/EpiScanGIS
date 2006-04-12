<%@ page language="java"%>

<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

Index

You are logged in as remote user <b><%= request.getRemoteUser() %></b>
in session <b><%= session.getId() %></b><br><br>
<%= request.isUserInRole("public_health")%>
<%= request.isUserInRole("epidegis_beta")%>
<%
  if (request.getUserPrincipal() != null) {
%>
    Your user principal name is
    <b><%= request.getUserPrincipal().getName() %></b><br><br>
<%
  } else {
%>
    No user principal could be identified.<br><br>
<%
  }
%>