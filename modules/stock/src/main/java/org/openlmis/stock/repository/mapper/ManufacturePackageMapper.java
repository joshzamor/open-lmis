package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.ManufacturePackage;
import org.openlmis.stock.domain.Vaccine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */
@Repository
public interface ManufacturePackageMapper {

    @Select("select * from manufacture_package")
    List<ManufacturePackage> getAll();

    @Insert("insert into manufacture_package (level, package_id, number_of_boxes, lot_number, delivery_status, vaccine_id) values " +
            "(#{level}, #{package_id}, #{number_of_boxes},#{lot_number},#{delivery_status},#{vaccine_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(ManufacturePackage manufacturePackage);

    @Update("update manufacture_package " +
            "set " +
            " name = #{level}, " +
            " package_id = #{package_id}," +
            " number_of_boxes = #{number_of_boxes}, " +
            " lot_number = #{lot_number}, " +
            " delivery_status = #{delivery_status}, " +
            " vaccine_id = #{vaccine_id}, " +
            "where id = #{id}")
    void update(ManufacturePackage manufacturePackage);

    @Select("select * from manufacture_package where id = #{id}")
    ManufacturePackage getById(@Param("id") Long id);
}
