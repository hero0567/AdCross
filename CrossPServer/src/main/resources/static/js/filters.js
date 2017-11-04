'use strict';

/* Filters */

var AppFilters = angular.module('AngularSpringApp.filters', []);

AppFilters.filter('interpolate', ['version', '$window', 'logService', function (version, win, logService) {	 
	logService.log.info('filters.js: ');
    return function (text) {
        return String(text).replace(/\%VERSION\%/mg, version);
    }
}]);
