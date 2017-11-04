'use strict';

/**
 * SettingController
 * @constructor
 */
var AppSpaceController = function($scope, $http) {
	
	$scope.hiddenShow= false;
    $scope.mySpaces = {};
    $scope.appSpace = {};
    $scope.templates = {};
    
    $scope.fetchAppSpaces = function() {
      	 $http.get('/setting/app/space/all').success(function(spaces){
               $scope.mySpaces = spaces;
           });
   	}
    
    $scope.fetchAppTemplates = function() {
     	 $http.get('/setting/app/template/conf/all').success(function(templates){
              $scope.templates = templates;
          });
  	}
    
    $scope.save = function(app) {
    	$http.post('/setting/app/space/add', app).success(function() {
    		$scope.msg = "Add Successful!";
    		$scope.fetchAppSpaces();
        }).error(function() {
            $scope.msg = 'Could not add a new application';
        });
    	
	}
    
    $scope.editAppSpace = function(appSpace) {
    	$scope.hiddenShow = true;    	
    	$scope.appSpace = appSpace;
  	}
    
    $scope.removeAppSpace = function(id) {
    	$http.delete('/setting/app/space/' + id).success(function() {
    		$scope.fetchAppSpaces();
        });
	}
    
    $scope.fetchAppTemplates();
    $scope.fetchAppSpaces();    
};