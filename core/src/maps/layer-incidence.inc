LAYER #incidence
  TEMPLATE "DUMMY"
  VALIDATION
    "fromMonth" "^[0-9]{2}$"
    "toMonth" "^[0-9]{2}$"
    "fromYear" "^[0-9]{4}$"
    "toYear" "^[0-9]{4}$"
    "INCITIER" "^[0-9]$"
    "SEROGROUPS" "^.*$"
  END
  METADATA
    "wms_title" "Annual incidence"
    "wms_include_items" "all"
  END
  NAME incidence
  INCLUDE "connection.inc"
  DATA "the_geom from (
    SELECT *
      FROM episcangis_incidence_per_area
       ( %fromMonth%, %fromYear%, %toMonth%, %toYear%, '{%SEROGROUPS%}', %INCITIER%)
        AS (oid int4, the_geom geometry, incidence real)
  ) AS foo USING SRID=4326 USING UNIQUE oid"
  STATUS ON
  TYPE POLYGON
  PROJECTION
    "init=epsg:4326"
  END

  CLASS
   NAME "Annual incidence"
   EXPRESSION (0)
  END
  CLASS
   NAME "(mean of observation"
   EXPRESSION (0)
  END
  CLASS
   NAME "period/100000/a)"
   EXPRESSION (0)
  END
  CLASS
   NAME "0"
   EXPRESSION ([incidence] <= 0)
   STYLE
    COLOR 254 254 254
   END
  END
  CLASS
   NAME "> 0 - 0.3"
   EXPRESSION (([incidence] > 0 AND [incidence] <= 0.3))
   STYLE
    COLOR  0 220 220
   END
  END
  CLASS
   NAME "0.3 - 0.6"
   EXPRESSION (([incidence] > 0.3 AND [incidence] <= 0.6))
   STYLE
    COLOR 0 190 190
   END
  END
  CLASS
   NAME "0.6 - 1.2"
   EXPRESSION (([incidence] > 0.6 AND [incidence] <= 1.2))
   STYLE  
    COLOR 0 143 143
   END
  END
  CLASS
   NAME "1.2 - 2.4"
   EXPRESSION (([incidence] > 1.2 AND [incidence] <= 2.4))
   STYLE
    COLOR 0 92 92
   END
  END
  CLASS
   NAME "> 2.4"
   EXPRESSION ([incidence] > 2.4)
   STYLE
    COLOR 0 41 41
   END
  END
END #Layer incidence
