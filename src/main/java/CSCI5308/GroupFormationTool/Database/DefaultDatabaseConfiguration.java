package CSCI5308.GroupFormationTool.Database;

import CSCI5308.GroupFormationTool.GroupFormationToolLogs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class DefaultDatabaseConfiguration implements IDatabaseConfiguration {
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            GroupFormationToolLogs.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public String getDatabaseUserName() {
        USER = properties.getProperty("spring.datasource.username");
        return USER;
    }

    public String getDatabasePassword() {
        PASSWORD = properties.getProperty("spring.datasource.password");
        return PASSWORD;
    }

    public String getDatabaseURL() {
        URL = properties.getProperty("spring.datasource.url");
        return URL;
    }
}
