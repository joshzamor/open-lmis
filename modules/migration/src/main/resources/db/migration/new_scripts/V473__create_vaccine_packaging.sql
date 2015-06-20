--
-- This program is part of the OpenLMIS logistics management information system platform software.
-- Copyright © 2013 VillageReach
--
-- This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
--  
-- This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
-- You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
--

DROP TABLE IF EXISTS vaccine_packaging CASCADE;
CREATE TABLE vaccine_packaging (
  id                    SERIAL PRIMARY KEY,
  packaging             VARCHAR(50) NULL,
  GTIN                  VARCHAR(50) NOT NULL,
  doses_per_vial        INTEGER NOT NULL,
  vials_per_box        INTEGER NOT NULL,
  wastage               VARCHAR(20),
  schedule              INTEGER NOT NULL,
  status                VARCHAR(20) NULL,
  country_id         INTEGER REFERENCES countries (id),
  vaccine_id         INTEGER REFERENCES vaccines (id),
  manufacturer_id INTEGER REFERENCES manufacturers (id)
);