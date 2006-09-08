package de.meningococcus.episcangis.map;

import java.util.Collection;
import java.util.Iterator;

/**
 * <p>
 * ParameterComponent represents an abstract superclass for a component that
 * holds values and is identified by a unique name. It is the interface for any
 * client, that wants to store parameters with values.
 * </p>
 * <p>
 * This class is part of the <a
 * href="http://en.wikipedia.org/wiki/Composite_pattern">Composite Pattern</a>
 * formed by ParameterComponent, ParameterValue, ParameterComposite
 * </p>
 */
public abstract class ParameterComponent implements
    Iterable<ParameterComponent>
{

  /**
   * <p>
   * This components name. Subclasses need to ensure by naming, that the name is
   * unique for the whole tree hierarchie of ParameterComponents
   * </p>
   */
  private String name;

  protected String elementName = "component";

  /**
   * <p>
   * The title represents a human readable ParameterComponent description or
   * alias
   * </p>
   */
  private String title;

  /**
   * @param name
   *          this components name
   */
  public ParameterComponent(String name)
  {
    this(name, "");
  }

  /**
   * @param name
   *          this components name
   * @param title
   *          this components title
   */
  public ParameterComponent(String name, String title)
  {
    this.name = name;
    this.title = title;
  }

  /**
   * <p>
   * Adds a new sub-ParameterComponent for this ParameterComponent. This method
   * is not supported for the base ParameterComponent because it does not hold
   * any other components
   * </p>
   *
   * @param element
   *          ParameterComponent to add
   */
  public void add(ParameterComponent element)
  {
    throw new UnsupportedOperationException(this.getClass().getSimpleName()
        + " must not contain ParameterComponents");
  }

  /**
   * <p>
   * Template method. Called by setValue(String name, String value) and
   * add(ParameterComponent element)
   * </p>
   *
   * @param elementName
   *          name of the ParameterComponent that should be returned
   * @return resulting component or null if not found
   * @throws ParameterNotFoundException
   * @see ParameterComponent#setValue(String)
   * @see ParameterComponent#add(ParameterComponent)
   */
  public ParameterComponent get(String elementName)
  {
    if (getName().equals(elementName))
    {
      return this;
    }
    else
    {
      for (ParameterComponent next : this)
      {
        if (!(next instanceof ParameterValue))
        {
          ParameterComponent result = next.get(elementName);
          if (result != null)
          {
            return result;
          }
        }
      }
    }
    return null;
  }

  /**
   * @return this ParameterElements value as String
   */
  public abstract String getValue();

  /**
   * @param parameterName
   * @return the value of the named Parameter as String
   * @throws ParameterNotFoundException
   *           if named Parameter is not found
   */
  public String getValue(String parameterName)
      throws ParameterNotFoundException
  {
    return get(parameterName).getValue();
  }

  /**
   * @return this ParameterElements aliasvalue as String
   */
  public abstract String getAliasValue();

  /**
   * @param parameterName
   * @return the aliasvalue of the named Parameter as String
   * @throws ParameterNotFoundException
   *           if named Parameter is not found
   */
  public String getAliasValue(String parameterName)
      throws ParameterNotFoundException
  {
    return get(parameterName).getAliasValue();
  }

  /**
   * <p>
   * Template method. Called by selectValue(String name, String value)
   * </p>
   *
   * @param value
   *          new value for this ParameterComponent
   */
  public void selectValue(String value) throws InvalidParameterValueException
  {
    this.selectValue(value, true);
  }

  /**
   * Selects or unselects a value depending on selection
   *
   * @param value
   *          value to mark
   * @param selection
   *          true=select, false=unselect
   */
  public void selectValue(String value, boolean selection)
      throws InvalidParameterValueException {
    for (ParameterComponent pc : this)
    {
      pc.selectValue(value, selection);
    }
  }

  /**
   * <p>
   * Select a value by name. Calls the template method selectValue(String) in
   * case that the template method getElement(element.getName()) does not return
   * null
   * </p>
   *
   * @param parameterName
   *          name of ParameterComponent to change value for
   * @param selectedValue
   *          new value for named ParameterComponent
   * @throws ParameterNotFoundException
   * @see ParameterComponent#setValue(String)
   * @see ParameterComponent#get(String)
   */
  public final void selectValue(String parameterName, String selectedValue)
      throws ParameterNotFoundException, InvalidParameterValueException
  {
    ParameterComponent element = get(parameterName);
    if (element != null)
    {
      element.selectValue(selectedValue);
    }
    else
      throw new ParameterNotFoundException();
  }

  public abstract boolean isSelected();

  public abstract void setSelected(boolean selection);

  public abstract Iterator<ParameterComponent> oldIterator();

  public abstract Iterator<ParameterComponent> iterator();

  /**
   * @return this ParameterComponents name
   */
  public String getName()
  {
    return name;
  }

  /**
   * @return this ParameterComponents title
   */
  public String getTitle()
  {
    return title;
  }

  abstract public String toXML();
}
