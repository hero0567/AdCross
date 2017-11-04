
<!DOCTYPE html>
<html >
<head>
<title>Setting Home Page</title>
</head>
<body ng-app="SettingModule" ng-controller="AppBindController">
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
				            <th>App Name</th>
				            <th>Space Name</th>
                    <th>Position</th>
                    <th>ShowType</th>
                    <th>Transparency</th>
                    <th>Template</th>
                    <th>isPublic</th>
				          </tr>
				        </thead>
				        <tbody ng-repeat="app in apps | filter: app.appSpaces.length > 1">
				          <tr  ng-repeat="space in app.appSpaces">				              
					            <td rowspan="{{app.appSpaces.length}}" ng-show="$index = $first">{{app.name}}</td>				            
					            <td>{{space.name}}</td>
	                    <td>{{space.position}}</td>                   
	                    <td>{{space.showType}}</td>
	                    <td>{{space.transparency}}</td>
	                    <td>{{space.appTemplate.name}}</td>
	                    <td>{{space.public}}</td>
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
<script src="../../../js/controllers/AppBindController.js"></script>
<script src="../../../js/app.js"></script>
</html>