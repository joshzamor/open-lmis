package org.openlmis.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Morley on 6/28/2015.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class VarDocument  extends StockModel{

    Long id;
    String name;
    String comments;
    Long var_details_id;

    VarDetails var_details;
    @Override
    public String getTableName() {
        return "var_documents";
    }
}
