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

/**
 *
 * @author luiis
 */
public class MessagesImpl implements MessagesService {

    @Inject
    private MessagesD messagesD;

    private ResponseModel res;

    @Override
    public Response getMessages(String tag, String xkey, String xSignature) {
        res = new ResponseModel();
        try {
            if (messagesD.validateSignature(xSignature) == true) {
                if (messagesD.validateKey(xkey) == true) {
                    JSONArray clients = messagesD.getMessages(tag);
                    if (clients.length() == 0) {
                        res.setCode(404);
                    } else {
                        res.setBody(clients).setCode(200);
                    }
                } else {
                    res.setCode(403);
                }
            } else {
                res.setCode(403);
            }

        } catch (Exception e) {
            res.setCode(500);

        }
        return res.getRes();
    }

}
