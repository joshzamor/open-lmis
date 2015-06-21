package org.openlmis.stock.domain;

/**
 * Created by Morley on 6/14/2015.
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;
import org.openlmis.core.domain.BaseModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Vaccine extends StockModel{

        Long id;
        String name;
        Integer expire_warning_period;
        String type;
        String status;

        @Override
        public String getTableName() {
                return "vaccines";
        }
}
