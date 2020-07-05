/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author luiis
 */
@Path("message")
public class MessageController {

    @Context
    private UriInfo context;

    @Inject
    MessageService messageService;

    @HeaderParam("X-Key")
    String xKey;
    
    @HeaderParam("X-Route")
    String xRoute;
    
    @HeaderParam("X-Signature")
    String xSignature;

    /**
     * Creates a new instance of MessageController
     */
    public MessageController() {
    }

    
    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMessage(@PathParam("id") String id) {
        return messageService.getMessage(id, xKey, xSignature);
    }
    /**
     * PUT method for updating or creating an instance of MessageController
     *
     * @param content representation for the resource
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMessage(String content) {
        return messageService.createMessage(content, xKey, xSignature);
    }
}
