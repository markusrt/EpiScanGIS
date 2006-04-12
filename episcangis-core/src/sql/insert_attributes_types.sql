DELETE FROM case_type_attributes;
INSERT INTO case_type_attributes (case_type_id, attribute_id, value) SELECT ct.case_type_id, a.attribute_id, split_part(ct.identifier,';',1) FROM case_types ct, attributes a WHERE a.name='Serogroup'
