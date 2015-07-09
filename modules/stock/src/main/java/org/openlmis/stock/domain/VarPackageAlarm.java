package org.openlmis.stock.domain;

import java.util.Date;

/**
 * Created by Morley on 6/28/2015.
 */
public class VarPackageAlarm  extends StockModel{

    Long id;
    String lot_number;
    String alarm_temperature;
    String cold_chain_monitor;
    Date time_of_inspection;
    Long var_details_id;

    VarDetails var_details;
    @Override
    public String getTableName() {
        return "var_package_alarms";
    }
}
