package org.openlmis.stock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    //@JsonIgnore
    Integer country_id;
    Country country;

    //@JsonIgnore
    Integer manufacturer_id;
    Manufacturer manufacturer;

    //@JsonIgnore
    Long vaccine_id;
    Vaccine vaccine;

    @Override
    public String getTableName() {
        return "vaccine_packaging";
    }
}
