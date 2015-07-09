package org.openlmis.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Morley on 6/20/2015.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VarDetails extends StockModel{
    Long id;
    String awb_number;
    String flight_number;
    Date estimate_time_of_arrival;
    Date actual_time_of_arrival;
    Integer number_of_items_inspected;
    String coolant_type;
    String temperature_monitor;

    @Override
    public String getTableName() {
        return "var_details";
    }
}
