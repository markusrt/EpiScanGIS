<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/xml; charset=UTF-8"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<result>
  <logic:iterate id="layerName" name="updatedLayers">
    <update><bean:write name="layerName"/></update>
  </logic:iterate>
</result>