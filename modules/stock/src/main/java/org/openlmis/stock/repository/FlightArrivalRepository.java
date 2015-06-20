package org.openlmis.stock.repository;

import org.openlmis.stock.domain.FlightArrival;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.repository.mapper.FlightArrivalMapper;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.openlmis.stock.repository.mapper.VaccineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Morley on 6/20/2015.
 */
@Component
public class FlightArrivalRepository  extends StockRepository<FlightArrival>{

    @Autowired
    private FlightArrivalMapper mapper;

    @Override
    public StockMapper getStockMapper() {
        return mapper;
    }
}
