if (Meteor.isClient){
	Template.layout.helpers({
	  url: function(){
	    return Session.get('url');
	    //url is a variable that holds the value of the session variable url//
  	}
	});
}

