
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
        // this route is responding to
        .when('/internation_arrival', {
            templateUrl: "/public/pages/stock/partials/international_shipment.html",
            controller: "StockModuleController"
        })
        // this route is responding topackage arrival route
        .when('/package_arrivals', {
            templateUrl: "/public/pages/stock/partials/package_arrival.html",
            controller: "StockModuleController"
        })
        // this route is responding to items route
        .when('/items', {
            templateUrl: "/public/pages/stock/partials/stock_items.html",
            controller: "StockModuleController"
        })
        // this route is responding to prepare  route
        .when('/prepare', {
            templateUrl: "/public/pages/stock/partials/prepare.html",
            controller: "StockModuleController"
        })
        // this route is responding to sending package
        .when('/sending_package', {
            templateUrl: "/public/pages/stock/partials/sending_package.html",
            controller: "StockModuleController"
        })
        // this route is responding to confirm  route
        .when('/confirm', {
            templateUrl: "/public/pages/stock/partials/confirm_reception.html",
            controller: "StockModuleController"
        })
        // this route is responding to receive route
        .when('/receive', {
            templateUrl: "/public/pages/stock/partials/receive.html",
            controller: "StockModuleController"
        })
        // this route is responding to home route
        .when('/home', {
            templateUrl: "/public/pages/stock/partials/stockHome.html",
            controller: "StockModuleController"
        })
       // this route is responding to pre advice route
        .when('/preadvice', {
            templateUrl: "/public/pages/stock/partials/preadvice.html",
            controller: "StockModuleController"
        })
        // this route is responding to scan package route
        .when('/', {
            templateUrl: "/public/pages/stock/partials/scan_package.html",
            controller: "StockModuleController"
        })
        // this route is responding to  any link other than registered
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

    // temporary api call to pull packages
    $http.get('/public/pages/stock/dummData/manufacturerpackage.json').
        success(function(data, status, headers, config) {
            $scope.packagesJson = data.manufacture_packages;
        }).
        error(function(data) {
            console.log("Error:" + data);
        });

    // HANDLING PRE ADVICE MENU
    $scope.tabToggle = function(menu){
        $scope.container = menu;
        if(menu=="all"){
            $scope.all = true;
            $scope.pending = false;
            $scope.received = false;
            $scope.viewForm = false;
            $scope.viewTable = true;
        }
        if(menu=="received"){
            $scope.all = false;
            $scope.pending = false;
            $scope.received = true;
            $scope.viewForm = false;
            $scope.viewTable = true;
        }
        if(menu=="pending"){
            $scope.all = false;
            $scope.pending = true;
            $scope.received = false;
            $scope.viewForm = false;
            $scope.viewTable = true;
        }
        console.log(menu);
    }
    $scope.tabToggle('all');

    // table between add form and table
    $scope.viewTable = true;
    $scope.toggleFormAndTable = function(tobeSeen){
        if(tobeSeen=="addNew"){
            $scope.viewForm = true;
            $scope.viewTable = false;
        }
        if(tobeSeen=="cancelAdd"){
            $scope.viewForm = false;
            $scope.viewTable = true;
        }
    }

    // adding package
    $scope.package = null;
    $scope.addPackage = function(data){
        var d = new Date();
        //var expire_date = new Date(data.expire_date);
        var manufacture_date = new Date(data.manufacture_date);
        //expire_date = Date.format(expire_date,"dd-m-yy");
        manufacture_date = d.fromFormattedString(manufacture_date,"dd-m-yy");

        var newObject  = {
            "delivery_status": 'pending',
            "expire_date":expire_date,
            "id": 1,
            "lot_number":data.lot_number ,
            "manufacture_date": manufacture_date,
            "number_of_doses":data.number_of_doses ,
            "purchasing_order_number":"order",
            "shipment_id":data ,
            "vaccine_packaging": null,
            "vaccine_packaging_id": null
        }

        console.log(newObject);
        console.log($scope.packagesJson);
        $scope.packagesJson.push(newObject);
        console.log($scope.packagesJson);
    }

    // cancell package adding
    $scope.cancelAddpackage = function(data){
        $scope.package = null;
        $scope.digest();
    }

    // action to scan package
    var scan_package_button =angular.element('#scan_package_button');
    scan_package_button.bind("click", function(){
        var shipping_number =angular.element('#shipping_number').val();
        var link = encodeURI("/public/pages/stock/index.html#/"+"receive?sscc="+shipping_number);
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

});


vaccine.controller("StockMenuController",function($scope, $location) {
    var dashboardMenuService = {};

    dashboardMenuService.tabs = [
        {header: 'PreAdvice', content:'/public/pages/stock/index.html#/preadvice', name:'preadvice', closable:false, displayOrder: 0},
        {header: 'ReceivePackage', content:'/public/pages/stock/index.html#/receive', name:'receive', closable:false, displayOrder: 0},
        {header: 'PreparePackage', content:'/public/pages/stock/index.html#/prepare', name:'prepare', closable:false, displayOrder: 1},
        {header: 'StockItems', content:'/public/pages/stock/index.html#/items', name:'items', closable: false, displayOrder: 2}
    ];


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

    dashboardMenuService.tabs = [
        {header: 'PreAdvice', content:'/public/pages/stock/index.html#/preadvice', name:'preadvice', closable:false, displayOrder: 0},
        {header: 'ReceivePackage', content:'/public/pages/stock/index.html#/receive', name:'receive', closable:false, displayOrder: 0},
        {header: 'PreparePackage', content:'/public/pages/stock/index.html#/prepare', name:'prepare', closable:false, displayOrder: 1},
        {header: 'StockItems', content:'/public/pages/stock/index.html#/items', name:'items', closable: false, displayOrder: 2}
    ];

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
