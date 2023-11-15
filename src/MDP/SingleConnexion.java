package MDP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingleConnexion {
    String db;
    String user;
    String pwd;
    String url ;
    private static Connection connection = null;

    private SingleConnexion() {
        try {
        	File f = new File("application.properties");
            FileInputStream i = new FileInputStream(f);
            Properties properties = new Properties();
            properties.load(i);
            
            db = properties.getProperty("db");
            user = properties.getProperty("user");
            pwd = properties.getProperty("pwd");
            url = "jdbc:mysql://"+properties.getProperty("host")+":"+properties.getProperty("port")+"/" + db;
            
            connection = DriverManager.getConnection(url, user, pwd);
            System.out.println("instance cree!!");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        if (connection == null)
            new SingleConnexion();
        return connection;
    }
}
