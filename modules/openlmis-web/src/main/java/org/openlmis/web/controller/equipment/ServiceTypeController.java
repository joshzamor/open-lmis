/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.web.controller.equipment;

import org.openlmis.equipment.domain.EquipmentType;
import org.openlmis.equipment.domain.ServiceType;
import org.openlmis.equipment.repository.EquipmentOperationalStatusRepository;
import org.openlmis.equipment.service.EquipmentTypeService;
import org.openlmis.equipment.service.ServiceTypeService;
import org.openlmis.web.controller.BaseController;
import org.openlmis.web.response.OpenLmisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value="/equipment/service-type/")
public class ServiceTypeController extends BaseController {


  @Autowired
  private ServiceTypeService service;

  @RequestMapping(method = GET, value = "list")
  @PreAuthorize("@permissionEvaluator.hasPermission(principal,'MANAGE_EQUIPMENT_SETTINGS') or @permissionEvaluator.hasPermission(principal,'SERVICE_VENDOR_RIGHT')")
  public ResponseEntity<OpenLmisResponse> getAll(){
    return  OpenLmisResponse.response("service_types", service.getAll());
  }

  @RequestMapping(method = GET, value = "id")
  @PreAuthorize("@permissionEvaluator.hasPermission(principal,'MANAGE_EQUIPMENT_SETTINGS')")
  public ResponseEntity<OpenLmisResponse> getById( @RequestParam("id") Long id){
    return  OpenLmisResponse.response("service_type", service.getById(id));
  }


  @RequestMapping(value = "save", method = POST, headers = ACCEPT_JSON)
  @PreAuthorize("@permissionEvaluator.hasPermission(principal,'MANAGE_EQUIPMENT_SETTINGS')")
  public ResponseEntity<OpenLmisResponse> save(@RequestBody ServiceType type){
    service.save(type);
    ResponseEntity<OpenLmisResponse> response = OpenLmisResponse.success("message.equipment.service.type.saved");
    response.getBody().addData("service_type", type);
    return response;
  }
}
