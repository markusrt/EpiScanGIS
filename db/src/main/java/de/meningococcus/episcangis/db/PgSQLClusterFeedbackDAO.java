package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import de.meningococcus.episcangis.db.dao.ClusterFeedbackDAO;
import de.meningococcus.episcangis.db.model.ClusterFeedback;

/**
 * Implementation of the DAO ClusterFeedbackDAO. This class uses Hibernate to
 * run queries on the database and fill beans with the results.
 *
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class PgSQLClusterFeedbackDAO extends HibernateDAO implements
    ClusterFeedbackDAO
{
  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(PgSQLClusterFeedbackDAO.class);

  public void createClusterFeedback(ClusterFeedback feedback)
  {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    SecureRandom random = new SecureRandom();
    String tan;
    ClusterFeedback tanFeedback;
    int tanLength = 8, countTries = 0;
    do
    {
      if (countTries > 5)
      {
        tanLength++;
        countTries = 0;
      }
      tan = RandomStringUtils.random(tanLength, 0, 0, true, true, null, random);
      tanFeedback = (ClusterFeedback) session.createQuery(
          "from ClusterFeedback where tan=?").setString(0, tan).uniqueResult();
      countTries++;
    } while (tanFeedback != null);
    feedback.setTan(tan);
    session.save(feedback);
    session.getTransaction().commit();
  }

  public void deleteClusterFeedback(ClusterFeedback feedback)
  {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    session.delete(feedback);
    session.getTransaction().commit();
  }

  public ClusterFeedback findByTan(String tan)
  {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    ClusterFeedback feedback = (ClusterFeedback) session.createQuery(
        "from ClusterFeedback where tan=?").setString(0, tan).uniqueResult();
    session.close();
    return feedback;
  }

  @SuppressWarnings("unchecked")
  public Collection<ClusterFeedback> findAll()
  {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    List<ClusterFeedback> result = session.createQuery("from ClusterFeedback")
        .list();
    session.getTransaction().commit();
    return result;
  }

  public void updateClusterFeedback(ClusterFeedback feedback)
  {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    session.beginTransaction();
    session.update(feedback);
    session.getTransaction().commit();
  }

}
