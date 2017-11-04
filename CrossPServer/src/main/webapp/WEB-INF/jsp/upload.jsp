
<!DOCTYPE html>
<html >
<head>
<title>About Page</title>
</head>
<body ng-app="UploadModule" ng-controller="AppUploadController">
  <div class="container">
  	<%@include file="header.jsp"%>
    <div class="content">
		<form>
		<input type="file" onchange="angular.element(this).scope().filesChanged(this)" multiple file-input="files" />
		<button ng-click="upload()">Upload</button>
		<li ng-repeat="file in files">{{file.name}}</li>
		</form>
    </div>
  </div>
</body>

<script src="../../../js/lib/angular/angular.js"></script>
<script src="../../../js/app.js"></script>
<script src="../../../js/directives.js"></script>
<script src="../../../js/controllers/AppUploadController.js"></script>  
</html>