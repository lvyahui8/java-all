package org.lyh;

import org.lyh.bean.ResultBody;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource  {

    public MyResource() {

    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultBody getJson(@QueryParam("time" ) int time){
        ResultBody result = new ResultBody();
        try {
            Thread.sleep(time);
            result.put("time",time);
        } catch (InterruptedException e) {
            //
        }
        return result;
    }
}
