/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

function CreateColdChainEquipmentController($scope, $routeParams, $location, Equipment, EquipmentTypes, SaveEquipment, messageService) {

  //Types/Designations
  $scope.equipmentTypes=[{id:1,name:"REFRIGERATOR"},{id:2,name:"FREEZER"},{id:3,name:"FREEZER AND REFRIGERATOR"}];
  $scope.energySources=[{id:1,name:"Solar"},{id:2,name:"Electricity"},{id:3,name:"Gas"}];
  $scope.pqsStatus=[{id:1,name:"Authorised"},{id:2,name:"Suspended"}];

  EquipmentTypes.get(function (data) {
  //  $scope.equipmentTypes_ = data.equipment_type;
  });
  // clear the message when this page is loaded.
  $scope.$parent.message = '';

  if ($routeParams.id === undefined) {
    $scope.equipment = {};
  } else {
    var equipments=[{id:1,name:"concord/concord1",designation:"Refrigerator",brand:"Concord",model:"Concord1",code:"CR",pqsCode:"domestic",refrigeratorCapacity:20,freezerCapacity:8},{id:2,name:"Biplex/Biplex Rural",designation:"Refrigerator&Freezer",brand:"Biplex",model:"Biplex Rural",code:"CR",pqsCode:"domestic",refrigeratorCapacity:55,freezerCapacity:36}];
    $scope.equipment={id:1,name:"concord/concord1",type:2,brand:"Concord",model:"Concord1",code:"CR",pqsCode:"domestic",refrigeratorCapacity:20,freezerCapacity:8};
  }

  $scope.saveEquipment = function () {

    var onSuccess = function(data){
      $scope.$parent.message = messageService.get(data.success);
      $location.path('');
    };

    var onError = function(data){
      $scope.showError = true;
      $scope.error = messageService.get(data.data.error);
    };

    if(!$scope.equipmentForm.$invalid){
      SaveEquipment.save($scope.equipment, onSuccess, onError);
    }

  };

  $scope.cancelCreateEquipment = function () {
    $location.path('');
  };
}