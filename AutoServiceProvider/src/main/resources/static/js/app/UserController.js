'use strict'

var module = angular.module('demo.controllers', []);
module.controller("UserController", [ "$scope", "UserService","$window",
		function($scope, UserService,$window) {

			$scope.userDto = {
				userId : null,
				userName : null,
				skillDtos : []
			};
			$scope.skills = [];
			
			UserService.getUserById(1).then(function(value) {
				//console.log(value.data);
			}, function(reason) {
				console.log("error occured");
			}, function(value) {
				console.log("no callback");
			});

			$scope.saveUser = function() {
				$scope.userDto.skillDtos = $scope.skills.map(skill => {
					return {skillId: null, skillName: skill};
				});
				UserService.saveUser($scope.userDto).then(function() {
					console.log("works");
					UserService.getAllUsers().then(function(value) {
						$scope.allUsers= value.data;
					}, function(reason) {
						console.log("error occured");
					}, function(value) {
						console.log("no callback");
					});

					$scope.skills = [];
					$scope.userDto = {
						userId : null,
						userName : null,
						skillDtos : []
					};
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
			}
			
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