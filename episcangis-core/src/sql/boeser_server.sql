select attname from pg_attribute, pg_constraint, pg_class 
       where pg_constraint.conrelid = pg_class.oid 
       and pg_class.oid = pg_attribute.attrelid and pg_constraint.contype = 'p' and pg_constraint.conkey[1] = pg_attribute.attnum 
       and pg_class.relname = 'SELECT reported_cases.id AS oid, the_geom, types.agent_type AS serogroup FROM reported_cases INNER JOIN types ON reported_cases.agent_type=types.id'
       and pg_constraint.conkey[2] is null