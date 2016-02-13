
Router.configure({
	layoutTemplate: 'layout',
	yieldTemplates: {
		'home': {to: 'home'},
		'viewPackages': {to: 'viewPackages'},
		'registration': {to: 'registration'},
		'addPackages': {to: 'addPackages'}
	}
});

Router.map(function () {
	this.route('Home', {
		path: '/',
		template: 'layout',
		data: function(){
			Session.set('url', 'home');
		}
	});
	this.route('Pages', {
		path: '/:name',
		template: 'layout',
		data: function(){
			Session.set('url', this.params.name);
		}
	});
});