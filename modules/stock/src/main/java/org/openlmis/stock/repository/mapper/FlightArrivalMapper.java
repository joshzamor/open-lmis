package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.FlightArrival;
import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.domain.Vaccine;
import org.springframework.stereotype.Repository;

/**
 * Created by Morley on 6/20/2015.
 */

@Repository
public interface FlightArrivalMapper extends StockMapper<FlightArrival>{

    @Insert("insert into flight_arrival (awb_number, destination, flight_number,estimate_time_of_arrival,time_of_arrival) values " +
            "(#{awb_number}, #{destination}, #{flight_number}, #{estimate_time_of_arrival}, #{time_of_arrival})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(FlightArrival flightArrival);

    @Update("update flight_arrival " +
            "set " +
            " awb_number = #{awb_number}, " +
            " destination = #{destination}, " +
            " flight_number = #{flight_number}, " +
            " estimate_time_of_arrival = #{estimate_time_of_arrival}, " +
            " time_of_arrival = #{time_of_arrival} " +
            "where id = #{id}")
    void update(FlightArrival flightArrival);

    @Delete("delete from flight_arrival where id = #{id}")
    void deleteById(@Param("id") Long id,FlightArrival flightArrival);
}
