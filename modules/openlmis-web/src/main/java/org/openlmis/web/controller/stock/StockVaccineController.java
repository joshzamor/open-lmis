package org.openlmis.web.controller.stock;

/**
 * Created by Morley on 6/14/2015.
 */

import org.openlmis.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.service.VaccineService;

@Controller
@RequestMapping(value = "/stock/vaccine")
public class StockVaccineController extends StockBaseController<Vaccine> {

    @Autowired
    private VaccineService service;

    @Override
    public StockService getService() {
        return service;
    }
}
