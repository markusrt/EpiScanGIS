LAYER
  NAME citynames_disabled
  STATUS ON
  INCLUDE "connection.inc"
  DATA "the_geom from cities"
  TYPE ANNOTATION
  TOLERANCE 20
  TOLERANCEUNITS PIXELS
  #SIZEUNITS PIXELS
  SYMBOLSCALE 800000
  LABELITEM "full_name"
  METADATA
    "WMS_SRS"    "epsg:4326"
    "WMS_TITLE"    "City names"
    "WMS_FEATURE_INFO_MIME_TYPE"    "text/html"

  END
  PROJECTION
   "init=epsg:4326"
  END

  CLASSITEM "pc"
  CLASS	#Großstädte
    EXPRESSION  "10" #('[NAME_TYP]' = '1')	#das gleiche wie EXPRESSION "1"
    MINSCALE 0
    MAXSCALE 50000000
    LABEL
      #TYPE BITMAP
      TYPE TRUETYPE
      FONT "arial-bold"
      SIZE 15
      MINSIZE 7
      MAXSIZE 15
      #POSITION AUTO
      POSITION ur
      OFFSET 1 1
      MINDISTANCE -1
      COLOR 0 0 0
      ANTIALIAS FALSE
      PARTIALS FALSE
      BACKGROUNDSHADOWCOLOR 255 255 255
      FORCE TRUE
    END
  END

  CLASS			#Landeshauptstädte
    EXPRESSION '1'
    MINSCALE 0
    MAXSCALE 5000000
    LABEL
      TYPE TRUETYPE
      FONT "arial-bold"
      SIZE 8
      MINSIZE 7
      MAXSIZE 11
      POSITION AUTO
      OFFSET 1 1
      MINDISTANCE -1
      COLOR 50 50 50
      ANTIALIAS FALSE
      PARTIALS FALSE
      FORCE FALSE
    END
  END

	CLASS
	  EXPRESSION ('[pc]' = '2')
	  MINSCALE 0
	  MAXSCALE 1000000
	  LABEL
	    #TYPE BITMAP
	    TYPE TRUETYPE
	    FONT "arial-bold"
	    SIZE 8
	    # SIZE small
	    MINSIZE 7
	    MAXSIZE 10
	    POSITION AUTO
	    #POSITION ur
	    OFFSET 1 1
	    MINDISTANCE -1
	    COLOR 50 50 50
	    ANTIALIAS FALSE
	    PARTIALS FALSE
	    #FORCE TRUE
	 END
	END

  CLASS
    EXPRESSION ('[pc]' = '3')
    MINSCALE 0
    MAXSCALE 450000
    LABEL
      #TYPE BITMAP
      TYPE TRUETYPE
      FONT "arial-bold"
      SIZE 8
      # SIZE small
      MINSIZE 7
      MAXSIZE 10
      POSITION AUTO
      #POSITION ur
      OFFSET 1 1
      MINDISTANCE -1
      COLOR 50 50 50
      ANTIALIAS FALSE
      PARTIALS FALSE
      #FORCE TRUE
    END
  END

  CLASS
    EXPRESSION ('[pc]' = '4')
    MINSCALE 0
    MAXSCALE 225000
    LABEL
      #TYPE BITMAP
      TYPE TRUETYPE
      FONT "arial-bold"
      SIZE 8
      # SIZE small
      MINSIZE 7
      MAXSIZE 10
      POSITION AUTO
      #POSITION ur
      OFFSET 1 1
      MINDISTANCE -1
      COLOR 50 50 50
      ANTIALIAS FALSE
      PARTIALS FALSE
      #FORCE TRUE
    END
  END
END
