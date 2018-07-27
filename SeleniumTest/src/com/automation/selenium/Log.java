import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    private Logger logger;
    private boolean logEnabled;
    private static Log log;

    private Log(Driver driver) {
        this.logEnabled = driver.logEnabled;
        logger = Logger.getLogger(Driver.class.getName());
    }

    public static Log getInstance(Driver driver) {
        if (log == null)
            log = new Log(driver);
        return log;
    }

    public void setFileName(String fileName) {
        if (!logEnabled)
            return;
        try {
            logger.addHandler( new FileHandler(fileName + ".log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(Level level, String message) {
        if (!logEnabled)
            return;
        logger.log(level, message);
    }



}
