package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.domain.Vaccine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Repository
public interface PackageContentMapper {

    @Select("select * from package_contents")
    List<PackageContent> getAll();

    @Insert("insert into package_contents (level, package_id, number_of_boxes, lot_number, delivery_status, vaccine_id) values " +
            "(#{level}, #{package_id}, #{number_of_boxes},#{lot_number},#{delivery_status},#{vaccine_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(PackageContent packageContent);

    @Update("update package_contents " +
            "set " +
            " name = #{level}, " +
            " package_id = #{package_id}," +
            " number_of_boxes = #{number_of_boxes}, " +
            " lot_number = #{lot_number}, " +
            " delivery_status = #{delivery_status}, " +
            " vaccine_id = #{vaccine_id}, " +
            "where id = #{id}")
    void update(PackageContent packageContent);

    @Select("select * from package_contents where id = #{id}")
    PackageContent getById(@Param("id") Long id);
}
