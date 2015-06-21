package org.openlmis.stock.service;

import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.repository.GeographicZoneArrivalPackageRepository;
import org.openlmis.stock.repository.PackageContentRepository;
import org.openlmis.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Service
public class GeographicZoneArrivalPackageService extends StockService<GeographicZoneArrivalPackage>{

    @Autowired
    private GeographicZoneArrivalPackageRepository repository;

    @Override
    public StockRepository getRepository() {
        return repository;
    }
}
