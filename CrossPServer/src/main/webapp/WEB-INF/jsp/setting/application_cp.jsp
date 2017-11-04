
<!DOCTYPE html>
<html >
<head>
<title>Setting Home Page</title>
</head>
<body ng-app="SettingModule" ng-controller="AppCPController">
  <div class="container">
  	<%@include file="../header.jsp"%>
    <div class="content">
    	<div style="width: 150px;float: left;"><%@include file="setting_menu.jsp"%></div>
       	<div style="float: left;margin-left: 20px;">
       		<div><h4>Welcome to Cross Promotion Application Management Page</h4></div>
       		<div ng-show="myApps.length == 0">Sorry! This no application!</div>
       		<div style="margin: 9px 9px 9px 815px;"> My Apps:
       		   <select ng-model="sltApp" ng-change="fetchCPApps()" ng-options="m.name for m in myApps">
						</select>
       		</div>
       		<div>       		       		
	       		<table class="table table-bordered table-striped ng-table"
				        ng-show="myApps.length > 0 && cpApps.length > 0">
				        <thead>
				          <tr>
				            <th>ID</th>
				            <th>Name</th>
				            <th>Location</th>
				            <th>OS</th>
				            <th>OS Version</th>
				            <th>Policy</th>
				            <th>Content</th>
				            <th>CpSpace Count</th>
				            <th>Operate</th>
				          </tr>
				        </thead>
				        <tbody>
				          <tr ng-repeat="app in cpApps">
				            <td>{{app.id}}</td>
				            <td>{{app.name}}</td>
				            <td>{{app.location}}</td>				            
				            <td>{{app.os}}</td>
				            <td>{{app.osVersion}}</td>
				            <td>{{app.policy}}</td>
				            <td>{{app.content}}</td>
				            <td>0</td>
				            <th>
				               <button type="button" class="btn btn-default" ng-click="breakCrossP(app.id)">Break</button>
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
<script src="../../../js/controllers/AppCPController.js"></script>
<script src="../../../js/app.js"></script>
</html>