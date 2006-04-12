CREATE OR REPLACE FUNCTION import_csv_file(filename varchar) RETURNS integer AS $$
DECLARE
  pg_class_row pg_class%ROWTYPE;
BEGIN
  -- Check if import table exists
  SELECT * INTO pg_class_row FROM pg_class WHERE relname='nrzm_imprt';
  IF pg_class_row.relname IS NULL THEN
    RAISE EXCEPTION 'Import failed, table ''nrzm_imprt'' does not exits.';
  ELSE
    -- Clear import table
    DELETE FROM nrzm_imprt; 
    EXECUTE 'COPY nrzm_imprt FROM ''' || filename || ''' WITH DELIMITER '';''';
  END IF;
  
END
$$ LANGUAGE plpgsql;