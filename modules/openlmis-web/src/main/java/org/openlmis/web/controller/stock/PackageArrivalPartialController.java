package org.openlmis.web.controller.stock;

import org.apache.ibatis.annotations.*;
import org.openlmis.core.domain.GeographicZone;
import org.openlmis.stock.domain.GeographicZonePackage;
import org.openlmis.stock.domain.PackageArrivalPartial;
import org.openlmis.stock.domain.Vaccine;
import org.openlmis.stock.domain.VaccinePackaging;
import org.openlmis.stock.repository.mapper.HasGeographicZone;
import org.openlmis.stock.repository.mapper.HasVaccinePackaging;
import org.openlmis.stock.repository.mapper.ModelProviders;
import org.openlmis.stock.repository.mapper.StockMapper;
import org.openlmis.stock.service.GeographicZonePackageService;
import org.openlmis.stock.service.PackageArrivalPartialService;
import org.openlmis.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Morley on 7/9/2015.
 */

@Controller
@RequestMapping(value = "/stock/package/arrival/partial")
public class PackageArrivalPartialController extends StockBaseController<PackageArrivalPartial>{
    @Autowired
    private PackageArrivalPartialService sevrice;

    @Override
    public StockService getService() {
        return sevrice;
    }

}
