<%@ page language="java"%>

<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html:html locale="true">
<head>
<meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
<meta http-equiv="content-language" content="de, at, ch, en, en-us" />
<meta name="distribution" content="global" />
<meta name="robots" content="index,follow" />
<meta name="description" content="" />
<meta name="description" lang="en-us" content="" />
<meta name="description" lang="en" content="" />
<meta name="keywords" content="" />
<meta name="keywords" lang="en-us" content="" />
<meta name="keywords" lang="en" content="" />

<link rel="stylesheet" type="text/css" media="screen,print"
	href="css/main.css" />
<link rel="stylesheet" type="text/css" media="print"
	href="css/print.css" />

<title>
  <tiles:useAttribute id="title" name="title"/>
  <bean:message	key="global.title" />: <bean:message name="title" /></title>
</head>

<body>
<table id="main">
	<tbody>
		<tr id="header">
			<td colspan="2"><tiles:insert attribute="header" /></td>
		</tr>
		<tr id="menu_content">
			<td id="menu"><tiles:insert attribute="menu" /></td>
			<td id="content"><tiles:insert attribute="body" /></td>
		</tr>
		<tr id="footer">
			<td colspan="2"><tiles:insert attribute="footer" /></td>
		</tr>
	</tbody>
</table>
</body>
</html:html>
