LAYER #incidence_finetype
  TEMPLATE "DUMMY"
  METADATA
    "wms_title" "Annual incidence / finetype"
  END
  NAME incidenceft
  INCLUDE "connection.inc"
  DATA "the_geom from (
    SELECT *
      FROM episcangis_incidence_finetype_per_area
       ( %fromMonth%, %fromYear%, %toMonth%, %toYear%, %CASETYPES%, %INCITIERFT%)
        AS (oid int4, the_geom geometry, incidence real, area varchar)
  ) AS foo USING SRID=4326 USING UNIQUE oid"
  STATUS ON
  TYPE POLYGON

  CLASS
   EXPRESSION ([incidence] <= 0)
   COLOR 254 254 254
  END
  CLASS
   EXPRESSION (([incidence] > 0 AND [incidence] <= 0.3))
   COLOR  0 220 220
  END
  CLASS
   EXPRESSION (([incidence] > 0.3 AND [incidence] <= 0.6))
   COLOR 0 190 190
  END
  CLASS
   EXPRESSION (([incidence] > 0.6 AND [incidence] <= 1.2))
   COLOR 0 143 143
  END
  CLASS
   EXPRESSION (([incidence] > 1.2 AND [incidence] <= 2.4))
   COLOR 0 92 92
  END
  CLASS
   EXPRESSION ([incidence] > 2.4)
    COLOR 0 41 41
  END
END #Layer incidence_finetype