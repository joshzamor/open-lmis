package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Vaccine;
import org.springframework.stereotype.Repository;
import org.openlmis.stock.domain.Diluent;
import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Repository
public interface DiluentMapper {
    @Select("select * from diluent")
    List<Diluent> getAll();

    @Insert("insert into diluent (name, unit_per_box, vaccine_id) values " +
            "(#{name}, #{unit_per_box}, #{vaccine_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(Diluent diluent);

    @Update("update diluent " +
            "set " +
            " name = #{name}, " +
            " unit_per_box = #{unit_per_box}," +
            " vaccine_id = #{vaccine_id}, " +
            "where id = #{id}")
    void update(Diluent diluent);

    @Select("select * from diluent where id = #{id}")
    Diluent getById(@Param("id") Long id);
}
