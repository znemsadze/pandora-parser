
var app = angular.module('myApp', []);


//
//app.controller('myCtrl', function($scope, $http) {
//
//		$http.get("rest/parser/x")
//			.then(function (response) {
//				$scope.myWelcome = response.data;
//			});
//
//});

app.controller('myCtrl', function($scope, $http, $timeout) {

	// Function to get the data
	$scope.getData = function(){
		$http.get('rest/parser/speed')
			.success(function(data, status, headers, config) {

				$scope.column1=data;

			});
		$http.get('rest/parser/angle')
			.success(function(data, status, headers, config) {

				$scope.column2=data;

			});
		$http.get('rest/parser/checkpoints')
			.success(function(data, status, headers, config) {

				$scope.checkpoints=data;

			});

		$http.get('rest/parser/checkpointids')
			.success(function(data, status, headers, config) {

				$scope.checkpointids=data;

			});
		$http.get('rest/parser/speed-angles')
			.success(function(data, status, headers, config) {

				$scope.speedangles=data;

			});
		$http.get('rest/parser/speed-angles-avg')
			.success(function(data, status, headers, config) {

				$scope.speedanglesavg=data;

			});
	};

	// Function to replicate setInterval using $timeout service.
	$scope.intervalFunction = function(){
		$timeout(function() {
			$scope.getData();
			$scope.intervalFunction();
		}, 1000)
	};

	$scope.intervalFunction();

	$scope.start=function(){
		$http.get('rest/parser/start')
			.success(function(data, status, headers, config) {

				$scope.state=data;

			});
	}


	$scope.stop=function(){
		$http.get('rest/parser/stop')
			.success(function(data, status, headers, config) {

				// Your code here
				$scope.state=data;

			});
	}
});
