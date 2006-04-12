SELECT awt.area_id, awt.the_geom, count(*)::real/(awt.population::real/100000::real)
                    AS incidence
  FROM contains_area_case, areas_with_types AS awt, cases
  WHERE contains AND contains_area_case.area_id=awt.area_id AND contains_area_case.case_id=cases.case_id
    AND case_type_id = types_with_attributes.case_type_id
    AND types_with_attributes.attname LIKE 'Serogroup'
    AND types_with_attributes.attvalue IN ('B')
    AND to_char(reportdate, 'YYYY') LIKE 2005
    AND ( awt.area_type_id=4 OR awt.parent=-1 )
  GROUP BY awt.area_id, awt.the_geom, population
