package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.core.domain.GeographicZone;
import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.domain.GeographicZoneStock;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.domain.VaccinePackaging;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Repository
public interface GeographicZoneStockMapper  extends HasVaccineMapper,HasVaccinePackaging,HasGeographicZone,StockMapper<GeographicZoneStock>{
    @Select("select * from geographic_zone_stocks")
    @Results(value = {
            @Result(property = "vaccine_packaging", javaType = VaccinePackaging.class, column = "vaccine_packaging_id",
                one = @One(select = "getVaccinePackagingById")),
            @Result(property = "geographic_zone", javaType = GeographicZone.class, column = "geographic_zone_id",
                    one = @One(select = "getGeographicZoneById"))
    })
    List<GeographicZoneStock> getAll();

    @Insert("insert into geographic_zone_stocks (expire_date, lot_number, number_of_doses, vaccine_packaging_id, geographic_zone_id) values " +
            "(#{expire_date}, #{lot_number}, #{number_of_doses}, #{vaccine_packaging_id}, #{geographic_zone_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(GeographicZoneStock geographicZoneStock);

    @Update("update geographic_zone_stocks " +
            "set " +
            " expire_date = #{expire_date}, " +
            " lot_number = #{lot_number}," +
            " number_of_doses = #{number_of_doses}, " +
            " vaccine_packaging_id = #{vaccine_packaging_id}, " +
            " geographic_zone_id = #{geographic_zone_id} " +
            "where id = #{id}")
    void update(GeographicZoneStock geographicZoneStock);

    @Select("select * from geographic_zone_stocks where id = #{id}")
    @Results(value = {
            @Result(property = "vaccine_packaging", javaType = VaccinePackaging.class, column = "vaccine_packaging_id",
                    one = @One(select = "getVaccinePackagingById")),
            @Result(property = "geographic_zone", javaType = GeographicZone.class, column = "geographic_zone_id",
                            one = @One(select = "getGeographicZoneById"))
    })
    GeographicZoneStock getById(@Param("id") Long id);

    @SelectProvider(type=ModelProviders.class, method="filterModal")
    List<GeographicZoneStock> filter(@Param("filter") String filter,GeographicZoneStock geographicZonePackage);

}
