package de.meningococcus.episcangis.web;

import java.security.Principal;

public class MockPrincipal implements Principal
{
  String name;
  
  public MockPrincipal(String name ){
    this.name = name;
  }
  
  public String getName()
  {
    return name;
  }

}
