/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credential;



/**
 *
 * @author luiis
 */
public interface CredentialD {
    
    public boolean getKey(String key);
    
    public boolean createKey(String key, String shared_secret);
}
