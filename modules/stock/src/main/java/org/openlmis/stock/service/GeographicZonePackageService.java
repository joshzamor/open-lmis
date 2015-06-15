package org.openlmis.stock.service;

import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.domain.GeographicZonePackage;
import org.openlmis.stock.repository.GeographicZoneArrivalPackageRepository;
import org.openlmis.stock.repository.GeographicZonePackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Service
public class GeographicZonePackageService {

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
}
