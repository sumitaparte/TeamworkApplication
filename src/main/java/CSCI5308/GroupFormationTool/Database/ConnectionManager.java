package CSCI5308.GroupFormationTool.Database;

import CSCI5308.GroupFormationTool.ErrorHandling.DatabaseAccessException;
import CSCI5308.GroupFormationTool.GroupFormationToolLogs;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

public class ConnectionManager {
    private static ConnectionManager uniqueInstance = null;

    private String dbURL;
    private String dbUserName;
    private String dbPassword;

    public ConnectionManager() {
        IDatabaseConfiguration config = SystemConfig.instance().getDatabaseConfiguration();
        dbURL = config.getDatabaseURL();
        dbUserName = config.getDatabaseUserName();
        dbPassword = config.getDatabasePassword();
    }

    public static ConnectionManager instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new ConnectionManager();
        }
        return uniqueInstance;
    }

    public Connection getDBConnection() {
        try {
            return DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        } catch (SQLException e) {
            GroupFormationToolLogs.log(Level.SEVERE, "Database can not be accessed", e);
            throw new DatabaseAccessException("Database is temporarily down. Please try again later");
        }
    }
}
