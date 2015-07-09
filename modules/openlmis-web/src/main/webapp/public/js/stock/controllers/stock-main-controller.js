/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

vaccine.controller("StockReceiveController",function($scope,$http,$location,$routeParams,$resource,$filter){
   $scope.scan_afresh = true;
   $scope.data = {};
   $scope.data.packages = [];
   $scope.data.processed_packages = [];
   $scope.loading_packages = false;
   $scope.no_package = false;
   $scope.process_package = false;
   $scope.data.shipping_number = "";
   $("#shipping_number").focus();
   $http.get('/stock/manufacture/package').
       success(function(data, status, headers, config) {
          $scope.data.packages = data.manufacture_packages;
       }).
       error(function(data) {
          $scope.loading_packages = false;
          console.log("Error:" + data);
       });

   $scope.scanPackage = function(){
      $scope.loading_packages = true;
      $http.get('/stock/manufacture/package?filter=shipment_id:eq:'+$scope.data.shipping_number+';delivery_status:eq:Pending').success(function(data, status, headers, config) {
         $scope.loading_packages = false;
         if(data.manufacture_packages.length == 0){
            $scope.data.processed_packages = {};
            $scope.no_package = true;
         }
         if(data.manufacture_packages.length != 0){
            $scope.data.processed_packages = data.manufacture_packages;
            $scope.no_package = false;
            $scope.process_package = true;
            $scope.scan_afresh = false;
         }

       }). error(function(data) {
         $scope.loading_packages = false;
         $scope.no_package = true;
             console.log("Error:" + data);
       });
   }

   //display package details
   $scope.loading_item = false;
   $scope.error_loading_item = false;
   $scope.show_singleItem = false;
   $scope.data.current_item = [];
       $scope.scanLotNumber = function(lot_number){
      $scope.loading_item = true;
      $scope.data.current_item = $scope.getItem(lot_number);
      if($scope.data.current_item.length == 0){
         $scope.error_loading_item = true;
         $scope.loading_item = false;
      }else{
         $scope.error_loading_item = false;
         $scope.loading_item = false;
         $scope.show_singleItem = true;
         $scope.process_package = false;
      }
   }

   //find an item with specific lot number
   $scope.getItem = function(lot_number){
      var item = [];
      angular.forEach($scope.data.processed_packages,function(value){
         if(lot_number == value.lot_number){
            item = value;
            $scope.condition = {}
            $scope.condition.number_recieved = $scope.numberOfBoxes(value.vaccine_packaging.doses_per_vial,value.vaccine_packaging.vials_per_box,value.number_of_doses)
         }
      });
      return item;
   }

   //rollback completely
   $scope.cancelPackageProcessing = function(){
      $scope.data.processed_packages = [];
      $scope.loading_packages = false;
      $scope.no_package = false;
      $scope.process_package = false;
      $scope.scan_afresh = true;
      $scope.data.shipping_number = "";
      $("#shipping_number").focus();
   }

   //go back one step to list of items
   $scope.cancelConfirmation = function(){
      $scope.show_singleItem = false;
      $scope.process_package = true;
   }
   //uncover the number of boxes
   $scope.numberOfBoxes = function(doses_per_vials,vials_per_box,number_of_doses){
      $scope.boxes = parseInt(number_of_doses/(doses_per_vials*vials_per_box));
      var boxes = $scope.boxes;
      return boxes;
   }

   //return color of a column
   $scope.getcolor = function (status) {
      if(status == "Pending"){
         return '';
      }if(status == "processed"){
         return "rgba(91,155,67,0.6)"
      }
   }

   //determine if all items have been checked
   $scope.allChecked = function(){
      var count  = 0;
      var item_length = $scope.data.processed_packages.length;
      angular.forEach($scope.data.processed_packages,function(value){
         if(value.delivery_status == 'processed'){
            count++;
         }
      });
      if(count == item_length){
         return true;
      }else{
         return false;
      }
   }
   //confirming single item
   $scope.itemTosave = {};
   $scope.itemTosave.arrival_package = [];
   $scope.addSingleItem = function(details,item){
       item.delivery_status = "processed";
       $scope.show_singleItem = false;
       $scope.process_package = true;
   }

});