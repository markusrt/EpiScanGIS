package de.meningococcus.episcangis.map;

public class LayerState
{
  boolean active;
  String name;
  
  LayerState( boolean isActive, String layerName ) {
    active = isActive;
    name = layerName;
  }
  
  /**
   * @return Returns the active.
   */
  public boolean isActive()
  {
    return active;
  }
  /**
   * @return Returns the name.
   */
  public String getName()
  {
    return name;
  }
  

}
