
<!DOCTYPE html>
<html >
<head>
<title>Setting Home Page</title>
</head>
<body ng-app="SettingModule" ng-controller="CrossPController">
  <div class="container">
  	<%@include file="../header.jsp"%>
    <div class="content">
    	<div style="width: 150px;float: left;"><%@include file="setting_menu.jsp"%></div>
       	<div style="float: left;margin-left: 20px;">
       		<div><h4>Welcome to Cross Promotion Application Management Page</h4></div>
       		<div ng-show="unJoinApps.length == 0">Sorry! This no application!</div>
       		<div style="margin: 9px 9px 9px 815px;"> My Apps:
             <select ng-model="sltApp" ng-change="fetchUnJoinCPApps()" ng-options="m.name for m in myApps">
            </select>
          </div>
       		<div style="width: 965px;">       		       		
	       		<table class="table table-bordered table-striped ng-table"
				        ng-show="unJoinApps.length > 0">
				        <thead>
				          <tr>
				            <th>ID</th>
				            <th>Name</th>
				            <th>Location</th>
				            <th>OS</th>
				            <th>OS Version</th>
				            <th>Policy</th>
				            <th>Content</th>				            
				            <th>Operate</th>
				          </tr>
				        </thead>
				        <tbody>
				          <tr ng-repeat="app in unJoinApps">
				            <td>{{app.id}}</td>
				            <td>{{app.name}}</td>
				            <td>{{app.location}}</td>				            
				            <td>{{app.os}}</td>
				            <td>{{app.osVersion}}</td>
				            <td>{{app.policy}}</td>
				            <td>{{app.content}}</td>
				            <th>
				               <button type="button" class="btn btn-default" ng-click="joinCrossP(app.id)">Join</button>
				            </th>
				          </tr>
				        </tbody>
				   </table>
			   </div>
       	</div>   
    </div>
  </div>
</body>

<script src="../../../js/lib/angular/angular.js"></script>
<script src="../../../js/controllers/CrossPController.js"></script>
<script src="../../../js/app.js"></script>
</html>