/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.report.model.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openlmis.core.service.*;
import org.openlmis.report.mapper.lookup.FacilityTypeReportMapper;
import org.openlmis.report.model.ReportParameter;
import org.openlmis.report.model.dto.Product;
import org.openlmis.report.service.lookup.ReportLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
public class AverageConsumptionReportParam extends BaseParam
  implements ReportParameter {

  private ProductCategoryService productCategoryService;

  private ReportLookupService reportLookupService;

  private GeographicZoneService geographicZoneService;

  private RequisitionGroupService requisitionGroupService;

  private FacilityTypeReportMapper facilityTypeService;

  private ProgramService programService;

  private FacilityService facilityService;

  // period selections
  private String periodType;
  private int yearFrom;
  private int yearTo;
  private int monthFrom;
  private int monthTo;
  private int quarterFrom;
  private int quarterTo;
  private int semiAnnualFrom;
  private int semiAnnualTo;

  private int facilityTypeId;
  private String facilityType;

  private Long zoneId;
  private String zone;

  private String productNames;
  private String productId;

  private Long productCategoryId;
  private String productCategories;

  private Long rgroupId;
  private String requisitionGroup;

  private Long facilityId;
  private String facility;

  private Long programId;
  private String program;

  private int pdformat;

  private Date startDate;
  private Date endDate;

  @Override
  public String toString() {
    try {
      DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);

      if (facilityTypeId == 0) {
        setFacilityType("All facility types");
      } else {
        //TODO: write the facility type service
        //facilityTypeService.
      }

      if (programId != 0) {
        this.setProgram(programService.getById(programId).getName());
      }

      if (zoneId != 0) {
        setZone(geographicZoneService.getById(getZoneId()).getName());
      } else {
        setZone("All geographical zones");
      }

      if (rgroupId != 0 && rgroupId != null) {
        setRequisitionGroup(requisitionGroupService.getBy(rgroupId).getName());
      } else {
        setRequisitionGroup("All requisition groups");
      }

      if (facilityId != null && facilityId != 0) {
        setFacility(facilityService.getById(getFacilityId()).getName());
      } else {
        setFacility("All facilities");
      }

      if (productCategoryId != 0) {
        setProductCategories(productCategoryService.getById(productCategoryId).getName());
      } else {
        setProductCategories("All Product Categories");
      }

      if (productId.equals("{}")) {
        setProductNames("All products");
      } else {
        String productNames = "";
        List<Product> products = reportLookupService.getListOfProducts(productId);
        for (Product p : products) {
          productNames = productNames + ((productNames.isEmpty()) ? "" : ", ") + p.getName();
        }
        setProductNames(productNames);
      }

      return "Program: " + this.getProgram() + "\n" +
        "Period : " + dateFormatter.format(this.getStartDate()) + " - " + dateFormatter.format(this.getEndDate()) + " \n" +
        "Geographic Zones: " + getZone() + "\n" +
        "Requisition Group: " + getRequisitionGroup() + "\n" +
        "Facility Types: " + this.getFacilityType() + "\n" +
        "Facility: " + this.getFacility() + "\n" +
        "Product Category: " + this.getProductCategories() + "\n" +
        "Product: " + this.getProductNames();

    } catch (Exception exp) {

    }
    return "";
  }
}


