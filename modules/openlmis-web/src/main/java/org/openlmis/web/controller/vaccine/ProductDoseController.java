/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.web.controller.vaccine;

import org.openlmis.core.domain.ConfigurationSetting;
import org.openlmis.core.service.ConfigurationSettingService;
import org.openlmis.vaccine.dto.VaccineServiceProtocolDTO;
import org.openlmis.vaccine.service.VaccineProductDoseService;
import org.openlmis.web.controller.BaseController;
import org.openlmis.web.response.OpenLmisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/vaccine/product-dose/")
public class ProductDoseController extends BaseController{

  @Autowired
  private VaccineProductDoseService service;

  @Autowired
  private ConfigurationSettingService settingService;

  @RequestMapping(value = "get/{programId}")
  public ResponseEntity<OpenLmisResponse> getProgramProtocol(@PathVariable Long programId){
    VaccineServiceProtocolDTO dto = new VaccineServiceProtocolDTO();
    dto.setProtocols(service.getProductDoseForProgram(programId));
    dto.setTrackCampaign(settingService.getBoolValue("TRACK_VACCINE_CAMPAIGN_COVERAGE"));
    dto.setTrackOutreach(settingService.getBoolValue("TRACK_VACCINE_OUTREACH_COVERAGE"));
    dto.setTabVisibilitySettings(settingService.getSearchResults("VACCINE_TAB%"));
    return OpenLmisResponse.response("protocol",dto );
  }


  @RequestMapping(value = "save", headers = ACCEPT_JSON, method = RequestMethod.PUT)
  public ResponseEntity<OpenLmisResponse> save(@RequestBody VaccineServiceProtocolDTO protocol){
    service.save( protocol.getProtocols() );
    settingService.saveBooleanValue("TRACK_VACCINE_CAMPAIGN_COVERAGE", protocol.getTrackCampaign());
    settingService.saveBooleanValue("TRACK_VACCINE_OUTREACH_COVERAGE", protocol.getTrackOutreach());
    for(ConfigurationSetting setting: protocol.getTabVisibilitySettings()){
      settingService.saveBooleanValue(setting.getKey(), Boolean.parseBoolean(setting.getValue()));
    }
    return OpenLmisResponse.response("status","success");
  }



}
