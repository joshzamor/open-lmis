package org.openlmis.web.controller.stock;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.openlmis.core.exception.DataException;
import org.openlmis.stock.domain.PackageContent;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.service.PackageContentService;
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
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

/**
 * Created by Morley on 6/14/2015.
 */
@Controller
@RequestMapping(value = "/stock/package/content")
public class PackageContentController extends StockBaseController<PackageContent>{

    @Autowired
    private PackageContentService service;

    @Override
    public StockService getService() {
        return service;
    }
}
