'use strict'

var module = angular.module('demo.controllers', []);
module.controller("UserController", [ "$scope", "UserService","$window",
		function($scope, UserService,$window) {

		$scope.saveContact = function() {
			console.log($scope.newContact);
			 if($scope.newContact == null || $scope.newContact == angular.undefined)
		  	 return;
				UserService.saveContact($scope.newContact).then(function() {
					console.log("works");
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
		$scope.registerUser = function() {
			console.log($scope.registerUser);
			 if($scope.registerUser == null || $scope.registerUser == angular.undefined)
		  	 return;
				UserService.registerUser($scope.registerUser).then(function() {
					console.log("works");
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
			
		} ]);