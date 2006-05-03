<%--

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<logic:redirect forward="welcome"/>


Redirect default requests to Welcome global ActionForward.
By using a redirect, the user-agent will change address to match the path of our Welcome ActionForward. 

--%>

<%@page pageEncoding="UTF-8"%>



<?xml version="1.0"?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <title>EpiScanGIS - Coming soon...</title>
    <style type="text/css" media="all">
      @import url("./css/episcangis-base.css");
      @import url("./css/episcangis-theme.css");                                                                                    
      @import url("./css/episcangis-nonav.css");
    </style>
    <style type="text/css" media="print">
      @import url("./css/episcangis-print.css");
    </style>
  </head>
	<body>
	  <div id="banner">
	    <div id="bannerLeft">
	      <a href="."><img 
	        src="images/logo_episcangis.png" alt="$msg.global.title"/></a>
	    </div>
	    <div id="bannerRight">
	      <a href="http://www.meningococcus.de"><img 
	        src="images/logo_nrzm.png" alt="$msg.global.organization"/></a>
	    </div>
	    <div class="clear">
	      <hr/>
	    </div>
	  </div>
	  <div id="breadcrumbs">
	    <div class="clear">
	      <hr/>
	    </div>
	  </div>
	  <div id="leftColumn">
	  </div>
	  <div id="bodyColumn">
	    <div id="contentBox">
	      <div class="section">
				  <h1>Demnächst...</h1>
				  <p>
				    ... entsteht hier die Internetpräsenz von EpiScanGIS, einem öffentlichen
				    web-basiertem System zur Überwachung von 
				    Meningokokken-Erkrankungsfällen in Deutschland.
				  </p>
				  <p>
				    Zur Zeit laufen die letzten Vorbereitungen um das System ab Juni
				    2006 unter dieser Adresse online stellen zu können. Bitte schauen
				    Sie bald wieder hier vorbei.
				  </p>
				</div>
				<div class="section">
				  <h1>Soon...</h1>
				  <p>
				    ... you will find here the internet presence of EpiScanGIS, a public
				    web-based system to monitor meningococcal disease in Germany.
				  </p>
				  <p>
				    At present we carry out the the final preparations get the system 
				    online as from June 2006. Please check back soon.
				  </p>
				</div>
				<div class="section">
				  <h1>Screenshots</h1>
				  <img src="images/teaser/screenshot.png" 
				    alt="Screenshot of EpiScanGIS Web-Application" />
				  <br />
				  <img src="images/teaser/serogroup_b.gif" 
				    alt="Generated surveillance map" />
				</div>
	    </div>
	  </div>
	  <div class="clear">
	    <hr/>
	  </div>
	  <div id="footer">
	    <div class="xright">
	      (C) 2005-2006 National Reference Centre for Meningococci,
	      Institute for Hygiene and Microbiology, W&#x00FC;rzburg, Germany
	    </div>
	  </div>
	</body>
</html>