package de.meningococcus.episcangis.map;

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
public abstract class ParameterComponent
{

  /**
   * <p>
   * This components name. Subclasses need to ensure by naming, that the name is
   * unique for the whole tree hierarchie of ParameterComponents
   * </p>
   */
  private String name;

  /**
   * <p>
   * The title represents a human readable ParameterComponent description or
   * alias
   * </p>
   */
  private String title;

  /**
   * @param name this components name
   */
  public ParameterComponent(String name)
  {
    this(name, "");
  }

  /**
   * @param name this components name
   * @param title this components title
   */
  public ParameterComponent(String name, String title)
  {
    this.name = name;
    this.title = title;
  }

  /**
   * <p>
   * Adds a new sub-ParameterComponent for this ParameterComponent. This method
   * calls the template method addElement in case that the template method
   * getElement(element.getName()) returns null.
   * </p>
   * @param element ParameterComponent to add
   */
  public final void add(ParameterComponent element)
  {
    if (getElement(element.getName()) == null)
    {
      addElement(element);
    }
  }

  /**
   * <p>
   * Set a value by name. Calls the template method setValue(String) in case
   * that the template method getElement(element.getName()) does not return null
   * </p>
   * @param name name of ParameterComponent to change value for
   * @param value new value for named ParameterComponent
   * @see ParameterComponent#setValue(String)
   * @see ParameterComponent#getElement(String)
   */
  public final void setValue(String name, String value)
  {
    ParameterComponent element = getElement(name);
    if (element != null)
    {
      element.setValue(value);
    }
  }

  /**
   * <p>
   * Template method. Called by setValue(String name, String value)
   * </p>
   * @param value new value for this ParameterComponent
   */
  abstract public void setValue(String value);

  /**
   * <p>
   * Template method. Called by setValue(String name, String value) and
   * add(ParameterComponent element)
   * </p>
   * @param name name of the ParameterComponent that should be returned
   * @return resulting component or null if not found
   * @see ParameterComponent#setValue(String)
   * @see ParameterComponent#add(ParameterComponent)
   */
  protected abstract ParameterComponent getElement(String name);

  /**
   * <p>
   * Template method. Called by add(ParameterComponent element)
   * </p>
   * @param element
   */
  protected abstract void addElement(ParameterComponent element);

  /**
   * @return this ParameterElements value as String
   */
  public abstract String getValue();

  /**
   * Selects or unselects a value depending on selection
   * @param value value to mark
   * @param selection true=select, false=unselect
   */
  public abstract void selectValue(String value, boolean selection);

  public abstract boolean isSelected();

  public abstract void setSelected(boolean selection);

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
}
