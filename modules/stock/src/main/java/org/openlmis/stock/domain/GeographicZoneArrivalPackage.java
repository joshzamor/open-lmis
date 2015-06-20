package org.openlmis.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openlmis.core.domain.BaseModel;
import org.openlmis.core.domain.GeographicZone;
import org.openlmis.core.domain.User;

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
    String package_number;
    Integer lot_number;
    Integer number_as_expected;
    String gtin;
    Integer number_recieved;
    Integer number_expected;
    String physical_damage;
    String vvm_status;
    String problems;

    //@JsonIgnore
    Integer receiving_user;
    User user;

    //@JsonIgnore
    Integer geographic_zone_id;
    GeographicZone geographic_zone;

    //@JsonIgnore
    Integer vaccine_packaging_id;
    VaccinePackaging vaccine_packaging;

    @Override
    public String getTableName() {
        return "geographic_zone_arrival_package";
    }
}
