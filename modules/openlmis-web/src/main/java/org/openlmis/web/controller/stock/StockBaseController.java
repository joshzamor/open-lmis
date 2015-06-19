package org.openlmis.web.controller.stock;

import org.openlmis.core.exception.DataException;
import org.openlmis.stock.domain.StockModel;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.service.StockService;
import org.openlmis.web.response.OpenLmisResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.ParameterizedType;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Morley on 6/19/2015.
 */
public abstract class StockBaseController<T extends StockModel> {

    @RequestMapping(value="", method = GET)
    public ResponseEntity<OpenLmisResponse> get() throws Exception {
        return OpenLmisResponse.response(getTableName(), getService().getAll());
    }
    @RequestMapping(value="", method = GET, params="filter")
    public ResponseEntity<OpenLmisResponse> filter(@RequestParam String filter) {
        try {
            return OpenLmisResponse.response(getTableName(), getService().filter(filter));
        }catch(DataException e){
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }catch(Exception e){
            return OpenLmisResponse.response("error", e.getMessage());
        }
    }
    @RequestMapping(value="{id}", method = GET)
    public ResponseEntity<OpenLmisResponse> get(@PathVariable Long id)  throws Exception{
        return OpenLmisResponse.response(getTableNameSingle(), getService().getById(id));
    }

    @RequestMapping(value="{id}", method = DELETE)
    public ResponseEntity<OpenLmisResponse> delete(@PathVariable Long id)  throws Exception  {
        try {
            getService().deleteById(id);
        } catch (DataException e) {

            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response("status", "success");
    }

    @RequestMapping(value="",method = POST)
    public ResponseEntity<OpenLmisResponse> save(@RequestBody T model)  throws Exception{
        try {
            getService().save(model);
        } catch (DataException e) {
            return OpenLmisResponse.error(e, BAD_REQUEST);
        }
        return OpenLmisResponse.response(getTableNameSingle(), getService().getById(model.getId()));
    }
    public abstract StockService getService();

    private String getTableName() throws Exception{
        ParameterizedType pt
                = (ParameterizedType) getClass().getGenericSuperclass();
        // You may need this split or not, use logging to check
        String parameterClassName
                = pt.getActualTypeArguments()[0].toString().split("\\s")[1];
        // Instantiate the Parameter and initialize it.
        T parameter = (T) Class.forName(parameterClassName).newInstance();
        return parameter.getTableName();
    }
    private String getTableNameSingle() throws Exception{
        String table = getTableName();
        return table.substring(0,table.length() - 1);
    }
}
