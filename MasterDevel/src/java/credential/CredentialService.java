/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credential;

import javax.ws.rs.core.Response;

/**
 *
 * @author luiis
 */
public interface CredentialService {
    
    public Response getKey(String request);
    
}
