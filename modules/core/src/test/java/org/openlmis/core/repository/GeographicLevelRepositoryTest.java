/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

package org.openlmis.core.repository;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openlmis.core.domain.GeographicLevel;
import org.openlmis.core.repository.mapper.GeographicLevelMapper;
import org.openlmis.db.categories.UnitTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@Category(UnitTests.class)
@RunWith(MockitoJUnitRunner.class)
public class GeographicLevelRepositoryTest {

  @Mock
  private GeographicLevelMapper mapper;

  @InjectMocks
  private GeographicLevelRepository repository;

  @Test
  public void shouldGetGeographicLevel() throws Exception {
    GeographicLevel expectedLevel = new GeographicLevel();
    expectedLevel.setCode("code");
    when(mapper.getGeographicLevelById(1)).thenReturn(expectedLevel);

    GeographicLevel actual = repository.getGeographicLevel(1);

    verify(mapper).getGeographicLevelById(1);
    assertEquals(actual.getCode(), "code");
  }
}
