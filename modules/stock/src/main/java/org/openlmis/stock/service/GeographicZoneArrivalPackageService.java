package org.openlmis.stock.service;

import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.repository.GeographicZoneArrivalPackageRepository;
import org.openlmis.stock.repository.PackageContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Service
public class GeographicZoneArrivalPackageService {

    @Autowired
    private GeographicZoneArrivalPackageRepository repository;

    public List<GeographicZoneArrivalPackage> getAll(){
        return repository.getAll();
    }

    public void save(GeographicZoneArrivalPackage geographicZoneArrivalPackage){
        if(geographicZoneArrivalPackage.getId() == null){
            repository.insert(geographicZoneArrivalPackage);
        }else {
            repository.update(geographicZoneArrivalPackage);
        }
    }

    public GeographicZoneArrivalPackage getById(Long id){
        return repository.getById(id);
    }
}
