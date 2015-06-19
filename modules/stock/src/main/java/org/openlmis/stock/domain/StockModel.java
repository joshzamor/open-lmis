package org.openlmis.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openlmis.core.domain.BaseModel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Morley on 6/18/2015.
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class StockModel extends BaseModel {
    public StockModel(Long id) {
        super(id);
    }

    public Map<String,Object> getColumns(){
        Map<String,Object> map = new HashMap<String,Object>();
        for(Field field:getClass().getFields())
        {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    public String getColumnValue(String columnName){
        try{
            Field field = getClass().getField(columnName);
            field.setAccessible(true);
            return field.get(this).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
    public abstract String getTableName();
}
