LAYER
  DUMP TRUE
  TEMPLATE "DUMMY"
  NAME cities
  STATUS ON
  INCLUDE "connection.inc"
  DATA "the_geom from (
    SELECT geonameid AS _oid, the_geom, name, population FROM cities
      WHERE hidden=false
    ) AS foo USING SRID=4326 USING UNIQUE _oid"
  TOLERANCE 3
  TYPE POINT
  METADATA
    "WMS_SRS"    "epsg:4326"
    "WMS_TITLE"    "Cities"
    "wms_include_items" "all"
  END
  PROJECTION
    "init=epsg:4326"
  END
  LABELITEM "name"

  CLASS #100K - 500K citizens
    EXPRESSION ([population] > 100000)
    NAME '>100000'
    MINSCALEDENOM 1000
    MAXSCALEDENOM 2500000
    MINSIZE 7
    MAXSIZE 9
    SYMBOL 'circle'
    COLOR 255 0 0 #169 169 169
    LABEL
      #TYPE BITMAP
      TYPE TRUETYPE
      FONT "arial-bold"
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
    MINSCALEDENOM 1000
    MAXSCALEDENOM 500000
    MINSIZE 5
    MAXSIZE 7
    SYMBOL 'circle'
    COLOR 250 250 250 #169 169 169
    LABEL
      #TYPE BITMAP
      TYPE TRUETYPE
      FONT "arial-bold"
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
    MINSCALEDENOM 1000
    MAXSCALEDENOM 300000
    MINSIZE 3
    MAXSIZE 5
    SYMBOL 'circle'
    COLOR 0 0 0 #169 169 169
    LABEL
      #TYPE BITMAP
      TYPE TRUETYPE
      FONT "arial-bold"
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
END
