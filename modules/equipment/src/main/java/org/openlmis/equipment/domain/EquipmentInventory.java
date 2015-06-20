/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.equipment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openlmis.core.domain.BaseModel;
import org.openlmis.core.serializer.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentInventory extends BaseModel {

  private Long facilityId;
  private Long programId;
  private Long equipmentId;

  private Equipment equipment;

  private Long operationalStatusId;
  private String serialNumber;
  private String manufacturerName;
  private String model;
  private String energySource;
  private Integer yearOfInstallation;
  private Float purchasePrice;
  private String sourceOfFund;
  private Boolean replacementRecommended;
  private String reasonForReplacement;
  private String nameOfAssessor;
  private Long primaryDonorId;
  private Boolean hasServiceContract;
  private Date serviceContractEndDate;
  private Boolean isActive;
  private Date dateDecommissioned;
  private Date dateLastAssessed;
  private Long capacity;
  private Long minTemperature;
  private Long maxTemperature;
  private String dimension;
  private String accessories;

  private String formatDate(Date date){
    try {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-MM");
      return date == null ? null : simpleDateFormat.format(date);
    }catch(Exception exp){

    }
    return null;
  }

  public String getDateLastAssessedString()  {
    return formatDate(this.dateLastAssessed);
  }

  public String getDateDecommissionedString(){
    return formatDate(this.dateDecommissioned);
  }

  public String getServiceContractEndDateString(){
    return formatDate(this.serviceContractEndDate);
  }

}
