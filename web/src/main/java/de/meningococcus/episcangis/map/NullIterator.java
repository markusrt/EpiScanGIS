package de.meningococcus.episcangis.map;

import java.util.Iterator;

/* ====================================================================
 *   Copyright ©2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class NullIterator<E> implements Iterator<E>
{

  public NullIterator()
  {
  }

  public boolean hasNext()
  {
    return false;
  }

  public E next()
  {
    return null;
  }

  public void remove()
  {
    throw new UnsupportedOperationException();
  }

}
