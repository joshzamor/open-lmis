package org.openlmis.stock.repository;

import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.repository.mapper.DiluentMapper;
import org.openlmis.stock.repository.mapper.GeographicZoneArrivalPackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Component
public class GeographicZoneArrivalPackageRepository {

    @Autowired
    private GeographicZoneArrivalPackageMapper mapper;

    public void update(GeographicZoneArrivalPackage geographicZoneArrivalPackage){
        mapper.update(geographicZoneArrivalPackage);
    }

    public void insert(GeographicZoneArrivalPackage geographicZoneArrivalPackage){
        mapper.insert(geographicZoneArrivalPackage);
    }

    public List<GeographicZoneArrivalPackage> getAll(){
        return mapper.getAll();
    }

    public GeographicZoneArrivalPackage getById(Long id) {
        return mapper.getById(id);
    }
}
