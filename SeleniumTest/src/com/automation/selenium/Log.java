import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    private Logger logger;
    private Driver driver;

    public Log(Driver driver) {
        this.driver = driver;
        logger = Logger.getLogger(Driver.class.getName());
    }

    public void setFileName(String fileName) {
        if (!driver.logEnabled)
            return;
        try {
            logger.addHandler( new FileHandler(fileName + ".log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(Level level, String message) {
        if (!driver.logEnabled)
            return;
        logger.log(level, message);
    }



}
