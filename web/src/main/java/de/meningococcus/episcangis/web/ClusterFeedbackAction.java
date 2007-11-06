package de.meningococcus.episcangis.web;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.ClusterFeedbackDAO;
import de.meningococcus.episcangis.db.model.ClusterFeedback;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorForm;

public class ClusterFeedbackAction extends DispatchAction {
    private static Log log;

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException, IllegalAccessException, InvocationTargetException
    {
        String forward = "error";
        String tan = request.getParameter( "tan" );
        ActionMessages errors = new ActionMessages();
        Object feedbackForm = (DynaValidatorForm) form;
        ClusterFeedback feedback = DaoFactory.getDaoFactory().getClusterFeedbackDAO().findByTan( tan );

        if( feedback == null )
        {
            errors.add( "org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage( "errors.tan.invalid" ) );
            forward = "tan";
        }
        else
        {
            Iterator i = null;

            BeanUtils.copyProperties( feedbackForm, feedback );
            i = ((DynaValidatorForm) feedbackForm).getMap().keySet().iterator();
            while( i.hasNext() )
            {
                String key = (String) i.next();

                if( !key.endsWith( "Question" ) || ((DynaValidatorForm) feedbackForm).get( key.substring( 0, key.indexOf( "Question" ) ) ) == null || ((String) ((DynaValidatorForm) feedbackForm).get( key.substring( 0, key.indexOf( "Question" ) ) )).length() <= 0 )
                    continue;
                ((DynaValidatorForm) feedbackForm).set( key, Boolean.valueOf( true ) );
            }
            forward = "input";
        }
        saveErrors( request, errors );
        return mapping.findForward( forward );
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException, IllegalAccessException, InvocationTargetException
    {
        String forward = "error";
        ActionMessages errors = new ActionMessages();
        Object feedbackForm = (DynaValidatorForm) form;
        Object i = ((DynaValidatorForm) feedbackForm).getMap().keySet().iterator();
        Object key = null;

        while( ((Iterator) i).hasNext() )
        {
            key = (String) ((Iterator) i).next();
            if( !((String) key).endsWith( "Question" ) || ((Boolean) ((DynaValidatorForm) feedbackForm).get( (String) key )).booleanValue() )
                continue;
            ((DynaValidatorForm) feedbackForm).set( ((String) key).substring( 0, ((String) key).indexOf( "Question" ) ), null );
        }
        i = DaoFactory.getDaoFactory().getClusterFeedbackDAO();
        key = ((ClusterFeedbackDAO) i).findByTan( ((DynaValidatorForm) feedbackForm).getString( "tan" ) );
        if( key == null )
        {
            errors.add( "org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage( "errors.tan.invalid" ) );
            forward = "tan";
        }
        else
        {
            BeanUtils.copyProperties( key, feedbackForm );
            DaoFactory.getDaoFactory().getClusterFeedbackDAO().updateClusterFeedback( (ClusterFeedback) key );
            forward = "success";
        }
        saveErrors( request, errors );
        return mapping.findForward( forward );
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException, IllegalAccessException, InvocationTargetException
    {
        ClusterFeedbackDAO cfDao = DaoFactory.getDaoFactory().getClusterFeedbackDAO();
        Collection feedback = cfDao.findAll();

        request.setAttribute( "feedback", feedback );
        return mapping.findForward( "list" );
    }
}