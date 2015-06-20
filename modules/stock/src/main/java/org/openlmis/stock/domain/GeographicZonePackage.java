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
public class GeographicZonePackage extends StockModel{

    Long id;
    String package_number;
    Integer number_of_packages ;
    Date date_sent;
    Date date_recieved;
    String recieved_status;
    Integer sending_user;
    Integer receiving_user;
    Integer facility_id;
    Integer geographic_zone_id;
    Integer vaccine_packaging_id;

    @Override
    public String getTableName() {
        return "geographic_zone_package";
    }
}
