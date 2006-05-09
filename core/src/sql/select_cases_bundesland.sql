SELECT locations.oid, reported_cases.*, locations.identifier 
  FROM locations,location_types,reported_cases
  WHERE location_types.id=type_ref AND tier=3 AND country='Germany'
  AND the_geom && place_geom
  AND contains(the_geom,place_geom)