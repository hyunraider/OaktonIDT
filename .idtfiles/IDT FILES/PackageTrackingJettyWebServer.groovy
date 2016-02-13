
@Grab(group='org.eclipse.jetty.aggregate', module='jetty-all', version='7.6.15.v20140411')
@Grab(group='com.gmongo', module='gmongo', version='1.0')

import org.eclipse.jetty.server.*
import org.eclipse.jetty.servlet.*
import javax.servlet.http.*
import javax.servlet.*
import com.gmongo.GMongo
 

class SimpleGroovyServlet extends HttpServlet {

    long updateCount = 0;
    long lastPrintCount = 0;
    //def mongo = new GMongo("mongodb://127.0.0.1:3001");
    //def db = mongo.getDB("meteor");


    void doGet(HttpServletRequest req, HttpServletResponse resp) {
        println "GET  "+req.getRequestURL()+"   query string:"+req.getQueryString();

        if(req.getPathInfo().equals("/tracknewpackage")) {

            def responseString = "{ \"ackUUID\":\""+req.getParameterMap().get("uuid")+"\" }"
            resp.setContentType("application/json");
            def writer = resp.getWriter();
            writer.print(responseString);
            writer.flush();
            println "\t\t  "+responseString;

            //
            //
            //
            //TODO: send the update to the new track logic here?
            //
            //
            //

        }
    }


    void doPost(HttpServletRequest req, HttpServletResponse resp) {
        println "POST: "+req.getRequestURL();

        if(req.getPathInfo().startsWith("/packagetrackupdate/")) {


            try {
                File file = new File("testing.js");
                File address = new File("address.js");

                if (!file.exists()){
                    file.createNewFile();
                }
                if (!address.exists()){
                    address.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                FileWriter fw2 = new FileWriter(address.getAbsoluteFile());
                BufferedWriter bw2 = new BufferedWriter(fw2);
                BufferedWriter bw = new BufferedWriter(fw);

                def uuidstring = "{uuid: '" + req.getRequestURL() + "'}";
                bw2.write(uuidstring);
                bw2.close();

                BufferedReader reader = req.getReader();
                String line = null;
                while ((line = reader.readLine()) != null) {

                    if(line.contains("delivered")) {
                        println req.getPathInfo()+" -> "+line;
                        println "Done";
                    }
                    else {
                        //comment out if you only want to print the delivered
                        //events
                        def responseString = "{'uuid': '" + req.getRequestURL() + "', " + line.substring(1);
                        resp.setContentType("application/json");
                        def writer = resp.getWriter();
                        //writer.print(responseString);
                        //writer.flush();
                        bw.write(responseString);
                        //db.testdb.insert([hello: 'hello']);
                        bw.close();
                        println req.getPathInfo()+" -> "+line;
                    }
                }

                updateCount++;
                if((updateCount - lastPrintCount) >= 1000) {
                    println "packagetrackupdate count: "+updateCount;
                    lastPrintCount = updateCount;
                }


                //
                //
                //
                //TODO: send the package track update to the update received
                //logic here?
                //
                //
                //

            } catch (Exception e) { e.printStackTrace(); /*report an error*/ }
        }
    }

}


def server = new Server(8080);
ServletHandler handler = new ServletHandler();
server.setHandler(handler);
handler.addServletWithMapping(SimpleGroovyServlet.class, "/*");
println "Starting Jetty, press Ctrl+C to stop."
server.start()
server.join();
