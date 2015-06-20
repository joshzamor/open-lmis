package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.openlmis.stock.domain.Manufacturer;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.domain.VaccinePackaging;

/**
 * Created by Morley on 6/20/2015.
 */
public interface HasVaccinePackaging extends HasVaccineMapper{

    @Select("SELECT * FROM vaccine_packaging WHERE id = #{packagingId}")
    @Results(value = {
            @Result(property = "vaccine", javaType = Vaccine.class, column = "vaccine_id",
                    one = @One(select = "getVaccineById")),
            @Result(property = "manufacturer", javaType = Manufacturer.class, column = "manufacturer_id",
                    one = @One(select = "getManufacturerById"))
    })
    VaccinePackaging getVaccinePackagingById(Long packagingId);

    @Select("SELECT * FROM manufacturers WHERE id = #{manufacturerId}")
    Manufacturer getManufacturerById(Long manufacturerId);
}
