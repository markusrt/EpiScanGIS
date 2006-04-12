SELECT awt.area_id AS oid, awt.identifier, 
         count(*)::real/(awt.population::real/100000::real)*
           (12/((2005*12+12)-(2004*12+1)+1)::real)::real AS incidence,
        FROM contains_area_case, areas_with_types AS awt, cases
        WHERE contains AND contains_area_case.area_id=awt.area_id 
          AND contains_area_case.case_id=cases.case_id
          AND case_type_id = types_with_attributes.case_type_id
          AND types_with_attributes.attname LIKE 'Serogroup'
          AND types_with_attributes.attvalue IN ('B')
          AND CAST(to_char(reportdate, 'YYYY') AS int) * 12 + CAST(to_char(reportdate, 'MM') AS int)
            BETWEEN 2004*12+1 AND 2005*12+12
          AND age IN (SELECT * FROM generate_series(0,1000))
          AND awt.tier=3
        GROUP BY awt.area_id, awt.identifier, population