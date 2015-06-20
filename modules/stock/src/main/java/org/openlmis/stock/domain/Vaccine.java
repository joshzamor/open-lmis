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
public class Vaccine extends BaseModel{

    Long id;
    String name;
    String packaging;
    String gtin;
    Integer doses_per_vial;
    Integer vials_per_box;
    Integer expire_warning_period;
    String type;
    Float wastage;
    Integer schedule;
    Integer status;
    String country_name;
    Integer manufacture_id;
}
