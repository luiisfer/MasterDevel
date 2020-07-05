/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import org.json.JSONObject;

/**
 *
 * @author luiis
 */
public interface MessageD {
    
    public JSONObject createMessage(String msg, String tags);
    
    public JSONObject getMessage(String id);
    
    public boolean validateKey(String key);
    
}
