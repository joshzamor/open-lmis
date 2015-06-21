package org.openlmis.stock.service;

/**
 * Created by Morley on 6/14/2015.
 */

import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.repository.StockRepository;
import org.openlmis.stock.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineService extends StockService<Vaccine>{

    @Autowired
    private VaccineRepository repository;

    @Override
    public StockRepository getRepository() {
        return repository;
    }
}