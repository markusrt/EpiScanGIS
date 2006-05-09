DELETE FROM contains_area_case;
CREATE TABLE contains_area_case AS
SELECT area_id, case_id
  FROM areas, cases 
  WHERE Envelope(Force_2d(areas.the_geom)) && cases.the_geom 
    AND contains(areas.the_geom, cases.the_geom);