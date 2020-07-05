/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import WS.ResponseModel;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author luiis
 */
public class MessagesImpl implements MessagesService{
    
    @Inject
    private MessagesD messagesD;
    
    private ResponseModel res;
    
    @Override
    public Response getMessages(String tag, String xkey){
        res = new ResponseModel();
        try {
            JSONArray clients = messagesD.getMessages(tag);
            if (clients.length() == 0) {
                res.setCode(404);
            } else {
                res.setBody(clients).setCode(200);
            }
            
        } catch (Exception e) {
            res.setCode(500);
            
        }
        return res.getRes();
    }
    
}
