package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.Select;
import org.openlmis.core.domain.User;
import org.openlmis.stock.domain.Manufacturer;

/**
 * Created by Morley on 6/20/2015.
 */
public interface HasUser {

    @Select("SELECT * FROM users WHERE id = #{userId}")
    User getUserById(Long userId);
}
