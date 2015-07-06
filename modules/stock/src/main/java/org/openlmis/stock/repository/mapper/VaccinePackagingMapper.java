package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/20/2015.
 */
@Repository
public interface VaccinePackagingMapper extends HasVaccineMapper,StockMapper<VaccinePackaging>{

    @Select("select * from vaccine_packaging")
    @Results(value = {
            @Result(property = "vaccine", javaType = Vaccine.class, column = "vaccine_id",
                    one = @One(select = "getVaccineById")),
            @Result(property = "manufacturer", javaType = Manufacturer.class, column = "manufacturer_id",
                    one = @One(select = "getManufacturerById"))
    })
    List<VaccinePackaging> getAll();

    @Select("SELECT * FROM manufacturers WHERE id = #{manufacturerId}")
    Manufacturer getManufacturerById(Long manufacturerId);

    @Select("select * from vaccine_packaging where id = #{id}")
    @Results(value = {
            @Result(property = "vaccine", javaType = Vaccine.class, column = "vaccine_id",
                    one = @One(select = "getVaccineById")),
            @Result(property = "manufacturer", javaType = Manufacturer.class, column = "manufacturer_id",
                    one = @One(select = "getManufacturerById"))
    })
    VaccinePackaging getById(@Param("id") Long id);

    @Insert("insert into vaccine_packaging (packaging, gtin, doses_per_vial,vials_per_box,wastage,schedule,status,country_id,manufacturer_id,vaccine_id) values " +
            "(#{packaging}, #{gtin}, #{doses_per_vial}, #{vials_per_box}, #{wastage}, #{schedule}, #{status}, #{country_id}, #{manufacturer_id}, #{vaccine_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(VaccinePackaging vaccinePackaging);

    @Update("update vaccine_packaging " +
            "set " +
            " packaging = #{packaging}, " +
            " gtin = #{gtin}, " +
            " doses_per_vial = #{doses_per_vial}, " +
            " vials_per_box = #{vials_per_box}, " +
            " wastage = #{wastage}, " +
            " schedule = #{schedule}, " +
            " status = #{status}, " +
            " country_id = #{country_id}, " +
            " manufacturer_id = #{manufacturer_id}, " +
            " vaccine_id = #{vaccine_id} " +
            "where id = #{id}")
    void update(VaccinePackaging vaccinePackaging);

    @SelectProvider(type=ModelProviders.class, method="filterModal")
    @Results(value = {
            @Result(property = "vaccine", javaType = Vaccine.class, column = "vaccine_id",
                    one = @One(select = "getVaccineById")),
            @Result(property = "manufacturer", javaType = Manufacturer.class, column = "manufacturer_id",
                    one = @One(select = "getManufacturerById"))
    })
    List<VaccinePackaging> filter(@Param("filter") String filter,VaccinePackaging vaccinePackaging);

    @Delete("delete from vaccine_packaging where id = #{id}")
    void deleteById(@Param("id") Long id);
}
