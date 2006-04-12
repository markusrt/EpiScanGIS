SELECT name, kreis_n FROM kreise WHERE kreis_n NOT IN (SELECT DISTINCT kreis_n FROM plz_kreis)
--SELECT plz99_n, plzort99, centroid(the_geom) FROM locations WHERE plz99_n NOT IN (SELECT plz99_n FROM plz_kreis)
--SELECT plz99_n FROM plz_kreis WHERE plz99_n NOT IN (SELECT plz99_n FROM locations)
--INSERT INTO plz_kreis VALUES (1474,14262);
--INSERT INTO plz_kreis VALUES (1827,14287);
--INSERT INTO plz_kreis VALUES (17259,13055);