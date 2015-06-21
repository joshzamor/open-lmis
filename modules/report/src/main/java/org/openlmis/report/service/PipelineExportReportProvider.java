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

import org.openlmis.core.service.ProgramService;
import org.openlmis.core.service.ProcessingPeriodService;

import org.openlmis.report.mapper.PipelineExportReportMapper;
import org.openlmis.report.model.ReportData;

import org.openlmis.report.model.params.FacilityReportParam;
import org.openlmis.report.model.params.PipelineExportParams;
import org.openlmis.report.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
public class PipelineExportReportProvider extends ReportDataProvider{
    private PipelineExportReportMapper reportMapper;

    private PipelineExportParams pipelineExportParam = null;

    @Autowired
    private ProgramService programService;

    @Autowired
    private ProcessingPeriodService processingPeriodService;


    @Autowired
    public PipelineExportReportProvider(PipelineExportReportMapper mapper) {
        this.reportMapper = mapper;
    }


    @Override
    protected List<? extends ReportData> getResultSetReportData(Map<String, String[]> filterCriteria) {
        return getMainReportData(filterCriteria, null, RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
    }

    @Override
    public List<? extends ReportData> getMainReportData(Map<String, String[]> filterCriteria, Map<String, String[]> SortCriteria, int page, int pageSize) {
        RowBounds rowBounds = new RowBounds((page - 1) * pageSize, pageSize);
        return reportMapper.getReport(getReportFilterData(filterCriteria),SortCriteria, rowBounds);
    }

    public PipelineExportParams getReportFilterData(Map<String, String[]> filterCriteria) {

        if (filterCriteria != null) {
            pipelineExportParam = new PipelineExportParams();
            pipelineExportParam.setProgramId(StringHelper.isBlank(filterCriteria, "program") ? 0 : Integer.parseInt(filterCriteria.get("program")[0]));  //defaults to 0
            pipelineExportParam.setYearId(filterCriteria.get("year") == null ? 0 : Integer.parseInt(filterCriteria.get("year")[0])); //defaults to 0
            pipelineExportParam.setPeriodId(filterCriteria.get("period") == null ? 0 : Integer.parseInt(filterCriteria.get("period")[0])); //defaults to 0
        }

        return pipelineExportParam;
    }

    @Override
    public String getFilterSummary(Map<String, String[]> params) {
        return getReportFilterData(params).toString();

    }


}