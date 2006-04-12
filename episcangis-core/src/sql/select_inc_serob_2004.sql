SELECT serotype, kreis_n, count(*) FROM 
  ( SELECT split_part(types.agent_type, ';',1) AS serotype, kreis_n 
      FROM reported_cases, types, locations WHERE reported_cases.agent_type=types.id  
                                              AND place=gid AND  to_char(reportdate, 'YY')='04') AS sero_kreis 
  WHERE serotype='B' GROUP BY kreis_n, serotype