/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credential;

import WS.PoolConnection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author luiis
 */
public class CredentialDImpl extends PoolConnection implements CredentialD{

    public CredentialDImpl() {
        super("jdbc/localhost");
    }
    
    @Override
    public boolean getKey(String key) {
        boolean estado = false;
        try {
            this.conectarJNDI();
            String Query = "SELECT ID_KEY , DESC TABLE_KEY WHERE ID_KEY = " + key;
            
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
    public boolean createKey(String key){
        boolean estado = false;
        try {
            this.conectarJNDI();
            String Query = "INSERT INTO TABLE_KEY(ID_KEY) VALUES(" + key + ")";
            estado = this.ExecThrows(Query) > 0;
        } catch (Exception ex) {
            System.out.println("Error" + ex);
            
        }finally{
            this.cerrarConexion();
        }
        return estado;
    }
    
}