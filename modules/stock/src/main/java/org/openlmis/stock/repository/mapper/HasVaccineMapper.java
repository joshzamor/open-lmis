package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Manufacturer;
import org.openlmis.stock.domain.Vaccine;

/**
 * Created by Morley on 6/17/2015.
 */
public interface HasVaccineMapper {
    @Select("select * from vaccines where id = #{id}")
    @Results(value = {
            @Result(property = "manufacturer", javaType = Manufacturer.class, column = "manufacturer_id",
                    one = @One(select = "getManufacturerById"))
    })
    Vaccine getVaccineById(@Param("id") Long id);

    @Select("SELECT * FROM manufacturers WHERE id = #{manufacturerId}")
    Manufacturer getManufacturerById(Long manufacturerId);
}
