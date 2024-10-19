LAYER
  NAME casetypes
  #GROUP "query"
  #TEMPLATE "DUMMY"
  DUMP TRUE
  TOLERANCE 10

  METADATA
    "wms_title" "Finetypes"
  END
  INCLUDE "connection.inc"
  DATA "the_geom from (
  SELECT case_id AS oid, the_geom, nice_identifier AS finetype
      FROM cases_random NATURAL JOIN case_types_nrzm
        WHERE
          CAST(to_char(reportdate, 'YYYY') AS int) * 12
            + CAST(to_char(reportdate, 'MM') AS int)
            BETWEEN %fromYear%*12+%fromMonth%
              AND %toYear%*12+%toMonth%
          AND identifier IN (%CASETYPES%)
          AND age BETWEEN %fromAge% AND %toAge%
  ) AS foo USING SRID=4326 USING UNIQUE oid"

  STATUS ON
  #DEBUG ON
  TYPE POINT
  #kleinerer Wert -> Ebene auch bei größerem Zoom sichtbar
  MINSCALEDENOM 500000
  #kleinerer Wert -> Ebene verschwindet beim rauszoomen früher
  # MAXSCALEDENOM 4000000
  MAXSCALEDENOM 6000000

  PROJECTION
    "init=epsg:4326"
  END
  # TRANSPARENCY 50
  # SYMBOLSCALE 50000000
  SYMBOLSCALE 60000000
  CLASS
   NAME " Finetypes:"
   EXPRESSION (0)
  END
  CLASS
    NAME ' Selected finetype'
    SYMBOL 'circle'
    MINSCALEDENOM 500000
    # MAXSCALEDENOM 4000000
    MAXSCALEDENOM 6000000
    # MINSIZE 23
    MINSIZE 13
    # MAXSIZE 100
    MAXSIZE 31
    COLOR 255 180 30
    OUTLINECOLOR 0 0 0
  END
END