SELECT case_id AS oid, the_geom
  FROM cases
  WHERE reportdate 
        BETWEEN to_timestamp('%fromDate%', 'MMYYYY') 
                AND to_timestamp('%toDate%', 'MMYYYY');
