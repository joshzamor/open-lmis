package org.openlmis.stock.service;

import org.openlmis.stock.domain.VaccinePackaging;
import org.openlmis.stock.repository.StockRepository;
import org.openlmis.stock.repository.VaccinePackagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Morley on 6/20/2015.
 */

@Service
public class VaccinePackagingService extends StockService<VaccinePackaging>{

    @Autowired
    private VaccinePackagingRepository repository;

    @Override
    public StockRepository getRepository() {
        return repository;
    }
}
