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
public class GeographicZoneStock  extends StockModel{

    Long id;
    Date expire_date;
    Integer lot_number;
    Integer number_of_doses;
    Integer vaccine_id;
    Integer geographic_zone_id;

    @Override
    public String getTableName() {
        return "geographic_zone_stocks";
    }
}
