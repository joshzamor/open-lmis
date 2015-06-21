package org.openlmis.web.controller.stock;

import org.openlmis.core.exception.DataException;
import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.ManufacturePackage;
import org.openlmis.stock.service.DiluentService;
import org.openlmis.stock.service.ManufacturePackageService;
import org.openlmis.stock.service.StockService;
import org.openlmis.web.response.OpenLmisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Morley on 6/14/2015.
 */

@Controller
@RequestMapping(value = "/stock/diluent")
public class DiluentController  extends StockBaseController<Diluent>{

    @Autowired
    private DiluentService service;

    @Override
    public StockService getService() {
        return service;
    }

}
