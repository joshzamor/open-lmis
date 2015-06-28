package org.openlmis.web.controller.stock;

import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.VarPackageAlarm;
import org.openlmis.stock.service.DiluentService;
import org.openlmis.stock.service.StockService;
import org.openlmis.stock.service.VarPackageAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Morley on 6/28/2015.
 */

@Controller
@RequestMapping(value = "/stock/var/package/alarm")
public class VarPackageAlarmController  extends StockBaseController<VarPackageAlarm>{
    @Autowired
    private VarPackageAlarmService service;

    @Override
    public StockService getService() {
        return service;
    }
}
