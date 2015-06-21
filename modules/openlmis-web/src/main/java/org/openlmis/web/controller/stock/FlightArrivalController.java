package org.openlmis.web.controller.stock;

import org.openlmis.stock.domain.FlightArrival;
import org.openlmis.stock.domain.VaccinePackaging;
import org.openlmis.stock.service.FlightArrivalService;
import org.openlmis.stock.service.StockService;
import org.openlmis.stock.service.VaccinePackagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Morley on 6/20/2015.
 */
@Controller
@RequestMapping(value = "/stock/flight/arrival")
public class FlightArrivalController  extends StockBaseController<FlightArrival> {
    @Autowired
    private FlightArrivalService service;

    @Override
    public StockService getService() {
        return service;
    }
}
