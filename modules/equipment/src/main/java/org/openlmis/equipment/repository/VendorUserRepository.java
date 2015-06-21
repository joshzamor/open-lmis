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

import org.openlmis.core.domain.User;
import org.openlmis.equipment.domain.VendorUser;
import org.openlmis.equipment.repository.mapper.VendorUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendorUserRepository {

  @Autowired
  private VendorUserMapper vendorUserMapper;

  public List<User> getAllUsersForVendor(Long vendorId) {
    return vendorUserMapper.getAllUsersForVendor(vendorId);
  }

  public void insert(VendorUser vendorUser){
    vendorUserMapper.insert(vendorUser);
  }

  public void update(VendorUser vendorUser){
    vendorUserMapper.update(vendorUser);
  }

  public void removeVendorUserAssociation(Long vendorId, Long userId) {
    vendorUserMapper.remove(vendorId,userId);
  }

  public List<User> getAllUsersAvailableForVendor() {
    return vendorUserMapper.getAllUsersAvailableForVendor();
  }
}
