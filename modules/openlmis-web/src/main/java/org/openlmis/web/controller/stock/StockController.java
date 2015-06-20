package org.openlmis.web.controller.stock;

import org.openlmis.core.exception.DataException;
import org.openlmis.stock.domain.GeographicZoneStock;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.service.GeographicZoneStockService;
import org.openlmis.stock.service.PackageContentService;
import org.openlmis.stock.service.StockService;
import org.openlmis.web.response.OpenLmisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Morley on 6/14/2015.
 */

@Controller
@RequestMapping(value = "/stock")
public class StockController extends StockBaseController<GeographicZoneStock>{

    @Autowired
    private GeographicZoneStockService service;

    @Override
    public StockService getService() {
        return service;
    }
}
