package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.FlightArrival;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.domain.VarDocument;
import org.openlmis.stock.domain.VarPackageAlarm;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/28/2015.
 */

@Repository
public interface VarPackageAlarmMapper extends HasFlightArrival,StockMapper<VarPackageAlarm> {

    @Insert("insert into var_package_alarms (lot_number, alarm_temperature, cold_chain_monitor,time_of_inspection,var_details_id) values " +
            "(#{lot_number}, #{alarm_temperature}, #{cold_chain_monitor}, #{time_of_inspection}, #{var_details_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(VarPackageAlarm varPackageAlarm);

    @Update("update var_package_alarms " +
            "set " +
            " lot_number = #{lot_number}, " +
            " alarm_temperature = #{alarm_temperature}, " +
            " cold_chain_monitor = #{cold_chain_monitor}, " +
            " time_of_inspection = #{time_of_inspection}, " +
            " var_details_id = #{var_details_id} " +
            "where id = #{id}")
    void update(VarPackageAlarm varPackageAlarm);

    @Select("select * from var_package_alarms")
    @Results(value = {
            @Result(property = "var_details", javaType = Vaccine.class, column = "var_details_id",
                    one = @One(select = "getFlightArrivalById"))
    })
    List<VarPackageAlarm> getAll();

    @Select("select * from var_package_alarms where id = #{id}")
    @Results(value = {
            @Result(property = "var_details", javaType = Vaccine.class, column = "var_details_id",
                    one = @One(select = "getFlightArrivalById"))
    })
    VarPackageAlarm getById(@Param("id") Long id);

    @Delete("delete from var_package_alarms where id = #{id}")
    void deleteById(@Param("id") Long id);
}
