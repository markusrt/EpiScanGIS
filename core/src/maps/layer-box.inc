LAYER #box
  # DUMP TRUE
  NAME box
  DEBUG ON
  METADATA
    "wms_title" "Box"
  END
  INCLUDE "connection.inc"
  DATA "the_geom FROM (
    SELECT id, the_geom FROM boxes )
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
END #Layer box
