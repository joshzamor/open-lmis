/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.web.controller;

import lombok.NoArgsConstructor;
import org.openlmis.core.service.SMSManagementService;
import org.openlmis.web.response.OpenLmisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

import static org.openlmis.web.response.OpenLmisResponse.error;
import static org.openlmis.web.response.OpenLmisResponse.success;


@Controller
@NoArgsConstructor
public class SMSController extends BaseController {

    @Autowired
    private SMSManagementService smsService;

    @RequestMapping(value = "/public/sms", method = RequestMethod.GET)
    public void IncomingSMS(@RequestParam(value = "message") String message, @RequestParam(value="phone_no") String phoneNumber){
        smsService.SaveIncomingSMSMessage(message,phoneNumber);
    }

    @RequestMapping(value = "/sms/setDetails",method = RequestMethod.GET,headers = ACCEPT_JSON)
    public void getParameterForSendingSms(@RequestParam( "content") String message,@RequestParam("mobile") String phoneNumber){
        try {
            smsService.sendSms(message,phoneNumber);

        } catch (Exception e){
            System.out.print(e.fillInStackTrace());
        }
    }

    @RequestMapping(value = "/getSMS",method = RequestMethod.GET)
    public void IncomingMessage(@RequestParam( "content") String message,@RequestParam("mobile") String phoneNumber){
        smsService.SaveIncomingSMSMessage(message,phoneNumber);
    }

    @RequestMapping(value = "/sms/MessageList", method = RequestMethod.GET, headers = ACCEPT_JSON)
    public ResponseEntity<OpenLmisResponse> getAll(HttpServletRequest request) {
        return OpenLmisResponse.response("sms", smsService.getSmsMessages());
    }

    @RequestMapping(value = "/sms/MessagesForMobile", method = RequestMethod.GET, headers = ACCEPT_JSON)
    public ResponseEntity<OpenLmisResponse> getMessagesForMobilePhone(@RequestParam("mobile") String mobile) {
        return OpenLmisResponse.response("sms", smsService.getMessagesForMobile(mobile));
    }

}
