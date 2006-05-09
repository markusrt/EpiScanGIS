SELECT oid, the_geom, identifier, population/(area(transform(the_geom,31467))/1000000) AS density FROM areas
WHERE area_type_id=4