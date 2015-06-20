package org.openlmis.web.controller.stock;

import org.openlmis.core.exception.DataException;
import org.openlmis.stock.domain.ManufacturePackage;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.service.ManufacturePackageService;
import org.openlmis.stock.service.StockService;
import org.openlmis.stock.service.VaccineService;
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
@RequestMapping(value = "/stock/manufacture/package")
public class ManufacturePackageController extends StockBaseController<ManufacturePackage>{

    @Autowired
    private ManufacturePackageService service;

    /*@RequestMapping(value="get/{id}")
    public ResponseEntity<OpenLmisResponse> get(@PathVariable Long id) {
        return OpenLmisResponse.response("manufacturePackage", service.getById(id));
    }

    @RequestMapping(value="all")
    public ResponseEntity<OpenLmisResponse> getAll() {
        return OpenLmisResponse.response("manufacturePackages", service.getAll());
    }

    @RequestMapping(value="save")
    public ResponseEntity<OpenLmisResponse> save(@RequestBody ManufacturePackage manufacturePackage) {
        try {
            service.save(manufacturePackage);
        } catch (DataException e) {
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response("manufacturePackage", service.getById(manufacturePackage.getId()));
    }*/

    @Override
    public StockService getService() {
        return service;
    }


}
