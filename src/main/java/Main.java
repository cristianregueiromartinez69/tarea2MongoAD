import com.mongodb.client.MongoDatabase;
import config.MongoDbConnection;
import service.LecturaDatosJson;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        LecturaDatosJson lecturaDatosJson = new LecturaDatosJson();
        List<Map<String, Object>> ejemplo = lecturaDatosJson.readDataJson("pais.json");

        for (Map<String, Object> map : ejemplo) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                System.out.println("Clave: " + key + ", Valor: " + value);
            }
            System.out.println("--------------------");
        }

    }
}
