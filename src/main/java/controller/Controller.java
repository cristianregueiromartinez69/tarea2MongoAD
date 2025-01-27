package controller;

import service.Crud;
import service.LecturaDatosJson;

import java.util.List;
import java.util.Map;

public class Controller {

    public void logicaPrograma(){
        LecturaDatosJson lecturaDatosJson = new LecturaDatosJson();
        Crud crud = new Crud();

        /* insercciones
        List<Map<String, Object>> paisesList = lecturaDatosJson.readDataJson("pais.json");
        List<Map<String, Object>> presidentesList = lecturaDatosJson.readDataJson("presidente.json");
         */

        //lectura de datos
        List<Map<String, Object>> paisesList = crud.getListaDePaises();
        List<Map<String, Object>> presidentesList = crud.getListaDePresidentes();

        lecturaDatosJson.readValuesFromDb(paisesList);
        lecturaDatosJson.readValuesFromDb(presidentesList);




    }
}
