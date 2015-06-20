package org.openlmis.report.model.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlmis.report.model.ReportData;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegimenSummaryReport implements ReportData {

    @Column(name = "regimen")
    private String regimen;

    @Column(name="patientsontreatment")
    private Integer patientsontreatment;

    private String district;
    private String facilityName;
    private String facilityCode;
    private String facilityTypeName;
    private String region;
    private String zone;


    @Column(name= "patientstoinitiatetreatment")
    private Integer patientstoinitiatetreatment;

   @Column(name="patientsstoppedtreatment")
    private Integer patientsstoppedtreatment;

    private Double totalpatientsToInitiateTreatmentPercentage;
    private Double totalOnTreatmentPercentage;
    private Double stoppedTreatmentPercentage;


}
