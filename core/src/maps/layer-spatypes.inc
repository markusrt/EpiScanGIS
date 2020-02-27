LAYER
  #GROUP "query"
  TEMPLATE "DUMMY"
  DUMP TRUE
  TRANSPARENCY ALPHA
  NAME spatypes_default
  TOLERANCE 8
  METADATA
    "wms_title" "Spa Types"
    "wms_include_items" "all"
  END
  INCLUDE "connection.inc"

  #SELECT case_id AS oid, setSrid(translate(the_geom,random(),random(),random()),4326) AS the_geom, identifier, attvalue, attname
  DATA "the_geom from (
  SELECT min(euregio_case_id) AS _id,
         chain(euregio_case_id) AS _ext_case_id_chain, the_geom, count(the_geom) AS _count
      FROM euregio_cases NATURAL JOIN case_types
      WHERE
        (identifier IN (%SPATYPES%) OR 'all' IN (%SPATYPES%)) AND
        (careunittype=%CAREUNITTYPE% OR %CAREUNITTYPE%='all') AND
        (pvlprobe=%PVL% OR %PVL%='all') AND
        (acquisition=%ACQUISITION% OR %ACQUISITION%='all') AND
        (association=%ASSOCATION% OR %ASSOCATION%='all') AND
        CAST(to_char(submissiondate, 'YYYY') AS int) * 12
        + CAST(to_char(submissiondate, 'MM') AS int)
        BETWEEN %FROMYEAR%*12+%FROMMONTH% AND %TOYEAR%*12+%TOMONTH%
      GROUP BY the_geom
  ) AS foo USING SRID=4326 USING UNIQUE _id"
  STATUS ON
 TYPE POINT

 SYMBOLSCALE 3000000

 PROJECTION
   "init=epsg:4326"
 END
  # LABELITEM "identifier"
  CLASS
   NAME "Cases by spatype(s)"
   EXPRESSION (0)
  END
  CLASS
   NAME "1 case"
   EXPRESSION (([_count] = 1))
   COLOR 124 177 211
   MINSIZE 5
   MAXSIZE 5
   OUTLINECOLOR 0 0 0
   SYMBOL 'circle'
  END
  CLASS
   NAME "2 cases"
   EXPRESSION (([_count] = 2))
   COLOR 62 157 211
   MINSIZE 7
   MAXSIZE 7
   OUTLINECOLOR 0 0 0
   SYMBOL 'circle'
  END
  CLASS
   NAME "3 cases"
   EXPRESSION (([_count] = 3))
   COLOR 0 121 192
   MINSIZE 9
   MAXSIZE 9
   OUTLINECOLOR 0 0 0
   SYMBOL 'circle'
 END
  CLASS
   NAME "3-8 cases"
   EXPRESSION (([_count] > 3) AND ([_count] <= 10))
   COLOR 0 121 192
   MINSIZE 11
   MAXSIZE 11
   OUTLINECOLOR 0 0 0
   SYMBOL 'circle'
  END
  CLASS
   NAME ">10 cases"
   EXPRESSION (([_count] > 10))
   COLOR 0 121 192
   MINSIZE 13
   MAXSIZE 13
   OUTLINECOLOR 0 0 0
   SYMBOL 'circle'
  END
END
