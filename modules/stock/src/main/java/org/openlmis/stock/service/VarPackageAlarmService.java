package org.openlmis.stock.service;

import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.domain.VarPackageAlarm;
import org.openlmis.stock.repository.StockRepository;
import org.openlmis.stock.repository.VaccineRepository;
import org.openlmis.stock.repository.VarPackageAlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Morley on 6/28/2015.
 */
@Service
public class VarPackageAlarmService  extends StockService<VarPackageAlarm>{

    @Autowired
    private VarPackageAlarmRepository repository;

    @Override
    public StockRepository getRepository() {
        return repository;
    }
}
