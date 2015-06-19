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


    @RequestMapping(value="get/{id}")
    @Results(value = {
            @Result(property = "vaccine", javaType = Vaccine.class, column = "vaccine_id",
                    one = @One(select = "getVaccineById"))
    })
    public ResponseEntity<OpenLmisResponse> get(@PathVariable Long id) {
        return OpenLmisResponse.response("packageContent", service.getById(id));
    }

    @RequestMapping(value="all")
    @Results(value = {
            @Result(property = "vaccine", javaType = Vaccine.class, column = "vaccine_id",
                    one = @One(select = "getVaccineById"))
    })
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

    @Override
    public StockService getService() {
        return service;
    }

    @RequestMapping(value="{id}", method = DELETE)
    public ResponseEntity<OpenLmisResponse> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (DataException e) {
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response("status", "success");
    }
}
