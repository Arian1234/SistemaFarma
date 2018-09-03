package modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;

/**
 *
 * @author AMolinaDeveloper
 */
public class Conexion {

    private static String db;
    private static String user;
    private static String pwd;
    private static String host;
    private static Preferences prefs;
    private static Connection conn = null;

    public static Connection Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //busco en el registro de windows
                     prefs = Preferences.userRoot().node("System3210");
            //prefs=Preferences.systemRoot().node("\\MySoftware");
            setDb(prefs.get("data", "null"));
            setUser(prefs.get("usr", "null"));
            setPwd(prefs.get("pw", "null"));
            setHost(prefs.get("hst", "null"));
/////////////////////////////////////////////////////////////////////////
          
            
            String url = "jdbc:mysql://"+getHost()+"/" + getDb() + "?noAccessToProcedureBodies=true";
            conn = (Connection) DriverManager.getConnection(url, getUser(), "PasswordSistema");
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Detalles del error : \n" + ex, ":::...La conexion a fallado...:::", JOptionPane.ERROR_MESSAGE);
            return null;   }  
    }

    
    public static void WriteConn() {
        prefs = Preferences.userRoot().node("System3210");
        prefs.get("data", "null");
        prefs.get("usr", "null");
        prefs.get("pw", "null");
        prefs.get("hst","null");
        
        prefs.put("data", getDb());
        prefs.put("usr", getUser());
        prefs.put("pw", getPwd());
        prefs.put("hst", getHost());
    }

    /**
     * @return the db
     */
    public static String getDb() {
        return db;
    }

    /**
     * @param aDb the db to set
     */
    public static void setDb(String aDb) {
        db = aDb;
    }

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(String aUser) {
        user = aUser;
    }

    /**
     * @return the pwd
     */
    public static String getPwd() {
        return pwd;
    }

    /**
     * @param aPwd the pwd to set
     */
    public static void setPwd(String aPwd) {
        pwd = aPwd;
    }

    /**
     * @return the host
     */
    public static String getHost() {
        return host;
    }

    /**
     * @param aHost the host to set
     */
    public static void setHost(String aHost) {
        host = aHost;
    }
}
