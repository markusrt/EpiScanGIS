package de.meningococcus.episcangis.web;

import java.sql.Date;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.model.ReportedCase;

public class InitializeWebapp implements PlugIn
{

  public void destroy()
  {
    // TODO Auto-generated method stub
  }

  public void init(ActionServlet servlet, ModuleConfig config)
      throws ServletException
  {
    //  TODO Auto-generated method stub
  }
}
