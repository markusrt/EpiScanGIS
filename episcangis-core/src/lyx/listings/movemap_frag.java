public ActionForward execute([...])
      throws Exception
{
  String forward = "error";
  MoveMapFormBean move = (MoveMapFormBean) form;
  AbstractWmsMap map = (AbstractWmsMap)request.
    getSession().getAttribute("map");
  if (map != null)
  {
    map.move(move.getXoffset(), move.getYoffset());
    forward = "success";
  }
  return (mapping.findForward(forward));
}
