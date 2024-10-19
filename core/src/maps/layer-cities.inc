LAYER
  #TEMPLATE "DUMMY"
  NAME cities_default
  STATUS ON 
  #TEMPLATE "DUMMY"
	#DUMP TRUE
  NAME cities_default
  METADATA
	  #"wms_srs"    "epsg:4326"
    "wms_title" "Cities"
    #"wms_include_items" "all"
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
    
  LABELMAXSCALEDENOM 15000000
  SYMBOLSCALEDENOM 1000
	
  CLASS
    NAME "Cities"
  EXPRESSION (0)
  END
  CLASS
   NAME "(Population):"
   EXPRESSION (0)
  END
  CLASS	#over 1M citizens
    EXPRESSION ([population] > 1000000)
    NAME 'over 1 Million' 
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
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
    NAME '500000 - 1 Million' 
    MINSCALEDENOM 1000
    MAXSCALEDENOM 15000000
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
      MINDISTANCE 1
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
    MINSCALEDENOM 1000
    MAXSCALEDENOM 2500000
    STYLE
      MINSIZE 7
      MAXSIZE 9
      SYMBOL 'circle'
      COLOR 255 0 0 #169 169 169
      OUTLINECOLOR 0 0 0
    END
    LABEL
      TYPE BITMAP
      MINSIZE 8
      POSITION AUTO
      MINDISTANCE 10
      
      OUTLINECOLOR 254 254 254
      COLOR 0 0 0
      PARTIALS FALSE
    END
	END
  
  CLASS #50K - 100K citizens
    EXPRESSION ([population] > 20000 AND [population] <= 100000)
    NAME '20000 - 100000' 
    MINSCALEDENOM 1000
    MAXSCALEDENOM 500000
    STYLE
      MINSIZE 5
      MAXSIZE 7
      SYMBOL 'circle'
      COLOR 250 250 250 #169 169 169
      OUTLINECOLOR 0 0 0
    END
    
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
  
  CLASS #20K citizens and below
    EXPRESSION ([population] <= 20000)
    NAME 'less than 20000' 
    MINSCALEDENOM 1000
    MAXSCALEDENOM 300000
    STYLE
      MINSIZE 3
      MAXSIZE 5
      SYMBOL 'circle'
      COLOR 0 0 0 #169 169 169
    END
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
END
