/*
 * RootResource.java
 * Creado el 02/nov/2017 12:48:14 PM
 * 
 */

package siayf.rh.reportes.core.rs;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 */
@Path("/")
@RequestScoped
public class RootResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RootResource
     */
    public RootResource() {
    }

    /**
     * Retrieves representation of an instance of siayf.rh.reportes.core.rs.RootResource
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public String getHtml() {
//        return "<!DOCTYPE html>"
//                + "<html>"
//                + "<title>siayf-rh-reportes</title>"
//                + "<body>"
//                + "<h1>API reportes</h1>"
//                + "<p>Corriendo correctamente. </p>"
//                + "</body>"
//                + "</html>";
//    }
}
