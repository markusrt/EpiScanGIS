LAYER
  VALIDATION
    "fromAge" "^[0-9]+$"
    "toAge" "^[0-9]+$"
    "fromMonth" "^[0-9]+$"
    "toMonth" "^[0-9]+$"
    "fromYear" "^[0-9]+$"
    "toYear" "^[0-9]+$"
    "SEROGROUPS" "^.*$"
  END
  NAME serogroups_default
  TOLERANCE 10
  METADATA
    "wms_title" "Serogroups"
  END
  INCLUDE "connection.inc"
  DATA "the_geom from (
  SELECT *
      FROM episcangis_cases_per_attribute_value
       ( %fromMonth%, %fromYear%, %toMonth%, %toYear%, %fromAge%, %toAge%,
         'Serogroup', '{%SEROGROUPS%}' )
        AS (oid int4, the_geom geometry, serogroup varchar, reportdate date)
  ) AS foo USING SRID=4326 USING UNIQUE oid"
  STATUS ON
 TYPE POINT

 SYMBOLSCALEDENOM 3000000

 PROJECTION
   "init=epsg:4326"
 END
  CLASSITEM "serogroup"
  CLASS
   NAME "Serogroups"
   EXPRESSION (0)
  END
  CLASS
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'B'
    EXPRESSION "B"
    STYLE
     MINSIZE 7
     MAXSIZE 30     
     SYMBOL 'triangle'
     COLOR 255 0 255
     OUTLINECOLOR 0 0 0
    END
  END
  CLASS

    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'C'
    EXPRESSION "C"
    STYLE
     MINSIZE 7
     MAXSIZE 30
     SYMBOL 'triangle'
     COLOR 255 255 0
     OUTLINECOLOR 0 0 0
    END
  END
  CLASS

    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'Y'
    EXPRESSION "Y"
    STYLE
     MINSIZE 7
     MAXSIZE 30
     SYMBOL 'triangle'
     COLOR 0 255 0
     OUTLINECOLOR 0 0 0
    END
  END
  CLASS
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'W'
    EXPRESSION "W"
    STYLE
     MINSIZE 6
     MAXSIZE 27
     SYMBOL 'square'
     COLOR 255 0 255
     OUTLINECOLOR 0 0 0
    END
  END
  CLASS

    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME '29E'
    EXPRESSION "29E"
    STYLE
     SYMBOL 'square'
     COLOR 255 255 0
     OUTLINECOLOR 0 0 0
     MINSIZE 6
     MAXSIZE 27
    END 
  END
  CLASS
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'A'
    EXPRESSION "A"
    STYLE
     MINSIZE 6
     MAXSIZE 27
     SYMBOL 'square'
     COLOR 0 255 0
     OUTLINECOLOR 0 0 0
    END
  END
  CLASS
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
    NAME 'others'
    STYLE
     MINSIZE 10
     MAXSIZE 27
     SYMBOL 'star'
     COLOR 255 0 0
     OUTLINECOLOR 0 255 0
    END
  END
END

