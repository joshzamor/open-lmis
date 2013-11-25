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

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.RowBounds;
import org.openlmis.report.builder.FacilityReportQueryBuilder;
import org.openlmis.report.model.filter.FacilityReportFilter;
import org.openlmis.report.model.report.FacilityReport;
import org.openlmis.report.model.ReportData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityReportMapper {

    static final String STOR_PROC_FACILITY_REPORT = "select * from facilities_report()";
    static String SORTED_PAGEGED_FILTERED_REPORT = "";

    @Select(STOR_PROC_FACILITY_REPORT)
   // @Options(statementType = StatementType.CALLABLE)
    @Results(value = {
            @Result(column="code", property="code"),
            @Result(column="facilityName", property="facilityName"),
            @Result(column="facilityType", property="facilityType")
    })
    public List<FacilityReport> getAllFacilitiesReportData();

    @SelectProvider(type=FacilityReportQueryBuilder.class, method="SelectFilteredSortedPagedFacilitiesSql")
    @Options(resultSetType = ResultSetType.SCROLL_SENSITIVE, fetchSize=10,timeout=0,useCache=true,flushCache=true)
    //@Select("SELECT id, code, name FROM facilities")
    //@RowBounds(getLimit = 10,getOffset = 10)
    @Results(value = {
            @Result(column="code", property="code"),
            @Result(column="name", property="facilityName"),
            @Result(column="active", property="active"),
            @Result(column="facilityType", property="facilityType"),
            @Result(column="region", property="region"),
            @Result(column="owner", property="owner"),
            @Result(column = "gpsCoordinates", property = "gpsCoordinates"),
            @Result(column="phoneNumber", property="phoneNumber"),
            @Result(column="fax", property="fax")
    })
    public  List<FacilityReport> SelectFilteredSortedPagedFacilities(
            @Param("filterCriteria") FacilityReportFilter filterCriteria,
            @Param("SortCriteria") ReportData SortCriteria ,
            @Param("RowBounds")RowBounds rowBounds
    );

}