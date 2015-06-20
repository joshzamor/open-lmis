package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.Vaccine;
import org.springframework.stereotype.Repository;
import org.openlmis.stock.domain.Diluent;
import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Repository
public interface DiluentMapper  extends HasVaccineMapper,StockMapper<Diluent>{

    @Insert("insert into vaccine_diluents (vaccine_unit, diluent_unit, vaccine_id, diluent_id) values " +
            "(#{vaccine_unit}, #{diluent_unit}, #{vaccine_id}, #{diluent_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(Diluent diluent);

    @Update("update vaccine_diluents " +
            "set " +
            " vaccine_unit = #{vaccine_unit}, " +
            " diluent_unit = #{diluent_unit}," +
            " vaccine_id = #{vaccine_id}, " +
            " diluent_id = #{diluent_id} " +
            "where id = #{id}")
    void update(Diluent diluent);

    @Select("select * from vaccine_diluents")
    List<Diluent> getAll();

    @Select("select * from vaccine_diluents where id = #{id}")
    Diluent getById(@Param("id") Long id);

    @Select("delete from vaccine_diluents where id = #{id}")
    void deleteById(@Param("id") Long id);

    @SelectProvider(type=ModelProviders.class, method="filterModal")
    List<Diluent> filter(@Param("filter") String filter,Diluent stockModel);
}
