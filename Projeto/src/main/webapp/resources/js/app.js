var app = angular.module("app", []);

app.controller("appController", function($scope, $window, $http) {

	/* CRIANDO UM ARRAY PARA OS REGISTROS QUE VÃO SER RETORNADOS PELO SPRING */
	$scope.processados = new Array();

	$scope.init = function() {

		var response = $http.get("/listarProcessados");

		response.success(function(data, status, headers, config) {

			$scope.processados = data;

		});

		response.error(function(data, status, headers, config) {
			$window.alert(data);
		});
	}

});