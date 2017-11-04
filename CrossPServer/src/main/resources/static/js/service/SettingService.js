'use strict';

var SettingServices = angular.module('setting.services', []);

SettingServices.factory('appService', ['$http', function($http) {

	function getUserApps(){
		 $http.get('/setting/app/user').success(function(apps){
	         return apps;
	     });
	}
	
	var userApps = getUserApps();	
	return {
		userApps: userApps
	}
}]);
 


