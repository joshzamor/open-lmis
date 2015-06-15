package org.openlmis.stock.service;

import org.openlmis.stock.domain.GeographicZonePackage;
import org.openlmis.stock.domain.GeographicZoneStock;
import org.openlmis.stock.repository.GeographicZonePackageRepository;
import org.openlmis.stock.repository.GeographicZoneStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Service
public class GeographicZoneStockService {

    @Autowired
    private GeographicZoneStockRepository repository;

    public List<GeographicZoneStock> getAll(){
        return repository.getAll();
    }

    public void save(GeographicZoneStock geographicZoneStock){
        if(geographicZoneStock.getId() == null){
            repository.insert(geographicZoneStock);
        }else {
            repository.update(geographicZoneStock);
        }
    }

    public GeographicZoneStock getById(Long id){
        return repository.getById(id);
    }
}
