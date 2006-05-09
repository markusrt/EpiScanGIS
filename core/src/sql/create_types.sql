-- Table: types

DROP TABLE types;

CREATE TABLE types
(
  id serial NOT NULL,
  agent_type varchar(256) NOT NULL,
  CONSTRAINT types_pkey PRIMARY KEY (agent_type)
) 
WITHOUT OIDS;
ALTER TABLE types OWNER TO postgres;
