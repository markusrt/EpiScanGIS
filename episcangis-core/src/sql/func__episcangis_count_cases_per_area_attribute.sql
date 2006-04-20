CREATE FUNCTION episcangis_count_cases_per_area_attribute(varchar,smallint) RETURNS SETOF record AS $$
SELECT 0 AS count, value, identifier 
 FROM areas_with_types AS awt, case_type_attributes NATURAL JOIN attributes
 WHERE tier=$2 AND  attributes.name=$1 AND value!=''
 AND (value,identifier) NOT IN
  (
    SELECT value, areas.identifier
     FROM areas NATURAL JOIN area_types, contains_area_case AS cac, 
       cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes 
       NATURAL JOIN attributes
     WHERE attributes.name=$1 AND value!=''
       AND cac.area_id=areas.area_id
       AND cac.case_id=cases.case_id
       AND tier=$2
     GROUP BY value, areas.identifier
   )
  GROUP BY value, awt.identifier
UNION   
SELECT count(*), value, areas.identifier
 FROM areas NATURAL JOIN area_types, contains_area_case AS cac, 
   cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes 
   NATURAL JOIN attributes
 WHERE attributes.name=$1 AND value!=''
   AND cac.area_id=areas.area_id
   AND cac.case_id=cases.case_id
   AND tier=$2
 GROUP BY value, areas.identifier    
ORDER BY identifier,value
$$ LANGUAGE SQL;