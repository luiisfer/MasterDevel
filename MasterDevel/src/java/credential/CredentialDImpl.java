/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credential;

import WS.PoolConnection;
import java.sql.ResultSet;
import javax.enterprise.inject.Default;

/**
 *
 * @author luiis
 */

@Default
public class CredentialDImpl extends PoolConnection implements CredentialD{

    public CredentialDImpl() {
        super("jdbc/localhost");
    }
    
    @Override
    public boolean getKey(String key) {
        boolean estado = false;
        try {
            this.conectarJNDI();
            String Query = "SELECT ID_KEY  TABLE_KEY WHERE ID_KEY = " + key;
            
            ResultSet res = this.Consultar(Query);
            
            if (res.next()) {
                estado = true;
            }
            this.cerrarConexion();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            
            
        }finally{
            this.cerrarConexion();
        }
        return estado;
    }
    
    @Override
    public boolean createKey(String key, String shared_secret){
        boolean estado = false;
        try {
            this.conectarJNDI();
            String Query = "INSERT INTO TABLE_KEY(ID_KEY, SHAREDKEY) VALUES(" + key + ", "+shared_secret+")";
            estado = this.ExecThrows(Query) > 0;
        } catch (Exception ex) {
            System.out.println("Error" + ex);
            
        }finally{
            this.cerrarConexion();
        }
        return estado;
    }
    
}
