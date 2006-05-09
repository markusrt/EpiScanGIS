SELECT c.*, ct.identifier
  FROM cases AS c, satscan_clusters AS sc, satscan_cluster_cases AS scc, 
    case_types AS ct
  WHERE c.case_type_id = ct.case_type_id AND
    sc.satscan_cluster_id = scc.satscan_cluster_id AND
    c.case_id = scc.case_id