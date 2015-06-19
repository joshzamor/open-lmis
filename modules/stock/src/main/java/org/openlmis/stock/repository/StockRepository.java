package org.openlmis.stock.repository;

import org.openlmis.stock.domain.StockModel;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Morley on 6/18/2015.
 */

@Component
public abstract class StockRepository<T extends StockModel> {
    public void update(T model){
        getStockMapper().update(model);
    }

    public void insert(T model){
        getStockMapper().insert(model);
    }

    public List<T> getAll() throws Exception{
        return getStockMapper().getAll(getParameter());
    }

    public T getById(Long id) throws Exception {
        return (T) getStockMapper().getById(id, getParameter());
    }
    public void deleteById(Long id) throws Exception {
        getStockMapper().deleteById(id, getParameter());
    }

    public List<T> filter(String filter) throws Exception {

        return getStockMapper().filter(filter, getParameter());
    }
    public abstract StockMapper getStockMapper();

    private T getParameter() throws Exception{
        ParameterizedType pt
                = (ParameterizedType) getClass().getGenericSuperclass();
        // You may need this split or not, use logging to check
        String parameterClassName
                = pt.getActualTypeArguments()[0].toString().split("\\s")[1];
        // Instantiate the Parameter and initialize it.
        T parameter = (T) Class.forName(parameterClassName).newInstance();
        return parameter;
    }
}
