-- Function: episcangis_nrzm_import()

-- DROP FUNCTION episcangis_nrzm_import(filename "varchar");

CREATE OR REPLACE FUNCTION episcangis_nrzm_import()
  RETURNS int4 AS
$BODY$
DECLARE
  pg_class_row pg_class%ROWTYPE;
BEGIN
  -- Check if import table exists
  SELECT * INTO pg_class_row FROM pg_class WHERE relname='nrzm_imprt';
  IF pg_class_row.relname IS NULL THEN
    RAISE EXCEPTION 'Import failed, table ''nrzm_imprt'' does not exits.';
  ELSE
    -- Insert new casetypes in table (with default timestamp=now)
    INSERT INTO case_types (identifier) (
      SELECT concat_type FROM (
        SELECT DISTINCT (sg || ';' || vr1|| ';' || vr2|| ';' || feta) as concat_type FROM nrzm_imprt
      ) AS distinct_types  
      WHERE concat_type NOT IN (SELECT identifier FROM case_types)
    );
    -- Refresh case_types_attributes
    DELETE FROM case_type_attributes;
    INSERT INTO case_type_attributes (case_type_id, attribute_id, value) (
      SELECT ct.case_type_id, a.attribute_id, split_part(ct.identifier,';',1) 
        FROM case_types ct, attributes a WHERE a.name='Serogroup'
    );
    -- Refresh cases table
    DELETE FROM cases WHERE case_id IN (SELECT pnr FROM nrzm_imprt);
    INSERT INTO cases (
      SELECT 
        pnr,
        CASE WHEN altr='' THEN
          -1
        ELSE
          CAST( altr AS int2 )
        END AS altr,
        '?',
        --CAST( geschlecht AS char ),
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
    SELECT * INTO pg_class_row FROM pg_class WHERE relname='contains_area_case';
    IF pg_class_row.relname IS NOT NULL THEN
      DROP TABLE contains_area_case;
    END IF;
    CREATE TABLE contains_area_case AS
    SELECT area_id, case_id
      FROM areas, cases 
      WHERE areas.the_geom && cases.the_geom 
        AND contains(areas.the_geom, cases.the_geom);
  END IF;
  RETURN 0;
END
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION episcangis_nrzm_import() OWNER TO "postgres";
GRANT EXECUTE ON FUNCTION episcangis_nrzm_import() TO public;
