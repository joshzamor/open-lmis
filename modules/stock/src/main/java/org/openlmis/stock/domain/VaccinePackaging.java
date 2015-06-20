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
public class VaccinePackaging extends StockModel{

    Long id;
    String packaging;
    String gtin;
    Integer doses_per_vial;
    Integer vials_per_box;
    String wastage;
    Integer schedule;
    String status;
    Integer country_id;
    Integer manufacturer_id;
    Manufacturer manufacturer;

    @Override
    public String getTableName() {
        return "vaccine_packaging";
    }
}
