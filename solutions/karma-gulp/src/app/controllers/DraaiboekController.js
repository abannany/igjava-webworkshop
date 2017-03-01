(function () {
    'use strict';

    angular.module('myApp')
        .controller('DraaiboekController', DraaiboekController);

    function DraaiboekController($scope, DraaiboekService) {
        $scope.draaiboekService = DraaiboekService;
    }
})();