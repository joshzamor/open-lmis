/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

function CreateEquipmentInventoryController($scope, $location, $routeParams, EquipmentInventory, Donors ,Equipments, SaveEquipmentInventory, Facility, EquipmentOperationalStatus, messageService) {

  $scope.$parent.message = $scope.$parent.error = '';

  $scope.max_year = new Date().getFullYear();
  $scope.submitted = false;
  $scope.showError = false;
  Equipments.get(function (data) {
    $scope.equipments = data.equipments;
  });

  if ($routeParams.id === undefined) {
    $scope.equipment = {};
    $scope.equipment.programId = $routeParams.program;
    $scope.equipment.facilityId = $routeParams.facility;

    Facility.get({id: $routeParams.facility}, function(data){
      $scope.facility = data.facility;
    });

    // set default of checkboxes so the submission does not become null and hence an error.
    $scope.equipment.replacementRecommended = false;
    $scope.equipment.hasServiceContract = false;
    $scope.equipment.dateLastAssessed = Date.now();
    $scope.equipment.isActive = true;

  } else {
    EquipmentInventory.get({
      id: $routeParams.id
    }, function (data) {
      $scope.equipment = data.inventory;
      $scope.equipment.dateLastAssessed = $scope.equipment.dateLastAssessedString ;
      $scope.equipment.dateDecommissioned = $scope.equipment.dateDecommissionedString;
      $scope.equipment.serviceContractEndDate = $scope.equipment.serviceContractEndDateString;
      Facility.get({ id: $scope.equipment.facilityId }, function(data){
        $scope.facility = data.facility;
      });
    });
  }

  EquipmentOperationalStatus.get(function(data){
     $scope.operationalStatusList = data.status;
  });

  Donors.get(function(data){
    $scope.donors = data.donors;
  });

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