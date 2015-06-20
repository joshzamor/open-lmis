package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Manufacturer;
import org.openlmis.stock.domain.Vaccine;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */
@Repository
public interface VaccineMapper extends StockMapper<Vaccine>{

    @Select("select * from vaccines")
    //@SelectProvider(type=ModelProviders.class, method="selectAll")
    /*@Results(value = {
            @Result(property = "manufacturer", javaType = Manufacturer.class, column = "manufacturer_id",
                    one = @One(select = "getManufacturerById"))
    })*/
    List<Vaccine> getAll(Vaccine vaccine);

    @Select("SELECT * FROM manufacturers WHERE id = #{manufacturerId}")
    Manufacturer getManufacturerById(Long manufacturerId);

    @Insert("insert into vaccines (name, expire_warning_period, type,status) values " +
            "(#{name}, #{expire_warning_period}, #{type}, #{status})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(Vaccine vaccine);

    @Update("update vaccines " +
            "set " +
            " name = #{name}, " +
            " expire_warning_period = #{expire_warning_period}, " +
            " type = #{type}, " +
            " status = #{status} " +
            "where id = #{id}")
    void update(Vaccine vaccine);

    //@Select("select * from vaccines where id = #{id}")
    @SelectProvider(type=ModelProviders.class, method="selectById")
    /*@Results(value = {
            @Result(property = "manufacturer", javaType = Manufacturer.class, column = "manufacturer_id",
                    one = @One(select = "getManufacturerById"))
    })*/
    Vaccine getById(@Param("id") Long id,Vaccine vaccine);

    @Select("delete from vaccines where id = #{id}")
    void deleteById(@Param("id") Long id);
}
