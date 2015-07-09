package org.openlmis.web.controller.stock;

import org.openlmis.stock.domain.VarDetails;
import org.openlmis.stock.service.VarDetailsService;
import org.openlmis.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Morley on 6/20/2015.
 */
@Controller
@RequestMapping(value = "/stock/var/details")
public class VarDetailsController extends StockBaseController<VarDetails> {
    @Autowired
    private VarDetailsService service;

    @Override
    public StockService getService() {
        return service;
    }
}
