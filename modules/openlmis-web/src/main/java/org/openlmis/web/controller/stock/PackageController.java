package org.openlmis.web.controller.stock;

import org.openlmis.core.exception.DataException;
import org.openlmis.stock.domain.GeographicZoneArrivalPackage;
import org.openlmis.stock.domain.GeographicZonePackage;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.service.GeographicZoneArrivalPackageService;
import org.openlmis.stock.service.GeographicZonePackageService;
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
@RequestMapping(value = "/stock/package")
public class PackageController {
    @Autowired
    private GeographicZonePackageService geographicZonePackageService;


    @RequestMapping(value="get/{id}")
    public ResponseEntity<OpenLmisResponse> get(@PathVariable Long id) {
        return OpenLmisResponse.response("package", geographicZonePackageService.getById(id));
    }

    @RequestMapping(value="all")
    public ResponseEntity<OpenLmisResponse> getAll() {
        return OpenLmisResponse.response("packages", geographicZonePackageService.getAll());
    }

    @RequestMapping(value="save")
    public ResponseEntity<OpenLmisResponse> save(@RequestBody GeographicZonePackage geographicZonePackage) {
        try {
            geographicZonePackageService.save(geographicZonePackage);
        } catch (DataException e) {
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response("package", geographicZonePackageService.getById(geographicZonePackage.getId()));
    }

    @Autowired
    private GeographicZoneArrivalPackageService geographicZoneArrivalPackageService;


    @RequestMapping(value="arrival/get/{id}")
    public ResponseEntity<OpenLmisResponse> getArrival(@PathVariable Long id) {
        return OpenLmisResponse.response("arrivalPackage", geographicZoneArrivalPackageService.getById(id));
    }

    @RequestMapping(value="arrival/all")
    public ResponseEntity<OpenLmisResponse> getAllArrival() {
        return OpenLmisResponse.response("arrivalPackages", geographicZoneArrivalPackageService.getAll());
    }

    @RequestMapping(value="arrival/save")
    public ResponseEntity<OpenLmisResponse> save(@RequestBody GeographicZoneArrivalPackage geographicZoneArrivalPackage) {
        try {
            geographicZoneArrivalPackageService.save(geographicZoneArrivalPackage);
        } catch (DataException e) {
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response("arrivalPackage", geographicZoneArrivalPackageService.getById(geographicZoneArrivalPackage.getId()));
    }
}
