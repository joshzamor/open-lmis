package org.openlmis.stock.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.openlmis.stock.domain.FlightArrival;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.domain.VarDocument;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morley on 6/28/2015.
 */

@Repository
public interface VarDocumentMapper extends HasFlightArrival,StockMapper<VarDocument>{

    @Insert("insert into var_documents (name, comments, var_details_id) values " +
            "(#{name}, #{comments}, #{var_details_id})")
    @Options(flushCache = true, useGeneratedKeys = true)
    Integer insert(VarDocument VarDocument);

    @Update("update var_documents " +
            "set " +
            " name = #{name}, " +
            " comments = #{comments}, " +
            " var_details_id = #{var_details_id} " +
            "where id = #{id}")
    void update(VarDocument varDocument);

    @Select("select * from var_documents")
    @Results(value = {
            @Result(property = "var_details", javaType = Vaccine.class, column = "var_details_id",
                    one = @One(select = "getFlightArrivalById"))
    })
    List<VarDocument> getAll();

    @Select("select * from var_documents where id = #{id}")
    @Results(value = {
            @Result(property = "var_details", javaType = Vaccine.class, column = "var_details_id",
                    one = @One(select = "getFlightArrivalById"))
    })
    VarDocument getById(@Param("id") Long id);

    @Delete("delete from var_documents where id = #{id}")
    void deleteById(@Param("id") Long id);
}
