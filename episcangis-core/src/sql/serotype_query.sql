SELECT locations.plzort99, split_part(types.agent_type, ';',1) AS serotype, reported_cases.gender, reported_cases.age, count(*) 
  FROM reported_cases, locations, types WHERE reported_cases.agent_type=types.id AND reported_cases.place_of_incidence=locations.gid
  GROUP BY types.agent_type, locations.plzort99, reported_cases.gender, reported_cases.age
  HAVING split_part(types.agent_type, ';',1)='B'
  ORDER BY locations.plzort99;