/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.equipment.service;

import org.openlmis.core.domain.User;
import org.openlmis.core.service.ConfigurationSettingService;
import org.openlmis.email.service.EmailService;
import org.openlmis.equipment.domain.*;
import org.openlmis.equipment.repository.EquipmentInventoryRepository;
import org.openlmis.equipment.repository.MaintenanceLogRepository;
import org.openlmis.equipment.repository.MaintenanceRequestRepository;
import org.openlmis.equipment.repository.ServiceContractRepository;
import org.openlmis.equipment.repository.mapper.MaintenanceLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MaintenanceLogService {

  @Autowired
  private MaintenanceLogRepository repository;

  @Autowired
  EquipmentInventoryRepository equipmentInventoryRepository;

  @Autowired
  ServiceContractRepository serviceContractRepository;

  @Autowired
  VendorUserService vendorUserService;

  @Autowired
  VendorService vendorService;

  @Autowired
  EmailService emailService;

  @Autowired
  ConfigurationSettingService settingService;

  public List<MaintenanceLog> getAll() {
    return repository.getAll();
  }

  public List<MaintenanceLog> getAllForFacility(Long facilityId) {
    return repository.getAllForFacility(facilityId);
  }

  public List<MaintenanceLog> getAllForVendor(Long vendorId) {
    return repository.getAllForVendor(vendorId);
  }

  public MaintenanceLog getById(Long id) {
    return repository.getById(id);
  }

  public void save(MaintenanceLog log) {
    if (log.getId() == null) {
      repository.insert(log);
    } else {
      repository.update(log);
    }
  }


  public void save(MaintenanceRequest maintenanceRequest) {
    EquipmentInventory equipmentInventory = equipmentInventoryRepository.getInventoryById(maintenanceRequest.getInventoryId());
    List<ServiceContract> serviceContracts = serviceContractRepository.getAllForEquipment(equipmentInventory.getEquipmentId());
    Long serviceContractId = null;
    if (serviceContracts != null && serviceContracts.size() > 0) {
      serviceContractId = serviceContracts.get(0).getId();
    }
    MaintenanceLog log = new MaintenanceLog();
    log.setUserId(maintenanceRequest.getUserId());
    log.setFacilityId(maintenanceRequest.getFacilityId());
    log.setEquipmentId(equipmentInventory.getEquipmentId());
    log.setVendorId(maintenanceRequest.getVendorId());
    log.setContractId(serviceContractId);
    log.setModifiedDate(maintenanceRequest.getModifiedDate());
    log.setRequestId(maintenanceRequest.getId());
    this.save(log);
  }
}