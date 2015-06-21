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

import org.openlmis.core.service.ConfigurationSettingService;


import org.openlmis.report.mapper.RegimenSummaryReportMapper;
import org.openlmis.report.model.ReportData;

import org.openlmis.report.model.ReportParameter;

import org.openlmis.report.model.params.RegimenSummaryReportParam;

import org.openlmis.report.util.SelectedFilterHelper;
import org.openlmis.report.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@NoArgsConstructor
public class RegimenDistributionReportDataProvider extends ReportDataProvider {
    @Autowired
    private RegimenSummaryReportMapper reportMapper;
    @Autowired
    private ConfigurationSettingService configurationService;


    @Autowired
    private SelectedFilterHelper filterHelper;

    @Autowired
    public RegimenDistributionReportDataProvider(RegimenSummaryReportMapper mapper, ConfigurationSettingService configurationService) {
        this.reportMapper = mapper;
        this.configurationService = configurationService;
    }

    @Override
    protected List<? extends ReportData> getResultSetReportData(Map<String, String[]> filterCriteria) {
        RowBounds rowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
        return reportMapper.getRegimenDistributionReport(getReportFilterData(filterCriteria), null, rowBounds, this.getUserId());
    }

    @Override
    public List<? extends ReportData> getMainReportData(Map<String, String[]> filterCriteria, Map<String, String[]> SortCriteria, int page, int pageSize) {
        RowBounds rowBounds = new RowBounds((page - 1) * pageSize, pageSize);
        return reportMapper.getRegimenDistributionReport(getReportFilterData(filterCriteria), SortCriteria, rowBounds, this.getUserId());
    }

    public ReportParameter getReportFilterData(Map<String, String[]> filterCriteria) {
        RegimenSummaryReportParam regimenSummaryReportParam = null;

        if (filterCriteria != null) {

            regimenSummaryReportParam = new RegimenSummaryReportParam();

            regimenSummaryReportParam.setRegimenCategoryId(StringHelper.isBlank(filterCriteria, "regimenCategory") ? 0 : Integer.parseInt(filterCriteria.get("regimenCategory")[0]));
            regimenSummaryReportParam.setRegimenId(StringHelper.isBlank(filterCriteria, "regimen") ? 0 : Integer.parseInt(filterCriteria.get("regimen")[0]));
            regimenSummaryReportParam.setPeriodId(Long.parseLong(filterCriteria.get("period")[0]));
            regimenSummaryReportParam.setZoneId(StringHelper.isBlank(filterCriteria, "zone") ? 0 : Integer.parseInt(filterCriteria.get("zone")[0]));
            regimenSummaryReportParam.setScheduleId(StringHelper.isBlank(filterCriteria, "schedule") ? 0 : Integer.parseInt(filterCriteria.get("schedule")[0]));
            regimenSummaryReportParam.setProgramId(StringHelper.isBlank(filterCriteria, "program") ? 0L : Long.parseLong(filterCriteria.get("program")[0]));
            regimenSummaryReportParam.setFacilityId(StringHelper.isBlank(filterCriteria, "facility") ? 0 : Integer.parseInt(filterCriteria.get("facility")[0]));
            regimenSummaryReportParam.setFacilityTypeId(StringHelper.isBlank(filterCriteria, ("facilityType")) ? 0 : Integer.parseInt(filterCriteria.get("facilityType")[0]));
        }
        return regimenSummaryReportParam;
    }

    @Override
    public String getFilterSummary(Map<String, String[]> params) {
        Map<String, String[]> modifiableParams = new HashMap<String, String[]>();
        modifiableParams.putAll(params);
        modifiableParams.put("userId", new String[]{String.valueOf(this.getUserId())});

        return filterHelper.getProgramPeriodGeoZone(modifiableParams);

    }
}
