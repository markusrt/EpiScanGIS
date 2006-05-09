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
