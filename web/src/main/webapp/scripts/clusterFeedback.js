function checkDetail() {
  var inputs = Form.getInputs('clusterFeedback', 'text');
  var allNo = new Boolean(true);

  for (var i=0; i < inputs.length; i++) {
    var answer;
    Form.getInputs('clusterFeedback','radio').each(
      function(input) {
        if(input.name==inputs[i].name+'Question' && input.checked )
        {
          answer=input.value;
        }
      }
    );
    if(answer=='true') {
      Element.show(inputs[i].name);
      if(i<inputs.length-3) allNo = false;
    }
    else Element.hide(inputs[i].name);
  }
  if(allNo) Element.hide('clusterbekannt');
  else Element.show('clusterbekannt');
}

function requestClusterInformation() {
  new Ajax.Updater('detailInformation','/episcangis-web/ClusterInformation.do', {method:'post', postBody:'clusterId=499&target=inline&layout=blank.vm'});
}