package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.ManufacturePackage;
import org.openlmis.stock.domain.Manufacturer;
import org.openlmis.stock.domain.Vaccine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */
@Repository
public interface ManufacturePackageMapper extends HasVaccineMapper,StockMapper<ManufacturePackage>{

    @Select("select * from manufacture_package")
    @Results(value = {
            @Result(property = "vaccine", javaType = Vaccine.class, column = "vaccine_id",
                    one = @One(select = "getVaccineById"))
    })
    List<ManufacturePackage> getAll();

    @Insert("insert into manufacture_package (sscc, manufacture_date, expire_date, lot_number, number_of_doses,delivery_status, vaccine_id) values " +
            "(#{sscc}, #{manufacture_date}, #{expire_date},#{lot_number},#{number_of_doses},#{delivery_status},#{vaccine_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(ManufacturePackage manufacturePackage);

    @Update("update manufacture_package " +
            "set " +
            " sscc = #{sscc}, " +
            " manufacture_date = #{manufacture_date}," +
            " expire_date = #{expire_date}, " +
            " lot_number = #{lot_number}, " +
            " lot_number = #{lot_number}, " +
            " number_of_doses = #{number_of_doses}, " +
            " vaccine_id = #{vaccine_id}, " +
            "where id = #{id}")
    void update(ManufacturePackage manufacturePackage);

    @Select("select * from manufacture_package where id = #{id}")
    @Results(value = {
            @Result(property = "vaccine", javaType = Vaccine.class, column = "vaccine_id",
                    one = @One(select = "getVaccineById"))
    })
    ManufacturePackage getById(@Param("id") Long id);

}
