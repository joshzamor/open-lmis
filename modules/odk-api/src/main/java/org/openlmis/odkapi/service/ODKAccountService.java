/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2013 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */
/**
 * Created with IntelliJ IDEA.
 * User: Messay Yohannes <deliasmes@gmail.com>
 * To change this template use File | Settings | File Templates.
 */
package org.openlmis.odkapi.service;

import org.openlmis.odkapi.domain.ODKAccount;
import org.openlmis.odkapi.exception.ODKAccountNotFoundException;
import org.openlmis.odkapi.repository.ODKAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ODKAccountService  {

    @Autowired
    ODKAccountRepository odkAccountRepository;

    public ODKAccount validateApiAuthenticationToken(String token)
    {
        return odkAccountRepository.getODKAccountByDeviceId(token);
    }

    public ODKAccount getODKAccountByDeviceId(String deviceId)
    {
        return odkAccountRepository.getODKAccountByDeviceId(deviceId);
    }

    public ODKAccount authenticate(ODKAccount odkAccount) throws ODKAccountNotFoundException
    {
        // currently authenticating using only the device id of the android based device accessing the
        // system
        ODKAccount result = odkAccountRepository.getODKAccountByDeviceId(odkAccount.getDeviceId());
        if (result == null)
        {
            throw new ODKAccountNotFoundException();
        }

        else
        {
            return result;
        }

    }


}
