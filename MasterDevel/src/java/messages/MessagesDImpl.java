/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import WS.PoolConnection;
import java.sql.ResultSet;
import javax.enterprise.inject.Default;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author luiis
 */
@Default
public class MessagesDImpl extends PoolConnection implements MessagesD {

    
    public MessagesDImpl() {
        super("jdbc/localhost");
    }
    
    @Override
    public JSONArray getMessages(String tag) {
        JSONArray messages = new JSONArray();
        try {
            this.conectarJNDI();
            String Query = "SELECT MESSAGE FROM TBL_MESSAGE WHERE TAG= '" +tag+ "'";

            ResultSet res = this.Consultar(Query);
            JSONObject message;
            while (res.next()) {
                message = new JSONObject();
                message.put("MESSAGE", res.getString(1));
                messages.put(message);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        } finally {
            this.cerrarConexion();
        }
        return messages;
    }

}
