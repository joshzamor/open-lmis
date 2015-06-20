/*
 * This program was produced for the U.S. Agency for International Development. It was prepared by the USAID | DELIVER PROJECT, Task Order 4. It is part of a project which utilizes code originally licensed under the terms of the Mozilla Public License (MPL) v2 and therefore is licensed under MPL v2 or later.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the Mozilla Public License as published by the Mozilla Foundation, either version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the Mozilla Public License for more details.
 *
 * You should have received a copy of the Mozilla Public License along with this program. If not, see http://www.mozilla.org/MPL/
 */

angular.module('setting', ['openlmis', 'textAngular', 'ui.bootstrap', 'ui.bootstrap.modal', 'ui.bootstrap.dialog', 'ui.bootstrap.tabs']).config(['$routeProvider','$provide', function ($routeProvider, $provide) {

  $provide.decorator('taOptions', ['$delegate', function (taOptions) {

    taOptions.toolbar = [
      ['h1', 'h2', 'h3'],
      ['bold', 'italics', 'underline'],['ul', 'ol',  'clear'],
      ['html', 'insertImage', 'insertLink',],
      ['justifyLeft', 'justifyCenter', 'justifyRight']
    ];
    taOptions.classes = {
      focussed: 'focussed',
      toolbar: 'btn-toolbar',
      toolbarGroup: 'btn-group',
      toolbarButton: 'btn btn-default',
      toolbarButtonActive: 'active',
      disabled: 'disabled',
      textEditor: 'form-control',
      htmlEditor: 'form-control'
    };
    return taOptions;
  }]);

  $routeProvider.
    when('/list', {controller: ListSettingController, templateUrl: 'partials/list.html'}).
    otherwise({redirectTo: '/list'});
}]).run(function ($rootScope, AuthorizationService) {
  $rootScope.roleSelected = "selected";
  AuthorizationService.preAuthorize('MANAGE_SETTING');
});


app.directive('setting', function ($compile, $http, $templateCache) {

  var getTemplate = function (contentType) {
    var templateLoader,
      baseUrl = '/public/pages/admin/setting/templates/',
      templateMap = {
        TEXT: 'text.html',
        HTML: 'html.html',
        TEXT_AREA: 'text-area.html',
        NUMBER: 'number.html',
        OPTIONS: 'options.html',
        BOOLEAN: 'boolean.html',
        EMAIL: 'email.html'
      };

    var templateUrl = baseUrl + templateMap[contentType];
    templateLoader = $http.get(templateUrl, {cache: $templateCache});

    return templateLoader;
  };

  var linker = function (scope, element, attrs) {
    var loader = getTemplate(scope.content.valueType);
    var promise = loader.success(function (html) {
      element.html(html);
    }).then(function (response) {
      element.replaceWith($compile(element.html())(scope));
    });
  };

  return {
    restrict: "E",
    rep1ace: true,
    link: linker,
    scope: {
      content: '=ngModel'
    }
  };
});