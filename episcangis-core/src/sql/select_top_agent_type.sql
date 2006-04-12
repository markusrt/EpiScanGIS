SELECT X(centroid(the_geom)), Y(centroid(the_geom)) FROM reported_cases, locations 
  WHERE agent_type IN
  ( SELECT agent_type
    FROM reported_cases 
    GROUP BY agent_type 
    ORDER BY count(*) DESC 
    LIMIT 1 ) 
  AND place_of_incidence = gid 