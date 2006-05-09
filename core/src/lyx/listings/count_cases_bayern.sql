SELECT count(*) FROM areas, cases
  WHERE areas.the_geom && cases.the_geom
    AND areas.identifier = 'Bayern'
    AND contains(areas.the_geom, cases.the_geom)
