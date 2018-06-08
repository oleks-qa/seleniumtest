import org.json.JSONObject;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class Json {

    public JSONObject jsonObj;

    public Json(String fileName) {
        File file = new File(fileName);
        String jsonString = "";
        try {
             jsonString = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonObj = new JSONObject(jsonString);
    }

}
