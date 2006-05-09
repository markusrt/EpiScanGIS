package de.meningococcus.episcangis.map;

import java.util.Iterator;
import java.util.Stack;

/* ====================================================================
 *   Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ParameterComponentIterator implements Iterator<ParameterComponent>
{
  Stack<Iterator<ParameterComponent>> stack = 
    new Stack<Iterator<ParameterComponent>>();

  public ParameterComponentIterator(Iterator<ParameterComponent> iterator)
  {
    stack.push(iterator);
  }

  public boolean hasNext()
  {
    if(stack.isEmpty()) {
      return false;
    }
    else {
      if( !stack.peek().hasNext() ) {
        stack.pop();
        return hasNext();
      }
      else {
        return true;
      }
    }
  }

  public ParameterComponent next()
  {
    if (hasNext())
    {
      ParameterComponent parameterComponent = stack.peek().next();
      stack.push(parameterComponent.iterator());
      return parameterComponent;
    }
    else {
      return null;
    }
  }

  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}
