import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

    private Logger logger;
    private boolean isEnabled;
    private static Log log;

    private Log(boolean isEnabled) {
        this.isEnabled = isEnabled;
        logger = Logger.getLogger(Driver.class.getName());
    }

    public static Log getInstance(boolean isEnabled) {
        if (log == null)
            log = new Log(isEnabled);
        return log;
    }

    public void setFileName(String fileName) {
        if (!isEnabled)
            return;
        try {
            logger.addHandler( new FileHandler(fileName + ".log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(Level level, String message) {
        if (!isEnabled)
            return;
        logger.log(level, message);
    }



}
