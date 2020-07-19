

var demoApp = angular.module('demo', [ 'ui.bootstrap', 'demo.controllers',
		'demo.services' ]);
demoApp.constant("CONSTANTS", {
	saveContact : "/user/saveContact",
	registerUser : "/register"
});