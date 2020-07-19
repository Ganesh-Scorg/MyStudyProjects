'use strict'

angular.module('demo.services', []).factory('UserService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};
			
			service.saveContact = function(newContact) {
				return $http.post(CONSTANTS.saveContact, newContact);
			}
			
			service.registerUser = function() {
				return $http.post(CONSTANTS.registerUser);
			}
			return service;
		} ]);