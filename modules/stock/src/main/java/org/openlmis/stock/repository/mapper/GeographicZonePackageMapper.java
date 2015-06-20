package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.core.domain.GeographicZone;
import org.openlmis.core.domain.User;
import org.openlmis.stock.domain.GeographicZonePackage;
import org.openlmis.stock.domain.GeographicZoneStock;
import org.openlmis.stock.domain.VaccinePackaging;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Repository
public interface GeographicZonePackageMapper extends HasGeographicZone,HasVaccinePackaging,HasUser,StockMapper<GeographicZonePackage>{

    @Select("select * from geographic_zone_package")
    @Results(value = {
            @Result(property = "source_geographic_zone", javaType = GeographicZone.class, column = "source_geographic_zone_id",
                    one = @One(select = "getGeographicZoneById")),
            @Result(property = "destination_geographic_zone_id", javaType = GeographicZone.class, column = "destination_geographic_zone_id",
                    one = @One(select = "getGeographicZoneById")),
            @Result(property = "sending_user", javaType = GeographicZone.class, column = "sending_user_id",
                    one = @One(select = "getUserById")),
            @Result(property = "receiving_user", javaType = GeographicZone.class, column = "receiving_user_id",
                    one = @One(select = "getUserById")),
            @Result(property = "facility", javaType = GeographicZone.class, column = "facility_id",
                    one = @One(select = "getFacilityById")),
            @Result(property = "vaccine_packaging", javaType = VaccinePackaging.class, column = "vaccine_packaging_id",
                    one = @One(select = "getVaccinePackagingById"))
    })
    List<GeographicZonePackage> getAll();

    @Select("SELECT * FROM facilities WHERE id = #{facilityId}")
    User getFacilityById(Long facilityId);

    @Insert("insert into geographic_zone_stock (package_number, number_of_packages, date_sent, date_recieved, recieved_status, sending_user_id, receiving_user_id, facility_id, source_geographic_zone_id, destination_geographic_zone_id, vaccine_packaging_id) values " +
            "(#{package_number}, #{number_of_packages}, #{date_sent}, #{date_recieved}, #{recieved_status}, #{sending_user_id}, #{receiving_user_id}, #{facility_id}, #{source_geographic_zone_id}, #{destination_geographic_zone_id}, #{vaccine_packaging_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(GeographicZonePackage geographicZonePackage);

    @Update("update geographic_zone_package " +
            "set " +
            " package_number = #{package_number}, " +
            " number_of_packages = #{number_of_packages}," +
            " date_sent = #{date_sent}, " +
            " date_recieved = #{date_recieved}, " +
            " recieved_status = #{recieved_status}, " +
            " sending_user_id = #{sending_user_id}, " +
            " receiving_user_id = #{receiving_user_id}, " +
            " facility_id = #{facility_id}, " +
            " source_geographic_zone_id = #{source_geographic_zone_id}, " +
            " destination_geographic_zone_id = #{destination_geographic_zone_id}, " +
            " vaccine_packaging_id = #{vaccine_packaging_id} " +
            "where id = #{id}")
    void update(GeographicZonePackage geographicZonePackage);

    @Select("select * from geographic_zone_package where id = #{id}")
    @Results(value = {
            @Result(property = "source_geographic_zone", javaType = GeographicZone.class, column = "source_geographic_zone_id",
                    one = @One(select = "getGeographicZoneById")),
            @Result(property = "destination_geographic_zone_id", javaType = GeographicZone.class, column = "destination_geographic_zone_id",
                    one = @One(select = "getGeographicZoneById")),
            @Result(property = "sending_user", javaType = GeographicZone.class, column = "sending_user_id",
                    one = @One(select = "getUserById")),
            @Result(property = "receiving_user", javaType = GeographicZone.class, column = "receiving_user_id",
                    one = @One(select = "getUserById")),
            @Result(property = "facility", javaType = GeographicZone.class, column = "facility_id",
                    one = @One(select = "getFacilityById")),
            @Result(property = "vaccine_packaging", javaType = VaccinePackaging.class, column = "vaccine_packaging_id",
                    one = @One(select = "getVaccinePackagingById"))
    })
    GeographicZonePackage getById(@Param("id") Long id);

}
