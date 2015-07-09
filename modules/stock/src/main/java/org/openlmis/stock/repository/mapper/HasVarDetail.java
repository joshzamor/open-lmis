package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.openlmis.stock.domain.VarDetails;

/**
 * Created by Morley on 6/28/2015.
 */
public interface HasVarDetail {

    @Select("select * from var_details where id = #{id}")
    VarDetails getVarDetailsById(@Param("id") Long id);
}
