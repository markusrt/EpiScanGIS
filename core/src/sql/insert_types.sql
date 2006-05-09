INSERT INTO case_types (identifier) (
  SELECT concat_type FROM (
    SELECT DISTINCT (sg || ';' || vr1|| ';' || vr2|| ';' || feta) as concat_type FROM nrzm_imprt
  ) AS distinct_types 
  WHERE concat_type NOT IN (SELECT identifier FROM case_types)
);