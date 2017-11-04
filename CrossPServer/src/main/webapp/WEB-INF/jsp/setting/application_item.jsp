
<!DOCTYPE html>
<html >
<head>
<title>Setting Home Page</title>
</head>
<body ng-app="SettingModule" ng-controller="AppItemController">
  <div class="container">
  	<%@include file="../header.jsp"%>
    <div class="content">
    	<div style="width: 150px;float: left;"><%@include file="setting_menu.jsp"%></div>
       	<div style="float: left;margin-left: 20px;">
       		<div><h4>Welcome to Cross Promotion Application Management Page</h4></div>
       		<div ng-show="myItems.length == 0">Sorry! This no application!</div>
       		<div style="width: 965px;">       		       		
	       		<table class="table table-bordered table-striped ng-table"
				        ng-show="myItems.length > 0">
				        <thead>
				          <tr>
				            <th>ID</th>
				            <th>Name</th>
				            <th>AppId</th>
				            <th>Icon</th>
				            <th>Type</th>
				            <th>Description</th>				            
				            <th>CP</th>
				            <th>Operate</th>
				          </tr>
				        </thead>
				        <tbody>
				          <tr ng-repeat="item in myItems">
				            <td>{{item.id}}</td>
				            <td>{{item.name}}</td>
				            <td>{{item.appId}}</td>				            
				            <td>{{item.icon}}</td>
				            <td>{{item.type}}</td>
				            <td>{{item.description}}</td>
				            <td>3</td>
				            <th>
				               <button type="button" class="btn btn-default" ng-click="editAppItem(item)">Edit</button>
				               <button type="button" class="btn btn-default" ng-click="removeAppItem(item.id)">Remove</button>
				            </th>
				          </tr>
				        </tbody>
				   </table>
			   </div>
       		
       		<div>
       			<button type="button" class="btn btn-default" ng-click="hiddenShow=!hiddenShow">Add</button>
       		</div>
       		<div ng-show="hiddenShow">       			
       			<div>Item Name:<input type="text" ng-model="item.name" value="" placeholder="Item Name" /></div>
       			<div>App ID:<input type="text" ng-model="item.appId" value="" placeholder="APP ID" /></div>
       			<div>Icon:<input type="text" ng-model="item.icon" value="" placeholder="Icon" /></div>
       			<div>Type:<input type="text" ng-model="item.type" value="" placeholder="Type" /></div>
       			<div>Description:<input type="text" ng-model="item.description" value="" placeholder="Description" /></div>
       			<div>
       			  <button type="button" class="btn btn-default" ng-click="saveAppItem(item)">Submit</button>
       			</div>       			
       		</div>
       	</div>   
    </div>
  </div>
</body>

<script src="../../../js/lib/angular/angular.js"></script>
<script src="../../../js/controllers/AppItemController.js"></script>
<script src="../../../js/app.js"></script>
</html>