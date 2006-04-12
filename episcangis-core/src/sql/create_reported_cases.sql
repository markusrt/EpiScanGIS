-- Table: reported_cases

-- DROP TABLE reported_cases;

CREATE TABLE reported_cases
(
  id int4 NOT NULL PRIMARY KEY,
  age int2,
  gender char check (gender in ('m', 'f', '?')),
  place_of_incidence int4 REFERENCES locations(gid),	
  date_of_incidence date,
  date_of_report date,
  agent_type serial REFERENCES types(id)
) 
WITHOUT OIDS;
ALTER TABLE reported_cases OWNER TO postgres;
