/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

function EquipmentInventoryController($scope, UserFacilityList, EquipmentInventories,  ManageEquipmentInventoryProgramList, ManageEquipmentInventoryFacilityProgramList,EquipmentInventoryFacilities, EquipmentTypes, EquipmentOperationalStatus, navigateBackService, $routeParams, messageService) {

  if ($routeParams.selectedType !== undefined) {
    $scope.selectedType = $routeParams.selectedType;
    $scope.selectedFacilityId = $routeParams.facility;
  }

  EquipmentOperationalStatus.get(function(data){
    $scope.operationalStatusList = data.status;
  });

  EquipmentTypes.get({}, function (data) {
    $scope.equipmentTypes = data.equipment_type;
  });

  $scope.$on('$viewContentLoaded', function () {
    $scope.selectedType = $routeParams.selectedType || "0";

    $scope.$watch('programs', function () {
      if ($scope.programs && !isUndefined($routeParams.program)) {
        $scope.selectedProgram = _.where($scope.programs, {id: $routeParams.program})[0];
      }
    });

    $scope.loadFacilityData($scope.selectedType);
    if ($scope.selectedProgram !== undefined) {
      $scope.loadFacilitiesForProgram();
    }
  });

  $scope.loadFacilityData = function (selectedType) {

    if (selectedType === "0") { //My facility
      UserFacilityList.get({}, function (data) {
        $scope.facilities = data.facilityList;
        $scope.myFacility = data.facilityList[0];
        if ($scope.myFacility) {
          $scope.facilityDisplayName = $scope.myFacility.code + '-' + $scope.myFacility.name;
          $scope.selectedFacilityId = $scope.myFacility.id;
          $scope.selectedProgram = undefined;
          ManageEquipmentInventoryFacilityProgramList.get({facilityId: $scope.selectedFacilityId}, function (data) {
            $scope.programs = data.programs;
          }, {});
        } else {
          $scope.facilityDisplayName = messageService.get("label.none.assigned");
          $scope.programs = undefined;
          $scope.selectedProgram = undefined;
        }
      }, {});
    } else if (selectedType === "1") { // Supervised facility
      ManageEquipmentInventoryProgramList.get({}, function (data) {
        $scope.programs = data.programs;
        $scope.selectedFacilityId = undefined;
      }, {});
    }
  };

  $scope.loadFacilitiesForProgram = function () {
    if ($scope.selectedProgram.id && $scope.selectedType === '1') {
      EquipmentInventoryFacilities.get({programId: $scope.selectedProgram.id}, function (data) {
        $scope.facilities = data.facilities;
        $scope.selectedFacilityId = null;
        $scope.error = null;
      }, {});
    }
  };

  $scope.loadEquipments = function () {
    if ($scope.equipmentTypeId !== undefined) {
/*      EquipmentInventories.get({
        equipmentTypeId: $scope.equipmentTypeId
      }, function (data) {
        $scope.inventory = data.inventory;
      });
*/
      if ($scope.equipmentTypeId === "2") {
        $scope.inventory = [
          {
            "id": 5,
            "facilityId": 387,
            "programId": 2,
            "equipmentId": null,
            "equipment": {
              "id": 2,
              "name": "Some Anesthesia Thing",
              "code": "e124",
              "equipmentType": {
                "id": 2,
                "name": "Anesthesia Units",
                "code": "ANS"
              },
              "equipmentTypeId": 2
            },
            "operationalStatusId": 2,
            "serialNumber": null,
            "manufacturerName": null,
            "model": "AN 100",
            "energySource": "Elec",
            "yearOfInstallation": null,
            "purchasePrice": null,
            "sourceOfFund": null,
            "replacementRecommended": false,
            "reasonForReplacement": null,
            "nameOfAssessor": null,
            "primaryDonorId": null,
            "hasServiceContract": false,
            "serviceContractEndDate": null,
            "isActive": true,
            "dateDecommissioned": null,
            "dateLastAssessed": 1488441600000,
            "capacity": null,
            "minTemperature": null,
            "maxTemperature": null,
            "dimension": null,
            "accessories": null,
            "dateLastAssessedString": "2017-02-03",
            "dateDecommissionedString": null,
            "serviceContractEndDateString": null
          },
          {
            "id": 6,
            "facilityId": 387,
            "programId": 2,
            "equipmentId": null,
            "equipment": {
              "id": 2,
              "name": "Some Anesthesia Thing",
              "code": "e124",
              "equipmentType": {
                "id": 2,
                "name": "Anesthesia Units",
                "code": "ANS"
              },
              "equipmentTypeId": 2
            },
            "operationalStatusId": 1,
            "serialNumber": null,
            "manufacturerName": null,
            "model": "AN 101",
            "energySource": null,
            "yearOfInstallation": null,
            "purchasePrice": null,
            "sourceOfFund": null,
            "replacementRecommended": false,
            "reasonForReplacement": null,
            "nameOfAssessor": null,
            "primaryDonorId": null,
            "hasServiceContract": false,
            "serviceContractEndDate": null,
            "isActive": true,
            "dateDecommissioned": null,
            "dateLastAssessed": 1430118000000,
            "capacity": null,
            "minTemperature": null,
            "maxTemperature": null,
            "dimension": null,
            "accessories": null,
            "dateLastAssessedString": "2015-27-04",
            "dateDecommissionedString": null,
            "serviceContractEndDateString": null
          }
        ];
      } else if ($scope.equipmentTypeId === "1") {
        $scope.inventory = [
          {
            "id": 7,
            "facilityId": 387,
            "programId": 2,
            "equipmentId": null,
            "equipment": {
              "id": 1,
              "name": "Some Lab Thing",
              "code": "e123",
              "equipmentType": {
                "id": 1,
                "name": "Lab Equipment",
                "code": "LAB"
              },
              "equipmentTypeId": 1
            },
            "operationalStatusId": 1,
            "serialNumber": null,
            "manufacturerName": null,
            "model": "LAB 1000",
            "energySource": null,
            "yearOfInstallation": null,
            "purchasePrice": null,
            "sourceOfFund": null,
            "replacementRecommended": false,
            "reasonForReplacement": null,
            "nameOfAssessor": null,
            "primaryDonorId": null,
            "hasServiceContract": false,
            "serviceContractEndDate": null,
            "isActive": true,
            "dateDecommissioned": null,
            "dateLastAssessed": 1430118000000,
            "capacity": null,
            "minTemperature": null,
            "maxTemperature": null,
            "dimension": null,
            "accessories": null,
            "dateLastAssessedString": "2015-27-04",
            "dateDecommissionedString": null,
            "serviceContractEndDateString": null
          }
        ];
      } else if ($scope.equipmentTypeId === "4") {
        $scope.inventory = [
          {
            "id": 8,
            "facilityId": 387,
            "programId": 2,
            "equipmentId": null,
            "equipment": {
              "id": 25,
              "name": "Some Microscope",
              "code": "MIC0001",
              "equipmentType": {
                "id": 4,
                "name": "Microscopes",
                "code": "MIC"
              },
              "equipmentTypeId": 4
            },
            "operationalStatusId": 3,
            "serialNumber": null,
            "manufacturerName": null,
            "model": "MIC 10000",
            "energySource": null,
            "yearOfInstallation": null,
            "purchasePrice": null,
            "sourceOfFund": null,
            "replacementRecommended": false,
            "reasonForReplacement": null,
            "nameOfAssessor": null,
            "primaryDonorId": null,
            "hasServiceContract": false,
            "serviceContractEndDate": null,
            "isActive": true,
            "dateDecommissioned": null,
            "dateLastAssessed": 1430118000000,
            "capacity": null,
            "minTemperature": null,
            "maxTemperature": null,
            "dimension": null,
            "accessories": null,
            "dateLastAssessedString": "2015-27-04",
            "dateDecommissionedString": null,
            "serviceContractEndDateString": null
          }
        ];
      } else if ($scope.equipmentTypeId === "3") {
        $scope.inventory = [
          {
            "id": 4,
            "facilityId": 387,
            "programId": 2,
            "equipmentId": null,
            "equipment": {
              "id": 18,
              "name": "Dulas / VC 150-2",
              "code": "PQS E003/027",
              "refrigerant": "R134a",
              "fridgeCapacity": 86.0,
              "freezerCapacity": null,
              "equipmentType": {
                "id": 3,
                "name": "Cold Chain Equipment",
                "code": "CCE"
              },
              "equipmentTypeId": 3
            },
            "operationalStatusId": 2,
            "serialNumber": "sdfsfsdfdfddf",
            "manufacturerName": "Dulas",
            "model": "VC 150-2",
            "energySource": "Electricity",
            "yearOfInstallation": 2009,
            "purchasePrice": null,
            "sourceOfFund": null,
            "replacementRecommended": false,
            "reasonForReplacement": null,
            "nameOfAssessor": null,
            "primaryDonorId": null,
            "hasServiceContract": false,
            "serviceContractEndDate": null,
            "isActive": true,
            "dateDecommissioned": null,
            "dateLastAssessed": 1562050800000,
            "capacity": null,
            "minTemperature": null,
            "maxTemperature": null,
            "dimension": null,
            "accessories": null,
            "dateLastAssessedString": "2019-02-07",
            "dateDecommissionedString": null,
            "serviceContractEndDateString": null
          },
          {
            "id": 9,
            "facilityId": 387,
            "programId": 2,
            "equipmentId": null,
            "equipment": {
              "id": 20,
              "name": "Haier / HBC-70",
              "code": "PQS E003/005",
              "refrigerant": "R134a",
              "fridgeCapacity": 45.0,
              "freezerCapacity": null,
              "equipmentType": {
                "id": 3,
                "name": "Cold Chain Equipment",
                "code": "CCE"
              },
              "equipmentTypeId": 3
            },
            "operationalStatusId": 1,
            "serialNumber": "HAI 70000",
            "manufacturerName": "Haier",
            "model": "HBC-70",
            "energySource": "Gas",
            "yearOfInstallation": 2010,
            "purchasePrice": null,
            "sourceOfFund": null,
            "replacementRecommended": false,
            "reasonForReplacement": null,
            "nameOfAssessor": null,
            "primaryDonorId": null,
            "hasServiceContract": false,
            "serviceContractEndDate": null,
            "isActive": true,
            "dateDecommissioned": null,
            "dateLastAssessed": 1430118000000,
            "capacity": null,
            "minTemperature": null,
            "maxTemperature": null,
            "dimension": null,
            "accessories": null,
            "dateLastAssessedString": "2015-27-04",
            "dateDecommissionedString": null,
            "serviceContractEndDateString": null
          },
          {
            "id": 10,
            "facilityId": 387,
            "programId": 2,
            "equipmentId": null,
            "equipment": {
              "id": 23,
              "name": "Vestfrost / MK 304",
              "code": "PQS E003/007",
              "refrigerant": "R134a",
              "fridgeCapacity": 105.0,
              "freezerCapacity": null,
              "equipmentType": {
                "id": 3,
                "name": "Cold Chain Equipment",
                "code": "CCE"
              },
              "equipmentTypeId": 3
            },
            "operationalStatusId": 1,
            "serialNumber": "VF 484892",
            "manufacturerName": "Vestfrost",
            "model": "MK 304",
            "energySource": "Electricity",
            "yearOfInstallation": 2011,
            "purchasePrice": null,
            "sourceOfFund": null,
            "replacementRecommended": false,
            "reasonForReplacement": null,
            "nameOfAssessor": null,
            "primaryDonorId": null,
            "hasServiceContract": false,
            "serviceContractEndDate": null,
            "isActive": true,
            "dateDecommissioned": null,
            "dateLastAssessed": 1430118000000,
            "capacity": null,
            "minTemperature": null,
            "maxTemperature": null,
            "dimension": null,
            "accessories": null,
            "dateLastAssessedString": "2015-27-04",
            "dateDecommissionedString": null,
            "serviceContractEndDateString": null
          },
          {
            "id": 11,
            "facilityId": 387,
            "programId": 2,
            "equipmentId": null,
            "equipment": {
              "id": 19,
              "name": "Electrolux / RC65",
              "code": "Old PIS",
              "refrigerant": "NH3",
              "fridgeCapacity": null,
              "freezerCapacity": 65.0,
              "equipmentType": {
                "id": 3,
                "name": "Cold Chain Equipment",
                "code": "CCE"
              },
              "equipmentTypeId": 3
            },
            "operationalStatusId": 3,
            "serialNumber": "EL48048",
            "manufacturerName": "Electrolux",
            "model": "RC65",
            "energySource": "Gas",
            "yearOfInstallation": 2005,
            "purchasePrice": null,
            "sourceOfFund": null,
            "replacementRecommended": false,
            "reasonForReplacement": null,
            "nameOfAssessor": null,
            "primaryDonorId": null,
            "hasServiceContract": false,
            "serviceContractEndDate": null,
            "isActive": true,
            "dateDecommissioned": null,
            "dateLastAssessed": 1430118000000,
            "capacity": null,
            "minTemperature": null,
            "maxTemperature": null,
            "dimension": null,
            "accessories": null,
            "dateLastAssessedString": "2015-27-04",
            "dateDecommissionedString": null,
            "serviceContractEndDateString": null
          }
        ];
      }
    }
  };

  $scope.getStatusName = function (statusId) {
    for (var i = 0; i < $scope.operationalStatusList.length; i++) {
      if ($scope.operationalStatusList[i].id === statusId) {
        return $scope.operationalStatusList[i].name;
      }
    }
  };

  $scope.getAge = function (yearOfInstallation) {
    return (new Date().getFullYear()) - yearOfInstallation;
  }

}