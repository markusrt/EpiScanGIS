LAYER #cloudtest
  METADATA
    "wms_title" "Wolkentest"
  END
  NAME cloudtest_disabled
  INCLUDE "connection.inc"
  DATA "the_geom from (
    SELECT case_id AS oid, the_geom
     FROM cases_random NATURAL JOIN types_with_attributes
        WHERE ( reportdate BETWEEN to_timestamp('%FROMDATE%', 'MMYYYY')
              AND to_timestamp('%TODATE%', 'MMYYYY'))
              AND attname='Serogruppe'
              AND attvalue IN (%SEROGROUP%) ) AS foo USING SRID=4326 USING UNIQUE oid"
  STATUS ON
  TYPE POINT
  PROJECTION
    "init=epsg:4326"
  END
  TRANSPARENCY 50
  CLASS
    #STYLE
    #  SYMBOL "cloud1"
    #  SIZE 20
    #END
    STYLE
     SYMBOL "cloud3"
     SIZE 30
    END
  END
END