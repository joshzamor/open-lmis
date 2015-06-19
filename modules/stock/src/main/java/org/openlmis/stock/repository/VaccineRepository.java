package org.openlmis.stock.repository;

/**
 * Created by Morley on 6/14/2015.
 */
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.openlmis.stock.repository.mapper.VaccineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VaccineRepository extends StockRepository<Vaccine>{

    @Autowired
    private VaccineMapper mapper;

    @Override
    public StockMapper getStockMapper() {
        return mapper;
    }
}