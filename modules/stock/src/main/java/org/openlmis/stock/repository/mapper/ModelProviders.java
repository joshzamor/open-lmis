package org.openlmis.stock.repository.mapper;

/**
 * Created by Morley on 6/18/2015.
 */

import org.openlmis.stock.domain.StockModel;

import static org.apache.ibatis.jdbc.SelectBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SelectBuilder.FROM;
import static org.apache.ibatis.jdbc.SelectBuilder.SELECT;
import static org.apache.ibatis.jdbc.SelectBuilder.SQL;
import static org.apache.ibatis.jdbc.SelectBuilder.WHERE;
import static org.apache.ibatis.jdbc.SqlBuilder.*;

import java.lang.reflect.Field;
import java.util.Map;

public class ModelProviders {
    public String filterModal(Map<String, Object> params) throws Exception{
        BEGIN();
            SELECT("*");
            boolean isSpecified = false;
            for (Map.Entry<String, Object> thisEntry : params.entrySet()) {
                if (thisEntry.getKey().startsWith("filter")) {
                    String[] req = ((String) thisEntry.getValue()).split(":");
                    String request = req[0];
                    if (req[1].endsWith("eq")) {
                        request += "=";
                    } else if (req[1].endsWith("gt")) {
                        request += ">";
                    } else if (req[1].endsWith("lt")) {
                        request += "<";
                    }
                    request += "\'" +req[2]+"\'";
                    WHERE(request);
                }else if(thisEntry.getValue() instanceof StockModel){
                    if(!isSpecified) {
                        StockModel stockModel = (StockModel)thisEntry.getValue();
                        FROM(stockModel.getTableName());
                        isSpecified = !isSpecified;
                    }
                }
            }
        return SQL();
    }
    public String selectAll(Map<String, Object> params) throws Exception{
        BEGIN();
        SELECT("*");
        //FROM("vaccines");
        boolean isSpecified = false;
        for (Map.Entry<String, Object> thisEntry : params.entrySet()) {
            if(thisEntry.getValue() instanceof StockModel){
                if(!isSpecified) {
                    StockModel stockModel = (StockModel)thisEntry.getValue();
                    FROM(stockModel.getTableName());
                    isSpecified = !isSpecified;
                }
            }
        }
        return SQL();
    }
    public String selectById(Map<String, Object> params) throws Exception{
        BEGIN();
        SELECT("*");
        //  The multiple WHERE methods make them an "AND"
        boolean isSpecified = false;
        for (Map.Entry<String, Object> thisEntry : params.entrySet()) {
            if (thisEntry.getKey().startsWith("id")) {
                WHERE("id = " + thisEntry.getValue());
            }else if(thisEntry.getValue() instanceof StockModel){
                if(!isSpecified) {
                    StockModel stockModel = (StockModel)thisEntry.getValue();
                    FROM(stockModel.getTableName());
                    isSpecified = !isSpecified;
                }
            }
        }
        return SQL();
    }
    public String deleteById(Map<String, Object> params) throws Exception{
        BEGIN();
        //  The multiple WHERE methods make them an "AND"
        boolean isSpecified = false;
        for (Map.Entry<String, Object> thisEntry : params.entrySet()) {
            if (thisEntry.getKey().startsWith("id")) {
                WHERE("id = " + thisEntry.getValue());
            }else if(thisEntry.getValue() instanceof StockModel){
                if(!isSpecified) {
                    StockModel stockModel = (StockModel)thisEntry.getValue();
                    DELETE_FROM(stockModel.getTableName());
                    isSpecified = !isSpecified;
                }
            }
        }
        return SQL();
    }
    public String insert(Map<String, Object> params) throws Exception{
        BEGIN();
        //  The multiple WHERE methods make them an "AND"
        boolean isSpecified = false;
        for (Map.Entry<String, Object> thisEntry : params.entrySet()) {
            if(thisEntry.getValue() instanceof StockModel){
                if(!isSpecified) {
                    StockModel stockModel = (StockModel)thisEntry.getValue();
                    INSERT_INTO(stockModel.getTableName());
                    for(Map.Entry<String, Object> columnEntry : stockModel.getColumns().entrySet())
                    {
                        if(columnEntry.getKey().equals(thisEntry.getKey())){
                            VALUES(thisEntry.getKey(), stockModel.getColumnValue(columnEntry.getKey()));
                        }

                    }
                    isSpecified = !isSpecified;
                }
            }
        }
        return SQL();
    }
}
