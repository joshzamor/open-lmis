package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.core.domain.GeographicZone;
import org.openlmis.stock.domain.PackageArrivalPartial;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.domain.VaccinePackaging;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 7/9/2015.
 */

@Repository
public interface PackageArrivalPartialMapper  extends HasGeographicZone,StockMapper<PackageArrivalPartial> {
    @Select("select * from package_arrival_partials")
    @Results(value = {
            @Result(property = "geographic_zone", javaType = GeographicZone.class, column = "geographic_zone_id",
                    one = @One(select = "getGeographicZoneById"))
    })
    List<PackageArrivalPartial> getAll();

    @Insert("insert into package_arrival_partials (box_number, lot_number, expected_number, available_number, geographic_zone_id) values " +
            "(#{box_number}, #{lot_number}, #{expected_number}, #{available_number}, #{geographic_zone_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(PackageArrivalPartial packageArrivalPartial);

    @Update("update package_arrival_partials " +
            "set " +
            " box_number = #{box_number}, " +
            " lot_number = #{lot_number}," +
            " expected_number = #{expected_number}, " +
            " available_number = #{available_number}, " +
            " geographic_zone_id = #{geographic_zone_id} " +
            "where id = #{id}")
    void update(PackageArrivalPartial packageArrivalPartial);

    @Select("select * from package_arrival_partials where id = #{id}")
    @Results(value = {
            @Result(property = "geographic_zone", javaType = GeographicZone.class, column = "geographic_zone_id",
                    one = @One(select = "getGeographicZoneById"))
    })
    PackageArrivalPartial getById(@Param("id") Long id);

    @SelectProvider(type=ModelProviders.class, method="filterModal")
    @Results(value = {
            @Result(property = "geographic_zone", javaType = GeographicZone.class, column = "geographic_zone_id",
                    one = @One(select = "getGeographicZoneById"))
    })
    List<PackageArrivalPartial> filter(@Param("filter") String filter,PackageArrivalPartial packageArrivalPartial);

    @Delete("delete from package_arrival_partials where id = #{id}")
    void deleteById(@Param("id") Long id);
}
