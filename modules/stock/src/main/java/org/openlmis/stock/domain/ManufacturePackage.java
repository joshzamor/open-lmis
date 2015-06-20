package org.openlmis.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ManufacturePackage  extends StockModel {
    Long id;
    String shipment_id;
    Date manufacture_date;
    Date expire_date;
    String lot_number;
    Integer number_of_doses;
    String delivery_status;

    String purchasing_order_number;

    //@JsonIgnore
    Integer vaccine_packaging_id;
    VaccinePackaging vaccine_packaging;

    @Override
    public String getTableName() {
        return "manufacture_package";
    }
}
