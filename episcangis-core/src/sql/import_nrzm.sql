CREATE TABLE nrzm_imprt_tmp
(
  pnr int4 NOT NULL,
  eingang varchar(16),
  entnahme varchar(16),
  sg varchar(16),
  vr1 varchar(16),
  vr2 varchar(16),
  feta varchar(16),
  plz varchar(16),
  bdslnr varchar(16),
  knr varchar(16),
  altr varchar(16),
  geschlecht varchar(16),
  CONSTRAINT nrzm_imprt_tmp_pkey PRIMARY KEY (pnr)
) 
WITHOUT OIDS;
ALTER TABLE nrzm_imprt_tmp OWNER TO postgres;
COPY nrzm_imprt_tmp FROM 'D:\saubere_tabelle.csv' WITH DELIMITER ';';