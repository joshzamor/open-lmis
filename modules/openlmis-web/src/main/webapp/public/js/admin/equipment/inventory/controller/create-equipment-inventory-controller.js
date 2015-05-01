/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

function CreateEquipmentInventoryController($scope, $location, $routeParams, EquipmentInventory, Donors ,Equipments, SaveEquipmentInventory, Facility, EquipmentInventoryFacilities, EquipmentOperationalStatus, messageService) {

  $scope.$parent.message = $scope.$parent.error = '';

  $scope.max_year = new Date().getFullYear();
  $scope.submitted = false;
  $scope.showError = false;

  $scope.equipmentTypes = [];
  $scope.manuModels = [];
  $scope.models = [];
  $scope.from = $routeParams.from;

  Equipments.get(function (data) {
    $scope.equipments = data.equipments;
    for (var i = 0; i < data.equipments.length; i++) {
      $scope.equipmentTypes[data.equipments[i].equipmentTypeId] = data.equipments[i].equipmentType;

      // Create manu/model object array
      if (data.equipments[i].equipmentTypeId === 36) { // Only create for CCE
        var manuModelArray = data.equipments[i].name.split(" / ");
        var manu = manuModelArray[0];
        var model = manuModelArray[1];
        var manuFound = false;
        for (var j = 0; j < $scope.manuModels.length; j++) {
          if ($scope.manuModels[j].name === manu) { // Found manu in array, add model to list
            $scope.manuModels[j].models.push(model);
            manuFound = true;
          }
        }
        if (!manuFound) {
          $scope.manuModels.push({name: manu, models: [model]});
        }
      }

      // Inject test energy source data into equipments
      switch (i % 3) {
        case 0:
          $scope.equipments[i].energySource = "Gas";
          break;
        case 1:
          $scope.equipments[i].energySource = "Electricity";
          break;
        case 2:
          $scope.equipments[i].energySource = "Solar";
          break;
      }
    }
    console.log($scope.manuModels);
  });

  if ($scope.from === "0") { // My facility
    Facility.get({id: $routeParams.facility}, function(data){
      $scope.facility = data.facility;
      $scope.equipment.facilityId = $scope.facility.id;
      $scope.facilityDisplayName = $scope.facility.code + " - " + $scope.facility.name;
    });
  } else { // Supervised facilities
    $scope.facilities = [
      {
        code: "F10",
        description: "Dispensary",
        id: 387,
        name: "Dispensary",
        virtualFacility: false
      },
      {
        code: "F11",
        description: "Central Hospital",
        id: 388,
        name: "Central Hospital",
        virtualFacility: false
      }
    ];
    /*      EquipmentInventoryFacilities.get({programId: 5}, function (data) {
     $scope.vacFacilities = data.facilities;
     }, {});
     */
  }


  if ($routeParams.id === undefined) { // Add New
    $scope.screenType = "create";
    $scope.equipment = {};
    $scope.equipment.equipmentTypeId = parseInt($routeParams.equipmentType);
    $scope.equipment.programId = 82; // TODO: remove this once we get the real program id

    // set default of checkboxes so the submission does not become null and hence an error.
    $scope.equipment.replacementRecommended = false;
    $scope.equipment.hasServiceContract = false;
    $scope.equipment.dateLastAssessed = Date.now();
    $scope.equipment.isActive = true;

    // Set CCE defaults
    $scope.equipment.yearOfInstallation = new Date().getFullYear();

  } else { // Edit
    $scope.screenType = "edit";
    EquipmentInventory.get({
      id: $routeParams.id
    }, function (data) {
      $scope.equipment = data.inventory;
      $scope.equipment.equipmentTypeId = $routeParams.equipmentType;
      $scope.equipment.programId = 82; // TODO: remove this once we get the real program id

      $scope.equipment.dateLastAssessed = $scope.equipment.dateLastAssessedString ;
      $scope.equipment.dateDecommissioned = $scope.equipment.dateDecommissionedString;
      $scope.equipment.serviceContractEndDate = $scope.equipment.serviceContractEndDateString;

      $scope.updateEquipments();
/*      Facility.get({ id: $scope.equipment.facilityId }, function(data){
        $scope.facility = data.facility;
      });*/
    });
  }

  EquipmentOperationalStatus.get(function(data){
     $scope.operationalStatusList = data.status;
  });

  Donors.get(function(data){
    $scope.donors = data.donors;
  });

  $scope.updateModels = function () {
    $scope.equipment.model = null;
    $scope.equipment.equipmentId = null;
    $scope.equipmentDisplayName = "";
    $scope.equipment.energySource = "";
    $scope.models = [];
    var arr = $scope.manuModels;
    for (var i = 0; i < arr.length; i++) {
      if (arr[i].name === $scope.equipment.manufacturerName) {
        $scope.models = arr[i].models;
        if (arr[i].models.length === 1) {
          $scope.equipment.model = $scope.models[0];
          $scope.updateEquipments();
        }
        break;
      }
    }
  };

  $scope.updateEquipments = function () {
    var arr = $scope.equipments;
    for (var i = 0; i < arr.length; i++) {
      if (arr[i].name === $scope.equipment.manufacturerName + " / " + $scope.equipment.model) {
        $scope.equipment.equipmentId = arr[i].id;
        $scope.equipmentDisplayName = $scope.equipment.manufacturerName + " / " + $scope.equipment.model;
        $scope.equipment.energySource = arr[i].energySource;
        break;
      }
    }
  };

  $scope.saveEquipment = function () {
    $scope.error = '';
    $scope.showError = true;
    if(!$scope.equipmentForm.$invalid ){
      SaveEquipmentInventory.save($scope.equipment, function (data) {
        $scope.$parent.message = messageService.get(data.success);
        $scope.$parent.selectedProgram = {id: $scope.equipment.programId};
        console.info($scope.$parent.selectedProgram);
        $location.path('/' + $routeParams.from + '/' + $scope.equipment.facility + '/' + $scope.equipment.program);
      }, function (data) {
        $scope.error = data.error;
      });
    }else{
      $scope.submitted = true;
      $scope.error = messageService.get('message.equipment.inventory.data.invalid');
    }
  };

  $scope.cancelCreateEquipment = function () {
    $location.path('');
  };
}