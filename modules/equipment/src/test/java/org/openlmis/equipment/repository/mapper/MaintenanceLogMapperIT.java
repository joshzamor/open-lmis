/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.equipment.repository.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openlmis.core.builder.FacilityBuilder;
import org.openlmis.core.domain.Facility;
import org.openlmis.core.repository.mapper.FacilityMapper;
import org.openlmis.db.categories.IntegrationTests;
import org.openlmis.equipment.builder.MaintenanceLogBuilder;
import org.openlmis.equipment.domain.MaintenanceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.natpryce.makeiteasy.MakeItEasy.a;
import static com.natpryce.makeiteasy.MakeItEasy.make;

import static org.junit.Assert.*;
@Category(IntegrationTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-applicationContext-equipment.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true, transactionManager = "openLmisTransactionManager")
public class MaintenanceLogMapperIT {

  @Autowired
  private MaintenanceLogMapper mapper;

  @Autowired
  private FacilityMapper facilityMapper;

  @Before
  public void setup(){

  }

  @Test
  public void testGetById() throws Exception {
    Facility facility  = make(a(FacilityBuilder.defaultFacility));
    facilityMapper.insert(facility);

    MaintenanceLog log = make(a(MaintenanceLogBuilder.defaultMaintenanceLog));
    log.setFacilityId(facility.getId());
    mapper.insert(log);

    MaintenanceLog log2 = mapper.getById(log.getId());
    assertEquals(log.getRecommendation(), log2.getRecommendation());
  }

  @Test
  public void testGetAll() throws Exception {
    Facility facility  = make(a(FacilityBuilder.defaultFacility));
    facilityMapper.insert(facility);

    List<MaintenanceLog> logs = mapper.getAll();
    assertEquals(0, logs.size());

    MaintenanceLog log = make(a(MaintenanceLogBuilder.defaultMaintenanceLog));
    log.setFacilityId(facility.getId());
    mapper.insert(log);

    logs = mapper.getAll();
    assertEquals(1, logs.size());
  }

  @Test
  public void testGetAllForFacility() throws Exception {
    Facility facility  = make(a(FacilityBuilder.defaultFacility));
    facilityMapper.insert(facility);

    assertEquals(0, mapper.getAllForFacility(facility.getId()).size());

    MaintenanceLog log = make(a(MaintenanceLogBuilder.defaultMaintenanceLog));
    log.setFacilityId(facility.getId());
    mapper.insert(log);

    List<MaintenanceLog> logs = mapper.getAllForFacility(facility.getId());
    assertEquals(1, logs.size());

  }

  @Test
  public void testGetAllForVendor() throws Exception {
    int initialSize = mapper.getAllForVendor(1L).size();
    Facility facility  = make(a(FacilityBuilder.defaultFacility));
    facilityMapper.insert(facility);

    MaintenanceLog log = make(a(MaintenanceLogBuilder.defaultMaintenanceLog));
    log.setFacilityId(facility.getId());
    log.setVendorId(1L);
    mapper.insert(log);

    List<MaintenanceLog> logs = mapper.getAllForVendor(1L);
    assertEquals(initialSize + 1, logs.size());
  }

  @Test
  public void testInsert() throws Exception {
    int initialSize = mapper.getAllForVendor(1L).size();
    Facility facility  = make(a(FacilityBuilder.defaultFacility));
    facilityMapper.insert(facility);

    MaintenanceLog log = make(a(MaintenanceLogBuilder.defaultMaintenanceLog));
    log.setFacilityId(facility.getId());
    log.setVendorId(1L);
    mapper.insert(log);

    List<MaintenanceLog> logs = mapper.getAll();
    assertEquals(initialSize + 1, logs.size());
  }

  @Test
  public void testUpdate() throws Exception {
    int initialSize = mapper.getAllForVendor(1L).size();
    Facility facility  = make(a(FacilityBuilder.defaultFacility));
    facilityMapper.insert(facility);

    MaintenanceLog log = make(a(MaintenanceLogBuilder.defaultMaintenanceLog));
    log.setFacilityId(facility.getId());
    log.setVendorId(1L);
    mapper.insert(log);

    log.setFinding("New Finding");
  }
}