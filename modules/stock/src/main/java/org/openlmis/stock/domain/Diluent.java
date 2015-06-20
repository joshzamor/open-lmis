package org.openlmis.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openlmis.core.domain.BaseModel;

/**
 * Created by Morley on 6/14/2015.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Diluent extends StockModel{

    Long id;
    Integer vaccine_unit;
    Integer diluent_unit;
    Integer diluent_id;
    Integer vaccine_id;

    Vaccine vaccine;
    Vaccine diluent;
    @Override
    public String getTableName() {
        return "vaccine_diluents";
    }
}
