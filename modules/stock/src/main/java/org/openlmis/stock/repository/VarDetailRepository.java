package org.openlmis.stock.repository;

import org.openlmis.stock.domain.VarDetails;
import org.openlmis.stock.repository.mapper.VarDetailMapper;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Morley on 6/20/2015.
 */
@Component
public class VarDetailRepository extends StockRepository<VarDetails>{

    @Autowired
    private VarDetailMapper mapper;

    @Override
    public StockMapper getStockMapper() {
        return mapper;
    }
}
