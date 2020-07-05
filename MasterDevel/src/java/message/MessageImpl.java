/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import WS.ResponseModel;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author luiis
 */
@Default
public class MessageImpl implements MessageService {

    @Inject
    private MessageD messageD;

    private ResponseModel res;

    @Override
    public Response createMessage(String request, String xkey) {
        try {
            res = new ResponseModel();
            JSONObject req;
            String msg;
            String tags;
            req = new JSONObject(request);
            msg = req.getString("msg");
            tags = req.getString("tags");
            if (messageD.validateKey(xkey) == true) {
                JSONObject status = messageD.createMessage(msg, tags);
                if (status.optBoolean("status")) {
                    res.setCode(200).setBody(status.optLong("newId"));
                } else {
                    res.setCode(409);
                }
            }
            else{
                res.setCode(403);
            }

            return res.getRes();
        } catch (JSONException ex) {
            res.setCode(500);
            System.out.println("error" + ex);
            return res.getRes();
        }
    }

    @Override
    public Response getMessage(String id, String xkey) {
        res = new ResponseModel();
        try {
            JSONObject clients = messageD.getMessage(id);
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
