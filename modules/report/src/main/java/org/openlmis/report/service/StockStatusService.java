/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.report.service;

import lombok.NoArgsConstructor;
import org.openlmis.report.mapper.StockStatusMapper;
import org.openlmis.report.model.dto.StockStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class StockStatusService {

  @Autowired
  private StockStatusMapper mapper;

  public List<StockStatusDTO> getStockStatusByQuarter(String programCode, Long year, Long quarter, Long userId){

    //TODO: remove this hard coded program code and move it to the programs table (may be as a flag).
    if(programCode.toUpperCase().equals("RMNCH")){
      return mapper.getStockStatusByQuarterAll(programCode, year,quarter);
    }

    //todo: check if user has permission
    return mapper.getStockStatusByQuarter(programCode, year, quarter);
  }

  public List<StockStatusDTO> getStockStatusByMonth(String programCode, Long year, Long quarter, Long userId){
    //TODO: remove this hard coded program code and move it to the programs table (may be as a flag).

    if (programCode.toUpperCase().equals("RMNCH")){
      return mapper.getStockStatusByMonthAll(programCode, year,quarter);
    }
    //todo: check if user has permission
    return mapper.getStockStatusByMonth(programCode, year, quarter);
  }

}
