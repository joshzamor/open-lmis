
/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2013 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */
var vaccine = angular.module('VaccineModule', ['openlmis', 'ngTable','ui.bootstrap','nsPopover']).config(['$routeProvider', function ($routeProvider) {

    $routeProvider
        .when('/internation_arrival', {
            templateUrl: "/public/pages/stock/partials/international_shipment.html",
            controller: "StockModuleController"
        })
        .when('/package_arrivals', {
            templateUrl: "/public/pages/stock/partials/package_arrival.html",
            controller: "StockModuleController"
        })
        .when('/items', {
            templateUrl: "/public/pages/stock/partials/stock_items.html",
            controller: "StockModuleController"
        })
        .when('/sending_package', {
            templateUrl: "/public/pages/stock/partials/sending_package.html",
            controller: "StockModuleController"
        }).when('/confirm', {
            templateUrl: "/public/pages/stock/partials/confirm_reception.html",
            controller: "StockModuleController"
        })
        .when('/receive', {
            templateUrl: "/public/pages/stock/partials/receive.html",
            controller: "StockModuleController"
        }).when('/', {
            templateUrl: "/public/pages/stock/partials/scan_package.html",
            controller: "StockModuleController"
        })
        .otherwise({redirectTo: '/'});

}]).directive('onKeyup', function () {
        return function (scope, elm, attrs) {
            elm.bind("keyup", function () {
                scope.$apply(attrs.onKeyup);
            });
        };
});
vaccine.controller("StockModuleController",function($scope,$http,$location,$routeParams,$resource){
$scope.packagesJson = null;
    initialize();

    function initialize() {
        var sscc_input =angular.element('#sscc_number_field');
        $(sscc_input).focus();
        $scope.productSelectOption = {maximumSelectionSize : 4};
        $scope.$parent.currentTab = 'SUMMARY';
        $scope.showProductsFilter = true;

    }

    $http.get('/public/pages/stock/dummData/packages.json').
        success(function(data, status, headers, config) {
            $scope.packagesJson = data;
        }).
        error(function(data) {
            console.log("Error:" + data);
        });


    // action to scan package
    var scan_package_button =angular.element('#scan_package_button');
    scan_package_button.bind("click", function(){

        var sscc_number =angular.element('#sscc_number_field').val();
        var link = encodeURI("/public/pages/stock/index.html#/"+"receive?ssc="+sscc_number);
        window.location.href = link;// normal angular function don work
    });

// action to scan sub package
    var scan_lot_number_button =angular.element('#scan_lot_number_button');
    scan_lot_number_button.bind("click", function(){

        var lot_number =angular.element('#lot_number_filed').val();
        var link = encodeURI("/public/pages/stock/index.html#/"+"confirm?lotn="+lot_number+"&ssc="+$scope.sscc_number);
        window.location.href = link;// normal angular function don work
    });

    if($routeParams.ssc){
        $scope.sscc_number = $routeParams.ssc;

    }

    if($routeParams.lotn){
        $scope.lot_number = $routeParams.lotn;


    }
    //$scope.shipment = {};
    //
    //$scope.packages = [
    //    {
    //        'lot_number':111,
    //        'gtn':123456,
    //        'item':'TT',
    //        'manufacturer':'GlaxoSmithKline',
    //        'max':3,
    //        'min':2,
    //        'current_stock':80,
    //        'new_stock':0,
    //        'expire_date':'20 April 2017'
    //
    //    },{
    //        'lot_number':222,
    //        'gtn':234567,
    //        'item':'TT',
    //        'manufacturer':'GlaxoSmithKline',
    //        'max':3,
    //        'min':2,
    //        'current_stock':87,
    //        'new_stock':0,
    //        'expire_date':'20 December 2017'
    //
    //    },{
    //        'lot_number':333,
    //        'gtn':345678,
    //        'item':'TT',
    //        'manufacturer':'GlaxoSmithKline',
    //        'max':3,
    //        'min':2,
    //        'current_stock':70,
    //        'new_stock':0,
    //        'expire_date':'20 September 2017'
    //
    //    },{
    //        'lot_number':444,
    //        'gtn':456789,
    //        'item':'TT',
    //        'manufacturer':'GlaxoSmithKline',
    //        'max':3,
    //        'min':2,
    //        'current_stock':100,
    //        'new_stock':0,
    //        'expire_date':'20 April 2016'
    //
    //    },{
    //        'lot_number':555,
    //        'gtn':567890,
    //        'item':'TT',
    //        'manufacturer':'GlaxoSmithKline',
    //        'max':3,
    //        'min':2,
    //        'current_stock':300,
    //        'new_stock':0,
    //        'expire_date':'1 April 2015'
    //
    //    },{
    //        'lot_number':666,
    //        'gtn':678901,
    //        'item':'TT',
    //        'manufacturer':'GlaxoSmithKline',
    //        'max':3,
    //        'min':2,
    //        'current_stock':85,
    //        'new_stock':0,
    //        'expire_date':'31 May 2017'
    //
    //    },{
    //        'lot_number':777,
    //        'gtn':789012,
    //        'item':'TT',
    //        'manufacturer':'GlaxoSmithKline',
    //        'max':3,
    //        'min':2,
    //        'current_stock':5,
    //        'new_stock':0,
    //        'expire_date':'22 July 2017'
    //
    //    },
    //];
    //$scope.selectedPackage = {};
    //$scope.destinations = [
    //    {
    //        'name':'Arusha',
    //        'targets':12342,
    //        'expected_birth':2345,
    //        'expected_pregnancies':35434,
    //        'surviving_infants':234
    //    },
    //    {   'name':'Dar es salaam',
    //        'targets':56789,
    //        'expected_birth':123,
    //        'expected_pregnancies':456,
    //        'surviving_infants':788
    //    }];
    //$scope.ManufaturePackages = [];
    //    $scope.sent_to = false;
    //    $scope.description_scanned_package = false;
    //    $scope.voucher_numbers = false;
    //    $scope.description_scanned_item = false;
    //    $scope.scanned_package_lot = null;
    //$scope.internationalShipment = function(shipment){
    //
    //    if(shipment){
    //        $scope.ManufaturePackages.push(shipment);
    //    }else{
    //        $scope.ManufaturePackages = $scope.packages;
    //    }
    //
    //
    //
    //    $scope.internationalShipment = function(shipment){
    //        $scope.ManufaturePackages.push(shipment);
    //        $scope.$watch('ManufaturePackages', function() {
    //            $location.path("/package_arrivals");
    //        });
    //        //
    //    }
    //
    //}
    //
    //
    //
    //$scope.reset = function(shipment){
    //    $scope.shipment = {};
    //    console.log($scope.shipment);
    //}
    //$scope.scannedPackage = {};
    //$scope.arrival_1 = true;
    //$scope.scanningPackage = function(scannedPackage){
    //    var  check = false;
    //    $scope.voucher_numbers = false;
    //    $scope.message =null;
    //    $scope.description_scanned_item =false;
    //    angular.forEach($scope.packages,function(value,index){
    //        if(value.lot_number==scannedPackage){
    //            $scope.scannedPackage = value;
    //            $scope.description_scanned_package = true;
    //            check = true;
    //        }
    //
    //    });
    //    if(!check){
    //        $scope.description_scanned_package = false;
    //    }
        //if($scope.arrival_1){
        //    $scope.arrival_1 = false;
        //    $scope.arrival_2 = true;
        //    $scope.arrival_3 = false;
        //}
        //if($scope.arrival_2){
        //    $scope.arrival_1 = false;
        //    $scope.arrival_2 = false;
        //    $scope.arrival_3 = true;
        //}
        //if($scope.arrival_3){
        //    $scope.arrival_1 = false;
        //    $scope.arrival_2 = true;
        //    $scope.arrival_3 = false;
        //}

    //}
    //$scope.addingPackage = function(selectedPackage){
    //    $scope.description_scanned_package = false;
    //    $scope.voucher_numbers = true;
    //    $scope.description_scanned_item = true;
    //    $scope.message =null;
    //    $scope.selectedPackage =$scope.scannedPackage;
    //    $scope.selectedPackage.boxes = $scope.boxes;
    //    $scope.selectedPackage.new_stock = parseInt($scope.boxes)+parseInt($scope.selectedPackage.current_stock);
    //
    //}
    //$scope.cancelSendingPackage = function(selectedPackage){
    //    $scope.selectedPackage = {};
    //    $scope.message = "Sending Cancelled";
    //    $scope.sent_to = false;
    //    $scope.description_scanned_package = false;
    //    $scope.voucher_numbers = false;
    //    $scope.description_scanned_item = false;
    //}
    //$scope.confirmAndSendPackage = function(selectedPackage){
    //    console.log(selectedPackage);
    //    $scope.message = "Sent Successfully";
    //}
    //
    //$scope.expectation = function(){
    //    $scope.sent_to = true;
    //}
    //$scope.internationalShipment(null);
});


