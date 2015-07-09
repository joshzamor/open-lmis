package org.openlmis.stock.service;

import org.openlmis.stock.domain.VarDetails;
import org.openlmis.stock.repository.VarDetailRepository;
import org.openlmis.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Morley on 6/20/2015.
 */

@Service
public class VarDetailsService extends StockService<VarDetails>{

    @Autowired
    private VarDetailRepository repository;

    @Override
    public StockRepository getRepository() {
        return repository;
    }
}
