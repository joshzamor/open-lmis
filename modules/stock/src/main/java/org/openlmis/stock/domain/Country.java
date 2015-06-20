package org.openlmis.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openlmis.core.domain.BaseModel;

import java.util.Date;

/**
 * Created by Morley on 6/20/2015.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Country  extends BaseModel {

    Long id;
    String name;
    String longname;
    String isocode2;
    String isocode3;
    Integer createdby;
    Integer createddate;
    Integer modifiedby;
    Integer modifieddate;

}