vaccine.controller("StockMenuController",function($scope, $location) {
    var dashboardMenuService = {};

    dashboardMenuService.tabs = [{header: 'ReceivePackage', content:'/public/pages/stock/index.html#/receive', name:'receive', closable:false, displayOrder: 0},
        {header: 'PreparePackage', content:'/public/pages/stock/index.html#/prepare', name:'prepare', closable:false, displayOrder: 1},
        {header: 'StockItems', content:'/public/pages/stock/index.html#/items', name:'items', closable: false, displayOrder: 2}];


    $scope.dashboardTabs = dashboardMenuService.tabs;

    $scope.$on('dashboardTabUpdated', function(){
        $scope.dashboardTabs = dashboardMenuService.tabs;
    });
    $scope.closeTab = function(tabName){
        dashboardMenuService.closeTab(tabName);
    };

});

services.factory('StockMenuService',function($rootScope,$location){
    var dashboardMenuService = {};

    dashboardMenuService.tabs = [{header: 'ReceivePackage', content:'/public/pages/stock/index.html#/receive', name:'receive', closable:false, displayOrder: 0},
        {header: 'PreparePackage', content:'/public/pages/stock/index.html#/prepare', name:'prepare', closable:false, displayOrder: 1},
        {header: 'StockItems', content:'/public/pages/stock/index.html#/items', name:'items', closable: false, displayOrder: 2}];


    dashboardMenuService.addTab = function(header, content, name, closable, displayOrder){
        var tab = isTabExists(name);
        var newTab = {header:header, content: content, name:name, closable:closable, displayOrder: displayOrder};

        if(_.isEqual(tab, newTab)){
            return;
        }
        if(_.isUndefined(tab)){
            dashboardMenuService.tabs.push(newTab);
        }else if(tab.name === newTab.name){ //replace
            dashboardMenuService.tabs[_.indexOf(dashboardMenuService.tabs,tab)] = newTab;
        }

        broadcastUpdate();
    };

    dashboardMenuService.closeTab = function(tabName){
        var tab = isTabExists(tabName);

        var closedTabIndex = _.indexOf(dashboardMenuService.tabs,tab);

        dashboardMenuService.tabs = _.reject(dashboardMenuService.tabs, function(tab){return tab.name === tabName && tab.closable === true;});

        var tabToShow = '';

        if(!isUndefined(tab)){

            var visibleTab;
            var nextTab = _.findWhere(dashboardMenuService.tabs,{displayOrder: tab.displayOrder + 1});

            if(nextTab !== undefined){
                visibleTab = nextTab.content;

            }else if(closedTabIndex > 0){
                visibleTab = dashboardMenuService.tabs[closedTabIndex-1].content;
            }

            tabToShow += visibleTab.slice(visibleTab.indexOf('#')+1);
        }

        broadcastUpdate();
        $location.path(tabToShow);
    };

    var broadcastUpdate = function(){
        $rootScope.$broadcast('dashboardTabUpdated');
    };

    var isTabExists = function(tabName){
        var tab = _.findWhere(dashboardMenuService.tabs, {name:tabName});
        if(_.isUndefined(tab)){
            return undefined;
        }
        return tab;
    };

    return dashboardMenuService;

});
