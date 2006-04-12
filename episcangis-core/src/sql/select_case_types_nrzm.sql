CREATE VIEW case_types_nrzm AS 
SELECT case_type_id, split_part(identifier, ';', 1) || ' : P1.' || 
split_part(identifier, ';', 2) || ',' ||
split_part(identifier, ';', 3) || ' : F' || split_part(identifier, ';', 4) AS 
identifier FROM case_types