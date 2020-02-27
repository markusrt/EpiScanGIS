LAYER #borders
  TEMPLATE "DUMMY"
  DUMP TRUE
  NAME borders_default
  DEBUG ON
  METADATA
    "wms_title" "Borders"
    "wms_include_items" "all"
  END
  INCLUDE "connection.inc"
  DATA "the_geom FROM (
    SELECT areas.oid, the_geom, areas.identifier AS areaname,
      area_types.tier AS depth, areas.area_id AS Id
      FROM areas NATURAL JOIN area_types
      WHERE tier=%BORDERTIER% OR tier=2
      ORDER BY tier ASC
      )
    AS foo USING SRID=4326 USING UNIQUE oid"
  SYMBOLSCALE 3000000
  STATUS ON
  TYPE POLYGON
  PROJECTION
    "init=epsg:4326"
  END

  CLASS
    EXPRESSION ([depth] = 2)
    STYLE
      OUTLINECOLOR 0 0 0 #169 169 169
      SYMBOL 'circle'
      MAXSIZE 1
    END
  END
  CLASS
    # TEMPLATE "DUMMY"
    EXPRESSION ([depth] > 2)
    STYLE
    OUTLINECOLOR 0 0 0
    SYMBOL 'circle'
    MAXSIZE 1
    END
	END
END #Layer borders