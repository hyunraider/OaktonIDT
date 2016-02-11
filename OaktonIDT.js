if (Meteor.isClient){
	Template.layout.helpers({
	  url: function(){
	    return Session.get('url');
  	}
	});
}

