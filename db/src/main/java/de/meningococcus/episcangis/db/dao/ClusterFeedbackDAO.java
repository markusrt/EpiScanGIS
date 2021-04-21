package de.meningococcus.episcangis.db.dao;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.util.Collection;

import de.meningococcus.episcangis.db.model.ClusterFeedback;
import de.meningococcus.episcangis.db.model.User;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public interface ClusterFeedbackDAO
{
  /**
   * @param tan  search for specific cluster feedback by tan
   * @return User object or null if not found
   */
  public ClusterFeedback findByTan(String tan);

  /**
   * @return collection of all possible cluster feedbacks, never null
   */
  public Collection<ClusterFeedback> findAll();

  /**
   * @param feedback cluster feedback to insert in datastore
   * @return number of inserted cluster feedbacks
   */
  public void createClusterFeedback(ClusterFeedback feedback);

  /**
   * @param feedback cluster feedback to update in datastore
   * @return number of updated cluster feedbacks
   */
  public void updateClusterFeedback(ClusterFeedback feedback);

  /**
   * @param feedback cluster feedback to remove from datastore
   * @return number of deleted cluster feedbacks
   */
  public void deleteClusterFeedback(ClusterFeedback feedback);
}
