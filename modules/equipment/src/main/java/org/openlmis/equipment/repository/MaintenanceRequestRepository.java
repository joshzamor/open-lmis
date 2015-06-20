/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.equipment.repository;

import org.openlmis.equipment.domain.MaintenanceRequest;
import org.openlmis.equipment.dto.Log;
import org.openlmis.equipment.repository.mapper.MaintenanceRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MaintenanceRequestRepository {

  @Autowired
  private MaintenanceRequestMapper mapper;

  public MaintenanceRequest getById(Long id){
    return mapper.getById(id);
  }

  public List<MaintenanceRequest> getAllForFacility(Long facilityId){
    return mapper.getAllForFacility(facilityId);
  }

  public List<MaintenanceRequest> getAllForVendor(Long vendorId){
    return mapper.getAllForVendor(vendorId);
  }

  public List<MaintenanceRequest> getOutstandingForVendor(Long vendorId){
    return mapper.getOutstandingRequestsForVendor(vendorId);
  }

  public List<MaintenanceRequest> getOutstandingForUser(Long userId){
    return mapper.getOutstandingRequestsForUser(userId);
  }

  public List<MaintenanceRequest> getAll(){
    return mapper.getAll();
  }

  public void insert(MaintenanceRequest value){
    mapper.insert(value);
  }

  public void update(MaintenanceRequest value){
    mapper.update(value);
  }

  public List<Log> getFullHistory(Long inventoryId){
    return mapper.getFullHistory(inventoryId);
  }
}
