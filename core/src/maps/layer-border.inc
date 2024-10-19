LAYER #borders
  TEMPLATE "DUMMY"
  VALIDATION
    "BORDERTIER" "^[0-9]$"
  END
  NAME borders_default
  METADATA
    "wms_title" "Borders"
    "wms_include_items" "all"
  END
  INCLUDE "connection.inc"
  DATA "the_geom FROM (
    SELECT areas.area_id, the_geom, areas.identifier AS areaname,
      area_types.tier AS depth, areas.area_id AS Id
      FROM areas NATURAL JOIN area_types 
      WHERE tier=%BORDERTIER% OR tier=2 
      ORDER BY tier ASC
      ) 
    AS foo USING SRID=4326 USING UNIQUE area_id"
  SYMBOLSCALEDENOM 3000000
  STATUS ON 
  TYPE POLYGON
  PROJECTION
    "init=epsg:4326"
  END

  CLASS
    EXPRESSION ([depth] > 2)
    STYLE
      OUTLINECOLOR 0 0 0 
      WIDTH 1 
    END
  END
END #Layer borders
