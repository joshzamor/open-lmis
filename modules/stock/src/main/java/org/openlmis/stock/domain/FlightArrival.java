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
public class FlightArrival extends StockModel{
    Long id;
    String awb_number;
    String destination;
    String flight_number;
    Date estimate_time_of_arrival;
    Date time_of_arrival;

    @Override
    public String getTableName() {
        return "flight_arrival";
    }
}
