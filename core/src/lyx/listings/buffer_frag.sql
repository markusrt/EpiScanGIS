transform(
  setSRID( 
    buffer(
      transform(centroid(the_geom),31467), radius*1000)
    , 31467)
  , 4326)
