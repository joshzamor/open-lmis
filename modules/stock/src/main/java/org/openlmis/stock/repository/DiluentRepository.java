package org.openlmis.stock.repository;

import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.repository.mapper.DiluentMapper;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.openlmis.stock.repository.mapper.VaccineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Component
public class DiluentRepository extends StockRepository<Diluent>{

    @Autowired
    private DiluentMapper mapper;

    @Override
    public StockMapper getStockMapper() {
        return mapper;
    }
}
