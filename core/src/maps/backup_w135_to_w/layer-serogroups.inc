LAYER
  #GROUP "query"
  #TEMPLATE "DUMMY"
  #DUMP TRUE
  NAME serogroups_default
  TOLERANCE 10
  METADATA
    "wms_title" "Serogroups"
  END
  INCLUDE "connection.inc"
  #SELECT case_id AS oid, setSrid(translate(the_geom,random(),random(),random()),4326) AS the_geom, identifier, attvalue, attname
  DATA "the_geom from (
  SELECT *
      FROM episcangis_cases_per_attribute_value
       ( %fromMonth%, %fromYear%, %toMonth%, %toYear%, %fromAge%, %toAge%,
         'Serogroup', '{%SEROGROUPS%}' )
        AS (oid int4, the_geom geometry, serogroup varchar, reportdate date)
  ) AS foo USING SRID=4326 USING UNIQUE oid"
  STATUS ON
 TYPE POINT

 SYMBOLSCALE 3000000

 PROJECTION
   "init=epsg:4326"
 END
  CLASSITEM "serogroup"
  CLASS
   NAME "Serogroups"
   EXPRESSION (0)
  END
  CLASS
    MINSIZE 7
    MAXSIZE 30
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'B'
    EXPRESSION "B"
    SYMBOL 'triangle'
    COLOR 255 0 255
    OUTLINECOLOR 0 0 0
  END
  CLASS
    MINSIZE 7
    MAXSIZE 30
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'C'
    EXPRESSION "C"
    SYMBOL 'triangle'
    COLOR 255 255 0
    OUTLINECOLOR 0 0 0
  END
  CLASS
    MINSIZE 7
    MAXSIZE 30
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'Y'
    EXPRESSION "Y"
    SYMBOL 'triangle'
    COLOR 0 255 0
    OUTLINECOLOR 0 0 0
  END
  CLASS
    MINSIZE 6
    MAXSIZE 27
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'W135'
    EXPRESSION "W135"
    SYMBOL 'square'
    COLOR 255 0 255
    OUTLINECOLOR 0 0 0
  END
  CLASS
    MINSIZE 6
    MAXSIZE 27
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME '29E'
    EXPRESSION "29E"
    SYMBOL 'square'
    COLOR 255 255 0
    OUTLINECOLOR 0 0 0
  END
  CLASS
    MINSIZE 6
    MAXSIZE 27
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'A'
    EXPRESSION "A"
    SYMBOL 'square'
    COLOR 0 255 0
    OUTLINECOLOR 0 0 0
  END
  CLASS
    MINSIZE 10
    MAXSIZE 27
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'others'
    SYMBOL 'star'
    COLOR 255 0 0
    OUTLINECOLOR 0 255 0
  END
END
