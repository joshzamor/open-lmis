package org.openlmis.stock.repository;

import org.openlmis.stock.domain.GeographicZoneStock;
import org.openlmis.stock.domain.ManufacturePackage;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.repository.mapper.GeographicZoneStockMapper;
import org.openlmis.stock.repository.mapper.ManufacturePackageMapper;
import org.openlmis.stock.repository.mapper.PackageContentMapper;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Component
public class PackageContentRepository extends StockRepository<PackageContent>{

    @Autowired
    private PackageContentMapper mapper;

    public void update(PackageContent packageContent){
        mapper.update(packageContent);
    }

    public void insert(PackageContent packageContent){
        mapper.insert(packageContent);
    }

    public List<PackageContent> getAll(){
        return mapper.getAll();
    }

    public PackageContent getById(Long id) {
        return mapper.getById(id);
    }

    public void deleteById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public StockMapper getStockMapper() {
        return mapper;
    }
}
