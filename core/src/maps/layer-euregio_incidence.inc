LAYER #incidence
  #GROUP "query"
  TEMPLATE "DUMMY"
  DUMP TRUE
  METADATA
    "wms_title" "Annual incidence"
    "wms_include_items" "all"
  END
  NAME incidence
  INCLUDE "connection.inc"
  DATA "the_geom from (
    SELECT oid AS _oid, the_geom, incidence, name AS _name
      FROM episcangis_euregio_incidence_per_tier
       ( %FROMMONTH%, %FROMYEAR%, %TOMONTH%, %TOYEAR%, %INCITIER%, %SPATYPES%, %CAREUNITTYPE%, %PVL%, %ACQUISITION%, %ASSOCATION%)
        AS (oid int4, the_geom geometry, incidence real, name varchar)
  ) AS foo USING SRID=4326 USING UNIQUE _oid"
  STATUS ON
  TYPE POLYGON
  PROJECTION
    "init=epsg:4326"
  END

  LABELITEM "_name"

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
   NAME "> 0 - 0.6"
   EXPRESSION (([incidence] > 0 AND [incidence] <= 0.6))
   SIZE 2
   COLOR  163 226 255
   OUTLINECOLOR 255 255 255
   LABEL
     TYPE TRUETYPE
     FONT "arial"
     MINSIZE 10
     MAXSIZE 10
     POSITION cc
     COLOR 0 0 0
   END
  END
  CLASS
   NAME "0.6 - 1.2"
   EXPRESSION (([incidence] > 0.6 AND [incidence] <= 1.2))
   SIZE 2
   COLOR 145 212 249
   OUTLINECOLOR 255 255 255
   LABEL
     TYPE TRUETYPE
     FONT "arial"
     MINSIZE 10
     MAXSIZE 10
     POSITION cc
     COLOR 0 0 0
   END
  END
  CLASS
   NAME "1.2 - 5"
   EXPRESSION (([incidence] > 1.2 AND [incidence] <= 5))
   SIZE 2
   COLOR 121 191 241
   OUTLINECOLOR 255 255 255
   LABEL
     TYPE TRUETYPE
     FONT "arial"
     MINSIZE 10
     MAXSIZE 10
     POSITION cc
     COLOR 0 0 0
   END
  END
  CLASS
   NAME "5 - 10"
   EXPRESSION (([incidence] > 5 AND [incidence] <= 10))
   SIZE 2
   COLOR 96 170 232
   OUTLINECOLOR 255 255 255
   LABEL
     TYPE TRUETYPE
     FONT "arial"
     MINSIZE 10
     MAXSIZE 10
     POSITION cc
     COLOR 0 0 0
   END
  END
  CLASS
   NAME "10 - 20"
   EXPRESSION (([incidence] > 10 AND [incidence] <= 20))
   SIZE 2
   COLOR 68 147 223
   OUTLINECOLOR 255 255 255
   LABEL
     TYPE TRUETYPE
     FONT "arial"
     MINSIZE 10
     MAXSIZE 10
     POSITION cc
     COLOR 200 200 200
   END
  END
  CLASS
   NAME "20 - 40"
   EXPRESSION (([incidence] > 20 AND [incidence] <= 40))
   SIZE 2
   COLOR 27 120 213
   OUTLINECOLOR 255 255 255
   LABEL
     TYPE TRUETYPE
     FONT "arial"
     MINSIZE 10
     MAXSIZE 10
     POSITION cc
     COLOR 200 200 200
   END
  END
   CLASS
   NAME "> 40"
   EXPRESSION ([incidence] > 40)
   SIZE 2
   COLOR 5 94 202
   OUTLINECOLOR 255 255 255
   LABEL
     TYPE TRUETYPE
     FONT "arial"
     MINSIZE 10
     MAXSIZE 10
     POSITION cc
     COLOR 200 200 200
   END
  END

END #Layer incidence
