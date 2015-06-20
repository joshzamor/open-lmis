package org.openlmis.stock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.openlmis.core.domain.BaseModel;

import java.util.Date;

/**
 * Created by Morley on 6/16/2015.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Manufacturer  extends BaseModel {

    Long id;
    String name;
    String website;
    String primaryphone;
    String email;
    String description;
    String specialization;
    String geographiccoverage;
    Date registrationdate;
}
