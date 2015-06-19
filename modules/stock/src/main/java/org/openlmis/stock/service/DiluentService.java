package org.openlmis.stock.service;

import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.ManufacturePackage;
import org.openlmis.stock.repository.DiluentRepository;
import org.openlmis.stock.repository.ManufacturePackageRepository;
import org.openlmis.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Service
public class DiluentService extends StockService<Diluent>{
    @Autowired
    private DiluentRepository repository;

    public List<Diluent> getAll(){
        return repository.getAll();
    }

    public void save(Diluent diluent){
        if(diluent.getId() == null){
            repository.insert(diluent);
        }else {
            repository.update(diluent);
        }
    }

    public Diluent getById(Long id){
        return repository.getById(id);
    }

    @Override
    public StockRepository getRepository() {
        return repository;
    }
}
