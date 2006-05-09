CREATE OR REPLACE VIEW inci AS
SELECT locations.oid, to_char(reported_cases.reportdate,'YY') AS r_year, 
       locations.identifier,
       transform(locations.the_geom,31467) AS the_geom,
       CAST(locations.population AS real)/100000 AS population,	
       (CAST( count(*) AS real)/(CAST(locations.population AS real)/100000)) AS incidence   
  FROM locations, location_types, reported_cases
  WHERE location_types.id = locations.type_ref 
    AND location_types.tier = 3 
    AND location_types.country::text = 'Germany'::text 
    AND locations.the_geom && reported_cases.the_geom 
    AND contains(locations.the_geom, reported_cases.the_geom)
    AND to_char(reported_cases.reportdate,'YY')='04'
  GROUP BY locations.oid, r_year, locations.identifier, locations.population, locations.the_geom