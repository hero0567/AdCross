'use strict';

/**
 * SettingController
 * @constructor
 */
var CrossPController = function($scope, $http) {
	
    $scope.unJoinApps = {};
    $scope.myApps = {};
    $scope.sltApp = {};
    
    $scope.fetchUserApps = function() {
   	 $http.get('/setting/app/user').success(function(apps){
            $scope.myApps = apps;
            if (apps.length > 0) {
	           	$scope.sltApp = apps[0];
	           	$scope.fetchUnJoinCPApps();
			 }
        });
	}    
    
    $scope.fetchUnJoinCPApps = function() {
	   	 $http.get('/setting/app/cp/unjoin/' + $scope.sltApp.id).success(function(apps){
	            $scope.unJoinApps = apps;
	   	 });
	}
    
    $scope.joinCrossP = function(id) {
    	$http.post('/setting/app/cp/'+ $scope.sltApp.id + '/' + id).success(function() {
    		$scope.msg = "Add Successful!";
    		$scope.fetchUnJoinCPApps();
        }).error(function() {
            $scope.msg = 'Could not add a new application';
        });
	}    
    
    $scope.fetchUserApps();    
};