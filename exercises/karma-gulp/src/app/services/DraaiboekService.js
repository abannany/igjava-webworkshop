(function () {
    'use strict';

    angular.module('myApp')
        .service('DraaiboekService', DraaiboekService);

    function DraaiboekService() {
        var thisService = this;
        thisService.draaiboekData = DraaiboekData;
        
        thisService.getDraaiboek = function () {
            thisService.sheetNumber = 0;
            thisService.draaiboekData.forEach(function (item, index) {
                var sheets = ""
                for (var i = 0; i < item.aantaalSheets; i++) {
                    thisService.sheetNumber = thisService.sheetNumber + 1;
                    sheets = sheets + "" + thisService.sheetNumber + " "
                }
                item.sheets = sheets
            });
            return thisService.draaiboekData
        };
    }
})();