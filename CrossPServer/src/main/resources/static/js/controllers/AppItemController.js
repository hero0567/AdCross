'use strict';

/**
 * SettingController
 * @constructor
 */
var AppItemController = function($scope, $http) {
	
	$scope.hiddenShow= false;
    $scope.myItems = {};
    $scope.item= {};
    
    $scope.fetchAppItems = function() {
      	 $http.get('/setting/app/item/all').success(function(items){
               $scope.myItems = items;
           });
   	}
    
    $scope.saveAppItem = function(item) {
    	$http.post('/setting/app/item/add', item).success(function() {
    		$scope.msg = "Add Successful!";
    		$scope.fetchAppItems();
        }).error(function() {
            $scope.msg = 'Could not add a new application';
        });
    	
	}
    
    $scope.editAppItem = function(item) {
    	$scope.hiddenShow = true;    	
    	$scope.item = item;
  	}
    
    $scope.removeAppItem = function(id) {
    	$http.delete('/setting/app/item/' + id).success(function() {
    		$scope.fetchAppItems();
        });
	}
    
    $scope.fetchAppItems();    
};