package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.VarDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/20/2015.
 */

@Repository
public interface VarDetailMapper extends StockMapper<VarDetails>{

    @Insert("insert into var_details (awb_number, number_of_items_inspected, flight_number,estimate_time_of_arrival,actual_time_of_arrival,coolant_type,temperature_monitor,labels,comments,invoice,packing_list,release_certificate,airway_bill) values " +
            "(#{awb_number}, #{number_of_items_inspected}, #{flight_number}, #{estimate_time_of_arrival}, #{actual_time_of_arrival}, #{coolant_type}, #{temperature_monitor},#{labels},#{comments},#{invoice},#{packing_list},#{release_certificate},#{airway_bill})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(VarDetails varDetails);

    @Update("update var_details " +
            "set " +
            " awb_number = #{awb_number}, " +
            " number_of_items_inspected = #{number_of_items_inspected}, " +
            " flight_number = #{flight_number}, " +
            " estimate_time_of_arrival = #{estimate_time_of_arrival}, " +
            " actual_time_of_arrival = #{actual_time_of_arrival}, " +
            " coolant_type = #{coolant_type}, " +
            " temperature_monitor = #{temperature_monitor}, " +
            " labels = #{labels}, " +
            " comments = #{comments}, " +
            " invoice = #{invoice}, " +
            " packing_list = #{packing_list}, " +
            " release_certificate = #{release_certificate}, " +
            " airway_bill = #{airway_bill} " +
            "where id = #{id}")
    void update(VarDetails varDetails);

    @Delete("delete from var_details where id = #{id}")
    void deleteById(@Param("id") Long id,VarDetails varDetails);

    @Select("select * from var_details")
    List<VarDetails> getAll(VarDetails varDetails);

    @Select("select from var_details where id = #{id}")
    VarDetails getById(@Param("id") Long id);
}
