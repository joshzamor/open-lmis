package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Vaccine;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */
@Repository
public interface VaccineMapper {

    @Select("select * from vaccines")
    List<Vaccine> getAll();

    @Insert("insert into vaccines (name, packaging, gtin, doses_per_vial, expire_warning_period, type,wastage,schedule,status,geographic_zone_id,manufacture_id) values " +
            "(#{name}, #{packaging}, #{gtin}, #{doses_per_vial}, #{expire_warning_period}, #{type}, #{wastage}, #{schedule}, #{status}, #{geographic_zone_id}, #{manufacture_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(Vaccine vaccine);

    @Update("update vaccines " +
            "set " +
            " name = #{name}, " +
            " packaging = #{packaging}," +
            " gtin = #{gtin}, " +
            " doses_per_vial = #{doses_per_vial}, " +
            " expire_warning_period = #{expire_warning_period} " +
            " type = #{type} " +
            " wastage = #{wastage} " +
            " schedule = #{schedule} " +
            " status = #{status} " +
            " geographic_zone_id = #{geographic_zone_id} " +
            " manufacture_id = #{manufacture_id} " +
            "where id = #{id}")
    void update(Vaccine vaccine);

    @Select("select * from vaccines where id = #{id}")
    Vaccine getById(@Param("id") Long id);
}
