package org.openlmis.stock.service;

import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.domain.GeographicZonePackage;
import org.openlmis.stock.repository.GeographicZoneArrivalPackageRepository;
import org.openlmis.stock.repository.GeographicZonePackageRepository;
import org.openlmis.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Service
public class GeographicZonePackageService extends StockService<GeographicZonePackage>{

    @Autowired
    private GeographicZonePackageRepository repository;

    public List<GeographicZonePackage> getAll(){
        return repository.getAll();
    }

    public void save(GeographicZonePackage geographicZonePackage){
        if(geographicZonePackage.getId() == null){
            repository.insert(geographicZonePackage);
        }else {
            repository.update(geographicZonePackage);
        }
    }

    public GeographicZonePackage getById(Long id){
        return repository.getById(id);
    }

    @Override
    public StockRepository getRepository() {
        return repository;
    }
}
