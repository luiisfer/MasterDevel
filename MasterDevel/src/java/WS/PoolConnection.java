/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.DbUtils;

/**
 *
 * @author luiis
 */
public class PoolConnection {

    public Connection conexion = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    public String JNDI;
    public String JNDILOG;

    public PoolConnection(String JNDI) {
        this.JNDI = JNDI;
    }

    public PoolConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getSchema() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    try {
                        return conexion.getSchema() + "---" + this.JNDILOG;
                    } catch (SQLException ex) {
                        System.out.println("error" + ex);
                        return this.JNDILOG;
                    }
                } else {
                    return this.JNDILOG;
                }
            } catch (SQLException ex) {
                System.out.println("error" + ex);
                return this.JNDILOG;
            }
        } else {
            return this.JNDILOG;
        }
    }

    @Override
    public String toString() {
        if (conexion == null) {
            return "ConexionIsActive: " + false;
        } else {
            try {
                return "ConexionIsActive: " + conexion.isClosed();
            } catch (Exception ex) {
                return ex.toString();
            }
        }
    }

    public boolean conectarJNDI(String JNDI) {
        
        this.JNDILOG = JNDI;
        boolean retorno = false;
        try {
            if (conexion != null) {
                cerrarConexion();
            }
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(JNDI);
            if (ds != null) {
                this.conexion = ds.getConnection();
                retorno = true;
            } else {
                System.err.println("\n" + "##############ERROR##############\n"
                        + "No se encontró datasource\n" + "#################################\n");
                retorno = false;
            }
        } catch (NamingException ex) {
            System.err.println(
                    "\n" + "##############ERROR##############\n" + "\nNo se encontró el nombre del recurso: "
                    + ex + "\n" + "#################################\n");
            retorno = false;
        } catch (SQLException ex) {
            System.err.println("\n" + "##############ERROR##############\n"
                    + "No se pudo obtener conexión: " + ex + "\n" + "#################################\n");
            retorno = false;
        }
        return retorno;
    }

    public boolean conectarJNDI() {
        this.JNDILOG = JNDI;
        boolean retorno = false;
        try {
            if (conexion != null) {
                cerrarConexion();
            }
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(JNDI);
            if (ds != null) {
                this.conexion = ds.getConnection();
                retorno = true;
            } else {
                System.err.println("\n" + "##############ERROR##############\n"
                        + "No se encontró datasource\n" + "#################################\n");
                retorno = false;
            }
        } catch (NamingException ex) {
            System.err.println(
                    "\n" + "##############ERROR##############\n" + "\nNo se encontró el nombre del recurso: "
                    + ex + "\n" + "#################################\n");
            retorno = false;
        } catch (SQLException ex) {
            System.err.println("\n" + "##############ERROR##############\n"
                    + "No se pudo obtener conexión: " + ex + "\n" + "#################################\n");
            retorno = false;
        }
        return retorno;
    }

    /**
     * Ejecuta la consulta recibida en la conexion abierta
     *
     * @param sql
     * @return q
     */
    public ResultSet Consultar(String sql) {

        try {
            System.out.println(this.JNDILOG);
            this.ps = this.conexion.prepareStatement(sql);
            this.ps.setQueryTimeout(6000);
            this.ps.setFetchSize(1000);
            this.ps.execute();
            rs = this.ps.getResultSet();
            return rs;
        } catch (SQLException ex) {
            System.out.println("error"+ ex);
            
            this.cerrarConexion();
        }
        return this.rs;
    }

    /**
     * xx Ejecuta el update recibido
     *
     * @param update
     */
    public void ExecUpIn(String update) {
        try {
            this.ps = null;
            this.ps = this.conexion.prepareStatement(update);
            this.ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("error" + ex);
        }

    }

    /**
     * xx Ejecuta el update recibido
     *
     * @param update
     * @return 
     * @throws java.sql.SQLException
     */
    public int ExecThrows(String update) throws SQLException {
        this.ps = null;
        this.ps = this.conexion.prepareStatement(update);
        return this.ps.executeUpdate();
    }

    public CallableStatement insertThrows(String insert, String id) throws SQLException {
        this.ps = null;
        CallableStatement sps = this.conexion.prepareCall("BEGIN " + insert + " returning " + id + " into ?; END;");
        sps.registerOutParameter(1, 2);
        return sps;
    }

    public boolean Execute(String update) {
        try {
            CallableStatement stmt;
            stmt = conexion.prepareCall(update);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PoolConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

   
    public void cerrarConexion() {
        try {
            DbUtils.closeQuietly(conexion, ps, rs);
        } catch (Exception ex) {
            System.out.println("error" + ex);
        }
    }

}