/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credential;

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
public class CredentialImpl implements CredentialService {

    @Inject
    private CredentialD credentialD;

    private ResponseModel res;

    @Override
    public Response getKey(String request) {
        try {
            res = new ResponseModel();
            JSONObject req;
            String key;
            String shared_secret;
            req = new JSONObject(request);
            key = req.getString("key");
            shared_secret = req.getString("shared_secret");

            boolean status = credentialD.getKey(key);
            if (status) {
                res.setCode(403);
            } else {
                res.setCode(204);
                credentialD.createKey(key, shared_secret);
            }
            return res.getRes();    
        } catch (JSONException ex) {
            res.setCode(500);
            System.out.println("error" + ex);
            return res.getRes();   
        }  
    }

}
