SELECT reported_cases.*,plz99_n FROM locations, reported_cases, nrzm_imprt_tmp 
  WHERE the_geom && place_geom
  AND contains(the_geom, place_geom)
  AND pnr=id