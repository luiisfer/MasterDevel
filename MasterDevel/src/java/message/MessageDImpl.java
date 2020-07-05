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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author luiis
 */
public class MessageDImpl extends PoolConnection implements MessageD{
    
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
    
}