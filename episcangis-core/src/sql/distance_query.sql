SELECT the_geom FROM locations
WHERE the_geom && 'BOX3D(12.71 50.07, 14.71 52.07)'::box3d
  AND distance( the_geom, GeometryFromText( 'POINT(13.71 51.07)', -1 ) ) < 1