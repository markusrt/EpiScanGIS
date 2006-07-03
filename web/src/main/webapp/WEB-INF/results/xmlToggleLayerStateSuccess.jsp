<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/xml; charset=UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<result>
  <logic:iterate id="layer" name="layerstates">
    <layer name='<bean:write name="layer" property="name"/>'
      active='<bean:write name="layer" property="active"/>' />
  </logic:iterate>
</result>