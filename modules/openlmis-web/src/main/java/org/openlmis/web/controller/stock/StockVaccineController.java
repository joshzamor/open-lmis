package org.openlmis.web.controller.stock;

/**
 * Created by Morley on 6/14/2015.
 */

import org.openlmis.core.exception.DataException;
import org.openlmis.web.controller.BaseController;
import org.openlmis.web.response.OpenLmisResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.service.VaccineService;

@Controller
@RequestMapping(value = "/stock/vaccine/")
public class StockVaccineController extends BaseController {

    @Autowired
    private VaccineService service;


    @RequestMapping(value="get/{id}")
    public ResponseEntity<OpenLmisResponse> get(@PathVariable Long id) {
        return OpenLmisResponse.response("vaccine", service.getById(id));
    }

    @RequestMapping(value="all")
    public ResponseEntity<OpenLmisResponse> getAll() {
        return OpenLmisResponse.response("vaccines", service.getAll());
    }

    @RequestMapping(value="save")
    public ResponseEntity<OpenLmisResponse> save(@RequestBody Vaccine vaccine) {
        try {
            service.save(vaccine);
        } catch (DataException e) {
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response("vaccine", service.getById(vaccine.getId()));
    }


}
