package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.core.domain.GeographicZone;
import org.openlmis.stock.domain.Manufacturer;
import org.openlmis.stock.domain.Vaccine;

/**
 * Created by Morley on 6/17/2015.
 */
public interface HasGeographicZone {

    @Select("select * from geographic_zones where id = #{id}")
    GeographicZone getGeographicZoneById(@Param("id") Long id);
}
