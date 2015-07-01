package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.FlightArrival;
import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.domain.Vaccine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/20/2015.
 */

@Repository
public interface FlightArrivalMapper extends StockMapper<FlightArrival>{

    @Insert("insert into var_details (awb_number, number_of_items_inspected, flight_number,estimate_time_of_arrival,actual_time_of_arrival,coolant_type,temperature_monitor) values " +
            "(#{awb_number}, #{number_of_items_inspected}, #{flight_number}, #{estimate_time_of_arrival}, #{actual_time_of_arrival}, #{coolant_type}, #{temperature_monitor})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(FlightArrival flightArrival);

    @Update("update var_details " +
            "set " +
            " awb_number = #{awb_number}, " +
            " number_of_items_inspected = #{number_of_items_inspected}, " +
            " flight_number = #{flight_number}, " +
            " estimate_time_of_arrival = #{estimate_time_of_arrival}, " +
            " actual_time_of_arrival = #{actual_time_of_arrival}, " +
            " coolant_type = #{coolant_type}, " +
            " temperature_monitor = #{temperature_monitor} " +
            "where id = #{id}")
    void update(FlightArrival flightArrival);

    @Delete("delete from var_details where id = #{id}")
    void deleteById(@Param("id") Long id,FlightArrival flightArrival);

    @Select("select * from var_details")
    List<FlightArrival> getAll(FlightArrival flightArrival);

    @Select("select from var_details where id = #{id}")
    FlightArrival getById(@Param("id") Long id,FlightArrival flightArrival);
}
