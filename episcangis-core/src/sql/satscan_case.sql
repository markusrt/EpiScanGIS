SELECT awt.area_id, identifier, count(*), to_char(reportdate, 'YYYY/MM/D')
  FROM contains_area_case, areas_with_types AS awt, cases
  WHERE contains AND contains_area_case.area_id=awt.area_id AND contains_area_case.case_id=cases.case_id
    AND case_type_id = 3703
    AND area_type_id = 4
  GROUP BY awt.area_id, awt.the_geom, reportdate, identifier