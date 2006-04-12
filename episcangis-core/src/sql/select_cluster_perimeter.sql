SELECT buffer(centroid(areas.the_geom),circleradius/100)
  FROM areas, satscan_clusters
  WHERE areas.area_id=satscan_clusters.area_id