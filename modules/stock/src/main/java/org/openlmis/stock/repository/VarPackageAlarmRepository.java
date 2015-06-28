package org.openlmis.stock.repository;

import org.openlmis.stock.domain.VarDocument;
import org.openlmis.stock.domain.VarPackageAlarm;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.openlmis.stock.repository.mapper.VarDocumentMapper;
import org.openlmis.stock.repository.mapper.VarPackageAlarmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Morley on 6/28/2015.
 */
@Component
public class VarPackageAlarmRepository  extends StockRepository<VarPackageAlarm>{

    @Autowired
    private VarPackageAlarmMapper mapper;

    @Override
    public StockMapper getStockMapper() {
        return mapper;
    }
}
