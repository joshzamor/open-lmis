package org.openlmis.web.controller.stock;

import org.openlmis.core.exception.DataException;
import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.service.GeographicZoneArrivalPackageService;
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
 * Created by Morley on 6/19/2015.
 */

@Controller
@RequestMapping(value = "/stock/package/arrival")
public class PackageArrivalController extends StockBaseController<GeographicZoneArrivalPackage>{

    @Autowired
    private GeographicZoneArrivalPackageService service;


    /*@RequestMapping(value="{id}")
    public ResponseEntity<OpenLmisResponse> getArrival(@PathVariable Long id) {
        return OpenLmisResponse.response("arrivalPackage", service.getById(id));
    }

    @RequestMapping(value="")
    public ResponseEntity<OpenLmisResponse> getAllArrival() {
        return OpenLmisResponse.response("arrivalPackages", service.getAll());
    }

    @RequestMapping(value="save")
    public ResponseEntity<OpenLmisResponse> save(@RequestBody GeographicZoneArrivalPackage geographicZoneArrivalPackage) {
        try {
            service.save(geographicZoneArrivalPackage);
        } catch (DataException e) {
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response("arrivalPackage", service.getById(geographicZoneArrivalPackage.getId()));
    }*/

    @Override
    public StockService getService() {
        return service;
    }
}
