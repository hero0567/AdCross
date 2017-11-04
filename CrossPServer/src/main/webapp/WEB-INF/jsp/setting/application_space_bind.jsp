
<!DOCTYPE html>
<html >
<head>
<title>Setting Home Page</title>
</head>
<body ng-app="SettingModule" ng-controller="AppController">
  <div class="container">
  	<%@include file="../header.jsp"%>
    <div class="content">
    	<div style="width: 150px;float: left;"><%@include file="setting_menu.jsp"%></div>
       	<div style="float: left;margin-left: 20px;">
       		<div><h4>Welcome to Cross Promotion Application Management Page</h4></div>
       		<div ng-show="apps.length == 0">Sorry! This no application!</div>
       		<div style="width: 965px;">       		       		
	       		<table class="table table-bordered table-striped ng-table"
				        ng-show="apps.length > 0">
				        <thead>
				          <tr>
				            <th>ID</th>
				            <th>Name</th>
				            <th>AppId</th>
				            <th>Platform</th>
				            <th>Type</th>
				            <th>Description</th>
				            <th>isPublic</th>
				            <th>CpSpace</th>
				            <th>CpItem</th>
				            <th>Operate</th>
				          </tr>
				        </thead>
				        <tbody>
				          <tr ng-repeat="app in apps">
				            <td>{{app.id}}</td>
				            <td>{{app.name}}</td>
				            <td>{{app.appId}}</td>
				            <td>{{app.platform}}</td>				            
				            <td>{{app.type}}</td>
				            <td>{{app.description}}</td>
				            <td>{{app.public}}</td>				            
				            <td>3</td>
				            <td>10</td>
				            <th>
				               <button type="button" class="btn btn-default" ng-click="removeApp(app.id)">Remove</button>
				            </th>
				          </tr>
				        </tbody>
				   </table>
			   </div>
       		
       		<div>
       			<button type="button" class="btn btn-default" ng-click="hiddenShow=!hiddenShow">Add</button>
       		</div>
       		<div ng-show="hiddenShow">       			
       			<div>Application Name:<input type="text" ng-model="app.name" value="" placeholder="Application Name" /></div>
       			<div>APP ID:<input type="text" ng-model="app.appId" value="" placeholder="APP ID" /></div>
       			<div>Platform:<input type="text" ng-model="app.platform" value="" placeholder="Platform" /></div>
       			<div>Type:<input type="text" ng-model="app.type" value="" placeholder="Type" /></div>
       			<div>Description:<input type="text" ng-model="app.description" value="" placeholder="Description" /></div>
       			<div>isPublic:<input type="checkbox" ng-model="app.public" value="" placeholder="Public to others" /></div>
       			<div><button type="button" class="btn btn-default" ng-click="save(app)">Submit</button></div>       			
       		</div>
       	</div>   
    </div>
  </div>
</body>

<script src="../../../js/lib/angular/angular.js"></script>
<script src="../../../js/controllers/AppController.js"></script>
<script src="../../../js/app.js"></script>
</html>