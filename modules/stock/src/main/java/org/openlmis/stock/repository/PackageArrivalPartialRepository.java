package org.openlmis.stock.repository;

import org.openlmis.stock.domain.PackageArrivalPartial;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.repository.mapper.PackageArrivalPartialMapper;
import org.openlmis.stock.repository.mapper.PackageContentMapper;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Morley on 7/9/2015.
 */

@Component
public class PackageArrivalPartialRepository extends StockRepository<PackageArrivalPartial>{

    @Autowired
    private PackageArrivalPartialMapper mapper;

    @Override
    public StockMapper getStockMapper() {
        return mapper;
    }
}
