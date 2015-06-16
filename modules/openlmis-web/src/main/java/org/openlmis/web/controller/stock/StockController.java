package org.openlmis.web.controller.stock;

import org.openlmis.core.exception.DataException;
import org.openlmis.stock.domain.GeographicZoneStock;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.service.GeographicZoneStockService;
import org.openlmis.stock.service.PackageContentService;
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
public class StockController {

    @Autowired
    private GeographicZoneStockService service;


    @RequestMapping(value="get/{id}")
    public ResponseEntity<OpenLmisResponse> get(@PathVariable Long id) {
        return OpenLmisResponse.response("stock", service.getById(id));
    }

    @RequestMapping(value="")
    public ResponseEntity<OpenLmisResponse> getAll() {
        return OpenLmisResponse.response("stocks", service.getAll());
    }

    @RequestMapping(value="save")
    public ResponseEntity<OpenLmisResponse> save(@RequestBody GeographicZoneStock geographicZoneStock) {
        try {
            service.save(geographicZoneStock);
        } catch (DataException e) {
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response("stock", service.getById(geographicZoneStock.getId()));
    }
}
