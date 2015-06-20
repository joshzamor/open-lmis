package org.openlmis.stock.service;

import org.openlmis.stock.domain.StockModel;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.repository.StockRepository;

import java.util.List;

/**
 * Created by Morley on 6/17/2015.
 */
public abstract class StockService<T extends StockModel> {
    public List<T> getAll() throws Exception{
        return getRepository().getAll();
    }

    public void save(T model){
        if(model.getId() == null){
            getRepository().insert(model);
        }else {
            getRepository().update(model);
        }
    }

    public T getById(Long id)throws Exception {
        return (T) getRepository().getById(id);
    }

    public void deleteById(Long id) throws Exception {getRepository().deleteById(id);}

    public List<T> filter(String filter) throws Exception { return getRepository().filter(filter);}

    public abstract StockRepository getRepository();
}
