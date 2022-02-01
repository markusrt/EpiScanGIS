LAYER #voronoi
  # DUMP TRUE
  NAME voronoi
  DEBUG ON
  METADATA
    "wms_title" "Voronoi"
  END
  INCLUDE "connection.inc"
  DATA "polygon FROM (
    SELECT id, polygon FROM vornoi_areas ) 
    AS foo USING SRID=4326 USING UNIQUE id"
  SYMBOLSCALE 3000000
  STATUS ON 
  TYPE POLYGON
  PROJECTION
    "init=epsg:4326"
  END

  CLASS
    STYLE
      OUTLINECOLOR 0 0 0 #169 169 169
      SYMBOL 'circle'
      MAXSIZE 1
    END
  END
END #Layer borders
