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
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.openlmis.core.service.ConfigurationSettingService;
import org.openlmis.report.mapper.RnRFeedbackReportMapper;
import org.openlmis.report.model.ReportData;
import org.openlmis.report.model.params.RnRFeedbackReportParam;
import org.openlmis.report.util.Constants;
import org.openlmis.report.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
public class RnRFeedbackReportDataProvider extends ReportDataProvider {

  @Autowired
  private RnRFeedbackReportMapper reportMapper;

  @Autowired
  private ConfigurationSettingService configurationService;

  private RnRFeedbackReportParam feedbackReportFilter = null;

  @Override
  protected List<? extends ReportData> getResultSetReportData(Map<String, String[]> filterCriteria) {
    return getMainReportData(filterCriteria, filterCriteria, RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
  }

  @Override
  public List<? extends ReportData> getMainReportData(Map<String, String[]> filterCriteria, Map<String, String[]> SortCriteria, int page, int pageSize) {
    RowBounds rowBounds = new RowBounds((page - 1) * pageSize, pageSize);
    return reportMapper.getRnRFeedbackReport(getReportFilterData(filterCriteria), SortCriteria, rowBounds, this.getUserId());
  }

  public RnRFeedbackReportParam getReportFilterData(Map<String, String[]> filterCriteria) {

    if (filterCriteria != null) {
      feedbackReportFilter = new RnRFeedbackReportParam();
      Calendar originalStart = Calendar.getInstance();
      Calendar originalEnd = Calendar.getInstance();

      feedbackReportFilter.setFacilityTypeId(StringHelper.isBlank(filterCriteria,"facilityType")? 0 : Integer.parseInt(filterCriteria.get("facilityType")[0])); //defaults to 0
      feedbackReportFilter.setFacilityId(StringHelper.isBlank(filterCriteria,"facility") ? 0 : Integer.parseInt(filterCriteria.get("facility")[0])); //defaults to 0
      feedbackReportFilter.setFacilityType((filterCriteria.get("facilityType") == null || filterCriteria.get("facilityType")[0].equals("")) ? "All Facilities" : filterCriteria.get("facilityType")[0]);
      feedbackReportFilter.setProductId(StringHelper.isBlank(filterCriteria,"product") ? -1 : Integer.parseInt(filterCriteria.get("product")[0])); //defaults to 0
      if (feedbackReportFilter.getProductId() == 0) {
        feedbackReportFilter.setProduct("All Products");
      } else if (feedbackReportFilter.getProductId() == -1) {
        //Indicator Products
        feedbackReportFilter.setProduct(configurationService.getConfigurationStringValue(Constants.CONF_INDICATOR_PRODUCTS).isEmpty() ? "Indicator Products" : configurationService.getConfigurationStringValue(Constants.CONF_INDICATOR_PRODUCTS));
      } else {
        feedbackReportFilter.setProduct(filterCriteria.get("product")[0]);
      }

      feedbackReportFilter.setOrderType(StringHelper.isBlank(filterCriteria,"orderType") ? "" : filterCriteria.get("orderType")[0]);
      feedbackReportFilter.setPeriodId(StringHelper.isBlank(filterCriteria,"period") ? 0 : Integer.parseInt(filterCriteria.get("period")[0])); //defaults to 0

      feedbackReportFilter.setZoneId(StringHelper.isBlank(filterCriteria,"zone") ? 0 : Integer.parseInt(filterCriteria.get("zone")[0])); //defaults to 0

      feedbackReportFilter.setScheduleId(StringHelper.isBlank(filterCriteria,"schedule")  ? 0 : Integer.parseInt(filterCriteria.get("schedule")[0])); //defaults to 0
      feedbackReportFilter.setSchedule(filterCriteria.get("schedule")[0]);
      feedbackReportFilter.setRgroupId(StringHelper.isBlank(filterCriteria,"requisitionGroup")? 0 : Integer.parseInt(filterCriteria.get("requisitionGroup")[0])); //defaults to 0

      feedbackReportFilter.setRgroup(StringHelper.isBlank(filterCriteria,"requisitionGroup") ? "All Reporting Groups" : filterCriteria.get("requisitionGroup")[0]);

      feedbackReportFilter.setPeriod(filterCriteria.get("period")[0]);
      feedbackReportFilter.setProgramId(StringHelper.isBlank(filterCriteria,"program")? 0 : Integer.parseInt(filterCriteria.get("program")[0])); //defaults to 0
      feedbackReportFilter.setProgram(filterCriteria.get("program")[0]);

    }
    return feedbackReportFilter;
  }

  @Override
  public String getFilterSummary(Map<String, String[]> params) {
    return getReportFilterData(params).toString();
  }

}
