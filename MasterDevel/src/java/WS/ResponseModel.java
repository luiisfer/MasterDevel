/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;


public class ResponseModel {

    private int code;
    private JSONObject resBody;

    public ResponseModel() {
        this.resBody = new JSONObject();
    }

    /**
     * @param code the code to set
     * @return
     */
    public ResponseModel setCode(int code) {
        this.resBody.put("code", code);
        this.code = code;
        return this;
    }

    /**
     * @param body the body to set
     * @return
     */
    public ResponseModel setBody(Object body) {
        this.resBody.put("result", body);
        return this;
    }

    private String codeSelector(int code) {
        String result;
        switch (code) {
            case 500:
                result = "Error fatal";
                break;
            case 403:
                result = "No modificado";
                break;
            case 204:
                result = "No hay contenido";
                break;
            case 200:
                result = "Exitoso";
                break;
            default:
                result = "Codigo no controlado";
        }
        return result;
    }

    /**
     * @return the res
     */
    public Response getRes() {
        return Response.status(code)
                .entity((resBody.has("result") ? resBody : (resBody.put("result", this.codeSelector(code)))).toString())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}