package org.openlmis.web.controller.stock;

/**
 * Created by Morley on 6/14/2015.
 */

import org.openlmis.core.exception.DataException;
import org.openlmis.stock.service.StockService;
import org.openlmis.web.controller.BaseController;
import org.openlmis.web.response.OpenLmisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.service.VaccineService;
import org.springframework.web.bind.annotation.RequestParam;

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
