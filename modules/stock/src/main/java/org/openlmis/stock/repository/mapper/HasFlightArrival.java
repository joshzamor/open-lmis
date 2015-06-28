package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.openlmis.core.domain.GeographicZone;
import org.openlmis.stock.domain.FlightArrival;

/**
 * Created by Morley on 6/28/2015.
 */
public interface HasFlightArrival {

    @Select("select * from var_details where id = #{id}")
    FlightArrival getFlightArrivalById(@Param("id") Long id);
}
