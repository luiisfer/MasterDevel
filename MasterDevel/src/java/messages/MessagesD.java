/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import org.json.JSONArray;

/**
 *
 * @author luiis
 */
public interface MessagesD {
    
    public JSONArray getMessages(String tag);
    
    public boolean validateKey(String key);
    
    public boolean validateSignature(String sharedKey);
    
}
