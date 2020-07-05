/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import javax.ws.rs.core.Response;

/**
 *
 * @author luiis
 */
public interface MessageService {
    
    public Response createMessage(String request, String xkey);
    
    public Response getMessage(String id, String xkey);
}
