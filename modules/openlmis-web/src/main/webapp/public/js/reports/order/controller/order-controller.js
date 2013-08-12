function OrderReportController($scope, OrderReport, Products ,ReportFacilityTypes,GeographicZones,AllReportPeriods,ReportFilteredPeriods, $http,OperationYears, Months, ReportPrograms,AllFacilites,GetFacilityByFacilityType, $routeParams,$location) {
        //to minimize and maximize the filter section
        var section = 1;

        $scope.section = function (id) {
            section = id;
        };

        $scope.show = function (id) {
            return section == id;
        };
        // lookups and references

        $scope.pagingOptions = {
            pageSizes: [ 20, 40, 50, 100],
            pageSize: 20,
            totalServerItems: 0,
            currentPage: 1
        };

        $scope.orderTypes = [
            {'name':'Regular', 'value':'Regular'},
            {'name':'Emergency', 'value':'Emergency'}
         ];

        //Order type defaults to Regular
        $scope.orderType = 'Regular'

        // default to the monthly period type
        $scope.periodType = 'monthly';

        $scope.periodTypes = [
            {'name':'Monthly', 'value':'monthly'},
            {'name':'Quarterly', 'value':'quarterly'},
            {'name':'Semi Anual', 'value':'semi-anual'},
            {'name':'Annual', 'value':'annual'}
        ];
        $scope.startYears = [];
        OperationYears.get(function(data){
            $scope.startYears  = data.years;
        });

        Months.get(function(data){
            var months = data.months;

            if(months != null){
                $scope.startMonths = [];
                $scope.endMonths = [];
                $.each(months,function(idx,obj){
                    $scope.startMonths.push({'name':obj.toString(), 'value': idx+1});
                    $scope.endMonths.push({'name':obj.toString(), 'value': idx+1});
                });
            }

        });

        $scope.startQuarters = function(){
            return $scope.quarters;
        };

        $scope.endQuarters  = function(){
            if($scope.startYear == $scope.endYear && $scope.startQuarter != '' ){
                var arr = [];
                for(var i=$scope.startQuarter - 1; i < $scope.quarters.length;i++){
                    arr.push($scope.quarters[i]);
                }
                return arr;
            }
            return $scope.quarters;
        };

        $scope.quarters         = [
            {'name':'One','value':'1'},
            {'name':'Two','value':'2'},
            {'name':'Three','value':'3'},
            {'name':'Four','value':'4'}
        ];

        $scope.semiAnnuals= [
            {'name':'First Half','value':'1'},
            {'name':'Second Half','value':'2'}
        ];

        // copy over the start month and end months
        // this is just for initial loading.
        $(function (){
            $scope.startQuarters  = $scope.quarters;
            $scope.endQuarters  = $scope.quarters;
            $scope.endYears     = $scope.startYears;
            $scope.startSemiAnnuals = $scope.semiAnnuals;
            $scope.endSemiAnnuals = $scope.semiAnnuals;
            $scope.toQuarter = 1;
            $scope.fromQuarter = 1;
            $scope.startHalf = 1;
            $scope.endHalf = 1;
        });

        $scope.isMonthly = function(){
            return $scope.periodType == 'monthly';
        };

        $scope.isQuarterly = function(){
            return $scope.periodType == 'quarterly';
        };

        $scope.isSemiAnnualy  = function(){
            return $scope.periodType == 'semi-anual';
        };


    $scope.filterGrid = function (){
           $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        };

        //filter form data section
        $scope.filterOptions = {
            period:$scope.period,
            filterText: "",
            useExternalFilter: false
        };



        //filter form data section
        $scope.filterObject =  {
             facilityTypeId : $scope.facilityType,
             facilityType : "",
             periodType: $scope.periodType,
             fromYear: $scope.fromYear,
             fromMonth: $scope.fromMonth,
             fromQuarter: $scope.fromQuarter,
             fromSemiAnnual:$scope.startHalf,
             toYear: $scope.toYear,
             toMonth: $scope.toMonth,
             toQuarter: $scope.toQuarter,
             toSemiAnnual:$scope.endHalf,
             programId : $scope.program,
             periodId : $scope.period,
             zoneId : $scope.zone,
             productId : $scope.productId,
             scheduleId : $scope.schedule,
             rgroupId : $scope.rgroup,
             rgroup : "",
             facilityName : $scope.facilityNameFilter,
             facilityId: $scope.facility,
             orderType: ""
        };

        ReportPrograms.get(function(data){
            $scope.programs = data.programs;
            $scope.programs.push({'name':'Select a Program'});
        });

        ReportFacilityTypes.get(function(data) {
            $scope.facilityTypes = data.facilityTypes;
            $scope.facilityTypes.push({'name': 'All Facility Types', 'id' : 'All'});
        });

        AllFacilites.get(function(data){
            $scope.allFacilities = data.allFacilities;
        });


        Products.get(function(data){
            $scope.products = data.productList;
            $scope.products.push({'name': 'All Products','id':'All'});
        });

        GeographicZones.get(function(data) {
            $scope.zones = data.zones;
            $scope.zones.push({'name': '- All Zones -', 'id' : 'All'});
        });

        AllReportPeriods.get(function(data) {
            $scope.periods = data.periods;
            $scope.periods.push({'name': 'Select Period'});
        });

        $scope.ChangeReportingPeriods = function(){
            var params  = {};

            $.each($scope.filterObject, function(index, value) {

                params[index] = value;
            });

            ReportFilteredPeriods.get(params, function(data) {
                $scope.periods = data.periods;
            });
        }

        $scope.$watch('facilityType', function(selection){
            if(selection == "All"){
                $scope.filterObject.facilityTypeId =  -1;
            }else if(selection != undefined || selection == ""){
                $scope.filterObject.facilityTypeId =  selection;
                $.each( $scope.facilityTypes,function( item,idx){
                    if(idx.id == selection){
                        $scope.filterObject.facilityType = idx.name;
                    }
                });
            }else{
                $scope.filterObject.facilityTypeId =  0;
            }
            if($scope.filterObject.facilityTypeId !== -1 && $scope.filterObject.facilityTypeId !== 0){

                $scope.ChangeFacility();
            }
           $scope.filterGrid();
        });

        $scope.ChangeFacility = function(){
            GetFacilityByFacilityType.get({ facilityTypeId : $scope.filterObject.facilityTypeId },function(data) {
                $scope.allFacilities =  data.facilities;
            });
        };

    $scope.$watch('facility', function(selection){
        if(selection != undefined || selection == ""){
            $scope.filterObject.facilityId =  selection;
            $.each( $scope.allFacilities,function( item,idx){
                if(idx.id == selection){
                    $scope.filterObject.facilityName = idx.name;
                }
            });
        }else{
            $scope.filterObject.facilityId =  0;
        }
         $scope.filterGrid();
    });


    $scope.$watch('orderType', function(selection){
            if(selection != undefined || selection == ""){
                $scope.filterObject.orderType =  selection;

            }else{
                $scope.filterObject.orderType = "";
            }
            $scope.filterGrid();
        });

        $scope.$watch('facilityNameFilter', function(selection){
            if(selection != undefined || selection == ""){
                $scope.filterObject.facilityName =  selection;

            }else{
                $scope.filterObject.facilityName = "";
            }
            $scope.filterGrid();
        });

        $scope.$watch('product', function(selection){
            if(selection == "All"){
                $scope.filterObject.productId =  -1;
            }else if(selection != undefined || selection == ""){
                $scope.filterObject.productId =  selection;
            }else{
                $scope.filterObject.productId =  0;
            }
            $scope.filterGrid();
        });


        $scope.$watch('zone', function(selection){
            if(selection == "All"){
                $scope.filterObject.zoneId =  -1;
            }else if(selection != undefined || selection == ""){
                $scope.filterObject.zoneId =  selection;
            }else{
                $scope.filterObject.zoneId =  0;
            }
            $scope.filterGrid();
        });

        $scope.$watch('program', function(selection){
            if(selection == "All"){
                $scope.filterObject.programId =  -1;
            }else if(selection != undefined || selection == ""){
                $scope.filterObject.programId =  selection;
            }else{
                $scope.filterObject.programId =  0;
            }
            $scope.filterGrid();
        });

        $scope.$watch('period', function(selection){
            if(selection == "All"){
                $scope.filterObject.periodId =  -1;
            }else if(selection != undefined || selection == ""){
                $scope.filterObject.periodId =  selection;
                $.each( $scope.periods,function( item,idx){
                    if(idx.id == selection){
                        $scope.filterObject.period = idx.name;
                    }
                });

            }else{
                $scope.filterObject.periodId =  0;
            }
            $scope.filterGrid();
        });

        $scope.$watch('startYear', function(selection){
            var date = new Date();
            if(selection != undefined || selection == ""){
                $scope.filterObject.fromYear =  selection;
                adjustEndYears();
                adjustEndMonths();
                adjustEndQuarters();
                adjustEndSemiAnnuals();
            }else{
                $scope.startYear  = date.getFullYear().toString();
                $scope.filterObject.fromYear =  date.getFullYear();
            }
            $scope.ChangeReportingPeriods();
            $scope.filterGrid();
        });

        $scope.$watch('endYear', function(selection){
            var date = new Date();
            if(selection != undefined || selection == ""){
                $scope.filterObject.toYear =  selection;
                adjustEndMonths();
                adjustEndQuarters();
                adjustEndSemiAnnuals();
            }else{
                $scope.endYear  = date.getFullYear().toString();
                $scope.filterObject.toYear =  date.getFullYear();
            }
            $scope.ChangeReportingPeriods();
            $scope.filterGrid();
        });

        $scope.$watch('startQuarter', function(selection){
            var date = new Date();
            if(selection != undefined || selection == ""){
                $scope.filterObject.fromQuarter =  selection;
                adjustEndQuarters();
            }else{
                var date = new Date();
                $scope.filterObject.fromQuarter =  1;
            }
            $scope.ChangeReportingPeriods();
            $scope.filterGrid();
        });

        $scope.$watch('endQuarter', function(selection){
            var date = new Date();
            if(selection != undefined || selection == ""){
                $scope.filterObject.toQuarter =  selection;
            }else{
                var date = new Date();
                $scope.filterObject.toQuarter =  $scope.filterObject.fromQuarter;
            }
            $scope.ChangeReportingPeriods();
            $scope.filterGrid();
        });

        $scope.$watch('startHalf', function(selection){

            if(selection != undefined || selection == ""){
                $scope.filterObject.fromSemiAnnual =  selection;
                adjustEndSemiAnnuals();
            }else{
                $scope.filterObject.fromSemiAnnual =  1;
            }
            $scope.ChangeReportingPeriods();
            $scope.filterGrid();
        });
        $scope.$watch('endHalf', function(selection){

            if(selection != undefined || selection == ""){
                $scope.filterObject.toSemiAnnual =  selection;
            }else{
                var date = new Date();
                $scope.filterObject.toSemiAnnual =  1;
            }
            $scope.ChangeReportingPeriods();
            $scope.filterGrid();
        });
        $scope.$watch('startMonth', function(selection){
            var date = new Date();
            if(selection != undefined || selection == ""){
                $scope.filterObject.fromMonth =  selection-1;
                adjustEndMonths();
            }else{
                $scope.startMonth = (date.getMonth()+1 ).toString();
                $scope.filterObject.fromMonth =  (date.getMonth()+1);
            }
            $scope.ChangeReportingPeriods();
            $scope.filterGrid();
        });

        $scope.$watch('endMonth', function(selection){
            var date = new Date();
            if(selection != undefined || selection == ""){
                $scope.filterObject.toMonth =  selection-1;
            }else{
                $scope.endMonth = (date.getMonth() +1 ).toString();
                $scope.filterObject.toMonth =  (date.getMonth()+1);
            }
            $scope.ChangeReportingPeriods();
            $scope.filterGrid();
        });

        var adjustEndMonths = function(){
            if($scope.startMonth != undefined && $scope.startMonths != undefined && $scope.startYear == $scope.endYear ){
                $scope.endMonths = [];
                $.each($scope.startMonths,function(idx,obj){
                    if(obj.value >= $scope.startMonth){
                        $scope.endMonths.push({'name':obj.name, 'value': obj.value});
                    }
                });
                if($scope.endMonth < $scope.startMonth){
                    $scope.endMonth = $scope.startMonth;
                }
            }else{
                $scope.endMonths = $scope.startMonths;
            }
        }

        var adjustEndQuarters = function(){
            if($scope.startYear == $scope.endYear){
                $scope.endQuarters = [];
                $.each($scope.startQuarters, function(idx,obj){
                    if(obj.value >= $scope.startQuarter){
                        $scope.endQuarters.push({'name':obj.name, 'value': obj.value});
                    }
                });
                if($scope.endQuarter < $scope.startQuarter){
                    $scope.endQuarter =  $scope.startQuarter;
                }
            }else{
                $scope.endQuarters = $scope.startQuarters;
            }
        }

        var adjustEndSemiAnnuals = function(){

            if($scope.startYear == $scope.endYear){
                $scope.endSemiAnnuals = [];
                $.each($scope.startSemiAnnuals, function(idx,obj){
                    if(obj.value >= $scope.startHalf){
                        $scope.endSemiAnnuals.push({'name':obj.name, 'value': obj.value});
                    }
                });
                if($scope.endHalf < $scope.startHalf){
                    $scope.endHalf =  $scope.startHalf;
                }
            }else{
                $scope.endSemiAnnuals = $scope.startSemiAnnuals;
            }
        }

        var adjustEndYears = function(){
            $scope.endYears = [];
            $.each( $scope.startYears,function( idx,obj){
                if(obj >= $scope.startYear){
                    $scope.endYears.push(obj);
                }
            });
            if($scope.endYear < $scope.startYear){
                $scope.endYear  = new Date().getFullYear();
            }
        }


        $scope.$watch('periodType', function(selection){
            if(selection != undefined || selection == ""){
                $scope.filterObject.periodType =  selection;

            }else{
                $scope.filterObject.periodType =  "monthly";
            }
            $scope.ChangeReportingPeriods();
            $scope.filterGrid();
        });



    $scope.currentPage = ($routeParams.page) ? parseInt($routeParams.page) || 1 : 1;

        $scope.exportReport   = function (type){
            $scope.filterObject.pdformat =1;
            var params = jQuery.param($scope.filterObject);
            var url = '/reports/download/order_summary/' + type +'?' + params;
            window.open(url);

        }

        $scope.goToPage = function (page, event) {
            angular.element(event.target).parents(".dropdown").click();
            $location.search('page', page);
        };

        $scope.$watch("currentPage", function () {  //good watch no problem

            if($scope.currentPage != undefined && $scope.currentPage != 1){
              //when clicked using the links they have done updated the paging info no problem here
               //or using the url page param
              //$scope.pagingOptions.currentPage = $scope.currentPage;
                $location.search("page", $scope.currentPage);
            }
        });

        $scope.$on('$routeUpdate', function () {
            if (!utils.isValidPage($routeParams.page, $scope.numberOfPages)) {
                $location.search('page', 1);
                return;
            }
        });


        $scope.sortInfo = { fields:["code","facilityType"], directions: ["ASC"]};

        $scope.setPagingData = function(data, page, pageSize, total){
            $scope.myData = data;
            $scope.pagingOptions.totalServerItems = total;
            $scope.numberOfPages = ( Math.ceil( total / pageSize))  ? Math.ceil( total / pageSize) : 1 ;

        };

        $scope.getPagedDataAsync = function (pageSize, page) {
                        var params  = {};
                        if(pageSize != undefined && page != undefined ){
                            var params =  {
                                "max" : pageSize,
                                "page" : page
                            };
                        }

                        $.each($scope.filterObject, function(index, value) {
                            //if(value != undefined)
                                params[index] = value;
                        });


                        // put out the sort order
                        $.each($scope.sortInfo.fields, function(index, value) {
                            if(value != undefined) {
                                params['sort-' + $scope.sortInfo.fields[index]] = $scope.sortInfo.directions[index];
                            }
                        });

                        OrderReport.get(params, function(data) {
                        $scope.setPagingData(data.pages.rows,page,pageSize,data.pages.total);
                        });

        };

        $scope.$watch('pagingOptions.currentPage', function () {
            $scope.currentPage = $scope.pagingOptions.currentPage;
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }, true);

        $scope.$watch('pagingOptions.pageSize', function () {
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
        }, true);

        $scope.$watch('sortInfo', function () {

            $.each($scope.sortInfo.fields, function(index, value) {
                if(value != undefined)
                    $scope.filterObject[$scope.sortInfo.fields[index]] = $scope.sortInfo.directions[index];
            });
            $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }, true);
    $scope.formatNumber = function(value){
        return utils.formatNumber(value,'0,0.00');
    }
    $scope.gridOptions = {
        data: 'myData',
        columnDefs:
            [

                { field: 'productCode', displayName: 'Product Code', width: "*", resizable: false},
                { field: 'description', displayName: 'Description', width: "***" },
                { field: 'facilityName', displayName: 'Facility', width: "**" },
                { field: 'unitSize', displayName: 'Unit Size', width : "*", cellTemplate: '<div class="ngCellText" style="text-align:right;" ng-class="col.colIndex()"><span ng-cell-text>{{formatNumber(COL_FIELD)}}</span></div>'},
                { field: 'unitQuantity', displayName: 'Unit Quantity', width : "*", cellTemplate: '<div class="ngCellText" style="text-align:right;" ng-class="col.colIndex()"><span ng-cell-text>{{formatNumber(COL_FIELD)}}</span></div>'},
                { field: 'packQuantity', displayName: 'Pack Quantity', width : "*", cellTemplate: '<div class="ngCellText" style="text-align:right;" ng-class="col.colIndex()"><span ng-cell-text>{{formatNumber(COL_FIELD)}}</span></div>'},
                { field: 'discrepancy', displayName: 'Discrepancy or Damages', width : "*"}


            ],
        enablePaging: true,
        enableSorting :true,
        showFooter: true,
        selectWithCheckboxOnly :false,
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions,
        useExternalSorting: true,
        sortInfo: $scope.sortInfo,
        showColumnMenu: true,
        enableRowReordering: true,
        showFilter: true,
        plugins: [new ngGridFlexibleHeightPlugin()]

    };

}
