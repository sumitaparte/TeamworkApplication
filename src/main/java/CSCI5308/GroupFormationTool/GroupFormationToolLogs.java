package CSCI5308.GroupFormationTool;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.*;

public class GroupFormationToolLogs {
    static Logger logger;
    private Handler fileHandler;
    Formatter plainText;

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GroupFormationToolLogs() throws IOException {
        String path = "logs/";
        if (!Files.exists(Paths.get(path))) {
            Files.createDirectories(Paths.get(path));
        }
        logger = Logger.getLogger(GroupFormationToolLogs.class.getName());
        LocalDate date = LocalDate.now();
        String logFileName = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(date);
        fileHandler = new FileHandler(path + logFileName + ".log", true);
        fileHandler.setLevel(Level.parse(properties.getProperty("spring.logging.level")));
        plainText = new SimpleFormatter();
        fileHandler.setFormatter(plainText);
        logger.addHandler(fileHandler);
    }

    private static Logger getLogger() {
        if (logger == null) {
            try {
                new GroupFormationToolLogs();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logger;
    }

    public static void log(Level level, String msg, Exception e) {
        getLogger().log(level, msg, e);
    }

}
