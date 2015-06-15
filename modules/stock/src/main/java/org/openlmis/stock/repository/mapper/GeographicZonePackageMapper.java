package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.GeographicZonePackage;
import org.openlmis.stock.domain.GeographicZoneStock;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Repository
public interface GeographicZonePackageMapper {

    @Select("select * from geographic_zone_stock")
    List<GeographicZonePackage> getAll();

    @Insert("insert into geographic_zone_stock (package_number, number_of_packages, date_sent, date_recieved, recieved_status, sending_user, receiving_user, facility_id, geographic_zone_id, date_recieved) values " +
            "(#{package_number}, #{number_of_packages}, #{date_sent}, #{date_recieved}, #{recieved_status}, #{sending_user}, #{receiving_user}, #{facility_id}, #{geographic_zone_id}, #{date_recieved})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(GeographicZonePackage geographicZonePackage);

    @Update("update geographic_zone_stock " +
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

    @Select("select * from geographic_zone_stock where id = #{id}")
    GeographicZonePackage getById(@Param("id") Long id);
}
