package org.openlmis.equipment.repository;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.mockito.runners.MockitoJUnitRunner;
import org.openlmis.db.categories.UnitTests;
import org.openlmis.equipment.domain.MaintenanceLog;
import org.openlmis.equipment.repository.mapper.MaintenanceLogMapper;


@Category(UnitTests.class)
@RunWith(MockitoJUnitRunner.class)
public class MaintenanceLogRepositoryTest {

  @Mock
  MaintenanceLogMapper mapper;

  @InjectMocks
  MaintenanceLogRepository repository;

  @Test
  public void shouldGetById() throws Exception {
    repository.getById(9L);
    verify(mapper).getById(9L);
  }

  @Test
  public void shouldGetAllForFacility() throws Exception {
    repository.getAllForFacility(9L);
    verify(mapper).getAllForFacility(9L);
  }

  @Test
  public void shouldGetAllForVendor() throws Exception {
    repository.getAllForVendor(9L);
    verify(mapper).getAllForVendor(9L);
  }

  @Test
  public void shouldGetAll() throws Exception {
    repository.getAll();
    verify(mapper).getAll();
  }

  @Test
  public void shouldInsert() throws Exception {
    MaintenanceLog log = new MaintenanceLog();
    repository.insert(log);
    verify(mapper).insert(log);
  }

  @Test
  public void shouldUpdate() throws Exception {
    MaintenanceLog log = new MaintenanceLog();
    repository.update(log);
    verify(mapper).update(log);
  }
}