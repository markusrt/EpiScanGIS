UPDATE nrzm_imprt_tmp SET entnahme=eingang WHERE entnahme='';
UPDATE nrzm_imprt_tmp SET altr='0' WHERE altr='';
UPDATE nrzm_imprt_tmp SET geschlecht='?' WHERE geschlecht='';
UPDATE nrzm_imprt_tmp SET geschlecht='f' WHERE geschlecht='w';