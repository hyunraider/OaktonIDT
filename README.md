This is the repository for Oakton HS Team 1 in the IDT Programming Competition. Our team members are Parv Ahuja, Greg Bae, Kevin Robinson, and Grace Yu.

This is a rather incomplete application that does have the functionality to view a certain tracking through the UUID.

![alt tag](/screenshot.png?raw=true "Optional Title")

This web application is written in a Javascript framework called Meteor.
It is not deployed, you can only host the server locally.

We did change the original groovy file to make it more compatible with the Javascript.

#### Core commands (needed to run the application-Written only for Bash/UX Terminals):
In the project root folder: $ meteor

In /.idtfiles folder: $ groovy PackageTrackingJettyWebServer.groovy
$ watch -n1 mongoimport --host 127.0.0.1 --port 3001 --db meteor --collection testdb testing.js

#### Adding Packages:
In /.idtfiles folder: $ groovy PackageEventsSimulator.groovy -n gpx_files/[gpxfilename].gpx
(When you input the name, ignore the square brackets. If you wish to put more .gpx files, put it in /.idtfiles/gpx_files)

So this is how its working:
You have port 3000, which is the app port.
You have port 8080, which is where the groovy stuff do their magic, and the eventgroovy file (modified) writes the latitude, longitude, and the UUID to a testing.js file. The watch command inserts the JSON in the testing.js to the app's mongodb every second.
The meteor application utilizes the mongodb and shoots out the location of the package with a google maps api.

If you run all those commands, you can run your app on http://localhost:3000/

We apologize for the inconvienience, although we went against an uphill battle trying to use java groovy apis with a javascript engine we had no experience with prior to this project, I think we all learned a lot. We used meteor because Javascript is an emerging fullstack language, and we wanted to test the waters with this project.
