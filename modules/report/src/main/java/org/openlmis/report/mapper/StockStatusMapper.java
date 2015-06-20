/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.report.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.openlmis.report.model.dto.StockStatusDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockStatusMapper {


  @Select("select * from vw_e2e_stock_status " +
            " where programCode = #{programCode} " +
            "   and reportYear = #{reportYear} " +
            "   and reportQuarter = #{quarter}")
  List<StockStatusDTO> getStockStatusByQuarter(@Param("programCode") String programCode, @Param("reportYear") Long reportYear, @Param("quarter") Long quarter);


  @Select("select * from vw_e2e_stock_status " +
    " where productCode in ( select p.code from products p join program_products pp on pp.productId = p.id join programs pr on pr.id = pp.programId where pr.code = #{programCode} ) " +
    "   and reportYear = #{reportYear} " +
    "   and reportQuarter = #{quarter}")
  List<StockStatusDTO> getStockStatusByQuarterAll(@Param("programCode") String programCode, @Param("reportYear") Long reportYear, @Param("quarter") Long quarter);


  @Select("select * from vw_e2e_stock_status " +
    " where programCode = #{programCode} " +
    "   and reportYear = #{reportYear} " +
    "   and reportMonth = #{month}")
  List<StockStatusDTO> getStockStatusByMonth(@Param("programCode") String programCode, @Param("reportYear") Long reportYear, @Param("month") Long month);

  @Select("select * from vw_e2e_stock_status " +
    " where productCode in ( select p.code from products p join program_products pp on pp.productId = p.id join programs pr on pr.id = pp.programId where pr.code = #{programCode}) " +
    "   and reportYear = #{reportYear} " +
    "   and reportMonth = #{month}")
  List<StockStatusDTO> getStockStatusByMonthAll(@Param("programCode") String programCode, @Param("reportYear") Long reportYear, @Param("month") Long month);
}
