package org.openlmis.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openlmis.core.domain.BaseModel;

import java.util.Date;

/**
 * Created by Morley on 6/14/2015.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GeographicZoneArrivalPackage extends StockModel{

    Long id;
    String sscc ;
    String package_number;
    Integer lot_number;
    Integer number_as_expected;
    String gtin;
    Integer number_recieved;
    Integer number_expected;
    String physical_damage;
    String vvm_status;
    String problems;
    Integer receiving_user;
    Integer geographic_zone_id;

    @Override
    public String getTableName() {
        return "geographic_zone_arrival_stocks";
    }
}
