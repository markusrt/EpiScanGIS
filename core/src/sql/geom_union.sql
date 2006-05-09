SELECT agent_type, count(*) AS anzahl 
  FROM reported_cases 
  GROUP BY agent_type 
  ORDER BY anzahl DESC 
  LIMIT 1