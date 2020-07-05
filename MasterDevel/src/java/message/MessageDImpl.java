/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import WS.PoolConnection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.enterprise.inject.Default;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author luiis
 */

@Default
public class MessageDImpl extends PoolConnection implements MessageD{
    
    public MessageDImpl() {
        super("jdbc/localhost");
    }
    
    @Override
    public JSONObject createMessage(String msg, String tags){
        boolean estado = false;
        JSONObject trs = new JSONObject();
        try {
            this.conectarJNDI();
            String Query = "INSERT INTO TBL_MESSAGE(MESSAGE,TAG) VALUES('" + msg + "','" + tags + "')";
            
            CallableStatement result = this.insertThrows(Query, "ID_MESSAGE");
            estado = result.executeUpdate() > 0;
            Object idTransaction = 0L;
            if (estado) {
                idTransaction = result.getLong(1);
            }
            trs.put("status", estado).put("newId", idTransaction);
            result.close();
            
        } catch (SQLException | JSONException ex) {
            trs = new JSONObject();
            System.out.println("Error" + ex);
            
        }finally{
            this.cerrarConexion();
        }
        return trs;
    }
    
    @Override
    public JSONObject getMessage(String id){
        JSONObject message = new JSONObject();
        try {
            this.conectarJNDI();
            String Query = "SELECT MESSAGE,TAG FROM TBL_MESSAGE WHERE ID_MESSAGE=" + id ;
            ResultSet res = this.Consultar(Query);
            
            if (res.next()) {
                
                message.put("MESSAGE", res.getString(1));
                message.put("TAG", res.getString(2));              
            }
            
        } catch (SQLException | JSONException ex) {
            System.out.println("Error" + ex);
            
        }finally{
            this.cerrarConexion();
        }
        return message;
    }
    
    @Override
    public boolean validateKey(String key) {
        boolean estado = false;
        try {
            this.conectarJNDI();
            String Query = "SELECT ID_KEY TABLE_KEY WHERE ID_KEY = " + key;
            
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
    public boolean validateSignature(String sharedkey) {
        boolean estado = false;
        try {
            this.conectarJNDI();
            String Query = "SELECT SHAREDKEY FROM TABLE_KEY WHERE SHAREDKEY = " + sharedkey;
            
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
    
}
