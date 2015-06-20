package org.openlmis.stock.service;

import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.repository.FlightArrivalRepository;
import org.openlmis.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Morley on 6/20/2015.
 */

@Service
public class FlightArrivalService extends StockService<Vaccine>{

    @Autowired
    private FlightArrivalRepository repository;

    @Override
    public StockRepository getRepository() {
        return repository;
    }
}
