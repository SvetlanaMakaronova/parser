import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

public class Config {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    public static Properties property;

    public static String outputFile;
    public static String dbPath;
    public static String logConfig;

    static {
        property = readFile();

        outputFile = getParameter("outputFile");
        dbPath = getParameter("DB_PATH");
        logConfig = getParameter("LOG_CONFIG");
    }

    private static Properties readFile() {

        Properties tempProperty = new Properties();

        try (FileInputStream input = new FileInputStream(Config.PATH_TO_PROPERTIES)) {
            tempProperty.load(input);
        } catch (IOException e) {
            Logger.LOGGER.log(Level.WARNING, PATH_TO_PROPERTIES + " not found: " + e );
        }
        return tempProperty;
    }

    private static String getParameter(String parameterName){
        return property.getProperty(parameterName) != null ? property.getProperty(parameterName) : System.getenv(parameterName);
    }

}
