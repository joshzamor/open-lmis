<<<<<<< HEAD
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
=======
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
public interface DiluentMapper  extends HasVaccineMapper,StockMapper<Diluent>{

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

    @Select("select * from diluent")
    List<Diluent> getAll();

    @Select("select * from diluent where id = #{id}")
    Diluent getById(@Param("id") Long id);

    @Select("delete from diluent where id = #{id}")
    void deleteById(@Param("id") Long id);

    @SelectProvider(type=ModelProviders.class, method="filterModal")
    List<Diluent> filter(@Param("filter") String filter,Diluent stockModel);
}
>>>>>>> ab13d65384b55a34d30f8383fc3f8589e803e45d
