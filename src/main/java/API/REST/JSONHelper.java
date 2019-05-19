package API.REST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONHelper {

    public static String getJSONFromObject(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
//            StringWriter sw = new StringWriter();
            return "Error";/* + e.printStackTrace();*/
        }
    }

    public static Object getObjectFromJSON(String json, Class objectClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, objectClass);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
