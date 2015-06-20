package org.openlmis.web.controller.stock;

import org.openlmis.core.exception.DataException;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.service.PackageContentService;
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
@RequestMapping(value = "/stock/package/content")
public class PackageContentController {

    @Autowired
    private PackageContentService service;


    @RequestMapping(value="get/{id}")
    public ResponseEntity<OpenLmisResponse> get(@PathVariable Long id) {
        return OpenLmisResponse.response("packageContent", service.getById(id));
    }

    @RequestMapping(value="all")
    public ResponseEntity<OpenLmisResponse> getAll() {
        return OpenLmisResponse.response("packageContents", service.getAll());
    }

    @RequestMapping(value="save")
    public ResponseEntity<OpenLmisResponse> save(@RequestBody PackageContent packageContent) {
        try {
            service.save(packageContent);
        } catch (DataException e) {
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response("packageContent", service.getById(packageContent.getId()));
    }


}
