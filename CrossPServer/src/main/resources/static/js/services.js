'use strict';

/* Services */

var AppServices = angular.module('AngularSpringApp.services', []);

AppServices.value('version', '0.1');

AppServices.factory('dataServices', [ function() {
	/// For getting storage disks
	function getDisks(){
		var disks = [
                     {name:"500B83DEF", type:"System", status:"n/a", allocations:"None", unallocated:"-", total:"930.39 GB"},
                     {name:"74B83DEF", type:"Base", status:"In Use", allocations:"None", unallocated:"-", total:"454 TB"}
                     ];
		return disks;
	}
	
	return {
		disks: disks
	}
}]);
 
