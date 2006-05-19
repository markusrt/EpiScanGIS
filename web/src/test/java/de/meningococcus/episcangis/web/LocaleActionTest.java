package de.meningococcus.episcangis.web;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import junit.framework.Test;
import junit.framework.TestSuite;

public class LocaleActionTest extends AbstractStrutsTestCase
{
  public LocaleActionTest(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(LocaleActionTest.class);
  }

  public void testSwitchLocale()
  {
    setRequestPathInfo("/Locale");
    addRequestParameter("language", "en");
    actionPerform();
    Locale locale = (Locale) request.getSession().getAttribute(
        "org.apache.struts.action.LOCALE");
    assertEquals("en", locale.getLanguage() );
    
    addRequestParameter("language", "de");
    actionPerform();
    locale = (Locale) request.getSession().getAttribute(
      "org.apache.struts.action.LOCALE");
    assertEquals("de", locale.getLanguage() );
  }

}
