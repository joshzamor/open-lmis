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

    @Insert("insert into manufacture_package (shipment_id, manufacture_date, expire_date, lot_number, number_of_doses,delivery_status,purchasing_order_number, vaccine_id) values " +
            "(#{shipment_id}, #{manufacture_date}, #{expire_date},#{lot_number},#{number_of_doses},#{delivery_status},#{purchasing_order_number},#{vaccine_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(ManufacturePackage manufacturePackage);

    @Update("update manufacture_package " +
            "set " +
            " shipment_id = #{shipment_id}, " +
            " manufacture_date = #{manufacture_date}," +
            " expire_date = #{expire_date}, " +
            " lot_number = #{lot_number}, " +
            " delivery_status = #{delivery_status}, " +
            " number_of_doses = #{number_of_doses}, " +
            " purchasing_order_number = #{purchasing_order_number}, " +
            " vaccine_id = #{vaccine_id} " +
            "where id = #{id}")
    void update(ManufacturePackage manufacturePackage);

    @Select("select * from manufacture_package where id = #{id}")
    @Results(value = {
            @Result(property = "vaccine", javaType = Vaccine.class, column = "vaccine_id",
                    one = @One(select = "getVaccineById"))
    })
    ManufacturePackage getById(@Param("id") Long id);

    @SelectProvider(type=ModelProviders.class, method="filterModal")
    List<ManufacturePackage> filter(@Param("filter") String filter,ManufacturePackage manufacturePackage);

    @Delete("delete from manufacture_package where id = #{id}")
    void deleteById(@Param("id") Long id,ManufacturePackage manufacturePackage);
}
