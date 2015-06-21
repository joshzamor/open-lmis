/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

function CreateEquipmentTypeController($scope, $routeParams, $location, EquipmentType, SaveEquipmentType) {

  $scope.$parent.message = '';

  if (isUndefined($routeParams.id)) {
    $scope.equipment_type = {};
  } else {
    EquipmentType.get({
      id: $routeParams.id
    }, function (data) {
      $scope.equipment_type = data.equipment_type;
    });
  }


  $scope.saveEquipmentType = function () {
    // clear the error message
    $scope.error = undefined;

    var onSuccess = function(data){
      $scope.$parent.message = 'Your changes have been saved!';
      $location.path('');
    };

    var onError = function(data){
      $scope.showError = true;
      $scope.error = data.data.error;
    };

    if(!$scope.equipmentTypeForm.$invalid){
      SaveEquipmentType.save( $scope.equipment_type, onSuccess, onError );
    }

  };

  $scope.cancelCreateEquipmentType = function () {
    $location.path('');
  };
}