/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.restapi.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import lombok.NoArgsConstructor;
import org.openlmis.report.model.dto.StockStatusDTO;
import org.openlmis.report.response.OpenLmisResponse;
import org.openlmis.report.service.StockStatusService;
import org.openlmis.restapi.domain.Agent;
import org.openlmis.restapi.response.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Api(value="Stock Status", description = "Returns monthly and quarterly stock status", position = 0)
public class StockStatusController extends BaseController {

  @Autowired
  private StockStatusService service;



  @ApiOperation(value = "Returns stock status details", notes = "Returns a complete list stock status for selected program.", response = StockStatusDTO.class)
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successful retrieval of Stock Status detail", response = StockStatusDTO.class),
    @ApiResponse(code = 404, message = "Given program does not exist"),
    @ApiResponse(code = 500, message = "Internal server error")}
  )
  @RequestMapping(value = "/rest-api/stock-status/quarter", method = GET, headers = ACCEPT_JSON)
  public ResponseEntity getStockStatusQuarterly(
                      @RequestParam("quarter") Long quarter,
                      @RequestParam("year") Long year,
                      @RequestParam("program") String program,
                      Principal principal) {
    return RestResponse.response("report", service.getStockStatusByQuarter(program, year, quarter, loggedInUserId(principal)));
  }

  @ApiOperation(value = "Returns stock status details", notes = "Returns a complete list stock status for selected program.", response = StockStatusDTO.class)
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successful retrieval of Stock Status detail", response = StockStatusDTO.class),
    @ApiResponse(code = 404, message = "Given program does not exist"),
    @ApiResponse(code = 500, message = "Internal server error")}
  )
  @RequestMapping(value = "/rest-api/stock-status/monthly", method = GET, headers = ACCEPT_JSON)
  public ResponseEntity getStockStatusMonthly(
    @RequestParam("month") Long month,
    @RequestParam("year") Long year,
    @RequestParam("program") String program,
    Principal principal) {
    return RestResponse.response("report", service.getStockStatusByMonth(program, year, month,loggedInUserId(principal)));
  }

}
