SELECT * FROM 
  (SELECT split_part( type, ';', 1) AS sero, 
          split_part( type, ';', 2) AS porA, 
          split_part( type, ';', 3) AS fetA FROM types) as sg
WHERE sero='B';