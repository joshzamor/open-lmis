package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.Select;
import org.openlmis.stock.domain.Manufacturer;
import org.openlmis.stock.domain.VaccinePackaging;

/**
 * Created by Morley on 6/20/2015.
 */
public interface HasVaccinePackaging {

    @Select("SELECT * FROM vaccine_packaging WHERE id = #{packagingId}")
    VaccinePackaging getVaccinePackagingById(Long packagingId);
}
