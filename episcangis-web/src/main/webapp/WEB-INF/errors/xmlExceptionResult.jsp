<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/xml; charset=UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>

<result> 
  <%Exception e = (Exception)request.getAttribute("org.apache.struts.action.EXCEPTION");%>
  <error>
    <message><%="<![CDATA["%>
    <%=e.getMessage()%>
    <%="]]>"%></message>
    <stacktrace><%="<![CDATA["%>
      <%e.printStackTrace(new PrintWriter(out));%>
    <%="]]>"%></stacktrace>
  </error>
</result>
