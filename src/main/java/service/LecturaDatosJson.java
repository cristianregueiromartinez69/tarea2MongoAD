package service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Clase con los metodos para leer el archuvo .json y lectura de datos de la base de datos
 * @author cristian
 * @version 1.0
 */
public class LecturaDatosJson {

    /**
     * Metodo que lee los datos de un .json
     * @param path el path donde está el .json
     * @return una lista con los datos del archivo
     */
    public List<Map<String, Object>> readDataJson(String path){
        /*
          Explicacion:
          1. Creamos un objectMapper
          2. llamamos al metodo readValue que recibe el path por parámetro
          3. el metodo recibe 2 argumentos, el path y otro metodo para transformar la información del archivo en una lista
         */
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(new File(path), mapper.getTypeFactory().constructCollectionType(List.class, Map.class));
        } catch (IOException e) {
            System.out.println("Ups, error al leer el " + path);
        }
        return null;
    }

    /**
     * Metodo que lee una lista que proviene de la base de datos de mongo
     * @param objectMap la lista que le pasamos por parámetro
     */
    public void readValuesFromDb(List<Map<String, Object>> objectMap){

        //recorremos la lista con un bucle for anidado y sacamos la información por pantalla
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
