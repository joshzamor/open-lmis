--
-- This program is part of the OpenLMIS logistics management information system platform software.
-- Copyright © 2013 VillageReach
--
-- This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
--  
-- This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
-- You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
--

DROP TABLE IF EXISTS var_details CASCADE;
CREATE TABLE var_details (
  id                  SERIAL PRIMARY KEY,
  awb_number          VARCHAR(20) NULL,
  flight_number          VARCHAR(20) NOT NULL,
  estimate_time_of_arrival    TIMESTAMP NOT NULL,
  actual_time_of_arrival    TIMESTAMP NOT NULL,
  number_of_items_inspected  INTEGER NOT NULL,
  coolant_type                VARCHAR(20) NOT NULL,
  temperature_monitor     VARCHAR(20) NOT NULL
);