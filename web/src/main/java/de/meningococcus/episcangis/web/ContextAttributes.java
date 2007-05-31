package de.meningococcus.episcangis.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.map.ClusterMap;
import de.meningococcus.episcangis.map.MapInitializationException;

public class ContextAttributes implements PlugIn {

	public static final String SATSCAN_DAO = "ssDao";

	public static final String USER_DAO = "userDao";

	public static final String REPORTEDCASE_DAO = "rcDao";

	public static final String CLUSTER_MAP = "clusterMap";

	private static ServletContext context;

	private static Log log = LogFactory.getLog(ContextAttributes.class);

	public ContextAttributes() {
	}

	public void destroy() {
	}

	public void init(ActionServlet servlet, ModuleConfig config)
			throws ServletException {
		try {
			context = servlet.getServletContext();
			context.setAttribute("ssDao", DaoFactory.getDaoFactory().getSatScanDAO());
			context.setAttribute("userDao", DaoFactory.getDaoFactory().getUserDAO());
			context.setAttribute("rcDao", DaoFactory.getDaoFactory()
					.getReportedCaseDAO());
			context.setAttribute("clusterMap", new ClusterMap(480, 640));
		}
		catch (MapInitializationException e) {
			log.error((new StringBuilder("Global map initialization failed: "))
					.append(e.getMessage()).toString());
		}
	}

	public static SatScanDAO getSatScanDAO() {
		return (SatScanDAO) context.getAttribute("ssDao");
	}

	public static UserDAO getUserDAO() {
		return (UserDAO) context.getAttribute("userDao");
	}

	public static ReportedCaseDAO getReportedCaseDAO() {
		return (ReportedCaseDAO) context.getAttribute("rcDao");
	}

	public static ClusterMap getClusterMap() {
		return (ClusterMap) context.getAttribute("clusterMap");
	}

}