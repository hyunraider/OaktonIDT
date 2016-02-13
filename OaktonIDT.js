if (Meteor.isClient){
	Session.setDefault('uuid', null);

	Template.layout.helpers({
	  url: function(){
	    return Session.get('url');
	    //url is a variable that holds the value of the session variable url//
  	}
	});

	Meteor.setInterval(function(){
		Meteor.call('deletedb');
	}, 20000);
	
	Meteor.startup(function() {
    	GoogleMaps.load();
  	});

	Template.addPackages.events({
		'click button': function (e, t) {
			e.preventDefault();

			var query = "http://127.0.0.1:8080/packagetrackupdate/" + $('#searchquery').val();
			Session.set('uuid', query);
		}
	});
  	Template.addPackages.helpers({
	  exampleMapOptions: function() {
	    // Make sure the maps API has loaded
	    	if (GoogleMaps.loaded()) {
	      // Map initialization options
		      return {
		        center: new google.maps.LatLng(Testdb.findOne({uuid: Session.get('uuid')}, {sort:{time:-1}}).lat, Testdb.findOne({uuid: Session.get('uuid')}, {sort:{time:-1}}).lon),
		        zoom: 8
	      		};
	    	}
	  }
	});
	Template.addPackages.onCreated(function() {
	  // We can use the `ready` callback to interact with the map API once the map is ready.
	  GoogleMaps.ready('exampleMap', function(map) {
	    // Add a marker to the map once it's ready
	    var marker = new google.maps.Marker({
	      position: map.options.center,
	      map: map.instance
	    });
	  });
	});
}

if (Meteor.isServer){
	Meteor.methods({
		calljson: function(){
			var myjson = JSON.parse("private/testing.js");
			console.log(myjson);
		},
		deletedb: function(){
			Testdb.remove({});
		}
	});
	WebApp.connectHandlers.use("/hello", function(req, res, next) {
	  res.writeHead(200);
	  res.end("Hello world from: " + Meteor.release);
	});
}

Testdb = new Mongo.Collection('testdb');
UUID = new Mongo.Collection('uuid');