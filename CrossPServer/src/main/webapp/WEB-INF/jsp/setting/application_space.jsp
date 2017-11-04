
<!DOCTYPE html>
<html >
<head>
<title>Setting Home Page</title>
</head>
<body ng-app="SettingModule" ng-controller="AppSpaceController">
  <div class="container">
  	<%@include file="../header.jsp"%>
    <div class="content">
    	<div style="width: 150px;float: left;"><%@include file="setting_menu.jsp"%></div>
       	<div style="float: left;margin-left: 20px;">
       		<div><h4>Welcome to Cross Promotion Application Management Page</h4></div>
       		<div ng-show="mySpaces.length == 0">Sorry! This no application space!</div>
       		<div style="width: 965px;">       		       		
	       		<table class="table table-bordered table-striped ng-table"
				        ng-show="mySpaces.length > 0">
				        <thead>
				          <tr>
				            <th>ID</th>
				            <th>Name</th>
				            <th>Position</th>
				            <th>ShowType</th>
				            <th>Transparency</th>
				            <th>Template</th>
				            <th>isPublic</th>
				            <th>Operate</th>
				          </tr>
				        </thead>
				        <tbody>
				          <tr ng-repeat="space in mySpaces">
				            <td>{{space.id}}</td>
				            <td>{{space.name}}</td>
				            <td>{{space.position}}</td>				            
				            <td>{{space.showType}}</td>
				            <td>{{space.transparency}}</td>
				            <td>{{space.appTemplate.name}}</td>
				            <td>{{space.public}}</td>
				            <th>
				               <button type="button" class="btn btn-default" ng-click="editAppSpace(space)">Edit</button>
				               <button type="button" class="btn btn-default" ng-click="removeAppSpace(space.id)">Remove</button>
				            </th>
				          </tr>
				        </tbody>
				   </table>
			   </div>
       		
       		<div>
       			<button type="button" class="btn btn-default" ng-click="hiddenShow=!hiddenShow">Add</button>       			
       		</div>  
       		<div ng-show="hiddenShow">       			
       			<div>AppSpace Name:<input type="text" ng-model="appSpace.name" value="" placeholder="AppSpace Name" /></div>
       			<div>Position:<input type="text" ng-model="appSpace.position" value="" placeholder="x, y, width, height" /></div>
       			<div>Show Type:<input type="text" ng-model="appSpace.showType" value="" placeholder="Show Type" /></div>
       			<div>Transparency:<input type="text" ng-model="appSpace.transparency" value="" placeholder="Transparency" /></div>
       			<div>Template:<select ng-model="appSpace.appTemplate.appTconf" ng-options="t.name for t in templates"> <option value="">--Choose--</option></select></div>
       			<div>isPublic:<input type="checkbox" ng-model="appSpace.public" value=""/></div>
       			<div><button type="button" class="btn btn-default" ng-click="save(appSpace)">Submit</button></div>       			
       		</div>
       	</div>   
    </div>
  </div>
</body>

<script src="../../../js/lib/angular/angular.js"></script>
<script src="../../../js/controllers/AppSpaceController.js"></script>
<script src="../../../js/app.js"></script>
</html>