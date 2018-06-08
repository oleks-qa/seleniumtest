import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Log {

    public Logger logger;

    public Log() {
        logger = Logger.getLogger(Driver.class.getName());
    }

    public void setFileName(String fileName) {
        try {
            logger.addHandler( new FileHandler(fileName + ".log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
