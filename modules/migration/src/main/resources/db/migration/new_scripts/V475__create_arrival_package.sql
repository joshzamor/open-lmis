--
-- This program is part of the OpenLMIS logistics management information system platform software.
-- Copyright © 2013 VillageReach
--
-- This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
--  
-- This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
-- You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
--

CREATE TABLE arrival_package (
  id                  SERIAL PRIMARY KEY,
  sscc                VARCHAR(255) NULL,
  package_number      VARCHAR(255) NULL,
  lot_number          INTEGER NOT NULL,
  number_as_expected  INTEGER NOT NULL,
  GTIN                VARCHAR(255) NOT NULL,
  number_recieved     INTEGER NOT NULL,
  number_expected     INTEGER,
  damage     VARCHAR(200),
  vvm_status          VARCHAR(255),
  problems            VARCHAR(200),
  receiving_user      INTEGER REFERENCES users (id),
  geographic_zone_id INTEGER REFERENCES geographic_zones (id)
);