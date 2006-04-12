CREATE OR REPLACE VIEW cases_random AS 
 SELECT setsrid(
   translate(
     cases.the_geom, 
     random() / 30, random() / 30, 0), 4326) AS the_geom, 
   [...]
 FROM cases;
