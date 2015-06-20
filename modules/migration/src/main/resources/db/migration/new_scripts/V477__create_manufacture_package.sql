--
-- This program is part of the OpenLMIS logistics management information system platform software.
-- Copyright © 2013 VillageReach
--
-- This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
--  
-- This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
-- You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
--
DROP TABLE IF EXISTS manufacture_package;
CREATE TABLE manufacture_package (
  id                SERIAL PRIMARY KEY,
  shipment_id       VARCHAR(30) ,
  manufacture_date  DATE,
  expire_date       DATE,
  lot_number        INTEGER,
  number_of_doses   INTEGER,
  delivery_status   VARCHAR(20),
  purchasing_order_number VARCHAR(30),
  vaccine_id INTEGER REFERENCES vaccines (id)
);