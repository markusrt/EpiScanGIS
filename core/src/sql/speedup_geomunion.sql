--CREATE INDEX cases_gist_idx ON cases 
--  USING GIST ( the_geom GIST_GEOMETRY_OPS ); 
--VACUUM ANALYZE cases;
--cluster cases_gist_idx on cases;
--SELECT update_geometry_stats()
--ANALYZE areas