package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.core.domain.GeographicZone;
import org.openlmis.stock.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Repository
public interface GeographicZoneArrivalPackageMapper extends HasGeographicZone,HasUser,HasVaccinePackaging,StockMapper<GeographicZoneArrivalPackage>{

    @Select("select * from geographic_zone_arrival_package")
    @Results(value = {
            @Result(property = "geographic_zone", javaType = GeographicZone.class, column = "geographic_zone_id",
                    one = @One(select = "getGeographicZoneById")),
            @Result(property = "user", javaType = GeographicZone.class, column = "receiving_user",
                    one = @One(select = "getUserById")),
            @Result(property = "vaccine_packaging", javaType = VaccinePackaging.class, column = "vaccine_packaging_id",
                    one = @One(select = "getVaccinePackagingById"))
    })
    List<GeographicZoneArrivalPackage> getAll();



    @Insert("insert into geographic_zone_arrival_package (vaccine_packaging_id, package_number, lot_number, number_as_expected, gtin, number_recieved, number_expected, physical_damage, vvm_status, problems, receiving_user, geographic_zone_id) values " +
            "(#{vaccine_packaging_id}, #{package_number}, #{lot_number}, #{number_as_expected}, #{gtin}, #{number_recieved}, #{number_expected}, #{physical_damage}, #{vvm_status}, #{problems}, #{receiving_user}, #{geographic_zone_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(GeographicZoneArrivalPackage geographicZoneArrivalPackage);

    @Update("update geographic_zone_arrival_package " +
            "set " +
            " vaccine_packaging_id = #{vaccine_packaging_id}, " +
            " package_number = #{package_number}," +
            " lot_number = #{lot_number}, " +
            " number_as_expected = #{number_as_expected}, " +
            " gtin = #{gtin} " +
            " number_recieved = #{number_recieved} " +
            " number_expected = #{number_expected} " +
            " physical_damage = #{physical_damage} " +
            " vvm_status = #{vvm_status} " +
            " problems = #{problems} " +
            " receiving_user = #{receiving_user} " +
            " geographic_zone_id = #{geographic_zone_id} " +
            "where id = #{id}")
    void update(GeographicZoneArrivalPackage geographicZoneArrivalPackage);

    @Select("select * from geographic_zone_arrival_package where id = #{id}")
    @Results(value = {
            @Result(property = "geographic_zone", javaType = GeographicZone.class, column = "geographic_zone_id",
                    one = @One(select = "getGeographicZoneById")),
            @Result(property = "user", javaType = GeographicZone.class, column = "receiving_user",
                    one = @One(select = "getUserById")),
            @Result(property = "vaccine_packaging", javaType = VaccinePackaging.class, column = "vaccine_packaging_id",
                    one = @One(select = "getVaccinePackagingById"))
    })
    GeographicZoneArrivalPackage getById(@Param("id") Long id);


    @Delete("delete from geographic_zone_arrival_package where id = #{id}")
    void deleteById(@Param("id") Long id,GeographicZoneArrivalPackage geographicZoneArrivalPackage);

    @SelectProvider(type=ModelProviders.class, method="filterModal")
    @Results(value = {
            @Result(property = "geographic_zone", javaType = GeographicZone.class, column = "geographic_zone_id",
                    one = @One(select = "getGeographicZoneById")),
            @Result(property = "user", javaType = GeographicZone.class, column = "receiving_user",
                    one = @One(select = "getUserById")),
            @Result(property = "vaccine_packaging", javaType = VaccinePackaging.class, column = "vaccine_packaging_id",
                    one = @One(select = "getVaccinePackagingById"))
    })
    List<GeographicZoneArrivalPackage> filter(@Param("filter") String filter,GeographicZoneArrivalPackage geographicZoneArrivalPackage);
}
