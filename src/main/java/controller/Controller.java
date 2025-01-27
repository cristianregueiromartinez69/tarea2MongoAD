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


        /*
        List<Map<String, Object>> paisesList = crud.getListaDePaises();
        List<Map<String, Object>> presidentesList = crud.getListaDePresidentes();

        lecturaDatosJson.readValuesFromDb(paisesList);
        lecturaDatosJson.readValuesFromDb(presidentesList);
        */

        /* update de los datos
        crud.updateDataPaises("España", "Junts", "dictadura");
        crud.updateDataPresidentes("Pedro Sánchez", 10);
         */

        /* nueva lectura de la base de datos
        List<Map<String, Object>> paisesList = crud.getListaDePaises();
        List<Map<String, Object>> presidentesList = crud.getListaDePresidentes();

        lecturaDatosJson.readValuesFromDb(paisesList);
        lecturaDatosJson.readValuesFromDb(presidentesList);
         */







    }
}
