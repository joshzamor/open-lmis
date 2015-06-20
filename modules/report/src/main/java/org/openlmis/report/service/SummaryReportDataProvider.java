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
import org.apache.ibatis.session.RowBounds;
import org.openlmis.report.mapper.SummaryReportMapper;
import org.openlmis.report.model.ReportData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@NoArgsConstructor
public class SummaryReportDataProvider extends ReportDataProvider {

  @Autowired
  private SummaryReportMapper reportMapper;

  @Override
  protected List<? extends ReportData> getResultSetReportData(Map<String, String[]> filterCriteria) {
    RowBounds rowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
    return reportMapper.getReport(filterCriteria, rowBounds);
  }

  @Override
  public List<? extends ReportData> getMainReportData(Map<String, String[]> filterCriteria, Map<String, String[]> SortCriteria, int page, int pageSize) {
    RowBounds rowBounds = new RowBounds((page - 1) * pageSize, pageSize);
    return reportMapper.getReport(filterCriteria, rowBounds);
  }

  @Override
  public String getFilterSummary(Map<String, String[]> params) {
    String facilityTypeId = params.get("facilityType")[0];
    String facilityType = "";

    if (facilityTypeId != null && !facilityTypeId.isEmpty()) {
      if (facilityTypeId.equals("-1") || facilityTypeId.equals(""))
        facilityType = "All Facility Types";
      else
        facilityType = params.get("facilityType")[0];
    }

    String finalFacilityType = facilityType;
    return finalFacilityType + "\n";

  }
}
