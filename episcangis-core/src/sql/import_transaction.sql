BEGIN;
  DELETE FROM nrzm_imprt;
  COPY nrzm_imprt FROM '/tmp/epidegis_data-20051116.csv' WITH DELIMITER ';';
  INSERT INTO case_types (identifier) (
    SELECT concat_type FROM (
      SELECT DISTINCT (sg || ';' || vr1|| ';' || vr2|| ';' || feta) as concat_type FROM nrzm_imprt
    ) AS distinct_types  
    WHERE concat_type NOT IN (SELECT identifier FROM case_types)
  );
  DELETE FROM case_type_attributes;
  INSERT INTO case_type_attributes (case_type_id, attribute_id, value) 
    SELECT ct.case_type_id, a.attribute_id, split_part(ct.identifier,';',1) 
    FROM case_types ct, attributes a WHERE a.name='Serogroup';
  DELETE FROM cases2;
  INSERT INTO cases2 (
    SELECT 
      pnr,
      CASE WHEN altr='' THEN
        -1
      ELSE
        CAST( altr AS int2 )
      END AS altr,
      '?',
      --CAST( geschlecht AS char ),
      --plz_n, 
      CASE WHEN eingang='' THEN
        NULL
      ELSE
        CAST( ( split_part(eingang, '.', 2) || '/' ||
                split_part(eingang, '.', 1) || '/' ||
                split_part(eingang, '.', 3) ) AS date )
      END AS eingang,
      CASE WHEN entnahme='' THEN
        CAST( ( split_part(eingang, '.', 2) || '/' ||
                split_part(eingang, '.', 1) || '/' ||
                split_part(eingang, '.', 3) ) AS date )
      ELSE
        CAST( ( split_part(entnahme, '.', 2) || '/' ||
                split_part(entnahme, '.', 1) || '/' ||
                split_part(entnahme, '.', 3) ) AS date )
      END AS entnahme,
      case_type_id,
      GeometryFromText('POINT(' || replace(lon,',','.') || ' ' || replace(lat,',','.') || ')',4326)
      FROM nrzm_imprt, case_types
      WHERE (sg || ';' || vr1|| ';' || vr2|| ';' || feta)=case_types.identifier
  );

  -- create the table contains_area_case
  -- this will speedup incidence calculations as contains() is very slow
  --DELETE FROM contains_area_case;
  --CREATE TABLE contains_area_case AS
  --SELECT area_id, case_id
  --  FROM areas, cases 
  --  WHERE areas.the_geom && cases.the_geom 
  --    AND contains(areas.the_geom, cases.the_geom);
COMMIT;