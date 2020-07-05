/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author luiis
 */
@Path("messages")
public class MessagesController {

    @Context
    private UriInfo context;
    
    @Inject
    MessagesService messagesService;
    
    @HeaderParam("X-Key")
    String xKey;
    
    @HeaderParam("X-Route")
    String xRoute;
    
    @HeaderParam("X-Signature")
    String xSignature;

    /**
     * Creates a new instance of MessagesController
     */
    public MessagesController() {
    }

    /**
     * Retrieves representation of an instance of messages.MessagesController
     * @param tag
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{tag}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMessage(@PathParam("tag") String tag) {
        return messagesService.getMessages(tag, xKey , xSignature);
    }

    
}
