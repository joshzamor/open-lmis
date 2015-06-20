<<<<<<< HEAD
package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Vaccine;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */
@Repository
public interface VaccineMapper {

    @Select("select * from vaccines")
    List<Vaccine> getAll();

    @Insert("insert into vaccines (name, packaging, gtin, doses_per_vial,vials_per_box, expire_warning_period, type,wastage,schedule,status,country_name,manufacture_id) values " +
            "(#{name}, #{packaging}, #{gtin}, #{doses_per_vial},#{vials_per_box} #{expire_warning_period}, #{type}, #{wastage}, #{schedule}, #{status}, #{country_name}, #{manufacture_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(Vaccine vaccine);

    @Update("update vaccines " +
            "set " +
            " name = #{name}, " +
            " packaging = #{packaging}," +
            " gtin = #{gtin}, " +
            " doses_per_vial = #{doses_per_vial}, " +
            " vials_per_box = #{vials_per_box}, " +
            " expire_warning_period = #{expire_warning_period} " +
            " type = #{type} " +
            " wastage = #{wastage} " +
            " schedule = #{schedule} " +
            " status = #{status} " +
            " country_name = #{country_name} " +
            " manufacture_id = #{manufacture_id} " +
            "where id = #{id}")
    void update(Vaccine vaccine);

    @Select("select * from vaccines where id = #{id}")
    Vaccine getById(@Param("id") Long id);
}
=======
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
    @Results(value = {
            @Result(property = "manufacturer", javaType = Manufacturer.class, column = "manufacturer_id",
                    one = @One(select = "getManufacturerById"))
    })
    List<Vaccine> getAll(Vaccine vaccine);

    @Select("SELECT * FROM manufacturers WHERE id = #{manufacturerId}")
    Manufacturer getManufacturerById(Long manufacturerId);

    @Insert("insert into vaccines (name, packaging, gtin, doses_per_vial,vials_per_box, expire_warning_period, type,wastage,schedule,status,country_name,manufacturer_id) values " +
            "(#{name}, #{packaging}, #{gtin}, #{doses_per_vial},#{vials_per_box}, #{expire_warning_period}, #{type}, #{wastage}, #{schedule}, #{status}, #{country_name}, #{manufacturer_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(Vaccine vaccine);

    @Update("update vaccines " +
            "set " +
            " name = #{name}, " +
            " packaging = #{packaging}," +
            " gtin = #{gtin}, " +
            " doses_per_vial = #{doses_per_vial}, " +
            " vials_per_box = #{vials_per_box}, " +
            " expire_warning_period = #{expire_warning_period}, " +
            " type = #{type}, " +
            " wastage = #{wastage}, " +
            " schedule = #{schedule}, " +
            " status = #{status}, " +
            " country_name = #{country_name}, " +
            " manufacturer_id = #{manufacturer_id} " +
            "where id = #{id}")
    void update(Vaccine vaccine);

    //@Select("select * from vaccines where id = #{id}")
    @SelectProvider(type=ModelProviders.class, method="selectById")
    @Results(value = {
            @Result(property = "manufacturer", javaType = Manufacturer.class, column = "manufacturer_id",
                    one = @One(select = "getManufacturerById"))
    })
    Vaccine getById(@Param("id") Long id,Vaccine vaccine);

    @Select("delete from vaccines where id = #{id}")
    void deleteById(@Param("id") Long id);
}
>>>>>>> ab13d65384b55a34d30f8383fc3f8589e803e45d
