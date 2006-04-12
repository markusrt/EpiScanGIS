<?xml version="1.0" encoding="utf-8"?>
<!--
  app-console.xslt
--> 

<!-- * X_LZ_COPYRIGHT_BEGIN ***************************************************
* Copyright 2001-2004 Laszlo Systems, Inc.  All Rights Reserved.              *
* Use is subject to license terms.                                            *
* X_LZ_COPYRIGHT_END ****************************************************** -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">

  <xsl:output method="html"
              indent="yes"/>
  
  <xsl:param name="lps"><xsl:value-of select="/*/request/@lps"/></xsl:param>
  <xsl:param name="assets"><xsl:value-of select="/*/request/@lps"/>/lps/assets</xsl:param>
  <xsl:param name="isKranked"/>
  <xsl:param name="isKranking" select="//param[@name='krank' and @value='true']"/>
  <xsl:param name="opturl" select="/*/request/@opt-url"/>
  <xsl:param name="unopturl" select="/*/request/@unopt-url"/>

  <xsl:template match="/">
    <html>
      <head>
        <link rel="SHORTCUT ICON" href="http://www.laszlosystems.com/favicon.ico"/>
        <link rel="stylesheet" href="{$lps}/lps/includes/console.css" type="text/css"/>
        <title>
          <xsl:choose>
          <xsl:when test="canvas">
            <xsl:value-of select="canvas/@title"/>
          </xsl:when>
          <xsl:otherwise>
            Compilation Errors
          </xsl:otherwise>
          </xsl:choose>
        </title>
        <style type="text/css">
          img {border: 0}
        </style>
        <script type="text/javascript" src="{$lps}/lps/includes/embed.js"></script>
      </head>
      <body style="margin: 0">
        <xsl:if test="$isKranking">
          <xsl:attribute name="bgcolor">#EAEAEA</xsl:attribute>
          <div id="krank-header">
            <img src="{$assets}/logo_krank_header.gif"/>
            <span class="status">The optimizer is collecting data.</span>
          </div>
          <center>
            <script type="text/javascript" >
                 lzEmbed({url: '<xsl:value-of select="$assets"/>/startup_small.swf', width: '400', height: '400'});
            </script>
          </center>
          <script type="text/javascript">defaultStatus = 'Optimizing...'</script>
        </xsl:if>
        <xsl:if test="//param[@name='showKrankDuration'] and not($isKranking)">
          <div id="krank-header">
            <img src="{$assets}/logo_krank_header.gif"/>
            <span class="status">Optimization took <xsl:value-of select="//param[@name='showKrankDuration']/@value"/> seconds.</span>
          </div>
        </xsl:if>
        <xsl:if test="/canvas/warnings and not($isKranking)">
          <div style="border-top: 1px solid; border-bottom: 1px solid; background-color: #fcc; padding: 1pt; padding-left: 2pt">
            <a href="#warnings">Review</a>
            <xsl:text> </xsl:text>
            <xsl:value-of select="count(/canvas/warnings/error)"/>
            <xsl:text> compilation warning</xsl:text>
            <xsl:if test="count(/canvas/warnings/error) > 1">s</xsl:if>.
          </div>
        </xsl:if>
        <xsl:apply-templates select="canvas|errors"/>
      </body>
    </html>
  </xsl:template>
  
  <xsl:template match="canvas">
    <xsl:param name="url" select="request/@url"/>
    <xsl:param name="query_args" select="request/@query_args"/>
    <xsl:choose>
      <!-- In the case of an lzt=html request, ResponderHTML uses string
           concatenation to create the <OBJECT>, <object>, and <embed>
           elements. See the comment in ResponderHTML for an explanation. -->
      <xsl:when test="@pocketpc">
        <OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
          width="{@width}" height="{@height}" id="lzx">
          <PARAM NAME="movie" VALUE="{$url}?lzt=swf"/>
        </OBJECT>
      </xsl:when>
      <xsl:otherwise>
        <div>
          <xsl:if test="not($isKranking)">
            <xsl:attribute name="style">
              <xsl:text>background-color: </xsl:text>
              <xsl:value-of select="/canvas/@bgcolor"/>
            </xsl:attribute>
          </xsl:if>
          <script type="text/javascript">
               lzEmbed({url: '<xsl:value-of select="$url"/>?lzt=swf<xsl:value-of select="$query_args"/>', bgcolor: '<xsl:value-of select="@bgcolor"/>'<xsl:choose><xsl:when test="not ($isKranking)">, width: '<xsl:value-of select="@width"/>', height: '<xsl:value-of select="@height"/>'</xsl:when><xsl:otherwise>, width: '1', height: '1'</xsl:otherwise></xsl:choose>});
          </script>
        </div>
      </xsl:otherwise>
    </xsl:choose>
    <xsl:call-template name="footer"/>
  </xsl:template>
  
  <xsl:template name="footer">
    <xsl:param name="url" select="request/@url"/>
    <xsl:param name="query_args" select="request/@query_args"/>
    <xsl:if test="not($isKranking)">
      <xsl:if test="not(//param[@name='showTaskBar'])">
        <xsl:call-template name="tasks"/>
        <xsl:apply-templates select="info"/>
      </xsl:if>
      <xsl:apply-templates select="warnings"/>
    </xsl:if>
  </xsl:template>
  
  <xsl:template name="tasks">
    <xsl:param name="url" select="request/@url"/>
    <xsl:param name="query_args" select="request/@query_args"/>
    <div id="footer">
      <img src="{$assets}/logo_laszlo_footer.gif" alt="" width="70" height="70"
        style="float: left; margin-right: 15pt"/>
      
      <div style="padding-top: 9px">
        <img src="{$assets}/tasks_title.gif" alt="Tasks" width="54" height="22"/>
        
        <!-- Refresh -->
        <a href="{$url}?{$query_args}" onmousedown="refresh.src='{$assets}/refresh_btn_dn.gif'" onmouseout="refresh.src='{$assets}/refresh_btn_up.gif'">
          <img src="{$assets}/refresh_btn_up.gif" alt="Refresh" name="refresh" width="88" height="22"/>
        </a>
        
        <!-- Debug -->
        <xsl:choose>
          <xsl:when test="/errors or $isKranked or //param[@name='debug' and @value='true']">
            <img src="{$assets}/debug_btn_disable.gif" alt="Debug" width="53" height="22"/>
          </xsl:when>
          <xsl:otherwise>
            <a href="{$url}?debug=true{$query_args}" onmousedown="debug.src='{$assets}/debug_btn_dn.gif'" onmouseout="debug.src='{$assets}/debug_btn_up.gif'">
              <img src="{$assets}/debug_btn_up.gif" alt="Debug" name="debug" width="53" height="22"/>
            </a>
          </xsl:otherwise>
        </xsl:choose>
        
        <!-- View Source -->
        <a href="{$url}?lzt=source" onmousedown="image.src='{$assets}/viewsource_btn_dn.gif'" target="_" onmouseout="image.src='{$assets}/viewsource_btn_up.gif'"><img src="{$assets}/viewsource_btn_up.gif" alt="View Source" name="image" width="84" height="22"/></a>

        <!-- Wrapper -->
        <a href="{$url}?lzt=deployment" onmousedown="wrapper.src='{$assets}/htmlwrapper_btn_dn.gif'" onmouseout="wrapper.src='{$assets}/htmlwrapper_btn_up.gif'">
          <img src="{$assets}/htmlwrapper_btn_up.gif" alt="HTML Wrapper" name="wrapper" width="96" height="22"/>
        </a>

        <!-- Krank -->
        <xsl:if test="not(/canvas/@runtime) or /canvas/@runtime != 'swf7'">
          <img src="{$assets}/krank_title.gif" alt="KRANK" width="52" height="22"
               style="margin-left: 20px"/>
          <!-- Optimize -->
          <a href="{$url}?krank=true{$query_args}" onmousedown="optimize.src='{$assets}/optimize_btn_dn.gif'" onmouseout="optimize.src='{$assets}/optimize_btn_up.gif'">
            <img src="{$assets}/optimize_btn_up.gif" alt="optimize" name="optimize" width="69" height="22"/>
          </a>
          
          <!-- Last Optimized -->
          <xsl:if test="request/@opt-exists = 'true'">
            <a href="{$opturl}?{$query_args}"
               onmousedown="lastOptimized.src='{$assets}/lastoptimize_btn_dn.gif'"
               onmouseout="lastOptimized.src='{$assets}/lastoptimize_btn_up.gif'">
              <img src="{$assets}/lastoptimize_btn_up.gif" alt="Last Optimized" name="lastOptimized" width="99" height="22"/>
            </a>
          </xsl:if>
        </xsl:if>

        <br/>
        
        <xsl:call-template name="support"/>

        <!-- New console controls -->

        <script>
          <![CDATA[

        /**
         * Return just the filename from a forward-slash separated URL.
         * Don't return a query string.
         */
        function baseName( h ) {
            var parts = h.split( "/" );
            var filenameAndQueryString = parts[parts.length-1];
            var parts = filenameAndQueryString.split( "?" );
            return parts[0];
        }

        /**
         * Return the value of the currently selected field in a
         * radio group, or null if none is selected
         */
        function findSelectedRadioField( p ) {
            for ( var i = 0; i < p.length; i++ ) {
                if ( p[i].checked ) return p[i].value;
            }
            return null;
        }

        /**
         * Redirect user based on developer console prefs:
         * If lzx is selected, redirect to .lzx version of file that 
         * user is currently looking at, with debug, and swf6/swf7 
         * querystring params as specified in the form.
         * If lzo is selected, redirect to .lzo version of file that
         * user is currently looking at, with no additional prefs 
         * as querystring params
         */
        function doFormURL( f ){
            var newFileName = ""; // File to redirect to.
            var newURL = ""; // Filename and querystring.
            var currentFileName = baseName( location.href );
            var currentFileNameParts = currentFileName.split(".");
            currentFileNameParts.pop();
            var currentFileNameNoExt = currentFileNameParts.join(".");

            switch ( f.target_app.value ) {
                case "lzx":
                    newFileName = currentFileNameNoExt + ".lzx";
                    var debugParam = ( f.debug.checked ) ? "debug=true" : "debug=false";
                    var lzrParam = "lzr=" + findSelectedRadioField( f.lzr ); 
                    newURL = newFileName + "?" + debugParam + "&" + lzrParam;
                break;

                case "lzo":
                    newFileName = currentFileNameNoExt + ".lzo";
                    newURL = newFileName;
                break;
            }
            location.href= newURL;
        }
        ]]>
        </script>


        <!--

        <form onsubmit="doFormURL( this ); return false;">

          <select name="target_app">
              <option value="lzx"><xsl:value-of select="$unopturl"/></option>
            <xsl:if test="request/@opt-exists = 'true'">
              <option value="lzo"><xsl:value-of select="$opturl"/> (optimized) </option>
            </xsl:if>
          </select>

          <xsl:choose>
            <xsl:when test="//param[@name='debug' and @value='true']">
              <input type="checkbox" name="debug" checked="true" value="true"/> Debug
            </xsl:when>
            <xsl:otherwise>
              <input type="checkbox" name="debug" value="true"/> Debug
            </xsl:otherwise>
          </xsl:choose>



          <xsl:choose>
            <xsl:when test="@runtime = 'swf6'">
              <input type="radio" value="swf6" name="lzr" checked="true"/> swf6
            </xsl:when>
            <xsl:otherwise>
              <input type="radio" value="swf6" name="lzr"/> swf6
            </xsl:otherwise>
          </xsl:choose>


          <xsl:choose>
            <xsl:when test="@runtime = 'swf7'">
              <input type="radio" value="swf7" name="lzr" checked="true"/> swf7
            </xsl:when>
            <xsl:otherwise>
              <input type="radio" value="swf7" name="lzr"/> swf7
            </xsl:otherwise>
          </xsl:choose>


          <input type="submit" value="Go"/>

        </form>

        -->

        <!-- Refresh button -->
        <br/>

        <div style="margin-top: 4px">
          <xsl:if test="$isKranked">
            <img src="{$assets}/optimization_alert.gif"
                 alt="Application has been optimized"
                 width="185" height="10"
              style="margin-left: 12px; padding-bottom: 5px"/>
          </xsl:if>
        </div>
      </div>
    </div>
  </xsl:template>
  

  <xsl:template name="support">
    <img src="{$assets}/resources_title.gif" alt="Resources" width="79" height="22" />
    <a href="{$lps}/docs/index.html" onmousedown="docs.src='{$assets}/doc_btn_dn.gif'" onmouseout="docs.src='{$assets}/doc_btn_up.gif'">
      <img src="{$assets}/doc_btn_up.gif" alt="Documentation" name="docs" width="102" height="22"/></a>

    <a href="http://www.laszlosystems.com/developers" onmousedown="developerNetwork.src='{$assets}/devnet_btn_dn.gif'" onmouseout="developerNetwork.src='{$assets}/devnet_btn_up.gif'">
      <img src="{$assets}/devnet_btn_up.gif" alt="Developer Network" name="developerNetwork" width="122" height="22"/></a>

  <a href="http://www.laszlosystems.com/developers/community/forums/" onmousedown="developerForums.src='{$assets}/devforum_btn_dn.gif'" onmouseout="developerForums.src='{$assets}/devforum_btn_up.gif'">
      <img src="{$assets}/devforum_btn_up.gif" alt="Developer Forums" name="developerForums" width="117" height="22"/></a>
  </xsl:template>

  <xsl:template match="info">
    <xsl:param name="url" select="../request/@url"/>
    <xsl:param name="query_args" select="../request/@query_args"/>
    <xsl:param name="size" select="@size"/>
    <xsl:param name="gzsize" select="@gz-size"/>
    <xsl:param name="runtime" select="@runtime"/>
    <!-- megabytes * 10 -->
    <xsl:param name="mb10" select="round($size * 10 div 1024 div 1024)"/>
    <xsl:param name="gzmb10" select="round($gzsize * 10 div 1024 div 1024)"/>


    <xsl:choose>
      <xsl:when test="@runtime = 'swf5'">
       <div class="info">
         <b>Runtime Target: </b> <xsl:value-of select="$runtime"/><br/>
         <b>Uncompressed application size: </b>
         <!-- print "ddK" or "d.dMB" -->
         <xsl:choose>
           <xsl:when test="$mb10 >= 9">
             <xsl:value-of select="floor($mb10 div 10)"/>
             <xsl:text>.</xsl:text>
             <xsl:value-of select="$mb10 mod 10"/>MB
           </xsl:when>
           <xsl:otherwise>
             <xsl:value-of select="round($size div 1024)"/>K
           </xsl:otherwise>
         </xsl:choose>
         <!-- "(ddd,ddd bytes)" -->
         (<xsl:call-template name="decimal">
           <xsl:with-param name="value" select="$size"/>
         </xsl:call-template> bytes)
         <br/><b>Compressed: </b>
         <xsl:choose>
           <xsl:when test="$gzmb10 >= 9">
             <xsl:value-of select="floor($gzmb10 div 10)"/>
             <xsl:text>.</xsl:text>
             <xsl:value-of select="$gzmb10 mod 10"/>MB
           </xsl:when>
           <xsl:otherwise>
             <xsl:value-of select="round($gzsize div 1024)"/>K
           </xsl:otherwise>
         </xsl:choose>
         <!-- "(ddd,ddd bytes)" -->
         (<xsl:call-template name="decimal">
           <xsl:with-param name="value" select="$gzsize"/>
         </xsl:call-template> bytes)
         <br/><b>Encoding: </b> 
         <xsl:value-of select="@encoding"/>
         <xsl:if test="@encoding = ''">uncompressed</xsl:if>;
         <a target="_blank" 
                 href="{$url}?lzt=info{$query_args}">Size profile</a>
       </div>
       </xsl:when>

       <!-- Flash 6 and greater runtimes don't have auxiliary gzip files -->
       <xsl:otherwise>
         <div class="info">
           <b>Runtime Target: </b> <xsl:value-of select="$runtime"/><br/>
           <b>Application Size: </b>
           <!-- print "ddK" or "d.dMB" -->
           <xsl:choose>
             <xsl:when test="$mb10 >= 9">
               <xsl:value-of select="floor($mb10 div 10)"/>
               <xsl:text>.</xsl:text>
               <xsl:value-of select="$mb10 mod 10"/>MB
             </xsl:when>
             <xsl:otherwise>
               <xsl:value-of select="round($size div 1024)"/>K
             </xsl:otherwise>
           </xsl:choose>
           <!-- "(ddd,ddd bytes)" -->
           (<xsl:call-template name="decimal">
             <xsl:with-param name="value" select="$size"/>
           </xsl:call-template> bytes)
           <a target="_blank" 
                   href="{$url}?lzt=info{$query_args}">Size profile</a>
         </div>
    </xsl:otherwise>
   </xsl:choose>

  </xsl:template>
  
  <!-- prints a decimal with interpolated commas -->
  <xsl:template name="decimal">
    <xsl:param name="value"/>
    <xsl:param name="remainder" select="$value mod 1000"/>
    <xsl:if test="$value &gt; 1000">
      <xsl:call-template name="decimal">
        <xsl:with-param name="value" select="floor($value div 1000)"/>
      </xsl:call-template>
      <xsl:text>,</xsl:text>
      <xsl:if test="$remainder &lt; 100">0</xsl:if>
      <xsl:if test="$remainder &lt; 10">0</xsl:if>
    </xsl:if>
    <xsl:value-of select="$remainder"/>
  </xsl:template>

  <xsl:template match="canvas/warnings">
    <div id="warnings">
      <h2>Compilation Warnings</h2>
      <pre class="warning">
        <xsl:for-each select="error">
          <xsl:if test="position() > 1">
            <br/>
          </xsl:if>
          <xsl:apply-templates select="."/>
        </xsl:for-each>
      </pre>
    </div>
  </xsl:template>
  
  <xsl:template match="error">
    <xsl:choose>
      <xsl:when test="starts-with(text(), '.tmp_')">
        <xsl:value-of select="substring-after(substring-after(text(), '_'), '_')"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="text()"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template match="/errors">
    <div style="border-top: 1px solid; border-bottom: 1px solid; background-color: #fcc; padding: 1pt; padding-left: 2pt">
      The application could not be compiled due to the following errors:
    </div>
    <h1>Compilation Errors</h1>
    <code class="error">
      <xsl:apply-templates select="error"/>
    </code>
    <xsl:if test="error/error">
      <div id="warnings">
        <h2>Compilation Warnings</h2>
        <pre class="warning">
          <xsl:for-each select="error/error">
            <xsl:if test="position() > 1">
              <br/>
            </xsl:if>
            <xsl:apply-templates select="."/>
          </xsl:for-each>
        </pre>
      </div>
    </xsl:if>
      
    <xsl:call-template name="footer"/>
  </xsl:template>

</xsl:stylesheet>
