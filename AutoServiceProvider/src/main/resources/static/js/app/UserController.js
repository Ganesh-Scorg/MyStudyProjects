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
		$scope.userLogin = function() {
			console.log($scope.loginuser);
			 if($scope.loginuser == null || $scope.loginuser == angular.undefined)
		  	 return;
				UserService.userLogin($scope.loginuser).then(function() {
					console.log("works");
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
		
		$scope.registerUser = function() {
			console.log("Inside Register User");
				UserService.registerUser().then(function() {
					console.log("works");
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
			
		} ]);