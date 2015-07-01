package org.openlmis.web.controller.stock;

import org.openlmis.stock.domain.Diluent;
import org.openlmis.stock.domain.VarDocument;
import org.openlmis.stock.service.DiluentService;
import org.openlmis.stock.service.StockService;
import org.openlmis.stock.service.VarDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Morley on 6/28/2015.
 */

@Controller
@RequestMapping(value = "/stock/var/document")
public class VarDocumentController  extends StockBaseController<VarDocument>{
    @Autowired
    private VarDocumentService service;

    @Override
    public StockService getService() {
        return service;
    }
}
