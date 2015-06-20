/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2013 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */

package org.openlmis.core.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.openlmis.core.domain.FacilityProgramProduct;
import org.openlmis.core.domain.Program;
import org.openlmis.core.domain.ProgramProduct;
import org.openlmis.core.domain.ProgramProductISA;
import org.openlmis.core.repository.FacilityProgramProductRepository;
import org.openlmis.db.categories.UnitTests;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.rule.PowerMockRule;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.spy;

@Category(UnitTests.class)
@PrepareForTest(FacilityProgramProduct.class)
public class FacilityProgramProductServiceTest {

  @Rule
  public PowerMockRule rule = new PowerMockRule();

  @InjectMocks
  private FacilityProgramProductService service;

  @Mock
  private FacilityProgramProductRepository repository;

  @Mock
  ProgramProductService programProductService;

  @Test
  public void shouldInsertIsa() throws Exception {
    ProgramProductISA isa = new ProgramProductISA();
    service.insertISA(isa);
    verify(repository).insertISA(isa);
  }

  @Test
  public void shouldUpdateIsa() throws Exception {
    ProgramProductISA isa = new ProgramProductISA();
    service.updateISA(isa);
    verify(repository).updateISA(isa);
  }

  @Test
  public void shouldGetProductsFilledWithIsaForAFacility() throws Exception {
    Long facilityId = 2L;

    final ProgramProduct programProduct = new ProgramProduct();
    programProduct.setId(1L);

    final ProgramProduct programProduct2 = new ProgramProduct();
    programProduct2.setId(2L);

    List<ProgramProduct> products = new ArrayList<ProgramProduct>() {{
      add(programProduct);
      add(programProduct2);
    }};
    when(programProductService.getByProgram(new Program(1L))).thenReturn(products);

    FacilityProgramProduct facilityProduct1 = new FacilityProgramProduct(programProduct, 2L, 34);
    when(repository.getOverriddenIsa(programProduct.getId(), facilityId)).thenReturn(34);

    FacilityProgramProduct facilityProduct2 = new FacilityProgramProduct(programProduct2, 2L, 44);
    when(repository.getOverriddenIsa(programProduct2.getId(), facilityId)).thenReturn(44);

    List<FacilityProgramProduct> returnedProducts = service.getForProgramAndFacility(1l, facilityId);

    assertThat(returnedProducts.get(0), is(facilityProduct1));
    assertThat(returnedProducts.get(0).getOverriddenIsa(), is(34));

    assertThat(returnedProducts.get(1), is(facilityProduct2));
    assertThat(returnedProducts.get(1).getOverriddenIsa(), is(44));

    verify(programProductService).getByProgram(new Program(1L));
    verify(repository).getOverriddenIsa(programProduct.getId(), facilityId);
    verify(repository).getOverriddenIsa(programProduct2.getId(), facilityId);
  }

  @Test
  public void shouldSaveFacilityProgramProductList() throws Exception {
    final FacilityProgramProduct facilityProduct1 = spy(new FacilityProgramProduct());
    facilityProduct1.setId(1L);
    final FacilityProgramProduct facilityProduct2 = spy(new FacilityProgramProduct());
    facilityProduct1.setId(2L);
    List<FacilityProgramProduct> facilityProgramProducts = new ArrayList<FacilityProgramProduct>() {{
      add(facilityProduct1);
      add(facilityProduct2);
    }};

    service.saveOverriddenIsa(1L, facilityProgramProducts);

    verify(repository).save(facilityProduct1);
    verify(repository).save(facilityProduct2);
  }

  @Test
  public void shouldGetActiveFacilityProgramProductsForFacilityAndProgram() throws Exception {
    List<FacilityProgramProduct> facilityProgramProducts = new ArrayList<>();
    List<FacilityProgramProduct> activeFacilityProgramProducts = new ArrayList<>();
    Long facilityId = 1L;
    Long programId = 1L;

    mockStatic(FacilityProgramProduct.class);
    FacilityProgramProductService spiedService = spy(service);

    doReturn(facilityProgramProducts).when(spiedService).getForProgramAndFacility(programId, facilityId);
    when(FacilityProgramProduct.filterActiveProducts(facilityProgramProducts)).thenReturn(activeFacilityProgramProducts);

    List<FacilityProgramProduct> returnedFacilityProgramProducts = spiedService.getActiveProductsForProgramAndFacility(programId, facilityId);

    assertThat(returnedFacilityProgramProducts, is(activeFacilityProgramProducts));
    verify(spiedService).getForProgramAndFacility(programId, facilityId);
  }
}
