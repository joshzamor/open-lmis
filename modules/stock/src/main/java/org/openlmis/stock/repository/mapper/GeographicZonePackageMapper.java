package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.core.domain.GeographicZone;
import org.openlmis.stock.domain.GeographicZonePackage;
import org.openlmis.stock.domain.GeographicZoneStock;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Repository
public interface GeographicZonePackageMapper extends HasGeographicZone,StockMapper<GeographicZonePackage>{

    @Select("select * from geographic_zone_package")
    @Results(value = {
            @Result(property = "geographic_zone", javaType = GeographicZone.class, column = "geographic_zone_id",
                    one = @One(select = "getGeographicZoneById"))
    })
    List<GeographicZonePackage> getAll();

    @Insert("insert into geographic_zone_stock (package_number, number_of_packages, date_sent, date_recieved, recieved_status, sending_user, receiving_user, facility_id, geographic_zone_id, date_recieved) values " +
            "(#{package_number}, #{number_of_packages}, #{date_sent}, #{date_recieved}, #{recieved_status}, #{sending_user}, #{receiving_user}, #{facility_id}, #{geographic_zone_id}, #{date_recieved})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(GeographicZonePackage geographicZonePackage);

    @Update("update geographic_zone_package " +
            "set " +
            " package_number = #{package_number}, " +
            " number_of_packages = #{number_of_packages}," +
            " date_sent = #{date_sent}, " +
            " date_recieved = #{date_recieved}, " +
            " recieved_status = #{recieved_status} " +
            " sending_user = #{sending_user} " +
            " receiving_user = #{receiving_user} " +
            " facility_id = #{facility_id} " +
            " geographic_zone_id = #{geographic_zone_id} " +
            " date_recieved = #{date_recieved} " +
            "where id = #{id}")
    void update(GeographicZonePackage geographicZonePackage);

    @Select("select * from geographic_zone_package where id = #{id}")
    @Results(value = {
            @Result(property = "geographic_zone", javaType = GeographicZone.class, column = "geographic_zone_id",
                    one = @One(select = "getGeographicZoneById"))
    })
    GeographicZonePackage getById(@Param("id") Long id);

}
