package org.openlmis.vaccine.repository.mapper;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import org.mockito.runners.MockitoJUnitRunner;
import org.openlmis.db.categories.IntegrationTests;
import org.openlmis.db.categories.UnitTests;
import org.openlmis.vaccine.domain.VitaminSupplementationAgeGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Category(IntegrationTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-applicationContext-vaccine.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true, transactionManager = "openLmisTransactionManager")
public class VitaminSupplementationAgeGroupMapperIT {

  @Autowired
  VitaminSupplementationAgeGroupMapper mapper;

  @Test
  public void shouldGetAll() throws Exception {
    List<VitaminSupplementationAgeGroup> groups = mapper.getAll();
    assertThat(groups.size(), is(2));
  }
}