'use strict';

/* Directives */

var UploadModule = angular.module('UploadModule.directives', []);

UploadModule.directive('fileInput',['$parse',function($parse){
	return {
		restrict : 'A',
		link : function(scope,elm,attrs){
			elm.bind('change',function(){
				$parse(attrs.fileInput)
				.assign(scope,elm[0].files)
				scope.$apply()
			})
		}
	}
}]);