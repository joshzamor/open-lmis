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
    String name;
    Integer unit_per_box;
    Integer vaccine_id;

    @Override
    public String getTableName() {
        return "diluent";
    }
}
