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

    /*@RequestMapping(value="{id}", method = GET)
    public ResponseEntity<OpenLmisResponse> get(@PathVariable Long id) {
        return OpenLmisResponse.response("diluent", service.getById(id));
    }

    @RequestMapping(value="")
    public ResponseEntity<OpenLmisResponse> getAll() {
        return OpenLmisResponse.response("diluents", service.getAll());
    }

    @RequestMapping(value="save")
    public ResponseEntity<OpenLmisResponse> save(@RequestBody Diluent diluent) {
        try {
            service.save(diluent);
        } catch (DataException e) {
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response("diluent", service.getById(diluent.getId()));
    }*/

    @Override
    public StockService getService() {
        return service;
    }

}