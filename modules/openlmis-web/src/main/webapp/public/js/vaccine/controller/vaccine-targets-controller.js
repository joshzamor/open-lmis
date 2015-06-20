/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

function VaccineTargetController($scope, $dialog, messageService, $routeParams, $location, GetFacilityCompleteList, VaccineTargetUpdate, GeographicZoneCompleteList, VaccineTargetList, GetVaccineTarget, DeleteVaccineTarget) {


    $scope.vaccineTargets = {};


    if (isUndefined($routeParams.id) || $routeParams.id === 0) {
        $scope.target = {};
    } else {

        $scope.$parent.message = '';

        GetVaccineTarget.get({
            id: $routeParams.id
        }, function (data) {
            $scope.target = data.vaccineTarget;
        });
    }

    GeographicZoneCompleteList.get({}, function(payload){
        $scope.geographicZones = payload.geographicZones;
    });

    loadVaccineTargetList();

    $scope.saveVaccineTarget = function(){

        if ($scope.vaccineTargetForm.$error.pattern || $scope.vaccineTargetForm.$error.required) {
            $scope.showError = "true";
            $scope.error = 'form.error';
            $scope.message = "";
            return;
        }

        var onSuccess = function(data){
            $scope.$parent.message = 'Your changes have been saved!';
            loadVaccineTargetList();
            $location.path('/targets');
        };

        var onError = function(data){
            $scope.showError = true;
            $scope.error = data.data.error;
        };

        VaccineTargetUpdate.post($scope.target,onSuccess, onError);
    };

    $scope.cancelTargetForm = function(){
        $location.path('/targets');
    };


    function loadVaccineTargetList(){
        VaccineTargetList.get({}, function(data){
            $scope.vaccineTargets = data.vaccineTargets;
        });
    }

    $scope.showRemoveVaccineTargetDialog = function (index) {
        var vaccineTarget = $scope.vaccineTargets[index];
        $scope.selectedVaccineTarget = vaccineTarget;

        var options = {
            id: "removeVaccineTargetConfirmDialog",
            header: "Confirmation",
            body: "Please confirm that you want to delete the selected vaccine target"
        };
        OpenLmisDialog.newDialog(options, $scope.removeVaccineTargetConfirm, $dialog, messageService);
    };

    $scope.removeVaccineTargetConfirm = function (result) {

        if (result) {
            DeleteVaccineTarget.delete({'id': $scope.selectedVaccineTarget.id}, function(){
                loadVaccineTargetList();
                $scope.$parent.message = 'Vaccine target deleted successfully!';
            });
        }
    };
}

