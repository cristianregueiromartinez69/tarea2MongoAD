import com.mongodb.client.MongoDatabase;
import config.MongoDbConnection;
import controller.Controller;
import service.LecturaDatosJson;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        new Controller().logicaPrograma();

    }
}
