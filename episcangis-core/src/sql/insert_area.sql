INSERT INTO areas (identifier,the_geom,numeric_identifier, area_type_id, population) SELECT 'Germany', the_geom, 49, 3, 0 FROM border_pl WHERE objectid=50
--UPDATE border_pl SET the_geom = foo.the_geom
--        FROM (SELECT setSRID(GeomUnion(the_geom),4326) AS the_geom FROM border_pl WHERE objectid<=49) AS foo WHERE objectid=50