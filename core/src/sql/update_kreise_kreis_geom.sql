--UPDATE kreise SET kreis_geom = (SELECT SetSRID(GeomUnion(envelope(the_geom)),4326) FROM locations WHERE kreise.kreis_n=locations.kreis_n)
--	      WHERE kreise.kreis_n<17000
--UPDATE kreise SET kreis_geom = NULL
UPDATE kreise SET kreis_geom = (SELECT SetSRID(GeomUnion(the_geom),4326) FROM locations WHERE kreise.kreis_n=locations.kreis_n)
	      WHERE kreise.kreis_n<17000