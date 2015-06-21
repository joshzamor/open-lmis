/*
 * This program is part of the OpenLMIS logistics management information system platform software.
 * Copyright © 2013 VillageReach
 *
 *  This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 *  You should have received a copy of the GNU Affero General Public License along with this program.  If not, see http://www.gnu.org/licenses.  For additional information contact info@OpenLMIS.org.
 */

function ReportsListController($scope, ngTableParams, $rootScope, PublicSiteData) {

         //A static data for List of reports in the project
        $scope.data = $scope.datarows =
            [
                { category:'Admin', name: 'Facility List', desription: '', roles: '' },
                { category:'Admin', name: 'User Summary', desription: '', roles: '' },

                { category:'Consumption', name: 'Aggregate Consumption', desription: '', roles: '' },
                { category:'Consumption', name: 'Consumption Average by Product', desription: '', roles: '' },
                { category:'Consumption', name: 'District Consumption Comparision', desription: '', roles: '' },

                { category:'Equipment Reports', name: 'Lab Equipment list', desription: '', roles: '' },
                { category:'Equipment Reports', name: 'Lab Equipments by Funding Source', desription: '', roles: '' },
                { category:'Equipment Reports', name: 'Lab Equipments by location', desription: '', roles: '' },

                { category:'Order Fulfillment', name: 'District Order Compilation', desription: '', roles: '' },
                { category:'Order Fulfillment', name: 'Order Fill Rate Report Summary', desription: '', roles: '' },
                { category:'Order Fulfillment', name: 'Order Fill Rate Report by Facility', desription: '', roles: '' },
                { category:'Order Fulfillment', name: 'Order Report', desription: '', roles: '' },
                { category:'Order Fulfillment', name: 'Report and Requisition Feedback', desription: '', roles: '' },
                { category:'Order Fulfillment', name: 'Seasonality/Rationing Adjustments', desription: '', roles: '' },

                { category:'Regimen Report', name: 'Aggregate Regimen', desription: '', roles: '' },
                { category:'Regimen Report', name: 'Regimen Summary', desription: '', roles: '' },
                { category:'Regimen Report', name: 'Regimen Distribution by District', desription: '', roles: '' },

                { category:'Report Status', name: 'Non Reporting Facilities', desription: '', roles: '' },
                { category:'Report Status', name: 'Reporting Rate', desription: '', roles: '' },

                { category:'Stock Keeping', name: 'Adjustment Summary', desription: '', roles: '' },
                { category:'Stock Keeping', name: 'Stocked Out', desription: '', roles: '' },
                { category:'Stock Keeping', name: 'Stock Imbalance by Facility', desription: '', roles: '' },
                { category:'Stock Keeping', name: 'Summary Report', desription: '', roles: '' },
                { category:'Stock Keeping', name: 'Supply Status by Facility', desription: '', roles: '' },
                { category:'Stock Keeping', name: 'Stock Status By Location', desription: '', roles: '' }
            ];

            $scope.tableParams = new ngTableParams({
                sorting: {
                    category: 'asc'
                }
            }, {
                getData: function($defer, params) {
                    $scope.datarows = $filter('orderBy')($scope.datarows, params.orderBy());
                    $defer.resolve($scope.datarows);
                }
            });

}