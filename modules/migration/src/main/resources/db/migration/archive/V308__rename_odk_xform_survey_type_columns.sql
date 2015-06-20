ALTER TABLE odk_xform RENAME COLUMN odk_xform_survey_type_id TO odkxformsurveytypeid;

ALTER TABLE odk_xform_survey_type RENAME COLUMN survey_name TO surveyname;
ALTER TABLE odk_xform_survey_type RENAME COLUMN number_of_questions TO numberofquestions;

ALTER TABLE odk_stock_status_submission RENAME COLUMN odk_submission_id TO odksubmissionid;
ALTER TABLE odk_stock_status_submission RENAME COLUMN msd_code TO msdcode;
ALTER TABLE odk_stock_status_submission RENAME COLUMN commodity_name TO commodityname;
ALTER TABLE odk_stock_status_submission RENAME COLUMN physical_inventory TO physicalinventory;
ALTER TABLE odk_stock_status_submission RENAME COLUMN qty_expired_today TO qtyexpiredtoday;
ALTER TABLE odk_stock_status_submission RENAME COLUMN stock_card_available TO stockcardavailable;
ALTER TABLE odk_stock_status_submission RENAME COLUMN stock_data_three_months TO stockdatathreemonths;
ALTER TABLE odk_stock_status_submission RENAME COLUMN so_seven_days TO sosevendays;
ALTER TABLE odk_stock_status_submission RENAME COLUMN total_days_stockedout_three_months TO totaldaysstockedoutthreemonths;
ALTER TABLE odk_stock_status_submission RENAME COLUMN issued_three_months TO issuedthreemonths;
ALTER TABLE odk_stock_status_submission RENAME COLUMN days_data_available TO daysdataavailable;
ALTER TABLE odk_stock_status_submission RENAME COLUMN qtyexpiredtoday TO quantityexpiredtoday;


