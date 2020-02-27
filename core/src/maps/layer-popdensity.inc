LAYER #Population density
  TEMPLATE "DUMMY"
  #DUMP TRUE
  METADATA
    "wms_title" "Population density"
    "wms_include_items" "all"
  END
  NAME popdensity
  INCLUDE "connection.inc"
  DATA "the_geom from (
    SELECT area_id AS oid, the_geom, identifier AS Areaname,
      population/(area(transform(the_geom,31467))/1000000) AS popdensity
    FROM areas_with_types AS awt
    WHERE tier=%POPDENTIER%
    ) AS foo USING SRID=4326 USING UNIQUE oid"
  STATUS ON
  TYPE POLYGON
  PROJECTION
    "init=epsg:4326"
  END

  CLASS
   NAME "Population density"
   EXPRESSION (0)
  END
  CLASS
   NAME "(Residents/km2): "
   EXPRESSION (0)
  END
  CLASS
   NAME "0 - 50"
   EXPRESSION ([popdensity] <= 50)
   COLOR 230 230 230
  END
  CLASS
   NAME "50 - 130"
   EXPRESSION ([popdensity] > 50 AND [popdensity] <= 130)
   COLOR 204 204 204
  END
  CLASS
   NAME "130 - 210"
   EXPRESSION ([popdensity] > 130 AND [popdensity] <= 210)
   COLOR 153 153 153
  END
  CLASS
   NAME "210 - 390"
   EXPRESSION ([popdensity] > 210 AND [popdensity] <= 390)
   COLOR 102 102 102
  END
  CLASS
   NAME "> 390"
   EXPRESSION ([popdensity] > 390)
   COLOR 51 51 51
  END
END #Layer Population density
