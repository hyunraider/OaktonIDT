Router.configure({
	layoutTemplate: 'layout'
});

Router.map(function () {
	this.route('Home', {
		path: '/',
		template: 'layout'
	});
});