package org.openlmis.web.controller.stock;

import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.domain.VaccinePackaging;
import org.openlmis.stock.service.StockService;
import org.openlmis.stock.service.VaccinePackagingService;
import org.openlmis.stock.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Morley on 6/20/2015.
 */

@Controller
@RequestMapping(value = "/stock/vaccine/packaging")
public class VaccinePackagingController extends StockBaseController<VaccinePackaging> {
    @Autowired
    private VaccinePackagingService service;

    @Override
    public StockService getService() {
        return service;
    }
}
