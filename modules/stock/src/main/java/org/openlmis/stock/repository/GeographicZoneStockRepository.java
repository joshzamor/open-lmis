package org.openlmis.stock.repository;

import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.GeographicZoneStock;
import org.openlmis.stock.repository.mapper.DiluentMapper;
import org.openlmis.stock.repository.mapper.GeographicZoneStockMapper;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Morley on 6/14/2015.
 */

@Component
public class GeographicZoneStockRepository extends StockRepository<GeographicZoneStock>{

    @Autowired
    private GeographicZoneStockMapper mapper;

    public void update(GeographicZoneStock geographicZoneStock){
        mapper.update(geographicZoneStock);
    }

    public void insert(GeographicZoneStock geographicZoneStock){
        mapper.insert(geographicZoneStock);
    }

    public List<GeographicZoneStock> getAll(){
        return mapper.getAll();
    }

    public GeographicZoneStock getById(Long id) {
        return mapper.getById(id);
    }

    @Override
    public StockMapper getStockMapper() {
        return mapper;
    }
}
