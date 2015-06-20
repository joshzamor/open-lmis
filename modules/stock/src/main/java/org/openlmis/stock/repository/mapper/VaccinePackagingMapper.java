package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.openlmis.stock.domain.FlightArrival;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.domain.VaccinePackaging;
import org.springframework.stereotype.Repository;

/**
 * Created by Morley on 6/20/2015.
 */
@Repository
public interface VaccinePackagingMapper extends StockMapper<VaccinePackaging>{

    @Insert("insert into flight_arrival (awb_number, destination, flight_number,estimate_time_of_arrival,time_of_arrival) values " +
            "(#{awb_number}, #{destination}, #{flight_number}, #{estimate_time_of_arrival, #{time_of_arrival}})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(VaccinePackaging vaccinePackaging);

    @Update("update flight_arrival " +
            "set " +
            " awb_number = #{awb_number}, " +
            " destination = #{destination}, " +
            " flight_number = #{flight_number}, " +
            " estimate_time_of_arrival = #{estimate_time_of_arrival} " +
            " time_of_arrival = #{time_of_arrival} " +
            "where id = #{id}")
    void update(VaccinePackaging vaccinePackaging);
}
