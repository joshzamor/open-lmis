package org.openlmis.equipment.service;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.mockito.runners.MockitoJUnitRunner;
import org.openlmis.db.categories.UnitTests;
import org.openlmis.equipment.domain.EquipmentProduct;
import org.openlmis.equipment.repository.EquipmentProductRepository;


@Category(UnitTests.class)
@RunWith(MockitoJUnitRunner.class)
public class ProgramEquipmentProductServiceTest {

  @Mock
  EquipmentProductRepository repository;

  @InjectMocks
  ProgramEquipmentProductService service;

  @Test
  public void shouldGetByProgramEquipmentId() throws Exception {
    service.getByProgramEquipmentId(3L);
    verify(repository).getByProgramEquipmentId(3L);
  }

  @Test
  public void shouldSaveNewRecords() throws Exception {
    EquipmentProduct pp = new EquipmentProduct();
    service.Save(pp);
    verify(repository).insert(pp);
    verify(repository, never()).update(pp);
  }

  @Test
  public void shouldUpdaetRecords() throws Exception {
    EquipmentProduct pp = new EquipmentProduct();
    pp.setId(3L);
    service.Save(pp);

    verify(repository, never()).insert(pp);
    verify(repository).update(pp);
  }


  @Test
  public void shouldRemove() throws Exception{
    service.remove(3L);
    verify(repository).remove(3L);
  }

  @Test
  public void shouldRemoveEquipmentProducts() throws Exception {
    service.removeAllByEquipmentProducts(5L);
    verify(repository).removeAllByEquipmentProducts(5L);
  }

  @Test
  public void shouldGetAvailableProductsToLink() throws Exception {
    service.getAvailableProductsToLink(4L, 6L);
    verify(repository).getAvailableProductsToLink(4L, 6L);
  }
}