--UPDATE areas SET the_geom=bdl.the_geom FROM bdl WHERE areas.identifier=bdl.gen
-- UPDATE sta SET the_geom=areas.the_geom FROM areas WHERE areas.oid=32821 AND sta.gid=2
--SELECT * FROM areas WHERE areas.identifier=bdl.gen
--SELECT  * FROM krs WHERE ags::int4/1000 not in (SELECT numeric_identifier FROM areas);
--UPDATE areas SET the_geom=krs.the_geom FROM krs WHERE ags::int4/1000=numeric_identifier;
--UPDATE areas SET identifier=gen || ', ' || des FROM krs WHERE ags::int4/1000=numeric_identifier;
--INSERT INTO areas (identifier,the_geom,numeric_identifier,area_type_id,population) SELECT gen || ', ' || des, krs.the_geom,ags::int4/1000,5,0 FROM krs WHERE ags::int4/1000 not in (SELECT numeric_identifier FROM areas);