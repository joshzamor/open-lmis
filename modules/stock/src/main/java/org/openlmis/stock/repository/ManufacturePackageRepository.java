package org.openlmis.stock.repository;

import org.openlmis.stock.domain.GeographicZoneStock;
import org.openlmis.stock.domain.ManufacturePackage;
import org.openlmis.stock.repository.mapper.GeographicZoneStockMapper;
import org.openlmis.stock.repository.mapper.ManufacturePackageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Component
public class ManufacturePackageRepository {

    @Autowired
    private ManufacturePackageMapper mapper;

    public void update(ManufacturePackage manufacturePackage){
        mapper.update(manufacturePackage);
    }

    public void insert(ManufacturePackage manufacturePackage){
        mapper.insert(manufacturePackage);
    }

    public List<ManufacturePackage> getAll(){
        return mapper.getAll();
    }

    public ManufacturePackage getById(Long id) {
        return mapper.getById(id);
    }
}
