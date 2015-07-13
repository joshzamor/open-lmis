package org.openlmis.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openlmis.core.domain.BaseModel;
import org.openlmis.core.domain.GeographicZone;

import java.util.Date;

/**
 * Created by Morley on 7/9/2015.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PackageArrivalPartial  extends StockModel {

    Long id;
    Integer box_number;
    String lot_number;
    Integer expected_number;
    Integer available_number;

    Integer geographic_zone_id;
    GeographicZone geographic_zone;

    @Override
    public String getTableName() {
        return "package_arrival_partials";
    }
}
