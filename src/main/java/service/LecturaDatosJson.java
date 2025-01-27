package service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LecturaDatosJson {

    public List<Map<String, Object>> readDataJson(String path){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(new File(path), mapper.getTypeFactory().constructCollectionType(List.class, Map.class));
        } catch (IOException e) {
            System.out.println("Ups, error al leer el " + path);
        }
        return null;
    }

    public void readValuesFromDb(List<Map<String, Object>> objectMap){
        for (Map<String, Object> map : objectMap) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                System.out.println("Clave: " + key + ", Valor: " + value);
            }
            System.out.println("-----------X----------");
        }
    }
}
