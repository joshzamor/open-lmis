package org.openlmis.stock.repository;

import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.GeographicZonePackage;
import org.openlmis.stock.repository.mapper.DiluentMapper;
import org.openlmis.stock.repository.mapper.GeographicZonePackageMapper;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Component
public class GeographicZonePackageRepository extends StockRepository<GeographicZonePackage>{

    @Autowired
    private GeographicZonePackageMapper mapper;

    @Override
    public StockMapper getStockMapper() {
        return mapper;
    }
}
