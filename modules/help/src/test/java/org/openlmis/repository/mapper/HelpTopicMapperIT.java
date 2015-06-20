
/*
* This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
*
* This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
*
* You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
*/
package org.openlmis.repository.mapper;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openlmis.core.query.QueryExecutor;
import org.openlmis.db.categories.IntegrationTests;
import org.openlmis.help.Repository.mapper.HelpTopicMapper;
import org.openlmis.help.domain.HelpTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@Category(IntegrationTests.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-applicationContext-help.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "openLmisTransactionManager")
@Transactional
public class HelpTopicMapperIT {
    @Autowired
    private HelpTopicMapper topicMapper;
    @Autowired
    private QueryExecutor queryExecutor;

    @Test
    public void testcreateRootHelpTopic() throws SQLException {
        HelpTopic helpTopic = new HelpTopic();
//        helpTopic.setId(Long.valueOf(1));
        helpTopic.setName("Testing Sample Help Topic");
        helpTopic.setLevel(2);
        this.topicMapper.insert(helpTopic);
        ResultSet resultSet = queryExecutor.execute("SELECT * FROM elmis_help_topic WHERE name = ?", helpTopic.getName());
        resultSet.next();
        assertThat(resultSet.getString("name"), is(helpTopic.getName()));

    }

}
