LAYER
  DUMP TRUE
  TEMPLATE "DUMMY"
  NAME cities_default
  STATUS ON
    TEMPLATE "DUMMY"
        DUMP TRUE
    NAME cities_default
    METADATA
          "wms_srs"    "epsg:4326"
      "wms_title" "Cities"
      "wms_include_items" "all"
    END

  INCLUDE "connection.inc"
  DATA "the_geom from (
    SELECT gid AS oid, the_geom,
    full_name AS name, population_n AS population
      FROM cities
    ) AS foo USING SRID=4326 USING UNIQUE oid"
  TOLERANCE 3
  TYPE POINT
  #UNITS METERS
  #  SIZEUNITS PIXELS
  #SYMBOLSCALE 1200000

  LABELITEM "name"

  PROJECTION
    "init=epsg:4326"
  END

 LABELMAXSCALE 15000000
 SYMBOLSCALE 1000

   CLASS
     NAME "Cities"
    EXPRESSION (0)
   END
   CLASS
     NAME "(Population):"
    EXPRESSION (0)
   END
   CLASS        #over 1M citizens
    EXPRESSION ([population] > 1000000)
    NAME 'over 1 Million'
    MINSCALE 1000
    MAXSCALE 15000000
    STYLE
      SYMBOL 'square'
      MINSIZE 8
      MAXSIZE 16
      COLOR 255 0 0 #169 169 169
      OUTLINECOLOR 0 0 0
    END
    #STYLE
    #  SYMBOL 'square'
    #  MINSIZE 3
    #  MAXSIZE 6
    #  COLOR 0 0 0 #169 169 169
    #END
    LABEL
      TYPE BITMAP
      #TYPE TRUETYPE
      #FONT "arial-bold"
      MINSIZE 10
      MAXSIZE 15
      #MAXSIZE 15
      POSITION AUTO
      #POSITION ur
      #OFFSET 1 1
      MINDISTANCE 2
      #BACKGROUNDCOLOR 255 255 255
      OUTLINECOLOR 230 230 230
      COLOR 0 0 0
      #ANTIALIAS TRUE
      PARTIALS FALSE
      ENCODING UTF-8
    END
        END

  CLASS #500K - 1M citizens
    EXPRESSION ([population] > 500000 AND [population] <= 1000000)
    #SYMBOL 'square'
    NAME '500000 - 1 Million'
    MINSCALE 1000
    MAXSCALE 15000000
    #MINSIZE 6
    #COLOR 255 0 0 #169 169 169
    #OUTLINECOLOR 0 0 0
    STYLE
      SYMBOL 'circle'
      MINSIZE 9
      MAXSIZE 11
      COLOR 255 0 0 #169 169 169
      OUTLINECOLOR 0 0 0
    END
    STYLE
      SYMBOL 'circle'
      MINSIZE 3
      MAXSIZE 5
      COLOR 0 0 0 #169 169 169
    END
    LABEL
      TYPE BITMAP
      #TYPE TRUETYPE
      #FONT "arial-bold"
      MINSIZE 9
      MAXSIZE 11
      POSITION AUTO
      #POSITION ur
      #OFFSET 1 1
      MINDISTANCE -1
      #BACKGROUNDCOLOR 255 255 255
      OUTLINECOLOR 254 254 254
      COLOR 0 0 0
      #ANTIALIAS TRUE
      PARTIALS FALSE
    END
        END

  CLASS #100K - 500K citizens
    EXPRESSION ([population] > 100000 AND [population] <= 500000)
    NAME '100000 - 500000'
    MINSCALE 1000
    MAXSCALE 2500000
    MINSIZE 7
    MAXSIZE 9
    SYMBOL 'circle'
    COLOR 255 0 0 #169 169 169
    LABEL
      TYPE BITMAP
      #TYPE TRUETYPE
      #FONT "arial-bold"
      MINSIZE 8
      POSITION AUTO
      #POSITION ur
      #OFFSET 1 1
      MINDISTANCE 10

      #BACKGROUNDCOLOR 255 255 255
      OUTLINECOLOR 254 254 254
      COLOR 0 0 0
      #ANTIALIAS TRUE
      PARTIALS FALSE
    END
    OUTLINECOLOR 0 0 0
        END

  CLASS #50K - 100K citizens
    EXPRESSION ([population] > 20000 AND [population] <= 100000)
    NAME '20000 - 100000'
    MINSCALE 1000
    MAXSCALE 500000
    MINSIZE 5
    MAXSIZE 7
    SYMBOL 'circle'
    COLOR 250 250 250 #169 169 169
    LABEL
      TYPE BITMAP
      #TYPE TRUETYPE
      #FONT "arial-bold"
      MINSIZE 8
      POSITION AUTO
      #POSITION ur
      #OFFSET 1 1
      MINDISTANCE 10

      #BACKGROUNDCOLOR 255 255 255
      OUTLINECOLOR 254 254 254
      COLOR 0 0 0
      #ANTIALIAS TRUE
      PARTIALS FALSE
    END
    OUTLINECOLOR 0 0 0
        END

  CLASS #20K citizens and below
    EXPRESSION ([population] <= 20000)
    NAME 'less than 20000'
    MINSCALE 1000
    MAXSCALE 300000
    MINSIZE 3
    MAXSIZE 5
    SYMBOL 'circle'
    COLOR 0 0 0 #169 169 169
    LABEL
      TYPE BITMAP
      #TYPE TRUETYPE
      #FONT "arial-bold"
      MINSIZE 8
      POSITION AUTO
      #POSITION ur
      #OFFSET 1 1
      MINDISTANCE 10

      #BACKGROUNDCOLOR 255 255 255
      OUTLINECOLOR 254 254 254
      COLOR 0 0 0
      #ANTIALIAS TRUE
      PARTIALS FALSE
    END
        END

  #CLASS
  #  EXPRESSION "3"
  #  MINSCALE 1000
  #  MAXSCALE 200000
  #  SYMBOL 'circle'
  #  #SIZE 3
  #  COLOR 255 255 255 #169 169 169
  #  OUTLINECOLOR 0 0 0
  #END

  #CLASS
  #  EXPRESSION "4"
  #  MINSCALE 1000
  #  MAXSCALE 200000
  #  SYMBOL 'square'
  #  #SIZE 1
  #  COLOR 0 255 0 #169 169 169
  #END
END
