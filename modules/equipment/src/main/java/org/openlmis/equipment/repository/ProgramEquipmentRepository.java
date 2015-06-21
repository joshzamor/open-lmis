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

import org.openlmis.equipment.domain.ProgramEquipment;
import org.openlmis.equipment.repository.mapper.EquipmentProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProgramEquipmentRepository {
  @Autowired
  EquipmentProgramMapper equipmentProgramMapper;

  public List<ProgramEquipment> getByProgramId(Long programId){
    return equipmentProgramMapper.getByProgramId(programId);
  }

  public void insert(ProgramEquipment programEquipment) {
    equipmentProgramMapper.insert(programEquipment);
  }

  public void update(ProgramEquipment programEquipment) {
    equipmentProgramMapper.update(programEquipment);
  }

    public void remove(Long programEquipmentId){
        equipmentProgramMapper.remove(programEquipmentId);
    }
}
