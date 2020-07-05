/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credential;

import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * REST Web Service
 *
 * @author luiis
 */
@Path("credential")
public class CredentialController {

    @Context
    private UriInfo context;
    
    @Inject
    CredentialService credentialService;

    /**
     * Creates a new instance of CredentialResource
     */
    public CredentialController() {
    }

    /**
     * PUT method for updating or creating an instance of CredentialResource
     *
     * @param content representation for the resource
     * @return 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(String content) {
        return credentialService.getKey(content);

    }
}
