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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ModelProviders {
    public String filterModal(Map<String, Object> params) throws Exception{
        BEGIN();
            SELECT("*");
            boolean isSpecified = false;
            for (Map.Entry<String, Object> thisEntry : params.entrySet()) {
                if (thisEntry.getKey().startsWith("filter")) {
                    String[] filters = ((String) thisEntry.getValue()).split(";");
                    for(String filter:filters){
                        /*String[] req = filter.split(":");
                        String request = req[0];
                        if (req[1].endsWith("eq")) {
                            request += "=";
                            request += "\'" +req[2]+"\'";
                        } else if (req[1].endsWith("gt")) {
                            request += ">";
                            request += req[2];
                        } else if (req[1].endsWith("lt")) {
                            request += "<";
                            request += req[2];
                        }*/

                        WHERE(evaluateFilter(filter));
                    }

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
    /*public String selectAll(Map<String, Object> params) throws Exception{
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
    }*/
    public String evaluateFilter(String filter) throws ParseException {
        String[] req = filter.split(":");
        if (req[2].contains("(")) {
            if (req[0].equals("lot_number")) {
                req[2] = getValue("(10)",req[2]);
            }else if(req[0].equals("gtin")){
                req[2] = getValue("(01)",req[2]);
            }else if(req[0].equals("expire_date")){
                req[2] = "20" + getValue("(17)",req[2]);
                DateFormat format = new SimpleDateFormat("yyyyMMdd");
                Date date = format.parse(req[2]);
                format = new SimpleDateFormat("yyyy-MM-dd");
                req[2] = format.format(date);

            }
        }
        String request = req[0];
        if (req[1].endsWith("eq")) {
            request += "=";
            request += "\'" + req[2] + "\'";
        } else if (req[1].endsWith("gt")) {
            request += ">";
            request += req[2];
        } else if (req[1].endsWith("lt")) {
            request += "<";
            request += req[2];
        }
        return request;
    }
    private String getValue(String index,String input){
        input = input.substring(input.indexOf(index) + 4);
        if(input.contains("(")){
            input = input.substring(0,input.indexOf("("));
        }
        return input;
    }
}
