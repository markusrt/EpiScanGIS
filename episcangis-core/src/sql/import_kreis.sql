CREATE TABLE imprt_kreis
(
  kreis_nr varchar(16) NOT NULL,
  name varchar(64),
  longitude varchar(16),
  latitude varchar(16),
  CONSTRAINT imprt_kreis_pkey PRIMARY KEY (kreis_nr)
) 
WITHOUT OIDS;
SET CLIENT_ENCODING="iso-8859-1"
ALTER TABLE imprt_kreis OWNER TO postgres;
COPY imprt_kreis FROM 'D:\kreis_koor.csv' WITH DELIMITER ';';