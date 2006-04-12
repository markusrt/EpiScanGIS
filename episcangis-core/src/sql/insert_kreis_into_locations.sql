INSERT INTO locations (plz99,plzort99,centroid) SELECT kreis_nr,name, 
  GeometryFromText('POINT(' || longitude || ' ' || latitude || ')',4326) 
  FROM imprt_kreis