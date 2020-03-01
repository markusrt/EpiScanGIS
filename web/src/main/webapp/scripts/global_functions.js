/*
function getBaseUrl() {
  var url = new LzUrl();
  url = lz.Browser.getLoadURLAsLzURL();

  return url.protocol + "://"
    + url.host + ":" + url.port + url.path;
}
*/

// Non proxied app
function getBaseUrl() {
  var url = new LzURL();
  url = lz.Browser.getLoadURLAsLzURL();
  
  return url.protocol + ":" /*+ "//"
    + url.host + ":" + url.port + */;
}

function addParamToURL(url, name, value) {
  url = remParamFromURL(url,name);
  if( name != undefined && value != undefined ) {
    url += "&" + name + "=" + value;
  }
  return url;
}

function remParamFromURL(url, name) {
  var para = getParamFromURL(url,name);
  if( para.length > 0 ) {
    var index = url.indexOf(para);
    url = url.substring(0, index) 
      + url.substring(index + para.length, url.length);
  }
  return url;
}

function getParamFromURL(url, name) {
  var paraIndex = url.indexOf( "&" + name + "=" );
  var para = "";
  if( paraIndex != -1) {
    var nextParaIndex = url.indexof( "&", paraIndex+1 );
    if( nextParaIndex == -1 ) {
      nextParaIndex=url.length;
    }
    para = url.substring(paraIndex, nextParaIndex);
  }
  return para;
}

String.prototype.replace = function(from, to) {
  if (this.indexOf(from) != -1) {
    var tokens = this.split(from);
    var rep=0;
    this = "";
    for( rep=0; rep<tokens.length; rep++ )
    {
      if(rep==0) {
        this += tokens[rep];
      }
      else {
        this += to + tokens[rep];
      }
    }
  }
  return this;
}
