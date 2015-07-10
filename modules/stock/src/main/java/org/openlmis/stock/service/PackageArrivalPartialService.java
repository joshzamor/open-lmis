package org.openlmis.stock.service;

import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.domain.PackageArrivalPartial;
import org.openlmis.stock.repository.GeographicZoneArrivalPackageRepository;
import org.openlmis.stock.repository.PackageArrivalPartialRepository;
import org.openlmis.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Morley on 7/9/2015.
 */
@Service
public class PackageArrivalPartialService  extends StockService<PackageArrivalPartial>{

    @Autowired
    private PackageArrivalPartialRepository repository;

    @Override
    public StockRepository getRepository() {
        return repository;
    }
}
