LAYER
  NAME "topography_disabled"
  STATUS ON
  INCLUDE "connection.inc"
  DATA "the_geom from topography"
  TYPE POLYGON
  PROJECTION
      "init=epsg:4326"
  END
  METADATA
    "WMS_SRS"    "epsg:4326"
    "WMS_TITLE"    "Topography"
    "WMS_FEATURE_INFO_MIME_TYPE"    "text/html"
  END
  CLASSITEM "hypytype"
  CLASS
    NAME "Lowlands"
    EXPRESSION "1"
    SYMBOL 0
    COLOR 255 255 255
  END
  CLASS
    NAME "Lowlands"
    EXPRESSION "2"
    SYMBOL 0
    COLOR 204 253 161
  END
  CLASS
    NAME "Highland"
    EXPRESSION "3"
    SYMBOL 0
    COLOR 215 200 145
  END
  CLASS
    NAME "Alps"
    EXPRESSION "4"
    SYMBOL 0
    COLOR 173 136 77
  END
  CLASS
    NAME "High mountains"
    EXPRESSION "5"
    SYMBOL 0
    COLOR 115 38 0
  END
END