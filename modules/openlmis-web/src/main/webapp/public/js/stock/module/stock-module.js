<<<<<<< HEAD
/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2013 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */
var vaccine = angular.module('vaccineStock', ['openlmis', 'ngTable','ui.bootstrap','nsPopover']).config(['$routeProvider', function ($routeProvider) {

    $routeProvider.
		when('/home', {controller: stockMainController, templateUrl: 'partials/stockHome.html'}).
        otherwise({redirectTo: '/receive'});
}]).directive('onKeyup', function () {
        return function (scope, elm, attrs) {
            elm.bind("keyup", function () {
                scope.$apply(attrs.onKeyup);
            });
        };
});
vaccine.controller("VaccineModule",function($scope,$http){
	$http.get('/stock/vaccine/all').
	  success(function(data, status, headers, config) {
		  console.log("Good:" + data);
	  }).
	  error(function(data) {
	    console.log("Error:" + data);
	  });
});
=======
/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2013 VillageReach
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *  
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org. 
 */
var vaccine = angular.module('vaccine', ['openlmis', 'ngTable','ui.bootstrap','nsPopover']).config(['$routeProvider', function ($routeProvider) {

    $routeProvider.
        otherwise({redirectTo: '/receive'});
}]).directive('onKeyup', function () {
        return function (scope, elm, attrs) {
            elm.bind("keyup", function () {
                scope.$apply(attrs.onKeyup);
            });
        };
});
vaccine.controller("VaccineModule",function($scope,$http){
	alert("here");
	$http.get('/stock/vaccine/all').
	  success(function(data, status, headers, config) {
		  console.log("Good:" + JSON.stringify(data));
	  }).
	  error(function(data) {
	    console.log("Error:" + data);
	  });
});
>>>>>>> 011867fb4d4c88b0c1e10f9496643a6b1b688747
